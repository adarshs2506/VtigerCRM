package listeners_utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_utility.BaseClass;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;

/**
 * List_Imp — TestNG Listener for ExtentReports integration.
 *
 * @author Adarsh Singh
 */
public class List_Imp implements ITestListener, ISuiteListener {

	public ExtentReports report;

	// Thread safe ExtentTest object
	private ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

	// ==============================
	// SUITE LEVEL — Report init
	// ==============================
	@Override
	public void onStart(ISuite suite) {

		System.out.println("[LISTENER] Suite Started: " + suite.getName());

		String time = JavaUtility.genCurrentTime();

		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/" + time + ".html");

		spark.config().setDocumentTitle("Vtiger CRM Automation Report");
		spark.config().setReportName("Vtiger CRM Test Execution");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);

		report.setSystemInfo("Project", "Vtiger CRM Automation");
		report.setSystemInfo("Author", "Adarsh Singh");
		report.setSystemInfo("Environment", "localhost:8888");
		report.setSystemInfo("Tool", "Selenium + TestNG + Java");
	}

	@Override
	public void onFinish(ISuite suite) {

		if (report != null) {
			report.flush();
		}

		System.out.println("[LISTENER] Suite Finished: " + suite.getName());
	}

	// ==============================
	// TEST LEVEL
	// ==============================

	@Override
	public void onTestStart(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		ExtentTest test = report.createTest(methodName);

		tlTest.set(test);

		System.out.println("Test Started: " + methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		if (tlTest.get() != null) {
			tlTest.get().log(Status.PASS, methodName + " PASSED");
		}

		System.out.println("Test PASSED: " + methodName);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		if (tlTest.get() != null) {
			tlTest.get().log(Status.FAIL, result.getThrowable());
		}

		System.out.println("Test FAILED: " + methodName);
		System.out.println("Reason: " + result.getThrowable());

		// Screenshot on failure
		try {

			Object obj = result.getInstance();

			if (obj instanceof BaseClass) {

				WebDriver driver = ((BaseClass) obj).driver;

				if (driver != null) {

					WebDriverUtility wdUtil = new WebDriverUtility(driver);

					wdUtil.takeScreenshotWithTimestamp("FAIL_" + methodName);

					if (tlTest.get() != null) {
						tlTest.get().log(Status.INFO, "Screenshot captured");
					}
				}
			}

		} catch (IOException e) {

			if (tlTest.get() != null) {
				tlTest.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
			}
		}

		System.out.println("Test FAILED: " + methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		if (tlTest.get() != null) {
			tlTest.get().log(Status.SKIP, methodName + " SKIPPED");
		}

		System.out.println("Test SKIPPED: " + methodName);
	}
}