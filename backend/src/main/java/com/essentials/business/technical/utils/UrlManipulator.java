package com.essentials.business.technical.utils;

import com.google.gson.internal.Primitives;

import java.util.*;
import java.util.stream.Stream;

public class UrlManipulator {
    private final Map<String, Object> queryParams;

    public UrlManipulator(String queryParamString) {
        this.queryParams = parseQueryParameters(queryParamString);
    }

    public String get(String key) {
        return (String) queryParams.get(key);
    }

    public List<String> getCollection(String key) {
        return (List<String>) queryParams.get(key);
    }

    private Map<String, Object> parseQueryParameters(String queryParamString) {
        String[] params = queryParamString.split("&");

        Map<String, Object> queryParams = new HashMap<>();
        for (String param : params) {
            String[] individualParm = param.split("=");
            if (individualParm[1].contains(",")) {
                queryParams.put(individualParm[0], Arrays.asList(individualParm[1].split(",")));
            } else {
                queryParams.put(individualParm[0], individualParm[1]);
            }
        }

        return queryParams;
    }
}
