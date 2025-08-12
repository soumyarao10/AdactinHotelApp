package com.hoteladactin.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.util.ElementUtil;

public class SelectHotelPage extends TestBase{
	public WebDriver driver;
private ElementUtil eleUtil;
    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

 // Add web elements and actions related to the Select Hotel Page here

    // WebElements
    @FindBy(xpath =  "//input[@type='radio']")
    WebElement firstHotelRadioButton;
 
    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "cancel")
    WebElement cancelBtn;

    @FindBy(xpath = "//td[contains(text(),'Select Hotel')]")
    WebElement headerText;
    
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/input")
    WebElement numbOfRooms;
    
  //input[@class='select_text' and @value= '2 Rooms']
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[7]/input")
    WebElement numbOfDays;
    //input[@class='select_text' and @value= '2 Days']
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[10]/input")
  //input[@class='select_text' and @value= 'AUD $ 500']
    WebElement totalPrice;
    
    @FindBy(className = "logo")
    WebElement adactinLogo;

	@FindBy(className = "welcome_menu")
    WebElement txt_welcome;

	@FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]")
    WebElement txt_LoggedInUserName;
	

	@FindBy(linkText = "Search Hotel")
    WebElement SearchHotelLink_InSelectHotelPage;
	
	@FindBy(linkText = "Booked Itinerary")
    WebElement Select_BookedItineraryLink;
	
	@FindBy(linkText = "Change Password")
    WebElement Select_ChangePasswordLink;
	
	@FindBy(linkText = "Logout")
    WebElement Select_LogoutLink;
	
	@FindBy(className = "reg_error")
    WebElement errorMsg;

	@FindBy (xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")
	WebElement  Priceper_Night;

    // Actions
	public void waitForVisibility(WebElement element, int timeOutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}

    public String getSelectHotelPageURL() {
		String url= driver.getCurrentUrl();
		System.out.println("Search Hotel Page url:" +url);
		return url;
	}
	public boolean isAdactinLogoExist() {
		waitForVisibility(adactinLogo, 10);
		return adactinLogo.isDisplayed();
	}
	
	public boolean isSearchHotelLinkExist() {
		waitForVisibility(SearchHotelLink_InSelectHotelPage, 10);
		return SearchHotelLink_InSelectHotelPage.isDisplayed();
	}
	
	public boolean isBookedItineraryLinkExistInSelectHotelPage() {
		waitForVisibility(Select_BookedItineraryLink, 10);
		return Select_BookedItineraryLink.isDisplayed();
	}
	
	public boolean isChangePasswordLinkExistInSelectHotelPag() {
		waitForVisibility(Select_ChangePasswordLink, 10);
		return Select_ChangePasswordLink.isDisplayed();
	}
	public boolean isLogoutLinkExistInSelectHotelPage() {
		waitForVisibility(Select_LogoutLink, 10);
		return Select_LogoutLink.isDisplayed();
	}
	
	public String getTextOfWelcomeMsg() {
		waitForVisibility(txt_welcome, 10);
		return txt_welcome.getText();
	}
	
	public String getTextofLoggedInUsername() {
		waitForVisibility(txt_LoggedInUserName, 20);
		return txt_LoggedInUserName.getText();
	}

    public void selectFirstHotel() {
    	waitForVisibility(firstHotelRadioButton, 10);
        firstHotelRadioButton.click();
    }

    // Page chaining: returns next page object
    public BookHotelPage clickContinue() {
        waitForVisibility(continueBtn, 10);
        continueBtn.click();
        return new BookHotelPage(driver);  // Page chaining to next screen
    }

    public SearchHotelPage clickCancel() {
    	waitForVisibility(cancelBtn, 10);
    	//eleUtil.waitForVisibility(cancelBtn, 10);
        cancelBtn.click();
        return new SearchHotelPage(driver);
    }
    private int extractNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
    public boolean isTotalPriceCorrect(int pricePerRoomPerDay) {
    	int rooms = extractNumber(numbOfRooms.getDomProperty("value")); // "2 Rooms"
    	int days = extractNumber(numbOfDays.getDomProperty("value"));   // "2 Days"
int pricePerNight = extractNumber(Priceper_Night.getDomProperty("value"));
    	int expectedTotal = rooms * days * pricePerRoomPerDay;

    	String priceText = totalPrice.getDomProperty("value"); // "AUD $ 500"
    	int actualTotal = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

        return expectedTotal == actualTotal;
    }   
    public int getNumberOfRooms() {
        return Integer.parseInt(numbOfRooms.getDomProperty("value").replaceAll("[^0-9]", ""));
    }

    public int getNumberOfDays() {
        return Integer.parseInt(numbOfDays.getDomProperty("value").replaceAll("[^0-9]", ""));
    }

    public int getTotalPriceFromPage() {
        return Integer.parseInt(totalPrice.getDomProperty("value").replaceAll("[^0-9]", ""));
    }
    public int getpricePerNightAmt() {
        return Integer.parseInt(Priceper_Night.getDomProperty("value").replaceAll("[^0-9]", ""));
    }

    public String getErrorMessage() {
    	waitForVisibility(errorMsg, 10);
        return errorMsg.getText();
    }
    
    public BookHotelPage selectHotel() {
    	waitForVisibility(firstHotelRadioButton, 20);
    	firstHotelRadioButton.click();
    	waitForVisibility(continueBtn, 10);
    	continueBtn.click();
        return new BookHotelPage(driver);
    }
}
