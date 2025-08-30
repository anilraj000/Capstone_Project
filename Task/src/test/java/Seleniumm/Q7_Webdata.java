package Seleniumm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.util.List;

public class Q7_Webdata {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Setup ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport7.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void extractTableData() {
        test = extent.createTest("Table Data Extraction", "Extract names and Jason's email from the first table");

        // Locate rows (excluding header)
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));
        test.info("Found " + rows.size() + " data rows in the table.");

        for (WebElement row : rows) {
            String firstName = row.findElement(By.xpath("td[2]")).getText();
            String lastName = row.findElement(By.xpath("td[1]")).getText();
            String fullName = firstName + " " + lastName;
            System.out.println("- " + fullName);
            test.info("Full Name: " + fullName);

            if (firstName.equalsIgnoreCase("Jason")) {
                String email = row.findElement(By.xpath("td[3]")).getText();
                System.out.println("✅ Email of Jason: " + email);
                test.pass("Email of Jason: " + email);
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            if (test != null) test.info("Browser closed");
        }
        extent.flush(); // Generate report
    }
}