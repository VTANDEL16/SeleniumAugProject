package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.Exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	// OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop)

	{
		// optionManager=new OptionsManager(prop);;
		String browserName = prop.getProperty("browser");    //if you want to supply browser from property file
		// String browserName =System.getProperty("browser");    //if  you want to supply browser from maven or terminal
		System.out.println("browser is " + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass right browser " + browserName);
			throw new FrameworkException("file not found");

		}
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();    //will give me local copy of driver
		
	}

	public Properties initProp() {
		
		FileInputStream ip = null;
		prop = new Properties();
	
		String envName = System.getProperty("env");

		System.out.println("enviroment name is : " + envName);
		try {
			if (envName == null)
			{
				System.out.println("env is null,hence running your test in QA enviroment");
				ip = new FileInputStream("/Users/vibhutitandel/eclipse-workspace/Aug2023POMSeries/src/test/resource/config/config.qa.properties");
			}

			else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(
							"/Users/vibhutitandel/eclipse-workspace/Aug2023POMSeries/src/test/resource/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream(
							"/Users/vibhutitandel/eclipse-workspace/Aug2023POMSeries/src/test/resource/config/config.dev.properties");
					break;
				case "uat":
					ip = new FileInputStream(
							"/Users/vibhutitandel/eclipse-workspace/Aug2023POMSeries/src/test/resource/config/config.uat.properties");
					break;
				case "stage":
					ip = new FileInputStream(
							"/Users/vibhutitandel/eclipse-workspace/Aug2023POMSeries/src/test/resource/config/config.stage.properties");
					break;
				default:

					System.out.println("Please pass the right enviroment....." + envName);
					throw new FrameworkException("wrong env name");
				}

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

			
			try {
				prop.load(ip);
			} catch (IOException e) {

				e.printStackTrace();
			}
		

		return prop;
	}
		/**
		 * take screenshot
		 */
		public static String getScreenshot(String methodName) {
			
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			
			String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis()+".png";
					
			File destination = new File(path);
			
			try {
				FileHandler.copy(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return path;

		}
}
