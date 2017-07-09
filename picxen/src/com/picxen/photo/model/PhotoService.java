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
		System.out.println("������:PhotoService");
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
	
	//1. �⺻ ī�װ� �˻� ���� ���!!
	//2 �޼ҵ� ����ȭ�� ���� ���
	public ArrayList<PhotoBean> listPhotoByCategory(String cgName)
			throws SQLException{
		return ptDao.listPhotoByCategory(cgName);
	}
	
	//���� listPhotoByCategory ��ü�޼ҵ�
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
	
	//2. ī�װ��˻�+�α����
	public ArrayList<PhotoBean> listPhotoByCtPop(String cgName) throws SQLException{
		return ptDao.listPhotoByCtPop(cgName);
	}
	
	//3. ī�װ�+���οö�� ����
	public ArrayList<PhotoBean> listPhotoByCtNew(String cgName) throws SQLException{
		return ptDao.listPhotoByCtNew(cgName);
	}
	
	//4. ī�װ�+�ߴ»���
	public ArrayList<PhotoBean> listPhotoByCtUpcom(String cgName) throws SQLException{
		return ptDao.listPhotoByCtUpcom(cgName);
	}
	
	//5. �α����
	public ArrayList<PhotoBean> listPhotoByPop() throws SQLException{
		return ptDao.listPhotoByPop();
	}
	
	//6. ���ο� ����
	public ArrayList<PhotoBean> listPhotoByNew() throws SQLException{
		return ptDao.listPhotoByNew();
	}
	
	//7. �߰��ִ� ����
	public ArrayList<PhotoBean> listPhotoByUpCom() throws SQLException{
		return ptDao.listPhotoByUpCom();
	}
	
	//�α��� ��ü�˻� ���� ��������Ʈ
	public ArrayList<PhotoBean> ptlistPop() throws SQLException{
		return ptDao.ptlistPop();
	}
	
	//�ε��� ��������Ʈ
	public ArrayList<PhotoBean> listPhotoAll() throws SQLException{
		return ptDao.listPhotoAll();
	}
	
	/*public ArrayList<PhotoBean> searchPhoto(Map<String, String>ptMap) throws SQLException{
		return ptDao.searchPhoto(ptMap);
	}*///���� �˻� �ʿ��� �Ϲ� �˻����� ����(�˻�â �ϳ��� ����)
	
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
	} Ʈ���������� ��ü*/
	
	public int insertLike(PhotoLikeBean pLikeBean) throws SQLException{
		return ptDao.insertLike(pLikeBean);
	}
	
	/*public boolean listPhotoLike(PhotoLikeBean plBean) throws SQLException{
		boolean result=false;
		result=ptDao.listPhotoLike(plBean);
		return result;
	}*///dao���� ���������� ����061517
	
/*	public int listPhotoLike(PhotoLikeBean plBean) throws SQLException{
		return ptDao.listPhotoLike(plBean);
	}*/
}///////
