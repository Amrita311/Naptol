package pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser 
{
	
	public static WebDriver openBrowser(String name)
	{
		WebDriver driver=null;
		if(name.equalsIgnoreCase("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
	     driver=new ChromeDriver();
		}
		else if(name.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
	
		
		driver.get("https://www.naaptol.com/");
		driver.manage().window().maximize();
		return driver;
	}

}
