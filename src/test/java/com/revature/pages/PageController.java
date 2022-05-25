package com.revature.pages;

import org.openqa.selenium.WebDriver;

public class PageController {
	public static HomePage homePage;
	public static RegisterPage registerPage;
	
	public static CreateBankAccountPage createBankAccountPage;
	public static LogInPage logInPage;
<<<<<<< HEAD


	public static ViewAllAccountPage viewAllAccountPage;
	
	public static TransferMoneyPage transferMoneyPage;

	

=======
	public static TransferMoneyPage transferMoneyPage;
	public static MyAccountPage myAccountPage;
>>>>>>> ebf66eaf58ea23a8b58802acccc075239019a72b
	
	public PageController(WebDriver driver){
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		createBankAccountPage = new CreateBankAccountPage(driver);
		logInPage = new LogInPage(driver);
<<<<<<< HEAD
		viewAllAccountPage = new ViewAllAccountPage(driver);

		
		transferMoneyPage = new TransferMoneyPage(driver);


		transferMoneyPage = new TransferMoneyPage(driver);

=======
		transferMoneyPage = new TransferMoneyPage(driver);
		myAccountPage = new MyAccountPage(driver);
>>>>>>> ebf66eaf58ea23a8b58802acccc075239019a72b
	}
}
