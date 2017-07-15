package com.picxen.grBoard.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	DataSource ds;
	
	//생성자
	public ConnectionProvider() {
		Context ctx;
		
		try {
			ctx= new InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oracledb");
			System.out.println("DataSource 할당 성공");
		}catch(NamingException e) {
			System.out.println("jsp DataSource 할당 추출 실패");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con=ds.getConnection();
			System.out.println("db연결 성공"+con);
		}catch(SQLException e) {
			System.out.println("db연결 실패");
			e.printStackTrace();
		}
		return con;
	}
	
	public void dbClose(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();//반납
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose(PreparedStatement ps, Connection con) {
		try {
			if(ps !=null)ps.close();
			if(con !=null)con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose(CallableStatement cs, Connection con) {
		try {
			if(cs!=null)cs.close();
			if(con!=null)con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}//class
