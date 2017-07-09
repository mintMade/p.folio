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
		System.out.println("������:AgreementController");
	}
	
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
		System.out.println("���Ӱ�ü ����:AgreementController"
				+"-setMemberService()");
	}
	
	//�̸��� ��й�ȣ
	@RequestMapping(value="/member/agreement.do", method=RequestMethod.GET)
	public ModelAndView getAgree(){
		System.out.println("ȸ������ ������:email, userid, name, pwd()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/member/agreement");
		return mav;
	}
	
	//register������ 1�� ȸ������ ���� ����
	/*@RequestMapping(value="/member/agreement.do", method=RequestMethod.POST)
	public ModelAndView postAgree(){
		System.out.println("ȸ�� ���� ������:postAgree()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/register");
		
		return mav;
	}*/
	
	//ȸ������, ���� �� �α���
	@RequestMapping(value="/member/agreement.do", method=RequestMethod.POST)
	public ModelAndView postAgree(MemberBean mBean, HttpSession session){
		//�Ķ����
		System.out.println("ȸ������:�̸���, ���, �̸���, �������̵�, �̸�, �н����� memberBean+"+mBean);
		//db�۾�
		int key = 0;
		
		try{
			key = memberService.insertMember(mBean);
			System.out.println("ȸ������ ����, key="+key+"�Է°�="+mBean);
		}catch(SQLException e){
			System.out.println("ȸ�� ���� ����"+mBean);
			e.printStackTrace();
		}
		
		//������ �α���=>Ȩ
		
		String userid = mBean.getUserid();
		String pwd = mBean.getPwd();
		int result = 0;
		try{
			result = memberService.checkIdPwd(userid, pwd);
			System.out.println("�α��� ����:userid"+userid+", pwd="+pwd);
		}catch(SQLException e){
			System.out.println("�α��� ����:userid="+userid+",pwd="+pwd+"result="+result);
			e.printStackTrace();
		}
		
		try{
			/*if(result==memberService.LOGIN_OK){*/
				//[1]���ǿ� ���̵�����
				session.setAttribute("userid", userid);
				/*Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));*/
				
				System.out.println("�������� ����");
		}catch(Exception e){
			System.out.println("�������� ����");
			e.printStackTrace();
		}
		
		//�����������
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/user/userMain.do?userid="+userid);
		
		return mav;
	}
	
	//�̸��� �ߺ� �ּ�üũ, ���̵� üũ ����
/*	@RequestMapping("/member/agreement.do")//���̵� �������� ����
	public ModelAndView getCheckEmail(String email, HttpServletRequest request){
		//�Ķ����
		System.out.println("�Ķ���� �̸��� �ߺ� email="+email);
		
		//db�۾�-select
		boolean result = false;
		if(email != null && !email.isEmpty()){
		try{
			result = memberService.checkUserEmail(email);
			System.out.println("�̸��� �ߺ� üũ ����:result"+result+"�̸���="+email);
		}catch(SQLException e){
			System.out.println("�̸��� �ߺ� üũ ����:result"+result);
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
		//ȸ������ ó��-insert
		
		//�Ķ����
		System.out.println("register, mBean:"+mBean);
		
		//db�۾�
		String email = mBean.getEmail();

		int key = 0;
		
		try{
			key = memberService.insertMember(mBean);
			System.out.println("ȸ�� ���� ����!, "+key
					+", �Է°�:mBean="+mBean);
		}catch(SQLException e){
			System.out.println("ȸ������ ����");
			e.printStackTrace();
		}
		
		//��� ��������
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");		
		
		return mav;
	}//member
*/	
	
	// idüũ, emailüũ
	@RequestMapping("/member/checkId.do")
	public ModelAndView getCheckId(String userid, String email){
		//1�Ķ����
		System.out.println("getCheckId, userId="+userid+"email"+email);
		
		/*|| */
		
		//2. db�۾�-select
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
				System.out.println("���̵� üũ ����, result= "+resId+", userid= "+userid);
			}catch(SQLException e){
				System.out.println("���̵�üũ ����");
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
				System.out.println("�̸��� üũ ����, resEmail= "+resEmail+", email="+email);
			}catch(SQLException e){
				System.out.println("�̸���üũ ����");
				e.printStackTrace();
			}
		}
		
		//3 ��� ��������
		ModelAndView mav = new ModelAndView();
		mav.addObject("userFlag", resId);
		mav.addObject("emailFlag", resEmail);
		/*mav.setViewName("/member/checkId");*///�˾���Ŀ��� �ٷ� Ȯ������ ����
		mav.setViewName("/member/checkId");
		
		return mav;
	}
	
/*	@RequestMapping("/member/checkId.do")
	public ModelAndView getCheckId(String userid, HttpServletRequest request
				, HttpServletResponse response){
		//1�Ķ����
		System.out.println("getCheckId, userId="+userid);
		
		//2. db�۾�-select
		boolean result = false;
		if(userid!=null && !userid.isEmpty()){
			try{
				result = memberService.checkUserid(userid);
				System.out.println("���̵� üũ ����, result= "+result
						+", userid= "+userid);
			}catch(SQLException e){
				System.out.println("���̵�üũ ����");
				e.printStackTrace();
			}
		}//if
		
		//3 ��� ��������
		ModelAndView mav = new ModelAndView();
		mav.addObject("userFlag", result);
		mav.setViewName("/member/checkId");//�˾���Ŀ��� �ٷ� Ȯ������ ����
		mav.setViewName("/member/checkId");
		
		return mav;
	}*/
	
}///////class
