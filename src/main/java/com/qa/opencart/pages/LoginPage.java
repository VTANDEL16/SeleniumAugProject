package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotpassword= By.linkText("Forgotten Password");
	private By logo= By.xpath("//img[@title='naveenopencart']");
	private By registerLink=By.linkText("Register");
	
	private By Logout=By.linkText("Logout");
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtils(this.driver);
		
	}
	
	public String getLoginPagetittle()
	{
		String tittle=driver.getTitle();
		System.out.println("tittle is "+tittle);
		return tittle;
	}
	public void getLoginPageURL()
	{
		String url=driver.getCurrentUrl();
		System.out.println("url is "+url);
	}
	public boolean IstLoginPageLogo()
	{
		 boolean flag=driver.findElement(logo).isDisplayed();
		 return flag;
	}
	
	public boolean IsforgotpasswordPage()
	{
		boolean flag=driver.findElement(forgotpassword).isDisplayed();
		return flag;
	}
	
	public AccountPage doLogin(String uname,String pwd)
	{	
		System.out.println("username and password is "+uname+":"+pwd);
		driver.findElement(username).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		return new AccountPage(driver);
	}
	public void doLogout()
	{
		eleUtil.waitForVisibilityOfElement(Logout, AppConstants.MEDIUM_DEFAULT_WAIT).click();
	}
	
	public RegistrationPage navigateToRegister()
	{
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegistrationPage(driver);
	}
	

}
