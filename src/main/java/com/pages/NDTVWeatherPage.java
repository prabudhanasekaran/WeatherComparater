package com.pages;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.browser.DriverManager;
import com.reports.LogStatus;
import com.utils.DynamicXpath;
import com.utils.TestUtils;

public class NDTVWeatherPage extends BasePage{

	WebDriver driver;
	
	public NDTVWeatherPage() {
		this.driver = DriverManager.getDriver();
	}
		
	@FindBy(id="searchBox")
	WebElement wpg_WeatherSrch_TxtBx;
	
//	@FindBy(xpath="//input[@id='Bengaluru']")
//	WebElement wpg_City_ChkBx;
	
//	@FindBy(xpath="//div[text()='Bengaluru']")
//	WebElement wpg_CityInMap_Txt;
	
	@FindBy(xpath="//a[text()='+']")
	WebElement wpg_ZoomIn_Btn;
	
	@FindBy(xpath="//a[text()='−']")
	WebElement wpg_ZoomOut_Btn;
	
	@FindBy(xpath="//b[contains(text(),'Temp in Degrees:')]")
	WebElement wpg_CityTempDegree_Txt;
	
	
	String wpg_City_ChkBx_Xpath = "//input[@id='%replaceable%']";
	String wpg_CityInMap_Txt_Xpath = "//div[text()='%replaceable%']";
	String wpg_CityInTooltip_Txt_Xpath = "//span[text()='%replaceable%']";
	
	public void searchAndSelectCity(String city){
		//Enter city in Search box
		LogStatus.info("Typing Text: "+city);
		typeText(wpg_WeatherSrch_TxtBx,city);
		
		//Validate city Check box is checked
		LogStatus.info("Selecting CheckBox: "+city);
		String newXpath = DynamicXpath.get(wpg_City_ChkBx_Xpath, city);
		selectCheckBoxDynamic(newXpath);
//		System.out.println("Sleep Started");
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void clickAndValidateCityDetails(String city,String cityFullName){
		String newXpath = DynamicXpath.get(wpg_CityInMap_Txt_Xpath, city);
		
		//Click on City in the Map
		
		//scrollToElementDynamic(newXpath);
		wpg_ZoomIn_Btn.click();    //Zooming In
		pageDown(5);
		
	//scrollDown(150);

		LogStatus.info("Clicking City in Map");
		highlightElementDynamic(newXpath);
		clickElementDynamic(newXpath);
		
		//Validate City in Tooltip
		LogStatus.info("Validating City Details in City Tool Tip");
		String newXpath1 = DynamicXpath.get(wpg_CityInTooltip_Txt_Xpath, cityFullName);
		verifyElementPresentDynamic(newXpath1);
	}
	
	public String getAndStoreCityTempDegree() {
		LogStatus.info("Storing City temperature");
		
		String cityTempTxt = wpg_CityTempDegree_Txt.getText();
		//String cityTempInDegrees = "Temp in Degrees: 24";
		String arr[] = cityTempTxt.split(":");
		
		for(int i=0;i<arr.length;i++) {
			arr[i] = arr[i].trim();
		}
		//Storing the Temp of City(in Degree)
		String cityTempInDegrees = arr[1];
		return cityTempInDegrees;
	}
	
}
