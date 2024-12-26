package nstarlike.utils.credential;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class IdUtils {
	public static String getRandomId(String baseId) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i=0; i<4; i++) {
			sb.append(r.nextInt(10));
		}
		
		return baseId + sb.toString();
	}
	
	public static List<String> getRecommendedIds(IdRecommender idRecommender, IdRefiner idRefiner) {
		List<String> ids = idRecommender.getIds();
		return idRefiner.refine(ids);
	}
	
	public static boolean checkId(String id, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(id);
		
		return m.matches();
	}
}