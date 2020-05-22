package com.qa.control.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.control.base.BasePage;
import com.qa.control.commons.Constants;


public class ElementActions extends BasePage{
	
	WebDriver driver;
	Actions actions ;
	JavaScriptUtil jsUtil;
	
	public ElementActions(WebDriver driver) {
		this.driver = driver;	
		actions  = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By selector) {
		WebElement element = null;
		try {
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		 element = driver.findElement(selector);
		}
		catch(Exception e) {
			System.out.println("Element can't be created");
			e.printStackTrace();
			e.getMessage();
		}
		return element;	
		}
	
	public int getElements(By selector) {
		int size = 0;
		try {
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		jsUtil.flash(getElement(selector));
		 size = driver.findElements(selector).size();
		}
		catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
	    }
		return size;	
		}
	
	public void sendKeysElement(By selector, String value) {
		jsUtil.flash(getElement(selector));
		getElement(selector).sendKeys(value);
	}
	
	public void clickOnElement(By selector) {
		jsUtil.flash(getElement(selector));
		getElement(selector).click();
	}
	
	public void clear(By selector) {
		jsUtil.flash(getElement(selector));
		getElement(selector).click();
		getElement(selector).clear();
	}
	
	public void mouseOver(By selector) {
		actions.moveToElement(getElement(selector));
		actions.click().build().perform();
	}
	
	public String getTextElement(By selector) {
		jsUtil.flash(getElement(selector));
		 return getElement(selector).getText();
	}
	
	public boolean isDisplayed(By selector) {
		jsUtil.flash(getElement(selector));
		 return getElement(selector).isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}

}
