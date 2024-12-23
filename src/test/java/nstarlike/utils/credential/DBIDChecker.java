package nstarlike.utils.credential;

import java.util.ArrayList;
import java.util.List;

public class DBIDChecker implements IDChecker {

	@Override
	public List<String> getCheckedIds(List<String> ids) {
		List<String> checked = new ArrayList<String>();
		for(int i=0; i<ids.size(); i++) {
			if(i > 0) {
				checked.add(ids.get(i));
			}
		}
		return checked;
	}

}
