package com.essentials.business.technical.handler;

import com.essentials.business.technical.dao.factory.SeleniumDAOFactory;
import com.essentials.business.technical.service.FortuneFivehundredService;
import com.essentials.business.technical.utils.ServerResponse;
import com.essentials.business.technical.utils.UrlManipulator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FortuneFIvehundredCompaniesHandler implements HttpHandler {
    // https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results?list_id=3040727&token=Zm9ydHVuZTpCcHNyZmtNZCN5SndjWkkhNHFqMndEOTM=
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            List<String> years = new UrlManipulator(httpExchange.getRequestURI().getQuery()).getCollection("years");
            FortuneFivehundredService service = getFortuneFivehundredService();
            Map<String, String> companiesUrlsByYear = service.getCompaniesUrls(years);
            ServerResponse.sendResultToClient(companiesUrlsByYear, httpExchange);
        } catch (Exception e) {
            ServerResponse.sendErrorToClient(e, httpExchange);
        }
    }

    private FortuneFivehundredService getFortuneFivehundredService() {
        return new FortuneFivehundredService(new SeleniumDAOFactory());
    }
}
