package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pojo.Browser;
import pom.CartPage;
import pom.NaptolHomePage;
import pom.ProductQuickViewPage;
import pom.ProductResultPage;

public class CartAmountTest extends BaseTest{
	ExtentReports extentReports;
	ExtentTest test;
	
	NaptolHomePage naptolHomePage;
	ProductResultPage productResultPage;
	ProductQuickViewPage productQuickViewPage;
	CartPage cartPage;
	
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
	
	@Test(enabled=false)
	public void addSingleProductToCartAndVerifIfSumOfUnitPriceShippingPriceIsEqualToOrderAmount()
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
		
		ProductQuickViewPage productQuickViewPage=new ProductQuickViewPage(driver);
		productQuickViewPage.clickOnClickHereToBuy();
		
	    cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		double unitPrice=cartPage.getproductPrice(driver,0);
		double shippingCharges=cartPage.getproductShippingCharge(0);
		double totalAmount=unitPrice+shippingCharges;
		System.out.println("Total amount: "+totalAmount);
		System.out.println("Total Order amount: "+cartPage.getOrderAmount(driver,0));
		
		System.out.println("Cart amount: "+cartPage.getCartAmount(driver));
		
		Assert.assertEquals(cartPage.getOrderAmount(driver,0),totalAmount);
	}
	
	@Test(enabled=true)
	public void AddTwoProductsIntoCartAndVerifyIfSumOfUnitPriceAndShippingPriceIsEqualToOrderAmountAndAlsoVerifyIfSumOfOrderAmountIsEqualToCartAmount()
	{
		test=extentReports.createTest("CreateAddTwoProductsIntoCartAndVerifyIfSumOfUnitPriceAndShippingPriceIsEqualToOrderAmountAndAlsoVerifyIfSumOfOrderAmountIsEqualToCartAmount");
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
		
		cartPage=new CartPage(driver);
		
		
		cartPage.clickOnContinueShopping(driver);
		
		naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.clearSearchTab();
		naptolHomePage.enterProductName("Bedsheet");
		naptolHomePage.clickOnSearchButton();
		String title=driver.getTitle();
		Assert.assertTrue(title.contains("Bedsheet"));
		
		 productResultPage=new ProductResultPage(driver);
		 productResultPage.getNoOfProducts();
		 productResultPage.clickOnQuickViewButton(driver,5);
		 
		 productQuickViewPage=new ProductQuickViewPage(driver);
		 productQuickViewPage.selectColor(0);
		 productQuickViewPage.clickOnClickHereToBuy();
		 
		 cartPage=new CartPage(driver);

		 Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 2);
		 		 
		 /*System.out.println("Product name: "+cartPage.getproductDescription(driver,0));
		 System.out.println("Product name2: "+cartPage.getproductDescription(driver,1));
		 //System.out.println(cartPage.getCartAmount(driver));
		 System.out.println("Price: "+cartPage.getproductPrice(driver,0));
		 System.out.println("Shippoing: "+cartPage.getproductShippingCharge(0));
		 
		 System.out.println("First Product: "+cartPage.getOrderAmount(driver,0));
		System.out.println("Second Product: "+cartPage.getOrderAmount(driver,1));*/
		
		 double unitPrice=cartPage.getproductPrice(driver,0);
		 double shippingPrice=cartPage.getproductShippingCharge(0);
		 double totalAmountOfFirstProduct=unitPrice+shippingPrice;
		 System.out.println("Total Amount1: "+totalAmountOfFirstProduct);
		 
		 double unitPrice1=cartPage.getproductPrice(driver,1);
		  double shippingPrice1=cartPage.getproductShippingCharge(1);
		  double totalAmountOfSecondProduct=unitPrice1+shippingPrice1;
		  System.out.println("Total Amount2: "+totalAmountOfSecondProduct);
		  
		  Assert.assertEquals(cartPage.getOrderAmount(driver, 0), totalAmountOfFirstProduct);
		  Assert.assertEquals(cartPage.getOrderAmount(driver, 1), totalAmountOfSecondProduct);
		  
		  double expectedCartAmount=totalAmountOfFirstProduct+totalAmountOfSecondProduct;
		  
		  Assert.assertEquals(cartPage.getCartAmount(driver), expectedCartAmount);
	}
	
	@AfterTest
	public void publishReports()
	{
		extentReports.flush();
	}
}
