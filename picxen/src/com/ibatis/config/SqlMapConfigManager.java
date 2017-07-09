package com.ibatis.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfigManager {
	//싱글톤 패턴으로 SqlMapClient 객체를 생성하는 클래스
	private static SqlMapConfigManager instance;
	private SqlMapClient sqlMapClient;
	
	
	//private 생성자
	private SqlMapConfigManager(){
		String configFile = "com/ibatis/config/SqlMapConfig.xml";
		try{
			Reader reader = Resources.getResourceAsReader(configFile);
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		}catch(IOException e){
			e.printStackTrace();
		}
	}//생성자
	
	public static SqlMapConfigManager getInstance(){
		if(instance==null){
			instance = new SqlMapConfigManager();
		}
		return instance;
	}
	
	public SqlMapClient getSqlMapClient(){
		return sqlMapClient;
	}
	
}///class
