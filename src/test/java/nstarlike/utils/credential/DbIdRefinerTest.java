package nstarlike.utils.credential;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class DbIdRefinerTest {
	private IdChecker checker;
	private IdRefiner refiner;
	
	@BeforeEach
	void initAll() {
		this.checker = new DbIdChecker();
		this.refiner = new DbIdRefiner(checker);
	}
	
	@Test
	void testRefine() {
		List<String> ids = new ArrayList<String>();
		ids.add("test2024");
		ids.add("test1223");
		
		List<String> refined = this.refiner.refine(ids);
		
		assertNotNull(refined);
		assertTrue(refined.size() <= ids.size());
		for(int i=0; i<refined.size(); i++) {
			assertTrue(ids.contains(refined.get(i)));
		}
	}

}
