package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class TC0011 {
	public static void main (String[]args) {
	// TODO Auto-generated method stub
	WebDriverManager. chromedriver (). setup ();
	WebDriver driver=(WebDriver) new ChromeDriver();
	driver. get ("https://www.amazon.in/") ;
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js. executeScript ("Window.scrollBy(0,1500)");
	
	//js.executeScript("Window.scrollBy(0,document.body.scrollHeight)");
	//js. executeScript ("alert ( 'Hello');");
   }
}