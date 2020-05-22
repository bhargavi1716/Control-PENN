package com.qa.control.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.control.pages.PlayersPage;
import com.qa.control.util.CommonUtil;
import com.qa.control.util.ElementActions;
import com.qa.control.util.JavaScriptUtil;

public class HomePage {
	
	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil jsUtil;

	//Side Links.
	
	By SideMenu = By.xpath("//*[@id='sideMenu']");
	By Home = By.xpath("//li[@_name = 'Home']");
	By Admin = By.xpath("//li[@_name = 'Administration']");
	By Monitoring = By.xpath("//li[@_name = 'Monitoring']");
	By Notifications = By.xpath("//li[@_name = 'Notification']");
	By Players = By.xpath("//li[@_name = 'Players']");
	By Reports = By.xpath("//li[@_name = 'Reports']");
	By Rewards = By.xpath("//li[@_name = 'Rewards']");
	By Payments = By.xpath("//li[@_name = 'Payment']");
	By Desk = By.xpath("//li[@_name = 'Desk']");
	By GameAdmin = By.xpath("//li[@_name = 'GameAdmin']");
	
	//Search Panel,Logout & BookMarks
	
	By Search = By.xpath("//div[@id='globalSearch']");
	By ProfileImage = By.xpath("//img[@id='profileImage']");
	By Logout = By.xpath("//a[text()='Logout']");
	By BookMarksLink = By.xpath("//a[@id='bookmarksLinkA']");
	
	//IGT Control Logo
	
	By IgtControlLogo = By.xpath("//img[@id='logo-style']");
	
	//1.create a constructor of page class and initialize objects with driver
		public HomePage(WebDriver driver) {
			this.driver = driver;
			elementActions = new ElementActions(driver);
			jsUtil = new JavaScriptUtil(driver);
		}
	
		
		//2. Methods
		public String getHomePageTitle()
		{
			return elementActions.getPageTitle();
		}
		
		public void clickingSideMenu() {
			elementActions.clickOnElement(SideMenu);
		}
		
		public boolean clickHomeLink() {
		return elementActions.isDisplayed(Home);
	   }
		
		public boolean clickAdminLink() {
		  return elementActions.isDisplayed(Admin);
		}
		
		public boolean clickMonitoringLink() {
			return elementActions.isDisplayed(Monitoring);
		}
		
		public boolean clickNotificationsLink() {
			return elementActions.isDisplayed(Notifications);
		}
		
		public boolean clickPlayersLink() {
			return elementActions.isDisplayed(Players);
		}
		
		public boolean clickReportsLink() {
			return elementActions.isDisplayed(Reports);
		}
		
		public boolean clickRewardsLink() {
			return elementActions.isDisplayed(Rewards);
		}
		
		public boolean clickPaymentsLink() {
			 return elementActions.isDisplayed(Payments);
		}
		
		public boolean clickDeskLink() {
			 return elementActions.isDisplayed(Desk); 
		}
		
		public boolean clickGameAdminLink() {
			 return elementActions.isDisplayed(GameAdmin);
		}
		
		public PlayersPage gotoPlayersPage() {			
			 elementActions.clickOnElement(Players);
			 CommonUtil.ShortWait();
		     return new PlayersPage(driver);
		
		}
	

}
