package com.qa.control.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.control.base.BasePage;
import com.qa.control.util.CommonUtil;
import com.qa.control.util.ElementActions;
import com.qa.control.util.JavaScriptUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil jsUtil;
	
	By username = By.id("gkl");
	By password = By.id("gkp");
	By loginBtn = By.id("signIn");
	
	//1.create a constructor of page class and initialize objects with driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
		 jsUtil = new JavaScriptUtil(driver);
	}
	
	//2. define methods
	
	public String getLoginPageTitle()
	{
		return elementActions.getPageTitle();
	}
	
	public HomePage doLogin(String un,String pw) {
		
	   elementActions.sendKeysElement(username, un);
	   elementActions.sendKeysElement(password, pw);
	   elementActions.clickOnElement(loginBtn); 
	   CommonUtil.MediumWait();
	   return new HomePage(driver);
	}

}
