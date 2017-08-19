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
	
	public CategoryBean getCgBean(String cgName) throws SQLException{
		return ptDao.getCgBean(cgName);
	}
	
	
	//출력방식 변경 적용 메소드
	public List<PhotoBean> listPhotoByAllView(CategoryBean cgBean) throws SQLException{
		return ptDao.listPhotoByAllView(cgBean);
	}
	
	public List<PhotoBean> listPhotoByCategory(CategoryBean cgBean) throws SQLException{
		return ptDao.listPhotoByCategory(cgBean);
	}//
	
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
	
	public PhotoBean insertLike(PhotoLikeBean pLikeBean) throws SQLException{
		return ptDao.insertLike(pLikeBean);
	}

}///////
