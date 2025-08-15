package com.hoteladactin.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
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
public class BookingConfirmationPageTest extends TestBase {

    Login login;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    BookHotelPage bookHotelPage;
    BookingConfirmationPage confirmationPage;

    String orderId;

    public BookingConfirmationPageTest() {
        super(); // Load config.properties
    }

    @BeforeMethod
    public void setUp() {
        try {
            initialization(); // Launch browser and open Adactin site

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

            // Select and Book Hotel
            bookHotelPage = selectHotelPage.selectHotel();
            bookHotelPage.enterFirstName("John");
            bookHotelPage.enterLastName("Doe");
            bookHotelPage.enterBillingAddress("123 Main Street");
            bookHotelPage.enterCreditCardNumber("4111111111111111");
            bookHotelPage.selectCreditCardType("VISA");
            bookHotelPage.selectExpiryMonth("January");
            bookHotelPage.selectExpiryYear("2026");
            bookHotelPage.enterCVV("123");

            // Click Book Now and get confirmation page
            confirmationPage = bookHotelPage.clickBookNow();

            // Wait and capture order ID
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement orderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));
            orderId = orderInput.getDomProperty("value");
            System.out.println("Booking Order ID: " + orderId);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setup failed: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void verifyOrderIDIsGenerated() {
        Assert.assertNotNull(orderId, "Order ID is null, booking might have failed.");
        Assert.assertTrue(orderId.matches("\\w+"), "Order ID is not in expected format.");
    }

    @Test(priority = 2)
    public void verifyMyItineraryButtonNavigation() {
        BookedItineraryPage itineraryPage = confirmationPage.clickMyItinerary();
        Assert.assertTrue(itineraryPage.isBookedItineraryLinkExistInMyItineraryPage(), "My Itinerary navigation failed.");
    }

    @Test(priority = 3)
    public void verifySearchHotelButtonNavigation() {
        SearchHotelPage searchPage = confirmationPage.clickSearchHotel();
        Assert.assertTrue(searchPage.isSearchHotelLinkExist(), "Search Hotel navigation failed.");
    }

    @Test(priority = 4)
    public void verifyLogoutFunctionality() {
        confirmationPage.clickLogout();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Logout"), "Logout failed or did not navigate to logout screen.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}