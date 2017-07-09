package com.picxen.comments.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentsService {
	private CommentsDAO cmDao;
	
	//setter
	public void setCmDao(CommentsDAO cmDao){
		this.cmDao=cmDao;
	}

	public ArrayList<CommentsBean> searchComments(int ptNo) throws SQLException{
		return cmDao.searchComments(ptNo);
	}//searchComments();
	
	public int insertComments(CommentsBean cmBean) throws SQLException{
		
		return cmDao.insertComments(cmBean);
	}
	
	public MemberViewBean getMyAvatar(String userid) throws SQLException{
		return cmDao.getMyAvatar(userid);
	}
	
	public int getMemNumber(String userid)throws SQLException{
		return cmDao.getMemNumber(userid);
	}
	
//ž�ڸ�Ʈ���:���������� �����
	public ArrayList<MemCommentViewBean> topComments(int ptNo)throws SQLException{
		return cmDao.topComments(ptNo);
	}

//�ڸ�Ʈ ���
	public ArrayList<MemCommentViewBean> getCmList(int ptNo)throws SQLException{
		return cmDao.getCmList(ptNo);
	}

//�ڸ�Ʈ like update	
	public int upCmLike(CmLikeBean clBean)throws SQLException{
		return cmDao.upCmLike(clBean);
	}
	
//�ڸ�Ʈ like �α�
	public ArrayList<CmLikeBean> getCmLog(int ptNo) throws SQLException{
		return cmDao.getCmLog(ptNo);
	}

//�ڸ�Ʈ like �α�1
	public ArrayList<CmLikeBean> getclike(Map<String, Object> clogMap) throws SQLException{
		return cmDao.getclike(clogMap);
	}
	
	public CommentsBean getComment(int commentNo) throws SQLException{
		return cmDao.getComment(commentNo);
	}//ReplyNo �̸������ �ڵ� �������	
	
	public int insertTrply(CommentsBean cmBean) throws SQLException{
		return cmDao.insertTrply(cmBean);
	}//ž���� �Է�
	
	public int delCm(int commentNo) throws SQLException{
		return cmDao.delCm(commentNo);
	}//���� ����
	
}//
