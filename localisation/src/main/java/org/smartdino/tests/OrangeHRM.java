package org.smartdino.tests;


import org.smartdino.PageActions.LoginPA;
import org.smartdino.PageObjects.LoginPO;
import org.smartdino.utils.PropertiesFileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class OrangeHRM extends BaseTest{
	
PropertiesFileReader properties;
LoginPA pageAction;
LoginPO pageObj;
	
	@BeforeMethod(alwaysRun = true)
	public void setup()  throws Exception {
		properties=new PropertiesFileReader();
		properties.loadProperties("testData");
		pageObj=new LoginPO(actionObj);
		pageAction= new LoginPA(actionObj,pageObj,properties);
	}
	
	
	
	 @DataProvider(name = "authRequired")
	    private  Object[][] authRequiredProvider() {
		 properties=new PropertiesFileReader();
		 properties.loadProperties("testData");
				return new Object[][]{
					   {properties.getValue("validUsername"), properties.getValue("inValidPassword"),properties.getValue("errorMessage")},
					   {properties.getValue("invalidUserName"),properties.getValue("validPassword"),properties.getValue("errorMessage")}};

	    }

	
	@Test(description="Verify Login Functionality",groups= {"Regression","login"})
	public void  validateLoginWithValidCredentials() {
		pageAction.goToLoginURL();
		actionObj.delay(2);
		pageAction.enterUserName(properties.getValue("validUsername"));
		pageAction.enterPassword(properties.getValue("validPassword"));
		pageAction.clickLogin();
		Assert.assertEquals(actionObj.getText(pageObj.dashbordHeader_we), properties.getValue("Header"), "Login is successfull and navigated to Dashboard page");		
	}

	@Test(description="Verify Login Functionality",dataProvider = "authRequired",groups= {"Regression","login"})
	public void  validateLoginWithValidCredentials(String username,String password,String errorMsg) {
		pageAction.goToLoginURL();
		actionObj.delay(2);
		pageAction.enterUserName(username);
		pageAction.enterPassword(password);
		pageAction.clickLogin();
		Assert.assertEquals(actionObj.getText(pageObj.invalidCredsErrMsg_we),errorMsg, "Invalid creds Error message validated Sucessfully");
	}
	
	
	
	

}
