package com.revature.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest{

public static SetUp setUp;

public LoginTest() {
	
}

public LoginTest(SetUp setUp) {
	this.setUp = setUp;
}
@When("the User clicks the log in button")
public void the_user_clicks_the_log_in_button() {
    // Write code here that turns the phrase above into concrete actions
	this.setUp.pageController.logInPage.clickLogInButton();
}

@Then("the app displays incorrect credentials")
public void the_app_displays_incorrect_credentials() {
    // Write code here that turns the phrase above into concrete actions
	this.setUp.pageController.logInPage.invalidCredentialsMessage();
}

@Then("the app displays Login Successfully")
public void the_app_displays_login_successfully() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the User clicks the log out button")
public void the_user_clicks_the_log_out_button() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the app displays log out successfully")
public void the_app_displays_log_out_successfully() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}

