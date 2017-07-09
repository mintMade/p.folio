package com.picxen.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PagingBean {
	private int currentPage; //현재 페이지
	private int totalRecord; //총 레코드수
	private int pageSize; //페이지당 보여질 레코드수
	private int totalPage; //총 페이지 수
	private int blockSize; //블럭당 보여질 페이지 수
	private int firstPage; //블럭당 시작 페이지
	private int lastPage; //블럭당 마지막 페이지
	private int curPos; //페이지당 시작 인덱스(list내에서)
	private int num; //페이지당 시작 글 번호
	
	public PagingBean(HttpServletRequest request, List list, int pageSize, int blockSize){
		currentPage=1;
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));			
		}
		totalRecord=list.size();
		this.pageSize=pageSize; //페이지당 보여질 레코드 수
		totalPage=(int)Math.ceil((float)totalRecord/pageSize); //총 페이지수=총 레코드/페이지당 보여질 레코드 수
		this.blockSize=blockSize; //블럭당 보여질 페이지 수 [1][2][3][4][5]
		firstPage=currentPage-((currentPage-1)%blockSize); //13 % 5 = 3 블럭당 시작 페이지
		lastPage=firstPage+(blockSize-1); //블럭당 마지막 페이지
		curPos=(currentPage-1)*pageSize;
		num=totalRecord-curPos;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "PagingBean [currentPage=" + currentPage + ", totalRecord="
				+ totalRecord + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", blockSize=" + blockSize + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", curPos=" + curPos
				+ ", num=" + num + "]";
	}
	
}/////////
