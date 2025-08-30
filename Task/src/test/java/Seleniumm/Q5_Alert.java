package Seleniumm;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class Q5_Alert {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Setup ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport5.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void handleJsAlert() {
        test = extent.createTest("JS Alert Handling Test", "Test to handle JavaScript alert and verify result text");

        // Step 1: Click JS Alert button
        WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertButton.click();
        test.info("Clicked on 'Click for JS Alert' button");

        // Step 2: Switch to alert and accept
        Alert alert = driver.switchTo().alert();
        test.info("Switched to alert");
        alert.accept();
        test.info("Accepted the alert");

        // Step 3: Verify result text
        WebElement resultText = driver.findElement(By.id("result"));
        String actualText = resultText.getText();
        String expectedText = "You successfully clicked an alert";

        test.info("Result text displayed: " + actualText);
        Assert.assertEquals(actualText, expectedText, "Alert result text did not match!");
        test.pass("Alert result text matches expected text");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            if (test != null) test.info("Browser closed");
        }
        extent.flush(); // Write the report to disk
    }
}