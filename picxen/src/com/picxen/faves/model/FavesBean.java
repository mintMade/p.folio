package com.picxen.faves.model;

public class FavesBean {
	private int faveNo;
	private String fUserId;
	private int PhotoNo;
	
	public int getFaveNo() {
		return faveNo;
	}
	public void setFaveNo(int faveNo) {
		this.faveNo = faveNo;
	}
	public String getfUserId() {
		return fUserId;
	}
	public void setfUserId(String fUserId) {
		this.fUserId = fUserId;
	}
	public int getPhotoNo() {
		return PhotoNo;
	}
	public void setPhotoNo(int photoNo) {
		PhotoNo = photoNo;
	}
	
	@Override
	public String toString() {
		return "FavesBean [faveNo=" + faveNo + ", fUserId=" + fUserId
				+ ", PhotoNo=" + PhotoNo + "]";
	}
	
	
/*	create table faves(
		    faveNo number primary key not null,
		    fUserId Varchar2(30) 
		        references member(userid) on delete cascade not null,--즐겨찾기 등록 아이디 : 고유접속번호(세션ID)
		    photoNo number
		        references photos(photoNo) not null
		);*/
}//
