package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pojo.Browser;
import pom.ClickProductFromDescriptionPage;
import pom.NaptolHomePage;
import pom.ProductResultPage;

@Listeners(test.Listener.class)
public class AddToCartFromProdutDescriptionTest extends BaseTest {
	
	@BeforeMethod
	public void openChrome()
	{
		driver=Browser.openBrowser();
	}
	
	@Test
	public void  verifyIfUserIsAbleToAddProductToCartUsingDescription()
	{
		NaptolHomePage naptolHomePage=new NaptolHomePage(driver);
		naptolHomePage.enterProductName("Mobiles");
		naptolHomePage.clickOnSearchButton();
		
		ProductResultPage productResultPage=new ProductResultPage(driver);
		productResultPage.clickOnProductDescription(1);
		
		ClickProductFromDescriptionPage clickProductFromDescriptionPage=new ClickProductFromDescriptionPage(driver);
		
		clickProductFromDescriptionPage.switchToPage(driver, "https://www.naaptol.com/smart-watches/bluetooth-calling-smart-watch-with-neckband-and-mobile-stand-sc6/p/12612081.html");
		clickProductFromDescriptionPage.clickOnClickHereToBuy();
		
	}
	
	

}
