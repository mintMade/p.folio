package com.picxen.member.model;

import java.sql.SQLException;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDAO {
	//멤버변수
	private SqlMapClient sqlMap;
	
	//생성자
	public MemberDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap = sqlMapConfig.getSqlMapClient();
		
		System.out.println("생성자:MemberDAO");
	}
	
	//메서드
	public int insertMember(MemberBean bean)
		throws SQLException{
		int key
		=(Integer)sqlMap.insert("memberInsert", bean);//memberInsert Dao , service 작성
		return key;
	}///회원가입
	
	//이메일 중복 체크
	public int checkUserEmail(String email) throws SQLException{
		int cnt
		 = (Integer)sqlMap.queryForObject("memberCheckEmail", email);
		return cnt;
	}//이메일 중복 체크
	
	public int checkUserid(String userid) throws SQLException{
		//아이디 중복확인 사용
		//해당id가 존재하는 체크
		int cnt
		=(Integer)sqlMap.queryForObject("memberCheckId", userid);
		return cnt;
	}//아이디 중복체크
	
	public String checkIdPwd(String userid)
		throws SQLException{
		String pwd = (String)sqlMap.queryForObject("memberCheckIdPwd", userid);
		return pwd;
	}//로그인
	
	public MemberBean viewMyInfo(String userid) throws SQLException{
		MemberBean mbBean=null;
		mbBean=(MemberBean)sqlMap.queryForObject("vMyInfo", userid);
		return mbBean;
	}//내정보 조회
	
	public int updateMyInfo(MemberBean mbBean) throws SQLException{
		int n=0;
		n=(Integer)sqlMap.queryForObject("upMyInfo", mbBean);
		return n;
	}
	
	public String getMyIcon(String iconUserid)throws SQLException{
		String userIcon=(String)sqlMap.queryForObject("getIcon", iconUserid);
		return userIcon; 
	}
	
}/////////
