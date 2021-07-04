package com.essentials.business.technical.dao.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class SeleniumBaseDAO implements AutoCloseable {
    private final ChromeOptions options;
    protected WebDriver driver;

    public SeleniumBaseDAO() {
        String chromeDriverLocation = "chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        options.addArguments("headless");
        this.options = options;
    }

    public void init() {
        driver = new ChromeDriver(options);
    }

    public void close() {
        driver.quit();
        driver = null;
    }
}
