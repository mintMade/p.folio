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
import org.springframework.web.bind.annotation.RequestMethod;
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

	//리스트 출력 방식 변경 (이전버전은 카테고리별로 메소드가 많았으나 통합으로 처리)
	@RequestMapping(value="/photo/photoList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ptList(HttpServletRequest request, String cgName, String sort){
			List<PhotoBean> ptList = null;
			
			CategoryBean cgBean = new CategoryBean();
			//인덱스 및 메뉴에서 진입 시 order 카테고리 파라미터 없을 경우 if문 진행 
			if(sort==null || sort.isEmpty()){
				cgBean.setSort("pop");
			}
			cgBean.setSort(sort);
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
					
					System.out.println("ptList출력 성공"+ptList.size());
				}catch(SQLException e){
					System.out.println("ptList출력 실패");
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
				mav.addObject("cgName", cgName);//테스트
				mav.addObject("sort", sort);
				mav.setViewName("/photo/photoList");
		
		return mav;
	}

	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String kword, String pageSize, HttpServletRequest request){
		//파라미터
		System.out.println("검색파라미터 tag+"+kword+"pageSize"+pageSize);
		
		//db작업
		ArrayList <PhotoBean> alist= null;
		CategoryBean cgBean = new CategoryBean();
		
		try{
			if(kword != null && !kword.isEmpty()){
				//태그및 타이틀 기반 검색
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(kword);
			}else{
				//모두 검색
				alist = (ArrayList<PhotoBean>)ptService.listPhotoByAllView(cgBean);
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
	
}//class
