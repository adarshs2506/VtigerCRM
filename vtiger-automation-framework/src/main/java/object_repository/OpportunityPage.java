package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * OpportunityPage POM — Elements for the Opportunities module.
 * 
 * @author Adarsh Singh
 */
public class OpportunityPage {

	public OpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Opportunity...']")
	private WebElement createOppBtn;

	@FindBy(name = "potentialname")
	private WebElement oppNameField;

	@FindBy(id = "jscal_field_closingdate")
	private WebElement closingDateField;

	@FindBy(name = "sales_stage")
	private WebElement salesStageDropdown;

	@FindBy(name = "related_to_type")
	private WebElement relatedToDropdown;

	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement organizationLookupIcon;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Getters

	public WebElement getCreateOppBtn() {
		return createOppBtn;
	}

	public WebElement getOppNameField() {
		return oppNameField;
	}

	public WebElement getClosingDateField() {
		return closingDateField;
	}

	public WebElement getSalesStageDropdown() {
		return salesStageDropdown;
	}

	public WebElement getRelatedToDropdown() {
		return relatedToDropdown;
	}

	public WebElement getOrganizationLookupIcon() {
		return organizationLookupIcon;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
}