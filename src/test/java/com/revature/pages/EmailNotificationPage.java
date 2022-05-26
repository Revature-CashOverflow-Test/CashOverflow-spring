package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailNotificationPage {
	WebDriver driver;

	public EmailNotificationPage(WebDriver driver) {
		this.driver = driver;
}
	public void ClickSetting() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-settings/app-navbar-general/nav/div/div/ul/li[6]/a")).click();
	}
	public void TurnonNotification() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-settings/app-email-toggle/div/div/div/div/div/div/form/div[1]/input")).click();
	}
	public void EnterAmountThreshold() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-settings/app-email-toggle/div/div/div/div/div/div/form/div[2]/input")).sendKeys("2000");
	}
	public void ClickSaveSettings() {
		this.driver.findElement(By.xpath("/html/body/app-root/app-settings/app-email-toggle/div/div/div/div/div/div/form/div[3]/input")).click();
	}
}