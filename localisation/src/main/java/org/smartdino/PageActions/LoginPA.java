package org.smartdino.PageActions;


import org.smartdino.PageObjects.LoginPO;
import org.smartdino.utils.PropertiesFileReader;
import org.smartdino.utils.WebDriverWrapper;

public class LoginPA {
	WebDriverWrapper actionObj;
	LoginPO pageObj;
	PropertiesFileReader properties;
	
	 public LoginPA(WebDriverWrapper actionObj, LoginPO pageObj, PropertiesFileReader properties) {
		 this.actionObj=actionObj;
		 this.pageObj=pageObj;
		 this.properties=properties;
	 }
	 
	 
	 public void goToLoginURL() {
		actionObj.navigateToUrl(properties.getValue("loginURL"));
	}

	 
	 
	 public void enterUserName(String username) {
		actionObj.waitForElementToBeVisible(pageObj.userName_we);
		actionObj.type(pageObj.userName_we, username);
	}

	public void enterPassword(String password) {
		actionObj.waitForElementToBeVisible(pageObj.password_we);
		actionObj.type(pageObj.password_we, password);
	}

	public void clickLogin() {
		actionObj.waitForElementToBeVisible(pageObj.loginBtn_we);
		actionObj.click(pageObj.loginBtn_we);
	}

   /* public String doLoginWithInvalidCreds(String username, String password){
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return lblInvalidCreds.getText();
    }*/

}
