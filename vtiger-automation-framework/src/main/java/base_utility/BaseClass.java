package base_utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.HomePage;
import object_repository.LoginPage;

/**
 * BaseClass — Parent class for all CRM test classes.
 *
 * Responsibilities:
 *   - @BeforeClass  : Launch browser (browser name from commondata.json)
 *   - @BeforeMethod : Login to Vtiger CRM (url/un/pwd from commondata.json)
 *   - @AfterMethod  : Logout from CRM using HomePage POM
 *   - @AfterClass   : Quit browser
 * @author Adarsh Singh
 */
public class BaseClass {

    public WebDriver driver;

    // ==============================
    // BROWSER SETUP
    // ==============================
    @BeforeClass
    public void setUp() throws FileNotFoundException, IOException, ParseException {

        FileUtility fUtil = new FileUtility();
        String browser = fUtil.getDataFromJsonFile("bro");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("[WARN] Unknown browser '" + browser + "' — defaulting to Chrome");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Browser launched: " + browser);
    }

    // ==============================
    // LOGIN BEFORE EVERY TEST METHOD
    // ==============================
    @BeforeMethod
    public void login() throws FileNotFoundException, IOException, ParseException {

        FileUtility fUtil = new FileUtility();
        String url      = fUtil.getDataFromJsonFile("url");
        String username = fUtil.getDataFromJsonFile("un");
        String password = fUtil.getDataFromJsonFile("pwd");

        driver.get(url);

        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);

        System.out.println("Login successful");
    }

    // ==============================
    // LOGOUT AFTER EVERY TEST METHOD
    // ==============================
    @AfterMethod
    public void logout() {

        HomePage hp = new HomePage(driver);

        WebDriverUtility wdUtil = new WebDriverUtility(driver);
        wdUtil.hover(hp.getProfileIcon());

        hp.getSignOutLink().click();
        System.out.println("Logout successful");
    }

    // ==============================
    // CLOSE BROWSER
    // ==============================
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}
