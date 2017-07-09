package com.picxen.member.model;

import java.sql.SQLException;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDAO {
	//�������
	private SqlMapClient sqlMap;
	
	//������
	public MemberDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap = sqlMapConfig.getSqlMapClient();
		
		System.out.println("������:MemberDAO");
	}
	
	//�޼���
	public int insertMember(MemberBean bean)
		throws SQLException{
		int key
		=(Integer)sqlMap.insert("memberInsert", bean);//memberInsert Dao , service �ۼ�
		return key;
	}///ȸ������
	
	//�̸��� �ߺ� üũ
	public int checkUserEmail(String email) throws SQLException{
		int cnt
		 = (Integer)sqlMap.queryForObject("memberCheckEmail", email);
		return cnt;
	}//�̸��� �ߺ� üũ
	
	public int checkUserid(String userid) throws SQLException{
		//���̵� �ߺ�Ȯ�� ���
		//�ش�id�� �����ϴ� üũ
		int cnt
		=(Integer)sqlMap.queryForObject("memberCheckId", userid);
		return cnt;
	}//���̵� �ߺ�üũ
	
	public String checkIdPwd(String userid)
		throws SQLException{
		String pwd = (String)sqlMap.queryForObject("memberCheckIdPwd", userid);
		return pwd;
	}//�α���
	
	public MemberBean viewMyInfo(String userid) throws SQLException{
		MemberBean mbBean=null;
		mbBean=(MemberBean)sqlMap.queryForObject("vMyInfo", userid);
		return mbBean;
	}//������ ��ȸ
	
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
