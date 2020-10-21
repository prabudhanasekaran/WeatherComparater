package com.constants;

public class Constants {
	//Project & Resource path
	public static final String PROJECTPATH=System.getProperty("user.dir");
	public static final String RESOURCESPATH = System.getProperty("user.dir")+"\\src\\test\\resources";
	
	//Drivers path
	public static final String CHROMEDRIVERPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe";
	public static final String GECKODRIVERPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\geckodriver.exe";
	
	//Extent config xml path
	public static final String EXTENTCONFIGPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\extentreport.xml";
	
	public static final int EXPLICITWAIT=10;
	
	//Test Data Sheet
	public static final String TESTDATASHEETNAME= "TestData";
		
	
}
