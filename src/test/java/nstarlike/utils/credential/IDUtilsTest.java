package nstarlike.utils.credential;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import nstarlike.utils.credential.IDUtils;

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
	
	@Test
	public void testGetRecommendedIDs() {
		IDRecommender recommender = new FixIDRecommender("testid");
		IDChecker checker = new DBIDChecker();
		IDRefiner refiner = new DBIDRefiner(checker);
		List<String> recommended = IDUtils.getRecommendedIDs(recommender, refiner);
		
		assertNotNull(recommended);
		System.out.println("recommended=" + recommended.toString());
	}
}
