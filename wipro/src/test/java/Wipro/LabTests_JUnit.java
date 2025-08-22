package Wipro;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class LabTests_JUnit {
    WebDriver driver;

    
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.opencart.com/");
    }

    @Test
    public void testLab2_Modifications() {
        driver.findElement(By.name("search")).sendKeys("Macbook");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        String title = driver.findElement(By.linkText("MacBook")).getText();
        assertEquals("MacBook", title);
    }

    @Test
    public void testLab3_BasicFlow() {
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Mac (1)")).click();

        driver.findElement(By.id("input-sort")).sendKeys("Name (A - Z)");
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();

        String successMsg = driver.findElement(By.cssSelector(".alert-success")).getText();
        assertTrue(successMsg.contains("Success: You have added"));
    }

    
    public void tearDown() {
        driver.quit();
    }
}