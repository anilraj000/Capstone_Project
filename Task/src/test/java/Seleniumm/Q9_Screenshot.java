package Seleniumm;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Q9_Screenshot {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Set up ExtentReports
        String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "Augreport9.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");

        // Setup ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void captureScreenshotWithReport() {
        test = extent.createTest("Wikipedia Screenshot Test", "Takes a screenshot of Wikipedia homepage");

        // Step 1: Open Wikipedia
        driver.get("https://www.wikipedia.org/");
        test.info("Opened https://www.wikipedia.org");

        // Step 2: Take Screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // Step 3: Save screenshot with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "wikipedia_" + timestamp + ".png";
        File destFile = new File(screenshotPath);

        try {
            destFile.getParentFile().mkdirs(); // Create screenshots folder if not exists
            Files.copy(srcFile.toPath(), destFile.toPath());
            test.pass("✅ Screenshot taken successfully")
                .addScreenCaptureFromPath(screenshotPath);  // attach screenshot in report
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            test.fail("❌ Failed to save screenshot: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush(); // Generate the report
    }
}