package nstarlike.utils.credential;

import java.util.List;

public interface IdRefiner {
	public List<String> refine(List<String> ids);
}
