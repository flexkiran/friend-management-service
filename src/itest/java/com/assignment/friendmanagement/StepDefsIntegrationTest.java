package com.assignment.friendmanagement;

import com.assignment.friendmanagement.wrapper.*;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StepDefsIntegrationTest extends SpringIT {

    @Value("${server.port}")
    private String port;
    private Response operationResult;

    @After
    public void clearDB() {
        resetData();
    }

    @Given("^Below email addresses exists$")
    public void belowEmailAddressesExists(List<Email> emails) throws IOException {
        emails.stream().forEach(e -> registerEmails(e.getEmail()));
    }

    @Given("^below email addresses are friend$")
    public void belowEmailAddressesAreFriend(List<EmailPair> emailPairs) {
        emailPairs.forEach(e -> makeFriend(e.getEmail1(), e.getEmail2()));
    }

    @Given("^below email addresses are not friend$")
    public void belowEmailAddressesAreNotFriend(List<EmailPair> emailPairs) {

    }

    @When("^User creates connection between$")
    public void userCreatesConnectionBetween(List<EmailPair> emailPairs) {
        makeFriend(emailPairs.get(0).getEmail1(), emailPairs.get(0).getEmail2());
    }

    @When("^User retrieves friend list for (.*)$")
    public void userRetrievesFriendListFor(String email) {
        getAllFriends(email);
    }

    @When("^User retrieves common friend list between$")
    public void userRetrievesCommonFriendListBetween(List<EmailPair> emailPairs) {
        getCommonFriends(emailPairs.get(0).getEmail1(), emailPairs.get(0).getEmail2());
    }

    @When("^User subscribes to updates for$")
    public void userSubscribersToUpdatesFor(List<NotificationEmailPair> notificationEmailPairs) {
        notificationEmailPairs.forEach(n ->
                subscribeForUpdate(n.getRequestor(), n.getTarget())
        );
    }


    @When("^User blocks updates for$")
    public void userBlocksUpdatesFor(List<NotificationEmailPair> notificationEmailPairs) {
        NotificationEmailPair notificationEmailPair = notificationEmailPairs.get(0);
        unSubscribeFromUpdate(notificationEmailPair.getRequestor(), notificationEmailPair.getTarget());

    }

    @Then("^User should receive success message$")
    public void userShouldReceiveMessage() {
        try {
            String result = operationResult.body().string();
            JSONObject jsonObject = new JSONObject(result);
            boolean success = jsonObject.getBoolean("success");
            assertEquals(true, success);
        } catch (IOException e) {
            throw new RuntimeException("error occurred while connecting to Server");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("error occurred while parsing JSON");
        }
    }

    @Then("^User should receive failure message$")
    public void userShouldReceiveFailureMessage(List<ErrorMessage> errorMessages) {

        ErrorMessage errorMessage = errorMessages.get(0);
        try {
            String result = operationResult.body().string();
            JSONObject jsonObject = new JSONObject(result);
            String code = jsonObject.getString("errorCode");
            String message = jsonObject.getString("message");
            assertEquals(errorMessage.getErrorCode(), code);
            assertEquals(errorMessage.getMessage(), message);
        } catch (IOException e) {
            throw new RuntimeException("error occurred while connecting to Server");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("error occurred while parsing JSON");
        }
    }

    @Then("^User should receive following details$")
    public void userShouldReceiveFollowingDetails(List<CommonFriendResult> commonFriendResults) {
        try {
            CommonFriendResult commonFriendResult = commonFriendResults.get(0);
            String result = operationResult.body().string();
            JSONObject jsonObject = new JSONObject(result);
            boolean success = jsonObject.getBoolean("success");
            int count = jsonObject.getInt("count");
            assertEquals(true, success);
            assertEquals(commonFriendResult.getCount(), count);
            JSONArray emails = jsonObject.getJSONArray("emails");
            assertEquals(commonFriendResult.getEmails().split(";").length, emails.length());
            String[] expectedCommonFriends = commonFriendResult.getEmails().split(";");
            List<String> actualCommonFriends = toStringList(emails);
            assertThat("List equality without order",
                    actualCommonFriends, containsInAnyOrder(expectedCommonFriends));

        } catch (IOException e) {
            throw new RuntimeException("error occurred while connecting to Server");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("error occurred while parsing JSON");
        }

    }

    @And("^(.*) should not receive notifications from (.*)$")
    public void shouldNotReceiveNotificationsFrom(String requestorEmail, String targetEmail) {
    }

    @Then("^User should receive below email$")
    public void userShouldReceiveBelowEmail(List<Email> emails) {

        try {
            String result = operationResult.body().string();
            JSONObject jsonObject = new JSONObject(result);
            boolean success = jsonObject.getBoolean("success");
            int count = jsonObject.getInt("count");
            assertEquals(success, true);
            assertEquals(count, 1);
            JSONArray emailsArray = jsonObject.getJSONArray("emails");
            assertEquals(emailsArray.get(0), emails.get(1).getEmail());

        } catch (IOException e) {
            throw new RuntimeException("error occurred while connecting to Server");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("error occurred while parsing JSON");
        }
    }

    @When("^message is sent with$")
    public void messageIsSentWith(List<Message> messages) {
        Message message = messages.get(0);
        sendMessage(message.getSender(), message.getText());
    }

    @Then("^User receives following subscribers$")
    public void userReceivesFollowingSubscribers(List<Subscribers> subscribers) {

        Subscribers subscriber = subscribers.get(0);
        try {
            String[] expectedSubs = subscriber.getEmails().split(";");
            String result = operationResult.body().string();
            JSONObject jsonObject = new JSONObject(result);
            boolean success = jsonObject.getBoolean("success");
            assertEquals(success, true);
            JSONArray emails = jsonObject.getJSONArray("recipients");
            List<String> actualSubs = toStringList(emails);
            if (actualSubs.size() != 0 && (emails != null && emails.length() >= 0)) {
                assertThat("List equality without order",
                        actualSubs, containsInAnyOrder(expectedSubs));
            }

        } catch (IOException e) {
            throw new RuntimeException("error occurred while connecting to Server");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("error occurred while parsing JSON");
        }
    }

    public void makeFriend(String emailAddress1, String emailAddress2) {
        String jsonBody = "{\"friends\":[\"" + emailAddress1 + "\"," +
                "\"" + emailAddress2 + "\"]}";
        String url = "/api/friend/add";
        sendRestCall(url, jsonBody);
    }

    public void getCommonFriends(String emailAddress1, String emailAddress2) {
        String jsonBody = "{\"friends\":[\"" + emailAddress1 + "\"," +
                "\"" + emailAddress2 + "\"]}";
        String url = "/api/friend/common";
        sendRestCall(url, jsonBody);
    }

    public void getAllFriends(String emailaddress) {
        String jsonBody = "{\"email\":\"" + emailaddress + "\"}";
        String url = "/api/friend/all";
        sendRestCall(url, jsonBody);
    }

    public void registerEmails(String emailAddress) {
        String jsonBody = "{\"email\":\"" + emailAddress + "\"}";
        String url = "/api/email/register";
        sendRestCall(url, jsonBody);
    }

    private void subscribeForUpdate(String requestor, String target) {
        String jsonBody = "{ \"requestor\":\"" + requestor +
                "\",\"target\":\"" + target + "\"}";
        String url = "/api/notifications/subscribe";
        sendRestCall(url, jsonBody);
    }

    private void unSubscribeFromUpdate(String requestor, String target) {
        String jsonBody = "{ \"requestor\":\"" + requestor +
                "\",\"target\":\"" + target + "\"}";
        String url = "/api/notifications/unsubscribe";
        sendRestCall(url, jsonBody);
    }

    private void sendMessage(String sender, String text) {
        String jsonBody = "{ \"sender\":\"" + sender +
                "\",\"text\":\"" + text + "\"}";
        String url = "/api/message/send";
        sendRestCall(url, jsonBody);
    }

    private void sendRestCall(String url, String jsonBody) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url("http://localhost:" + port + url)
                .post(body)
                .build();

        try {
            operationResult = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> toStringList(JSONArray jsonArray) {
        if (jsonArray == null)
            return null;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.optString(i));
        }
        return result;
    }
}
