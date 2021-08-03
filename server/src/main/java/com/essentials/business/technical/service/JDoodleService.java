package com.essentials.business.technical.service;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.dao.http.JNoodleDAO;
import com.essentials.business.technical.model.request.JNoodleCodeCompileRequest;
import com.essentials.business.technical.model.response.JNoodleCodeCompileResponse;

import java.util.HashMap;
import java.util.Map;

public class JNoodleService {

    private static final String JAVA = "java";
    private static final String JDK_1_8_0_66 = "0";

    private static final String PYTHON3 = "python3";
    private static final String PYTHON_3_5_1 = "0";

    private static final String NODEJS = "nodejs";
    private static final String NODEJS_12_11_1 = "3";

    private static final Map<String, JNoodleCodeCompileRequest> validRequests = new HashMap<String, JNoodleCodeCompileRequest>() {{
        put(JAVA, new JNoodleCodeCompileRequest(JAVA, JDK_1_8_0_66));
        put(PYTHON3, new JNoodleCodeCompileRequest(PYTHON3, PYTHON_3_5_1));
        put(NODEJS, new JNoodleCodeCompileRequest(NODEJS, NODEJS_12_11_1));
    }};

    private static final String COMPILE_URL = "/execute";

    public JNoodleCodeCompileResponse compileCode(JNoodleCodeCompileRequest request, String userAgent) throws TBEServerException {
        JNoodleDAO dao = getJNoodleDao();
        if (isValidateRequest(request)) {
            return dao.compileCode(request, userAgent, COMPILE_URL);
        } else {
            throw new TBEBadRequestException("Invalid request");
        }

    }

    public JNoodleDAO getJNoodleDao() {
        return new JNoodleDAO();
    }

    public boolean isValidateRequest(JNoodleCodeCompileRequest request) {
        return (validRequests.containsKey(request.getLanguage())
                && validRequests.get(request.getLanguage()).getVersionIndex().equals(request.getVersionIndex()));
    }
}
