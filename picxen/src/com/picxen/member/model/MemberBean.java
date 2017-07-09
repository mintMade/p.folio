package com.picxen.member.model;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class MemberBean {
	//멤버변수-property
	private int memberNo;
	private String userid;
	private String fName;
	private String lName;
	private String pwd;
	private String email;
	private Timestamp regdate;
	private int mileage;
	private String myIcon;
	private String descript;
	private String bg;
	private long iconSize;
	private long bgSize;
	
	//아이콘파일
	private MultipartFile uploadIcon;
	
	
	//bg파일
	private MultipartFile uploadBG;


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


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	public int getMileage() {
		return mileage;
	}


	public void setMileage(int mileage) {
		this.mileage = mileage;
	}


	public String getMyIcon() {
		return myIcon;
	}


	public void setMyIcon(String myIcon) {
		this.myIcon = myIcon;
	}


	public String getDescript() {
		return descript;
	}


	public void setDescript(String descript) {
		this.descript = descript;
	}


	public String getBg() {
		return bg;
	}


	public void setBg(String bg) {
		this.bg = bg;
	}


	public long getIconSize() {
		return iconSize;
	}


	public void setIconSize(long iconSize) {
		this.iconSize = iconSize;
	}


	public long getBgSize() {
		return bgSize;
	}


	public void setBgSize(long bgSize) {
		this.bgSize = bgSize;
	}


	public MultipartFile getUploadIcon() {
		return uploadIcon;
	}


	public void setUploadIcon(MultipartFile uploadIcon) {
		this.uploadIcon = uploadIcon;
	}


	public MultipartFile getUploadBG() {
		return uploadBG;
	}


	public void setUploadBG(MultipartFile uploadBG) {
		this.uploadBG = uploadBG;
	}


	@Override
	public String toString() {
		return "MemberBean [memberNo=" + memberNo + ", userid=" + userid
				+ ", fName=" + fName + ", lName=" + lName + ", pwd=" + pwd
				+ ", email=" + email + ", regdate=" + regdate + ", mileage="
				+ mileage + ", myIcon=" + myIcon + ", descript=" + descript
				+ ", bg=" + bg + ", iconSize=" + iconSize + ", bgSize="
				+ bgSize + ", uploadIcon=" + uploadIcon + ", uploadBG="
				+ uploadBG + "]";
	}

}//class
