package com.revature.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackExpensesTest {
	public static SetUp setUp;
	
	public TrackExpensesTest() {
		
	}
	public  TrackExpensesTest(SetUp setUp) {
		this.setUp=setUp; 
		
	}

	@When("the user clicks on card")
	public void the_user_clicks_on_card() {
		this.setUp.pageController.trackExpensesPage.clickOnCard(); 
	}

//	@Then("Account info is shown.")
//	public void account_info_is_shown() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}


}
