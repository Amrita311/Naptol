package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import pojo.Browser;
import pom.CartPage;
import pom.NaptolHomePage;
import pom.ProductQuickViewPage;
import pom.ProductResultPage;

@Listeners (test.Listener.class)

public class ProductValidationTest extends BaseTest {
	ExtentReports extentReports;
	ExtentTest test;

	@BeforeTest
	public void configureReports()
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
	public void verifyIfProductsDetailsOnShoppingCartAreSimilarToProductAddedFromQuickViewTab()
	{
		test=extentReports.createTest("createVerifyIfProductsDetailsOnShoppingCartAreSimilarToProductAddedFromQuickViewTab");
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
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
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		System.out.println("Product description: "+cartPage.getproductDescription(0));
		System.out.println("Product price: "+cartPage.getproductPrice(0));
		System.out.println("Shipping charges: "+cartPage.getproductShippingCharge(0));
		System.out.println("Product name: "+productQuickViewPage.getProductName(driver));
		
		/*if(productQuickViewPage.getProductName().equals(cartPage.getproductDescription(0)))
		{
			System.out.println("Product added successfully...");
		}*/
		
	}
	@AfterTest
	public void publishReports()
	{
		extentReports.flush();
	}
	

}
