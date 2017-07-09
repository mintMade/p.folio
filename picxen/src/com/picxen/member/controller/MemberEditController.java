package com.picxen.member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.Utility;
import com.picxen.member.model.MemberBean;
import com.picxen.member.model.MemberService;

@Controller
public class MemberEditController {
	private MemberService mbService;
	
	public MemberEditController(){
		System.out.println("생성자 MemberEditController()");
	}
	
	public void setMemberService(MemberService mbService){
		this.mbService=mbService;
		System.out.println("종속객체 주입:MemberService"+"MemberEditController()");
	}
	
	
	@RequestMapping(value="/member/settings.do", method=RequestMethod.GET)
	public ModelAndView editMeGet(HttpSession session, String userid, MemberBean mbBean){
		//파라미터
		System.out.println("memberEditController: userid"+userid+"MemberBean"+mbBean);
		
		//db
		try{
			mbBean = mbService.viewMyInfo(userid);
			System.out.println("내 정보 출력 성공 userid="+userid+", mbBean="+mbBean);
		}catch(SQLException e){
			System.out.println("회원정보 출력 실패");
			e.printStackTrace();
		}
		
		//뷰페이지
		ModelAndView mav = new ModelAndView();
		//로그인여부:쿠키시간 상실 예외처리
		userid = (String)session.getAttribute("userid");
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요.");
			mav.addObject("url", "../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		
		mav.addObject("myInfo", mbBean);
		mav.setViewName("/member/settings.do?userid="+userid);
		return mav;
	}
	
	@RequestMapping(value="/member/settings.do", method=RequestMethod.POST)
	public ModelAndView editMePost(){
		//파라미터
		MemberBean mbBean=null;
		String userid=null;
		
		//파일 업로드 처리
		//사용자가 업로드하려고 선택한 파일-임시파일 형태
		MultipartFile tempFile = mbBean.getUploadIcon();
		
		//선택한 파일의 이름
		String oName=tempFile.getOriginalFilename();
		
		//업로드 경로-실제 물리적인 경로 구하기
		//String upPath = "/pt_images";
		//upPath = session.getServletContext().getRealPath(upPath);
		String upPath = Utility.MY_ICON;
		
		//파일이름이 중복되는 경우, 유일한 이름으로 변경하자=>  _일련번호
		String iconUrl=Utility.getUniqueFileName(upPath, oName);
		
		//파일 업로드 처리
		File myIcon=new File(upPath, iconUrl);
		
		try{
			tempFile.transferTo(myIcon);
			System.out.println("아이콘 업로드 성공");
		}catch(IllegalStateException e){
			System.out.println("아이콘 업로드 실패");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("아이콘 업로드 실패!");
			e.printStackTrace();
		}
		//db작업
		mbBean.setMyIcon(iconUrl);
		
		int n = 0;
		try{
			n = mbService.updateMyInfo(mbBean);
			System.out.println("내 정보 수정 성공 n="+n+"mbBean"+mbBean);
		}catch(SQLException e){
			System.out.println("내 정보 수정 실패");
			e.printStackTrace();
		}
		
		//결과뷰
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/settings.do?userid="+userid);
		return mav;
	}
	

}//
