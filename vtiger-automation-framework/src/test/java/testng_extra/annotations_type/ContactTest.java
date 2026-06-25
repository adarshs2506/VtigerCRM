package testng_extra.annotations_type;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactTest {

	@BeforeClass
	public void setUp() {
		System.out.println("open browser");
	}

	@BeforeMethod
	public void login() {
		System.out.println("login");
	}

	@Test
	public void createContactTest() {
		System.out.println("create Contact");
		System.out.println("verify Contact");
	}

	@AfterMethod
	public void logout() {
		System.out.println("logout");
	}

	@AfterClass
	public void tearDown() {
		System.out.println("close browser");
	}
}
