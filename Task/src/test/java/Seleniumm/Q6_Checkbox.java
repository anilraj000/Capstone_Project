package Seleniumm;

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
import java.util.List;

public class Q6_Checkbox {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Setup ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport6.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the page
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void testSelectCheckboxes() {
        test = extent.createTest("Checkbox Selection Test", "Verify checkboxes are selected");

        // Locate checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']"));
        test.info("Located " + checkboxes.size() + " checkboxes");

        // Select checkboxes if not selected
        for (int i = 0; i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()) {
                checkbox.click();
                test.info("Checkbox " + (i + 1) + " was not selected and now clicked");
            } else {
                test.info("Checkbox " + (i + 1) + " already selected");
            }
        }

        // Verify all checkboxes are selected
        for (int i = 0; i < checkboxes.size(); i++) {
            Assert.assertTrue(checkboxes.get(i).isSelected(), "Checkbox " + (i + 1) + " is not selected!");
            test.pass("Checkbox " + (i + 1) + " is selected");
        }
}
}