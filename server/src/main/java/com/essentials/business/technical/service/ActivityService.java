package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.dao.http.JDoodleDAO;
import com.essentials.business.technical.model.request.ActivityCodeCompileRequest;
import com.essentials.business.technical.model.response.ActivityCodeCompileResponse;

import java.util.HashMap;
import java.util.Map;

public class ActivityService {

    private static final String JAVA = "java";
    private static final String JDK_1_8_0_66 = "0";

    private static final String PYTHON3 = "python3";
    private static final String PYTHON_3_5_1 = "0";

    private static final String NODEJS = "nodejs";
    private static final String NODEJS_12_11_1 = "3";

    private static final Map<String, ActivityCodeCompileRequest> validRequests = new HashMap<String, ActivityCodeCompileRequest>() {{
        put(JAVA, new ActivityCodeCompileRequest(JAVA, JDK_1_8_0_66));
        put(PYTHON3, new ActivityCodeCompileRequest(PYTHON3, PYTHON_3_5_1));
        put(NODEJS, new ActivityCodeCompileRequest(NODEJS, NODEJS_12_11_1));
    }};

    private static final String COMPILE_URL = "/execute";

    public ActivityCodeCompileResponse compileCode(ActivityCodeCompileRequest request, String userAgent) throws TBEServerException {
        JDoodleDAO dao = getJDoodleDao();
        if (isValidateRequest(request)) {
            return dao.compileCode(request, userAgent, COMPILE_URL);
        } else {
            throw new TBEBadRequestException(String.format("Unsupported language [%s] or version [%s]", request.getLanguage(), request.getVersionIndex()));
        }
    }

    public JDoodleDAO getJDoodleDao() {
        return new JDoodleDAO();
    }

    public boolean isValidateRequest(ActivityCodeCompileRequest request) {
        return (validRequests.containsKey(request.getLanguage())
                && validRequests.get(request.getLanguage()).getVersionIndex().equals(request.getVersionIndex()));
    }
}
