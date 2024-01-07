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
import pom.CartPage;
import pom.NaptolHomePage;
import pom.ProductQuickViewPage;
import pom.ProductResultPage;

@Listeners(test.Listener.class)

public class RemoveProductTest extends BaseTest{
	
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
	public void verifyIfUserIsAbleToRemoveProductFromCart()
	{
		test=extentReports.createTest("CreatVerifyIfUserIsAbleToRemoveProductFromCart");
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
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		cartPage.clickOnRemove(driver, 0);
		int expectedSize=cartPage.getNoOfProductsInCart(driver)-1;
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), expectedSize);
		
		
	}
	
	@AfterTest
	public void publishReport()
	{
		extentReports.flush();
	}

}
