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
	
	//ȸ������
	
	public int insertMember(MemberBean bean)
			throws SQLException{
		return memberDao.insertMember(bean);
	}
	
	public int checkUserEmail(String email) throws SQLException{
		return memberDao.checkUserEmail(email);
	}
	
/*	public boolean checkUserid(String userid) throws SQLException{
		boolean result = false;
		int cnt = memberDao.checkUserid(userid);
			if(cnt>0){
				result = true;//�ش� ���̵� �����ϴ� ���
			}
		return result;
	}*///boolean ���̵� üũ
	
	public int checkUserid(String userid) throws SQLException{
		return memberDao.checkUserid(userid);
	}//idüũ
	
	public int checkIdPwd(String userid, String pwd)
			throws SQLException{
		int result = 0;
		String dbPwd = memberDao.checkIdPwd(userid);
		if(dbPwd != null && !dbPwd.isEmpty()){ //����� �������
			if(dbPwd.equals(pwd)){ // ����� ��ġ�� ���
				result=LOGIN_OK; //�α��� ����
			}else{
				result=PWD_DISAGREE;// �׷��� ������ �α��� ����
			}
		}else{
			result=ID_NONE; //�׷��� ������ ���̵� ����
		}
		System.out.println("reesult"+result);
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
