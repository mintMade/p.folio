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
import com.picxen.photo.model.PhotoLikeBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoLikeController {
	private PhotoService ptService;
	
	public PhotoLikeController(){
		System.out.println("������:PhotoLikeController");
	}
	
	//setter
	public void setPhotoService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("���Ӱ�ü ����:PhotoService"+", setPhotoService()");
	}
	
/*	@RequestMapping(value="/photo/photo/photoLikeUpdate.do", method=RequestMethod.GET)
	public ModelAndView photoLikeListGet(PhotoLikeBean plBean, HttpSession session, String userid, int ptNo){
		//�Ķ����
		System.out.println("�������̵�="+userid+"ptNo="+ptNo);
		
		ModelAndView mav=new ModelAndView();
		String userid = (String)session.getAttribute("userid");
		System.out.println("�������̵�(useridüũ)"+userid);
		if(userid==null||userid.isEmpty()){
			mav.addObject("msg", "�α��θ��� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
		}
				
		return mav;
		
		
		//2db�۾�
		boolean result=false;
		
		try{
			result=ptService.listPhotoLike(plBean);
			System.out.println("���� photoLike ��ȸ ����, result="+result);
		}catch(SQLException e){
			System.out.println("photoLike ��ȸ ����");
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("likeCnt", result);
		mav.setViewName("redirect:/photo/photo/photoDetail.do?ptNo="+ptNo);
		
		return mav;
	}*/
	
	
	@RequestMapping("/photo/photo/photoLikeUpdate.do")
	public ModelAndView updateLike(HttpServletRequest request, HttpSession session, MemberBean mbBean, 
			PhotoLikeBean plBean, int ptNo, String userid, String sort){
		//�Ķ����
		//���� ������ ��õ�� ������Ʈ
		//��õ ���̺� db�Է�
		System.out.println("PhotoLike�Ķ����:ptNo="+ptNo+"userid="+userid+", sort"+sort);
		
		ModelAndView mav = new ModelAndView();
		userid = (String)session.getAttribute("userid");
		System.out.println("userid(�α���üũ)"+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		
		int key=0;
		
		try{
			key=ptService.insertLike(plBean);
			System.out.println("like���� ����"+key+"pLikeBean"+plBean);
			
			/*n=ptService.likeUpdate(plBean);
			System.out.println("like������Ʈ ����"+ptNo+"n="+n);Ʈ���������� ��ü*/
		}catch(SQLException e){
			System.out.println("like������Ʈ, ���� ����");
			e.printStackTrace();
		}
		
		//�����������

		mav.addObject("userid", userid);//detail.do�� ������ �� ������ �ش������ like�� ������� ��ȸ�ϱ� ���� ���� ���̵� �Ķ���ͷ� �ѱ�(!���� sql������ ó������)
		mav.addObject("sort", sort);
		mav.setViewName("redirect:/photo/photo/photoDetail.do?ptNo="+ptNo);
		return mav;
	}
	
}///