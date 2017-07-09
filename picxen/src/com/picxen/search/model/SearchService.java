package com.picxen.search.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.picxen.photo.model.PhotoBean;

public class SearchService {
	private SearchDAO searchDao;
	
	public SearchService(){
		System.out.println("»ý¼ºÀÚ:SearchService");
	}

	public ArrayList<PhotoBean> resultPhotoBySearch(PhotoBean ptBean) throws SQLException {
		return searchDao.resultPhotoBySearch(ptBean);
	}//result photo by search
}//
