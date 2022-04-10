package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

import BaseClass.BaseClass;

public class PropertyManager{
	
	public static String curerntDir = System.getProperty("user.dir");
	private static PropertyManager instance;
	private static String propertyFilePath = curerntDir + "//config.properties";
	private static final Object obj = new Object();

	private static String WebURL;
	private static String Browser;
	private static String Suite;
	
	public static PropertyManager getInstance()
	{
		if(instance == null)
		{
			synchronized (obj) {
				instance = new PropertyManager();
				instance.loadData();
			}
		}
		return instance;
	}
	
	public static void loadData()
	{
		Properties prop = new Properties();
		try
		{
			prop.load(new FileInputStream(propertyFilePath));
		}
		catch (Exception e) {
			System.out.println("Config file no found!!!");
		}
		
		WebURL = prop.getProperty("WebURL");
		Browser = prop.getProperty("Browser");
		Suite = prop.getProperty("Suite");
	}
	
	public String getWebURL()
	{
		return WebURL;
	}

	public static void setWebURL(String webURL)
	{
		WebURL = webURL;
	}
	
	public String getBrowser()
	{
		return Browser;
	}

	public static void setBrowser(String browserr)
	{
		Browser = browserr;
	}
	
	public String getSuite()
	{
		return Suite;
	}

	public static void setSuite(String suites)
	{
		Suite = suites;
	}
}
