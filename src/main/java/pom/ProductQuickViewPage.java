package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductQuickViewPage 
{
	@FindBy (xpath="//div[@id='square_Details']//h1") private WebElement productName;
	@FindBy (xpath="//span[@class='offer-price']") private WebElement price;
	@FindBy (xpath="//span[text()='Click here to Buy']") private WebElement clickHereToBuy;
	@FindBy (xpath="//span[@class='ship-price']") private WebElement shippingCharges;
	@FindBy (xpath="(//a[text()='Branded'])[2]") private WebElement branded;
	@FindBy (xpath="//ul[@class=\"sizeBox clearfix\"]//li") List<WebElement> color;
	
	public ProductQuickViewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(branded));
		return productName.getText();
	}
	
	public double getProductPrice()
	{
		return Double.parseDouble(price.getText());
	}
	
	public void clickOnClickHereToBuy()
	{
		clickHereToBuy.click();
	}
	
	public double getShippingCharges()
	{
		String charge=shippingCharges.getText();
		String charges[]=charge.split(" ");
		return Double.parseDouble(charges[1]);
	}
	
	public void selectColor(int index)
	{
	   color.get(index).click();
	}
	
	
}


