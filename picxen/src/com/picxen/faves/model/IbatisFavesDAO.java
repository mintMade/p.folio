package com.picxen.faves.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.config.SqlMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.MemberViewBean;
import com.picxen.photo.model.PhotoLikeBean;

public class IbatisFavesDAO implements FavesDAO{//implements ������ �� dispatcher�� dao����, no matching editors...
	//�������
	public SqlMapClient sqlMap;
	
	public IbatisFavesDAO(){
		SqlMapConfigManager sqlMapConfig = SqlMapConfigManager.getInstance();
		sqlMap=sqlMapConfig.getSqlMapClient();
		System.out.println("������:IbatisFavesDAO");
	}
	
	@Override
	public int insertFaves(FavesBean fvBean) throws SQLException{ //like�� photoDAO������
		int key=0, n=0;
		
		try{
			sqlMap.startTransaction();
			
			/*key =(Integer)sqlMap.insert("favesInsert", fvBean);
			System.out.println("nnnn="+n);
			//insert�� Ű ���� faveNo�� photos table�� update�ǵ��� �Ѱ���� ��
			fvBean.setFaveNo(key);
			n=sqlMap.update("updateFaves", fvBean);*/
			
			n=sqlMap.update("updateFaves", fvBean);
			//insert�� Ű ���� faveNo�� photos table�� update�ǵ��� �Ѱ���� ��
			if( n == 1){
				key =(Integer)sqlMap.insert("favesInsert", fvBean);
			}
									
			sqlMap.queryForObject("prcdFaves_pop", fvBean);//���ν���
			
			sqlMap.executeBatch();
			sqlMap.commitTransaction();
			System.out.println("!Ʈ�����Ǽ��� fave insert=>favesTable, update=>photosTable, pop���ν��� fvBean="+fvBean+", key="+key+", n="+n);
			
		}catch(SQLException e){
			System.out.println("���ã�� �߰�/ptTable faves������Ʈ ���� key="+key+", n="+n+", fvBean="+fvBean);
			e.printStackTrace();
		}finally{
			sqlMap.endTransaction();
		}
		
		return key;
	}//insertFaves()
	
	//faveList for ptDetail page ���� ����Ʈ �˻� �� ����Ʈ�� ���
	@Override
	public List<MemberViewBean> getFaveList(int ptNo) throws SQLException{
		List<MemberViewBean> fList = null;
		
		fList=(List<MemberViewBean>)sqlMap.queryForList("favesList", ptNo);
		return fList;
	}
	
	//�ش� �������δ� �����ϱ�
	@Override
	public String getUploader(int ptNo) throws SQLException{
		String uploader = null;
		
		uploader = (String)sqlMap.queryForObject("getUloader", ptNo);
		return uploader;
	}

	@Override//icon ������ ������� ���̵������� ������ �����Ͽ� ���
	public List<MemberViewBean> getLikeList (int ptNo) throws SQLException{
		List<MemberViewBean> lList 
			= (List<MemberViewBean>)sqlMap.queryForList("getLikeIconList", ptNo);
		
		return lList;
	}
}//
