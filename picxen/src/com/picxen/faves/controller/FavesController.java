package com.picxen.faves.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.MemberViewBean;
import com.picxen.faves.model.FavesBean;
import com.picxen.faves.model.FavesService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoLikeBean;

@Controller
public class FavesController {
	private FavesService fvService;
	/*private PhotoService ptService;*/
	
	public void setFvService(FavesService fvService){
		this.fvService=fvService;
	}
	
	/*public void setPtService(PhotoService ptService){
		this.ptService=ptService;
	}*/
	
	@RequestMapping("/photo/faves/favesAdd.do")
	public ModelAndView insertFaves(HttpServletRequest request, HttpSession session/*,*/ /*String fUserId,*//* int photoNo,*/ /*FavesBean fvBean*//*, String sort*/){
		//파라미터
		System.out.println("FavesController:fUserId="/*+*//*fUserId+*//*", photoNo="+photoNo+*//*"fvBean"+fvBean*//*+", sort="+sort*/);
		
		ModelAndView mav = new ModelAndView();
		String userid= (String) session.getAttribute("userid");
		if(userid==null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("inc/message");
			
			return mav;
		}
		
		//db작업
		/*FavesBean fvBean = new FavesBean(); 즐겨찾기 추가 전에 로그인서트 후 즐겨찾기 카운트업데이트하는 방식*/
		String strPtNo = (String)request.getParameter("ptNo");
		Integer ptNo = Integer.parseInt(strPtNo);
		System.out.println("ptNo="+ptNo);
		
		FavesBean fvBean = new FavesBean();
		fvBean.setfUserId(userid);
		fvBean.setPhotoNo(ptNo);
		PhotoBean ptBean = new PhotoBean();
		System.out.println("fvBBean="+fvBean);
		try{
			ptBean = fvService.insertFaves(fvBean);
			System.out.println("즐겨찾기 추가 성공, fvBean="+fvBean+", ptBean="+ptBean);
		}catch(SQLException e){
			System.out.println("즐겨찾기 추가 실패");
			e.printStackTrace();
		}
		//결과뷰페이지
		mav.addObject("userid", userid);//detail.do로 리셋할때 유저가 해당사진의 like를 찍었는지 조회하기 위해 유저 아이디를 파라미터로 넘김 viewCntController와 같음
		/*mav.addObject("sort", sort);*/
		mav.addObject("sinkBean", ptBean);
		mav.setViewName("/photo/faves/favesAdd");//즐겨찾기 클릭후 리셋되는 주소, 파라미터 ajax로 변경
		return mav;
	}
	
	//애정 유저이미지리스트 및 링크컨트롤러
	@RequestMapping("/photo/faves/faveList.do")
	public ModelAndView listByfaves(int ptNo, HttpServletRequest request){
		System.out.println("faveList애정 컨트롤러 파라미터"+ptNo);
		List<MemberViewBean> fList = null;
		List<MemberViewBean> lList = null;
		String uploader = null;//uploader;
		
		try{
			fList = fvService.getFaveList(ptNo);
			lList = fvService.getLikeList(ptNo);
			uploader = fvService.getUploader(ptNo);
			System.out.println("애정 리스트 로딩 성공"+fList.size()+", 업로더="+uploader);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("애정 리스트 로딩 실패");
		}
		request.setAttribute("uploader", uploader);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("faveList", fList);
		mav.addObject("faveSize", fList.size());
		mav.addObject("likeList", lList);
		mav.addObject("likeSize", lList.size());
		mav.setViewName("/photo/faves/faveList");
		
		return mav;
	}
}//
