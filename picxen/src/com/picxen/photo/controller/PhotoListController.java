package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.PagingBean;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
public class PhotoListController {
	private PhotoService ptService;
	
	public PhotoListController(){
		System.out.println("생성자:PhotoListController");
	}
		
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("종속객체 주입:PhotoListController"+"-setPhotoListService()");
	}
	
		
	//카테고리필터 검색 //출력방식 변경으로 삭제예정
	@RequestMapping("/photo/photo/photoLists.do")//("/photo/photo/photoList.do") loot변경
	public ModelAndView ptList(HttpServletRequest request, String cgName, String popName, 
			String newName, String upcomName){
		//파라미터
		System.out.println("파라미터:카테고리 이름1="+cgName+", 인기순="+popName+", 새로운순="+newName
				+", 뜨는순="+upcomName);
		
		//db작업
		ArrayList<PhotoBean> alist = null;
		
		try{
			if(cgName != null && !cgName.isEmpty()){
				//이벤트별 상품 조회
				alist = ptService.listPhotoByCategory(cgName); //삭제 대상 xml존재하지 않음
			}else {
				//전체 상품 조회
				alist = ptService.ptlistPop();
			}
			System.out.println("사진 리스트 로딩 성공:기본인기사진 리스트 사이즈"+alist.size());
		}catch(SQLException e){
			System.out.println("카테고리 사진리스트 로딩 실패"+alist);
			e.printStackTrace();
		}
		
		//결과 뷰
		//페이징
		int pageSize=40;
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/photoLists");
		
		return mav;
	}//출력방식 변경으로 삭제예정
	
	@RequestMapping("/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String cgName, String ftName){
			List<PhotoBean> ptList = null;

			CategoryBean cgBean = new CategoryBean();
			//인덱스 및 메뉴에서 진입 시 order 카테고리 파라미터 없을 경우 if문 진행 
			if(ftName==null || ftName.isEmpty()){
				cgBean.setFilterName("pop");
			}
			cgBean.setFilterName(ftName);
			cgBean.setCategoryName(cgName);
			
			System.out.println("cgBean=="+cgBean);
			
				try{
					//"모두보기"는 sql db에 데이터가 없음. db를 수정해서 넣을 수도 있지만, 안넣고 해보는걸로 처리
					//select 조건에 파라미터 없이 정렬방식만 변경해서 파라미터를 ftName만 받고 order형식만 틀리게
					if("모두보기".equals(cgName)){
						ptList = ptService.listPhotoByAllView(cgBean);
					}else if(cgName != null && !cgName.isEmpty()){
						//cgName을 파라미터로 검색
						ptList = ptService.listPhotoByCategory(cgBean);
					}else{
						ptList = ptService.listPhotoByAllView(cgBean);
					}
					
					System.out.println("filterList출력 성공"+ptList.size());
				}catch(SQLException e){
					System.out.println("filterList출력 실패"+ptList);
					e.printStackTrace();
				}
				
				//제외 예정 기능(페이징)
				int pageSize=40;
				int blockSize=10;
				/*PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);*/
				PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);
		
				ModelAndView mav = new ModelAndView();
				mav.addObject("alist", ptList);
				mav.addObject("totalRecord", ptList.size());
				mav.addObject("pb", pb); //제외 예정 파라미터(페이징)
				mav.setViewName("/photo/photoList");
		
		return mav;
	}
	
/*  //출력방식 변경으로 삭제 예정	
	//인기 사진 검색
	@RequestMapping("/photo/photo/popular.do")
	public ModelAndView ptPopList(HttpServletRequest request, String cgName){
	System.out.println("인기사진 카테고리="+cgName);
	ArrayList<PhotoBean> alist = null;
	
	try{
		if("모두보기".equals(cgName)){
			alist=ptService.listPhotoByPop();	
		}else if(cgName != null && !cgName.isEmpty()){
			alist=ptService.listPhotoByCtPop(cgName);
		}else{
			alist=ptService.listPhotoByPop();
		}
		System.out.println("인기 사진 로딩 성공="+alist.size());
	}catch(SQLException e){
		System.out.println("인기 사진 로딩 실패"+alist);
		e.printStackTrace();
	}
	
	int pageSize=10;
	int blockSize=10;
	PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
	System.out.println("pb="+pb);
	
	ModelAndView mav = new ModelAndView();
	mav.addObject("alist", alist);
	mav.addObject("cgName", cgName);
	mav.addObject("totalRecord", alist.size());
	mav.addObject("pb", pb);
	mav.setViewName("/photo/photo/popular");
	
	return mav;
	}
	
	//새로운 사진 검색
	@RequestMapping("/photo/photo/new.do")
	public ModelAndView ptNewList(HttpServletRequest request, String cgName){
		System.out.println("새로운 사진 카테고리 파라미터"+cgName);
		ArrayList<PhotoBean> alist=null;
		try{
			if("모두보기".equals(cgName)){
				alist=ptService.listPhotoByNew();	
			}else if(cgName != null && !cgName.isEmpty()){
				alist=ptService.listPhotoByCtNew(cgName);
			}else{
				alist=ptService.listPhotoByNew();
			}
			System.out.println("새로운사진 로딩 성공"+alist.size());
		}catch(SQLException e){
			System.out.println("새로운사진 로딩실패"+alist);
			e.printStackTrace();
		}
		int pageSize = 40;
		int blockSize = 10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("cgName", cgName);
		mav.addObject("totalRocord", alist.size());
		mav.addObject("pb",pb);
		mav.setViewName("/photo/photo/new");
		
		return mav;
	}
	
	
	//뜨고있는 사진 검색
	@RequestMapping("/photo/photo/upcoming.do")
	public ModelAndView ptUpcomList(HttpServletRequest request, String cgName){
		System.out.println("뜨는사진파라미터"+cgName);
		ArrayList<PhotoBean> alist = null;
		try{
			if("모두보기".equals(cgName)){
				alist=ptService.listPhotoByUpCom();
			}else if(cgName != null && !cgName.isEmpty()){
				alist=ptService.listPhotoByCtUpcom(cgName);
			}else{
				alist=ptService.listPhotoByUpCom();
			}
			System.out.println("뜨는 사진 로딩 성공"+alist.size());
		}catch(SQLException e){
			System.out.println("뜨는 사진 로딩 실패"+alist);
			e.printStackTrace();
		}
		
		int pageSize = 40;
		int blockSize =10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("cgName", cgName);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/upcoming");
		return mav;
	}*/
	

	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String kword, String pageSize, HttpServletRequest request){
		//파라미터
		System.out.println("검색파라미터 tag+"+kword+"pageSize"+pageSize);
		
		//db작업
		ArrayList <PhotoBean> alist= null;
		
		try{
			if(kword != null && !kword.isEmpty()){
				//태그및 타이틀 기반 검색
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(kword);
			}else{
				//모두 검색
				alist = (ArrayList<PhotoBean>)ptService.listPhotoAll();
			}
			System.out.println("사진 검색 성공(tag, 타이틀 기반)="+alist);
		}catch(Exception e){
			System.out.println("사진 검색 실패(tag, 타이틀 기반)="+alist.size());
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
