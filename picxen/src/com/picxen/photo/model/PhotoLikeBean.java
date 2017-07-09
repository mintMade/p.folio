package com.picxen.photo.model;

public class PhotoLikeBean {
	private int likeNo;
	private String userid;
	private int ptNo;
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPtNo() {
		return ptNo;
	}
	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}
	
	@Override
	public String toString() {
		return "PhotoLikeBean [likeNo=" + likeNo + ", userid=" + userid
				+ ", ptNo=" + ptNo + "]";
	}
	

}//
