package com.picxen.faves.model;

import java.sql.SQLException;
import java.util.List;

import com.picxen.comments.model.MemberViewBean;

public class FavesService {
	private FavesDAO fvDao;
	
	//setter
	public void setFavesDao(FavesDAO fvDao){
		this.fvDao=fvDao;
	}
	
	//class
	public int insertFaves(FavesBean fvBean) throws SQLException{
		return fvDao.insertFaves(fvBean);
	}
	
	public List<MemberViewBean> getFaveList(int ptNo) throws SQLException{
		return fvDao.getFaveList(ptNo);
	}
	
	public String getUploader(int ptNo) throws SQLException{
		return fvDao.getUploader(ptNo);
	}
	public List<MemberViewBean> getLikeList (int ptNo) throws SQLException{
		return fvDao.getLikeList(ptNo);
	}
}//
