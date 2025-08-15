package com.hoteladactin.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;

public class BookingConfirmationPage extends TestBase {

	 WebDriver driver;

	    public BookingConfirmationPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	 // WebElements
	    @FindBy(xpath = "/html/body/table[1]/tbody/tr[1]/td[1]/img")
	    WebElement adactinLogo_BookConfirmation;

		@FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[1]")
	    WebElement txt_welcome_BookingConfirmation;

		@FindBy(xpath = "//input[@id='username_show']")
	    WebElement txt_LoggedInUserName_BookingConfirmation;

		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(2)")
	    WebElement SearchHotelLink_InBookingConfirmationPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3)")
	    WebElement BookedItineraryLink_InBookingConfirmationPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(4)")
	    WebElement ChangePasswordLink_InBookingConfirmationPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(5)")
	    WebElement LogoutLink_InBookingConfirmationPage;
		
		 @FindBy(xpath = "//*[@id=\"search_hotel\"]")
		    private WebElement SearchHotelButton;
		    
		    @FindBy(xpath = "//*[@id=\"my_itinerary\"]")
		    private WebElement MyItineraryButton;

		    @FindBy(xpath = "//*[@id=\"logout\"]")
		    private WebElement LogOutButton;
		    
		    

		    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		   // WebElement orderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));
		   // String orderId = driver.findElement(By.id("order_no")).getDomProperty("value");
		
		  // Actions
		   
		    
	    public String getBookingConfirmationPageURL() {
			String url= driver.getCurrentUrl();
			System.out.println("Booking Confirmation Page url:" +url);
			return url;
		}
	    public void waitForVisibility(WebElement element, int timeOutInSeconds) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		    wait.until(ExpectedConditions.visibilityOf(element));
	    }
		    public boolean isAdactinLogoExist() {
				waitForVisibility(adactinLogo_BookConfirmation, 10);
				return adactinLogo_BookConfirmation.isDisplayed();
			}
			
			public boolean isSearchHotelLinkExistinBookingConfirmation() {
				waitForVisibility(SearchHotelLink_InBookingConfirmationPage, 10);
				return SearchHotelLink_InBookingConfirmationPage.isDisplayed();
			}
			
			public boolean isBookedItineraryLinkExistInBookingConfirmationPage() {
				waitForVisibility(BookedItineraryLink_InBookingConfirmationPage, 10);
				return BookedItineraryLink_InBookingConfirmationPage.isDisplayed();
			}
			
			public boolean isChangePasswordLinkExistInBookHotelPage() {
				waitForVisibility(ChangePasswordLink_InBookingConfirmationPage, 10);
				return ChangePasswordLink_InBookingConfirmationPage.isDisplayed();
			}
			public boolean isLogoutLinkExistInBookHotelPage() {
				waitForVisibility(LogoutLink_InBookingConfirmationPage, 10);
				return LogoutLink_InBookingConfirmationPage.isDisplayed();
			}
			
			public String getTextOfWelcomeMsgBookConfirmPage() {
				waitForVisibility(txt_welcome_BookingConfirmation, 10);
				return txt_welcome_BookingConfirmation.getText();
			}
			
			public String getTextofLoggedInUsernameInBookHotel() {
				waitForVisibility(txt_LoggedInUserName_BookingConfirmation, 10);
				return txt_LoggedInUserName_BookingConfirmation.getDomProperty("value");
			}
			
			 public SearchHotelPage  clickSearchHotel() {
			    	waitForVisibility(SearchHotelButton, 10);
			    	SearchHotelButton.click();
			    	  return new SearchHotelPage(driver);//page chaining
			    }

			    public BookedItineraryPage  clickMyItinerary() {
			    	waitForVisibility(MyItineraryButton, 10);
			    	MyItineraryButton.click();
			    	 return new BookedItineraryPage(driver);
			    }
			    public LogoutPage clickLogout() {
			    	waitForVisibility(LogOutButton, 10);
			    	LogOutButton.click();
			    	return new LogoutPage(driver);
			    }
}
