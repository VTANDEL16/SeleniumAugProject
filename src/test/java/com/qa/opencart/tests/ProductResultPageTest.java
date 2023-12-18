package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Exelutils;

public class ProductResultPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup()
	{
		acc=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getSearchData()
	{
		
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1}
			};
		
	}
	
	@DataProvider
	public static Object[][] getProductTestData()
	{
		return Exelutils.getTestData(AppConstants.PRODUCT_TESTDATA_SHEET_NAME);
	}
	
	
	
	@Test(dataProvider="getProductTestData")
	public void productImagecountTest(String searchKey,String productName,String  imageCount)
	{
		searchResultPage=acc.doSearch(searchKey);
		productInfoPage=searchResultPage.selectProduct(productName);
		int imagecount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(imagecount), imageCount);
	}
	
	@Test
	public void productInfoTest()
	{
		searchResultPage=acc.doSearch("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		productInfoPage.getProductImagesCount();
		
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		
		Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		
	
	}

}
