package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackExpensesPage {
	WebDriver driver; 

	public TrackExpensesPage(WebDriver driver) {
		this.driver=driver; 
	}
	
	public void clickOnCard() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-user-page/app-feed/div/div/div[1]/div/div")).click();
	}

}
