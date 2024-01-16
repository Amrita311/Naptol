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
	//@FindBy (xpath="//ul[@id='cartData']//li[5]") private List<WebElement> orderAmount;
	@FindBy (xpath="(//ul[@id='cartTotal']//label)[1]") private WebElement cartAmount;
	
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int getNoOfProductsInCart(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		return availableProducts.size();
	}
	
	
	
	public void clickOnContinueShopping(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		continueShopping.click();
	}
	
	public void clickOnRemove(WebDriver driver,int index)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		removeProduct.get(index).click();
	}
	
	public String getproductDescription(WebDriver driver,int index)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		return productDescription.get(index).getText();
	}
	
	public double getproductPrice(WebDriver driver,int index)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		return Double.parseDouble(productPrice.get(index).getText().substring(3));
		
	}
	
	public double getproductShippingCharge(int index)
	{
		return Double.parseDouble(productShippingCharges.get(index).getText().substring(3));
	}
	
	public double getOrderAmount(WebDriver driver,int index)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		return Double.parseDouble(orderAmount.get(index).getText());
	}
	
	public double getCartAmount(WebDriver driver)
	{
		String amount=cartAmount.getText().substring(3).replace(",", "");
		return Double.parseDouble(amount);
	}
	
	
	
}
