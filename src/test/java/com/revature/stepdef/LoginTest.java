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
    throw new io.cucumber.java.PendingException();
}

@Then("the app displays incorrect password")
public void the_app_displays_incorrect_password() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the User inputs {string} into the Username form")
public void the_user_inputs_into_the_username_form(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the User inputs {string} into the Password form")
public void the_user_inputs_into_the_password_form(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the User clicks log in button")
public void the_user_clicks_log_in_button() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the app displays username doesn’t exist")
public void the_app_displays_username_doesn_t_exist() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the app displays invalid credentials")
public void the_app_displays_invalid_credentials() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the app displays Login Successfully")
public void the_app_displays_login_successfully() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}

