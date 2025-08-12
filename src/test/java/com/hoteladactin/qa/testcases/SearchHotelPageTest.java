package com.hoteladactin.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.pages.Login;
import com.hoteladactin.qa.pages.SearchHotelPage;
import com.hoteladactin.qa.pages.SelectHotelPage;

@Listeners(com.hoteladactin.qa.listener.ExtentReportListener.class)
public class SearchHotelPageTest extends TestBase {

	Login loginPage;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    
    public SearchHotelPageTest() {
        super(); // calls TestBase constructor to load config.properties
    }
    
    @BeforeMethod
    public void setUp() {
        initialization(); // launches browser and URL

        loginPage = new Login(driver);
        loginPage.setUsername(prop.getProperty("username"));
        loginPage.setPassword(prop.getProperty("password"));
        loginPage.clickLoginButton();

        searchHotelPage = new SearchHotelPage(driver);
    }
   
    @Test(priority = 1)
    public void verifySearchHotelPageURLTest() {
        String currentURL = searchHotelPage.getSearchHotelPageURL();
        Assert.assertTrue(currentURL.contains("SearchHotel"), "URL does not contain expected text");
    }
    @Test(priority = 2)
    public void isAdactinLogoExistTest1() {
		boolean flag= searchHotelPage.isAdactinLogoExist();
		Assert.assertTrue(flag, "Adactin logo is not displayed on Search Hotel Page.");
	}
    
    @Test(priority=3)
	public void isSearchHotelLinkExistTest(){
		boolean flag1= searchHotelPage.isSearchHotelLinkExist();
		Assert.assertTrue(flag1, "Search Hotel link is not displayed on Search Hotel Page.");
	}
    
    
    @Test(priority=4)
	public void isBookedItineraryLinkExistInSearchHotelPageTest(){
		boolean flag2= searchHotelPage.isBookedItineraryLinkExistInSearchHotelPage();
		Assert.assertTrue(flag2, "Booked Itinerary link is not displayed on Search Hotel Page.");
	}
    
    @Test(priority=5)
   	public void isLogoutLinkExistInSearchHotelPageTest(){
   		boolean flag3= searchHotelPage.isLogoutLinkExistInSearchHotelPage();
   		Assert.assertTrue(flag3, "LogOut link is not displayed on Search Hotel Page.");
   	}
     
    @Test(priority=6)
	public void getTextOfWelcomeMsgTest() {
		String ActualTestNote= searchHotelPage.getTextOfWelcomeMsg();
		Assert.assertTrue(ActualTestNote.contains("Welcome"));
	}
    
    @Test(priority=7)
	public void getTextofLoggedInUsernameTest() {
		String ActualTestNote= searchHotelPage.getTextofLoggedInUsername();
		System.out.println("Logged-in Username Text: '" + ActualTestNote + "'");
		Assert.assertTrue(ActualTestNote.contains("adactinsr123"), "Logged-in username not found in message");
	}
    
    @Test(priority = 8)
    public void verifyResetFunctionalityTest() throws InterruptedException {
        // Step 1: Fill the form
        searchHotelPage.selectLocation("Sydney");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("2 - Two");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("1 - One");

        searchHotelPage.setCheckInDate();
        searchHotelPage.setCheckOutDate();

        // Step 2: Click Reset
        searchHotelPage.clickResetButton();
        Thread.sleep(1500);
        searchHotelPage.clearCheckInDate();
        searchHotelPage.clearCheckOutDate();
        Thread.sleep(1500);
        
        // Step 3: Assert fields are reset
        String location = searchHotelPage.getSelectedLocation();
        String checkIn = searchHotelPage.getCheckInDate();
        String checkOut = searchHotelPage.getCheckOutDate();

        Assert.assertEquals(location, "- Select Location -", "Location dropdown not reset");
        Assert.assertEquals(checkIn, "", "Check-in date not cleared");
        Assert.assertEquals(checkOut, "", "Check-out date not cleared");
    }
    @Test(priority = 14)
    public void verifyAllSearchFieldsAndSearchFlow() throws InterruptedException {
        // Fill form fields using reusable dropdown functions
        searchHotelPage.selectLocation("Sydney");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("2 - Two");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("1 - One");

        // Set current and future dates
        searchHotelPage.setCheckInDate();   // Today
        searchHotelPage.setCheckOutDate();  // +2 days
Thread.sleep(1500);
        // Click Search and move to next page using page chaining
        selectHotelPage = searchHotelPage.doSearch();

        // Assert that you landed on the Select Hotel page
        Assert.assertNotNull(selectHotelPage, "Failed to navigate to Select Hotel Page");
    }

    @Test(priority = 9)
    public void validateErrorWhenLocationNotSelectedTest() {
        // Do NOT select location — leave it as default (assumed empty)

        // Fill other required fields
    	searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("1 - One");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("2 - Two");

        searchHotelPage.setCheckInDate();
        searchHotelPage.setCheckOutDate();

        // Click search
        searchHotelPage.doSearch();

        // Capture and assert error message
        String errorMsg = searchHotelPage.getLocationErrorMessage();
        Assert.assertEquals(errorMsg.trim(), "Please Select a Location", "Error message not displayed correctly");
    }
    
    @Test(priority = 10)
    
    public void validateErrorWhenNoOfRoomsNotSelectedTest() {
        // Do NOT select Number Of Rooms dropdown 

        // Fill other required fields
    	searchHotelPage.selectLocation("London");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("- Select Number of Rooms -");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("2 - Two");

        searchHotelPage.setCheckInDate();
        searchHotelPage.setCheckOutDate();

        // Click search
        searchHotelPage.doSearch();

        // Capture and assert error message
        String errorMsg = searchHotelPage.getNumberOfRoomsErrorMessage();
        Assert.assertEquals(errorMsg.trim(), "Please Select Total Number of Rooms", "Error message not displayed correctly");
    }
    
    @Test(priority = 11)
    public void validateErrorWhenAdultPerRoomNotSelectedTest() {
        // Do NOT select Adults per Rooms dropdown 

        // Fill other required fields
    	searchHotelPage.selectLocation("Melbourne");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("2 - Two");
        searchHotelPage.selectAdults("- Select Adults per Room -");
        searchHotelPage.selectChildren("2 - Two");

        searchHotelPage.setCheckInDate();
        searchHotelPage.setCheckOutDate();

        // Click search
        searchHotelPage.doSearch();

        // Capture and assert error message
        String errorMsg = searchHotelPage.adultPerRoomErrorMessage();
        Assert.assertEquals(errorMsg.trim(), "Please Select Adults per Room", "Error message not displayed correctly");
    }
    
    @Test(priority = 12)
    public void validateErrorWhenCheckInDateNotSelectedTest() throws InterruptedException {
        // Do NOT enter Check In Date

        // Fill other required fields
    	searchHotelPage.selectLocation("Melbourne");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("2 - Two");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("2 - Two");
        Thread.sleep(1500);
        searchHotelPage.clearCheckInDate();
        Thread.sleep(1500);
        searchHotelPage.setCheckOutDate();
Thread.sleep(1500);
        // Click search
        searchHotelPage.doSearch();
        Thread.sleep(3000);
        // Capture and assert error message
        String errorMsg = searchHotelPage.CheckInDateErrorMessage();
        System.out.println("Error message for blank Check In date: '" + errorMsg + "'");
        Assert.assertEquals(errorMsg.trim(), "Please Select Check-In Date", "Error message not displayed correctly");
    }
    
    @Test(priority = 13)
    public void validateErrorWhenCheckOutDateNotSelectedTest() {
        // Do NOT enter Check Out Date

        // Fill other required fields
    	searchHotelPage.selectLocation("Melbourne");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectNumberOfRooms("2 - Two");
        searchHotelPage.selectAdults("2 - Two");
        searchHotelPage.selectChildren("2 - Two");

        searchHotelPage.setCheckInDate();
        searchHotelPage.clearCheckOutDate();

        // Click search
        searchHotelPage.doSearch();

        // Capture and assert error message
        String errorMsg1 = searchHotelPage.CheckOutDateErrorMessage();
        Assert.assertEquals(errorMsg1.trim(), "Please Select Check-Out Date", "Error message not displayed correctly");
    }
    
    @Test(priority = 15)
    public void verifySearchHotelLinkNavigation() {
        searchHotelPage.clickSearchHotelLink();
        
        // Validate URL contains expected path or identifier
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("SearchHotel"), "Navigation to Search Hotel page failed");

        
    }

    @Test(priority = 16)
    public void verifyChangePasswordLinkNavigation() {
        searchHotelPage.clickChangePasswordLink();

        // Validate URL
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("ChangePassword"), "Navigation to Change Password page failed");
       
    }
    
    @Test(priority = 17)
    public void verifyBookedItineraryNavigation() {
        searchHotelPage.clickBookeItineraryLink();

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("BookedItinerary"), "Did not navigate to Booked Itinerary page");

            }
    @Test(priority = 18)
    public void verifyLogoutNavigation() {
        searchHotelPage.clickLogoutLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("Logout.php"), "Logout did not redirect to login page");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    
}
