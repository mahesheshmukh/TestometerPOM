package BaseClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Utilities.CommonFunctions;
import Utilities.PropertyManager;
import Utilities.Reports;

public class BaseClass {

	public static WebDriver driver;
	public static String curerntDir = System.getProperty("user.dir");
	public static String loginData = System.getProperty("user.dir") + "\\src\\test\\repository\\TestData.xlsx";
	public static String loginSheet = "Login";
	public static String timestamp= new SimpleDateFormat("yyMdHms") .format(new Date());
	public static String webURL = PropertyManager.getInstance().getWebURL();
	public static String browser = 	PropertyManager.getInstance().getBrowser();
	public static String suite = PropertyManager.getInstance().getSuite();

	@BeforeSuite(alwaysRun = true)
	public void openBrowser()
	{
		try 
		{
			if(browser.equals("Chrome"))
			{
				System.out.println(webURL);
				System.setProperty("webdriver.chrome.driver", curerntDir + "\\src\\main\\repository\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.equals("EDGE"))
			{
				System.out.println(webURL);
				System.setProperty("webdriver.edge.driver", curerntDir + "\\src\\main\\repository\\msedgedriver.exe");
				driver = new EdgeDriver();
			}

			CommonFunctions.CreateDir(curerntDir + "\\" + "Reports");
			CommonFunctions.CreateDir(curerntDir + "\\Reports\\" + timestamp +"_Reports");
			Reports.startReport();

			driver.manage().window().maximize();
			driver.get("https://opensource-demo.orangehrmlive.com");
		}
		catch (Exception e) {
		}
	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			Reports.test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
			Reports.test.fail(result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			Reports.test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
		}
		else {
			Reports.test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
			Reports.test.skip(result.getThrowable());
		}
		Reports.extent.flush();
	}


	/*******************************************Selenium Methods*******************************************/

	public WebElement findWebElement(String locator)
	{
		WebElement webElement = null;
		if(!isBlankorNull(locator))
		{
			if(locator.startsWith("@id"))
			{
				webElement = driver.findElement(By.id(removeStart(locator, "@id=")));
			}
			else if(locator.startsWith("@name")) 
			{
				webElement = driver.findElement(By.name(removeStart(locator, "@name=")));
			}
			else if(locator.startsWith("@className")) 
			{
				webElement = driver.findElement(By.className(removeStart(locator, "@className=")));
			}
			else if(locator.startsWith("@css")) 
			{
				webElement = driver.findElement(By.cssSelector(removeStart(locator, "@css=")));
			}
			else if(locator.startsWith("@linkText")) 
			{
				webElement = driver.findElement(By.linkText(removeStart(locator, "@linkText=")));
			}
			else if(locator.startsWith("@partialLinkText")) 
			{
				webElement = driver.findElement(By.partialLinkText(removeStart(locator, "@partialLinkText=")));
			}
			else if(locator.startsWith("@xpath")) 
			{
				webElement = driver.findElement(By.xpath(removeStart(locator, "@xpath=")));
			}
		}
		return webElement;
	}

	public static boolean isBlankorNull(String str) 
	{
		return str == null;
	}

	public static String removeStart(String str, String remove)
	{
		String returnStr = "";
		if(isBlankorNull(str) || isBlankorNull(remove))
		{
			returnStr = str;
		}
		if(str.startsWith(remove))
		{
			returnStr = str.substring(remove.length());
		}
		return returnStr;
	}

	public void sendKeys(String locator, String value, String desc)
	{
		try {
			WebElement webElement = null;
			if(!isBlankorNull(locator))
			{
				webElement = findWebElement(locator);
				webElement.clear();
				webElement.sendKeys(value);
				Reports.passTest(desc);
			}
		}
		catch (Exception e) {
			try {
				Reports.failTest(desc);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void click(String locator, String desc)
	{
		try {
			WebElement webElement = null;
			if(!isBlankorNull(locator))
			{
				webElement = findWebElement(locator);
				webElement.click();
				Reports.passTest(desc);
			}
		}
		catch (Exception e) {
			try {
				Reports.failTest(desc);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
