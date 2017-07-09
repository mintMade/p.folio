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

public class PhotoDAO { //faves상속 2207 extends IbatisFavesDAO 
	//멤버변수
	private SqlMapClient sqlMap;
	
	//생성자
public PhotoDAO(){
	SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
	sqlMap=sqlMapConfig.getSqlMapClient();
	
	System.out.println("생성자:PhotoDAO");
}

	//메서드
	public ArrayList<PhotoBean> listPhotoByEvent(String evntName)
	throws SQLException{
		//이벤트별 상품목록 조회하기
		//new에 해당하는 상품목록, BEST, MD
	
		ArrayList<PhotoBean> alist
=(ArrayList<PhotoBean>)sqlMap.queryForList("ptListByEvent", evntName);
		
		return alist;
	}//listPhotoByEvent()
	
	public int uploadPhoto(PhotoBean bean) throws SQLException{
		int key =(Integer)sqlMap.insert("ptUpload", bean);
		return key;
	}//uploadPhoto()
	
	//1. 기본 카테고리 삭제 대상 //"ptListByCg"를 pop으로 변경해서 적용 xml에는 해당 아이디없음
	//2. 쿼리 및 메소드 간소화 변경으로 삭제 예정20170607
	public ArrayList<PhotoBean> listPhotoByCategory(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist 
			= (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByCg", cgName);
		return alist;
	}//listPhotoCategory()
	
	
	//위에서 변경된 메소드
	public CategoryBean getCgBean(String cgName) throws SQLException{
		CategoryBean cgBean = (CategoryBean)sqlMap.queryForObject("getCgBean",cgName);
		return cgBean;
	}
	
	//proto type 삭제예정
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
	
	//2. 카테고리+인기 사진
	public ArrayList<PhotoBean> listPhotoByCtPop(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist=null;
		alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByPop", cgName);
		
		return alist;
	}
	
	//3. 카테고리+새로올라온 사진
	public ArrayList<PhotoBean> listPhotoByCtNew(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByNew", cgName);
		
		return alist;
	}
	
	//4. 카테고리+신규 뜨는 사진
	public ArrayList<PhotoBean> listPhotoByCtUpcom(String cgName) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByUpcom",cgName);
		
		return alist;
	}
	
	//5. 인기사진
	public ArrayList<PhotoBean> listPhotoByPop() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptPopList");
		
		return alist;
	}
	
	//6 .새로올라온 사진
	public ArrayList<PhotoBean> listPhotoByNew() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptNewList");
		
		return alist;
	}
	
	//7. 뜨고있는 사진
	public ArrayList<PhotoBean> listPhotoByUpCom() throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptUpcomList");

		return alist;		
	}
	
	//인기사진 리스트 구경하기 기본
	public ArrayList<PhotoBean> ptlistPop() throws SQLException{
		ArrayList<PhotoBean> alist = null;
		alist = (ArrayList<PhotoBean>)sqlMap.queryForList("ptPopList");
		
		return alist;
	}//listPopList
	
	//index에서 로드
	public ArrayList<PhotoBean> listPhotoAll() throws SQLException{
		ArrayList<PhotoBean> alist
=(ArrayList<PhotoBean>)sqlMap.queryForList("ptListAll");
		
		return alist;
	}//listPhotoAll()
	
	/*public ArrayList<PhotoBean> searchPhoto(Map<String, String>ptMap) throws SQLException{
		return (ArrayList<PhotoBean>)sqlMap.queryForList("searchPhoto", ptMap);
	}//searchPhoto()
*/	//사진 검색 맵에서 일반 검색으로 변경(현재 검색창 하나만 지원)
	
	public ArrayList<PhotoBean> searchPtTag(String kword) 
		throws SQLException{
		ArrayList<PhotoBean> alist 
= (ArrayList<PhotoBean>)sqlMap.queryForList("searchPtByTag", kword);
		return alist;
	}//searchPtTag
	
	
	public PhotoBean viewPhoto(int ptNo) throws SQLException{
		//해당 상품번호에 해당하는 사진 정보
		PhotoBean ptBean = (PhotoBean) sqlMap.queryForObject("ptDetail", ptNo);
		return ptBean;
	}//viewPhoto()
	
	//필요없음??
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
				key=(Integer)sqlMap.insert("insertLike", plBean);//로그가계속쌓임 나중에 1개 이상일 경우 인서트 금지 2016/7= fix 2207
			}
			sqlMap.queryForObject("prcdLike_pop", plBean);
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			System.out.println("!트랜젝션성공 like insert(likeTable), update(photosTable), pop프로시져 likeCnt="+plBean+", key="+key);
			
		}catch(SQLException e){
			System.out.println("like 트랜젝션 실패");
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
	}*///checkLike(); 사용안함

}/////
