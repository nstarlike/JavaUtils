package com.nstarlike.JavaUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IDUtilTest {
	@Test
	public void testGetRandomId() {
		String id = IDUtil.getRandomID();
		
		assertNotNull(id);
	}
	
	@Test
	public void testCheckId() {
		String id = "abc1234";
		String regex = "/^[a-z0-9]{6,12}$/";
		boolean ret = IDUtil.checkID(id, regex);
		
		assertTrue(ret);
	}
}
