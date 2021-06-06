package com.essentials.business.technical.handler;

import com.essentials.business.technical.utils.Serializer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class TestHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        sendStatusToClient(true, httpExchange);
        sendResultToClient("It has changed :)", httpExchange);
    }

    private void sendResultToClient(Object result, HttpExchange exchange) throws IOException {
        OutputStream responseBody = exchange.getResponseBody();
//        String jsonResult = Serializer.serialize(result);
        String jsonResult = (String) result;
        writeString(jsonResult, responseBody);
        responseBody.close();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(str);
        bw.flush();
    }

    private void sendStatusToClient(boolean isSuccessful, HttpExchange exchange) throws IOException {
        if (isSuccessful) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    }
}
