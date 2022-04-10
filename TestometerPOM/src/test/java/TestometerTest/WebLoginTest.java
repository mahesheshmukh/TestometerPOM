package TestometerTest;

import java.io.IOException;

import org.testng.annotations.Test;

import BaseClass.BaseClass;
import PageMethods.WebLogin;
import Utilities.ReadExcel;
import Utilities.Reports;

public class WebLoginTest extends BaseClass{

	WebLogin login = new WebLogin();
	ReadExcel excel = new ReadExcel();

	@Test (priority = 1)
	public void testLogin() throws IOException
	{
		Reports.test = Reports.extent.createTest("testLogin");
		try
		{
			login.Login(excel.getDataFromCell(loginData, loginSheet, 1, 0), excel.getDataFromCell(loginData, loginSheet, 1, 1));
		}
		catch (Exception e) {
			Reports.failTest("testLogin" + e.getMessage());
		}
	}
	
}
