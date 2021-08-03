package com.essentials.business.technical.controller;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.request.JDoodleCodeCompileRequest;
import com.essentials.business.technical.model.response.JDoodleCodeCompileResponse;
import com.essentials.business.technical.service.JDoodleService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class JDoodleController {

    @PostMapping("JDoodle")
    public JDoodleCodeCompileResponse postCompileCode(@RequestBody JDoodleCodeCompileRequest request, @RequestHeader(HttpHeaders.USER_AGENT) String userAgent) throws TBEServerException {
        JDoodleService service = getJDoodleService();
        JDoodleCodeCompileResponse response = null;
        response = service.compileCode(request, userAgent);
        return response;
    }

    public JDoodleService getJDoodleService() {
        return new JDoodleService();
    }
}
