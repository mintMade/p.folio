package com.picxen.user.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.picxen.member.model.MemberBean;
import com.picxen.photo.model.PhotoBean;

public class UserMainService {
	private UserMainDAO uMainDao;
	
	public UserMainService(){
		System.out.println("생성자:UserMainService()");
	}
	
	//setter
	public void setUMainDao(UserMainDAO uMainDao){
		this.uMainDao=uMainDao;
	}

	
	public MemberBean userInfo(String umId) throws SQLException{
		return uMainDao.userInfo(umId);
	}//userHomeView();
	
	public ArrayList<PhotoBean> myPtList(String umId) throws SQLException{
		return uMainDao.myPtList(umId);
	}//myPtList();
	
	public int userSetUpdate(MemberBean mbBean) throws SQLException{
		return uMainDao.userSetUpdate(mbBean);
	}//update user info
	
	public int likeTotal(String umId) throws SQLException{
		return uMainDao.likeTotal(umId);
	}//likeTotalcnt
	
	public int viewTotal(String umId) throws SQLException{
		return uMainDao.viewTotal(umId);
	}
	
	public int faveTotal(String umId) throws SQLException{
		return uMainDao.faveTotal(umId);
	}
	
	public List<PhotoBean> myAbmList(String umId) throws SQLException{
		return uMainDao.myAbmList(umId);
	}
	
	public int upUserBG(Map<String, Object> bgMap) throws SQLException{
		return uMainDao.upUserBG(bgMap);
	}
	
	public ArrayList<PhotoBean> listPtByTerm(String userid) throws SQLException{
		return uMainDao.listPtByTerm(userid);
	}//기간별 이미지리스트 myPtList로 대체
	
	public int upUserPt(PhotoBean ptBean) throws SQLException{
		return uMainDao.upUserPt(ptBean);
	}//이미지 수정
	
	public int delPt(int ptNo) throws SQLException{
		return uMainDao.delPt(ptNo);
	}
/*(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=picxen.crrp8x6fsdud.ap-northeast-2.rds.amazonaws.com)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=ORCL)(SERVER=DEDICATED))))*/

/*(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=picxen.crrp8x6fsdud.ap-northeast-2.rds.amazonaws.com)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=ORCL)(SERVER=DEDICATED)))*/
	
/*  (DESCRIPTION=
	(ADDRESS=(PROTOCOL=TCP)(HOST=mcharpen-fr-vip.fr.oracle.Com)(PORT=1555))
	  (CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=orcl.fr.oracle.com))
)*/
}//
