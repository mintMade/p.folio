package com.picxen.member.model;

import java.sql.SQLException;

public class MemberService {
	
	public static final int LOGIN_OK=1;
	public static final int PWD_DISAGREE=2;
	public static final int ID_NONE=3;
	
	
	private MemberDAO memberDao;
	
	//setter
	public void setMemberDao(MemberDAO memberDao){
		this.memberDao = memberDao;
	}
	
	//회원가입
	
	public int insertMember(MemberBean bean)
			throws SQLException{
		return memberDao.insertMember(bean);
	}
	
	public int checkUserEmail(String email) throws SQLException{
		return memberDao.checkUserEmail(email);
	}
	
	public int checkUserid(String userid) throws SQLException{
		return memberDao.checkUserid(userid);
	}//id체크
	
	public int checkIdPwd(String userid, String pwd)
			throws SQLException{
		int result = 0;
		String dbPwd = memberDao.checkIdPwd(userid);
		if(dbPwd != null && !dbPwd.isEmpty()){ //비번이 있을경우
			if(dbPwd.equals(pwd)){ // 비번이 일치할 경우
				result=LOGIN_OK; //로그인 성공
			}else{
				result=PWD_DISAGREE;// 그렇지 않으면 로그인 실패
			}
		}else{
			result=ID_NONE; //그렇지 않으면 아이디 없음
		}
		/*System.out.println("result"+result);*/
		return result;
		
	}
	
	public MemberBean viewMyInfo(String userid) throws SQLException{
		return memberDao.viewMyInfo(userid);
	}//
	
	public int updateMyInfo(MemberBean mbBean) throws SQLException{
		return memberDao.updateMyInfo(mbBean);
	}

	public String getMyIcon(String iconUserid)throws SQLException{
		return memberDao.getMyIcon(iconUserid);
	}
	
}//////class
