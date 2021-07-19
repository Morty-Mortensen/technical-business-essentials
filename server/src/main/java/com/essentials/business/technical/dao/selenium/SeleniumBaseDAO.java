package com.essentials.business.technical.dao.selenium;

import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class SeleniumBaseDAO implements AutoCloseable {
    private final ChromeOptions options;
    protected WebDriver driver;

    public SeleniumBaseDAO() throws DataAccessException {
        try {
            String chromeDriverLocation = "chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
            ChromeOptions options = new ChromeOptions();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            options.setCapability("goog:loggingPrefs", logPrefs);
            options.addArguments("headless");
            this.options = options;
        } catch (Exception ex) {
            throw new DataAccessException("Unable to initialize the driver options", ex, ErrorType.INTERNAL_SERVER_ERROR);
        }

    }

    public void init() throws DataAccessException {
        try {
            driver = new ChromeDriver(options);
        } catch (Exception ex) {
            throw new DataAccessException("Unable to start the driver.", ex, ErrorType.INTERNAL_SERVER_ERROR);
        }
    }

    public void close() throws DataAccessException {
        try {
            driver.quit();
            driver = null;
        } catch (Exception ex) {
            throw new DataAccessException("Unable to terminate the driver.", ex, ErrorType.INTERNAL_SERVER_ERROR);
        }
    }
}
