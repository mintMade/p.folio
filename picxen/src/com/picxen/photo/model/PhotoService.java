package com.picxen.photo.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.picxen.comments.model.MemberViewBean;
import com.picxen.log.model.IpLogBean;
import com.picxen.member.model.MemberBean;

public class PhotoService {
	private PhotoDAO ptDao;
	
	public PhotoService(){
		System.out.println("생성자:PhotoService");
	}
	
	//setter
	public void setPtDao(PhotoDAO ptDao){
		this.ptDao=ptDao;
	}
	
	public ArrayList<PhotoBean> listPhotoByEvent(String evntName)
			throws SQLException{
		return ptDao.listPhotoByEvent(evntName);
	}
	
	public int uploadPhoto(PhotoBean bean) throws SQLException{
		return ptDao.uploadPhoto(bean);
	}
	
	//1. 기본 카테고리 검색 삭제 대상!!
	//2 메소드 간소화로 삭제 대상
	public ArrayList<PhotoBean> listPhotoByCategory(String cgName)
			throws SQLException{
		return ptDao.listPhotoByCategory(cgName);
	}
	
	//위에 listPhotoByCategory 대체메소드
	public CategoryBean getCgBean(String cgName) throws SQLException{
		return ptDao.getCgBean(cgName);
	}
	
	public List<PhotoBean> listPhotoBySearch(CategoryBean cgBean) throws SQLException{
		return ptDao.listPhotoBySearch(cgBean);
	}
	
	////////////////////alpha
	public List<PhotoBean> listPhotoByAllView(CategoryBean cgBean) throws SQLException{
		return ptDao.listPhotoByAllView(cgBean);
	}
	
	public List<PhotoBean> listPhotoByCategory(CategoryBean cgBean) throws SQLException{
		return ptDao.listPhotoByCategory(cgBean);
	}
	/////////////////////
	
	//2. 카테고리검색+인기사진
	public ArrayList<PhotoBean> listPhotoByCtPop(String cgName) throws SQLException{
		return ptDao.listPhotoByCtPop(cgName);
	}
	
	//3. 카테고리+새로올라온 사진
	public ArrayList<PhotoBean> listPhotoByCtNew(String cgName) throws SQLException{
		return ptDao.listPhotoByCtNew(cgName);
	}
	
	//4. 카테고리+뜨는사진
	public ArrayList<PhotoBean> listPhotoByCtUpcom(String cgName) throws SQLException{
		return ptDao.listPhotoByCtUpcom(cgName);
	}
	
	//5. 인기사진
	public ArrayList<PhotoBean> listPhotoByPop() throws SQLException{
		return ptDao.listPhotoByPop();
	}
	
	//6. 새로운 사진
	public ArrayList<PhotoBean> listPhotoByNew() throws SQLException{
		return ptDao.listPhotoByNew();
	}
	
	//7. 뜨고있는 사진
	public ArrayList<PhotoBean> listPhotoByUpCom() throws SQLException{
		return ptDao.listPhotoByUpCom();
	}
	
	//인기기반 전체검색 기준 사진리스트
	public ArrayList<PhotoBean> ptlistPop() throws SQLException{
		return ptDao.ptlistPop();
	}
	
	//인덱스 사진리스트
	public ArrayList<PhotoBean> listPhotoAll() throws SQLException{
		return ptDao.listPhotoAll();
	}
	
	/*public ArrayList<PhotoBean> searchPhoto(Map<String, String>ptMap) throws SQLException{
		return ptDao.searchPhoto(ptMap);
	}*///사진 검색 맵에서 일반 검색으로 변경(검색창 하나만 지원)
	
	public ArrayList<PhotoBean> searchPtTag(String kword) throws SQLException{
		return ptDao.searchPtTag(kword);
	}
	
	public PhotoBean viewPhoto(int ptNo) throws SQLException{
		return ptDao.viewPhoto(ptNo);
	}
	
	public MemberBean viewUserId(int ptNo) throws SQLException{
		return ptDao.viewUserId(ptNo);
	}
	
	public ArrayList<PhotoBean> listMostPopularPhoto(int ptNo) throws SQLException{
		return ptDao.listMostPopularPhoto(ptNo);
	}
	
	public int photoCountView(IpLogBean ilBean) throws SQLException{
		return ptDao.photoCountView(ilBean);
	}
	
	/*public int likeUpdate(PhotoLikeBean pLikeBean) throws SQLException{
		return ptDao.likeUpdate(pLikeBean);
	} 트랜젝션으로 대체*/
	
	public int insertLike(PhotoLikeBean pLikeBean) throws SQLException{
		return ptDao.insertLike(pLikeBean);
	}
	
	/*public boolean listPhotoLike(PhotoLikeBean plBean) throws SQLException{
		boolean result=false;
		result=ptDao.listPhotoLike(plBean);
		return result;
	}*///dao에서 사용안함으로 제거061517
	
/*	public int listPhotoLike(PhotoLikeBean plBean) throws SQLException{
		return ptDao.listPhotoLike(plBean);
	}*/
}///////
