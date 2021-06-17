package com.essentials.business.technical.handler;

import com.essentials.business.technical.utils.ServerResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;

public class TestHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            ServerResponse.sendResultToClient("\"Backend Update\"", httpExchange);
        } catch (Exception e) {
            ServerResponse.sendErrorToClient(e, httpExchange);
        }
    }
}
