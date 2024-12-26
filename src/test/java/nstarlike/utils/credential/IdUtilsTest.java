package nstarlike.utils.credential;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class IdUtilsTest {
	@Test
	public void testGetRandomId() {
		String id = IdUtils.getRandomId("test");
		
		assertNotNull(id);
		assertTrue(id.length() > 4);
	}
	
	@Test
	public void testCheckId() {
		String id = "abc1234";
		String regex = "^[a-z0-9]{6,20}$";
		boolean ret = IdUtils.checkId(id, regex);
		
		assertTrue(ret);
	}
	
	@Test
	public void testGetRecommendedIDs() {
		IdRecommender recommender = new FixIdRecommender("testid");
		IdChecker checker = new DbIdChecker();
		IdRefiner refiner = new DbIdRefiner(checker);
		List<String> recommended = IdUtils.getRecommendedIds(recommender, refiner);
		
		assertNotNull(recommended);
		for(String id : recommended) {
			assertTrue(recommender.getIds().contains(id));
		}
	}
}
