package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic100:Design opencart login page")
@Story("US: Login page features")
@Feature("F50: Feature login page")
@Listeners(TestAllureListener.class)
public class LoginPageTest  extends BaseTest{
	
	@Description("login page tittle...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void LoginPageTittletest()
	{
		String lpTitlr=lp.getLoginPagetittle();
		Assert.assertEquals(lpTitlr, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Description("login page URL ...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void LoginPageURlTest()
	{
	lp.getLoginPageURL();
	}
	
	@Description("login page LogoTest...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void LoginPageLogoTest()
	{
		boolean flag=lp.IstLoginPageLogo();
		Assert.assertTrue(flag);
	}
	
	@Description("login page forgotpassword link...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=4)
	public void ForgotPasswordLinkTest()
	{
		boolean flag1=lp.IsforgotpasswordPage();
		Assert.assertTrue(flag1);
	}
	
	@Description("login page Applicatin Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void LoginTest()
	{
		acc=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(acc.IsLogoutLinkExsist());
		acc.logout();
		
	}
	
	@Description("Navigate to Register page...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6)
	public void  navigateToregisterPageTest()
	{
		regPage=lp.navigateToRegister();

	}

}
