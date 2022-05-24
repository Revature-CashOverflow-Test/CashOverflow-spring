package com.revature.pages;

import org.openqa.selenium.WebDriver;

public class PageController {
	public static HomePage homePage;
	public static RegisterPage registerPage;
	
	public static CreateBankAccountPage createBankAccountPage;
	public static LogInPage logInPage;
	public static ViewAllAccountPage viewAllAccountPage;
	public static TransferMoneyPage transferMoneyPage;
	
	public PageController(WebDriver driver){
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		createBankAccountPage = new CreateBankAccountPage(driver);
		logInPage = new LogInPage(driver);
		viewAllAccountPage = new ViewAllAccountPage(driver);
		transferMoneyPage = new TransferMoneyPage(driver);
	}
}
