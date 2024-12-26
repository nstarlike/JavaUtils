package nstarlike.utils.credential;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FixIdRecommenderTest {
	
	@Test
	void testConstructor() {
		// Test if ID is null
		assertThrows(RuntimeException.class, () -> {
			// Create an instance with number
			new FixIdRecommender(null);
		});
		
		// Test if ID is empty
		assertThrows(RuntimeException.class, () -> {
			new FixIdRecommender("");
		});
		
		// Test if ID is whitespace
		assertThrows(RuntimeException.class, () -> {
			new FixIdRecommender(" ");
		});
		
		// Test if ID starts with a number
		assertThrows(RuntimeException.class, () -> {
			new FixIdRecommender("1test");
		});
	}
	
	@Test
	void testAddIds() {
		// Create an instance
		IdRecommender idRec = new FixIdRecommender("testid");
		
		// Test the addIds method
		String id1 = "testid1";
		String id2 = "testid2";
		List<String> ids = new ArrayList<String>();
		ids.add(id1);
		ids.add(id2);
		idRec.addIds(ids);
		
		// Assert
		List<String> idsAll = idRec.getIds();
		assertNotNull(idsAll);
		assertTrue(idsAll.size() > 0);
		assertTrue(idsAll.contains(id1));
		assertTrue(idsAll.contains(id2));
	}

	@Test
	void testGetIds() {
		// Create an instance
		String id = "testid";
		IdRecommender idRec = new FixIdRecommender(id);
		
		// Test the getIds method
		List<String> ids = idRec.getIds();
		
		// Assert
		assertNotNull(ids);
		assertTrue(ids.size() > 0);
		assertTrue(ids.get(0).startsWith(id));
	}

}
