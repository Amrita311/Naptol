package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports 
{
	public static ExtentReports genrateReports()
	{
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter("ExtentsReport.html");
		ExtentReports report=new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("version", "1.10");
		report.setSystemInfo("Created by", "Amrita");
		report.setSystemInfo("Testing type", "Regression");
		return report;
	}

}
