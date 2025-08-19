package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab6 {

	public static void main(String[] args) throws InterruptedException {
		        // Setup ChromeDriver
		        WebDriverManager.chromedriver().setup();
		        WebDriver driver = (WebDriver) new ChromeDriver();
		        driver.manage().window().maximize();

		        // Step 1: Open OpenCart demo
		        driver.get("http://demo.opencart.com/");

		        // Step 2: Login with credentials (replace with valid credentials)
		        driver.findElement(By.xpath("//span[text()='My Account']")).click();
		        driver.findElement(By.linkText("Login")).click();
		        driver.findElement(By.id("input-email")).sendKeys("your_email@test.com");
		        driver.findElement(By.id("input-password")).sendKeys("your_password");
		        driver.findElement(By.xpath("//input[@value='Login']")).click();

		        // Step 3: Go to Components -> Monitors
		        driver.findElement(By.linkText("Components")).click();
		        driver.findElement(By.linkText("Monitors (2)")).click();

		        // Step 4: Select 25 from 'Show' dropdown
		        Select showDropdown = new Select(driver.findElement(By.id("input-limit")));
		        showDropdown.selectByVisibleText("25");

		        // Step 5: Add first item to cart
		        driver.findElement(By.xpath("(//button[@data-original-title='Add to Cart'])[1]")).click();

		        // Step 6: Click on 'Specification' tab
		        driver.findElement(By.linkText("Specification")).click();

		        // Step 7: Add to Wishlist
		        driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']")).click();
		        Thread.sleep(2000);

		        // Step 8: Verify success message
		        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
		        if(successMsg.getText().contains("Success: You have added Apple Cinema 30\" to your wish list!")) {
		            System.out.println("Wishlist message verified ✅");
		        }

		        // Step 9: Search for 'Mobile'
		        driver.findElement(By.name("search")).sendKeys("Mobile");
		        driver.findElement(By.cssSelector(".btn-default.btn-lg")).click();

		        // Step 10: Enable 'Search in product descriptions'
		        driver.findElement(By.id("description")).click();

		        // Step 11: Click on HTC Touch HD
		        driver.findElement(By.linkText("HTC Touch HD")).click();

		        // Step 12: Change Qty = 3
		        WebElement qtyBox = driver.findElement(By.id("input-quantity"));
		        qtyBox.clear();
		        qtyBox.sendKeys("3");

		        // Step 13: Add to Cart
		        driver.findElement(By.id("button-cart")).click();

		        // Step 14: Verify success message
		        WebElement cartMsg = driver.findElement(By.cssSelector(".alert-success"));
		        if(cartMsg.getText().contains("Success: You have added HTC Touch HD to your shopping cart!")) {
		            System.out.println("Cart message verified ✅");
		        }

		        // Step 15: View cart
		        driver.findElement(By.id("cart-total")).click();
		        driver.findElement(By.linkText("View Cart")).click();

		        // Step 16: Verify product name in cart
		        if(driver.getPageSource().contains("HTC Touch HD")) {
		            System.out.println("Product verified in cart ✅");
		        }

		        // Step 17: Click Checkout
		        driver.findElement(By.linkText("Checkout")).click();

		        // Step 18: Logout
		        driver.findElement(By.xpath("//span[text()='My Account']")).click();
		        driver.findElement(By.linkText("Logout")).click();

		        // Step 19: Verify Account Logout heading
		        if(driver.getPageSource().contains("Account Logout")) {
		            System.out.println("Logout verified ✅");
		        }

		        // Step 20: Continue
		        driver.findElement(By.linkText("Continue")).click();

		        // Close browser
		        driver.quit();
		    }
		}