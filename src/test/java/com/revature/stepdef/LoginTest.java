package com.revature.stepdef;

import io.cucumber.java.en.When;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

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
	@When("the User inputs {string} into Username login form")
	public void the_user_inputs_into_username_login_form(String string) {
	    // Write code here that turns the phrase above into concrete actions
		this.setUp.pageController.logInPage.inputIntoUsername(string);
	}
	@When("the User inputs {string} into Password login form")
	public void the_user_inputs_into_password_login_form(String string) {
	    // Write code here that turns the phrase above into concrete actions
		this.setUp.pageController.logInPage.inputIntoPassword(string);
	}
	@Then("the app displays incorrect credentials")
	public void the_app_displays_incorrect_credentials() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(this.setUp.pageController.logInPage.invalidCredentialsMessage());
	}
	@Then("the app displays Login Successfully")
	public void the_app_displays_login_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(this.setUp.pageController.logInPage.logInSuccessfullyMessage());
	}
	@When("the User clicks the log out button")
	public void the_user_clicks_the_log_out_button() {
	    // Write code here that turns the phrase above into concrete actions
		this.setUp.pageController.homePage.clickLogOutButton();
	}
	@Then("the app displays log out successfully")
	public void the_app_displays_log_out_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(this.setUp.pageController.homePage.checkIfAtLoginPage());
	}

	@When("User clicks on dark mode")
	public void user_clicks_on_dark_mode() {
	   this.setUp.pageController.logInPage.clickDarkModeButton();
	}
	
	@Then("application change to dark mode")
	public void application_change_to_dark_mode() {
	
		String exptheme = "dark-theme";
		String acttheme = this.setUp.pageController.logInPage.gettheme();
		Assert.assertEquals(exptheme,acttheme);
		
	}
	
	
}

