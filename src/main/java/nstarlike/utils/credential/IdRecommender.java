package nstarlike.utils.credential;

import java.util.List;

public interface IdRecommender {
	public void addIds(List<String> list);
	public List<String> getIds();
}
