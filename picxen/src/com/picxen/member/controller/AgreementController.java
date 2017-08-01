package com.picxen.member.controller;

/*import java.net.URLEncoder;*/
import java.sql.SQLException;

/*import javax.servlet.http.Cookie;*/
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
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
	
	//회원가입
	@RequestMapping(value="/member/agreement.do", method=RequestMethod.POST)
	public ModelAndView postAgree(MemberBean mBean, HttpSession session){
		//파라미터
		System.out.println("회원가입 정보 memberBean+"+mBean);
		//db작업
		int key = 0;
		
		try{
			key = memberService.insertMember(mBean);
			System.out.println("회원가입 성공, key="+key+"입력값="+mBean);
		}catch(SQLException e){
			System.out.println("회원 가입 실패");
			e.printStackTrace();
		}
		
		//가입후 로그인=>홈
		String userid = mBean.getUserid();
		String pwd = mBean.getPwd();
		int result = 0;
		try{
			result = memberService.checkIdPwd(userid, pwd);
			System.out.println("로그인 성공:userid"+result);
			session.setAttribute("userid", userid);
			System.out.println("세션저장 성공");
		}catch(SQLException e){
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/user/userMain.do?userid="+userid);
		
		return mav;
	}

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
		/*mav.setViewName("/member/checkId");*///팝업방식에서 Ajax 비동식 방식으로 변경
		mav.setViewName("/member/checkId");
		
		return mav;
	}
	
}///////class
