package com.essentials.business.technical.dao.database;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.model.Token;

public class AuthenticationDAO extends BaseDAO {

    private static final String TABLE_NAME = "authentication";
    private static AuthenticationDAO authenticationDAO;
    private static final String TOKEN_COLUMN = "token";
    private static final String TIME_COLUMN = "time";
    private static final long EXPIRATION_TIME = 5000; // Milliseconds (1 min)

    private AuthenticationDAO() throws DataAccessException {
        super(TABLE_NAME);
    }

    public synchronized static AuthenticationDAO getInstance() throws DataAccessException {
        if (authenticationDAO == null) {
            authenticationDAO = new AuthenticationDAO();
        }

        return authenticationDAO;
    }

    public Token get(String token) throws DataAccessException {
        Item item = table.getItem(TOKEN_COLUMN, token);

        if (item == null) {
            throw new DataAccessException("Token does not exist.", ErrorType.BAD_REQUEST);
        }

        return new Token(item.getString(TOKEN_COLUMN), item.getLong(TIME_COLUMN));
    }

    public Token post(String token) throws DataAccessException {
        Item item = new Item()
                .withString(TOKEN_COLUMN, token)
                .withLong(TIME_COLUMN, System.currentTimeMillis() + EXPIRATION_TIME);

        try {
            table.putItem(item);
        } catch (Exception e) {
            throw new DataAccessException("Unable to add token.", e, ErrorType.INTERNAL_SERVER_ERROR);
        }

        return new Token(token, System.currentTimeMillis() + EXPIRATION_TIME);
    }

    public void update(String token) throws DataAccessException {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(TOKEN_COLUMN, token)
                .withUpdateExpression("set time = :t")
                .withValueMap(new ValueMap().withLong(":t", System.currentTimeMillis() + EXPIRATION_TIME));

        try {
            table.updateItem(updateItemSpec);
        } catch (Exception e) {
            throw new DataAccessException("Unable to update token", e, ErrorType.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(String token) throws DataAccessException {
        try {
            table.deleteItem(TOKEN_COLUMN, token);
        } catch (Exception e) {
            throw new DataAccessException("Unable to delete token", e, ErrorType.INTERNAL_SERVER_ERROR);
        }

    }
}
