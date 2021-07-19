package com.essentials.business.technical.controller.middleware;

import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.utils.Cache;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("http://localhost:4200")
public class CacheMiddleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] params = request.getQueryString().split("=");
        String token = "";
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("token")) {
                token = params[i + 1];
                break;
            }
        }

        if (token.equals("")) {
            request.getRequestDispatcher("/error/401").forward(request, response);
            return false;
        }

        long cookieTime = 0;
        try {
            cookieTime = AuthenticationDAO.getInstance().get(token).getTime();
        } catch (DataAccessException ex) {
            request.getRequestDispatcher("/error/401").forward(request, response);
            return false;
        }

        if (System.currentTimeMillis() > cookieTime) {
            Cache.getInstance().clear();
            AuthenticationDAO.getInstance().delete(token);
            request.getRequestDispatcher("/error/401").forward(request, response);
            return false;
        }

        return true;
    }
}
