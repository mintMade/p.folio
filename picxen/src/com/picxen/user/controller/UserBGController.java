package com.picxen.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.user.model.UserMainService;

@Controller
public class UserBGController {
	private UserMainService umService;
	
	public UserBGController(){
		System.out.println("생성자:UserBGController");
	}
	
	public void setUmService(UserMainService umService){
		this.umService=umService;
		System.out.println("종속객체:UserBGController"+"umService");
	}
	
	@RequestMapping("/user/user/userBG.do")
	public ModelAndView uploadUserBG(HttpServletRequest request, int bgNo, String userid){
		System.out.println("userBG 파라미터:bgNo="+bgNo+", userid="+userid);
	
		//db
		String sort = request.getParameter("sort");
		
		int result=0;
		try{
			//사진번호 하나만 갖고 업로더로 업데이트가능 그러나 다른사람도 업데이트하게 해주려면 확장성을 고려해야하기 때문에 유저 아이디도 받게함 파라미터를 MAP으로 처리
			//bgMap의 bgNo와 userid가 같은 테이블레코드에 있지않아 맵으로 처리
			Map<String, Object> bgMap = new HashMap<String, Object>();
			bgMap.put("bgNo", bgNo);
			bgMap.put("userid", userid);
			System.out.println("bgMap파라미터="+bgMap);
			result = umService.upUserBG(bgMap);
			
			System.out.println("result= "+result);
			
		}catch(SQLException e){
			System.out.println("유저 BG 변경 실패");
			e.printStackTrace();
		}
		
		int ptNo = bgNo;//photodetailController 에 넘겨줄 사진번호
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptNo", ptNo);
		mav.addObject("sort", sort);
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		return mav;
	}
}//
