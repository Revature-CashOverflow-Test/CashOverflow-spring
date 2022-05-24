package com.revature.pages;

import org.openqa.selenium.WebDriver;

public class PageController {
	public static HomePage homePage;
	public static RegisterPage registerPage;
	public static LogInPage logInPage;
	public static TransferMoneyPage transferMoneyPage;
	
	public PageController(WebDriver driver){
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		logInPage = new LogInPage(driver);
		transferMoneyPage = new TransferMoneyPage(driver);
	}
}
