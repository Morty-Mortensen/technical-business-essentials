package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.dao.database.UsersDAO;
import com.essentials.business.technical.model.Token;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.utils.StringUtils;

public class UserService {

    public User postUser(PostUserRequest request) throws TBEServerException {
        User user = UsersDAO.getInstance().post(request);
        Token token = AuthenticationDAO.getInstance().post(StringUtils.generateUuid());
        user.setToken(token);
        return user;
    }

    public User validateUser(ValidateUserRequest request) throws TBEServerException {
        User user = UsersDAO.getInstance().getWithPassword(request.getEmail());

        if (!request.getPassword().equals(user.getPassword())) {
            throw new TBEBadRequestException("Invalid credentials.");
        }

        Token token = AuthenticationDAO.getInstance().post(StringUtils.generateUuid());
        user.setToken(token);
        return user;
    }
}
