package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	@FindBy (xpath="//ul[@id='cartData']") private List<WebElement> availableProducts;
	@FindBy (xpath="(//a[@class='red_button2'])[1]") private WebElement proceedToCheckout;
	@FindBy (xpath="(//a[@onclick=\"cart.continueShopping()\"])[1]") private WebElement continueShopping;
	@FindBy (xpath= "//a[text()='Remove']")private List<WebElement> removeProduct;
	@FindBy (xpath="//div[@id='cartItems']//li[@class='head_item']//div//h2") private List<WebElement> productDescription;
	@FindBy (xpath="//div[@id='cartItems']//li[@class='head_UPrice']") private List<WebElement> productPrice;
	@FindBy (xpath="//div[@id='cartItems']//li[@class='head_ship']") private List<WebElement> productShippingCharges;
	@FindBy (xpath="//div[@id='cartItems']//li[@class='head_Amount']") private List<WebElement> orderAmount;
	
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int getNoOfProductsInCart(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		return availableProducts.size();
	}
	
	
	
	public void clickOnContinueShopping()
	{
		continueShopping.click();
	}
	
	public void clickOnRemove(WebDriver driver,int index)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		removeProduct.get(index).click();
	}
	
	public String getproductDescription(int index)
	{
		return productDescription.get(index).getText();
	}
	
	public String getproductPrice(int index)
	{
		return productPrice.get(index).getText();
		/*String price=productPrice.get(index).getText();
		String productPrice1[]=price.split(" ");
		return productPrice1[1];*/
	}
	
	public String getproductShippingCharge(int index)
	{
		return productShippingCharges.get(index).getText();
		/*String charge=productShippingCharges.get(index).getText();
		String charges[]=charge.split(" ");
		return charges[1];*/
	}
	
	public String getOrderAmount(int index)
	{
		return orderAmount.get(index).getText();
	}
	

	
}
