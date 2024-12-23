package nstarlike.utils.credential;

import java.util.ArrayList;
import java.util.List;

public class DBIDRefiner implements IDRefiner {
	private IDChecker iDChecker;
	
	public DBIDRefiner(IDChecker iDChecker) {
		this.iDChecker = iDChecker;
	}
	
	@Override
	public List<String> refine(List<String> ids) {
		List<String> candidates = new ArrayList<String>();
		for(String id : ids) {
			candidates.add(id);
		}
		
		List<String> checkedIds = this.iDChecker.getCheckedIds(candidates);
		candidates.removeAll(checkedIds);
		return candidates;
	}
}
