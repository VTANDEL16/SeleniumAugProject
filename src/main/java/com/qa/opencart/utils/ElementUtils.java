package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private WebDriver driver;
	//ElementUtils eleUtil=new ElementUtils(WebDriver driver)
			
	public ElementUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getAttribute(By locator,String value)
	{
		return driver.findElement(locator).getAttribute(value);
	}
	
	public void doSendkeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
		
	}
	public int getSize(By locator)
	{
		return getfindElements(locator).size();
	}
	
	public WebElement getElement(By locator) {
	
		return driver.findElement(locator);
	}
	public void  doClick(By locator)
	{
		driver.findElement(locator).click();
	}
	
	public List<WebElement> getfindElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public List<String> getAttributeList(By locator,String attVAlue)
	{
		List<WebElement> elements= getfindElements(locator);
		List<String>attlist=new ArrayList<String>();
		for(WebElement e:elements)
		{
			String attval=e.getAttribute(attVAlue);
			attlist.add(attval);
		}
		return attlist;
		
	}
	public List<String>getlinkText(By locator)
	{
		List<WebElement> linkelements = getfindElements(locator);
		List<String> textval=new ArrayList<String>();
		for(WebElement link:linkelements)
		{
			String linkt=link.getText();
			if(linkt.length()!=0)
			{
			textval.add(linkt);
			}
		}
		return textval;
	}
	
	//******* SELECT CLASS METHODS *********/////
	public Select createSelect(By locator)
	{
	 Select se=new Select(driver.findElement(locator));
	 return se;
	}
	public int  getSelectElementsCount(By locator)
	{
		//Select se=new Select(getElement(locator));
		return createSelect(locator).getOptions().size();
		
	}
	
	public void selectDropdownByIndex(By locator,int val)
	{
		//Select se1=new Select(getElement(locator));
		//se1.selectByIndex(val);
		createSelect(locator).selectByIndex(val);
	}
	public void selectDropdownByValue(By locator,String val)
	{
		//Select se2=new Select(getElement(locator));
		//se2.selectByValue(val);
		createSelect(locator).selectByValue(val);
		
	}
	public void selectDropdownByText(By locator,String Value)
	{
		//Select se3=new Select(getElement(locator));
		//se3.selectByVisibleText(Value);
		createSelect(locator).selectByVisibleText(Value);
	}
	
	public List<String> getAllDropdownText(By locator)
	{
		//Select se4=new Select(getElement(locator));
		
		List<WebElement> OpList = createSelect(locator).getOptions();
		List<String>OpTextList=new ArrayList<String>();
		System.out.println(OpList.size());
		
		for(WebElement e1:OpList)
		{
			String text=e1.getText();
			OpTextList.add(text);
		}
		return OpTextList;
	}
	
	public void selectDropdownOption(By locator,String DropdownValue)
	{
		//Select select=new Select(getElement(locator));
		List<WebElement> optionsList = createSelect(locator).getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(DropdownValue)) {
				e.click();
				break;
			}
		}
	}
	public boolean isDropDownMultiple(By locator)
	{
		return  createSelect(locator).isMultiple()?true:false;
		
	}
	
	/********
	 * this method is use to select value from multiselect dropdown 
	 * 1)It can select multiple value
	 * 2) It can select single Value
	 * 3) It can select all the value from the DropDown
	 * @param locator
	 * @param OptionLocator
	 * @param Values
	 */
	public  void selectmultipleValueDropdown(By locator,By OptionLocator ,String...Values) {
		createSelect(locator);
		if(isDropDownMultiple(locator))
		{
			if(Values[0].equalsIgnoreCase("All"))
			{
				List<WebElement> optionList = getfindElements(OptionLocator);
				for(WebElement e:optionList)
				{
					e.click();
				}
			}
		}
		
		else {
		for(String value:Values)
		{
			createSelect(locator).selectByVisibleText(value);
		}
		}
	}
	//*****    Actions Methods	********//////
	public void doActionSendkeys(By locator, String value)
	{
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	public void doActionClick(By locator)
	{
		Actions act=new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	public void twoLevelMenuHandler(By Parentmenulocator, By childMenuLocator) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getElement(Parentmenulocator)).build().perform();
		Thread.sleep(2000);
		doClick(childMenuLocator);
		
	}
	
	public void fourLevelMenuHandler(By Parentmenulocator,By FirstChildmenuLocator,By secondChildmenuLocator,By forthChildMenuLocator) throws InterruptedException
	{
		
		Thread.sleep(2000);
		Actions act=new Actions(driver);
		doClick(Parentmenulocator);
		act.moveToElement(getElement(FirstChildmenuLocator)).build().perform();
		act.moveToElement(getElement(secondChildmenuLocator)).build().perform();
		doClick(forthChildMenuLocator);
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	//Pending
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	/////******** Waits for NonElements ***********//////////
	public String  waitForTittleContains(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		if(wait.until(ExpectedConditions.titleContains("About")))
			{
				String tittle=driver.getTitle();
				System.out.println(tittle);
				return tittle;
			}
			else
			{
				 System.out.println("tittle is not match");
			}
		return null;
		
	}
	public void waitTittleis(By locator,String tittle,int timeout)
	{
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
		if(wait1.until(ExpectedConditions.titleIs(tittle)))
		{
			String title1=driver.getTitle();	
		}
	
	

	
		
	}
	
	
	


}
