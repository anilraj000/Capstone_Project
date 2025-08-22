package Wipro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?");

		
		String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        if (actualTitle.equals("Your Store")) {
            System.out.println("Title verification passed");
        } else {
            System.out.println("Title verification failed");
        }
        
        WebElement myAccount = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]"));
        myAccount.click();
        
        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();
        
        WebElement registerHeading = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        if(registerHeading.isDisplayed()) {
        	System.out.println("Register heading is displayed " + registerHeading.getText());
        		
        	}else {
        		System.out.println("Register heading is not found");
        }
        
        WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));
        continueBtn.click();
        
        
        WebElement warningMsg = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]"));
        String warningMsgText = warningMsg.getText();
        if(warningMsgText.contains("Warning: You must agree to the Privacy Policy!")) {
        	System.out.println("Warning msg verified");
        }else {
        	System.out.println("warning msg not verified");
        }
	}

}


