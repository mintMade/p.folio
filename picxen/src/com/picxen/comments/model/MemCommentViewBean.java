package com.picxen.comments.model;

import java.sql.Timestamp;

public class MemCommentViewBean {
	private int commentNo;
	private int commentUser;
	private Timestamp regdate;
	private int commentLike;
	private String content;
	private int groupNo;
	private int sortGroup;
	private int step;
	private int sortNo;
	private String delflag;
	private int ptNo;
	private String userid;
	private String fName;
	private String lName;
	private String myIcon;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(int commentUser) {
		this.commentUser = commentUser;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getSortGroup() {
		return sortGroup;
	}
	public void setSortGroup(int sortGroup) {
		this.sortGroup = sortGroup;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public int getPtNo() {
		return ptNo;
	}
	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getMyIcon() {
		return myIcon;
	}
	public void setMyIcon(String myIcon) {
		this.myIcon = myIcon;
	}
	@Override
	public String toString() {
		return "MemCommentViewBean [commentNo=" + commentNo + ", commentUser="
				+ commentUser + ", regdate=" + regdate + ", commentLike="
				+ commentLike + ", content=" + content + ", groupNo=" + groupNo
				+ ", sortGroup=" + sortGroup + ", step=" + step + ", sortNo="
				+ sortNo + ", delflag=" + delflag + ", ptNo=" + ptNo
				+ ", userid=" + userid + ", fName=" + fName + ", lName="
				+ lName + ", myIcon=" + myIcon + "]";
	}
	
}
