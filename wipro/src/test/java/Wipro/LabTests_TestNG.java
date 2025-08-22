package Wipro;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LabTests_TestNG {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.opencart.com/");
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testLab2_Modifications() {
        Reporter.log("Starting Lab2 Modifications Test", true);

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.sendKeys("MacBook");
        Reporter.log("Entered search text", true);

        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        Reporter.log("Clicked search button", true);

        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook"))).getText();
        Reporter.log("Captured product title: " + title, true);

        Assert.assertEquals(title, "MacBook");
        Reporter.log("Verification Passed", true);
    }

    @Test(priority = 2)
    public void testLab3_BasicFlow() {
        Reporter.log("Starting Lab3 Basic Flow Test", true);

        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Mac (1)")).click();
        Reporter.log("Navigated to Mac Desktops", true);

        // Use Select for dropdown
        Select sortDropdown = new Select(driver.findElement(By.id("input-sort")));
        sortDropdown.selectByVisibleText("Name (A - Z)");
        Reporter.log("Sorted products by Name (A - Z)", true);

        // Click Add to Cart button
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-original-title='Add to Cart']")));
        addToCartBtn.click();
        Reporter.log("Clicked Add to Cart", true);

        // Verify success message
        String successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))).getText();
        Reporter.log("Captured success message: " + successMsg, true);

        Assert.assertTrue(successMsg.contains("Success: You have added"));
        Reporter.log("Verification Passed", true);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}