package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pojo.Browser;
import pom.NaptolHomePage;


@Listeners(test.Listener.class)
public class NaptolHomePageTest extends BaseTest
{
	ExtentReports extentReports;
	ExtentTest test;
		
	@BeforeTest
	public void configureReports()
	{
		extentReports=Reports.genrateReports();
	}
	@BeforeMethod
	public void openChrome()
	{
		driver=Browser.openBrowser();
	}
	
	@Test
	public void verifyIfUserIsAbleToClickOnShoppingCategories()
	{
		test=extentReports.createTest("createVerifyIfUserIsAbleToClickOnShoppingCategories");
		NaptolHomePage  naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.clickOnShoppingCategoriesDropdown();
		naptolHomePage.selectShoppingCategories(driver, 3);
		String pageTitle =driver.getTitle();
		
		Assert.assertTrue(pageTitle.contains("Mobile Handset"));
		//Assert.assertEquals(naptolHomePage.headingOfCategory(), "Mobiles : Mobile Handset");
		
	}
	
	@Test
	public void verifyIfUserIsAbleToSearchTheProduct()
	{
		test=extentReports.createTest("createVerifyIfUserIsAbleToSearchTheProduct");
		NaptolHomePage  naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("Cooker");
		naptolHomePage.clickOnSearchButton();
		String pageTitle=driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Cooker"));
	}
	@AfterTest
	public void publishReport()
	{
		extentReports.flush();
	}

}
