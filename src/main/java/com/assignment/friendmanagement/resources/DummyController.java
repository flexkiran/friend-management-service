package com.assignment.friendmanagement.resources;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @ApiOperation(value = "Returns Api Version")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful Version retrieval")
            }
    )
    @RequestMapping(method = {RequestMethod.GET}, value = {"/version"})
    public String getVersion() {
        return "1.0";
    }
}