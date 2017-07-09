package com.picxen.search.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.photo.model.PhotoBean;
import com.picxen.search.model.SearchService;

@Controller
public class SearchPhotoController {
	private SearchService srchService;
	
	/*public SearchPhotoController(){
		System.out.println("생성자:SearchPhotoController");
	}*/

	//setter
	public void setSrchService(SearchService srchService){
		this.srchService = srchService;
	}
	
	//사진 검색
	@RequestMapping("/photo/SearchPtRslt.do")
	public ModelAndView searchPtResult(String cgName, String kword, String tag, String userid,  PhotoBean ptBean){
		System.out.println("검색파라미터:cgName,kword,id, ptBean");
		
		ArrayList<PhotoBean> searchList = null;
		
		try{
			searchList = srchService.resultPhotoBySearch(ptBean);
			System.out.println("검색 성공:searchList="+searchList.size());			
		}catch(SQLException e){
			System.out.println("사진검색 실패"+searchList);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultPt", searchList);
		mav.addObject("resultPhotoSize", searchList.size());
		mav.setViewName("/photo/searchPtRslt");
		
		return mav;
	}
	
	
	
}//
