package com.essentials.business.technical;

import com.essentials.business.technical.handler.FortuneFIvehundredCompaniesHandler;
import com.essentials.business.technical.handler.TestHandler;
import com.essentials.business.technical.utils.ServerResponse;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

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
        server.createContext("/fortunefivehundred/companies/years", new FortuneFIvehundredCompaniesHandler());
    }
}
