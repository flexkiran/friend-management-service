package com.assignment.friendmanagement.resource.rest;

import com.assignment.friendmanagement.model.request.MessagingRequest;
import com.assignment.friendmanagement.model.request.SubscriptionRequest;
import com.assignment.friendmanagement.model.response.MessageRecipientsResponse;
import com.assignment.friendmanagement.model.response.OperationSuccessResponse;
import com.assignment.friendmanagement.service.MessagingService;
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


@RestController
@RequestMapping("/api/message")
public class MessagingResource {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private MessagingService messagingService;

    @ApiOperation(value = "Send message to all subscribers")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Message sent successfully"),
                    @ApiResponse(code = 2002, message = "Email address is not registered")
            }
    )
    @PostMapping("/send")
    public MessageRecipientsResponse send(@Valid @RequestBody MessagingRequest messagingRequest) throws Exception {
        logger.debug("/api/message/send  = ", messagingRequest);
        MessageRecipientsResponse msgResponse = messagingService.sendMessage(messagingRequest.getSender(), messagingRequest.getText());
        logger.debug("{} -> {}",messagingRequest,msgResponse);
        return msgResponse;
    }

}
