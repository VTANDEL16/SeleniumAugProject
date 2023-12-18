package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage lp;
	protected AccountPage acc;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage regPage;
	
	
	@Parameters ({"browser"})
	@BeforeTest
	public void setup(String browserName)//comming browser from xml file because of Parameters use
	{
		df=new DriverFactory ();
		prop=df.initProp();		//its returning prop reference spstore into properties
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		
		driver=df.initDriver(prop);
		lp=new LoginPage(driver);
		
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
