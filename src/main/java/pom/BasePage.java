package pom;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BasePage {
	public void switchToNewPage(WebDriver driver, String ExpectedTitle)
	{
		Set<String> addressOfPage=driver.getWindowHandles();
		Iterator<String> i=addressOfPage.iterator();
		
		
		while(i.hasNext())
		{
			String adress=i.next();
			driver.switchTo().window(adress);
			if(driver.getTitle().contains(ExpectedTitle))
			{
				break;
			}

		}
	}

}
