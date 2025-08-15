package com.hoteladactin.qa.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;
//
import com.hoteladactin.qa.util.ElementUtil;

public class SearchHotelPage extends TestBase{
	public WebDriver driver;
	//public ElementUtil eleUtil;
	//has its own webdriver reference passed via constructor
	public SearchHotelPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);//Initialize all web elements
			}
	
	@FindBy(className = "logo")
    WebElement adactinLogo;

	@FindBy(className = "welcome_menu")
    WebElement txt_welcome;

	@FindBy(xpath = "//input[@id='username_show']")
    WebElement txt_LoggedInUserName;

	@FindBy(linkText = "Search Hotel")
    WebElement SearchHotelLink_InSearchHotelPage;
	
	@FindBy(linkText = "Booked Itinerary")
    WebElement Search_BookedItineraryLink;
	
	@FindBy(linkText = "Change Password")
    WebElement Search_ChangePasswordLink;
	
	@FindBy(linkText = "Logout")
    WebElement Search_LogoutLink;
	
	@FindBy(className = "login_title")
    WebElement txt_SearchHotelPageTitle;
	
	@FindBy(id = "location")
	WebElement search_locationDropdown;
	
	@FindBy(id = "hotels")
	WebElement search_HotelDropdown;
	
	@FindBy(id = "room_type")
	WebElement search_RoomDropdown;
	
	@FindBy(id = "room_nos")
	WebElement search_NumberOfRoomsDropdown;
	
	@FindBy(id = "datepick_in")
	WebElement search_CheckInDate;
	
	@FindBy(id = "datepick_out")
	WebElement search_CheckOutDate;
	
	@FindBy(id = "adult_room")
	WebElement search_AdultsPerRoom;
	
	@FindBy(id = "child_room")
	WebElement search_ChildrenPerRoom;
	
	@FindBy(id = "Submit")
	WebElement search_SearchButton;
	
	@FindBy(id = "Reset")
	WebElement search_ResetButton;
	
	@FindBy (id = "location_span")
	WebElement locationDropdownBlankError;
	
	@FindBy (id = "num_room_span")
	WebElement noOfRoomsDropdownBlankError;
	
	@FindBy (id = "adults_room_span")
	WebElement adultRoomsDropdownBlankError;
	
	@FindBy (id = "checkin_span")
	WebElement checkInDateBlankError;
	
	@FindBy (id = "checkout_span")
	WebElement checkOutDateBlankError;
	
	//Actions/Methods
	
	public String getSearchHotelPageURL() {
		String url= driver.getCurrentUrl();
		System.out.println("Search Hotel Page url:" +url);
		return url;
	}
	public boolean isAdactinLogoExist() {
		waitForVisibility(adactinLogo, 10);
		return adactinLogo.isDisplayed();
	}
	
	public boolean isSearchHotelLinkExist() {
		waitForVisibility(SearchHotelLink_InSearchHotelPage, 10);
		return SearchHotelLink_InSearchHotelPage.isDisplayed();
	}
	
	public boolean isBookedItineraryLinkExistInSearchHotelPage() {
		waitForVisibility(Search_BookedItineraryLink, 10);
		return Search_BookedItineraryLink.isDisplayed();
	}
	
	public boolean isChangePasswordLinkExistInSearchHotelPag() {
		waitForVisibility(Search_ChangePasswordLink, 10);
		return Search_ChangePasswordLink.isDisplayed();
	}
	public boolean isLogoutLinkExistInSearchHotelPage() {
		waitForVisibility(Search_LogoutLink, 10);
		return Search_LogoutLink.isDisplayed();
	}
	
	public String getTextOfWelcomeMsg() {
		waitForVisibility(txt_welcome, 10);
		return txt_welcome.getText();
	}
	
	public String getTextofLoggedInUsername() {
		waitForVisibility(txt_LoggedInUserName, 10);
		return txt_LoggedInUserName.getDomProperty("value");
	}
	
	private void selectFromDropdownByVisibleText(WebElement element, String text) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element));
	    new Select(element).selectByVisibleText(text);
	}
	public void waitForVisibility(WebElement element, int timeOutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void selectLocation(String location) {
		 selectFromDropdownByVisibleText(search_locationDropdown, location);
	}

	public void selectHotel(String hotel) {
		 selectFromDropdownByVisibleText(search_HotelDropdown, hotel);
	}

	public void selectRoomType(String roomType) {
		 selectFromDropdownByVisibleText(search_RoomDropdown, roomType);
	}

	public void selectNumberOfRooms(String numberOfRooms) {
		 selectFromDropdownByVisibleText(search_NumberOfRoomsDropdown, numberOfRooms);
	}

	public void selectAdults(String adults) {
		 selectFromDropdownByVisibleText(search_AdultsPerRoom, adults);
	}

	public void selectChildren(String children) {
	   selectFromDropdownByVisibleText(search_ChildrenPerRoom, children);
	}
	/*public void fillSearchForm(String location, String hotel, String roomType, String rooms, String adults, String children) {
        selectLocation(location);
        selectHotel(hotel);
        selectRoomType(roomType);
        selectNumberOfRooms(rooms);
        selectAdults(adults);
        selectChildren(children);
    }
	public SelectHotelPage searchHotels(String location, String hotel, String roomType, String rooms, String adults, String children) {
	    fillSearchForm(location, hotel, roomType, rooms, adults, children);
	    return doSearch();  // page chaining here
	}*/
	
	
	public String getSelectedLocation() {
	    return new Select(search_locationDropdown).getFirstSelectedOption().getText();
	}

	public String getCheckInDate() {
	    return search_CheckInDate.getDomProperty("value");
	}

	public String getCheckOutDate() {
	    return search_CheckOutDate.getDomProperty("value");
	}
	public void setCheckInDate() {
		waitForVisibility(search_CheckInDate, 10);
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
		 search_CheckInDate.clear();
		search_CheckInDate.sendKeys(currentDate);
		
	}
	
	public void setCheckOutDate() {
		waitForVisibility(search_CheckOutDate, 10);
		String futureDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		search_CheckOutDate.clear();
		search_CheckOutDate.sendKeys(futureDate);
		
	}
	
	public SelectHotelPage doSearch() {
		waitForVisibility(search_SearchButton, 10);
		search_SearchButton.click();
		return new SelectHotelPage(driver);
	}
	
	public void clickResetButton() {
		waitForVisibility(search_ResetButton, 10);
		search_ResetButton.click();
	}
	
	//Negative validation
	public String getLocationErrorMessage() {
		waitForVisibility(locationDropdownBlankError, 10);
	    return locationDropdownBlankError.getText();
	}
	
	public String getNumberOfRoomsErrorMessage() {
		waitForVisibility(noOfRoomsDropdownBlankError, 10);
	    return noOfRoomsDropdownBlankError.getText();
	}
	
	public String adultPerRoomErrorMessage() {
		waitForVisibility(adultRoomsDropdownBlankError, 10);
	    return adultRoomsDropdownBlankError.getText();
	}
	
	public String CheckInDateErrorMessage() {
		waitForVisibility(checkInDateBlankError, 10);
	    return checkInDateBlankError.getText();
	}
	
	public String CheckOutDateErrorMessage() {
		waitForVisibility(checkOutDateBlankError, 10);
	    return checkOutDateBlankError.getText();
	}
	public void clearCheckInDate() {
	    search_CheckInDate.clear();
	}
	public void clearCheckOutDate() {
	    search_CheckOutDate.clear();
	}
	
	public void clickSearchHotelLink() {
		waitForVisibility(SearchHotelLink_InSearchHotelPage, 10);
		SearchHotelLink_InSearchHotelPage.click();
	}

	public void clickChangePasswordLink() {
		waitForVisibility(Search_ChangePasswordLink, 10);
		Search_ChangePasswordLink.click();
	}
	public void clickBookeItineraryLink() {
		waitForVisibility(Search_BookedItineraryLink, 10);
		Search_BookedItineraryLink.click();
	}
	public void clickBookedItineraryLink() {
		waitForVisibility(Search_BookedItineraryLink, 10);
		Search_BookedItineraryLink.click();
	}

	public void clickLogoutLink() {
		waitForVisibility(Search_LogoutLink, 10);
		Search_LogoutLink.click();
	}
	public String getSearchHotelPageURL1() {
	  return driver.getCurrentUrl();
	}
}
