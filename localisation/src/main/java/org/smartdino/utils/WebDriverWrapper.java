package org.smartdino.utils;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import java.util.HashMap;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {
		private WebDriver driver;
		int iGlobalTimeout = 20000;

		public WebDriverWrapper() {
			String SELENIUM_BROWSER;
			try {
				SELENIUM_BROWSER = System.getProperty("browser");
			} catch (NullPointerException e) {
				SELENIUM_BROWSER = "chrome";
			}
			if (SELENIUM_BROWSER == null||SELENIUM_BROWSER == "chrome") {
				
			// initialize the driver based on the browser to be used.
				ChromeOptions options=new ChromeOptions();
				options.addArguments("incognito");
				options.addArguments("disable-geolocation");
			    Map<String, Object> prefs = new HashMap<String, Object>();
			    Map<String, Object> profile = new HashMap<String, Object>();
			    Map<String, Object> contentSettings = new HashMap<String, Object>();

			    // SET CHROME OPTIONS
			    // 0 - Default, 1 - Allow, 2 - Block
			    contentSettings.put("geolocation", 1);
			    profile.put("managed_default_content_settings", contentSettings);
			    prefs.put("profile", profile);
			    options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);}
			else if (SELENIUM_BROWSER.contains("firefox")) 
				driver = new FirefoxDriver();
			else if (SELENIUM_BROWSER == "edge") 
				driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		
		
		public DevTools getDevtoolsObj() {
			DevTools devTools=((ChromeDriver)driver).getDevTools();
			return devTools;
		}
		
		
		public void browserQuit() {
			System.out.println("Calling browser quit method");
			driver.quit();
		}
		
		public void delay(int num)  {
			try {
				Thread.sleep(num * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void type(WebElement element, String inputData) {
			// waitTill().until(ExpectedConditions.visibilityOf(element));
			element.clear();
			delay(2);
			element.sendKeys(inputData);
			System.out.println("Entered input: " + inputData);
		}
		
		public WebDriverWait waitTill() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(1000));
			return wait;
		}
		
		public void waitForElementToBeVisible(WebElement element) {
			try{
			waitTill().until(ExpectedConditions.visibilityOf(element));
			}catch(Exception e)
			{}
		}
		
		
		public void click(WebElement element) {
			waitTill().until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			System.out.println("clicked on element: " + element);
		}
		
		public void navigateToUrl(String url) {
			if(driver == null)
				System.out.println("Oops!! the driver is null..");
			System.out.println(url);
			driver.navigate().to(url);
			System.out.println("Navigated to: " + url);
		}
		
		public void handleAlert(String actionTobeTaken) {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			if (actionTobeTaken.equalsIgnoreCase("accept")) {
				alert.accept();
				System.out.println("Alert: Clicked on OK/Yes Button");
			} else
				alert.dismiss();
		}
		
		public String getText(WebElement element) {
			delay(2);
			return element.getText();
		}
		
		
		public void initPageFactory(Object instance) {
			PageFactory.initElements(driver, instance);
		}
		
		
		
		
}

