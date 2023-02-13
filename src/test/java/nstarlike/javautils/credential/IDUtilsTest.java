package nstarlike.javautils.credential;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IDUtilsTest {
	@Test
	public void testGetRandomId() {
		String id = IDUtils.getRandomID("test");
		
		assertNotNull(id);
		assertTrue(id.length() > 4);
	}
	
	@Test
	public void testCheckId() {
		String id = "abc1234";
		String regex = "^[a-z0-9]{6,20}$";
		boolean ret = IDUtils.checkID(id, regex);
		
		assertTrue(ret);
	}
}
