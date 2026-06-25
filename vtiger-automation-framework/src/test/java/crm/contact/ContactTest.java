package crm.contact;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import object_repository.ContactsPage;
import object_repository.HomePage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ContactTest — Automates Create Contact flow in Vtiger CRM.
 * Test Data Source: testScriptData.xlsx → sheet "contacts", row 1, col 0
 *
 * @author Adarsh Singh
 */
public class ContactTest extends BaseClass {

    @Test
    public void createContactTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

        // ==============================
        // Step 1: Read test data from Excel
        // ==============================
        FileUtility fUtil = new FileUtility();
        String expectedLastName = fUtil.getDataFromExcelFile("contacts", 1, 0);

        // ==============================
        // Step 2: Navigate to Contacts Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getConLink().click();

        // ==============================
        // Step 3: Create Contact using POM
        // ==============================
        ContactsPage cp = new ContactsPage(driver);
        cp.createContact(expectedLastName);

        // ==============================
        // Step 4: Validate Contact Creation
        // ==============================
        String actualLastName = driver.findElement(
                org.openqa.selenium.By.id("dtlview_Last Name")).getText();

        System.out.println("[CONTACT] Expected : " + expectedLastName);
        System.out.println("[CONTACT] Actual   : " + actualLastName);

        // FIX 1: TestNG Assert (not JUnit)
        Assert.assertEquals(actualLastName, expectedLastName,
                "Contact Last Name mismatch after creation!");

        System.out.println("[CONTACT] PASS — Contact created: " + actualLastName);
    }
}
