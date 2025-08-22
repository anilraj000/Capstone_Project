package Wipro;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Lab15_parameter {
	WebDriver driver;
	String projectpath=System.getProperty("user.dir");
  @Test(dataProvider = "dp")
  public void f(String firstname,String lastname,String email, String phno, String password, String password1) throws InterruptedException {
	  	String title=driver.getTitle();
		System.out.println("The Title is:"+title);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//i[@class='fa fa-user']")).click();
		driver.findElement(By.linkText("Register")).click();
		String rtitle=driver.findElement(By.xpath("//*[@id='content']/h1")).getText();
		if(rtitle.equals("Register Account"))
		{
			System.out.println("title is matched");
		}
		else
		{
			System.out.println("title is not matched");
		}
		driver.findElement(By.name("firstname")).sendKeys(firstname);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("telephone")).sendKeys(phno);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirm")).sendKeys(password1);
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));
		
	
	  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Before method");
	  WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php");
  }
  @AfterMethod
  public void afterMethod() {
	  System.out.println("After method");
	  driver.quit();
  }


  @DataProvider
  public Object[][] dp() throws IOException, CsvException {
  String[][] data1=new String[1][6];
	  
  String csvFile = projectpath+"\\parameter data.csv";  // Path to your CSV file
  CSVReader csvReader = new CSVReader(new FileReader(csvFile));
  List<String[]> data = csvReader.readAll(); // Read all rows
 
	  
	  for(int i=0;i<data.size();i++)
	  {
		  data1[i][0]=data.get(i)[0];
	
		  data1[i][1]=data.get(i)[1];
		  
		  data1[i][2]=data.get(i)[2];
		  
		  data1[i][3]=data.get(i)[3];
		  
		  data1[i][4]=data.get(i)[4];
		  
		  data1[i][5]=data.get(i)[5];

	  }
	  return data1;
	  
    
    }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("After Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After Suite");
  }

}
