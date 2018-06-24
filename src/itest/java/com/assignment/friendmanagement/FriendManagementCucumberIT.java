package com.assignment.friendmanagement;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/end2end"}, features = {"src/itest/resources/"}, tags = {"~@ignore"})
public class FriendManagementCucumberIT {
}
