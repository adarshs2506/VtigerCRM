package crm.opportunity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.OpportunityPage;

/**
 * OpportunityTest — Automates Create Opportunity flow in Vtiger CRM.
 *
 * @author Adarsh Singh
 */
public class OpportunityTest extends BaseClass {

    @Test
    public void createOpportunityTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException, InterruptedException {

        // ==============================
        // Step 1: Read Test Data
        // ==============================
        FileUtility fUtil = new FileUtility();

        String baseOppName = fUtil.getDataFromExcelFile("opportunity", 1, 0);
        String closingDate = fUtil.getDataFromExcelFile("opportunity", 1, 1);

        String expectedOppName = baseOppName + "_"
                + JavaUtility.generateRandomNumber();

        // ==============================
        // Step 2: Navigate to Opportunity Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getOppLink().click();

        OpportunityPage op = new OpportunityPage(driver);

        op.getCreateOppBtn().click();

        // ==============================
        // Step 3: Fill Opportunity Details
        // ==============================
        op.getOppNameField().sendKeys(expectedOppName);

        op.getClosingDateField().sendKeys(closingDate);

        Select stage = new Select(op.getSalesStageDropdown());
        stage.selectByIndex(1);

        // Related To = Organizations
        Select related = new Select(op.getRelatedToDropdown());
        related.selectByVisibleText("Organizations");

        // ==============================
        // Step 4: Select Existing Organization
        // ==============================
        String parentWindow = driver.getWindowHandle();

        op.getOrganizationLookupIcon().click();

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {

            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        System.out.println("Switched to Child Window");

        // Existing Organization Name
        driver.findElement(By.linkText("adarsh_0121_843")).click();

        System.out.println("Organization selected");

        // Switch back to parent window
        driver.switchTo().window(parentWindow);

        System.out.println("Switched to Parent Window");

        Thread.sleep(2000);

        // ==============================
        // Step 5: Save Opportunity
        // ==============================
        System.out.println("Before Save");

        op.getSaveBtn().click();

        System.out.println("After Save");

        // ==============================
        // Step 6: Validation
        // ==============================
        String actualOppName = driver
                .findElement(By.id("dtlview_Opportunity Name"))
                .getText();

        System.out.println("Expected : " + expectedOppName);
        System.out.println("Actual   : " + actualOppName);

        Assert.assertEquals(actualOppName, expectedOppName,
                "Opportunity Name mismatch!");

        System.out.println("Opportunity Created Successfully");
        System.out.println("Test Completed");
    }
}