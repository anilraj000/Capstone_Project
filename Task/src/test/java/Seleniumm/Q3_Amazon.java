package Seleniumm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.util.List;

public class Q3_Amazon {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Set up ExtentReports
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + File.separator + "test-output" + File.separator + "Augreport3.html";

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
    public void searchLaptopOnAmazon() {
        test = extent.createTest("Amazon Laptop Search", "Search for laptops on Amazon and list top 5 results");

        // 1. Open Amazon
        driver.get("https://www.amazon.in/");
        test.info("Opened Amazon.in");

        // 2. Search for "laptop"
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        searchBox.submit();
        test.info("Searched for 'laptop'");

        // 3. Wait for results (not ideal, but acceptable for simple tests)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            test.warning("InterruptedException occurred while waiting for results.");
        }

        // 4. Get product names
        List<WebElement> productTitles = driver.findElements(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal"));

        if (productTitles.size() > 0) {
            test.pass("Search results loaded successfully. Number of results: " + productTitles.size());
        } else {
            test.fail("No products found!");
        }

        System.out.println("Top 5 Laptop Results:");
        for (int i = 0; i < Math.min(5, productTitles.size()); i++) {
            String title = productTitles.get(i).getText();
            System.out.println((i + 1) + ". " + title);
            test.info("Result " + (i + 1) + ": " + title);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush(); // Write the report
    }
}