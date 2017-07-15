package com.picxen.grBoard.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class GrBoardBean {
	private int boardNo;
	private int writerNo;
	private String title;
	private Date regdate;
	private String content;
	private int thumpCnt;
	private int readCnt;
	private String grImageUrl;
	private MultipartFile uploadImageFile;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getThumpCnt() {
		return thumpCnt;
	}
	public void setThumpCnt(int thumpCnt) {
		this.thumpCnt = thumpCnt;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getGrImageUrl() {
		return grImageUrl;
	}
	public void setGrImageUrl(String grImageUrl) {
		this.grImageUrl = grImageUrl;
	}
	public MultipartFile getUploadImageFile() {
		return uploadImageFile;
	}
	public void setUploadImageFile(MultipartFile uploadImageFile) {
		this.uploadImageFile = uploadImageFile;
	}
	@Override
	public String toString() {
		return "GrBoardBean [boardNo=" + boardNo + ", writerNo=" + writerNo + ", title=" + title + ", regdate="
				+ regdate + ", content=" + content + ", thumpCnt=" + thumpCnt + ", readCnt=" + readCnt + ", grImageUrl="
				+ grImageUrl + ", uploadImageFile=" + uploadImageFile + "]";
	}

	
}//class
