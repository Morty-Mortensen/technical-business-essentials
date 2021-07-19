package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.dao.database.UsersDAO;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.model.Token;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.utils.StringGenerator;

public class UserService {

    public User postUser(PostUserRequest request) throws DataAccessException {
        User user = UsersDAO.getInstance().post(request);
        Token token = AuthenticationDAO.getInstance().post(StringGenerator.generator());
        user.setToken(token);
        return user;
    }

    public User validateUser(ValidateUserRequest request) throws DataAccessException {
        User user = UsersDAO.getInstance().getWithPassword(request.getEmail());

        if (!request.getPassword().equals(user.getPassword())) {
            throw new DataAccessException("Invalid credentials.", ErrorType.BAD_REQUEST);
        }

        Token token = AuthenticationDAO.getInstance().post(StringGenerator.generator());
        user.setToken(token);
        return user;
    }
}
