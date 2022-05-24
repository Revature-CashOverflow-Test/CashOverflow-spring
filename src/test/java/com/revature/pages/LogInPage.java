package com.revature.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
	WebDriver driver;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
	}
	public void inputIntoUsername(String str){
	        driver.findElement(By.xpath("//*[@id=\"exampleInputEmail1\"]")).sendKeys(str); }
	
	public void inputIntoPassword(String str){
	        driver.findElement(By.xpath("//*[@id=\"exampleInputPassword1\"]")).sendKeys(str);}
	
	public void clickLogInButton(){
	        WebDriverWait wait = new WebDriverWait(driver, 5);
	        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("/html/body/app-root/app-login-page/app-login/div/div/div/div/div/div/form/div/input")));
	        ele.sendKeys(Keys.ENTER);
	}
	public boolean invalidCredentialsMessage(){
	        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                        .withTimeout(Duration.ofSeconds(3))
	                        .pollingEvery(Duration.ofMillis(100))
	                        .ignoring(NoSuchElementException.class);
	        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(
	                        By.xpath("//*[@id=\\\"toast-container\\\"]/div/")));
	        String text = ele.getText().toString();
	        System.out.println(text);
	        return text.contains("Password/Username authentication erro"); 
	        
	}
	
	public boolean logInSuccessfullyMessage() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(3))
				  .pollingEvery(Duration.ofMillis(100))
				  .ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		String text = ele.getText().toString();
		System.out.println(text);
		return text.contains("You have been successfully logged in");
	}
	
	public void clickLogOutButton(){
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(
	                    By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[7]/a"
	                    		+ "")));
	    ele.click();
	}


}

