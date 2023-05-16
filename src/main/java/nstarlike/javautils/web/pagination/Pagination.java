package nstarlike.javautils.web.pagination;

import java.util.HashMap;
import java.util.Map;

public class Pagination {
	private int pageSize = 10;
	private int pageListSize = 10;
	private int pageNo;
	private int totalPage;
	private int firstPage;
	private int prevPage;
	private int startPage;
	private int endPage;
	private int nextPage;
	private int lastPage;
	private int total;
	private int startNo;
	private int endNo;

	public Pagination(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageListSize() {
		return pageListSize;
	}

	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public void calculateIndexes() throws PaginationInvalidValueException {
		if (pageNo <= 0) {
			throw new PaginationInvalidValueException("The pageNo variable value is not valid, " + pageNo);
		}
		if(pageSize < 1) {
			throw new PaginationInvalidValueException("The pageSize variable value is not valid, " + pageSize);
		}

		this.startNo = (pageNo - 1) * pageSize;
		this.endNo = startNo + pageSize - 1;
	}

	public void calculate(int total) throws PaginationInvalidValueException {
		calculateIndexes();
		
		if (total < 0) {
			throw new PaginationInvalidValueException("The total variable value is not valid, " + total);
		}
		this.total = total;

		if (this.pageNo <= 0) {
			throw new PaginationInvalidValueException("The pageNo variable value is not valid, " + this.pageNo);
		}

		if (this.pageSize <= 0) {
			throw new PaginationInvalidValueException("The pageSize value is not valid, " + this.pageSize);
		}

		if (this.pageListSize <= 0) {
			throw new PaginationInvalidValueException("The pageListSize value is not valid, " + this.pageListSize);
		}
		
		if(total == 0) {
			this.totalPage = 0;
			this.prevPage = 0;
			this.startPage = 0;
			this.endPage = 0;
			this.lastPage = 0;
			
			return;
		}

		
		if (this.total % this.pageSize == 0) {
			this.totalPage = this.total / this.pageSize;
		} else {
			this.totalPage = (int) (this.total / this.pageSize) + 1;
		}
		
		this.startPage = (int) ((this.pageNo - 1) / this.pageListSize) * this.pageListSize + 1;
		
		this.endPage = this.startPage + this.pageListSize - 1;
		if (this.endPage > this.totalPage) {
			this.endPage = this.totalPage;
		}

		this.firstPage = 1;
		
		if (this.startPage > 1) {
			this.prevPage = this.startPage - 1;
		}else {
			this.prevPage = 1;
		}
		
		if (this.endPage < this.totalPage) {
			this.nextPage = this.endPage + 1;
		} else {
			this.nextPage = this.endPage;
		}
		
		this.lastPage = 1;
		if(this.totalPage > 1) {
			this.lastPage = this.totalPage;
		}
	}

	@Override
	public String toString() {
		return "Pagination [pageSize=" + pageSize + ", pageListSize=" + pageListSize + ", pageNo=" + pageNo
				+ ", totalPage=" + totalPage + ", firstPage=" + firstPage + ", prevPage=" + prevPage + ", startPage="
				+ startPage + ", endPage=" + endPage + ", nextPage=" + nextPage + ", lastPage=" + lastPage + ", total="
				+ total + ", startNo=" + startNo + ", endNo=" + endNo + "]";
	}
	
	
}
