package com.essentials.business.technical.controller;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.request.JNoodleCodeCompileRequest;
import com.essentials.business.technical.model.response.JNoodleCodeCompileResponse;
import com.essentials.business.technical.service.JNoodleService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class JNoodleController {

    @PostMapping("jnoodle")
    public JNoodleCodeCompileResponse postCompileCode(@RequestBody JNoodleCodeCompileRequest request, @RequestHeader(HttpHeaders.USER_AGENT) String userAgent) throws TBEServerException {
        JNoodleService service = getJNoodleService();
        JNoodleCodeCompileResponse response = null;
        response = service.compileCode(request, userAgent);
        return response;
    }

    public JNoodleService getJNoodleService() {
        return new JNoodleService();
    }
}
