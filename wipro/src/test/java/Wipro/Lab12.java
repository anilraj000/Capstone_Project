package Wipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab12{
    public static void main(String[] args) throws IOException {
        // Load properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\bhaskar\\eclipse-workspace\\wipro\\Lab12.properties");
        prop.load(fis);

        // Setup Chrome automatically (WebDriverManager will download driver)
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        // Perform login using locators from properties
        driver.findElement(By.xpath(prop.getProperty("myaccount_link"))).click();
        driver.findElement(By.xpath(prop.getProperty("login_link"))).click();
        driver.findElement(By.xpath(prop.getProperty("email_field"))).sendKeys(prop.getProperty("username"));
        driver.findElement(By.xpath(prop.getProperty("password_field"))).sendKeys(prop.getProperty("password"));
        driver.findElement(By.xpath(prop.getProperty("login_button"))).click();
        
    }
}