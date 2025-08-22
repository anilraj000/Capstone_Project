package Wipro;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC007_Select_Dropdown {
	
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		 driver.get("https://tutorialsninja.com/demo/index.php");
		 driver.findElement(By.linkText("Desktops")).click();
		 driver.findElement(By.linkText("Mac (1)")).click();
        WebElement sort = driver.findElement(By.id("input-sort"));
        Select sle = new Select(sort);
        // sle.selectByIndex(4);
        List <WebElement> elecount = sle.getOptions();
        for (int i=0;i<elecount.size();i++) {
        	System.out.println("The values: " + elecount.get(i).getText());
        }
	
	 }
}