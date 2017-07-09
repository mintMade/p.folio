package com.picxen.comments.model;

public class MemberViewBean {
	private int memberNo;
	private String userid;
	private String fName;
	private String lName;
	private String myIcon;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
		return "MemberViewBean [memberNo=" + memberNo + ", userid=" + userid
				+ ", fName=" + fName + ", lName=" + lName + ", myIcon="
				+ myIcon + "]";
	}
	
}//
