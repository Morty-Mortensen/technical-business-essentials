package com.essentials.business.technical.handler;

import com.essentials.business.technical.dao.factory.SeleniumDAOFactory;
import com.essentials.business.technical.service.FortuneFivehundredService;
import com.essentials.business.technical.utils.ServerResponse;
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
            Headers headers = httpExchange.getRequestHeaders();
            List<String> years = Arrays.asList(headers.get("years").get(0).split(","));
            System.out.println("Years: ");
            System.out.println(years);
//            List<String> years = Arrays.asList("2020", "2021");
            FortuneFivehundredService service = getFortuneFivehundredService();
            Map<String, String> companiesUrlsByYear = service.getCompaniesUrls(years);

            for (String year : companiesUrlsByYear.keySet()) {
                String url = companiesUrlsByYear.get(year);
                System.out.println("The " + year + " url: " + url);
            }


            ServerResponse.sendResultToClient(companiesUrlsByYear, httpExchange);
        } catch (Exception e) {
            ServerResponse.sendErrorToClient(e, httpExchange);
        }
    }

    private FortuneFivehundredService getFortuneFivehundredService() {
        return new FortuneFivehundredService(new SeleniumDAOFactory());
    }
}
