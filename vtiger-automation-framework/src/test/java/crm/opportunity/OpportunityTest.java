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
 * Module : Opportunities Extends : BaseClass (handles browser + login + logout)
 *
 * NOTE: Previously CreateOpportunityTest.java was EMPTY (2 bytes). This is a
 * complete implementation.
 *
 * Test Data Source: testScriptData.xlsx → sheet "opportunity"
 *
 * @author Adarsh Singh
 */

public class OpportunityTest extends BaseClass {

	@Test
	public void createOpportunityTest()
			throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

		FileUtility fUtil = new FileUtility();

		String baseOppName = fUtil.getDataFromExcelFile("opportunity", 1, 0);

		String closingDate = fUtil.getDataFromExcelFile("opportunity", 1, 1);

		String expectedOppName = baseOppName + "_" + JavaUtility.generateRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOppLink().click();

		OpportunityPage op = new OpportunityPage(driver);

		op.getCreateOppBtn().click();

		op.getOppNameField().sendKeys(expectedOppName);

		op.getClosingDateField().sendKeys(closingDate);

		Select stage = new Select(op.getSalesStageDropdown());

		stage.selectByIndex(1);

		// Related To = Organizations
		Select related = new Select(op.getRelatedToDropdown());

		related.selectByVisibleText("Organizations");

		// Open lookup window
		op.getOrganizationLookupIcon().click();

		String parent = driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();

		for (String win : allWindows) {
			driver.switchTo().window(win);
		}

		// Existing Organization name
		driver.findElement(By.linkText("adarsh_0121_843")).click();

		driver.switchTo().window(parent);

		op.getSaveBtn().click();

		String actualOppName = driver.findElement(By.id("dtlview_Opportunity Name")).getText();

		Assert.assertEquals(actualOppName, expectedOppName);

		System.out.println("Opportunity Created Successfully");
	}
}