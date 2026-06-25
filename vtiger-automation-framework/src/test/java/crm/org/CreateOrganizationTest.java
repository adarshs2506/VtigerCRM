package crm.org;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.HomePage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * CreateOrganizationTest — Automates Create Organization flow in Vtiger CRM.

 * @author Adarsh Singh
 */
public class CreateOrganizationTest extends BaseClass {

    @Test
    public void createOrganizationTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

        // ==============================
        // Step 1: Read base org name from Excel + make it unique
        // ==============================
        FileUtility fUtil = new FileUtility();
        String baseOrgName    = fUtil.getDataFromExcelFile("org", 1, 0);
        String expectedOrgName = baseOrgName + "_" + JavaUtility.generateRandomNumber();

        // ==============================
        // Step 2: Navigate to Organizations Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();

        driver.findElement(org.openqa.selenium.By.cssSelector("img[title='Create Organization...']")).click();

        // ==============================
        // Step 3: Enter Organization Name
        // ==============================
        driver.findElement(org.openqa.selenium.By.name("accountname")).sendKeys(expectedOrgName);
        driver.findElement(org.openqa.selenium.By.cssSelector("input[title='Save [Alt+S]']")).click();

        System.out.println("[ORG] Organization form submitted");

        // ==============================
        // Step 4: Validate
        // ==============================
        String actualOrgName = driver.findElement(
                org.openqa.selenium.By.id("dtlview_Organization Name")).getText();

        System.out.println("[ORG] Expected : " + expectedOrgName);
        System.out.println("[ORG] Actual   : " + actualOrgName);

        Assert.assertEquals(actualOrgName, expectedOrgName,
                "Organization Name mismatch after creation!");

        System.out.println("[ORG] PASS — Organization created: " + actualOrgName);
    }
}
