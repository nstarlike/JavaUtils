package nstarlike.utils.credential;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class FixIDRecommender implements IDRecommender {
	private String baseId;
	private List<String> fixes;
	private List<String> ids;

	public FixIDRecommender(String baseId) {
		// Validate the id
		if(baseId == null) {
			throw new RuntimeException("ID is null.");
		}
		
		if(baseId.isBlank()) {
			throw new RuntimeException("ID is blank.");
		}
		
		if(baseId.matches("^[0-9].*$")) {
			throw new RuntimeException("ID starts with a number.");
		}
		
		this.baseId = baseId;
		this.fixes = new ArrayList<String>();
		this.ids = new ArrayList<String>();
		
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(c.getTime());
		this.fixes.add(today);
		
		sdf = new SimpleDateFormat("MMdd");
		String monthDate = sdf.format(c.getTime());
		this.fixes.add(monthDate);
		
		sdf = new SimpleDateFormat("yyyy");
		String fullYear = sdf.format(c.getTime());
		this.fixes.add(fullYear);
		
		this.fixes.forEach((entry) -> {
			ids.add(this.baseId + entry);
		});
	}

	@Override
	public void addIds(List<String> list) {
		list.forEach((entry) -> {
			if(!this.ids.contains(entry)) {
				this.ids.add(entry);
			}
		});
	}

	@Override
	public List<String> getIds() {
		return this.ids;
	}

}
