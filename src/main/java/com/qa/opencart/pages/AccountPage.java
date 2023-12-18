package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtils(this.driver);
	}
	
	private By AccHeader=By.cssSelector("(//div[@id='content']/h2)[1]");
	private By logoutLink= By.linkText("Logout");
	private By search= By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	private By Headers =By.xpath("//div[@id='content']/h2");
	
	
	
	
	public boolean  IsAccountHeaderPresent()
	{
		boolean flag=driver.findElement(AccHeader).isDisplayed();
		return flag;
	}
	public List<String>IsAllHeaders()
	{
		 List<WebElement> headerelements = driver.findElements(Headers);
		 List<String> headerValue=new ArrayList<String>();
		 for(WebElement e: headerelements)
		 {
			String text =e.getText();
			headerValue.add(text);
		 }
		 return headerValue;
	}
	
	
	public boolean IsLogoutLinkExsist()
	{
		return driver.findElement(logoutLink).isDisplayed();
	}
	public void logout()
	{
		eleUtil.waitForVisibilityOfElement(logoutLink,AppConstants.MEDIUM_DEFAULT_WAIT ).click();
	}
	public String  getAccountPagetittle()
	{
		String tittle=driver.getTitle();
		System.out.println("tittle is "+tittle);
		return tittle;
	}
	public String getAccountPageURL()
	{
		String url=driver.getCurrentUrl();
		System.out.println("url is "+url);
		return url;
	}
	
	public  SearchResultPage doSearch(String searchKey)
	{
		
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		 return new SearchResultPage(driver);
	}
	

}
