package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	
	private By firstname=By.xpath("//input[@id='input-firstname']");
	private By lastname=By.xpath("//input[@id='input-lastname']");
	private By email=By.xpath("//input[@id='input-email']");
	private By telephone=By.xpath("//input[@id='input-telephone']");
	private By password=By.xpath("//input[@id='input-password']");
	private By confirmPassword=By.xpath("//input[@id='input-confirm']");
	
	private By subscribeYes=By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo=By.xpath("//label[normalize-space()='No']");
	private By checkbox=By.xpath("//input[@name='agree']");
	
	private By submit=By.xpath("//input[@type='submit']");
	
	private By successmessage= By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerationLink=By.linkText("Register");
	
	
	public boolean userRegistration(String firstname,String lastname,String email,String telephone,String password,String confirmPassword,String subscribe)
	{
		
		eleUtil.waitForVisibilityOfElement(this.firstname, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstname);
		eleUtil.doSendkeys(this.lastname, lastname);
		eleUtil.doSendkeys(this.email, email);
		eleUtil.doSendkeys(this.telephone, telephone);

		eleUtil.doSendkeys(this.password, password);
		eleUtil.doSendkeys(this.confirmPassword,confirmPassword);
		
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(checkbox);
		eleUtil.doClick(submit);
		
		String successMess=eleUtil.waitForVisibilityOfElement(successmessage,AppConstants.SHORT_DEFAULT_WAIT).getText();
		System.out.println(successMess);			//Register account text message getting only
		if(successMess.contains(AppConstants.USER_REGISTRATION_SUCCESS_MESSAGE))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerationLink);
			return true;
		}
		else
		{
			return false;}	
	}
	
	

}
