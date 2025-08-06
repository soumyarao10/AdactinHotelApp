package com.hoteladactin.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {
	WebDriver driver;

    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	

    // Add web elements and actions related to the Select Hotel Page here

}
