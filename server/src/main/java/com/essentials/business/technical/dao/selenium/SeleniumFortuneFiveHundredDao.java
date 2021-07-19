package com.essentials.business.technical.dao.selenium;

import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.exception.DataAccessException;
import org.json.JSONObject;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumFortuneFiveHundredDao extends SeleniumBaseDAO {

    public SeleniumFortuneFiveHundredDao() throws DataAccessException {
        super();
    }

    public Map<String, String> getCompanyUrls(List<String> years) throws DataAccessException {
        Map<String, String> urlsByYear = new HashMap<>();
        try {
            for (String year : years) {
                String targetUrl = "";
                driver.get("https://fortune.com/fortune500/" + year + "/search/");
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                LogEntries les = driver.manage().logs().get(LogType.PERFORMANCE);
                for (LogEntry le : les) {
                    try {
                        if (le.getMessage().contains("https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results")) {
                            targetUrl = (String) new JSONObject(le.getMessage())
                                    .getJSONObject("message")
                                    .getJSONObject("params")
                                    .getJSONObject("request")
                                    .get("url");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Not the target");
                    }
                }
                urlsByYear.put(year, targetUrl);
            }
        } catch (Exception ex) {
            throw new DataAccessException("Unable to retrieve fortune five-hundred companies.", ex, ErrorType.INTERNAL_SERVER_ERROR);
        }

        return urlsByYear;
    }
}
