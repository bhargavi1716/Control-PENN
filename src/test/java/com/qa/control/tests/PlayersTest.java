package com.qa.control.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.control.base.BasePage;
import com.qa.control.pages.HomePage;
import com.qa.control.pages.LoginPage;
import com.qa.control.pages.PlayersPage;
import com.qa.control.util.CommonUtil;
import com.qa.control.util.ExcelUtil;

public class PlayersTest {
	
	WebDriver driver;
	Properties prop;
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	PlayersPage playersPage;
	
	@BeforeTest
	public void setup() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.MediumWait();
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.MediumWait();
		playersPage = homePage.gotoPlayersPage();
		CommonUtil.MediumWait();
	}
	
	@DataProvider(name = "PlayersDM")
	public Object[][] getPlayersTestData() {
		Object playersData[][] = ExcelUtil.getTestData("PlayersInfo");
		return playersData;
	}
	
	/**
	 * @param USER_ID
	 * @param LAST_NAME
	 * @param FIRST_NAME
	 * @param EMAIL_ADDR
	 * @param GENDER
	 * @param USER_NAME
	 * @param COUNTRY
	 * @param STATE
	 * @param BANK_ACCOUNT_STATUS
	 * @param LOGIN_STATUS
	 * @param WAGER_STATUS
	 * @param WALLET_STATUS
	 * @param DW_DAILY_LIMIT
	 * @param DW_WEEKLY_LIMIT
	 * @param DW_MONTHLY_LIMIT
	 */
	
	
	@Test(dataProvider = "PlayersDM")
	public void searchPlayerTest(String USER_ID,String LAST_NAME,String FIRST_NAME,String EMAIL_ADDR,
			String GENDER,String USER_NAME,String COUNTRY,String STATE,String BANK_ACCOUNT_STATUS,
			String LOGIN_STATUS,String WAGER_STATUS,String WALLET_STATUS, String DW_DAILY_LIMIT,String DW_WEEKLY_LIMIT,String DW_MONTHLY_LIMIT) {

		SoftAssert sa = new SoftAssert();
		String foundContractIdTest =playersPage.playerSearch(USER_ID, LOGIN_STATUS);
		Assert.assertEquals(foundContractIdTest,USER_ID,"No records found");
		
		String lname = playersPage.verifyLastName();
		sa.assertEquals(lname, LAST_NAME,"Last name is not matched");
		
		String fname = playersPage.verifyFirstName();
		sa.assertEquals(fname, FIRST_NAME,"First name is not matched");
		
		String email = playersPage.verifyEmail();
		sa.assertEquals(email, EMAIL_ADDR,"Email Address is not matched");
		
		String gender = playersPage.verifyGender();
		sa.assertEquals(gender, GENDER,"Gender is not matched");
		
		String username = playersPage.verifyUserName();
		sa.assertEquals(username, USER_NAME,"User name is not matched");
		
		String country = playersPage.verifyCountry();
		sa.assertEquals(country, COUNTRY,"Country is not matched");
		
		String state = playersPage.verifyState();
		sa.assertEquals(state, STATE,"State is not matched");
		
		String playerlock = playersPage.playerAccountLocked();
		String playeraccountlock = playersPage.determinePlayerAccountLockStatus(LOGIN_STATUS);
		sa.assertEquals(playerlock, playeraccountlock,"Login Status is not matched");
		
		
		//Responsible Gaming fields verification.
		playersPage.userDetailView();
		
		String dll = playersPage.getDailyLossLimit();
		sa.assertEquals(dll, DW_DAILY_LIMIT,"Daily loss limit is not matched");
		
		String wll = playersPage.getDailyLossLimit();
		sa.assertEquals(wll, DW_WEEKLY_LIMIT,"Weekly loss limit is not matched");
		
		String mll = playersPage.getDailyLossLimit();
		sa.assertEquals(mll, DW_MONTHLY_LIMIT,"Monthly loss limit is not matched");

		playersPage.closePlayer();
		
		sa.assertAll();
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
