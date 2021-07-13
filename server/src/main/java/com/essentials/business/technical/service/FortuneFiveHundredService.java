package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.selenium.SeleniumFortuneFiveHundredDao;

import java.util.List;
import java.util.Map;

public class FortuneFiveHundredService {

    public Map<String, String> getCompaniesUrls(List<String> years) {
        SeleniumFortuneFiveHundredDao dao = getFortuneFiveHundredDAO();
        dao.init();
        Map<String, String> compsByUrl = dao.getCompanyUrls(years);
        dao.close();

        return compsByUrl;
    }

    public SeleniumFortuneFiveHundredDao getFortuneFiveHundredDAO() {
        return new SeleniumFortuneFiveHundredDao();
    }
}
