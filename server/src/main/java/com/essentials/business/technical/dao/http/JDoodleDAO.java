package com.essentials.business.technical.dao.http;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.dao.http.utils.ClientCommunicator;
import com.essentials.business.technical.model.request.JDoodleCodeCompileRequest;
import com.essentials.business.technical.model.response.JDoodleCodeCompileResponse;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class JDoodleDAO {
    private static final String SERVER_URL = "https://api.jdoodle.com/v1";
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    public JDoodleCodeCompileResponse compileCode(JDoodleCodeCompileRequest request, String userAgent, String urlPath) throws TBEServerException {
        request.setApiKeys();
        Map<String, String> headers = new HashMap<String, String>() {{
            put(HttpHeaders.USER_AGENT, userAgent);
        }};
        JDoodleCodeCompileResponse response = clientCommunicator.doPost(urlPath, request, headers, JDoodleCodeCompileResponse.class);
        return response;
    }
}
