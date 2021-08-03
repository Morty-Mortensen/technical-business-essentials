package com.essentials.business.technical.dao.selenium;

import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEServerException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class SeleniumBaseDAO implements AutoCloseable {
    private final ChromeOptions options;
    protected WebDriver driver;

    public SeleniumBaseDAO() throws TBEServerException {
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
            throw new TBEInternalServerErrorException("Unable to initialize the driver options", ex);
        }

    }

    public void init() throws TBEServerException {
        try {
            driver = new ChromeDriver(options);
        } catch (Exception ex) {
            throw new TBEInternalServerErrorException("Unable to start the driver.", ex);
        }
    }

    public void close() throws TBEServerException {
        try {
            driver.quit();
            driver = null;
        } catch (Exception ex) {
            throw new TBEInternalServerErrorException("Unable to terminate the driver.", ex);
        }
    }
}
