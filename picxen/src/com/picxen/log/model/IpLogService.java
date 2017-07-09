package com.picxen.log.model;

import java.sql.SQLException;

public class IpLogService {
	private IpLogDAO ilDao;
	
	//setter
	public void setIlDao(IpLogDAO ilDao){
		this.ilDao=ilDao;
	}
	
	public int insertPtlog(IpLogBean ilBean) throws SQLException{
		return ilDao.insertPtlog(ilBean);
	}
	
}////
