package crm.documents;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.DocumentsPage;
import object_repository.HomePage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * UploadDocumentTest — Automates Document upload in Vtiger CRM.
 *
 * Was a standalone main() — now a proper TestNG test extending BaseClass
 * Hardcoded file path "C:\\Users\\adars\\..." REMOVED File path now read from
 * testScriptData.xlsx (configurable per machine) if-else println replaced with
 * Assert.assertEquals() Thread.sleep() removed Uses DocumentsPage POM
 *
 * Data Source: testScriptData.xlsx → sheet "documents"
 *
 * @author Adarsh Singh
 */
public class UploadDocumentTest extends BaseClass {

    @Test
    public void uploadDocumentTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

        // ==============================
        // Step 1: Read test data from Excel
        // ==============================
        FileUtility fUtil = new FileUtility();
        String docTitle  = fUtil.getDataFromExcelFile("documents", 1, 0);
        String filePath  = fUtil.getDataFromExcelFile("documents", 1, 1);

        String expectedTitle = docTitle + "_" + JavaUtility.generateRandomNumber();

        // ==============================
        // Step 2: Navigate to Documents Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getDocumentLink().click();

        // ==============================
        // Step 3: Fill Document Form using POM
        // ==============================
        DocumentsPage dp = new DocumentsPage(driver);
        dp.getCreateDocumentBtn().click();

        dp.getTitleField().sendKeys(expectedTitle);

        // Select Default folder safely
        try {
            Select folderSelect = new Select(dp.getFolderDropdown());
            folderSelect.selectByVisibleText("Default");
            System.out.println("Folder selected: Default");
        } catch (Exception e) {
            System.out.println("Default folder already selected or not found");
        }

        dp.getFileUploadField().sendKeys(filePath);
        dp.getSaveBtn().click();

        System.out.println("Document saved");

        // ==============================
        // Step 4: Validate
        // ==============================
        String actualTitle = driver.findElement(
                org.openqa.selenium.By.id("dtlview_Title")).getText();

        System.out.println("[DOC] Expected : " + expectedTitle);
        System.out.println("[DOC] Actual   : " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle,
                "Document Title mismatch after upload!");

        System.out.println("[DOC] PASS — Document uploaded: " + actualTitle);
    }
}
