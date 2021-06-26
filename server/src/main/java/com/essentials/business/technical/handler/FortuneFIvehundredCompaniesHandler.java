package com.essentials.business.technical.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.essentials.business.technical.dao.factory.SeleniumDAOFactory;
import com.essentials.business.technical.service.FortuneFivehundredService;
import com.essentials.business.technical.utils.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FortuneFIvehundredCompaniesHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    // https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results?list_id=3040727&token=Zm9ydHVuZTpCcHNyZmtNZCN5SndjWkkhNHFqMndEOTM=
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        try {
            Map<String, String> queryParams = request.getQueryStringParameters();
            //List<String> years = new UrlManipulator(httpExchange.getRequestURI().getQuery()).getCollection("years");
            List<String> years = new ArrayList<>();
            FortuneFivehundredService service = getFortuneFivehundredService();
            Map<String, String> companiesUrlsByYear = service.getCompaniesUrls(years);
            ServerResponse.sendResultToClient(companiesUrlsByYear, null);
        } catch (Exception e) {
            System.out.println("Error");
//            ServerResponse.sendErrorToClient(e, null);
        }
        return new APIGatewayProxyResponseEvent();
    }

    private FortuneFivehundredService getFortuneFivehundredService() {
        return new FortuneFivehundredService(new SeleniumDAOFactory());
    }
}
