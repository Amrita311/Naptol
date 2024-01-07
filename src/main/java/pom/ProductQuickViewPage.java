package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductQuickViewPage 
{
	@FindBy (xpath="") private WebElement productName;
	@FindBy (xpath="//span[@class='offer-price']") private WebElement price;
	@FindBy (xpath="//span[text()='Click here to Buy']") private WebElement clickHereToBuy;
	@FindBy (xpath="//span[@class='ship-price']") private WebElement shippingCharges;
	
	public ProductQuickViewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() {
		return productName.getText();
	}
	
	public String getProductPrice()
	{
		return price.getText();
	}
	
	public void clickOnClickHereToBuy()
	{
		clickHereToBuy.click();
	}
	
	public String getShippingCharges()
	{
		String charge=shippingCharges.getText();
		String charges[]=charge.split(" ");
		return charges[1];
	}
	
}


