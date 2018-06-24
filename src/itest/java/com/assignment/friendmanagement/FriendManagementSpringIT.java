package com.assignment.friendmanagement;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Application.class,
        loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class FriendManagementSpringIT {
    protected OkHttpClient client = new OkHttpClient();

}