package test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
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
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchBrowser(String browser)
	{
		driver=Browser.openBrowser(browser);
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
		productResultPage.clickOnQuickViewButton(driver, 0);
		Assert.assertTrue(productResultPage.getNoOfProducts()>0);
		
		System.out.println("Welcome");
		
		ProductQuickViewPage productQuickViewPage=new ProductQuickViewPage(driver);
		System.out.println("price: "+productQuickViewPage.getProductPrice());
		System.out.println("shipping: "+productQuickViewPage.getShippingCharges());
		System.out.println("Product name"+productQuickViewPage.getProductName(driver));
		
		productQuickViewPage.clickOnClickHereToBuy();
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		System.out.println("Product description: "+cartPage.getproductDescription(0));
		System.out.println("Product price: "+cartPage.getproductPrice(0));
		System.out.println("Shipping charges: "+cartPage.getproductShippingCharge(0));
		System.out.println("Order amount: "+cartPage.getOrderAmount(0));
		
		
		/*double price=Double.parseDouble(cartPage.getproductPrice(0));
		double shipping=Double.parseDouble(cartPage.getproductShippingCharge(0));
		double total=price+shipping;
		System.out.println("Total amount");
		
		 double price=Double.parseDouble(cartPage.getproductPrice(0));
		 double shipping=Double.parseDouble(cartPage.getproductShippingCharge(0));
		  double total=price+shipping;
		  System.out.println("Total="+total);*/
		  
		  
		
	}
	
	@Test(enabled=false)
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
		productResultPage.clickOnQuickViewButton(driver, 0);
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
