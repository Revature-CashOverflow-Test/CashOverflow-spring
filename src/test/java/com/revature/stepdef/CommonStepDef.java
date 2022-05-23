package com.revature.stepdef;

import static org.junit.Assert.assertNull;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;

public class CommonStepDef {
	public static SetUp setUp;
	public static RegisterTest rt;
	
	@BeforeAll
	public static void beforeAll() {
		setUp = new SetUp();
		rt = new RegisterTest(setUp);
	}
	
	@Given("the User is in homepage")
	public void the_user_is_in_homepage() {
		setUp.driver.get("http://localhost:4200/");
	}
	@Given("the User is not logged in")
	public void the_user_is_not_logged_in() {
		assertNull(setUp.js.executeScript("return sessionStorage.getItem(\"username\");"));
	}
	
}
