package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pojo.Browser;
import pom.ClickProductFromDescriptionPage;
import pom.NaptolHomePage;
import pom.ProductResultPage;

@Listeners(test.Listener.class)
public class AddToCartFromProdutDescriptionTest extends BaseTest {
	
	ExtentReports extentReports;
	ExtentTest test;

	@BeforeTest
	public void configureReports()
	{
		extentReports=Reports.genrateReports();
	}

	@BeforeMethod
	public void launchBrowser(String browser)
	{
		driver=Browser.openBrowser(browser);
	}
	
	@Test
	public void  verifyIfUserIsAbleToAddProductToCartUsingDescription()
	{
		test=extentReports.createTest("CreateVerifyIfUserIsAbleToAddProductToCartUsingDescription");
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("Mobiles");
		naptolHomePage.clickOnSearchButton();
		
		ProductResultPage productResultPage=new ProductResultPage(driver);
		productResultPage.clickOnProductDescription(1);
		
		ClickProductFromDescriptionPage clickProductFromDescriptionPage=new ClickProductFromDescriptionPage(driver);
		
		clickProductFromDescriptionPage.switchToNewPage(driver, "https://www.naaptol.com/smart-watches/bluetooth-calling-smart-watch-with-neckband-and-mobile-stand-sc6/p/12612081.html");
		clickProductFromDescriptionPage.clickOnClickHereToBuy();
		
	}
	@AfterTest
	public void publishReports()
	{
		extentReports.flush();
	}
	
	

}
