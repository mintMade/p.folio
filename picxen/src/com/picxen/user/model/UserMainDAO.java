
package com.picxen.user.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.picxen.comments.model.CmLikeBean;
import com.picxen.member.model.MemberBean;
import com.picxen.photo.model.PhotoBean;

public class UserMainDAO {
	//�������
	private SqlMapClient sqlMap;
	
	//������
	public UserMainDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		System.out.println("������:UserMainDAO");
	}
	
	//�޼���
	public MemberBean userInfo(String umId) throws SQLException{
		MemberBean mbBean = (MemberBean)sqlMap.queryForObject("viewUserInfo", umId);
		return mbBean;
	}//userHomeView();
	
	public ArrayList<PhotoBean> myPtList(String umId) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("UserMainPtList", umId);
		return alist;
	}//userPtList();
	
	
	/*public ArrayList<PhotoBean> myPtList(Map<String, String>ptMap) throws SQLException{
		ArrayList<PhotoBean> alist = (ArrayList<PhotoBean>)sqlMap.queryForList("UserMainPtList", ptMap);
		return alist;
	}//userPtList(); map����*/
	
	public int userSetUpdate(MemberBean mbBean) throws SQLException{
		int n = (Integer)sqlMap.update("userSetUpdate", mbBean);
		return n;
	}//userSetUpdate();
	
	public int likeTotal(String umId) throws SQLException{
		int likeTtl=(Integer)sqlMap.queryForObject("userLikeTotal", umId);
		return likeTtl;
	}//liketotal();
	
	public int viewTotal(String umId) throws SQLException{
		int viewTtl=(Integer)sqlMap.queryForObject("userViewTotal", umId);
		return viewTtl;
	}//viewtotal();
	
	public int faveTotal(String umId) throws SQLException{
		int faveTtl = (Integer)sqlMap.queryForObject("userFaveTotal", umId);
		return faveTtl;
	}//faveTotal();
	
	public List<PhotoBean> myAbmList(String umId) throws SQLException{
		List<PhotoBean> blist = (List<PhotoBean>)sqlMap.queryForList("albumList", umId);
		return blist;
	}
	
	public int upUserBG(Map<String, Object> bgMap) throws SQLException{
		int result = (Integer)sqlMap.update("upBgMap", bgMap);
		return result;
	}//bg������Ʈ
	
	public ArrayList<PhotoBean> listPtByTerm(String userid) throws SQLException{
		ArrayList<PhotoBean> ptList = (ArrayList<PhotoBean>)sqlMap.queryForList("ptListByTerm", userid);
		return ptList;
	}//listPtByTerm�Ⱓ�� �̹��� ����Ʈ ��� myPtList�� ��ü
	
	public int upUserPt(PhotoBean ptBean) throws SQLException{
		int n = (Integer)sqlMap.update("updatePt", ptBean);
		return n;
	}//�̹��� ����
	
	public int delPt(int ptNo) throws SQLException{
		int n = (Integer)sqlMap.delete("delPt", ptNo);
		return n;
	}
	
	
	
}///
