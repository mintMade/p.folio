package com.picxen.search.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.picxen.photo.model.PhotoBean;

public class SearchDAO {
	private SqlMapClient sqlMap;
	
	//持失切
	public SearchDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap = sqlMapConfig.getSqlMapClient();
		System.out.println("持失切:SearchDAO");
	}
	
	//五辞球
	public ArrayList<PhotoBean> resultPhotoBySearch(PhotoBean ptBean) throws SQLException{
		ArrayList<PhotoBean> searchList 
			= (ArrayList<PhotoBean>)sqlMap.queryForList("resultPtBySearch", ptBean);
		return searchList;
	}//result search for photos
	
}//
