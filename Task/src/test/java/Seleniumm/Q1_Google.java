package Seleniumm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class Q1_Google {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Define report path
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport1.html";

        // Use ExtentSparkReporter instead of ExtentHtmlReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");

        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void openGoogleAndPrintTitle() {
        test = extent.createTest("Google Title Test", "Open Google and print the page title");

        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        // Test validation using TestNG assertion
        Assert.assertTrue(title.contains("Google"), "Title does not contain 'Google'");

        test.pass("Page title is: " + title);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            
        }
        extent.flush(); // Write everything to the report
    }
}