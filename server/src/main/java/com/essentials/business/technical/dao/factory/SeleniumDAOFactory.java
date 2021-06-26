package com.essentials.business.technical.dao.factory;

import com.essentials.business.technical.dao.IFortuneFivehundredDao;
import com.essentials.business.technical.dao.SeleniumFortuneFivehundredDao;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class SeleniumDAOFactory implements DAOFactory {
    private final WebDriver driver;

    public SeleniumDAOFactory() {
        String chromeDriverLocation = "chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        options.addArguments("headless");
        driver = new ChromeDriver(options);
    }

    @Override
    public IFortuneFivehundredDao getFortuneFivehundredDao() {
        return new SeleniumFortuneFivehundredDao(driver);
    }

}
