package com.revature.stepdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackMultipleAccts {

	public static SetUp setUp;

	public TrackMultipleAccts() {

	}

	public TrackMultipleAccts(SetUp setUp) {
		this.setUp = setUp;
	}

	@When("the User clicks on create a bank account")
	public void the_user_clicks_on_create_a_bank_account() {
		this.setUp.pageController.createBankAccountPage.clickCreateAccount();
	}

	@When("the User inputs the data into a corresponding form")
	public void the_user_inputs_the_date_into_a_corresponding_form() {
		// Write code here that turns the phrase above into concrete actions
		this.setUp.pageController.createBankAccountPage.sendInputToNameForm("Priest");
		this.setUp.pageController.createBankAccountPage.sendInputToDescriptionForm("Checking");
	}

	@When("the User selects checking account")
	public void the_user_selects_checking_account() {
		this.setUp.pageController.createBankAccountPage.selectAccountTypeForm();
		this.setUp.pageController.createBankAccountPage.selectAccountTypeChecking();
	}

	@When("the User clicks create account")
	public void the_user_clicks_create_account() {
		this.setUp.pageController.createBankAccountPage.clickCreateAccount();
	}

	@Then("the application shows a new checking account is created")
	public void the_application_shows_a_new_checking_account_is_created() {
		
	}

	@When("the User selects savings account")
	public void the_user_selects_savings_account() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the application shows a new savings account is created")
	public void the_application_shows_a_new_savings_account_is_created() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the application notifies the user that they need to fill in the missing form")
	public void the_application_notifies_the_user_that_they_need_to_fill_in_the_missing_form() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the User clicks on create my accounts")
	public void the_user_clicks_on_create_my_accounts() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the User clicks between accounts to access")
	public void the_user_clicks_between_accounts_to_access() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the application displays all accounts")
	public void the_application_displays_all_accounts() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
