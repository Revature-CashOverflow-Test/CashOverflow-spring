package com.revature.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickRegisterOnNavBar() {
		WebDriverWait wait=new WebDriverWait(driver, 2);		        
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-login-page/app-navbar-login/nav/div/div/ul/li[3]/a")));
		System.out.println(ele.getText());
		ele.click();
	}
	
	public boolean checkIfAtLoginPage() {
		WebDriverWait wait=new WebDriverWait(driver, 2);		        
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"exampleInputEmail1\"]")));
		System.out.println(this.driver.getCurrentUrl());
		return this.driver.getCurrentUrl().equals("http://localhost:4200/login");
	}

}
