package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaptolHomePage
{
	@FindBy (xpath="//a[@href='#LoginBox']") private WebElement login;
	@FindBy (xpath="//a[@href='/customer-support/track-order.html']") private WebElement trackOrder;
	@FindBy (xpath="//div[@class='cate_head']") private WebElement shoppingCategories;
	@FindBy (xpath = "//ul[@id='mainMenu_UL']//li")private List<WebElement> categories;
	@FindBy (xpath = "(//a[text()='Mobile Handsets'])[1]")private WebElement subcategories;
	@FindBy (xpath="//span[@class='arrowNav']")private WebElement shoppingCategoriesDropdown;
	@FindBy (xpath="//input[@id='header_search_text']") private WebElement search;
	@FindBy (xpath = "//li[@class='head']//h1") private WebElement headingOfCategory;
	@FindBy (xpath="(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]") private WebElement searchButton;
	@FindBy (xpath="(//a[@href=\"#ShoppingCartBox\"])[2]") private WebElement cart;
	
	public NaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLogin()
	{
		login.click();
	}
	
	public void clickOnTrackOrder()
	{
		trackOrder.click();
	}
	
	public void clickOnShoppingCategories()
	{
		shoppingCategories.click();
	}
	
	public void clickOnShoppingCategoriesDropdown()
	{
		shoppingCategoriesDropdown.click();
	}
	
	public void selectShoppingCategories(WebDriver driver, int index) {
		Actions action =new Actions(driver);
		action.moveToElement(categories.get(index)).perform();
		subcategories.click();
	}
    	
	public void enterProductName(String productName)
	{
		search.sendKeys(productName);
	}
	
	public String headingOfCategory()
	{
		return headingOfCategory.getText();
	}
	
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	
	public void clickOnCart()
	{
		cart.click();
	}

}
