package testng_extra;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssert {
	@Test
	public void hardAssert() {

		String a = "abc";
		String b = "xyz";
		String c = "abc";

		Object obj1 = null;
		Object obj2 = new Object();

		Assert.assertEquals(a, c);
		Assert.assertNotEquals(a, b);

		Assert.assertNull(obj1);
		Assert.assertNotNull(obj2);

		System.out.println("last line of the code");
	}

	@Test
	public void softAssert() {
		String a = "abc";
		String b = "xyz";
		String c = "abc";

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(a, b);
		sa.assertNotEquals(a, c);

		sa.assertAll();
		System.out.println("last line of the code");
	}
}