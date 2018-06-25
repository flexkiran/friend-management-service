package com.assignment.friendmanagement.resource.rest;

import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.request.EmailPairRequest;
import com.assignment.friendmanagement.model.request.EmailRequest;
import com.assignment.friendmanagement.model.response.EmailListResponse;
import com.assignment.friendmanagement.model.response.OperationSuccessResponse;
import com.assignment.friendmanagement.service.FriendsService;
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
import java.util.List;


@RestController
@RequestMapping("/api/friend")
public class FriendshipResource {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private FriendsService friendshipService;

    @ApiOperation(value = "Add a new friend relation between two email addresses")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Added new friend successfully"),
                    @ApiResponse(code = 2002, message = "Email address is not registered"),
                    @ApiResponse(code = 3001, message = "Email addresses are already connected as friends"),
                    @ApiResponse(code = 3001, message = "One email have blocked another email's updates")
            }
    )
    @PostMapping("/add")
    public OperationSuccessResponse addNewFriend(@Valid @RequestBody EmailPairRequest emailPairRequest) {
        logger.debug("/api/friend/add  = ", emailPairRequest);
        friendshipService.makeNewFriend(emailPairRequest.getFriends().get(0), emailPairRequest.getFriends().get(1));
        return new OperationSuccessResponse(true);
    }

    @ApiOperation(value = "Get all friends for an email address")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List of all friends for email address"),
                    @ApiResponse(code = 2002, message = "Email address is not registered"),
            }
    )
    @PostMapping("/getall")
    public EmailListResponse getAllFriends(@Valid @RequestBody EmailRequest emailRequest) {
        logger.debug("/api/friend/getall  = ", emailRequest);
        List<String> allFriends = friendshipService.getAllFriends(emailRequest.getEmail());
        return new EmailListResponse(allFriends,true);
    }

    @ApiOperation(value = "Get common friends between two email addresses")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List of all common friends"),
                    @ApiResponse(code = 2002, message = "Email address is not registered"),
            }
    )
    @PostMapping("/getcommon")
    public EmailListResponse getCommonFriends(@Valid @RequestBody EmailPairRequest emailPairRequest) {
        logger.debug("/api/friend/getcommon  = ", emailPairRequest);
        List<String> allCommonFriends = friendshipService.getAllCommonFriends(emailPairRequest.getFriends().get(0), emailPairRequest.getFriends().get(1));
        return new EmailListResponse(allCommonFriends,true);
    }

}
