package Wipro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class TC0014 {
	@Parameters("browser")
  @Test
  public void test2(String browser) {
	  if(browser.equalsIgnoreCase("chrome"))
	  {
	  System.out.println("This is chrometest");
	  WebDriverManager.chromedriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.flipkart.com/");
	  }
	  else if(browser.equalsIgnoreCase("firefox"))
			  {
		  System.out.println("This is firefoxtest");
		  WebDriverManager.firefoxdriver().setup();
			WebDriver driver=new FirefoxDriver();
			driver.get("https://www.flipkart.com/");
			  }
	  
	  else if(browser.equalsIgnoreCase("edge"))
	  {
  System.out.println("This is edgetest");
  WebDriverManager.edgedriver().setup();
	WebDriver driver=new EdgeDriver();
	driver.get("https://www.flipkart.com/");
	  }

			  
  }
}
