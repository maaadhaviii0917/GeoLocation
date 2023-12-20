package org.smartdino.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.smartdino.utils.WebDriverWrapper;


public class LocationPO {
	
	@FindBy(how = How.ID, using = "address")
	public WebElement adress_we;
	
	
	public LocationPO(WebDriverWrapper actionObj) throws Exception {
		actionObj.initPageFactory(this);
	}

}
