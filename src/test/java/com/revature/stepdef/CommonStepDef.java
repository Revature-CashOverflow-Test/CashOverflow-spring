package com.revature.stepdef;

import static org.junit.Assert.assertNull;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;

public class CommonStepDef {
	public static SetUp setUp;
	public static RegisterTest rt;
	public static TransferMoneyFromAccountsTest TMFAt;
	
	@BeforeAll
	public static void beforeAll() {
		setUp = new SetUp();
		rt = new RegisterTest(setUp);
		TMFAt = new TransferMoneyFromAccountsTest(setUp);
	}
	
	@Given("the User is in homepage")
	public void the_user_is_in_homepage() {
		setUp.driver.get("http://localhost:4200/");
	}
	@Given("the User is not logged in")
	public void the_user_is_not_logged_in() {
		assertNull(checkLogin());
	}
	
	
	@Given("the User logs in successfully")
	public void the_user_logs_in_successfully() {
		if(checkLogin() == false) {
			LogIn();
		}
	}

	@Given("the User had previously created two accounts")
	public void the_user_had_previously_created_two_accounts() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the User had some fund in the account")
	public void the_user_had_some_fund_in_the_account() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	public boolean checkLogin() {
		return setUp.js.executeScript("return sessionStorage.getItem(\"username\")") != null;
	}
	
	public void LogIn() {
		this.setUp.pageController.homePage.checkIfAtLoginPage();
		//
	}
}
