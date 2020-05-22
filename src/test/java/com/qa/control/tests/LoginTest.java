package com.qa.control.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.control.base.BasePage;
import com.qa.control.commons.Constants;
import com.qa.control.listeners.TestAllureListener;
import com.qa.control.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Listeners({TestAllureListener.class})
public class LoginTest {
	
	BasePage basePage;
	LoginPage loginPage;
	Properties prop;
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		//CommonUtil.MediumWait();
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority = 1)
	@Description("verify Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is : " +title );
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE);
	}
	
	@Test(priority = 2)
	@Description("verify Login Page Title Test")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
