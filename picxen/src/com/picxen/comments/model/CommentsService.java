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
	
//탑코멘트출력:모두출력으로 변경됨
	public ArrayList<MemCommentViewBean> topComments(int ptNo)throws SQLException{
		return cmDao.topComments(ptNo);
	}

//코멘트 출력
	public ArrayList<MemCommentViewBean> getCmList(int ptNo)throws SQLException{
		return cmDao.getCmList(ptNo);
	}

//코멘트 like update	
	public int upCmLike(CmLikeBean clBean)throws SQLException{
		return cmDao.upCmLike(clBean);
	}
	
//코멘트 like 로그
	public ArrayList<CmLikeBean> getCmLog(int ptNo) throws SQLException{
		return cmDao.getCmLog(ptNo);
	}

//코멘트 like 로그1
	public ArrayList<CmLikeBean> getclike(Map<String, Object> clogMap) throws SQLException{
		return cmDao.getclike(clogMap);
	}
	
	public CommentsBean getComment(int commentNo) throws SQLException{
		return cmDao.getComment(commentNo);
	}//ReplyNo 미리써놓은 코드 삭제대상	
	
	public int insertTrply(CommentsBean cmBean) throws SQLException{
		return cmDao.insertTrply(cmBean);
	}//탑리플 입력
	
	public int delCm(int commentNo) throws SQLException{
		return cmDao.delCm(commentNo);
	}//리플 삭제
	
}//
