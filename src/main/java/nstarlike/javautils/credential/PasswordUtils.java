package nstarlike.javautils.credential;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordUtils {
	public static String getRandomPassword(int size, boolean hasUpper, boolean hasNum, boolean hasSpecial) {
		//variable declarations
		StringBuilder password = new StringBuilder();
		final char[] lowers = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
							'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		final char[] uppers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
							'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		final char[] specials = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '?'};
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
	
	public static String encryptPassword(String password, String algorithm) throws NoSuchAlgorithmException {
		if(algorithm.startsWith("SHA-")) {
			//instance for hashing using algorithm
			MessageDigest md = MessageDigest.getInstance(algorithm);
			//hash the password
			byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			//convert hash to hex
			StringBuilder hexStr = new StringBuilder(hash.length * 2);
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexStr.append('0');
				}
				hexStr.append(hex);
			}
			
			return hexStr.toString();
			
		}else {
			throw new NoSuchAlgorithmException("Unsupported algorithm");
		}
	}
	
	public static boolean isSafePassword(String password) {
		//words not to be included
		final String[] blackLists = {
				"1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000", 
				"1234", "2345", "3456", "4567", "5678", "6789", "7890", 
				"aaaa", "bbbb", "cccc", "dddd", "eeee", "ffff", "gggg", "hhhh", "iiii", "jjjj", 
				"kkkk", "llll", "mmmm", "nnnn", "oooo", "pppp", "qqqq", "rrrr", "ssss", "tttt", 
				"uuuu", "vvvv", "wwww", "xxxx", "yyyy", "zzzz", 
				"qwer", "asdf", "zxcv", "wert", "sdfg", "xcvb"
		};
		
		//return false if password's length is short
		if(password.length() < 8) {
			return false;
		}
		
		//return false if password contains one of words
		for(String blackList : blackLists){
			if(password.contains(blackList)) {
				return false;
			}
		}
		
		return true;
	}
}
