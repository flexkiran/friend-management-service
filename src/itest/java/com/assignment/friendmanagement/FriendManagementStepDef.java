package com.assignment.friendmanagement;

import com.assignment.friendmanagement.wrapper.*;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class FriendManagementStepDef extends FriendManagementSpringIT {

    private Response operationResult;

    @Given("^Below email addresses exists$")
    public void belowEmailAddressesExists(List<Email> emails) throws IOException {
        emails.stream().forEach( e -> registerEmails(e.getEmail()));
    }

    @Given("^below email addresses are friend$")
    public void belowEmailAddressesAreFriend(List<EmailPair> emailPairs) {
        System.out.println(emailPairs);
    }

    @Given("^below email addresses are not friend$")
    public void belowEmailAddressesAreNotFriend(List<EmailPair> emailPairs) {
        System.out.println(emailPairs);
    }

    @When("^User creates connection between$")
    public void userCreatesConnectionBetween(List<EmailPair> emailPairs) {
        makeFriend(emailPairs.get(0).getEmail1(),emailPairs.get(0).getEmail2());
    }

    @When("^User retrieves friend list for (.*)$")
    public void userRetrievesFriendListFor(String email) {
        System.out.println(email);
    }

    @When("^User retrieves common friend list between$")
    public void userRetrievesCommonFriendListBetween(List<EmailPair> emailPairs) {
        System.out.println(emailPairs);
    }

    @When("^User subscribes to updates for$")
    public void userSubscribersToUpdatesFor(List<NotificationEmailPair> notificationEmailPairs) {
        System.out.println(notificationEmailPairs);
    }

    @When("^User blocks updates for$")
    public void userBlocksUpdatesFor(List<NotificationEmailPair> notificationEmailPairs) {
        System.out.println(notificationEmailPairs);
    }

    @Then("^User should receive (.*) message$")
    public void userShouldReceiveMessage(String success) {
        try {
            String result = operationResult.body().string();
            Assert.assertTrue(result.contains("success") && result.contains("true"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("^User should receive following details$")
    public void userShouldReceiveFollowingDetails(List<CommonFriendResult> commonFriendResults) {
        System.out.println(commonFriendResults);
    }

    @And("^(.*) should not receive notifications from (.*)$")
    public void shouldNotReceiveNotificationsFrom(String requestorEmail, String targetEmail) {
        System.out.println("requestor " + requestorEmail + ",target=" + targetEmail);
    }

    @Then("^User should receive below email$")
    public void userShouldReceiveBelowEmail(List<Email> emails) {
        System.out.println(emails);
    }

    @When("^message is sent with$")
    public void messageIsSentWith(List<Message> messages) {
        System.out.println(messages);
    }

    @Then("^User receives following subscribers$")
    public void userReceivesFollowingSubscribers(List<Subscribers> subscribers) {
        System.out.println(subscribers);
    }

    public void makeFriend(String emailAddress1, String emailAddress2){

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\"friends\":[\""
                +emailAddress1+"\"," +
                "\"" +emailAddress2+"\"]}");

        Request request = new Request.Builder()
                .url("http://localhost:8082/api/friend/add")
                .post(body)
                .build();

        try {
            operationResult = client.newCall(request).execute();
            System.out.println(operationResult.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerEmails(String emailAddress){

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\"email\":\""+emailAddress+"\"}");

        Request request = new Request.Builder()
                .url("http://localhost:8082/api/email/register")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
