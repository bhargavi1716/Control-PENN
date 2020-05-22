package com.qa.control.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.control.base.BasePage;
import com.qa.control.commons.Constants;
import com.qa.control.listeners.TestAllureListener;
import com.qa.control.pages.HomePage;
import com.qa.control.pages.LoginPage;
import com.qa.control.util.CommonUtil;


@Listeners({TestAllureListener.class})
public class HomePageTest {
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	Properties prop;
	
    WebDriver driver;
	
	@BeforeTest
	public void setup() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		//CommonUtil.MediumWait();  18/05
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.MediumWait();
		
	}
	
	@Test
	public void verifyLinksTest() {
		
		SoftAssert sa = new SoftAssert();
		
		homePage.clickingSideMenu();
		
		String title = homePage.getHomePageTitle();
		sa.assertEquals(title, Constants.HOMEPAGE_TITLE);
	
		boolean homeLink = homePage.clickHomeLink();
		sa.assertTrue(homeLink,"Home Page is not displayed");
		
		boolean adminLink = homePage.clickAdminLink();
		sa.assertTrue(adminLink,"Admin Page is not displayed");
		
		boolean monitoringLink = homePage.clickMonitoringLink();
		sa.assertTrue(monitoringLink,"Monitoring Page is not displayed");
		
		boolean notificationsLink = homePage.clickNotificationsLink();
		sa.assertTrue(notificationsLink,"Notifications Page is not displayed");
		
		boolean playersLink = homePage.clickPlayersLink();
		sa.assertTrue(playersLink,"Players Page is not displayed");
		
		boolean reportsLink = homePage.clickReportsLink();
		sa.assertTrue(reportsLink,"Reports Page is not displayed");
		
		boolean rewardsLink = homePage.clickRewardsLink();
		sa.assertTrue(rewardsLink,"Rewards Page is not displayed");
		
		boolean paymentsLink = homePage.clickPaymentsLink();
		sa.assertTrue(paymentsLink,"Payments Page is not displayed");
		
		boolean deskLink = homePage.clickDeskLink();
		sa.assertTrue(deskLink,"Desk Page is not displayed");
		
		boolean gameAdminLink = homePage.clickGameAdminLink();
		sa.assertTrue(gameAdminLink,"Desk Page is not displayed");
		
		sa.assertAll();

	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}


