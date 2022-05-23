package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewAllAccountPage {
	WebDriver driver;
	
	public ViewAllAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickViewAccount() {
		this.driver.findElements(By.id(bank_accounts))
	}

	public boolean viewSuccess() {
		String Message = driver.findElement(By.id("bank_accounts")).getAttribute("validationMessage");
		return Message.contains("See your account");
	}
}
