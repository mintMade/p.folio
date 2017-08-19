package com.picxen.log.model;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

//持失切
public class IpLogDAO {
	private SqlMapClient sqlMap;
	
	public IpLogDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		
		System.out.println("持失切:IpLogDAO");
	}
	
	//五辞球
	public int insertPtlog(IpLogBean ilBean) throws SQLException{
		int k = 0;
		k = sqlMap.update("ipInsert", ilBean);
		return k;
	}//insertPtlog()
	
}///
