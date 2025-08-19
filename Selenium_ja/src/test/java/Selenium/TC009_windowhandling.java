package Selenium;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
@SuppressWarnings("unused")

public class TC009_windowhandling {
	
	public static void main (String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver(); 
		driver.get ("https: //letcode.in/window"); 
		driver. findElement (By. id ("home") ).click();
		String pwindow=driver.getWindowHandle();
		System.out.println("parent window: "+pwindow);
		System.out.println("url is:"+driver.getCurrentUrl());
	}
}
