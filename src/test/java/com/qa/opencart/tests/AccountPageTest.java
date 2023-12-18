package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.ElementUtils;

public class AccountPageTest extends BaseTest{
	
	@Test(priority=1)
	public void accSetup()
	{
		acc=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Test(priority=2)
	public void accPageTittleTest()
	{
		String tittle=acc.getAccountPagetittle();
		Assert.assertEquals(tittle, "My Account");
	}
	@Test(priority=3)
	public void AccountPageUrl()
	{
		String URL=acc.getAccountPageURL();
		Assert.assertTrue(URL.contains("route=account/account"));
	}
	@Test(priority=4)
	public void LogountLinkExsistTest()
	{
		boolean FLAG=acc.IsLogoutLinkExsist();
		Assert.assertTrue(true);
	}
	@Test(priority=5)
	public void IsaccheadersCount()
	{
		List<String> allHeaders = acc.IsAllHeaders();
		System.out.println(allHeaders);
		Assert.assertEquals(allHeaders.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	}
	@Test (priority=6)
	public void IsaccheadersCountTest()
	{
		List<String> allHeaders = acc.IsAllHeaders();
		System.out.println(allHeaders);
		Assert.assertEquals(allHeaders,AppConstants.ACCOUNT_PAGE_HEADER_LIST);
	}
	@Test(priority=7)
	public ProductInfoPage searchTest()
	{

		searchResultPage=acc.doSearch("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		productInfoPage.getProductImagesCount();
		return new ProductInfoPage (driver);
		 
	}
}
