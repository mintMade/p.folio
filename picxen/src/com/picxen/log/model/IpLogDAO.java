package com.picxen.log.model;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

//������
public class IpLogDAO {
	private SqlMapClient sqlMap;
	
	public IpLogDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		
		System.out.println("������:IpLogDAO");
	}
	
	//�޼���
	public int insertPtlog(IpLogBean ilBean) throws SQLException{
		int key = 0;
		key = (Integer)sqlMap.insert("ipInsert", ilBean);
		return key;
	}//insertPtlog()
	
}///
