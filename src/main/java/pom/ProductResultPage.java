package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductResultPage extends BasePage
{
	@FindBy (xpath="//div[@class='grid_Square ']") private List<WebElement> products;
	@FindBy (xpath="//a[@href='#QuickView']") private List<WebElement> quickView;
	
	public ProductResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int getNoOfProducts()
	{
		return products.size();
	}
	
	public void clickOnQuickViewButton(WebDriver driver,int product)
	{
		Actions actions=new Actions(driver);
		actions.moveToElement(products.get(product)).perform();
		quickView.get(product).click();
	}
	
	public void clickOnProductDescription(int product) {
		products.get(product).click();
	}

}
