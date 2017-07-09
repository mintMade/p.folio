package com.picxen.comments.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class CommentsDAO {
	private SqlMapClient sqlMap;
	
	//생성자
	public CommentsDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		
		System.out.println("comment생성자:CommentsDAO()");
	}
	
	//코멘트 출력
	public ArrayList<CommentsBean> searchComments(int ptNo) throws SQLException{
		ArrayList<CommentsBean> alist = (ArrayList<CommentsBean>)sqlMap.queryForList("userComments",ptNo);
		return alist;
	}
//코멘트 입력	
	public int insertComments(CommentsBean cmBean) throws SQLException{
		int key=0;
		key=(Integer)sqlMap.insert("insertCmts", cmBean);
		return key;
	}
//내아바타정보	
	public MemberViewBean getMyAvatar(String userid) throws SQLException{
		MemberViewBean mvBean=null;
		mvBean=(MemberViewBean)sqlMap.queryForObject("getMyAvtar", userid);
		return mvBean;
	}//getavatar()
	
	public int getMemNumber(String userid)throws SQLException{
		int commentUser = 0;
		commentUser = (Integer)sqlMap.queryForObject("getMemNum", userid);
		return commentUser;
	}//memberNo()
	
//탑코멘트 출력	: 모두출력으로 변경됨
	public ArrayList<MemCommentViewBean> topComments(int ptNo)throws SQLException{
		ArrayList<MemCommentViewBean> tAlist=null;
		tAlist = (ArrayList<MemCommentViewBean>)sqlMap.queryForList("getTopComments", ptNo);
		return tAlist;
	}//topComment, memCommentBean
	
//일반 코멘트 출력
	
/*	public ArrayList<MemCommentViewBean> getCmList(int ptNo)throws SQLException{
		ArrayList<MemCommentViewBean> alist=
		 (ArrayList<MemCommentViewBean>)sqlMap.queryForList("getComments", ptNo);
		return alist;
	}//alist; commentList
*/	
	public ArrayList<MemCommentViewBean> getCmList(int ptNo)throws SQLException{
		ArrayList<MemCommentViewBean> alist= null;
			try{
				 alist = (ArrayList<MemCommentViewBean>)sqlMap.queryForList("getComments", ptNo);
				 System.out.println("코멘트출력성공DAO"+alist.size());
			}catch(SQLException e){
				System.out.println("코멘트출력실패");
				e.printStackTrace();
			}
		return alist;
	}//alist; commentList
	
	//////트랜젝션
	public int upCmLike(CmLikeBean clBean)throws SQLException{
		int n=0;
		try{
			sqlMap.startTransaction();
		
			//merge 구문
			n=(Integer)sqlMap.update("cmLikeMerge", clBean);
			
			//clNo clBean을받아서 아래 업데이트에 주입하려했으나 무조건 업데이트되는 현상 발생
			
			/*clBean.setClNo(clBean.getClNo());
			System.out.println("clNo=!"+clBean);*/
			
			//like update가 무조건 업데이트하는것을 방지하기위해 if문 적용
			if(n == 1){
			//commentLike 업데이트
			sqlMap.update("upCommentLikeTran", clBean);
			}
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			System.out.println("!트랜젝션 성공 commentUpdate(commentsTable) clBean="+clBean);
			
		}catch(SQLException e){
			System.out.println("업데이트 실패 Key="+n+", clBean="+clBean);
			e.printStackTrace();
		}finally{
			sqlMap.endTransaction();
		}
		
		return n;
	}
		//코멘트 좋아요 로그 1
		public ArrayList<CmLikeBean> getCmLog(int ptNo) throws SQLException{
			ArrayList<CmLikeBean> cmlikeList = (ArrayList<CmLikeBean>)sqlMap.queryForList("getCmLikeList", ptNo);
			return cmlikeList;
		}//cmLikeList(추천한 유저 로그 리스트 출력)
		
		//코멘트 좋아요 로그 2 http://hedging.tistory.com/13
		public ArrayList<CmLikeBean> getclike(Map<String, Object> clogMap) throws SQLException{
			return (ArrayList<CmLikeBean>)sqlMap.queryForList("getCmlog", clogMap);
		}//
		
	public CommentsBean getComment(int commentNo) throws SQLException{
		CommentsBean cmBean = null;
			cmBean=(CommentsBean)sqlMap.queryForObject("getComment", commentNo);
		return cmBean;
	} //미리 써놓은 코드로 이후 필요없어짐 삭제 대상
	
//리플입력	트랜잭션(sortNo 증가)
	public int insertTrply(CommentsBean cmBean) throws SQLException{
		//업데이트(기존sortNo+1), 자신의 sortNo+1 , 리플insert
		//업데이트를 않할경우 최초원본글 리플 클릭시 sortNo가 0이기때문에 +1만할경우 sortNo가중복이 될수 있음 정렬을regdate로해도되지만 이방법 적용
		int key = 0;
		int commentNo = cmBean.getCommentNo();
		
		try{
			sqlMap.startTransaction();
			
			//sortNo 상황별 증가
			//리플달 글이 원본글(첫 리플sortNo=0) 일경우(form에서 리플달 원본 sortNo를넘김 ) => 제일 마지막으로 이동
			/*if(cmBean.getSortNo()==0){
				int cnt = (Integer)sqlMap.queryForObject("trplCNT", commentNo); //갯수구하기 쿼리
				System.out.println("리플갯수 추출성공 cnt="+cnt+", commentNo="+commentNo);
				cmBean.setSortNo(cmBean.getSortNo()+cnt+1);//구한 갯수를 sortNo에더하기
			}else{
				//리플달 글이 첫 리플이 아닐경우 받아온 리플대상 sortNo보다 높은 sortNo=+1 이후 대상 sortNo +1해서 자신리플 입력
				int n = sqlMap.update("rplSNUpdate", cmBean);//리플대상보다 높은 sortNo +1씩 업데이트
				cmBean.setSortNo(cmBean.getSortNo()+1);//리플 대상 sortNo에서 +1해서 자신 입력
				cmBean.setStep(cmBean.getStep()+1);
				System.out.println("리플 업데이트 성공 n="+n);
			}*/
			
			if(cmBean.getSortNo()==0){
				int cnt = (Integer)sqlMap.queryForObject("trplCNT", commentNo); //갯수구하기 쿼리
				System.out.println("리플갯수 추출성공 cnt="+cnt+", commentNo="+commentNo);
				cmBean.setSortNo(cmBean.getSortNo()+cnt+1);//구한 갯수를 sortNo에더하기
				cmBean.setStep(cmBean.getStep()+1);
				
				int m = (Integer)sqlMap.queryForObject("sortG_cnt", cmBean);///sortNo는 업데이트로인해 변하므로 sortGroup이 중복될 수있음 갯수구함
				if(m == 0){
					cmBean.setSortGroup(cmBean.getSortNo());
				}else if(m > 0){
					int sg = (Integer)sqlMap.queryForObject("sortGroupCnt", commentNo);
					cmBean.setSortGroup(sg+1);
				}
				
				
			}else{
				//오름차순정렬 OR 내림차순정렬 조건문
					int v = (Integer)sqlMap.queryForObject("cmMIN", cmBean); //입력할 그룹의 첫번째 sortNo
					int b = (Integer)sqlMap.queryForObject("cmCNT", cmBean); //같은 그룹의 리플이 존재하는지
					System.out.println("cmMin="+v+", cmCnt="+b);
					
					if(v != cmBean.getSortNo() && b == 0 ){
						int n = sqlMap.update("rplSNDwndate", cmBean);//sortNo+1
						int u = sqlMap.update("upSortGroup", cmBean);///sortGroup+1
						
						cmBean.setSortNo(cmBean.getSortNo()+1);
						cmBean.setStep(cmBean.getStep()+1);
						cmBean.setSortGroup(cmBean.getSortGroup()+1);//리플이 달릴경우 해당 소트번호로 리플수만큼 중복세팅, 소트그룹 카운트+1
						
						System.out.println("업데이트 성공: 스텝 새로 추가+1 step="+cmBean.getStep());
					}else if(v != cmBean.getSortNo() && b > 0 ){
						cmBean.setSortGroup(cmBean.getSortGroup()+1);
						int n = sqlMap.update("rplSNUpdate", cmBean);
						int c = (Integer)sqlMap.queryForObject("sortMax", cmBean);///소트그룹 가장큰수 구한후 카운트+1
										
						/*cmBean.setSortNo(cmBean.getSortNo()+1);*/
						cmBean.setSortNo(c+1);
						cmBean.setStep(cmBean.getStep()+1);
						/*cmBean.setSortGroup(cmBean.getSortGroup()+1);*/
						System.out.println("업데이트 성공: 스텝 증가+1 step="+cmBean.getStep());
					}else {
											
				//오름차순정렬 OR 내림차순정렬 조건문 종료
				
				int n = sqlMap.update("rplSNUpdate", cmBean);
				int c = (Integer)sqlMap.queryForObject("sortMax", cmBean);///소트그룹 가장큰수 구한후 카운트+1
				
				cmBean.setSortNo(c+1);
				cmBean.setStep(cmBean.getStep()+1);
				cmBean.setSortGroup(cmBean.getSortGroup());//리플이 달릴경우 해당 소트번호로 리플수만큼 중복세팅, 소트그룹 카운트+1
				//업데이트
					
				System.out.println("오름차순 업데이트 성공 n="+n);
				}
			}
			key = (Integer)sqlMap.insert("insertTreply", cmBean);
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			
			System.out.println("리플트랜젝션성공key="+key+"cmBean="+cmBean);
		}catch(SQLException e){
			System.out.println("리플트랜젝션실패");
			e.printStackTrace();
		}finally{
			try{
				sqlMap.endTransaction();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return key;	
	}
	
//리플 삭제
	public int delCm(int commentNo) throws SQLException{
		int n=0;
		CommentsBean cmBean = new CommentsBean();
		try{
			sqlMap.startTransaction();
			cmBean = (CommentsBean) sqlMap.queryForObject("getComment", commentNo);
			System.out.println("cmBean1"+cmBean);
			
			int delS = sqlMap.update("rplSortN-", cmBean);
			int delG = sqlMap.update("sortGroup-", cmBean);
			
			int l = (Integer)sqlMap.delete("delCmLike", commentNo);//무결성 제약조건 clLike DB레코드먼저 지우기
			System.out.println("deleteCmLike"+l);
			
			n=(Integer)sqlMap.delete("deleteCm", commentNo);
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			
			System.out.println("리플 삭제 성공 n="+n+"delS:소트넘버="+delS+", delG="+delG);			
		}catch(SQLException e){
			System.out.println("리플 삭제 실패");
			e.printStackTrace();
		}finally{
			try{
				sqlMap.endTransaction();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return n;
	}
}///
