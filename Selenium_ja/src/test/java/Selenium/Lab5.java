package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab5 {
	public static void main(String []args)
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver =(WebDriver) new ChromeDriver();
		
		driver.get("https://www.opencart.com/");
		String title=driver.getTitle();
		if(title.equals("OpenCart - Open Source Shopping Cart Solution"))
		{
			System.out.println("Title matched");
		}
		else
		{
			System.out.println("Invalid title");
		}
		//driver.sendKeys("")
		
		driver.navigate().to("https://www.yahoo.com/");
		System.out.println(driver.getCurrentUrl());
		
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		
		driver.navigate().forward();
		System.out.println(driver.getCurrentUrl());
		
		System.out.println(driver.getPageSource());
		
	}

}

