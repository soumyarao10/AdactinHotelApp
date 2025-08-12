package com.hoteladactin.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hoteladactin.qa.base.TestBase;

public class BookHotelPage extends TestBase{
	  public WebDriver driver;

	  public BookHotelPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
		
	    // WebElements
	    @FindBy(xpath = "/html/body/table[1]/tbody/tr[1]/td[1]/img")
	    WebElement adactinLogo_BookHotel;

		@FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[1]")
	    WebElement txt_welcome_BookHotel;

		@FindBy(xpath = "//input[@id='username_show']")
	    WebElement txt_LoggedInUserName_BookHotel;

		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(2)")
	    WebElement SearchHotelLink_InBookHotelPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3)")
	    WebElement BookedItineraryLink_InBookHotelPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(4)")
	    WebElement ChangePasswordLink_InBookHotelPage;
		
		@FindBy(css = "body > table.content > tbody > tr:nth-child(1) > td:nth-child(2) > a:nth-child(5)")
	    WebElement LogoutLink_InBookHotelPage;
		
	    @FindBy(id = "first_name")
	    private WebElement firstName;

	    @FindBy(id = "last_name")
	    private WebElement lastName;

	    @FindBy(id = "address")
	    private WebElement billingAddress;

	    @FindBy(id = "cc_num")
	    private WebElement creditCardNumber;

	    @FindBy(id = "cc_type")
	    private WebElement creditCardType;

	    @FindBy(id = "cc_exp_month")
	    private WebElement expMonth;

	    @FindBy(id = "cc_exp_year")
	    private WebElement expYear;

	    @FindBy(id = "cc_cvv")
	    private WebElement cvvNumber;

	    @FindBy(id = "book_now")
	    private WebElement bookNowButton;
	    
	    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[19]/td[2]/input[2]")
	    private WebElement cancelButton;

	    @FindBy(css = "#book_hotel_form > table > tbody > tr:nth-child(1) > td > a")
	    private WebElement back_Link;
	    
		@FindBy (id = "first_name_span")
		WebElement firstNameBlankError;
		
		@FindBy (id = "last_name_span")
		WebElement lastNameBlankError;
		
		@FindBy (id = "address_span")
		WebElement billingAddressBlankError;
		
		@FindBy (id = "cc_num_span")
		WebElement creditCardNumberBlankError;
		
		@FindBy (id = "cc_type_span")
		WebElement creditCardTypeBlankError;
		
		@FindBy (id = "cc_expiry_span")
		WebElement creditCardExpiryBlankError;
	    
		@FindBy (id = "cc_cvv_span")
		WebElement cvvNumberBlankError;
		
		
	    // Actions
	    public String getBookHotelPageURL() {
			String url= driver.getCurrentUrl();
			System.out.println("Book Hotel Page url:" +url);
			return url;
		}
	    public void waitForVisibility(WebElement element, int timeOutInSeconds) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		    wait.until(ExpectedConditions.visibilityOf(element));
		}
	    
		public boolean isAdactinLogoExist() {
			waitForVisibility(adactinLogo_BookHotel, 10);
			return adactinLogo_BookHotel.isDisplayed();
		}
		
		public boolean isSearchHotelLinkExistinBookHotel() {
			waitForVisibility(SearchHotelLink_InBookHotelPage, 10);
			return SearchHotelLink_InBookHotelPage.isDisplayed();
		}
		
		public boolean isBookedItineraryLinkExistInBookHotelPage() {
			waitForVisibility(BookedItineraryLink_InBookHotelPage, 10);
			return BookedItineraryLink_InBookHotelPage.isDisplayed();
		}
		
		public boolean isChangePasswordLinkExistInBookHotelPage() {
			waitForVisibility(ChangePasswordLink_InBookHotelPage, 10);
			return ChangePasswordLink_InBookHotelPage.isDisplayed();
		}
		public boolean isLogoutLinkExistInBookHotelPage() {
			waitForVisibility(LogoutLink_InBookHotelPage, 10);
			return LogoutLink_InBookHotelPage.isDisplayed();
		}
		
		public String getTextOfWelcomeMsgBookHotel() {
			waitForVisibility(txt_welcome_BookHotel, 10);
			return txt_welcome_BookHotel.getText();
		}
		
		public String getTextofLoggedInUsernameInBookHotel() {
			waitForVisibility(txt_LoggedInUserName_BookHotel, 10);
			return txt_LoggedInUserName_BookHotel.getDomProperty("value");
		}
	    public void enterFirstName(String fname) {
	    	waitForVisibility(firstName, 10);
	        firstName.sendKeys(fname);
	    }

	    public void enterLastName(String lname) {
	    	waitForVisibility(lastName, 10);
	        lastName.sendKeys(lname);
	    }

	    public void enterBillingAddress(String address) {
	    	waitForVisibility(billingAddress, 10);
	        billingAddress.sendKeys(address);
	    }

	    public void enterCreditCardNumber(String ccNum) {
	    	waitForVisibility(creditCardNumber, 10);
	        creditCardNumber.sendKeys(ccNum);
	    }

	    public void selectCreditCardType(String cardType) {
	    	waitForVisibility(creditCardType, 10);
	        Select select = new Select(creditCardType);
	        select.selectByVisibleText(cardType);
	    }

	    public void selectExpiryMonth(String month) {
	    	waitForVisibility(expMonth, 10);
	        Select select = new Select(expMonth);
	        select.selectByVisibleText(month);
	    }

	    public void selectExpiryYear(String year) {
	    	waitForVisibility(expYear, 10);
	        Select select = new Select(expYear);
	        select.selectByVisibleText(year);
	    }

	    public void enterCVV(String cvv) {
	    	waitForVisibility(cvvNumber, 10);
	        cvvNumber.sendKeys(cvv);
	    }

	    public BookingConfirmationPage clickBookNow() {
	    	waitForVisibility(bookNowButton, 10);
	        bookNowButton.click();
	        return new BookingConfirmationPage(driver);//page chaining
	    }

	    public void clickCancel() {
	    	waitForVisibility(cancelButton, 10);
	        cancelButton.click();
	    }
	    public void clickBack() {
	    	waitForVisibility(back_Link, 20);
	        back_Link.click();
	    }
	    
	    public String getFirstNameErrorMessage() {
			waitForVisibility(firstNameBlankError, 10);
		    return firstNameBlankError.getText();
		}
		
		public String getLastNameErrorMessage() {
			waitForVisibility(lastNameBlankError, 10);
		    return lastNameBlankError.getText();
		}
		
		public String billingAddressErrorMessage() {
			waitForVisibility(billingAddressBlankError, 10);
		    return billingAddressBlankError.getText();
		}
		
		public String CreditcardNumberErrorMessage() {
			waitForVisibility(creditCardNumberBlankError, 10);
		    return creditCardNumberBlankError.getText();
		}
		
		public String CreditcardTypeErrorMessage() {
			waitForVisibility(creditCardTypeBlankError, 10);
		    return creditCardTypeBlankError.getText();
		}
		
		public String ExpiryDateErrorMessage() {
			waitForVisibility(creditCardExpiryBlankError, 10);
		    return creditCardExpiryBlankError.getText();
		}
		public String CVVNumberErrorMessage() {
			waitForVisibility(cvvNumberBlankError, 10);
		    return cvvNumberBlankError.getText();
		}
		
	    
	    /* Combined method for booking
	    public void bookHotel(String fname, String lname, String address, String ccNum,
	                          String cardType, String expMonth, String expYear, String cvv) {
	        enterFirstName(fname);
	        enterLastName(lname);
	        enterBillingAddress(address);
	        enterCreditCardNumber(ccNum);
	        selectCreditCardType(cardType);
	        selectExpiryMonth(expMonth);
	        selectExpiryYear(expYear);
	        enterCVV(cvv);
	        clickBookNow();
	    }*/
}
