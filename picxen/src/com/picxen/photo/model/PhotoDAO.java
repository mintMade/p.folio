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
	
		ArrayList<PhotoBean> alist
=(ArrayList<PhotoBean>)sqlMap.queryForList("ptListByEvent", evntName);
		
		return alist;
	}//listPhotoByEvent()
	
	public int uploadPhoto(PhotoBean bean) throws SQLException{
		int key =(Integer)sqlMap.insert("ptUpload", bean);
		return key;
	}//uploadPhoto()
	
	//1. �⺻ ī�װ� ���� ��� //"ptListByCg"�� pop���� �����ؼ� ���� xml���� �ش� ���̵����
	//2. ���� �� �޼ҵ� ����ȭ �������� ���� ����20170607
	public ArrayList<PhotoBean> listPhotoByCategory(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist 
			= (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByCg", cgName);
		return alist;
	}//listPhotoCategory()
	
	
	//������ ����� �޼ҵ�
	public CategoryBean getCgBean(String cgName) throws SQLException{
		CategoryBean cgBean = (CategoryBean)sqlMap.queryForObject("getCgBean",cgName);
		return cgBean;
	}
	
	//proto type ��������
	public List<PhotoBean> listPhotoBySearch(CategoryBean cgBean) throws SQLException{
		
		List<PhotoBean> ptList 
			= (List<PhotoBean>)sqlMap.queryForList("listPtBySearch", cgBean);
		
		return ptList;
	}
	//alpha
	public List<PhotoBean> listPhotoByAllView(CategoryBean cgBean) throws SQLException{
		List<PhotoBean> ptList = (List<PhotoBean>)sqlMap.queryForList("listPtAll", cgBean);
		return ptList;
	}
	
	public List<PhotoBean> listPhotoByCategory(CategoryBean cgBean) throws SQLException{
		List<PhotoBean> ptList=(List<PhotoBean>)sqlMap.queryForList("listPtCg", cgBean);
		return ptList;
	}
	
	////alpha
	
	//////////////////
	
	//2. ī�װ�+�α� ����
	public ArrayList<PhotoBean> listPhotoByCtPop(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist=null;
		alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByPop", cgName);
		
		return alist;
	}
	
	//3. ī�װ�+���οö�� ����
	public ArrayList<PhotoBean> listPhotoByCtNew(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByNew", cgName);
		
		return alist;
	}
	
	//4. ī�װ�+�ű� �ߴ� ����
	public ArrayList<PhotoBean> listPhotoByCtUpcom(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByUpcom",cgName);
		
		return alist;
	}
	
	//5. �α����
	public ArrayList<PhotoBean> listPhotoByPop() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptPopList");
		
		return alist;
	}
	
	//6 .���οö�� ����
	public ArrayList<PhotoBean> listPhotoByNew() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptNewList");
		
		return alist;
	}
	
	//7. �߰��ִ� ����
	public ArrayList<PhotoBean> listPhotoByUpCom() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptUpcomList");

		return alist;		
	}
	
	//�α���� ����Ʈ �����ϱ� �⺻
	public ArrayList<PhotoBean> ptlistPop() throws SQLException{
		ArrayList<PhotoBean> alist = null;
		alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptPopList");
		
		return alist;
	}//listPopList
	
	//index���� �ε�
	public ArrayList<PhotoBean> listPhotoAll() throws SQLException{
		ArrayList<PhotoBean> alist
=(ArrayList<PhotoBean>)sqlMap.queryForList("ptListAll");
		
		return alist;
	}//listPhotoAll()
	
	/*public ArrayList<PhotoBean> searchPhoto(Map<String, String>ptMap) throws SQLException{
		return (ArrayList<PhotoBean>)sqlMap.queryForList("searchPhoto", ptMap);
	}//searchPhoto()
*/	//���� �˻� �ʿ��� �Ϲ� �˻����� ����(���� �˻�â �ϳ��� ����)
	
	public ArrayList<PhotoBean> searchPtTag(String kword) 
		throws SQLException{
		ArrayList<PhotoBean> alist 
= (ArrayList<PhotoBean>)sqlMap.queryForList("searchPtByTag", kword);
		return alist;
	}//searchPtTag
	
	
	public PhotoBean viewPhoto(int ptNo) throws SQLException{
		//�ش� ��ǰ��ȣ�� �ش��ϴ� ���� ����
		PhotoBean ptBean = (PhotoBean) sqlMap.queryForObject("ptDetail", ptNo);
		return ptBean;
	}//viewPhoto()
	
	//�ʿ����??
	public ArrayList<PhotoBean> listMostPopularPhoto(int ptNo) throws SQLException{
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
	
	public int insertLike(PhotoLikeBean plBean) throws SQLException{
		int key = 0 , n=0;
		try{
			sqlMap.startTransaction();
			
			n=sqlMap.update("ptLikeCnt", plBean);
			if( n == 1){
				key=(Integer)sqlMap.insert("insertLike", plBean);//�αװ���ӽ��� ���߿� 1�� �̻��� ��� �μ�Ʈ ���� 2016/7= fix 2207
			}
			sqlMap.queryForObject("prcdLike_pop", plBean);
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			System.out.println("!Ʈ�����Ǽ��� like insert(likeTable), update(photosTable), pop���ν��� likeCnt="+plBean+", key="+key);
			
		}catch(SQLException e){
			System.out.println("like Ʈ������ ����");
			e.printStackTrace();
		}
		
		return key;
	}
	

	/*public boolean listPhotoLike(PhotoLikeBean plBean) throws SQLException{
		boolean result=false;
		int cnt=0;
			cnt=(Integer)sqlMap.queryForObject("getLikeUserid", plBean);
			if(cnt > 0){
				result=true;
			}
			return result;
	}*///checkLike(); ������

}/////
