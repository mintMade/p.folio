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
import com.picxen.photo.model.PhotoBean;
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
/*		System.out.println("���Ӱ�ü ����:PhotoService"+", setPhotoService()");*/
	}
	
	@RequestMapping("/photo/photo/photoLikeUpdate.do")
	public ModelAndView updateLike(HttpServletRequest request, HttpSession session){
		//�Ķ����
		//���� ������ ��õ�� ������Ʈ
		//��õ ���̺� db�Է�
		
		String strPtNo = (String)request.getParameter("ptNo");
		Integer ptNo = Integer.parseInt(strPtNo);
		/*System.out.println("PhotoLike�Ķ����:ptNo="+ptNo);*/
		
		ModelAndView mav = new ModelAndView();
		
		//set Parameter
		String userid = (String)session.getAttribute("userid");
		PhotoLikeBean plBean = new PhotoLikeBean();
		plBean.setPtNo(ptNo);
		plBean.setUserid(userid);
		
		PhotoBean ptBean = new PhotoBean();
		try{
			ptBean=ptService.insertLike(plBean);
			/*System.out.println("like���� ����"+key+"pLikeBean"+plBean+", ptBean="+ptBean);*/
			
		}catch(SQLException e){
			System.out.println("like������Ʈ, ���� ����");
			e.printStackTrace();
		}
		
		//�����������
		mav.addObject("userid", userid);//detail.do�� ������ �� ������ �ش������ like�� ������� ��ȸ�ϱ� ���� ���� ���̵� �Ķ���ͷ� �ѱ�(!���� sql������ ó������)
		mav.addObject("sinkBean", ptBean); //���� ���ھ� �� ���ƿ�ī��Ʈ ���� parameter Bean
		mav.setViewName("/photo/faves/favesAdd");
		return mav;
	}
	
}///