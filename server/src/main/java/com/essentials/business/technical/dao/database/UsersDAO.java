package com.essentials.business.technical.dao.database;

import com.amazonaws.services.dynamodbv2.document.*;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;

import java.util.Collections;
import java.util.List;

public class UsersDAO extends BaseDAO {
    private static final String TABLE_NAME = "users";
    private static UsersDAO usersDAO;
    private static final String EMAIL_COLUMN = "email";
    private static final String FIRST_NAME_COLUMN = "firstName";
    private static final String LAST_NAME_COLUMN = "lastName";
    private static final String PASSWORD = "password";

    private UsersDAO() throws TBEServerException {
        super(TABLE_NAME);
    }

    public synchronized static UsersDAO getInstance() throws TBEServerException {
        if (usersDAO == null) {
            usersDAO = new UsersDAO();
        }

        return usersDAO;
    }

    public User get(String email) throws TBEServerException {
        Item item = table.getItem(EMAIL_COLUMN, email);

        if (item == null) {
            throw new TBEBadRequestException("User does not exist.");
        }

        return new User(item.getString(EMAIL_COLUMN), item.getString(FIRST_NAME_COLUMN), item.getString(LAST_NAME_COLUMN));
    }

    public User getWithPassword(String email) throws TBEServerException {
        Item item = table.getItem(EMAIL_COLUMN, email);

        if (item == null) {
            throw new TBEBadRequestException("User does not exist.");
        }

        return new User(item.getString(EMAIL_COLUMN), item.getString(PASSWORD), item.getString(FIRST_NAME_COLUMN), item.getString(LAST_NAME_COLUMN));
    }

    public User post(PostUserRequest request) throws TBEServerException {
        Item item = new Item()
                .withPrimaryKey(EMAIL_COLUMN, request.getUser().getEmail())
                .withString(FIRST_NAME_COLUMN, request.getUser().getFirstName())
                .withString(LAST_NAME_COLUMN, request.getUser().getLastName())
                .withString(PASSWORD, request.getPassword());

        try {
            table.putItem(item);
        } catch (Exception e) {
            throw new TBEInternalServerErrorException("Unable to add user.");
        }

        return new User(request.getUser().getEmail(), request.getUser().getFirstName(), request.getUser().getLastName());
    }

    public List<User> getAll() {
//        // Normal GET-ALL
//        ItemCollection<ScanOutcome> outcome = table.scan();
//
//        // GET-ALL WHERE email IN (Amazon S3, Amazon DynamoDB)
//        TableKeysAndAttributes forumTableKeysAndAttributes = new TableKeysAndAttributes("users");
//        forumTableKeysAndAttributes.addHashOnlyPrimaryKeys("email",
//                "Amazon S3",
//                "Amazon DynamoDB");
//        BatchGetItemOutcome outcome = dynamoDB.batchGetItem(
//                forumTableKeysAndAttributes);
//        for (String tableName : outcome.getTableItems().keySet()) {
//            System.out.println("Items in table " + tableName);
//            List<Item> items = outcome.getTableItems().get(tableName);
//            for (Item item : items) {
//                System.out.println(item);
//            }
//        }
        return Collections.emptyList();
    }

    public User update(User user) {

//        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 121)
//                .withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "Tyler"))
//                .withValueMap(new ValueMap().withString(":val1", "No Longer Tyler")).withReturnValues(ReturnValue.ALL_NEW);
//
//        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
        return null;
    }

    public boolean delete(String email) {
        DeleteItemOutcome outcome = table.deleteItem("email", email);
        return false;
    }
}
