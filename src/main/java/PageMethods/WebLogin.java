package PageMethods;

import BaseClass.BaseClass;
import PageObjects.LoginObj;

public class WebLogin extends  BaseClass{

	public void Login(String Uname, String Pass)
	{
		sendKeys(LoginObj.UsernameTxt, Uname, "Type Username " + Uname);
		sendKeys(LoginObj.PasswordTxt, Pass, "Type Password " + Pass);
		click(LoginObj.LoginBtn, "Click on Login button");
	}
}
