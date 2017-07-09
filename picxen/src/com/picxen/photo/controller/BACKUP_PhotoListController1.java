package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.PagingBean;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class BACKUP_PhotoListController1 {
	private PhotoService ptService;
	
	public BACKUP_PhotoListController1(){
		System.out.println("생성자:PhotoListController");
	}
		
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("종속객체 주입:PhotoListController"+"-setPhotoListService()");
	}
	
	//카테고리필터 검색
	@RequestMapping("/photo/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String cgName){
		//파라미터
		System.out.println("파라미터:카테고리 이름="+cgName);
		
		//db작업
		ArrayList<PhotoBean> alist = null;
		
		try{
			if(cgName != null && !cgName.isEmpty()){
				//이벤트별 상품 조회
				alist = ptService.listPhotoByCategory(cgName);
			}else{
				//전체 상품 조회
				alist = ptService.ptlistPop();
			}
			System.out.println("사진 리스트 로딩 성공:기본인기사진 리스트 사이즈"+alist.size());
		}catch(SQLException e){
			System.out.println("사진리스트 로딩 실패"+alist);
			e.printStackTrace();
		}
		
		//결과 뷰
		//페이징
		int pageSize=5;
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/photoList");
		
		return mav;
	}
	
	
	//카테고리 리스트
	/*@requestMapping("")*/
	
	
	
/*	@RequestMapping("/photo/photo/photoByCategory.do")
	public ModelAndView ptList(int cgNo){
		//파라미터
		System.out.println("cgNo="+cgNo);
		
		//2.db작업
		ArrayList<PhotoBean> alist=null;
		try{
			alist = ptService.listPhotoByCategory(cgNo);
			System.out.println("카테고리별 사진 조회 성공+alist.size()"+alist.size()
					+"cgNo"+cgNo);
		}catch(SQLException e){
			System.out.println("카테고리별 사진 조회 실패@");
			e.printStackTrace();
		}
		
		
		//결과, 뷰페이지
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/photoByCategory");
		
		return mav;
	}*/////페이지 사이드에 카테고리bar 카테고리별 목록
	
	//폐기 어드민
	/*@RequestMapping("/photo/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String eventName){
		//이벤트상품 기반 전체사진 조회
		//1.파라미터
		
		//db작업-select
		ArrayList<PhotoBean> alist=null;
		try{
			if(eventName != null && !eventName.isEmpty()){
				
			//이벤트별 상품 조회
			alist=(ArrayList<PhotoBean>)ptService.listPhotoByEvent(eventName);
			}else{
				
			//전체 상품 조회
			alist=ptService.listPhotoAll();
			}
			System.out.println("상품 목록 조회 성공, alist.size()="+alist.size());
		}catch(SQLException e){
			System.out.println("사진 조회 실패");
			e.printStackTrace();
		}
	
	//3.결과.뷰페이지
	int pageSize=5;
	int blockSize=10;
	PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
	ModelAndView mav = new ModelAndView();
	mav.addObject("alist", alist);
	mav.addObject("totalRecord", alist.size());
	mav.addObject("pb", pb);
	
	mav.setViewName("/photo/photo/photoList");
	
	return mav;
	}*/
	
/*	@RequestMapping("/photo/photo/searchResult.do")
	public ModelAndView searchPhoto(String ptName, String ptName2, String pageSize,
			HttpServletRequest request){
		
		//1. 파라미터
		System.out.println("사진 이름 ptName"+ptName+"ptName2"+ptName2);
		
		//2.db작업
		ArrayList<PhotoBean> alist = null;
		
		Map<String, String>ptMap = new HashMap<String, String>();
		ptMap.put("ptName", ptName);
		ptMap.put("ptName2", ptName2);
		try{
			alist = ptService.searchPhoto(ptMap);
			System.out.println("사진 검색 성공,alist.size"+alist.size());
		}catch(SQLException e){
			System.out.println("사진 검색 실패");
			e.printStackTrace();
		}
		
		//결과뷰페이지
		int iPageSize=5;
		if(pageSize!=null && !pageSize.isEmpty()){
			iPageSize=Integer.parseInt(pageSize);
		}
		int blockSize=10;
		PagingBean pb 
		= new PagingBean(request, alist, iPageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptList", alist);
		mav.addObject("pb", pb);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/searchResult");
		
		return mav;
	}*/

	
	
	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String tag, String pageSize, HttpServletRequest request){
		//파라미터
		System.out.println("검색파라미터 tag+"+tag+"pageSize"+pageSize);
		
		//db작업
		ArrayList <PhotoBean> alist= null;
		
		try{
			if(tag != null && !tag.isEmpty()){
				//태그및 타이틀 기반 검색
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(tag);
			}else{
				//모두 검색
				alist = (ArrayList<PhotoBean>)ptService.listPhotoAll();
			}
			System.out.println("사진 검색 성공(tag, 타이틀 기반)");
		}catch(Exception e){
			System.out.println("사진 검색 실패(tag, 타이틀 기반)");
			e.printStackTrace();
		}
		//결과 뷰페이지
		int iPageSize=5;
		if(pageSize != null && !pageSize.isEmpty()){
			iPageSize=Integer.parseInt(pageSize);
		}
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, iPageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptTagList", alist);
		mav.addObject("pb", pb);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/ptSearchResult");
		
		return mav;
	}
	
}/////////
