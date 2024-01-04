package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductQuickViewPage 
{
	@FindBy (xpath="//span[@class='offer-price']") private WebElement price;
	@FindBy (xpath="//span[text()='Click here to Buy']") private WebElement clickHereToBuy;
	@FindBy (xpath="//span[@class='ship-price']") private WebElement shippingCharges;
	
	public ProductQuickViewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void getProductPrice()
	{
		price.getText();
	}
	
	public void clickOnClickHereToBuy()
	{
		clickHereToBuy.click();
	}
	
	public void getShippingCharges()
	{
		shippingCharges.getText();
	}
}


