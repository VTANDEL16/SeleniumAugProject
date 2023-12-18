package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	
	
	public ProductInfoPage (WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtils(this.driver);
	}
	By productHeader=By.cssSelector("div#content h1");
	By productImages=By.cssSelector("ul.thumbnails li");
	
	By productMetaData=By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	By productPriceData=By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	
	private Map<String,String>productMap=new HashMap<String,String>();
	
	public String getProductHeaderName()
	
	{
		String productname= driver.findElement(productHeader).getText();
		
		System.out.println(productname);
		return productname;
	}
	public int getProductImagesCount()
	{
		int imageCount=eleUtil.waitForVisibilityOfElements(productImages,  AppConstants.SHORT_DEFAULT_WAIT).size();
		//int imageCount=driver.findElements(productImages).size();
		System.out.println("Product information"+getProductHeaderName()+"product count "+imageCount);
		return imageCount;
		
	}
	public void getProductMetaData()
	{
		List<WebElement>metaDataList=eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		for(WebElement e: metaDataList)
		{
			String metaData=e.getText();
			String metaKey=metaData.split(":")[0].trim();
			String metaValue=metaData.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	public void getProductPriceData()
	{
		List<WebElement>metaPriceList=eleUtil.waitForVisibilityOfElements(productPriceData,AppConstants.MEDIUM_DEFAULT_WAIT );
		
		String productPrice =metaPriceList.get(0).getText();
		String productEcTaxPrice=metaPriceList.get(1).getText();
		
		productMap.put("price", productPrice);
		productMap.put("extaxprice", productEcTaxPrice);
		
	}
	public Map<String, String> getProductDetails()
	{
		productMap.put("producname",getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		return productMap;
	}

}
