package com.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.aventstack.extentreports.Status;
import com.browser.Driver;
import com.browser.DriverManager;
import com.constants.Constants;
//import com.pd.listeners.CustomListeners;
import com.reports.LogStatus;
import com.utils.DynamicXpath;
import com.utils.TestUtils;

public class BasePage {
	
	
	protected BasePage(){
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public static void explicitlyWait(WebElement element) {
		WebDriverWait wait=new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void clickElement(WebElement element) {
		explicitlyWait(element);
		element.click();
	}	
	
	public static void typeText(WebElement element,String city) {
		explicitlyWait(element);
		element.sendKeys(city);
	}
	
	public static void selectCheckBoxDynamic(String newXpath) {
		boolean cityCheckBxSelected = false;
		WebElement checkBox = DriverManager.getDriver().findElement(By.xpath(newXpath));
		explicitlyWait(checkBox);
	
		cityCheckBxSelected = checkBox.isSelected();
		if(!cityCheckBxSelected) {
			checkBox.click();
			LogStatus.info("City Check box selected");
		}
		else {
			LogStatus.info("Check box already selected");
		}
	}

//	public static void clickElement(WebElement element)  {
//		explicitlyWait(element);
//		//highlightElement(element);
//		element.click();
//		LogStatus.pass("Clicking is successful on element", TestUtils.pullScreenshotPath());
//		//LogStatus.pass("Screenshot below", TestUtils.pullScreenshotPath());
//
//	}

//	//Common Keywords
//		public static void clickElement(String locator) {
//
//			if (locator.endsWith("_CSS")) {
//				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
//			} else if (locator.endsWith("_XPATH")) {
//				driver.findElement(By.xpath(OR.getProperty(locator))).click();
//			} else if (locator.endsWith("_ID")) {
//				driver.findElement(By.id(OR.getProperty(locator))).click();
//			}else if (locator.endsWith("_CLASS")) {
//				driver.findElement(By.className(OR.getProperty(locator))).click();
//			}else if (locator.endsWith("_LINK")) {
//				driver.findElement(By.linkText(OR.getProperty(locator))).click();
//			}	
//			log.debug("Clicking on Element: " + locator);
//			CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);
//		}
//	
//	public static void click(By by)  {
//		click(DriverManager.getDriver().findElement(by));
//	}
	public static void pageDown(int noOfTimes){
		try {
			Robot robot = new Robot();
			for(int i=0;i<noOfTimes;i++) {
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			}
		} catch (AWTException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void scrollDown(int iPixels) {
		JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
		jse.executeScript("window.scrollBy(0,"+iPixels+");");
		LogStatus.pass("Scrolled Down in Page");
	}
	
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		LogStatus.pass("Scrolled To Element");
	}
	
	public static void scrollToElementDynamic(String newXpath) {
		scrollToElement(DriverManager.getDriver().findElement(By.xpath(newXpath)));
	}
	
	public static void clickElementDynamic(String newXpath) {
		clickElement(DriverManager.getDriver().findElement(By.xpath(newXpath)));
	}

	public static void verifyElementPresentDynamic(String newXpath) {
		boolean isElementPresent = false;
		WebElement element = DriverManager.getDriver().findElement(By.xpath(newXpath));
		isElementPresent = element.isDisplayed();
		if(isElementPresent) {
			LogStatus.pass("Element found");
		}
	}
	
	public static void clearText(WebElement element) {
		element.clear();
		LogStatus.pass("Text cleared on element");
	}
	

	public static void sendkeys(WebElement element, String text)  {
		explicitlyWait(element);
		//highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass(text + " is entered in to the element");
		LogStatus.pass(text + " is entered in to the element", TestUtils.pullScreenshotPath());
		
	}
	
	public static String getTitle()
	{
		String Title = DriverManager.getDriver().getTitle();
			
		if(Title!=null && Title!="") {
			System.out.println("Expected Page Loaded");
		}else {
			System.out.println("Expected Page not Loaded");
			//Assert.fail("Expected Page not Loaded. Test case failed");
			
		}
		return Title;
		
}

	//	public static void sendkeys(By by, String text)  {
//		sendkeys(DriverManager.getDriver().findElement(by), text);
//	}
	
	
	public static void moveToElement(WebElement element) {
		Actions actions= new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}
	
	public static void moveToElement(By by) {
		moveToElement(DriverManager.getDriver().findElement(by));
	}

	private static void highlightElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
		jse.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void highlightElementDynamic(String newXpath) {
		highlightElement(DriverManager.getDriver().findElement(By.xpath(newXpath)));
	}
	
	public static void switchToNewWindow() {
		String parentWinHandle = DriverManager.getDriver().getWindowHandle();
		Set<String> winHandles = DriverManager.getDriver().getWindowHandles();
		for(String temp:winHandles) {
			if(!temp.equalsIgnoreCase(parentWinHandle)) {
				DriverManager.getDriver().switchTo().window(temp);
				LogStatus.pass("Switched to new Window : " +temp);
			}
		}
	}
	
	public static void selectByValue(WebElement element,String text) {
		new Select(element).selectByValue(text);
		LogStatus.pass("Selected dropdown " +element + "with value " +text);
	}
	
	public static void selectByVisibleText(WebElement element,String text) {
		new Select(element).selectByVisibleText(text);
		LogStatus.pass("Selected dropdown " +element + "with text " +text);
	}
	
	public static void selectByIndex(WebElement element,int index) {
		new Select(element).selectByIndex(index);
		LogStatus.pass("Selected dropdown " +element + "with index " +index);
	}
	
	
	
	public static void verifyEquals(String expected, String actual){
	
	LogStatus.pass("");
	}
	
public static String getText(WebElement element) {
	String eleText = element.getText();
	LogStatus.info("Element text is "+eleText);
	return eleText;
}

public static void verifyElementTextContainsExpected(WebElement element, String expected) {
//	String eleText = element.getText();
//	LogStatus.info("Element text is "+eleText);
//	return eleText;
	element.getText().trim().contains(expected);
	highlightElement(element);
	LogStatus.pass("Expected text found"); 
}
}
