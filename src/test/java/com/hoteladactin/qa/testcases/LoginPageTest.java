package com.hoteladactin.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hoteladactin.qa.base.TestBase;
import com.hoteladactin.qa.pages.Login;

@Listeners(com.hoteladactin.qa.listener.ExtentReportListener.class)
public class LoginPageTest extends TestBase {
Login login;


public LoginPageTest() {
	super();
}


	@BeforeMethod
public void setUp(){
	initialization();
	login = new Login(driver);
}

	
	@Test(priority=1)
	public void  getLoginPageURLTest() {
	String actUrl=	login.getLoginPageURL();
	Assert.assertTrue(actUrl.contains("HotelAppBuild2/index.php"));
	}

	@Test(priority=2)
	public void isAdactinLogoExistTest() {
		boolean flag= login.isAdactinLogoExist();
		Assert.assertTrue(flag);
	}
	
	
	@Test(priority=3)
	public void isForgotPwdLinkExistTest(){
		boolean flag1= login.isForgotPwdLinkExist();
		Assert.assertTrue(flag1);
	}
	
	@Test(priority=4)
	public void isRegisterNewUserLinkExistTest() {
		boolean flag2= login.isRegisterNewUserLinkExist();
		Assert.assertTrue(flag2);
	}
	
	@Test(priority=5)
	public void getTextOfImportantNoteTest() {
		String ActualTestNote= login.getTextOfImportantNote();
		Assert.assertTrue(ActualTestNote.contains("Important"));
	}
	
	@Test(priority=6)
	public void getTextofImpNoteContentsTest() {
		String ActualTestNoteContents = login.getTextofImpNoteContents();
		Assert.assertTrue(ActualTestNoteContents.contains("Has been developed with known defects"));
	}
	
	@Test(priority=7)
	 public void setUsernameTest() {
		login.setUsername(prop.getProperty("username"));
	}
	
	@Test(priority=8)
	public void setPasswordTest() {
		login.setPassword(prop.getProperty("password"));
	}
	
	@Test(priority=9)
	public void clickLoginButtonTest(){
	login.clickLoginButton();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

}
