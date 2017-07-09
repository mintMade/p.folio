package com.picxen.faves.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.MemberViewBean;
import com.picxen.photo.model.PhotoLikeBean;

public class IbatisFavesDAO implements FavesDAO{//implements 미적용 시 dispatcher의 dao에러, no matching editors...
	//멤버변수
	public SqlMapClient sqlMap;
	
	public IbatisFavesDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		System.out.println("생성자:IbatisFavesDAO");
	}
	
	@Override
	public int insertFaves(FavesBean fvBean) throws SQLException{ //like는 photoDAO에있음
		int key=0, n=0;
		
		try{
			sqlMap.startTransaction();
			
			/*key =(Integer)sqlMap.insert("favesInsert", fvBean);
			System.out.println("nnnn="+n);
			//insert된 키 값인 faveNo를 photos table에 update되도록 넘겨줘야 함
			fvBean.setFaveNo(key);
			n=sqlMap.update("updateFaves", fvBean);*/
			
			n=sqlMap.update("updateFaves", fvBean);
			//insert된 키 값인 faveNo를 photos table에 update되도록 넘겨줘야 함
			if( n == 1){
				key =(Integer)sqlMap.insert("favesInsert", fvBean);
			}
									
			sqlMap.queryForObject("prcdFaves_pop", fvBean);//프로시져
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			System.out.println("!트랜젝션성공 fave insert=>favesTable, update=>photosTable, pop프로시져 fvBean="+fvBean+", key="+key+", n="+n);
			
		}catch(SQLException e){
			System.out.println("즐겨찾기 추가/ptTable faves업데이트 실패 key="+key+", n="+n+", fvBean="+fvBean);
			e.printStackTrace();
		}finally{
			sqlMap.endTransaction();
		}
		
		return key;
	}//insertFaves()
	
	//faveList for ptDetail page 애정 리스트 검색 후 리스트에 담기
	@Override
	public List<MemberViewBean> getFaveList(int ptNo) throws SQLException{
		List<MemberViewBean> fList = null;
		
		fList=(List<MemberViewBean>)sqlMap.queryForList("favesList", ptNo);
		return fList;
	}
	
	//해당 사진업로더 추출하기
	@Override
	public String getUploader(int ptNo) throws SQLException{
		String uploader = null;
		
		uploader = (String)sqlMap.queryForObject("getUloader", ptNo);
		return uploader;
	}

	@Override//icon 정보를 얻기위해 아이디정보로 멤버뷰빈 연동하여 출력
	public List<MemberViewBean> getLikeList (int ptNo) throws SQLException{
		List<MemberViewBean> lList 
			= (List<MemberViewBean>)sqlMap.queryForList("getLikeIconList", ptNo);
		
		return lList;
	}
}//
