package com.hoteladactin.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.pages.BookHotelPage;
import com.hoteladactin.qa.pages.BookingConfirmationPage;
import com.hoteladactin.qa.pages.Login;
import com.hoteladactin.qa.pages.SearchHotelPage;
import com.hoteladactin.qa.pages.SelectHotelPage;

@Listeners(com.hoteladactin.qa.listener.ExtentReportListener.class)
public class BookHotelPageTest extends TestBase {
	
	 Login login;
	    SearchHotelPage searchHotelPage;
	    SelectHotelPage selectHotelPage;
	    BookHotelPage bookHotelPage;
	    BookingConfirmationPage confirmationPage ;
	    
	    public BookHotelPageTest() {
	        super(); // calls TestBase constructor to load config.properties
	    }
	    @BeforeMethod
	    public void setUp() {
	    	try {
	        initialization(); // Launch browser, navigate to Adactin site

	        // Login
	        login = new Login(driver);
	        login.setUsername(prop.getProperty("username"));
	        login.setPassword(prop.getProperty("password"));
	        System.out.println("Navigating to login page...");
	        System.out.println("Logging in with user: " + prop.getProperty("username"));
	        login.clickLoginButton();

	        // Search Hotel
	        searchHotelPage = new SearchHotelPage(driver);
	        searchHotelPage.selectLocation("Sydney");
	        searchHotelPage.selectHotel("Hotel Creek");
	        searchHotelPage.selectRoomType("Deluxe");
	        searchHotelPage.selectNumberOfRooms("3 - Three");
	        searchHotelPage.setCheckInDate();      // You may want to pass values here
	        searchHotelPage.setCheckOutDate();     // Same here
	        searchHotelPage.selectAdults("2 - Two");
	        searchHotelPage.selectChildren("2 - Two");

	        // Navigate to SelectHotelPage
	        selectHotelPage = searchHotelPage.doSearch();

	        // Navigate to BookHotelPage
	        bookHotelPage = selectHotelPage.selectHotel(); // Page chaining continues
	    	 } catch (Exception e) {
	    	        e.printStackTrace(); // 👈 Helps pinpoint the error line
	    	        Assert.fail("Setup failed: " + e.getMessage());
	    	    }
	    }
	    @Test(priority = 1)
	    public void verifyBookHotelPageURLTest() {
	        String currentURL = bookHotelPage.getBookHotelPageURL();
	        Assert.assertTrue(currentURL.contains("BookHotel"), "URL does not contain expected text");
	    }
	    @Test(priority = 2)
	    public void isAdactinLogoExistTest3() {
			boolean flag= bookHotelPage.isAdactinLogoExist();
			Assert.assertTrue(flag, "Adactin logo is not displayed on Search Hotel Page.");
		}
	    
	    @Test(priority=3)
		public void isSearchHotelLinkExistInBookHotelPageTest(){
			boolean flag1= bookHotelPage.isSearchHotelLinkExistinBookHotel();
			Assert.assertTrue(flag1, "Search Hotel link is not displayed on Book Hotel Page.");
		}
	    
	    
	    @Test(priority=4)
		public void isBookedItineraryLinkExistInBookHotelPageTest(){
			boolean flag2= bookHotelPage.isBookedItineraryLinkExistInBookHotelPage();
			Assert.assertTrue(flag2, "Booked Itinerary link is not displayed on Book Hotel Page.");
		}
	    
	    @Test(priority=5)
		public void isChangePasswordLinkExistInBookHotelPageTest(){
			boolean flag3= bookHotelPage.isChangePasswordLinkExistInBookHotelPage();
			Assert.assertTrue(flag3, "Change Password link is not displayed on Book Hotel Page.");
		}
	    
	    @Test(priority=6)
	   	public void isLogoutLinkExistInSelectHotelPageTest(){
	   		boolean flag4= bookHotelPage.isLogoutLinkExistInBookHotelPage();
	   		Assert.assertTrue(flag4, "LogOut link is not displayed on Book Hotel Page.");
	   	}
	     
	    @Test(priority=7)
		public void getTextOfWelcomeMsginBookHotelPageTest() {
			String ActualTestNote= bookHotelPage.getTextOfWelcomeMsgBookHotel();
			Assert.assertTrue(ActualTestNote.contains("Welcome"));
		}
	    
	    @Test(priority=8)
		public void getTextofLoggedInUsernameinBookHotelPageTest() {
			String ActualTestNote= bookHotelPage.getTextofLoggedInUsernameInBookHotel();
			System.out.println("Logged-in Username Text: '" + ActualTestNote + "'");
			Assert.assertTrue(ActualTestNote.contains("Hello"), "Logged-in username not found in message");
		}
	    @Test(priority = 18)
	    public void verifyBookingWithValidData() {
	        bookHotelPage.enterFirstName("John");
	        bookHotelPage.enterLastName("Doe");
	        bookHotelPage.enterBillingAddress("123 River Street, NY");
	        bookHotelPage.enterCreditCardNumber("4111111111111111");
	        bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        //BookingConfirmationPage confirmationPage = bookHotelPage.clickBookNow();

	        // Validation: Check if redirected to booking confirmation page
	       // String currentURL = driver.getCurrentUrl();
	       // Assert.assertTrue(currentURL.contains("BookingConfirm"),
	              //  "Failed to reach Booking Confirmation page.");
	        BookingConfirmationPage confirmationPage = bookHotelPage.clickBookNow();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        boolean urlChanged = wait.until(ExpectedConditions.urlContains("BookingConfirm"));

	        Assert.assertTrue(urlChanged, "❌ Failed to reach Booking Confirmation page.");
	        //Assert.assertTrue(confirmationPage.isBookingConfirmed(), "❌ Booking confirmation content not found.");
	    }
	    
	    @Test(priority = 9)
	    public void verifyErrorMessagesWhenFirstNameIsBlank() {
	       

	      
	        bookHotelPage.enterLastName("Doe");
	        bookHotelPage.enterBillingAddress("123 River Street, NY");
	        bookHotelPage.enterCreditCardNumber("4111111111111111");
	        bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg = bookHotelPage.getFirstNameErrorMessage();
	        Assert.assertEquals(errorMsg.trim(), "Please Enter your First Name", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 10)
	    public void verifyErrorMessagesWhenLastNameIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	        bookHotelPage.enterBillingAddress("123 River Street, NY");
	        bookHotelPage.enterCreditCardNumber("4111111111111111");
	        bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg1 = bookHotelPage.getLastNameErrorMessage();
	        Assert.assertEquals(errorMsg1.trim(), "Please Enter you Last Name", "Error message not displayed correctly");
	       	    } 
	    @Test(priority = 11)
	    public void verifyErrorMessagesWhenBillingAddressIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	    	bookHotelPage.enterLastName("Doe");
	        bookHotelPage.enterCreditCardNumber("4111111111111111");
	        bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg2 = bookHotelPage.billingAddressErrorMessage();
	        Assert.assertEquals(errorMsg2.trim(), "Please Enter your Address", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 12)
	    public void verifyErrorMessagesWhenCreditCardNoIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	    	bookHotelPage.enterLastName("Doe");
	    	bookHotelPage.enterBillingAddress("123 River Street, NY");
	        bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg3 = bookHotelPage.CreditcardNumberErrorMessage();
	        Assert.assertEquals(errorMsg3.trim(), "Please Enter your 16 Digit Credit Card Number", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 13)
	    public void verifyErrorMessagesWhenCreditCardTypeIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	    	bookHotelPage.enterLastName("Doe");
	    	bookHotelPage.enterBillingAddress("123 River Street, NY");
	    	  bookHotelPage.enterCreditCardNumber("4111111111111111");
	        bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg4 = bookHotelPage.CreditcardTypeErrorMessage();
	        Assert.assertEquals(errorMsg4.trim(), "Please Select your Credit Card Type", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 14)
	    public void verifyErrorMessagesWhenCreditCardExpiryMonthIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	    	bookHotelPage.enterLastName("Doe");
	    	bookHotelPage.enterBillingAddress("123 River Street, NY");
	    	bookHotelPage.enterCreditCardNumber("4111111111111111");
	    	bookHotelPage.selectCreditCardType("VISA");
	        bookHotelPage.selectExpiryYear("2026");
	        bookHotelPage.enterCVV("123");

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	        String errorMsg4 = bookHotelPage.ExpiryDateErrorMessage();
	        Assert.assertEquals(errorMsg4.trim(), "Please Select your Credit Card Expiry Month", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 15)
	    public void verifyErrorMessagesWhenCVVNumberIsBlank() {
	       

	    	bookHotelPage.enterFirstName("John");
	    	bookHotelPage.enterLastName("Doe");
	    	bookHotelPage.enterBillingAddress("123 River Street, NY");
	    	bookHotelPage.enterCreditCardNumber("4111111111111111");
	    	bookHotelPage.selectCreditCardType("VISA");
	    	bookHotelPage.selectExpiryMonth("December");
	        bookHotelPage.selectExpiryYear("2026");
	       

	        bookHotelPage.clickBookNow();
	        // Capture and assert error message
	       
	        String errorMsg5 = bookHotelPage.CVVNumberErrorMessage();
	        Assert.assertEquals(errorMsg5.trim(), "Please Enter your Credit Card CVV Number", "Error message not displayed correctly");
	       	    }
	    
	    @Test(priority = 16)
	    public void verifyCancelButtonRedirectsToSearchHotelPage() throws InterruptedException {
	        bookHotelPage.clickCancel();
	        Thread.sleep(10);
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains("SelectHotel"),
	                "Cancel button did not redirect to Select Hotel page.");
	        System.out.println("Cancel button redirected to Select Hotel page.");
	    }

	    @Test(priority = 17)
	    public void verifyBackLinkRedirectsToSelectHotelPage() throws InterruptedException {
	        bookHotelPage.clickBack();
	        Thread.sleep(10);
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains("SelectHotel"),
	                "Back link did not redirect to Select Hotel page.");
	        System.out.println("Back link redirected to Select Hotel page.");
	    }
	    
	    
	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
	    
}
