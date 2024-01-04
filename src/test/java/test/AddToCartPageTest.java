package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pojo.Browser;
import pom.NaptolHomePage;
import pom.ProductQuickViewPage;
import pom.ProductResultPage;

public class AddToCartPageTest extends BaseTest
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
	public void verifyIfUserIsAbleToAddProductUsingQuickViewOption()
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
		
		ProductQuickViewPage productQuickViewPage=new ProductQuickViewPage(driver);
		productQuickViewPage.clickOnClickHereToBuy();
		
		String cartPageTitle=driver.getTitle();
		//Assert.assertTrue(cartPageTitle.contains("Cart"));
		
	}

}
