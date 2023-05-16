package nstarlike.javautils.web.pagination;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaginationTest {
	private Pagination pagination;
	
	@Test
	void testCalculateIndexes_pageNoIs0() {
		System.out.println("start testCalculateIndexes_pageNoIs0");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(0);
			
			System.out.println(p.toString());
			
			p.calculateIndexes();
		});
	}
	
	@Test
	void testCalculateIndexes_pageNoIsNegative() {
		System.out.println("testCalculateIndexes_pageNoIsNegative");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(-1);
			
			System.out.println(p.toString());
			
			p.calculateIndexes();
		});
	}
	
	@Test
	void testCalculateIndexes_pageNoIs1() {
		System.out.println("start testCalculateIndexes_pageNoIs1");
		
		int pageNo = 1;
		Pagination p = new Pagination(pageNo);
		p.calculateIndexes();
		
		System.out.println(p.toString());
		
		assertTrue(p.getStartNo() == 0);
		assertTrue(p.getEndNo() == 9);
	}
	
	@Test
	void testCalculateIndexes_pageNoIs10() {
		System.out.println("start testCalculateIndexes_pageNoIs10");
		
		int pageNo = 10;
		Pagination p = new Pagination(pageNo);
		p.calculateIndexes();
		
		System.out.println(p.toString());
		
		assertTrue(p.getStartNo() == 90);
		assertTrue(p.getEndNo() == 99);
	}
	
	@Test
	void testCalculateIndexes_pageNoIs11() {
		System.out.println("start testCalculateIndexes_pageNoIs11");
		
		int pageNo = 11;
		Pagination p = new Pagination(pageNo);
		p.calculateIndexes();
		
		System.out.println(p.toString());
		
		assertTrue(p.getStartNo() == 100);
		assertTrue(p.getEndNo() == 109);
	}
	
	@Test
	void testCalculate_pageNoIs0() {
		System.out.println("start testCalculate_pageNoIs0");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(0);
			p.calculate(10);
		});
	}
	
	@Test
	void testCalculate_pageNoIsNegative() {
		System.out.println("start testCalculate_pageNoIsNegative");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(-1);
			p.calculate(10);
		});
	}
	
	@Test
	void testCalculate_pageSizeIs0() {
		System.out.println("start testCalculate_pageSizeIs0");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(1);
			p.setPageSize(0);
			p.calculate(10);
		});
	}
	
	@Test
	void testCalculate_pageSizeIsNegative() {
		System.out.println("start testCalculate_pageSizeIsNegative");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(-1);
			p.setPageSize(-1);
			p.calculate(10);
		});
	}
	
	@Test
	void testCalculate_pageListSizeIs0() {
		System.out.println("start testCalculate_pageListSizeIs0");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(1);
			p.setPageListSize(0);
			p.calculate(10);
		});
	}
	
	@Test
	void testCalculate_pageListSizeIsNegative() {
		System.out.println("start testCalculate_pageListSizeIsNegative");
		
		assertThrows(PaginationInvalidValueException.class, () -> {
			Pagination p = new Pagination(1);
			p.setPageListSize(-1);
			p.calculate(10);
		});
	}

	@Test
	void testCalculate_pageNoIs1_totalIs0() {
		System.out.println("start testCalculate_pageNoIs1_totalIs0");
		
		Pagination p = new Pagination(1);
		p.calculate(0);
		
		System.out.println(p.toString());
		
		assertTrue(p.getTotalPage() == 0);
		
		assertTrue(p.getStartPage() == 0);
		assertTrue(p.getEndPage() == 0);
		
		assertTrue(p.getFirstPage() == 0);
		assertTrue(p.getLastPage() == 0);
		
		assertTrue(p.getPrevPage() == 0);
		assertTrue(p.getLastPage() == 0);
	}
	
	@Test
	void testCalculate_pageNoIs1_totalIs1() {
		System.out.println("start testCalculate_pageNoIs1_totalIs0");
		
		Pagination p = new Pagination(1);
		p.calculate(1);
		
		System.out.println(p.toString());
		
		assertTrue(p.getTotalPage() == 1);
		
		assertTrue(p.getStartPage() == 1);
		assertTrue(p.getEndPage() == 1);
		
		assertTrue(p.getFirstPage() == 1);
		assertTrue(p.getLastPage() == 1);
		
		assertTrue(p.getPrevPage() == 1);
		assertTrue(p.getLastPage() == 1);
	}
	
	@Test
	void testCalculate_pageNoIs10() {
		System.out.println("start testCalculate_pageNoIs10");
		
		Pagination p = new Pagination(10);
		p.calculate(200);
		
		System.out.println(p.toString());
		
		assertTrue(p.getTotalPage() == 20);
		
		assertTrue(p.getStartPage() == 1);
		assertTrue(p.getEndPage() == 10);
		
		assertTrue(p.getFirstPage() == 1);
		assertTrue(p.getLastPage() == 20);
		
		assertTrue(p.getPrevPage() == 1);
		assertTrue(p.getNextPage() == 11);
	}

	@Test
	void testCalculate_pageNoIs11() {
		System.out.println("start testCalculate_pageNoIs11");
		
		Pagination p = new Pagination(11);
		p.calculate(201);
		
		System.out.println(p.toString());
		
		assertTrue(p.getTotalPage() == 21);
		
		assertTrue(p.getStartPage() == 11);
		assertTrue(p.getEndPage() == 20);
		
		assertTrue(p.getFirstPage() == 1);
		assertTrue(p.getLastPage() == 21);
		
		assertTrue(p.getPrevPage() == 10);
		assertTrue(p.getNextPage() == 21);
	}
	
	@Test
	void testCalculate_nextPage() {
		System.out.println("start testCalculate_nextPage");
		
		Pagination p = new Pagination(10);
		p.calculate(100);
		
		System.out.println(p.toString());
		
		assertTrue(p.getNextPage() == 10);
	}
}
