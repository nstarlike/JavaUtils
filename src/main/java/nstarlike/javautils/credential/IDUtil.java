package nstarlike.javautils.credential;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDUtil {
	public static String getRandomID(String baseID) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i=0; i<4; i++) {
			sb.append(r.nextInt(10));
		}
		
		return baseID + sb.toString();
	}
	
	public static boolean checkID(String id, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(id);
		
		return m.matches();
	}
}
