package types_of_execution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LeadTest {

	WebDriver driver;

	@Parameters("bro")
	@Test
	public void createLeadTest(String browser) throws InterruptedException {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		Thread.sleep(2000);
		System.out.println("Lead created");
		driver.quit();
	}

	@Test
	public void modifyLeadTest() throws InterruptedException {
		driver = new ChromeDriver();
		Thread.sleep(2000);
		System.out.println("Lead modified");
		driver.quit();
	}
//
//	@Test
//	public void deleteLeadTest() throws InterruptedException {
//		driver = new ChromeDriver();
//		Thread.sleep(2000);
//		System.out.println("Lead deleted");
//		driver.quit();
//	}
}