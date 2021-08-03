package com.essentials.business.technical.dao.database;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.Token;

public class AuthenticationDAO extends BaseDAO {

    private static final String TABLE_NAME = "authentication";
    private static AuthenticationDAO authenticationDAO;
    private static final String TOKEN_COLUMN = "token";
    private static final String TIME_COLUMN = "time";
    private static final long EXPIRATION_TIME = 5000; // Milliseconds (1 min)

    private AuthenticationDAO() throws TBEServerException {
        super(TABLE_NAME);
    }

    public synchronized static AuthenticationDAO getInstance() throws TBEServerException {
        if (authenticationDAO == null) {
            authenticationDAO = new AuthenticationDAO();
        }

        return authenticationDAO;
    }

    public Token get(String token) throws TBEServerException {
        Item item = table.getItem(TOKEN_COLUMN, token);

        if (item == null) {
            throw new TBEBadRequestException("Token does not exist.");
        }

        return new Token(item.getString(TOKEN_COLUMN), item.getLong(TIME_COLUMN));
    }

    public Token post(String token) throws TBEServerException {
        Item item = new Item()
                .withString(TOKEN_COLUMN, token)
                .withLong(TIME_COLUMN, System.currentTimeMillis() + EXPIRATION_TIME);

        try {
            table.putItem(item);
        } catch (Exception e) {
            throw new TBEInternalServerErrorException("Unable to add token.", e);
        }

        return new Token(token, System.currentTimeMillis() + EXPIRATION_TIME);
    }

    public void update(String token) throws TBEServerException {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(TOKEN_COLUMN, token)
                .withUpdateExpression("set time = :t")
                .withValueMap(new ValueMap().withLong(":t", System.currentTimeMillis() + EXPIRATION_TIME));

        try {
            table.updateItem(updateItemSpec);
        } catch (Exception e) {
            throw new TBEInternalServerErrorException("Unable to update token", e);
        }
    }

    public void delete(String token) throws TBEServerException {
        try {
            table.deleteItem(TOKEN_COLUMN, token);
        } catch (Exception e) {
            throw new TBEInternalServerErrorException("Unable to delete token", e);
        }

    }
}
