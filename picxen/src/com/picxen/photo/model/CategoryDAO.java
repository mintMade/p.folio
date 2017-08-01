package com.picxen.photo.model;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class CategoryDAO {
	//멤버변수
	private SqlMapClient sqlMap;
	
	//생성자
	public CategoryDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		
		System.out.println("생성자:CategoryDAO");
	}
	
	public List<CategoryBean> listCategoryAll() throws SQLException{
		@SuppressWarnings("unchecked")
		List<CategoryBean> list = sqlMap.queryForList("cgList");
		return list;
	}//listCategoryAll()
	
}///
