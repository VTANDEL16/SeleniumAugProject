package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Exelutils;

public class RegistrationTest extends BaseTest {

	@BeforeClass
	public void regSetup()
	{
		regPage=lp.navigateToRegister();
	}
	public String getRandomEmailId()
	{
		return "testautomation"+System.currentTimeMillis()+"@Opencart.com";
		
		//UUID FROM JAVA
		//return "testautomation"+UUID.randomUUID()+"@Opencart.com";
	}
	@DataProvider
	public Object[][] getUserRegistrationData()
	{
		return new Object[][] 
		{
			{"kiaran", "patel" , "9876654322", "patel876", "patel876", "NO"},
			{"samina", "sharma", "1232367890", "sel876", "sel876", "NO"},
			{"sajia", "aup","5643637328", "sajia876", "sajia876", "NO"}
		};
	}
	
	@DataProvider
	public Object[][] getUserExcellTestData()
	{
		Object TestData[][]=Exelutils.getTestData(AppConstants.USER_REG_TESTDATA_SHEET_NAME);
		return TestData;
	}
	
	@Test(dataProvider="getUserRegistrationData")
	public void doRegistration(String fname,String lname,String telephon,String pwd,String confipwd,String subscribe )
	{
		boolean isRegDone=regPage.userRegistration(fname, lname,getRandomEmailId(),telephon,pwd,confipwd,subscribe);
		System.out.println(isRegDone);
		Assert.assertTrue(isRegDone);
	}
	

}