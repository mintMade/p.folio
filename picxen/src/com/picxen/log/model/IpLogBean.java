package com.picxen.log.model;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

public class IpLogBean {
	private int logNo;
	private String userid;
	private String ip;
	private int ptNo;
	private Timestamp idate;
	
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPtNo() {
		return ptNo;
	}
	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}
	public Timestamp getIdate() {
		return idate;
	}
	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}
	
	@Override
	public String toString() {
		return "IpLogBean [logNo=" + logNo + ", userid=" + userid + ", ip="
				+ ip + ", ptNo=" + ptNo + ", idate=" + idate + "]";
	}
	
	/*logNo number primary key,
    userid varchar2(30) references member(userid) null,
    ip varchar2(15) null,
    ptNo number references photos(photoNo) null,
    idate date default sysdate*/
}
