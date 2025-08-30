package Seleniumm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class Q4_Dropdown {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Set up ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "DropdownReport.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @Test
    public void testDropdownSelection() {
        test = extent.createTest("Dropdown Selection Test", "Verify Option 2 can be selected from dropdown");

        // Locate dropdown
        WebElement dropdownElement = driver.findElement(By.name("my-select"));
        test.info("Dropdown element located");

        // Select Option 2
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Option 2");
        test.info("Selected 'Option 2' from dropdown");

        // Verify selected option
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        test.info("Selected option text: " + selectedText);

        Assert.assertEquals(selectedText, "Option 2", "Dropdown selection failed!");
        test.pass("Dropdown selection validated successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            if (test != null) test.info("Browser closed.");
        }
        extent.flush(); // Write report
    }
}