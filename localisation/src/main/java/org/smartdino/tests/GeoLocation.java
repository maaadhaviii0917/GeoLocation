package org.smartdino.tests;

import java.util.ArrayList;
import java.util.Optional;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.browser.Browser;
import org.openqa.selenium.devtools.v120.browser.model.PermissionType;
import org.openqa.selenium.devtools.v120.emulation.Emulation;
import org.smartdino.PageObjects.LocationPO;
import org.smartdino.utils.PropertiesFileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class GeoLocation extends BaseTest{
	
PropertiesFileReader properties;
LocationPO pageObj;
ArrayList<PermissionType> permissionList;
DevTools devTools;
	
	@BeforeMethod(alwaysRun = true)
	public void setup()  throws Exception {
		properties=new PropertiesFileReader();
		properties.loadProperties("testData");
		pageObj= new LocationPO(actionObj);
		devTools=actionObj.getDevtoolsObj();
		devTools.createSession();
		permissionList = new ArrayList<PermissionType>();
        permissionList.add(PermissionType.GEOLOCATION);
	}

	@Test(description="verify Current geolocation",groups= {"Regression","geoLocation"})
	public void getCurrentGeoLocation() {
        
		// Set geolocation
		devTools.send(Browser.grantPermissions(permissionList, Optional.empty(), Optional.empty()));
        actionObj.navigateToUrl(properties.getValue("url.geolocation"));
		actionObj.delay(2);
		Assert.assertFalse(actionObj.getText(pageObj.adress_we).contains(properties.getValue("settedRegion")), "default Location is Visible");
    }
	
	
	@Test(description="setGeoLoaction to Ukrine and verify Ukrine Adress were showing ",groups= {"Regression","geoLocation"})
	public void setGeoLocation() {
	
        // Set geolocation
        
       	devTools.send(Browser.grantPermissions(permissionList, Optional.empty(), Optional.empty()));
        devTools.send(Emulation.setGeolocationOverride(Optional.of(50.450001), Optional.of(30.523333), Optional.of(1)));
        actionObj.navigateToUrl(properties.getValue("url.geolocation"));
		actionObj.delay(2);
		Assert.assertTrue(actionObj.getText(pageObj.adress_we).contains(properties.getValue("settedRegion")), "Geo Location is set to Ukrine based on latitude and longitude given");
      
    }
	

}
