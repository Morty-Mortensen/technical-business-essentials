package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.database.UsersDAO;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;

public class UserService {

    public User postUser(PostUserRequest request) throws DataAccessException {
        return UsersDAO.getInstance().post(request);
    }

    public User validateUser(ValidateUserRequest request) throws DataAccessException {
        User user = UsersDAO.getInstance().getWithPassword(request.getEmail());

        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        return user;
    }
}
