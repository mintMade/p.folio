package com.picxen.photo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PhotoBean {
	
   /* PhotoNo number Not Null Primary Key, --���� ��ȣ
    categoryNo number references category(categoryNo) not null, --ī�װ���ȣ
    PhotoTitle varchar2(50) not null, --���� ����
    imageURL varchar2(50) not null, --��ǰ�̹���
    tag varchar2(50), --�±�
    description varchar2(4000) null, --�󼼼���
    regdate Date default sysdate, --��ǰ�����
    publicSet number Default 0 not null, --���� ���������
    mileage number Default 0, --���ϸ���
    likeCnt number Default 0 null, --���ƿ�
    ViewCnt number Default 0 null, --��ȸ��
    Faves number Default 0 null,--���ã��
    popular number Default 0 null, --�α��ġ
    popDate Date null,--�α� ������Ʈ��¥
    memberNo number references member(no) not null --���δ��̸�
    --�ǸŰ���, ����ȸ����ȸ�Ͽ���*/
	
	private int photoNo;
	private int categoryNo;
	private String photoTitle;
	private String imageURL;
	private String tag;
	private String description;
	private Timestamp regdate;
	private int publicSet;
	private int mileage;
	private int likeCnt;
	private int viewCnt;
	private int faves;
	private float popular;
	private Timestamp popDate;
	private String uploader;
	
	//���� ���ε� ���� �߰�
	private MultipartFile uploadFile;
	
	//�˻� Ű����
	private String searchCategory;
	private String searchKeyword;
	
	//ī�װ�����
	/*private String orderNo;*/
	
	//�ð� ī�װ�
	/*private List<Timestamp> regList;*/

	public int getPhotoNo() {
		return photoNo;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getPhotoTitle() {
		return photoTitle;
	}

	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getPublicSet() {
		return publicSet;
	}

	public void setPublicSet(int publicSet) {
		this.publicSet = publicSet;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getFaves() {
		return faves;
	}

	public void setFaves(int faves) {
		this.faves = faves;
	}

	public float getPopular() {
		return popular;
	}

	public void setPopular(float popular) {
		this.popular = popular;
	}

	public Timestamp getPopDate() {
		return popDate;
	}

	public void setPopDate(Timestamp popDate) {
		this.popDate = popDate;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "PhotoBean [photoNo=" + photoNo + ", categoryNo=" + categoryNo
				+ ", photoTitle=" + photoTitle + ", imageURL=" + imageURL
				+ ", tag=" + tag + ", description=" + description
				+ ", regdate=" + regdate + ", publicSet=" + publicSet
				+ ", mileage=" + mileage + ", likeCnt=" + likeCnt
				+ ", viewCnt=" + viewCnt + ", faves=" + faves + ", popular="
				+ popular + ", popDate=" + popDate + ", uploader=" + uploader
				+ ", uploadFile=" + uploadFile + ", searchCategory="
				+ searchCategory + ", searchKeyword=" + searchKeyword + "]";
	}


	
}/////
