package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.model.request.LogoutUserRequest;

public class AuthenticationService {

    public void logoutUser(LogoutUserRequest request) throws TBEServerException {
        AuthenticationDAO.getInstance().delete(request.getToken());
    }
}
