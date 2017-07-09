package com.picxen.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class IndexController {
	private PhotoService ptService;
	
	public IndexController(){
		System.out.println("������:IndexController");
	}
	
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("���Ӱ�ü����:IndexController"+"setPtService()");
	}
	
	@RequestMapping("/index.do")
	public ModelAndView getIndex(){
		System.out.println("����()index ������:getIndex()");
		
		//index.jsp ���������� �ʿ��� �̺�Ʈ�� ��ǰ �������ȸ�ؼ� ����� �־�����!!!!!!!!!!!!!!!!!!��γ־��ֱ�bootstrap dynamic image
		//[2]�̺�Ʈ�� ��ǰ��� ��ȸ
		ArrayList<PhotoBean>newList=new ArrayList<PhotoBean>();
		ArrayList<PhotoBean>bestList=new ArrayList<PhotoBean>();
		ArrayList<PhotoBean>mdList=new ArrayList<PhotoBean>();
		
		try{
			newList=ptService.listPhotoByEvent("NEW");
			bestList=ptService.listPhotoByEvent("BEST");
			mdList=ptService.listPhotoByEvent("MD");
			System.out.println("�̺�Ʈ�� ������� ��ȸ����,newList.size()="
					+newList.size()+"bestList.size()="+bestList.size()+
					"mdList.size()="+mdList.size());
		}catch(SQLException e){
			System.out.println("�̺�Ʈ ������� ��ȸ����");
			e.printStackTrace();
		}
		
		ArrayList<PhotoBean> alist = null;
		
		try{
			alist=ptService.listPhotoAll();
			System.out.println("���� ���� ����Ʈ �ε� ����");
		}catch(SQLException e){
			System.out.println("���� ���� ����Ʈ �ε� ����");
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("newList", newList );
		mav.addObject("bestList", bestList);
		mav.addObject("mdList", mdList);
		mav.addObject("alist", alist);
		
		mav.setViewName("/index");
		
		return mav;
	}//
	
}////
