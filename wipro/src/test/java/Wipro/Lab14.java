package Wipro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;   // for .xls
// If you use .xlsx, replace with: import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Lab14 {
    WebDriver driver;

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe"); // ✅ update path if needed
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        // Enter username and password from Excel
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        Thread.sleep(2000);

        driver.quit();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        // ✅ Excel file must exist here:
        FileInputStream fis = new FileInputStream("C:\\Users\\bhaskar\\eclipse-workspace\\wipro");

        HSSFWorkbook workbook = new HSSFWorkbook(fis);   // use XSSFWorkbook if .xlsx
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) { // start from 1 (skip header row)
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell == null) ? "" : cell.toString();
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}