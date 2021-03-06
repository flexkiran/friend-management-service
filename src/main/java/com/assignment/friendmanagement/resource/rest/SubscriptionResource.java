package com.assignment.friendmanagement.resource.rest;

import com.assignment.friendmanagement.model.SubscriptionStatus;
import com.assignment.friendmanagement.model.request.SubscriptionRequest;
import com.assignment.friendmanagement.model.response.OperationSuccessResponse;
import com.assignment.friendmanagement.service.SubscriptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.assignment.friendmanagement.model.SubscriptionStatus.SUBSCRIBED;
import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;


@RestController
@RequestMapping("/api/notifications")
public class SubscriptionResource {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation(value = "Subscribe for notifications from email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "subscribed successfully"),
                    @ApiResponse(code = 2002, message = "Email address is not registered"),
                    @ApiResponse(code = 4001, message = "Already subscribed for updates"),
            }
    )
    @PostMapping("/subscribe")
    public OperationSuccessResponse subscribe(@Valid @RequestBody SubscriptionRequest subscriptionRequest)  {
        logger.debug("/api/notifications/subscribe  = ", subscriptionRequest);
        subscriptionService.subscribe(subscriptionRequest.getRequestor(),subscriptionRequest.getTarget(), SUBSCRIBED);
        return new OperationSuccessResponse(true);
    }

    @ApiOperation(value = "Unsubscribe for notifications from email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "un-subscribed successfully"),
                    @ApiResponse(code = 2002, message = "Email address is not registered"),
                    @ApiResponse(code = 5001, message = "Already unsubscribed from further updates"),
            }
    )
    @PostMapping("/unsubscribe")
    public OperationSuccessResponse unSubscribe(@Valid @RequestBody SubscriptionRequest subscriptionRequest)  {
        logger.debug("/api/notifications/unsubscribe  = ", subscriptionRequest);
        subscriptionService.subscribe(subscriptionRequest.getRequestor(),subscriptionRequest.getTarget(),UNSUBSCRIBED);
        return new OperationSuccessResponse(true);
    }



}
