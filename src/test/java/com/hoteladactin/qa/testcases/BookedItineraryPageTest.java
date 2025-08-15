package com.hoteladactin.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.pages.BookHotelPage;
import com.hoteladactin.qa.pages.BookedItineraryPage;
import com.hoteladactin.qa.pages.BookingConfirmationPage;
import com.hoteladactin.qa.pages.Login;
import com.hoteladactin.qa.pages.SearchHotelPage;
import com.hoteladactin.qa.pages.SelectHotelPage;
@Listeners(com.hoteladactin.qa.listener.ExtentReportListener.class)

public class BookedItineraryPageTest extends TestBase {

    Login login;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    BookHotelPage bookHotelPage;
    BookingConfirmationPage confirmationPage;
    BookedItineraryPage bookedItineraryPage;
    String orderId;

    public BookedItineraryPageTest() {
        super(); // loads config.properties via TestBase constructor
    }

    @BeforeMethod
    public void setUp() {
        try {
            initialization(); // Opens browser and navigates to app

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
            searchHotelPage.selectNumberOfRooms("1 - One");
            searchHotelPage.setCheckInDate();
            searchHotelPage.setCheckOutDate();
            searchHotelPage.selectAdults("2 - Two");
            searchHotelPage.selectChildren("2 - Two");
            selectHotelPage = searchHotelPage.doSearch();

            // Select Hotel
            bookHotelPage = selectHotelPage.selectHotel();

            // Book Hotel
            bookHotelPage.enterFirstName("John");
            bookHotelPage.enterLastName("Doe");
            bookHotelPage.enterBillingAddress("123 River Street");
            bookHotelPage.enterCreditCardNumber("4111111111111111");
            bookHotelPage.selectCreditCardType("VISA");
            bookHotelPage.selectExpiryMonth("December");
            bookHotelPage.selectExpiryYear("2026");
            bookHotelPage.enterCVV("123");

            // Booking confirmation
            confirmationPage = bookHotelPage.clickBookNow();
         // Capture Order ID
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement orderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));
            orderId = orderInput.getDomProperty("value");
            System.out.println("Order ID: " + orderId);
         // Navigate to Booked Itinerary
            bookedItineraryPage = confirmationPage.clickMyItinerary(); 

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setup failed: " + e.getMessage());
        }
    }
    @Test(priority = 3)
    public void verifyOrderIdCancellationFlow() {
        try {
            System.out.println("Checking presence of Order ID before cancellation: " + orderId);
            Assert.assertTrue(bookedItineraryPage.isOrderIdPresent(orderId),
                    "Order ID not found before cancellation.");

            boolean cancelled = bookedItineraryPage.cancelBookingByOrderId(orderId);
            Assert.assertTrue(cancelled, "Cancellation failed for Order ID: " + orderId);
            System.out.println("Cancellation triggered for Order ID: " + orderId);

            // Wait until the Order ID input field is no longer in the DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By locator = By.xpath("//input[@type='text' and @value='" + orderId + "']");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println("Order ID element is now invisible or gone.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }

        // Final assertion (safe method)
        Assert.assertFalse(bookedItineraryPage.isOrderIdPresent(orderId),
                "Order ID still visible after cancellation.");
        System.out.println("Order ID confirmed cancelled and removed: " + orderId);
    }

   /* @Test(priority = 3)
    public void verifyOrderIdIsPresentBeforeCancellation() {
    	        
    	System.out.println("Verifying presence of orderId: " + orderId);
        Assert.assertTrue(bookedItineraryPage.isOrderIdPresent(orderId),
                "Order ID not found before cancellation.");
    }
    @Test(priority = 4, dependsOnMethods = "verifyOrderIdIsPresentBeforeCancellation")
    public void verifyCancelBookingByOrderId() {
        boolean cancelled = bookedItineraryPage.cancelBookingByOrderId(orderId);
        Assert.assertTrue(cancelled, "Order ID was not cancelled successfully.");
        System.out.println("Order id is cancelled"  +orderId); 
    }

   @Test(priority = 5, dependsOnMethods = "verifyOrderIdIsPresentBeforeCancellation")
    public void verifyOrderIdIsNotPresentAfterCancellation() {
    	//driver.navigate().refresh();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	System.out.println("Order id is present after cancellation"  +orderId); 
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@type='checkbox' and @value='" + orderId + "']")));
    	System.out.println("Order id is present after cancellation"  +orderId); 
    	bookedItineraryPage = new BookedItineraryPage(driver);
    	System.out.println("Order id is present after cancellation"  +orderId); 
        Assert.assertFalse(bookedItineraryPage.isOrderIdPresent(orderId),
                "Order ID still visible after cancellation." );
        System.out.println("Order id is present after cancellation"  +orderId); 
    }*/

    @Test(priority = 1)
    public void verifyBookedItineraryPageURL() {
        String expectedURL = "https://adactinhotelapp.com/HotelAppBuild2/BookedItinerary.php";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Incorrect page URL.");
    }

    @Test(priority = 2)
    public void verifyNavigationLinks() {
        Assert.assertTrue(bookedItineraryPage.isSearchHotelLinkExistinMyItineraryPage(), "Search Hotel link missing.");
        Assert.assertTrue(bookedItineraryPage.isBookedItineraryLinkExistInMyItineraryPage(), "Booked Itinerary link missing.");
        Assert.assertTrue(bookedItineraryPage.isChangePasswordLinkExistInMyItineraryPage(), "Change Password link missing.");
        Assert.assertTrue(bookedItineraryPage.isLogoutLinkExistInMyItineraryPage(), "Logout link missing.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}