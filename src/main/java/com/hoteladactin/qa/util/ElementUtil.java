package com.hoteladactin.qa.util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;
	public void selectFromDropdownByVisibleText(WebElement dropdown, String visibleText) {
	    Select select = new Select(dropdown);
	    select.selectByVisibleText(visibleText);
	}
	public void waitForVisibility(WebElement element, int timeOutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
}
