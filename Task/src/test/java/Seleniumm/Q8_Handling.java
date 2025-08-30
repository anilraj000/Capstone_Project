package Seleniumm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.util.Set;

public class Q8_Handling {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    String parentWindowHandle;

    @BeforeClass
    public void setUp() {
        // Set up ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport8.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate and get parent window handle
        driver.get("https://the-internet.herokuapp.com/windows");
        parentWindowHandle = driver.getWindowHandle();
    }

    @Test
    public void testMultipleWindows() {
        test = extent.createTest("Multiple Windows Test", "Verify switching between parent and child windows");

        // Click to open new window
        driver.findElement(By.linkText("Click Here")).click();
        test.info("Clicked on 'Click Here' link to open new window");

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        test.info("Window handles found: " + windowHandles.size());

        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                String childTitle = driver.getTitle();
                test.pass("Switched to child window with title: " + childTitle);
                System.out.println("🔵 Child Window Title: " + childTitle);
                driver.close();
                test.info("Closed child window");
            }
        }

        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        String parentTitle = driver.getTitle();
        test.pass("Switched back to parent window with title: " + parentTitle);
        System.out.println("🟢 Parent Window Title: " + parentTitle);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush(); // Write to report
    }
}