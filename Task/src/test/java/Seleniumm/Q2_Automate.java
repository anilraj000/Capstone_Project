package Seleniumm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class Q2_Automate {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Set up ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport2.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");

        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest() {
        test = extent.createTest("Login Test", "Login to HerokuApp and verify success message");

        driver.get("https://the-internet.herokuapp.com/login");
        test.info("Opened login page");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        test.info("Entered username");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        test.info("Entered password");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
        test.info("Clicked login button");

        WebElement successMessage = driver.findElement(By.id("flash"));
        String actualText = successMessage.getText();
        test.info("Captured success message: " + actualText);

        Assert.assertTrue(actualText.contains("You logged into a secure area!"),
                "Login success message not found. Actual message: " + actualText);

        test.pass("Login success message verified");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush();
    }
}