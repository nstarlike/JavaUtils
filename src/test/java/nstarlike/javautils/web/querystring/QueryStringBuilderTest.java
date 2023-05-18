package nstarlike.javautils.web.querystring;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueryStringBuilderTest {
	private QueryStringBuilder builder;
	
	@BeforeEach
	void beforeEach() {
		System.out.println("start beforeEach");
		
		builder = new QueryStringBuilder();
		builder.add("pageNo");
		builder.add("search");
		builder.add("keyword");
		builder.add("id");
		
		System.out.println("builder=" + builder);
	}
	
	@Test
	void testAdd() {
		System.out.println("start testAdd");
		
		builder = new QueryStringBuilder();
		builder.add("pageNo");
		builder.add("search");
		builder.add("keyword");
		
		System.out.println("builder=" + builder);
		
		assertTrue(builder.getWhiteList().contains("pageNo"));
		assertTrue(builder.getWhiteList().contains("search"));
		assertTrue(builder.getWhiteList().contains("keyword"));
	}
	
	@Test
	void testBuildQueryString_WithAllParameters() {
		System.out.println("start testBuilderQueryString_WithAllParameters");
		
		Map<String, String> params = new HashMap<>();
		params.put("pageNo", "1");
		params.put("search", "title");
		params.put("keyword", "test");
		
		List<String> excludes = new ArrayList<>();
		excludes.add("search");
		excludes.add("keyword");
		
		String queryString = builder.buildQueryString(params, excludes, "?");
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.startsWith("?"));
		assertTrue(queryString.contains("pageNo=1"));
		assertFalse(queryString.contains("search="));
		assertFalse(queryString.contains("keyword="));
	}
	
	@Test
	void testBuildQueryString_WithAllParameters_Empty() {
		System.out.println("start testBuildQueryString_WithAllParameters_Empty");
		
		String queryString = builder.buildQueryString(null, null, null);
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.equals(""));
	}

	@Test
	void testBuildQueryString_WithParams() {
		System.out.println("start testBuilderQuerString_WithParams");
		
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
		params.put("search", "content");
		params.put("keyword", "hi");
		
		String queryString = builder.buildQueryString(params);
		
		System.out.println("queryString=" + queryString);
		
		assertFalse(queryString.startsWith("?"));
		assertFalse(queryString.startsWith("&"));
		assertTrue(queryString.contains("id=1"));
		assertTrue(queryString.contains("search=content"));
		assertTrue(queryString.contains("keyword=hi"));
	}

	@Test
	void testAttachQueryString() {
		System.out.println("start testAttachQueryString");
		
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
		params.put("search", "title");
		params.put("keyword", "hi");
		
		String queryString = builder.attachQueryString(params);
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.startsWith("?"));
		assertTrue(queryString.contains("id=1"));
		assertTrue(queryString.contains("search=title"));
		assertTrue(queryString.contains("keyword=hi"));
	}

	@Test
	void testAddQueryString() {
		System.out.println("start testAddQueryString");
		
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
		params.put("pageNo", "1");
		
		String queryString = builder.addQueryString(params);
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.startsWith("&"));
		assertTrue(queryString.contains("id=1"));
		assertTrue(queryString.contains("pageNo=1"));
	}

	@Test
	void testAttachListQueryString() {
		System.out.println("start testAttachListQueryString");
		
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
		params.put("pageNo", "1");
		
		builder.setIdParamName("id");
		String queryString = builder.attachListQueryString(params);
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.startsWith("?"));
		assertTrue(queryString.contains("pageNo=1"));
		assertFalse(queryString.contains("id="));
	}
	
	@Test
	void testAttachListQueryString_Exception() {
		System.out.println("start testAttachListQueryString_Exception");
		
		assertThrows(QueryStringParamNameException.class, () -> {
			Map<String, String> params = new HashMap<>();
			params.put("id", "1");
			params.put("pageNo", "1");
			
			builder.attachListQueryString(params);
		});
	}

	@Test
	void testAddPageQueryString() {
		System.out.println("start testAddPageQueryString");
		
		Map<String, String> params = new HashMap<>();
		params.put("pageNo", "1");
		params.put("search", "title");
		params.put("keyword", "hi");
		
		builder.setPageParamName("pageNo");
		String queryString = builder.addPageQueryString(params);
		
		System.out.println("queryString=" + queryString);
		
		assertTrue(queryString.startsWith("&"));
		assertTrue(queryString.contains("search=title"));
		assertTrue(queryString.contains("keyword=hi"));
		assertFalse(queryString.contains("pageNo="));
	}
	
	@Test
	void testAddPageQueryString_Exception() {
		System.out.println("start testAddPageQueryString_Exception");
		
		assertThrows(QueryStringParamNameException.class, () -> {
			Map<String, String> params = new HashMap<>();
			params.put("pageNo", "1");
			
			builder.addPageQueryString(params);
		});
	}

}
