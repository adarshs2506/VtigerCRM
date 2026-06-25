package testng_extra.learning_dataprovider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceDemouseddataprovider {

	@Test(dataProvider = "getData")
	public void loginSauceDemo(String username, String password) throws InterruptedException {
//		selenium 3 way of launching browser
		System.setProperty("webdriver.edge.driver", "./resources/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		try {

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//			login
			driver.get("https://www.saucedemo.com/");

			LoginPage lp = new LoginPage(driver);
			WebElement un = lp.getUn();
			WebElement pwd = lp.getPwd();
			WebElement loginBtn = lp.getLoginBtn();

			un.sendKeys(username);
			pwd.sendKeys(password);
			loginBtn.submit();

//			verification
			boolean header = driver.findElement(By.id("header_container")).isDisplayed();
			if (header) {
				System.out.println("logged in successfullyyy !!!");
			}

		} finally {
			Thread.sleep(500);
			driver.quit();
		}
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] obj = new Object[6][2];

		obj[0][0] = "standard_user";
		obj[0][1] = "secret_sauce";

		obj[1][0] = "locked_out_user";
		obj[1][1] = "secret_sauce";

		obj[2][0] = "problem_user";
		obj[2][1] = "secret_sauce";

		obj[3][0] = "performance_glitch_user";
		obj[3][1] = "secret_sauce";

		obj[4][0] = "error_user";
		obj[4][1] = "secret_sauce";

		obj[5][0] = "visual_user";
		obj[5][1] = "secret_sauce";

		return obj;
	}
}