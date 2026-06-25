package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactBtn;

	@FindBy(name = "lastname")
	private WebElement lastNameTextField;

	@FindBy(name = "button")
	private WebElement saveBtn;

	@FindBy(className = "dvHeaderText")
	private WebElement headerText;

	public void createContact(String lastName) {
		createContactBtn.click();
		lastNameTextField.sendKeys(lastName);
		saveBtn.click();
	}

	public String getContactHeader() {
		return headerText.getText();
	}
}