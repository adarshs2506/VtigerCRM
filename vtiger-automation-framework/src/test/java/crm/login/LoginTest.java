package crm.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import object_repository.HomePage;

/**
 * LoginTest — Validates login to Vtiger CRM.
 
 * @author Adarsh Singh
 */
public class LoginTest extends BaseClass {

    @Test
    public void loginTest() {

        HomePage hp = new HomePage(driver);

        boolean orgVisible = hp.getOrgLink().isDisplayed();
        boolean conVisible = hp.getConLink().isDisplayed();
        boolean oppVisible = hp.getOppLink().isDisplayed();

        System.out.println("[LOGIN] Organizations visible : " + orgVisible);
        System.out.println("[LOGIN] Contacts visible      : " + conVisible);
        System.out.println("[LOGIN] Opportunities visible : " + oppVisible);

        Assert.assertTrue(orgVisible && conVisible && oppVisible,
                "Login failed — Home page navigation links not visible!");

        System.out.println("[LOGIN] PASS — Login successful and Home Page verified");
    }
}
