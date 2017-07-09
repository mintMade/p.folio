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

import com.picxen.member.model.MemberBean;
import com.picxen.member.model.MemberService;

@Controller
public class AgreementController {
	
	private MemberService memberService;
	
	public AgreementController(){
		System.out.println("생성자:AgreementController");
	}
	
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
		System.out.println("종속객체 주입:AgreementController"
				+"-setMemberService()");
	}
	
	//이메일 비밀번호
	@RequestMapping(value="/member/agreement.do", method=RequestMethod.GET)
	public ModelAndView getAgree(){
		System.out.println("회원가입 페이지:email, userid, name, pwd()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/member/agreement");
		return mav;
	}
	
	//register페이지 1본 회원가입 형식 변경
	/*@RequestMapping(value="/member/agreement.do", method=RequestMethod.POST)
	public ModelAndView postAgree(){
		System.out.println("회원 가입 페이지:postAgree()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/register");
		
		return mav;
	}*/
	
	//회원가입, 가입 후 로그인
	@RequestMapping(value="/member/agreement.do", method=RequestMethod.POST)
	public ModelAndView postAgree(MemberBean mBean, HttpSession session){
		//파라미터
		System.out.println("회원가입:이메일, 비번, 이메일, 유저아이디, 이름, 패스워드 memberBean+"+mBean);
		//db작업
		int key = 0;
		
		try{
			key = memberService.insertMember(mBean);
			System.out.println("회원가입 성공, key="+key+"입력값="+mBean);
		}catch(SQLException e){
			System.out.println("회원 가입 실패"+mBean);
			e.printStackTrace();
		}
		
		//가입후 로그인=>홈
		
		String userid = mBean.getUserid();
		String pwd = mBean.getPwd();
		int result = 0;
		try{
			result = memberService.checkIdPwd(userid, pwd);
			System.out.println("로그인 성공:userid"+userid+", pwd="+pwd);
		}catch(SQLException e){
			System.out.println("로그인 실패:userid="+userid+",pwd="+pwd+"result="+result);
			e.printStackTrace();
		}
		
		try{
			/*if(result==memberService.LOGIN_OK){*/
				//[1]세션에 아이디저장
				session.setAttribute("userid", userid);
				/*Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));*/
				
				System.out.println("세션저장 성공");
		}catch(Exception e){
			System.out.println("세션저장 실패");
			e.printStackTrace();
		}
		
		//결과뷰페이지
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/user/userMain.do?userid="+userid);
		
		return mav;
	}
	
	//이메일 중복 주소체크, 아이디 체크 통합
/*	@RequestMapping("/member/agreement.do")//아이디 페이지로 통합
	public ModelAndView getCheckEmail(String email, HttpServletRequest request){
		//파라미터
		System.out.println("파라미터 이메일 중복 email="+email);
		
		//db작업-select
		boolean result = false;
		if(email != null && !email.isEmpty()){
		try{
			result = memberService.checkUserEmail(email);
			System.out.println("이메일 중복 체크 성공:result"+result+"이메일="+email);
		}catch(SQLException e){
			System.out.println("이메일 중복 체크 실패:result"+result);
			e.printStackTrace();
		}
		
	}//if
		ModelAndView mav = new ModelAndView();
		mav.addObject("emailFlag", result);
		mav.setViewName("/member/agreement");
		
		return mav;
	}*/
	
/*	@RequestMapping("/member/register.do")
	public ModelAndView register(MemberBean mBean){
		//회원가입 처리-insert
		
		//파라미터
		System.out.println("register, mBean:"+mBean);
		
		//db작업
		String email = mBean.getEmail();

		int key = 0;
		
		try{
			key = memberService.insertMember(mBean);
			System.out.println("회원 가입 성공!, "+key
					+", 입력값:mBean="+mBean);
		}catch(SQLException e){
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
		
		//결과 뷰페이지
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");		
		
		return mav;
	}//member
*/	
	
	// id체크, email체크
	@RequestMapping("/member/checkId.do")
	public ModelAndView getCheckId(String userid, String email){
		//1파라미터
		System.out.println("getCheckId, userId="+userid+"email"+email);
		
		/*|| */
		
		//2. db작업-select
		int resId = 0;
		int resEmail =0;

		if(userid!=null && !userid.isEmpty()){
			try{
				resId = memberService.checkUserid(userid);
				if(resId > 0){
					resId = 101;
				}
				if(resId == 0){
					resId = 100;
				}
				System.out.println("아이디 체크 성공, result= "+resId+", userid= "+userid);
			}catch(SQLException e){
				System.out.println("아이디체크 실패");
				e.printStackTrace();
			}
		}//if Userid
		
		if(email !=null && !email.isEmpty()){
			try{
				resEmail = memberService.checkUserEmail(email);
				if(resEmail > 0){
					resEmail = 201;
				}
				if(resEmail == 0){
					resEmail =200;
				}
				System.out.println("이메일 체크 성공, resEmail= "+resEmail+", email="+email);
			}catch(SQLException e){
				System.out.println("이메일체크 실패");
				e.printStackTrace();
			}
		}
		
		//3 결과 뷰페이지
		ModelAndView mav = new ModelAndView();
		mav.addObject("userFlag", resId);
		mav.addObject("emailFlag", resEmail);
		/*mav.setViewName("/member/checkId");*///팝업방식에서 바로 확인으로 변경
		mav.setViewName("/member/checkId");
		
		return mav;
	}
	
/*	@RequestMapping("/member/checkId.do")
	public ModelAndView getCheckId(String userid, HttpServletRequest request
				, HttpServletResponse response){
		//1파라미터
		System.out.println("getCheckId, userId="+userid);
		
		//2. db작업-select
		boolean result = false;
		if(userid!=null && !userid.isEmpty()){
			try{
				result = memberService.checkUserid(userid);
				System.out.println("아이디 체크 성공, result= "+result
						+", userid= "+userid);
			}catch(SQLException e){
				System.out.println("아이디체크 실패");
				e.printStackTrace();
			}
		}//if
		
		//3 결과 뷰페이지
		ModelAndView mav = new ModelAndView();
		mav.addObject("userFlag", result);
		mav.setViewName("/member/checkId");//팝업방식에서 바로 확인으로 변경
		mav.setViewName("/member/checkId");
		
		return mav;
	}*/
	
}///////class
