package com.essentials.business.technical.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class ServerResponse {

    public static void sendResultToClient(Object result, HttpExchange exchange) throws IOException {
        sendStatusToClient(HttpURLConnection.HTTP_OK, exchange);
        writeData(result, exchange.getResponseBody());
    }

    public static void sendErrorToClient(Exception exception, HttpExchange exchange) throws IOException {
        if (exception.getMessage().toUpperCase().contains("BAD REQUEST")) {
            sendStatusToClient(HttpURLConnection.HTTP_BAD_REQUEST, exchange);
        } else {
            sendStatusToClient(HttpURLConnection.HTTP_INTERNAL_ERROR, exchange);
        }
        writeData(exception, exchange.getResponseBody());
    }

    private static void writeData(Object result, OutputStream responseBody) throws IOException {
        String jsonResult = Serializer.serialize(result);
        OutputStreamWriter sw = new OutputStreamWriter(responseBody);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(jsonResult);
        bw.flush();
        responseBody.close();
    }

    private static void sendStatusToClient(int statusCode, HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        exchange.getResponseHeaders().add("Access-Control-Max-Age", "3600");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
        exchange.sendResponseHeaders(statusCode, 0);
    }
}
