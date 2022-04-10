package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.*;

import BaseClass.BaseClass;

public class Reports extends BaseClass{

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String reportPath = curerntDir + "\\Reports\\" + timestamp +"_Reports\\" + "Report.html";

	public static void startReport()
	{
		htmlReporter = new ExtentHtmlReporter(reportPath);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("URL", webURL);
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("Suite", suite);
		extent.setSystemInfo("QA", "Mahesh");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public static void infoTest(String desc)
	{
		test.info(desc);
	}
	
	public static void passTest(String desc)
	{
		test.pass(desc);
	}
	
	public static void failTest(String desc) throws IOException
	{
		String screenshotPath =  getScreenshot();
		test.fail("Failed to " + desc , MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
	}
	
	public static String getScreenshot() throws IOException
	{
		String scrBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(scrBase64);
		byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		return new String(encoded, StandardCharsets.US_ASCII);
	}
	
}
