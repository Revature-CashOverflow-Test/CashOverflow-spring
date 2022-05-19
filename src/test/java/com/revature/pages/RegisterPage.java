package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoUsername(String str) {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(str);
	}
	public void inputIntoFirstName(String str) {
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys(str);

	}
	public void inputIntoLastName(String str) {
		driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys(str);

	}
	public void inputIntoEmail(String str) {
		driver.findElement(By.xpath("//*[@id=\"email1\"]")).sendKeys(str);

	}
	public void inputIntoPassword(String str) {
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(str);

	}
	public void inputIntoConfirmPassword(String str) {
		driver.findElement(By.xpath("//*[@id=\"password2\"]")).sendKeys(str);
	}
	
	public void clickRegisterButton(){
		driver.findElement(By.xpath("/html/body/app-root/app-register-page/app-register-user/div/div/div/div/div/div/form/div[7]/button"));
	}
}
