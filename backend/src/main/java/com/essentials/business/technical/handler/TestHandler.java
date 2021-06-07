package com.essentials.business.technical.handler;

import com.essentials.business.technical.utils.ServerResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TestHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            ServerResponse.sendResultToClient("\"Backend Update\"", httpExchange);
        } catch (Exception e) {
            ServerResponse.sendErrorToClient(e, httpExchange);
        }
    }
}
