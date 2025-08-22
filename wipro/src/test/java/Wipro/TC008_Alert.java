package Wipro;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;



public class TC008_Alert {
	public static void main (String[] args) {
		
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://letcode.in/alert");
		driver. findElement(By.id("accept")).click();
		Alert salert= driver.switchTo().alert();
		System.out.println("The Message is:"+salert.getText()); salert.accept();
		driver. findElement (By.id("confirm")) .click();
		Alert calert=driver.switchTo().alert();
		System.out.println("Confirm alert:"+calert. getText()); calert. dismiss();
		driver. findElement (By.id("prompt")) .click();
		Alert palert=driver.switchTo().alert();
		System.out.println("prompt alert:"+palert.getText());
		palert. sendKeys ("Anil Raj"); palert. accept();
	}

}
	
