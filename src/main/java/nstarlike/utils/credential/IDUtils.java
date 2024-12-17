package nstarlike.utils.credential;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class IDUtils {
	public static String getRandomID(String baseID) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i=0; i<4; i++) {
			sb.append(r.nextInt(10));
		}
		
		return baseID + sb.toString();
	}
	
	public static List<String> getRecommendedIDs(IDRecommender iDRecommender, IDRefiner iDRefiner) {
		List<String> ids = iDRecommender.getIds();
		return iDRefiner.refine(ids);
	}
	
	public static boolean checkID(String id, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(id);
		
		return m.matches();
	}
}