package com.hoteladactin.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	
	public Properties initProp() {
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./src/main/java/com/hoteladactin/qa/config/config.properties");
			prop.load(ip);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static void initialization() {
		TestBase base = new TestBase();
        base.initProp();
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")){
			driver = new ChromeDriver();
		}
		else if (browserName.equals("firefox")){
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(prop.getProperty("url"));
		
	}

}
