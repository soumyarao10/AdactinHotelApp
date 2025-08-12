package com.hoteladactin.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingConfirmationPage {

	 WebDriver driver;

	    public BookingConfirmationPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    /*public boolean isBookingConfirmed() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement orderNumber = null;
				return wait.until(ExpectedConditions.visibilityOf(orderNumber)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }*/
}
