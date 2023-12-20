package org.smartdino.tests;

import java.lang.reflect.Method;

import org.smartdino.utils.WebDriverWrapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;







public class BaseTest {
	
	protected WebDriverWrapper actionObj;
	
	public ExtentReports report;
	
	public ExtentSparkReporter spark;
	ExtentTest extentTest;
    
	
	@BeforeSuite
	public void instantiateReorter() {
		report = new ExtentReports();
		spark = new ExtentSparkReporter("target/SmartDino.html");
		report.attachReporter(spark);
	}
	
	@BeforeMethod
	public void invokeBrowser(Method method) {
		actionObj = new WebDriverWrapper();
		extentTest=report.createTest(this.getClass().getName()+"."+method.getName(),method.getAnnotation(Test.class).description());	 
		
	}
	
	
	
	@AfterMethod(alwaysRun=true)
	public void printReport(ITestResult result) {
			switch (result.getStatus()) {
			case ITestResult.FAILURE:
				extentTest.log(Status.FAIL, result.getThrowable());
				System.out.println(result.getThrowable());
				break;
			case ITestResult.SKIP:
				extentTest.log(Status.SKIP, result.getThrowable());
				System.out.println(result.getThrowable());
				break;
			default:
				break;
			} 
	}

	
	@AfterMethod(alwaysRun=true)
	public void quitBrowser() {
		actionObj.browserQuit();
	}
	
	@AfterSuite(alwaysRun=true)
	public void tearDown() {
		report.flush();
	}
	
	

}
