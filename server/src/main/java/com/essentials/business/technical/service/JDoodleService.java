package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.dao.http.JDoodleDAO;
import com.essentials.business.technical.model.request.JDoodleCodeCompileRequest;
import com.essentials.business.technical.model.response.JDoodleCodeCompileResponse;

import java.util.HashMap;
import java.util.Map;

public class JDoodleService {

    private static final String JAVA = "java";
    private static final String JDK_1_8_0_66 = "0";

    private static final String PYTHON3 = "python3";
    private static final String PYTHON_3_5_1 = "0";

    private static final String NODEJS = "nodejs";
    private static final String NODEJS_12_11_1 = "3";

    private static final Map<String, JDoodleCodeCompileRequest> validRequests = new HashMap<String, JDoodleCodeCompileRequest>() {{
        put(JAVA, new JDoodleCodeCompileRequest(JAVA, JDK_1_8_0_66));
        put(PYTHON3, new JDoodleCodeCompileRequest(PYTHON3, PYTHON_3_5_1));
        put(NODEJS, new JDoodleCodeCompileRequest(NODEJS, NODEJS_12_11_1));
    }};

    private static final String COMPILE_URL = "/execute";

    public JDoodleCodeCompileResponse compileCode(JDoodleCodeCompileRequest request, String userAgent) throws TBEServerException {
        JDoodleDAO dao = getJDoodleDao();
        if (isValidateRequest(request)) {
            return dao.compileCode(request, userAgent, COMPILE_URL);
        } else {
            throw new TBEBadRequestException("Invalid request");
        }

    }

    public JDoodleDAO getJDoodleDao() {
        return new JDoodleDAO();
    }

    public boolean isValidateRequest(JDoodleCodeCompileRequest request) {
        return (validRequests.containsKey(request.getLanguage())
                && validRequests.get(request.getLanguage()).getVersionIndex().equals(request.getVersionIndex()));
    }
}
