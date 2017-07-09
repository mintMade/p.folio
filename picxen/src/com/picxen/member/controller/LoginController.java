package com.picxen.member.controller;

import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.member.model.MemberService;

@Controller
public class LoginController {
	private MemberService memberService;
	
	public LoginController(){
		System.out.println("생성자:LoginController");
	}
	
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService = memberService;
		System.out.println("종속객체 주입: MemberController"+"setMemberService()");
	}
@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
public ModelAndView getLogin(){
	//로그인 화면 보여주기
	System.out.println("로그인 페이지:getLogin()");
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("/login/login");
	
	return mav;
}
	
@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
public ModelAndView postLogin(String userid, String pwd, String idSave,
		HttpSession session, HttpServletRequest request, HttpServletResponse response){
	//로그인 처리
	System.out.println("postLoging(), userid="+userid+", pwd="+pwd+",idSave="+idSave);
	
	//[1]db에서 해당 아이디가 존재하는지 확인
	int result = 0;
	
	try{
		result = memberService.checkIdPwd(userid,pwd);
		System.out.println("로그인 처리 성공!, result="+result);
	}catch(SQLException e){
		System.out.println("로그인 처리 실패");
		e.printStackTrace();
	}
	
	String msg="", url="";
	try{
	if(result==MemberService.LOGIN_OK){
		//[2]session에 아이디 저장
		session.setAttribute("userid", userid);
		
		//[3]쿠키에 아이디 저장
		Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));
		if(idSave != null){ //아이디 저장을 체크한 경우
			//쿠키 저장
			cookie.setMaxAge(1000*24*60*60);//초=>1000일
			response.addCookie(cookie);
		}else{//쿠키삭제
			cookie.setMaxAge(0);//쿠키 파일 제거
			response.addCookie(cookie);
		}
		
		msg=userid + "로 로그인 되었습니다";
		/*url=request.getContextPath()+"/index.do";*/ //유저홈으로 이동
		url=request.getContextPath()+"/user/user/userMain.do?userid="+userid;
		}else if(result==MemberService.ID_NONE){
			msg="해당 아이디가 없습니다.";
			url="login.do";
		}else if(result==MemberService.PWD_DISAGREE){
			msg="비밀번호가 일치하지 않습니다.";
			url="login.do";
		}else{
			msg="로그인 처리 실패!";
			url="login.do";
		}
		System.out.println("아이디 체크 성공");
	}catch(Exception e){
		System.out.println("예외발생 아이디 체크");
		e.printStackTrace();
	}
	
	ModelAndView mav = new ModelAndView();
	mav.addObject("msg", msg);
	mav.addObject("url", url);
	mav.setViewName("/inc/message");
	
	return mav;
}
@RequestMapping("/member/logout.do")
public ModelAndView logout(HttpSession session){
	//로그아웃 처리
	session.invalidate();//세션 무효
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("redirect:/index.do");
	
	return mav;
}


}///class  dispatcher예정
