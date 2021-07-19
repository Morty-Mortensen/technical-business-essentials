package com.essentials.business.technical.controller.middleware;

import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.utils.Cache;
import com.essentials.business.technical.utils.ExceptionHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("http://localhost:4200")
public class CacheMiddleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String queryString = request.getQueryString();
        String token = "";

        if (queryString != null) {
            String[] params = queryString.split("=");
            for (int i = 0; i < params.length; i++) {
                if (params[i].equals("token")) {
                    token = params[i + 1];
                    break;
                }
            }
        }

        if (token.equals("")) {
            return ExceptionHandler.forwardException(request, response, CacheMiddleware.class.getName(), ErrorType.UNAUTHORIZED);
        }

        long cookieTime = 0;
        try {
            cookieTime = AuthenticationDAO.getInstance().get(token).getTime();
        } catch (DataAccessException ex) {
            return ExceptionHandler.forwardException(request, response, CacheMiddleware.class.getName(), ErrorType.UNAUTHORIZED);
        }

        if (System.currentTimeMillis() > cookieTime) {
            Cache.getInstance().clear();
            AuthenticationDAO.getInstance().delete(token);
            return ExceptionHandler.forwardException(request, response, CacheMiddleware.class.getName(), ErrorType.UNAUTHORIZED);
        }

        return true;
    }
}
