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
	public void selectAccountTypeChecking() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-bank-account-register-page/app-create-bank-account-form/div/div/div/div/div/div/form/div[3]/select/option[1]")).click();
	}
	public void selectAccountTypeSaving() {

	}
	public void clickCreateAccount() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-bank-account-register-page/app-create-bank-account-form/div/div/div/div/div/div/form/div[4]/button")).click();
	}
	
}
