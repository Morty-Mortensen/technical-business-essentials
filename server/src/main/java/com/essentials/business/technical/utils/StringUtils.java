package com.essentials.business.technical.utils;

import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StringUtils {
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static Map<String, String> getQueryParams(String queryString) throws TBEServerException {
        Map<String, String> queryParams = new HashMap<>();
        if (queryString != null) {
            String[] params = queryString.split("=");
            for (int i = 0; i < params.length; i = i + 2) {
                queryParams.put(params[i], params[i + 1]);
            }
        } else {
            throw new TBEBadRequestException("No query params when query params were expected.");
        }

        return queryParams;
    }
}
