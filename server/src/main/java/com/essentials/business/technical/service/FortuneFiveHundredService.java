package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.selenium.SeleniumFortuneFiveHundredDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FortuneFiveHundredService {
    private final SeleniumFortuneFiveHundredDao seleniumFortuneFiveHundredDao;

    @Autowired
    public FortuneFiveHundredService(SeleniumFortuneFiveHundredDao seleniumFortuneFiveHundredDao) {
        this.seleniumFortuneFiveHundredDao = seleniumFortuneFiveHundredDao;
    }

    public Map<String, String> getCompaniesUrls(List<String> years) {
        seleniumFortuneFiveHundredDao.init();
        Map<String, String> compsByUrl = seleniumFortuneFiveHundredDao.getCompanyUrls(years);
        seleniumFortuneFiveHundredDao.close();
        
        return compsByUrl;
    }
}
