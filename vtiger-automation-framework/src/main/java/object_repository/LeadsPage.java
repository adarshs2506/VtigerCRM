package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LeadsPage POM — Elements for the Leads module.
 * @author Adarsh Singh
 */
public class LeadsPage {

    public LeadsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Create Lead...']")
    private WebElement createLeadBtn;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "company")
    private WebElement companyField;

    @FindBy(css = "input[title='Save [Alt+S]']")
    private WebElement saveBtn;

    @FindBy(className = "dvHeaderText")
    private WebElement headerText;

    public void createLead(String lastName, String company) {
        createLeadBtn.click();
        lastNameField.sendKeys(lastName);
        companyField.sendKeys(company);
        saveBtn.click();
    }

    public String getLeadHeader() {
        return headerText.getText();
    }
}
