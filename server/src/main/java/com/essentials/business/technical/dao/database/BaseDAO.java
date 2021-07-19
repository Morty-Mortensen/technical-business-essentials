package com.essentials.business.technical.dao.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.exception.DataAccessException;

public class BaseDAO {
    private static final String REGION = "us-west-1";
    protected static final DynamoDB dynamoDB;
    protected Table table;

    static {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(REGION)
                .build();

        dynamoDB = new DynamoDB(client);
    }

    public BaseDAO(String tableName) throws DataAccessException {
        try {
            table = dynamoDB.getTable(tableName);
        } catch (Exception ex) {
            throw new DataAccessException("Error connecting to the database", ex, ErrorType.INTERNAL_SERVER_ERROR);
        }
    }
}
