package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.database.exception.DataAccessException;
import com.essentials.business.technical.dao.selenium.SeleniumFortuneFiveHundredDao;

import java.util.List;
import java.util.Map;

public class FortuneFiveHundredService {

    public Map<String, String> getCompaniesUrls(List<String> years) throws DataAccessException {
        Map<String, String> compsByUrl;
        SeleniumFortuneFiveHundredDao dao = getFortuneFiveHundredDAO();
        dao.init();
        compsByUrl = dao.getCompanyUrls(years);
        dao.close();

        return compsByUrl;
    }

    public SeleniumFortuneFiveHundredDao getFortuneFiveHundredDAO() throws DataAccessException {
        return new SeleniumFortuneFiveHundredDao();
    }
}
