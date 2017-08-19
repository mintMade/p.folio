package com.picxen.photo.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.picxen.comments.model.MemberViewBean;
import com.picxen.faves.model.IbatisFavesDAO;
import com.picxen.log.model.IpLogBean;
import com.picxen.member.model.MemberBean;

public class PhotoDAO { //faves��� 2207 extends IbatisFavesDAO 
	//�������
	private SqlMapClient sqlMap;
	
	//������
public PhotoDAO(){
	SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
	sqlMap=sqlMapConfig.getSqlMapClient();
	
	System.out.println("������:PhotoDAO");
}

	//�޼���
	public ArrayList<PhotoBean> listPhotoByEvent(String evntName)
	throws SQLException{
		//�̺�Ʈ�� ��ǰ��� ��ȸ�ϱ�
		//new�� �ش��ϴ� ��ǰ���, BEST, MD
	
		@SuppressWarnings("unchecked")
		ArrayList<PhotoBean> alist=(ArrayList<PhotoBean>)sqlMap.queryForList("ptListByEvent", evntName);
		return alist;
	}//listPhotoByEvent()
	
	public int uploadPhoto(PhotoBean bean) throws SQLException{
		int key =(Integer)sqlMap.insert("ptUpload", bean);
		return key;
	}//uploadPhoto()
	
	//������ ����� �޼ҵ�
	public CategoryBean getCgBean(String cgName) throws SQLException{
		CategoryBean cgBean = (CategoryBean)sqlMap.queryForObject("getCgBean",cgName);
		return cgBean;
	}
	

	//alpha
	public List<PhotoBean> listPhotoByAllView(CategoryBean cgBean) throws SQLException{
		@SuppressWarnings("unchecked")
		List<PhotoBean> ptList = (List<PhotoBean>)sqlMap.queryForList("listPtAll", cgBean);
		return ptList;
	}
	
	public List<PhotoBean> listPhotoByCategory(CategoryBean cgBean) throws SQLException{
		@SuppressWarnings("unchecked")
		List<PhotoBean> ptList=(List<PhotoBean>)sqlMap.queryForList("listPtCg", cgBean);
		return ptList;
	}
	
	////alpha
	
	public ArrayList<PhotoBean> searchPtTag(String kword) 
		throws SQLException{
		@SuppressWarnings("unchecked")
		ArrayList<PhotoBean> alist 
= (ArrayList<PhotoBean>)sqlMap.queryForList("searchPtByTag", kword);
		return alist;
	}//searchPtTag
	
	
	public PhotoBean viewPhoto(int ptNo) throws SQLException{
		//�ش� ��ǰ��ȣ�� �ش��ϴ� ���� ����
		PhotoBean ptBean = (PhotoBean) sqlMap.queryForObject("ptDetail", ptNo);
		return ptBean;
	}//viewPhoto()
	
	//���� �����뿹��
	public ArrayList<PhotoBean> listMostPopularPhoto(int ptNo) throws SQLException{
		@SuppressWarnings("unchecked")
		ArrayList<PhotoBean> alist
=(ArrayList<PhotoBean>)sqlMap.queryForList("listMostPopularPhoto", ptNo);
		return alist;
/*return (ArrayList<ProductBean>) sqlMap.queryForList("listMostPopularProduct", pdNo);*/
	}//listMostPopularPhoto()
	
	public MemberBean viewUserId(int ptNo) throws SQLException{
		MemberBean mbBean =(MemberBean)sqlMap.queryForObject("viewId", ptNo);
		return mbBean;
	}//viewUserName()
	
	public int photoCountView(IpLogBean ilBean) throws SQLException{ //ptNo=>ilBean
		int n=0;
		n = sqlMap.update("ptViewCnt", ilBean);
		return n;
	}
	
	public PhotoBean insertLike(PhotoLikeBean plBean) throws SQLException{
		int key = 0 , n=0;
		PhotoBean ptBean = new PhotoBean();
		try{
			sqlMap.startTransaction();
			
			n=sqlMap.update("ptLikeCnt", plBean);
			if( n == 1){
				key=(Integer)sqlMap.insert("insertLike", plBean);
			}
			
			sqlMap.queryForObject("prcdLike_pop", plBean);
			
			//���ھ� �� likeī��Ʈ ������ ���� ptBean ����
			int ptNo = plBean.getPtNo();
			ptBean = (PhotoBean) sqlMap.queryForObject("ptDetail", ptNo);
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			/*System.out.println("!Ʈ�����Ǽ��� pop���ν��� likeCnt="+plBean+", key="+key);*/
			
		}catch(SQLException e){
			System.out.println("like Ʈ������ ����");
			e.printStackTrace();
		}
		
		/*return key;*/
		return ptBean;
	}

}/////
