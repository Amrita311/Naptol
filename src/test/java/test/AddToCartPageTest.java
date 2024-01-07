package test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.Browser;
import pom.CartPage;
import pom.NaptolHomePage;
import pom.ProductQuickViewPage;
import pom.ProductResultPage;

@Listeners(test.Listener.class)

public class AddToCartPageTest extends BaseTest
{
	ExtentReports extentReports;
	ExtentTest test;
	
	NaptolHomePage naptolHomePage;
	ProductResultPage productResultPage;
	ProductQuickViewPage productQuickViewPage;
	
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
		naptolHomePage=new NaptolHomePage(driver);
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
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
	}
	
	@Test
	public void VerifyIfUserIsAbleToAddMultipleProductsToCart()
	{
		test=extentReports.createTest("CreateVerifyIfUserIsAbleToAddMultipleProductsToCart");
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("Mobiles");
		naptolHomePage.clickOnSearchButton();
		String pageTitle=driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Mobiles"));
		
	    productResultPage=new ProductResultPage(driver);
		productResultPage.getNoOfProducts();
		productResultPage.clickOnQuickViewButton(driver, 1);
		Assert.assertTrue(productResultPage.getNoOfProducts()>0);
		
		productQuickViewPage=new ProductQuickViewPage(driver);
		productQuickViewPage.clickOnClickHereToBuy();
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		cartPage.clickOnContinueShopping();
		
		naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.clearSearchTab();
		naptolHomePage.enterProductName("Cooker");
		naptolHomePage.clickOnSearchButton();
		String title=driver.getTitle();
		Assert.assertTrue(title.contains("Cooker"));
		
		 productResultPage=new ProductResultPage(driver);
		 productResultPage.getNoOfProducts();
		 productResultPage.clickOnQuickViewButton(driver, 1);
		 
		 productQuickViewPage=new ProductQuickViewPage(driver);
		 productQuickViewPage.clickOnClickHereToBuy();
		
		
	}
	

	@AfterTest
	public void publishReport()
	{
		extentReports.flush();
	}

}
