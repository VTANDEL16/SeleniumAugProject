package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	public SearchResultPage(WebDriver driver)
	{
		this.driver=driver;
		 eleUtil=new ElementUtils(this.driver);
	}
	
	//By productImages= By.cssSelector("div.product-thumb");

	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}


	
	}

