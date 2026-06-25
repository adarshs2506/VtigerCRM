package testng_extra.advance_reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Facebook {
	
	public ExtentReports report ;
	
	@BeforeSuite
	public void repConfig() {
//		report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_report/report1.html");
		spark.config().setDocumentTitle("fb_dummy");
		spark.config().setReportName("dummy_01");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("key1", "value1");
		report.setSystemInfo("key2", "value2");
		report.setSystemInfo("key3", "value3");
	}
	
	@Test
	public void login() throws InterruptedException {
//		start the testMethod with createTest()
		ExtentTest test = report.createTest("login");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.quit();

//		check at the end whether it is passed/failed/skipped
		test.log(Status.PASS, "Passed...");
	}
	
	@AfterSuite
	public void repBackup() {
		report.flush();
	}
}