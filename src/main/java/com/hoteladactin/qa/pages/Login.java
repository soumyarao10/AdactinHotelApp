package com.hoteladactin.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.hoteladactin.qa.base.TestBase;

public class Login extends TestBase{
	public WebDriver driver;
	
	//WebDriver driver = new ChromeDriver();
	//driver.get("http://adactinhotelapp.com/index.php");
	//Constructor
	public Login(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);//Initialize all web elements
			}
	//Locators
	//WebElement txt_username= driver.findElement(By.id("username"));
	//WebElement txt_password= driver.findElement(By.id("password"));
	//WebElement loginBtn= driver.findElement(By.id("login"));
	//WebElement forgotPwd= driver.findElement(By.linkText("Forgot Password?"));
	//WebElement backToLoginScreen= driver.findElement(By.linkText("Go back to Login page"));
	//WebElement registerNewUser = driver.findElement(By.linkText("New User Register Here"));
	//WebElement backToLoginInRegScreen= driver.findElement(By.linkText("Go back to Login page"));
	//WebElement adactinLogo = driver.findElement(By.className("logo"));
	//WebElement adactinImportantNote= driver.findElement(By.className("build_title"));
	//WebElement adactinImpNoteTxt =driver.findElement(By.xpath("//td//ul/.."));
	
	 @FindBy(id = "username")
	    WebElement txt_username;

	    @FindBy(id = "password")
	    WebElement txt_password;

	    @FindBy(id = "login")
	    WebElement loginBtn;

	    @FindBy(linkText = "Forgot Password?")
	    WebElement forgotPwd;

	    @FindBy(linkText = "New User Register Here")
	    WebElement registerNewUser;

	    @FindBy(className = "logo")
	    WebElement adactinLogo;

	    @FindBy(className = "build_title")
	    WebElement adactinImportantNote;

	    @FindBy(xpath = "//td//ul/..")
	    WebElement adactinImpNoteTxt;
	
	//Actions/Methods
	public String getLoginPageURL() {
		String url= driver.getCurrentUrl();
		System.out.println("Login Page url:" +url);
		return url;
	}
	public boolean isAdactinLogoExist() {
		return adactinLogo.isDisplayed();
	}
	public void setUsername(String username) {
		txt_username.sendKeys(username);
		
	}
	public void setPassword(String password) {
		txt_password.sendKeys(password);
		
	}
	
	public void clickLoginButton(){
		loginBtn.click();
	}
	public boolean isForgotPwdLinkExist() {
		return forgotPwd.isDisplayed();
	}
	
	public boolean isRegisterNewUserLinkExist() {
		return registerNewUser.isDisplayed();
	}

	//public void doLogin(String username1, String password1) {
	//	System.out.println("user credentials"  +username1+ " :" +password1);
		//username.sendKeys("Adactinsr123");
		//password.sendKeys("Shriya1985#");
		//loginBtn.click();	
	
	public String getTextOfImportantNote() {
		return adactinImportantNote.getText();
	}
	
	public String getTextofImpNoteContents() {
		return adactinImpNoteTxt.getText();
	}
	}

