package com.revature.stepdef;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;

public class CommonStepDef {
	public static SetUp setUp;
	public static RegisterTest rt;
	public static TrackMultipleAccts tma;
	public static TransferMoneyFromAccountsTest TMFAt;
	public static LoginTest lt;
	
	@BeforeAll
	public static void beforeAll() {
		setUp = new SetUp();
		rt = new RegisterTest(setUp);
		tma = new TrackMultipleAccts(setUp);
		TMFAt = new TransferMoneyFromAccountsTest(setUp);
		lt = new LoginTest(setUp);
	}
	
	@Given("the User is in homepage")
	public void the_user_is_in_homepage() {
		setUp.driver.get("http://localhost:4200/");
		setUp.js.executeScript("return sessionStorage.removeItem(\"username\");");
	}
	@Given("the User is not logged in")
	public void the_user_is_not_logged_in() {
		assertFalse(checkLogin());
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
	@Given("the User logs in successfully")
	public void the_user_logs_in_successfully() {
		if(checkLogin() == false) {
			LogIn("testingExample","Password!1");
		}
	}
	
	public boolean checkLogin() {
		return setUp.js.executeScript("return sessionStorage.getItem(\"username\");") != null;
	}
	
	public void LogIn(String Username, String Password) {
		this.setUp.pageController.homePage.checkIfAtLoginPage();
		this.setUp.pageController.logInPage.inputIntoUsername(Username);
		this.setUp.pageController.logInPage.inputIntoPassword(Password);
		this.setUp.pageController.logInPage.clickLogInButton();
	}
}
