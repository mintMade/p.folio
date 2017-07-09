package com.picxen.faves.model;

import java.sql.SQLException;
import java.util.List;

import com.picxen.comments.model.MemberViewBean;
import com.picxen.photo.model.PhotoLikeBean;

public interface FavesDAO {
	public int insertFaves(FavesBean fvBean) throws SQLException;
	
	public List<MemberViewBean> getFaveList(int ptNo) throws SQLException;//photoLikeList 유저 아이콘리스트 출력
	
	public String getUploader(int ptNo) throws SQLException;
	
	public List<MemberViewBean> getLikeList (int ptNo) throws SQLException;//photoLikeList 유저 아이콘리스트 출력
	
	
}//
