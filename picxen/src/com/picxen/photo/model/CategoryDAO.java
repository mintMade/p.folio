package com.picxen.photo.model;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class CategoryDAO {
	//�������
	private SqlMapClient sqlMap;
	
	//������
	public CategoryDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		
		System.out.println("������:CategoryDAO");
	}
	
	public List<CategoryBean> listCategoryAll() throws SQLException{
		List<CategoryBean> list = sqlMap.queryForList("cgList");
		return list;
	}//listCategoryAll()
	
}///
