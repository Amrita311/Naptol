package test;

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
import pom.ProductResultPage;

@Listeners(test.Listener.class)
public class ProductSearchTest extends BaseTest
{
	ExtentReports extentReports;
	ExtentTest test;
	
	@BeforeTest
	public void configureReport()
	{
		extentReports=Reports.genrateReports();

	}
	
	@BeforeMethod
	public void openChrome()
	{
		driver=Browser.openBrowser();
	}
	
	@Test
	public void verifyIfProdutsAreDisplayedOnValidSearch()
	{
		test=extentReports.createTest("createVerifyIfProdutsAreDisplayedOnValidSearch");
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("Mobiles");
		naptolHomePage.clickOnSearchButton();
		String pageTitle=driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Mobile"));
		
		ProductResultPage productResultPage=new ProductResultPage(driver);
		productResultPage.getNoOfProducts();
		productResultPage.clickOnQuickViewButton(driver, 1);
		Assert.assertTrue(productResultPage.getNoOfProducts()>0);
	}
	
	@Test
	public void verifyIfProdutsAreNotDisplayedOnInvalidSearch()
	{
		test=extentReports.createTest("createVerifyIfProdutsAreNotDisplayedOnInvalidSearch");
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("iphone");
		naptolHomePage.clickOnSearchButton();
		String pageTitle=driver.getTitle();
		Assert.assertTrue(pageTitle.contains("iphone"));
		
		ProductResultPage productResultPage=new ProductResultPage(driver);
		Assert.assertTrue(productResultPage.getNoOfProducts()==0);
	}
	
	@AfterTest
	public void publishReport()
	{
		extentReports.flush();
	}

}
