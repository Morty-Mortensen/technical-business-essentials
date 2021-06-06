package com.essentials.business.technical.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class ServerResponse {

    public static void sendResultToClient(Object result, int statusCode, HttpExchange exchange) throws IOException {
        sendStatusToClient(statusCode, exchange);
        writeData(result, exchange.getResponseBody());
    }

    public static void sendErrorToClient(Exception exception, HttpExchange exchange) throws IOException {
        if (exception.getMessage().toLowerCase().contains("Bad Request")) {
            sendStatusToClient(HttpURLConnection.HTTP_BAD_REQUEST, exchange);
        } else {
            sendStatusToClient(HttpURLConnection.HTTP_INTERNAL_ERROR, exchange);
        }
        writeData(exception, exchange.getResponseBody());
    }

    private static void writeData(Object result, OutputStream responseBody) throws IOException {
//        String jsonResult = Serializer.serialize(result);
        String jsonResult = (String) result;
        OutputStreamWriter sw = new OutputStreamWriter(responseBody);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(jsonResult);
        bw.flush();
        responseBody.close();
    }

    private static void sendStatusToClient(int statusCode, HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(statusCode, 0);
    }
}
