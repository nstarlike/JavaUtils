package nstarlike.utils.credential;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBIDChecker implements IDChecker {

	@Override
	public List<String> getCheckedIds(List<String> ids) {
		List<String> checked = new ArrayList<String>();
		Random r = new Random();
		int rInt = r.nextInt(ids.size());
		for(int i=0; i<ids.size(); i++) {
			if(i == rInt) {
				checked.add(ids.get(i));
			}
		}
		return checked;
	}

}
