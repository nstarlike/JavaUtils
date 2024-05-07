package nstarlike.utils.credential;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nstarlike.utils.credential.PasswordUtils;

class PasswordUtilsTest {

	@Test
	void testGetRandomPassword() {
		//all English characters with only lowercases
		int size = 10;
		String password = PasswordUtils.getRandomPassword(size, false, false, false);
		
		assertNotNull(password);
		assertTrue(password.length() == size);
		
		System.out.println("all lower English characters: " + password);
		
		//all English characters with lowercases and uppercases
		size = 10;
		password = PasswordUtils.getRandomPassword(size, true, false, false);
		
		assertNotNull(password);
		assertTrue(password.length() == size);
		
		System.out.println("all lower and upper English characters: " + password);
		
		//English characters with lowercases and uppercases and numbers
		size = 10;
		password = PasswordUtils.getRandomPassword(size, true, true, false);
		
		assertNotNull(password);
		assertTrue(password.length() == size);
		
		System.out.println("lower and upper English characters and numbers: " + password);
		
		//English characters with lowercases and uppercases, numbers and special characters
		size = 10;
		password = PasswordUtils.getRandomPassword(size, true, true, true);
		
		assertNotNull(password);
		assertTrue(password.length() == size);
		
		System.out.println("lower and upper English characters, numbers and special characters: " + password);
	}

	@Test
	void testEncryptPassword() {
		String password = "hashtrial";
		try {
			//use SHA-256
			String encrypted = PasswordUtils.encryptPassword(password, "SHA-256");
			
			assertNotNull(encrypted);
			assertTrue(encrypted.length() == 64);
			
			System.out.println(encrypted);
			
			//use SHA-512
			encrypted = PasswordUtils.encryptPassword(password, "SHA-512");
			
			assertNotNull(encrypted);
			assertTrue(encrypted.length() == 128);
			
			System.out.println(encrypted);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testIsSafePassword() {
		//test a password with consecutive characters
		String password = "ffsd2222saf&*(";
		boolean isSafe = PasswordUtils.isSafePassword(password);
		
		assertFalse(isSafe);
		
		//test a password with consecutive characters in keyboard
		password = "qwer124&*(";
		isSafe = PasswordUtils.isSafePassword(password);
		
		assertFalse(isSafe);
		
		//test a short password
		password = "327fjs^";
		isSafe = PasswordUtils.isSafePassword(password);
		
		assertFalse(isSafe);
	}

}
