package pom_extra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import generic_utility.WebDriverUtility;

public class SauceDemoTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new EdgeDriver();

		driver.get("https://www.saucedemo.com/");

//		login
//		WebElement usernameField = driver.findElement(By.id("user-name"));
//		WebElement passwordField = driver.findElement(By.id("password"));
//		WebElement loginBtn = driver.findElement(By.id("login-button"));

		LoginPage lp = new LoginPage(driver);
		WebElement un = lp.getUn();
		WebElement pwd = lp.getPwd();
		WebElement loginBtn = lp.getLoginBtn();
		
		Thread.sleep(1000);
		driver.navigate().refresh();


		Thread.sleep(1000);
		
		un.sendKeys("standard_user");
		

		Thread.sleep(1000);
		
		driver.navigate().refresh();


		Thread.sleep(1000);
		
		un.sendKeys("standard_user");
		pwd.sendKeys("secret_sauce");
		loginBtn.submit();

//		verification
		boolean header = driver.findElement(By.id("header_container")).isDisplayed();
		if (header) {
			System.out.println("logged in successfullyyy !!!");
		}

//		logout
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebDriverUtility wdUtil = new WebDriverUtility(driver);

		WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
		wdUtil.waitAndClick(logout, 10);

		Thread.sleep(3000);
		driver.quit();
	}
}