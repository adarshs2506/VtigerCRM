package testng_extra.learning_dataprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 1. num of webpages = num of POM pages
 * 2. num of webelements = num of webelement refs
 * 3. declare them as private
 * 4. access them through public services (Getters)
 * 5. initialize them through PageFactory.initElements() 
 * 		by passing driver and this
 * 		
 */
public class LoginPage {
//	@FindBy(locName = "locValue")
//	private WebElement element;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user-name")
	private WebElement un;
	
	public WebElement getUn() {
		return un;
	}

	@FindBy(id = "password")
	private WebElement pwd;
	
	public WebElement getPwd() {
		return pwd;
	}
	
	@FindBy(id = "login-button")
	private WebElement loginBtn;
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}
}