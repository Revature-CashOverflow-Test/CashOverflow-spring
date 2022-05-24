package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickMyAccount() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-user-page/app-navbar-general/nav/div/div/ul/li[2]/a")).click();
	}

	public boolean viewSuccess() {
		String Message = driver.findElement(By.id("bank_accounts")).getAttribute("validationMessage");
		return Message.contains("See your account");
	}
}
