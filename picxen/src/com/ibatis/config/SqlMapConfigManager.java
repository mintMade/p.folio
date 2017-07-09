package com.ibatis.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfigManager {
	//�̱��� �������� SqlMapClient ��ü�� �����ϴ� Ŭ����
	private static SqlMapConfigManager instance;
	private SqlMapClient sqlMapClient;
	
	
	//private ������
	private SqlMapConfigManager(){
		String configFile = "com/ibatis/config/SqlMapConfig.xml";
		try{
			Reader reader = Resources.getResourceAsReader(configFile);
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		}catch(IOException e){
			e.printStackTrace();
		}
	}//������
	
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
