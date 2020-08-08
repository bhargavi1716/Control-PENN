package com.qa.control.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.control.base.BasePage;
import com.qa.control.commons.Constants;
import com.qa.control.util.CommonUtil;
import com.qa.control.util.ElementActions;
import com.qa.control.util.JavaScriptUtil;


public class PlayersPage extends BasePage{
	
	//Defining page objects.
	//Player Search
	
	By contractId = By.xpath("//span[text()='Contract Id']");
	By search = By.xpath("//*[@type='search']");
	By address = By.xpath("//span[text()='Address']");
	By searchResults = By.xpath("//*[@id='select2-search-criteria-results']");
	By enterValue = By.xpath("//*[@id='value_criteria']");
	By searchButton = By.xpath("//button[@id='searchButton']");
	By searchResult = By.xpath("//*[@id=\\\"playerDataTables\\\"]/tbody/tr");
	
	//Player Found Details
	By foundPlayerRow = By.xpath("//td[@class='fullname_pl']");
	By closePlayerIcon = By.xpath("//a[contains(@id,'buttonCloseTab')]");
	By playerFoundMessage = By.xpath("//div[text()='No Records found']");
	By foundContractId;
	
	String Bxpath = "//label[text()='";
	String Axpath = "']";
	
	By lastName = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[7]//td[2]/label");
	By firstName = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[5]//td[2]/label");
	By email  = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[31]//td[2]/label");
	By gender = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[1]//td[2]/label");
	By userName = By.xpath("//div[@id='boxDetailsPlayer_1']/div[1]//tr[4]//td[2]/label");
	By country = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[9]//td[2]/label"); 
	By State  = By.xpath("//div[@id='boxDetailsPlayer_2']/div[1]//tr[10]//td[2]/label");
	By playerAccountLocked = By.xpath("//*[@id=\"boxDetailsPlayer_1\"]/div/table/tbody/tr[11]/td[2]/label");
	
	//User Detail all Menus
	By main_Menu = By.xpath("//label[text()='" + Constants.USER_DETAIL_VIEW_MAIN + "']");
	By sub_Menu = By.xpath("//a[text()='" + Constants.USER_DETAIL_VIEW_SUB + "']");
	
	//Daily/Weekly/Monthly loss limits
	
     By daily_Loss_Limit = By.xpath("//table[@class='table table-bordered table-striped responsive dataTable moderator']//tr[9]//div[1]/div[1]");
     By weekly_Loss_Limit = By.xpath("//table[@class='table table-bordered table-striped responsive dataTable moderator']//tr[10]//div[1]/div[1]");
     By monthly_Loss_Limit = By.xpath("//table[@class='table table-bordered table-striped responsive dataTable moderator']//tr[11]//div[1]/div[1]");
	
	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil jsUtil;
	
	//1.create a constructor of page class and initialize objects with driver
			public PlayersPage(WebDriver driver) {
				this.driver = driver;
				elementActions = new ElementActions(driver);
				jsUtil = new JavaScriptUtil(driver);
			}
			
	//2 . Methods.
			public String playerSearch(String USER_ID,String LOGIN_STATUS) {
				
				String contractIdFound = " ";
				elementActions.clickOnElement(contractId);
				elementActions.clickOnElement(search);
				elementActions.sendKeysElement(search, Constants.DROP_DOWN_SEARCH_ITEM);
				elementActions.clickOnElement(searchResults);
				elementActions.clear(enterValue);
				elementActions.sendKeysElement(enterValue, USER_ID);
				elementActions.clickOnElement(searchButton);
				int size = elementActions.getElements(playerFoundMessage);
//				System.out.println("*****************************");
//				System.out.println("Size =" + size);
//			    System.out.println("*****************************");
				if (size==1){
					contractIdFound = "Player not found";
					CommonUtil.MediumWait();				
				}else {					
					elementActions.clickOnElement(foundPlayerRow);
					foundContractId  = By.xpath(Bxpath + USER_ID + Axpath);
					contractIdFound =  elementActions.getTextElement(foundContractId);
				}		
				return contractIdFound;				
			}
			
			public String verifyLastName() {
				return elementActions.getTextElement(lastName);
			}
			public String verifyFirstName() {
				return elementActions.getTextElement(firstName);
			}
			
			public String verifyEmail() {
				return elementActions.getTextElement(email);
			}
			
			public String verifyGender() {
				return elementActions.getTextElement(gender);
			}
			
			public String verifyUserName() {
				return elementActions.getTextElement(userName);
			}
			
			public String verifyCountry() {
				return elementActions.getTextElement(country);
			}
			
			public String verifyState() {
				return elementActions.getTextElement(State);
			}
			
			public String playerAccountLocked () {
				return elementActions.getTextElement(playerAccountLocked);
			}
			
			public String determinePlayerAccountLockStatus(String login_status) {
				String playeraccountlock;
				if (login_status.equals("1")) {
					 playeraccountlock =  "YES";					
				} else {
					playeraccountlock = "NO";
				}
               return playeraccountlock;
			}
			
			public void userDetailView(){
				  elementActions.clickOnElement(main_Menu);
				  elementActions.clickOnElement(sub_Menu);
				  CommonUtil.ShortWait();
			}
			
			public String getDailyLossLimit() {
				  return elementActions.getTextElement(daily_Loss_Limit);
			}
			
			public String getWeeklyLossLimit() {
				  return elementActions.getTextElement(weekly_Loss_Limit);
			}
			
			public String getMonthlyLossLimit() {
				  return elementActions.getTextElement(monthly_Loss_Limit);
			}
			
			public void closePlayer() {
				elementActions.clickOnElement(closePlayerIcon);
			}
		
}
