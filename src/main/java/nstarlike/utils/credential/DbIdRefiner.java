package nstarlike.utils.credential;

import java.util.ArrayList;
import java.util.List;

public class DbIdRefiner implements IdRefiner {
	private IdChecker idChecker;
	
	public DbIdRefiner(IdChecker iDChecker) {
		this.idChecker = iDChecker;
	}
	
	@Override
	public List<String> refine(List<String> ids) {
		List<String> candidates = new ArrayList<String>();
		for(String id : ids) {
			candidates.add(id);
		}
		
		List<String> checkedIds = this.idChecker.getCheckedIds(candidates);
		candidates.removeAll(checkedIds);
		return candidates;
	}
}
