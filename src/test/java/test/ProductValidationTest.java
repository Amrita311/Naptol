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
		test=extentReports.createTest("CreateverifyIfProductsDetailsOnShoppingCartAreSimilarToProductAddedFromQuickViewTab");
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

		System.out.println("price: "+productQuickViewPage.getProductPrice());
		System.out.println("shipping: "+productQuickViewPage.getShippingCharges());
		System.out.println("Product name from Quick:"+productQuickViewPage.getProductName(driver));
		
		String expectedProductName=productQuickViewPage.getProductName(driver);
		double priceFromQuickView=productQuickViewPage.getProductPrice();
		double shippingChargesFromQuickView=productQuickViewPage.getShippingCharges();
		productQuickViewPage.clickOnClickHereToBuy();
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getNoOfProductsInCart(driver), 1);
		
		Assert.assertEquals(cartPage.getproductDescription(driver,0), expectedProductName);
		Assert.assertEquals(cartPage.getproductPrice(driver,0),priceFromQuickView );
		Assert.assertEquals(cartPage.getproductShippingCharge(0), shippingChargesFromQuickView);
		
	}
	@AfterTest
	public void publishReports()
	{
		extentReports.flush();
	}
	

}
