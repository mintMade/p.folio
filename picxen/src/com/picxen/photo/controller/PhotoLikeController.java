package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.member.model.MemberBean;
import com.picxen.photo.model.PhotoLikeBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoLikeController {
	private PhotoService ptService;
	
	public PhotoLikeController(){
		System.out.println("생성자:PhotoLikeController");
	}
	
	//setter
	public void setPhotoService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("종속객체 주입:PhotoService"+", setPhotoService()");
	}
	
/*	@RequestMapping(value="/photo/photo/photoLikeUpdate.do", method=RequestMethod.GET)
	public ModelAndView photoLikeListGet(PhotoLikeBean plBean, HttpSession session, String userid, int ptNo){
		//파라미터
		System.out.println("유저아이디="+userid+"ptNo="+ptNo);
		
		ModelAndView mav=new ModelAndView();
		String userid = (String)session.getAttribute("userid");
		System.out.println("유저아이디(userid체크)"+userid);
		if(userid==null||userid.isEmpty()){
			mav.addObject("msg", "로그인먼저 하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
		}
				
		return mav;
		
		
		//2db작업
		boolean result=false;
		
		try{
			result=ptService.listPhotoLike(plBean);
			System.out.println("유저 photoLike 조회 성공, result="+result);
		}catch(SQLException e){
			System.out.println("photoLike 조회 실패");
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("likeCnt", result);
		mav.setViewName("redirect:/photo/photo/photoDetail.do?ptNo="+ptNo);
		
		return mav;
	}*/
	
	
	@RequestMapping("/photo/photo/photoLikeUpdate.do")
	public ModelAndView updateLike(HttpServletRequest request, HttpSession session, MemberBean mbBean, 
			PhotoLikeBean plBean, int ptNo, String userid, String sort){
		//파라미터
		//사진 정보의 추천수 업데이트
		//추천 테이블에 db입력
		System.out.println("PhotoLike파라미터:ptNo="+ptNo+"userid="+userid+", sort"+sort);
		
		ModelAndView mav = new ModelAndView();
		userid = (String)session.getAttribute("userid");
		System.out.println("userid(로그인체크)"+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		
		int key=0;
		
		try{
			key=ptService.insertLike(plBean);
			System.out.println("like증가 성공"+key+"pLikeBean"+plBean);
			
			/*n=ptService.likeUpdate(plBean);
			System.out.println("like업데이트 성공"+ptNo+"n="+n);트랜젝션으로 대체*/
		}catch(SQLException e){
			System.out.println("like업데이트, 증가 실패");
			e.printStackTrace();
		}
		
		//결과뷰페이지

		mav.addObject("userid", userid);//detail.do로 리셋할 때 유저가 해당사진의 like를 찍었는지 조회하기 위해 유저 아이디를 파라미터로 넘김(!이후 sql문에서 처리했음)
		mav.addObject("sort", sort);
		mav.setViewName("redirect:/photo/photo/photoDetail.do?ptNo="+ptNo);
		return mav;
	}
	
}///