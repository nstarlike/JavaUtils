package nstarlike.javautils.credential;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordUtils {
	public static String getRandomPassword(int size, boolean hasUpper, boolean hasNum, boolean hasSpecial) {
		//variable declarations
		StringBuilder password = new StringBuilder();
		char[] lowers = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
							'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] uppers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
							'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] specials = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '?'};
		List<String> chTypes = new ArrayList<String>();
		chTypes.add("lower");
		if(hasUpper) {
			chTypes.add("upper");
		}
		if(hasNum) {
			chTypes.add("number");
		}
		if(hasSpecial) {
			chTypes.add("special");
		}
		Random r = new Random();
		
		while(size > 0) {
			//get the index of the character type to use
			int i = r.nextInt(chTypes.size());
			String chType = chTypes.get(i);
			
			switch(chType) {
			//lowercase English character
			case "lower":
				i = r.nextInt(lowers.length);
				password.append(lowers[i]);
				break;
				
			//uppercase English character
			case "upper":
				i = r.nextInt(uppers.length);
				password.append(uppers[i]);
				break;
				
			//number
			case "number":
				i = r.nextInt(10);
				password.append(i);
				break;
				
			//special character
			case "special":
				i = r.nextInt(specials.length);
				password.append(specials[i]);
				break;
				
			default:
				throw new RuntimeException("Unsupported character type.");
			}
			
			size--;
		}
		return password.toString();
	}
	
	public static String encryptPassword(String password, String algorithm) {
		String encrypted = "";
		return encrypted;
	}
	
	public static String decryptPassword(String password, String algorithm) {
		String decrypted = "";
		return decrypted;
	}
	
	public static boolean isSafePassword(String password) {
		boolean isSafe = false;
		return isSafe;
	}
}
