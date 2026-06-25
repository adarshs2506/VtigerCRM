package crm.products;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.ProductsPage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * CreateProductTest — Automates Create Product flow in Vtiger CRM.
 * Data Source: testScriptData.xlsx → sheet "products"
 *
 * @author Adarsh Singh
 */
public class CreateProductTest extends BaseClass {

    @Test
    public void createProductTest()
            throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

        // ==============================
        // Step 1: Read test data from Excel
        // ==============================
        FileUtility fUtil = new FileUtility();
        String baseProductName = fUtil.getDataFromExcelFile("products", 1, 0);
        String productCode     = fUtil.getDataFromExcelFile("products", 1, 1);
        String unitPrice       = fUtil.getDataFromExcelFile("products", 1, 2);
        String qty             = fUtil.getDataFromExcelFile("products", 1, 3);

        String expectedProductName = baseProductName + "_" + JavaUtility.generateRandomNumber();

        // ==============================
        // Step 2: Navigate to Products Module
        // ==============================
        HomePage hp = new HomePage(driver);
        hp.getProductLink().click();

        // ==============================
        // Step 3: Fill Product Form using POM
        // ==============================
        ProductsPage pp = new ProductsPage(driver);
        pp.getCreateProductBtn().click();

        pp.getProductNameField().sendKeys(expectedProductName);
        pp.getProductCodeField().sendKeys(productCode);

        pp.getUnitPriceField().clear();
        pp.getUnitPriceField().sendKeys(unitPrice);

        pp.getQtyInStockField().sendKeys(qty);
        pp.getUsageUnitField().sendKeys("Box");

        new Select(pp.getCategoryDropdown()).selectByIndex(1);
        new Select(pp.getManufacturerDropdown()).selectByIndex(1);

        pp.getSaveBtn().click();
        System.out.println("[PRODUCT] Product form submitted");

        // ==============================
        // Step 4: Validate
        // ==============================
        String actualProductName = driver.findElement(
                org.openqa.selenium.By.id("dtlview_Product Name")).getText();

        System.out.println("[PRODUCT] Expected : " + expectedProductName);
        System.out.println("[PRODUCT] Actual   : " + actualProductName);

        Assert.assertEquals(actualProductName, expectedProductName,
                "Product Name mismatch after creation!");

        System.out.println("[PRODUCT] PASS — Product created: " + actualProductName);
    }
}
