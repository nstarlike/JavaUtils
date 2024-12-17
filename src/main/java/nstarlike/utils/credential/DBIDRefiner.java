package nstarlike.utils.credential;

import java.util.List;

public class DBIDRefiner implements IDRefiner {
	private IDChecker iDChecker;
	
	public DBIDRefiner(IDChecker iDChecker) {
		this.iDChecker = iDChecker;
	}
	
	@Override
	public List<String> refine(List<String> ids) {
		List<String> candidates = List.copyOf(ids);
		List<String> checkedIds = this.iDChecker.getCheckedIds(candidates);
		candidates.removeAll(checkedIds);
		return candidates;
	}
}
