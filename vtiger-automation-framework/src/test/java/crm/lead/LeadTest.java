package crm.lead;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.LeadsPage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * LeadTest — Automates Create Lead flow in Vtiger CRM.
 *
 * Module  : Leads
 * Extends : BaseClass (handles browser + login + logout)
 * Assert  : TestNG Assert.assertEquals()
 * Data    : testScriptData.xlsx → sheet "lead"
 *
 * @author Adarsh Singh
 */
public class LeadTest extends BaseClass {

    @Test
    public void createLeadTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

        // ==============================
        // Step 1: Read data from Excel
        // ==============================
        FileUtility fUtil = new FileUtility();
        String lastName = fUtil.getDataFromExcelFile("lead", 1, 0);
        String company  = fUtil.getDataFromExcelFile("lead", 1, 1);

        // Make names unique
        String expectedLastName = lastName + "_" + JavaUtility.generateRandomNumber();

        // ==============================
        // Step 2: Navigate to Leads Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getLeadLink().click();

        // ==============================
        // Step 3: Create Lead using POM
        // ==============================
        LeadsPage lp = new LeadsPage(driver);
        lp.createLead(expectedLastName, company);

        // ==============================
        // Step 4: Validate
        // ==============================
        String actualLastName = driver.findElement(
                org.openqa.selenium.By.id("dtlview_Last Name")).getText();

        System.out.println("[LEAD] Expected : " + expectedLastName);
        System.out.println("[LEAD] Actual   : " + actualLastName);

        Assert.assertEquals(actualLastName, expectedLastName,
                "Lead Last Name mismatch after creation!");

        System.out.println("[LEAD] PASS — Lead created: " + actualLastName);
    }
}
