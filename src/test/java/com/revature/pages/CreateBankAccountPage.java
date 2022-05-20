package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateBankAccountPage {

	WebDriver driver;

	public CreateBankAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void sendInputToNameForm(String str) {
		this.driver.findElement(By.id("nameId")).sendKeys(str);
	}
	
	public void sendInputToDescriptionForm(String str) {
		this.driver.findElement(By.id("descriptionId")).sendKeys(str);
	}
	public void selectAccountTypeForm() {
		this.driver.findElement(By.name("accountType")).click();
	}
}
