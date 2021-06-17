package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.SeleniumDAOFactory;
import com.essentials.business.technical.dao.interfaces.DAOFactory;
import com.essentials.business.technical.dao.interfaces.IFortuneFivehundredDao;

import java.util.List;
import java.util.Map;

public class FortuneFivehundredService {
    private DAOFactory factory;

    public FortuneFivehundredService(DAOFactory factory) {
        this.factory = factory;
    }

    public Map<String, String> getCompaniesUrls(List<String> years) {
        IFortuneFivehundredDao dao = factory.getFortuneFiveHundredDao();
        return dao.getUrlsByYears(years);
    }
}
