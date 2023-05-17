package nstarlike.javautils.web.querystring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class QueryStringBuilder {
	private List<String> whiteList = new ArrayList<>();
	private String idParamName;
	private String pageParamName;
	
	public QueryStringBuilder() {}
	
	public QueryStringBuilder(List<String> whiteList, String idParamName, String pageParamName) {
		this.whiteList = whiteList;
		this.idParamName = idParamName;
		this.pageParamName = pageParamName;
	}

	public List<String> getWhiteList() {
		return whiteList;
	}
	
	public void setWhiteList(List<String> whiteList) {
		this.whiteList = whiteList;
	}
	
	public String getIdParamName() {
		return idParamName;
	}
	
	public void setIdParamName(String idParamName) {
		this.idParamName = idParamName;
	}
	
	public String getPageParamName() {
		return pageParamName;
	}
	
	public void setPageParamName(String pageParamName) {
		this.pageParamName = pageParamName;
	}
	
	public void add(String whiteEntry) {
		this.whiteList.add(whiteEntry);
	}

	public String buildQueryString(Map<String, String> params, List<String> excludes, String prefix) {
		StringJoiner sj = new StringJoiner("&");
		
		Set<String> keys = params.keySet();
		for(String key : keys) {
			if(whiteList.contains(key) && !excludes.contains(key)) {
				sj.add(key + "=" + params.get(key));
			}
		}
		
		String queryString = "";
		
		if(sj.length() > 0) {
			queryString = prefix + sj.toString();
		}
		
		return queryString;
	}
	
	public String buildQueryString(Map<String, String> params) {
		return buildQueryString(params, null, null);
	}
	
	public String attachQueryString(Map<String, String> params) {
		return buildQueryString(params, null, "?");
	}
	
	public String addQueryString(Map<String, String> params) {
		return buildQueryString(params, null, "&");
	}
	
	public String attachListQueryString(Map<String, String> params) {
		List<String> excludes = new ArrayList<>();
		excludes.add(this.idParamName);
		return buildQueryString(params, excludes, "?");
	}
	
	public String addPageQueryString(Map<String, String> params) {
		List<String> excludes = new ArrayList<>();
		excludes.add(this.pageParamName);
		return buildQueryString(params, excludes, "&");
	}
}
