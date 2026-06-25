package testng_extra;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Demo2Test {
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
	
	@Test(groups = {"reg", "smoke"})
	public void createCity() {
		System.out.println("noida");
	}
	
	@Test(groups = "reg")
	public void modifyCity() {
		System.out.println("Greater noida");
	}
	
	@Test(groups = "system")
	public void deleteCity() {
		System.out.println("Greater noida deleted");
	}
}