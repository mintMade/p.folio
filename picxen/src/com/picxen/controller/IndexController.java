package com.picxen.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class IndexController {
	private PhotoService ptService;
	
	public IndexController(){
		System.out.println("생성자:IndexController");
	}
	
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("종속객체주입:IndexController"+"setPtService()");
	}
	
	@RequestMapping("/index.do")
	public ModelAndView getIndex(){
		System.out.println("메인()index 페이지:getIndex()");
		
		//index.jsp 사용안함 폐기대상
		//[2]이벤트별 상품목록 조회
		ArrayList<PhotoBean>newList=new ArrayList<PhotoBean>();
		ArrayList<PhotoBean>bestList=new ArrayList<PhotoBean>();
		ArrayList<PhotoBean>mdList=new ArrayList<PhotoBean>();
		
		try{
			newList=ptService.listPhotoByEvent("NEW");
			bestList=ptService.listPhotoByEvent("BEST");
			mdList=ptService.listPhotoByEvent("MD");
			System.out.println("이벤트별 사진목록 조회성공,newList.size()="
					+newList.size()+"bestList.size()="+bestList.size()+
					"mdList.size()="+mdList.size());
		}catch(SQLException e){
			System.out.println("이벤트 사진목록 조회실패");
			e.printStackTrace();
		}
		
		List<PhotoBean> alist = null;
		
		try{
			CategoryBean cgBean = new CategoryBean();
			/*alist=ptService.listPhotoAll();*/
			alist=ptService.listPhotoByAllView(cgBean);
			System.out.println("메인 사진 리스트 로딩 성공");
		}catch(SQLException e){
			System.out.println("메인 사진 리스트 로딩 실패");
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("newList", newList );
		mav.addObject("bestList", bestList);
		mav.addObject("mdList", mdList);
		mav.addObject("alist", alist);
		
		mav.setViewName("/index");
		
		return mav;
	}//
	
}////
