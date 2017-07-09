package com.picxen.comments.model;

public class CmLikeBean {
	private int clNo;
	private int commentUser;
	private int ptNo;
	private int commentNo;
	private int sortNo;
	private String userid;
	
	public int getClNo() {
		return clNo;
	}
	public void setClNo(int clNo) {
		this.clNo = clNo;
	}
	public int getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(int commentUser) {
		this.commentUser = commentUser;
	}
	public int getPtNo() {
		return ptNo;
	}
	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "CmLikeBean [clNo=" + clNo + ", commentUser=" + commentUser
				+ ", ptNo=" + ptNo + ", commentNo=" + commentNo + ", sortNo="
				+ sortNo + ", userid=" + userid + "]";
	}	
	
}//
