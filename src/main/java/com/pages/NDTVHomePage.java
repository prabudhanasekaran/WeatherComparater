package com.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.browser.DriverManager;
import com.reports.LogStatus;
import com.utils.TestUtils;

public class NDTVHomePage extends BasePage{

	WebDriver driver;
	
	public NDTVHomePage() {
		this.driver = DriverManager.getDriver();
	}
		
	@FindBy(id="h_sub_menu")
	WebElement hmpg_SubMenu_Lnk;
	
	@FindBy(linkText="WEATHER")
	WebElement hmpg_Weather_Lnk;
	
	public void navigateToWeatherMenu() {
		//clicking SubMenu ...
		LogStatus.info("Clicking SubMenu ...");
		clickElement(hmpg_SubMenu_Lnk);
	
		//Click on WEATHER Link
		LogStatus.info("Clicking WEATHER Link");
		clickElement(hmpg_Weather_Lnk);
	}
	
}
