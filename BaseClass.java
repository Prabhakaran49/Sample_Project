package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Return;

public class BaseClass {

	public static WebDriver driver;

	//BROWSER LAUNCH
	public static WebDriver chromeLaunch() {
		WebDriverManager.chromedriver().setup();
		return driver=new ChromeDriver();
	}
	
	public static WebDriver edgeLaunch() {
		WebDriverManager.edgedriver().setup();
		return driver=new EdgeDriver();
	}
	public static WebDriver firefox() {
		WebDriverManager.firefoxdriver().setup();
		return driver=new FirefoxDriver();

	}


	
	public static void  urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();

	}

	public static void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	public static void sendKeys(WebElement e,String data ) {
		e.sendKeys(data);

	}
	public static void click(WebElement e) {
		e.click();

	}
	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;

	}
	public static String getTitle() {
		String title = driver.getTitle();
		return title;


	}

	public static void quit() {
		driver.quit();

	}


	//MOUSE ACTIONS


	public static void moveToElement(WebElement element) {
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();

	}

	public static void cLickTheElement(WebElement element) {
		Actions a=new Actions(driver);
		a.click(element).perform();

	}

	public static void clickAndHoldRelease(WebElement element) {
		Actions a=new Actions(driver);
		a.clickAndHold(element).release().perform();


	}
	public static void clickHoldMove(WebElement element1,WebElement element2) {
		Actions a=new Actions(driver);
		a.clickAndHold(element1).moveToElement(element2).release().build().perform();
	}

	public static void dragAndDrop(WebElement from,WebElement to) {
		Actions a=new Actions(driver);
		a.dragAndDrop(from, to).perform();
	}

	public static void doubleClick() {
		Actions a=new Actions(driver);
		a.doubleClick().perform();

	}

	public static void contextClick() {
		Actions a=new Actions(driver);
		a.contextClick().perform();

	}

	//SELECT

	public static   void SelectByIndex(WebElement e,int index) {
		Select s=new Select(e);
		s.selectByIndex(index);


	}

	public static   void SelectByValue(WebElement e,String value) {
		Select s=new Select(e);
		s.selectByValue(value);


	}

	public static   void SelectByVisibleText(WebElement e,String value) {
		Select s=new Select(e);
		s.selectByVisibleText(value);


	}
	public static List<WebElement> getOptions(WebElement e) {
		Select s = new Select(e);
		List<WebElement> options=s.getOptions();
		return options;
		


	}
	public static List<WebElement> getAllSelectedOptions(WebElement e) {
		Select s = new Select(e);
		List<WebElement> options=s.getAllSelectedOptions();
		return options;


	}
	public static void getFirstSelectedOptions() {
		
	}


	//ALERT
	public static void switchAlertAccept() {
		Alert a=driver.switchTo().alert();
		a.accept();
	}

	public static void switchAlertDismiss() {
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}

	public static void alertPromptAccept(String string) {
		Alert prompt = driver.switchTo().alert();
		prompt.sendKeys(string);
		prompt.accept();
	}
	
	//SCREENSHOT
	public static void screenShot(String loc, long date) throws IOException {
		Date d=new Date(date);
		TakesScreenshot tk=(TakesScreenshot) driver;
		File from=tk.getScreenshotAs(OutputType.FILE);
		File to=new File(loc+d);
		FileUtils.copyDirectory(from, to);
	}

//JAVASCRIPT EXECUTOR
	public static void javaScriptSendKeys(String script,WebElement arg)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(script, arg);

	}

public static void javaScriptClick(String script,WebElement arg) {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(script,arg);

	
}
public static WebElement javaScriptGetAttribute(String script,WebElement arg) {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(script,arg);
	return arg;

	
	
}
//SCROLLDOWN
public static void scrollDown(String script,WebElement arg) {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(script,arg);

	
}

public static String excelRead(String filename, String Sheet, int rowindex, int cellindex) throws IOException {
File f=new File("C:\\Users\\prabhakaran\\eclipse-workspace\\Mavennn_Neww\\src\\test\\resources\\"+filename+".xlsx");
	
	FileInputStream fi = new FileInputStream(f);
	
	Workbook w = new XSSFWorkbook(fi);
	
	
	Sheet sheet = w.getSheet(Sheet);
	
	Row row = sheet.getRow(rowindex);
	
	Cell cell = row.getCell(cellindex);
	
	int type = cell.getCellType();
	
	String value=null;
	if (type==1) {
		 value = cell.getStringCellValue();
		
	}
	else
		
		if (DateUtil.isCellDateFormatted(cell)) {
			java.util.Date d = cell.getDateCellValue();
			
			SimpleDateFormat s = new SimpleDateFormat("dd-MMMM-yyyy");
			value=s.format(d);
			
			
			
		
	}else {
		double tt = cell.getNumericCellValue();
		long ln=(long)tt;
		 value = String.valueOf(ln);
		
	}
	return value;
	

}









}
