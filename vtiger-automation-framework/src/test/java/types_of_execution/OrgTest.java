package types_of_execution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * OrgTest — Cross-browser test via @Parameters.
 *
 * BIG FIX: Previously the method received 'browser' parameter from @Parameters
 *          BUT internally was using a hardcoded local variable 'browser1 = "chrome"'
 *          This means Chrome was ALWAYS launched regardless of what testng.xml said.
 *          Now the parameter is used directly.
 *
 * @author Adarsh Singh
 */
public class OrgTest {

    WebDriver driver;

    @Parameters("bro")
    @Test
    public void createOrgTest(String browser) throws InterruptedException {

        // FIX: Use the 'browser' param directly — was using hardcoded 'browser1'
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("[WARN] Unknown browser: " + browser + " — defaulting to Chrome");
            driver = new ChromeDriver();
        }

        Thread.sleep(2000);
        System.out.println("[ORG] org created on browser: " + browser);
        driver.quit();
    }

    @Parameters("bro")
    @Test
    public void modifyOrgTest(String browser) throws InterruptedException {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        Thread.sleep(2000);
        System.out.println("[ORG] org modified on browser: " + browser);
        driver.quit();
    }

    @Parameters("bro")
    @Test
    public void deleteOrgTest(String browser) throws InterruptedException {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        Thread.sleep(2000);
        System.out.println("[ORG] org deleted on browser: " + browser);
        driver.quit();
    }
}
