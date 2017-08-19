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
import com.picxen.photo.model.PhotoBean;
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
/*		System.out.println("종속객체 주입:PhotoService"+", setPhotoService()");*/
	}
	
	@RequestMapping("/photo/photo/photoLikeUpdate.do")
	public ModelAndView updateLike(HttpServletRequest request, HttpSession session){
		//파라미터
		//사진 정보의 추천수 업데이트
		//추천 테이블에 db입력
		
		String strPtNo = (String)request.getParameter("ptNo");
		Integer ptNo = Integer.parseInt(strPtNo);
		/*System.out.println("PhotoLike파라미터:ptNo="+ptNo);*/
		
		ModelAndView mav = new ModelAndView();
		
		//set Parameter
		String userid = (String)session.getAttribute("userid");
		PhotoLikeBean plBean = new PhotoLikeBean();
		plBean.setPtNo(ptNo);
		plBean.setUserid(userid);
		
		PhotoBean ptBean = new PhotoBean();
		try{
			ptBean=ptService.insertLike(plBean);
			/*System.out.println("like증가 성공"+key+"pLikeBean"+plBean+", ptBean="+ptBean);*/
			
		}catch(SQLException e){
			System.out.println("like업데이트, 증가 실패");
			e.printStackTrace();
		}
		
		//결과뷰페이지
		mav.addObject("userid", userid);//detail.do로 리셋할 때 유저가 해당사진의 like를 찍었는지 조회하기 위해 유저 아이디를 파라미터로 넘김(!이후 sql문에서 처리했음)
		mav.addObject("sinkBean", ptBean); //사진 스코어 및 좋아요카운트 리셋 parameter Bean
		mav.setViewName("/photo/faves/favesAdd");
		return mav;
	}
	
}///