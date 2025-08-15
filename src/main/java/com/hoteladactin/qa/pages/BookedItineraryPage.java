package com.hoteladactin.qa.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;

public class BookedItineraryPage extends TestBase{
	WebDriver driver;

    public BookedItineraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

 // WebElements
   
    @FindBy(name = "cancelall")
    private WebElement cancelSelectedButton;
	
    @FindBy(xpath = "/html/body/table[1]/tbody/tr[1]/td[1]/img")
    WebElement adactinLogo_MyItinerary;

	@FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[1]")
    WebElement txt_welcome_MyItinerary;

	@FindBy(xpath = "//input[@id='username_show']")
    WebElement txt_LoggedInUserName_MyItinerary;

	@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(2)")
    WebElement SearchHotelLink_MyItinerary;
	
	@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3)")
    WebElement BookedItineraryLink_MyItinerary;
	
	@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(4)")
    WebElement ChangePasswordLink_MyItinerary;
	
	@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(5)")
    WebElement LogoutLink_MyItinerary;
	
    
	public boolean cancelBookingByOrderId(String orderId) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Checkbox for specific order ID
           String inputXpath = "//input[@type='text' and @value='" + orderId + "']";
         
            //String checkboxXpath = "//input[@type='checkbox' and @value='" + orderId + "']";
            
           // WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkboxXpath)));
            
           //WebElement orderIdCell = driver.findElement(By.xpath(inputXpath));
           List<WebElement> matches = driver.findElements(By.xpath(inputXpath));
           if (matches.isEmpty()) {
               System.out.println("Order ID input not found for cancellation: " + orderId);
               return false;
           }
           WebElement orderIdCell = matches.get(0);
           
           WebElement checkbox = orderIdCell.findElement(By.xpath("./ancestor::tr/td[1]/input[@type='checkbox']"));
           checkbox.click();
           System.out.println("Clicked checkbox for order ID: " + orderId);
            // Click 'Cancel Selected'
            cancelSelectedButton.click();

            // Accept confirmation alert
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();

            // Wait for checkbox to disappear (indicating successful cancellation)
            wait.until(ExpectedConditions.invisibilityOf(checkbox));

            return true;
        } catch (Exception e) {
            System.out.println("Error while cancelling order: " + orderId + "\n" + e.getMessage());
            return false;
        }
    }

    //  Check if Order ID is present
    public boolean isOrderIdPresent(String orderId) {
        try {
            String xpath = "//input[@type='text' and @value='" + orderId + "']";
            return !driver.findElements(By.xpath(xpath)).isEmpty();
        
           // WebElement orderCheckbox = driver.findElement(By.xpath(xpath));
          // return orderCheckbox.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

		
	 public String getBookedItineraryPageURL() {
			String url= driver.getCurrentUrl();
			System.out.println("Book Itinerary Page url:" +url);
			return url;
		}
	    public void waitForVisibility(WebElement element, int timeOutInSeconds) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		    wait.until(ExpectedConditions.visibilityOf(element));
		}
	    
		public boolean isAdactinLogoExist() {
			waitForVisibility(adactinLogo_MyItinerary, 10);
			return adactinLogo_MyItinerary.isDisplayed();
		}
		
		public boolean isSearchHotelLinkExistinMyItineraryPage() {
			waitForVisibility(SearchHotelLink_MyItinerary, 10);
			return SearchHotelLink_MyItinerary.isDisplayed();
		}
		
		public boolean isBookedItineraryLinkExistInMyItineraryPage() {
			waitForVisibility(BookedItineraryLink_MyItinerary, 10);
			return BookedItineraryLink_MyItinerary.isDisplayed();
		}
		
		public boolean isChangePasswordLinkExistInMyItineraryPage() {
			waitForVisibility(ChangePasswordLink_MyItinerary, 10);
			return ChangePasswordLink_MyItinerary.isDisplayed();
		}
		public boolean isLogoutLinkExistInMyItineraryPage() {
			waitForVisibility(LogoutLink_MyItinerary, 10);
			return LogoutLink_MyItinerary.isDisplayed();
		}
		
		public String getTextOfWelcomeMsgMyItineraryPage() {
			waitForVisibility(txt_welcome_MyItinerary, 10);
			return txt_welcome_MyItinerary.getText();
		}
		
		public String getTextofLoggedInUsernameInItinerary() {
			waitForVisibility(txt_LoggedInUserName_MyItinerary, 10);
			return txt_LoggedInUserName_MyItinerary.getDomProperty("value");
		}
}
