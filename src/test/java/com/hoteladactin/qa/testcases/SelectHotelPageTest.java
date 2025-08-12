package com.hoteladactin.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.pages.BookHotelPage;
import com.hoteladactin.qa.pages.Login;
import com.hoteladactin.qa.pages.SearchHotelPage;
import com.hoteladactin.qa.pages.SelectHotelPage;

@Listeners(com.hoteladactin.qa.listener.ExtentReportListener.class)
public class SelectHotelPageTest extends TestBase{
	Login login;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    BookHotelPage bookHotelPage;


  
    public SelectHotelPageTest() {
        super(); // calls TestBase constructor to load config.properties
    }
    @BeforeMethod
    public void setUp() {
        initialization(); // Launch browser

        // Login
        login = new Login(driver);
        login.setUsername(prop.getProperty("username"));
        login.setPassword(prop.getProperty("password"));
        login.clickLoginButton();

        // Search Hotel
        searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage.selectLocation("Sydney");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Deluxe");
        searchHotelPage.selectNumberOfRooms("3 - Three");
        searchHotelPage.setCheckInDate();
        searchHotelPage.setCheckOutDate();
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("2 - Two");

        // Navigate to SelectHotelPage
        selectHotelPage = searchHotelPage.doSearch(); // Page chaining
    }

    @Test(priority = 1)
    public void verifySelectHotelPageURLTest() {
        String currentURL = selectHotelPage.getSelectHotelPageURL();
        Assert.assertTrue(currentURL.contains("SelectHotel"), "URL does not contain expected text");
    }
    @Test(priority = 2)
    public void isAdactinLogoExistTest2() {
		boolean flag= selectHotelPage.isAdactinLogoExist();
		Assert.assertTrue(flag, "Adactin logo is not displayed on Search Hotel Page.");
	}
    
    @Test(priority=3)
	public void isSearchHotelLinkExistInSelectHotelPageTest(){
		boolean flag1= selectHotelPage.isSearchHotelLinkExist();
		Assert.assertTrue(flag1, "Search Hotel link is not displayed on Search Hotel Page.");
	}
    
    
    @Test(priority=4)
	public void isBookedItineraryLinkExistInSelectHotelPageTest(){
		boolean flag2= selectHotelPage.isBookedItineraryLinkExistInSelectHotelPage();
		Assert.assertTrue(flag2, "Booked Itinerary link is not displayed on Search Hotel Page.");
	}
    
    @Test(priority=5)
   	public void isLogoutLinkExistInSelectHotelPageTest(){
   		boolean flag3= selectHotelPage.isLogoutLinkExistInSelectHotelPage();
   		Assert.assertTrue(flag3, "LogOut link is not displayed on Search Hotel Page.");
   	}
     
    @Test(priority=6)
	public void getTextOfWelcomeMsginSelectHotelPageTest() {
		String ActualTestNote= selectHotelPage.getTextOfWelcomeMsg();
		Assert.assertTrue(ActualTestNote.contains("Welcome"));
	}
    
    @Test(priority=7)
	public void getTextofLoggedInUsernameinSelectHotelPageTest() {
		String ActualTestNote= selectHotelPage.getTextofLoggedInUsername();
		System.out.println("Logged-in Username Text: '" + ActualTestNote + "'");
		Assert.assertTrue(ActualTestNote.contains("Hello"), "Logged-in username not found in message");
	}

    @Test(priority = 10)
    public void selectHotelAndContinueTest() {
        selectHotelPage.selectFirstHotel();
        bookHotelPage = selectHotelPage.clickContinue(); // Page chaining
        Assert.assertNotNull(bookHotelPage, "Failed to navigate to Book Hotel page");
    }

    @Test(priority = 8)
    public void continueWithoutSelectingHotelTest() {
        selectHotelPage.clickContinue();
        System.out.println("selectHotelPage is null? " + (selectHotelPage == null));
        String errorMsg = selectHotelPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Please Select a Hotel"), "Error message not shown as expected");
    }

    @Test(priority = 9)
    public void verifyTotalPriceCalculationTest() {
        int pricePerRoomPerDay = selectHotelPage.getpricePerNightAmt(); // Expected unit price

        int rooms = selectHotelPage.getNumberOfRooms();
        int days = selectHotelPage.getNumberOfDays();
        int expectedTotal = rooms * days * pricePerRoomPerDay;
        int actualTotal = selectHotelPage.getTotalPriceFromPage();

        System.out.println("Expected Total: " + expectedTotal);
        System.out.println("Actual Total: " + actualTotal);

        Assert.assertEquals(actualTotal, expectedTotal, "Price mismatch on Select Hotel page");
    }
    @Test(priority = 11)
    public void cancelButtonTest() {
        // Click Cancel and verify redirection
        searchHotelPage = selectHotelPage.clickCancel(); // Page chaining

        String currentUrl = searchHotelPage.getSearchHotelPageURL1();
        Assert.assertTrue(currentUrl.contains("SearchHotel"), "Did not return to Search Hotel page after cancel");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
