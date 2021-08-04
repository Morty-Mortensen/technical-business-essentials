package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.dao.database.AuthenticationDAO;
import com.essentials.business.technical.dao.database.UsersDAO;
import com.essentials.business.technical.model.Token;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.LogoutUserRequest;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.utils.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class UserService {

    public User postUser(PostUserRequest request) throws TBEServerException {
        String salt = StringUtils.generateUuid();
        String saltAndHashedPassword = salt + '$' + getSaltAndHashedPassword(request.getPassword(), salt);
        request.setPassword(saltAndHashedPassword);
        User user = UsersDAO.getInstance().post(request);
        Token token = AuthenticationDAO.getInstance().post(StringUtils.generateUuid());
        user.setToken(token);
        return user;
    }

    public User validateUser(ValidateUserRequest request) throws TBEServerException {
        User user = UsersDAO.getInstance().getWithPassword(request.getEmail());
        String[] saltAndHashedPassword = user.getPassword().split("\\$");
        String salt = saltAndHashedPassword[0];
        String originalHashedPassword = saltAndHashedPassword[1];
        String requestSaltAndHashedPassword = getSaltAndHashedPassword(request.getPassword(), salt);

        if (!originalHashedPassword.equals(requestSaltAndHashedPassword)) {
            throw new TBEBadRequestException("Invalid credentials.");
        }

        Token token = AuthenticationDAO.getInstance().post(StringUtils.generateUuid());
        user.setToken(token);
        return user;
    }

    private String getSaltAndHashedPassword(String password, String salt) throws TBEServerException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException ex) {
            throw new TBEInternalServerErrorException("Unable to get MessageDigest salting/hashing algorithm.", ex);
        }
        md.update(salt.getBytes());
        byte[] hashedPassword = md.digest(password.getBytes());

        return bytesToHex(hashedPassword);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
