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
		System.out.println("������:LoginController");
	}
	
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService = memberService;
		System.out.println("���Ӱ�ü ����: MemberController"+"setMemberService()");
	}
@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
public ModelAndView getLogin(){
	//�α��� ȭ�� �����ֱ�
	System.out.println("�α��� ������:getLogin()");
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("/login/login");
	
	return mav;
}
	
@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
public ModelAndView postLogin(String userid, String pwd, String idSave,
		HttpSession session, HttpServletRequest request, HttpServletResponse response){
	//�α��� ó��
	System.out.println("postLoging(), userid="+userid+", pwd="+pwd+",idSave="+idSave);
	
	//[1]db���� �ش� ���̵� �����ϴ��� Ȯ��
	int result = 0;
	
	try{
		result = memberService.checkIdPwd(userid,pwd);
		System.out.println("�α��� ó�� ����!, result="+result);
	}catch(SQLException e){
		System.out.println("�α��� ó�� ����");
		e.printStackTrace();
	}
	
	String msg="", url="";
	try{
	if(result==MemberService.LOGIN_OK){
		//[2]session�� ���̵� ����
		session.setAttribute("userid", userid);
		
		//[3]��Ű�� ���̵� ����
		Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));
		if(idSave != null){ //���̵� ������ üũ�� ���
			//��Ű ����
			cookie.setMaxAge(1000*24*60*60);//��=>1000��
			response.addCookie(cookie);
		}else{//��Ű����
			cookie.setMaxAge(0);//��Ű ���� ����
			response.addCookie(cookie);
		}
		
		msg=userid + "�� �α��� �Ǿ����ϴ�";
		/*url=request.getContextPath()+"/index.do";*/ //����Ȩ���� �̵�
		url=request.getContextPath()+"/user/user/userMain.do?userid="+userid;
		}else if(result==MemberService.ID_NONE){
			msg="�ش� ���̵� �����ϴ�.";
			url="login.do";
		}else if(result==MemberService.PWD_DISAGREE){
			msg="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			url="login.do";
		}else{
			msg="�α��� ó�� ����!";
			url="login.do";
		}
		System.out.println("���̵� üũ ����");
	}catch(Exception e){
		System.out.println("���ܹ߻� ���̵� üũ");
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
	//�α׾ƿ� ó��
	session.invalidate();//���� ��ȿ
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("redirect:/index.do");
	
	return mav;
}


}///class  dispatcher����
