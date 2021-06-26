package com.essentials.business.technical.service;

import com.essentials.business.technical.dao.IFortuneFivehundredDao;
import com.essentials.business.technical.dao.factory.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FortuneFivehundredService {
    private DAOFactory factory;

    @Autowired
    public FortuneFivehundredService(DAOFactory factory) {
        this.factory = factory;
    }

    public Map<String, String> getCompaniesUrls(List<String> years) {
        IFortuneFivehundredDao dao = factory.getFortuneFivehundredDao();
        return dao.getCompanyUrls(years);
    }
}
