package nstarlike.utils.credential;

import java.util.List;

public class DBIDChecker implements IDChecker {

	@Override
	public List<String> getCheckedIds(List<String> ids) {
		List<String> exists = List.copyOf(ids);
		exists.remove(0);
		return exists;
	}

}
