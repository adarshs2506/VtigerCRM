package testng_extra;

import org.junit.Assert;
import org.testng.annotations.Test;

public class DemoTest {
//	@Test(priority = -1)
//	public void createCity() {
//		System.out.println("noida");
//	}
//	
//	@Test
//	public void modifyCity() {
//		System.out.println("Greater noida");
//		Assert.assertTrue(false);
//	}
//	
//	@Test(priority = 1)
//	public void deleteCity() {
//		System.out.println("Greater noida deleted");
//	}
	
	@Test
	public void createCity() {
		System.out.println("noida");
	}
	
	@Test(dependsOnMethods = "createCity")
	public void modifyCity() {
		System.out.println("Greater noida");
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "modifyCity")
	public void deleteCity() {
		System.out.println("Greater noida deleted");
	}
}