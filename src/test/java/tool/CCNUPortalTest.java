package tool;

import org.junit.Assert;
import org.junit.Test;

public class CCNUPortalTest {

	String xh = "2012210817";
	String mm = "930820";
	String xh2 = "2012210008";
	String mm2 = "2012210008";
	String errorPassword = "error";
	int TIME = 100;

	@Test
	public void testXHMMisTrue() throws Exception {
		for (int i = 0; i < TIME; i++) {
			boolean re = CCNUPortal.XHMMisTrue(xh, mm);
			Assert.assertTrue(re);
			re = CCNUPortal.XHMMisTrue(xh2, mm2);
			Assert.assertTrue(re);
		}
	}

	@Test
	public void testXHMMisTrue_error() throws Exception {
		for (int i = 0; i < TIME; i++) {
			boolean re = CCNUPortal.XHMMisTrue(xh, errorPassword);
			Assert.assertTrue(!re);
			re = CCNUPortal.XHMMisTrue(xh2, errorPassword);
			Assert.assertTrue(!re);
		}
	}

	@Test
	public void testXHMMisTrue_list() throws Exception {
		for (int i = 2012210001; i < 2012210100; i++) {
			String XH=Integer.toString(i);
			boolean re = CCNUPortal.XHMMisTrue(XH, errorPassword);
			Assert.assertTrue(!re);
		}
	}

	@Test
	public void testGetStudentInfo() throws Exception {

	}

	@Test
	public void testGetTeacherInfo() throws Exception {

	}
}