package com.picxen.grBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class GrBoardDAO {
	private ConnectionProvider pool;
	
	public GrBoardDAO() {
		pool = new ConnectionProvider();
	}
	
	public int insertBoard(GrBoardBean grBean) {
		Connection con = null;
		PreparedStatement ps = null;
		
		int n = 0;
		try {
			//1.����̹� �ε� 2.db����
			con=pool.getConnection();
			
			//3.sql ����ó�� ps
			
			String sql = "insert into grBoard(boardNo, writerNo, title, content, thumpCnt, readCnt, grImageUrl)"
					+"values(grBoard_seq.nextval, ?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, grBean.getWriterNo());
			ps.setString(2, grBean.getTitle());
			ps.setString(3, grBean.getContent());
			ps.setInt(4, grBean.getThumpCnt());
			ps.setInt(5, grBean.getReadCnt());
			ps.setString(6, grBean.getGrImageUrl());
			
			//����
			n=ps.executeUpdate();
			System.out.println("�׷캸�� �۾��� ����"+n);
		}catch(SQLException e) {
			System.out.println("�׷캸�� �۾��� ����");
			e.printStackTrace();
		}finally {
			//�ڿ� ����
			pool.dbClose(ps, con);
		}
		return n;
	}
	
	public ArrayList<GrBoardBean> grListAll(){
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		ArrayList<GrBoardBean> gList= new ArrayList<GrBoardBean>();
		try {
			//1. ����̹� �ε� 2.��񿬰� 
			con=pool.getConnection();
			
			//3.����ó��
			String sql = "select * from grBoard order by boardNo desc";
			ps=con.prepareStatement(sql);
			
			//����
			rs = ps.executeQuery();
			while(rs.next()) {
				int boardNo=rs.getInt("boardNo");
				int writerNo=rs.getInt("memberNo");
				String title=rs.getString("title");
				Timestamp regdate=rs.getTimestamp("regdate");
				String content=rs.getString("content");
				int thumpCnt=rs.getInt("thumpCnt");
				int readCnt=rs.getInt("readCnt");
				String grImageUrl=rs.getString("grImageUrl");
				
				GrBoardBean grBean = new GrBoardBean();
				grBean.setBoardNo(boardNo);
				grBean.setWriterNo(writerNo);
				grBean.setTitle(title);
				grBean.setRegdate(regdate);
				grBean.setContent(content);
				grBean.setThumpCnt(thumpCnt);
				grBean.setReadCnt(readCnt);
				grBean.setGrImageUrl(grImageUrl);
			}
			System.out.println("�׷츮��Ʈ ����"+gList.size());
		}catch(SQLException e) {
			System.out.println("�׷츮��Ʈ ����");
			e.printStackTrace();
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
		return gList;
	}//ArrayList<GrBoardBean>
	
	public GrBoardBean detailBoard(int boardNo) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		
		GrBoardBean grBean = new GrBoardBean();
		try {
			//1.����̹��ε� 2.db����
			con=pool.getConnection();
			
			//3.sql
			String sql="select * from grBoard where boardNo=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, boardNo);
			
			//4����
			rs=ps.executeQuery();
			if(rs.next()) {
				/*boardNo=rs.getInt("boardNo");*/ //���� �������ʰ� �Ķ���͸� �Ѱ���
				int writerNo = rs.getInt("memberNo");
				String title = rs.getString("title");
				Timestamp regdate = rs.getTimestamp("regdate");
				String content = rs.getString("content");
				int thumpCnt = rs.getInt("thumpCnt");
				int readCnt = rs.getInt("readCnt");
				String grImageUrl = rs.getString("grImageUrl");
				
				grBean.setBoardNo(boardNo);
				grBean.setWriterNo(writerNo);
				grBean.setTitle(title);
				grBean.setRegdate(regdate);
				grBean.setContent(content);
				grBean.setThumpCnt(thumpCnt);
				grBean.setReadCnt(readCnt);
				grBean.setGrImageUrl(grImageUrl);
			}
			System.out.println("�׷�� �󼼺��⼺��"+grBean);
			
		}catch(SQLException e) {
			System.out.println("�׷�� �� ���� ����");
			e.printStackTrace();
		}
		return grBean;
	}//GrBoardBean detailBoard
	
	
	
	
}//class
