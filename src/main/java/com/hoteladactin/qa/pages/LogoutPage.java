package com.hoteladactin.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;

public class LogoutPage extends TestBase{
	

		 WebDriver driver;

		    public LogoutPage(WebDriver driver) {
		        this.driver = driver;
		        PageFactory.initElements(driver, this);
		    }
		    // WebElements
		    @FindBy(linkText = "Click here to login again")
		    WebElement LoginAgainLink;
		    
		    // Actions
		    public String getLogoutPageURL() {
				String url= driver.getCurrentUrl();
				System.out.println("Logout Page url:" +url);
				return url;
			}
		    public void waitForVisibility(WebElement element, int timeOutInSeconds) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
			    wait.until(ExpectedConditions.visibilityOf(element));
		    }
			    public boolean isLoginAgainLinkExist() {
					waitForVisibility(LoginAgainLink, 10);
					return LoginAgainLink.isDisplayed();
				}
		    
	}
	
