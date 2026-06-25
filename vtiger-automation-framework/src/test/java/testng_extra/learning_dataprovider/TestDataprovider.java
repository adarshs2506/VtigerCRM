package testng_extra.learning_dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataprovider {

	@Test(dataProvider = "getData")
	public void dummyLogin(String un, String pwd) {
//		String un = "admin";
//		String pwd = "manager";

		System.out.println(un);
		System.out.println(pwd);
	}

	@DataProvider
	public Object[][] getData() {

		Object[][] obj = new Object[5][2];
//									num of row = num of execution
//									num of column = num of data/variable
		obj[0][0] = "sorry";
		obj[0][1] = "yaad_nahi_hai";

		obj[1][0] = "naagar143";
		obj[1][1] = "Naagar@98";

		obj[2][0] = "mk0410";
		obj[2][1] = "mk@143";

		obj[3][0] = "radhamahant";
		obj[3][1] = "Radha@Mahant@007";

		obj[4][0] = "abhisssheek";
		obj[4][1] = "abhishek04";

		return obj;
	}
}