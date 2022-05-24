package com.revature.pages;

import org.openqa.selenium.WebDriver;

public class PageController {
	public static HomePage homePage;
	public static RegisterPage registerPage;
	
	public static CreateBankAccountPage createBankAccountPage;
	public static LogInPage logInPage;
<<<<<<< HEAD
	public static ViewAllAccountPage viewAllAccountPage;
=======
	public static TransferMoneyPage transferMoneyPage;
>>>>>>> 63b84ae7507c0ab6b52e2f4c1951022ccc1a7ba4
	
	public PageController(WebDriver driver){
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		createBankAccountPage = new CreateBankAccountPage(driver);
		logInPage = new LogInPage(driver);
<<<<<<< HEAD
		viewAllAccountPage = new ViewAllAccountPage(driver);
		
=======
		transferMoneyPage = new TransferMoneyPage(driver);
>>>>>>> 63b84ae7507c0ab6b52e2f4c1951022ccc1a7ba4
	}
}
