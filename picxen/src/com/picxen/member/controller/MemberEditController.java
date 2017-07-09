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
		System.out.println("������ MemberEditController()");
	}
	
	public void setMemberService(MemberService mbService){
		this.mbService=mbService;
		System.out.println("���Ӱ�ü ����:MemberService"+"MemberEditController()");
	}
	
	
	@RequestMapping(value="/member/settings.do", method=RequestMethod.GET)
	public ModelAndView editMeGet(HttpSession session, String userid, MemberBean mbBean){
		//�Ķ����
		System.out.println("memberEditController: userid"+userid+"MemberBean"+mbBean);
		
		//db
		try{
			mbBean = mbService.viewMyInfo(userid);
			System.out.println("�� ���� ��� ���� userid="+userid+", mbBean="+mbBean);
		}catch(SQLException e){
			System.out.println("ȸ������ ��� ����");
			e.printStackTrace();
		}
		
		//��������
		ModelAndView mav = new ModelAndView();
		//�α��ο���:��Ű�ð� ��� ����ó��
		userid = (String)session.getAttribute("userid");
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���.");
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
		//�Ķ����
		MemberBean mbBean=null;
		String userid=null;
		
		//���� ���ε� ó��
		//����ڰ� ���ε��Ϸ��� ������ ����-�ӽ����� ����
		MultipartFile tempFile = mbBean.getUploadIcon();
		
		//������ ������ �̸�
		String oName=tempFile.getOriginalFilename();
		
		//���ε� ���-���� �������� ��� ���ϱ�
		//String upPath = "/pt_images";
		//upPath = session.getServletContext().getRealPath(upPath);
		String upPath = Utility.MY_ICON;
		
		//�����̸��� �ߺ��Ǵ� ���, ������ �̸����� ��������=>  _�Ϸù�ȣ
		String iconUrl=Utility.getUniqueFileName(upPath, oName);
		
		//���� ���ε� ó��
		File myIcon=new File(upPath, iconUrl);
		
		try{
			tempFile.transferTo(myIcon);
			System.out.println("������ ���ε� ����");
		}catch(IllegalStateException e){
			System.out.println("������ ���ε� ����");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("������ ���ε� ����!");
			e.printStackTrace();
		}
		//db�۾�
		mbBean.setMyIcon(iconUrl);
		
		int n = 0;
		try{
			n = mbService.updateMyInfo(mbBean);
			System.out.println("�� ���� ���� ���� n="+n+"mbBean"+mbBean);
		}catch(SQLException e){
			System.out.println("�� ���� ���� ����");
			e.printStackTrace();
		}
		
		//�����
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/settings.do?userid="+userid);
		return mav;
	}
	

}//
