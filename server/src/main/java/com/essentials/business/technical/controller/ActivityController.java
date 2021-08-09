package com.essentials.business.technical.controller;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.request.ActivityCodeCompileRequest;
import com.essentials.business.technical.model.response.ActivityCodeCompileResponse;
import com.essentials.business.technical.service.ActivityService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class ActivityController {

    // TODO: Update to use {activityId} to verify correct.
    @PostMapping("activities/{activityId}/compile")
    public ActivityCodeCompileResponse postCompileCode(@PathVariable String activityId, @RequestBody ActivityCodeCompileRequest request, @RequestHeader(HttpHeaders.USER_AGENT) String userAgent) throws TBEServerException {
        ActivityService service = getActivityService();
        ActivityCodeCompileResponse response = service.compileCode(request, userAgent);
        return response;
    }

    public ActivityService getActivityService() {
        return new ActivityService();
    }
}
