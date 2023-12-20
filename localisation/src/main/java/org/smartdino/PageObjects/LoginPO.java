package org.smartdino.PageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.smartdino.utils.WebDriverWrapper;


public class LoginPO {

	    @FindBy(how=How.NAME,using="username")
	    public WebElement userName_we;
	    
	    @FindBy(how=How.NAME,using="password")
	    public WebElement password_we;
	    
	    @FindBy(how=How.CSS,using="[type=submit]")
	    public WebElement loginBtn_we;
	    
	    @FindBy(how=How.TAG_NAME,using = "p")
	    public WebElement invalidCredsErrMsg_we;
	    
	    @FindBy(how=How.XPATH,using = "//h6[contains(@class,'breadcrumb-module')]")
	    public WebElement dashbordHeader_we;
	  
	    
	    
	    

	    public LoginPO(WebDriverWrapper actionObj) throws Exception{
	    	actionObj.initPageFactory(this);
	    }
	    

}
