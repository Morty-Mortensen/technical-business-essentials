package com.essentials.business.technical.dao.http;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.dao.http.utils.ClientCommunicator;
import com.essentials.business.technical.model.request.ActivityCodeCompileRequest;
import com.essentials.business.technical.model.response.ActivityCodeCompileResponse;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class JDoodleDAO {
    private static final String SERVER_URL = "https://api.jdoodle.com/v1";
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    public ActivityCodeCompileResponse compileCode(ActivityCodeCompileRequest request, String userAgent, String urlPath) throws TBEServerException {
        request.setApiKeys();
        Map<String, String> headers = new HashMap<String, String>() {{
            put(HttpHeaders.USER_AGENT, userAgent);
        }};
        ActivityCodeCompileResponse response = clientCommunicator.doPost(urlPath, request, headers, ActivityCodeCompileResponse.class);
        return response;
    }
}
