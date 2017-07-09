package com.picxen.photo.model;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
	private CategoryDAO cgDao;
	
	public CategoryService(){
		System.out.println("»ý¼ºÀÚ:CategoryService");
	}
	
	//setter
	public void setCgDao(CategoryDAO cgDao){
		this.cgDao=cgDao;
	}
	
	public List<CategoryBean> listCategoryAll() throws SQLException{
		return cgDao.listCategoryAll();
	}
	
	
}//
