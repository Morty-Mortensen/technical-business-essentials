package com.essentials.business.technical.dao;

import java.util.List;
import java.util.Map;

public interface IFortuneFivehundredDao {
    Map<String, String> getUrlsByYears(List<String> years);
}
