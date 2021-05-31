package com.essentials.business.technical;

import com.essentials.business.technical.handler.TestHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(serverAddress, 5);
        registerHandlers(server);
        server.start();
        System.out.println("Listening on port " + 8080);
    }

    private static void registerHandlers(HttpServer server) {
        server.createContext("/test", new TestHandler());
    }
}
