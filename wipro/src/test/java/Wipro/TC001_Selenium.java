package Wipro;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
@SuppressWarnings("unused")
public class TC001_Selenium {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=(WebDriver) new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String title = driver.getTitle();
		System.out.println("The title is:" + title);
		Thread.sleep(3000);
		//WebElement username = driver.findElement(By.name("username"));
		//username.sendKeys("Admin");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("Admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

}
