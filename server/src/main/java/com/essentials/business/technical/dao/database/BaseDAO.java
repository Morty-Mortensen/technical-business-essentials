package com.essentials.business.technical.dao.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEServerException;

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

    public BaseDAO(String tableName) throws TBEServerException {
        try {
            table = dynamoDB.getTable(tableName);
        } catch (Exception ex) {
            throw new TBEInternalServerErrorException("Error connecting to the database", ex);
        }
    }
}
