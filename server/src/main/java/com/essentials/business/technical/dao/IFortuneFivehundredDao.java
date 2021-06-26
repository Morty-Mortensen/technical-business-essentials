package com.essentials.business.technical.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IFortuneFivehundredDao {
    Map<String, String> getCompanyUrls(List<String> years);
}
