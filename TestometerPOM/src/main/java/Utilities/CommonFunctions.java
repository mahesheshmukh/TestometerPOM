package Utilities;

import java.io.File;

public class CommonFunctions {
	
	public static void CreateDir(String dir)
	{
		File file = new File(dir);
		if(!file.exists())
		{
			file.mkdir();
		}
		else
		{
			System.out.println("Directory already exists!!!");
		}
	}

}
