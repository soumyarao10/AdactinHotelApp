package com.hoteladactin.qa.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementUtil {
	
	public void selectFromDropdownByVisibleText(WebElement dropdown, String visibleText) {
	    Select select = new Select(dropdown);
	    select.selectByVisibleText(visibleText);
	}

}
