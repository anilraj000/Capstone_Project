package Wipro;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TC002 {

	
		// TODO Auto-generated method stub
		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			WebDriver driver=(WebDriver) new ChromeDriver();
	        driver.get("https://www.google.com/");
	        System.out.println("Title:" + driver.getTitle());
	        WebElement search = driver.findElement(By.id("APjFqb"));
	        search.sendKeys("Automation Testing tools");
	        Thread.sleep(3000);
	        search.sendKeys(Keys.ENTER);
	        Thread.sleep(3000);

	        //search.submit();
	        //driver.findElement(By.name("btnk")).click();
	        System.out.println("Title: " + driver.getTitle());
	        //driver.quit();
	}

}
