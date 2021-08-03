package com.essentials.business.technical.controller.middleware;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.controller.exception.TBEUnauthorizedException;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.model.Token;
import com.essentials.business.technical.utils.Cache;
import com.essentials.business.technical.utils.ExceptionHandler;
import com.essentials.business.technical.utils.StringUtils;
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
        try {
            token = StringUtils.getQueryParams(queryString).get(Token.TOKEN);
        } catch (Exception ex) {
            return ExceptionHandler.forwardException(request, response, new TBEUnauthorizedException(String.format("%s: No query params were sent, token required.", CacheMiddleware.class.getName()), ex));
        }


        if (token.equals("")) {
            return ExceptionHandler.forwardException(request, response, new TBEUnauthorizedException(String.format("%s: Token was not found.", CacheMiddleware.class.getName())));
        }

        long cookieTime = 0;
        try {
            cookieTime = AuthenticationDAO.getInstance().get(token).getTime();
        } catch (TBEServerException ex) {
            return ExceptionHandler.forwardException(request, response, new TBEUnauthorizedException(String.format("%s: Unable to retrieve time remaining for token.", CacheMiddleware.class.getName()), ex));
        }

        if (System.currentTimeMillis() > cookieTime) {
            Cache.getInstance().clear();
            AuthenticationDAO.getInstance().delete(token);
            return ExceptionHandler.forwardException(request, response, new TBEUnauthorizedException(String.format("%s: Token expired.", CacheMiddleware.class.getName())));
        }

        return true;
    }
}
