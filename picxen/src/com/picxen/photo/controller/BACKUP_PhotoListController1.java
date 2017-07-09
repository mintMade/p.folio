package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.PagingBean;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class BACKUP_PhotoListController1 {
	private PhotoService ptService;
	
	public BACKUP_PhotoListController1(){
		System.out.println("������:PhotoListController");
	}
		
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("���Ӱ�ü ����:PhotoListController"+"-setPhotoListService()");
	}
	
	//ī�װ����� �˻�
	@RequestMapping("/photo/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String cgName){
		//�Ķ����
		System.out.println("�Ķ����:ī�װ� �̸�="+cgName);
		
		//db�۾�
		ArrayList<PhotoBean> alist = null;
		
		try{
			if(cgName != null && !cgName.isEmpty()){
				//�̺�Ʈ�� ��ǰ ��ȸ
				alist = ptService.listPhotoByCategory(cgName);
			}else{
				//��ü ��ǰ ��ȸ
				alist = ptService.ptlistPop();
			}
			System.out.println("���� ����Ʈ �ε� ����:�⺻�α���� ����Ʈ ������"+alist.size());
		}catch(SQLException e){
			System.out.println("��������Ʈ �ε� ����"+alist);
			e.printStackTrace();
		}
		
		//��� ��
		//����¡
		int pageSize=5;
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/photoList");
		
		return mav;
	}
	
	
	//ī�װ� ����Ʈ
	/*@requestMapping("")*/
	
	
	
/*	@RequestMapping("/photo/photo/photoByCategory.do")
	public ModelAndView ptList(int cgNo){
		//�Ķ����
		System.out.println("cgNo="+cgNo);
		
		//2.db�۾�
		ArrayList<PhotoBean> alist=null;
		try{
			alist = ptService.listPhotoByCategory(cgNo);
			System.out.println("ī�װ��� ���� ��ȸ ����+alist.size()"+alist.size()
					+"cgNo"+cgNo);
		}catch(SQLException e){
			System.out.println("ī�װ��� ���� ��ȸ ����@");
			e.printStackTrace();
		}
		
		
		//���, ��������
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/photoByCategory");
		
		return mav;
	}*/////������ ���̵忡 ī�װ�bar ī�װ��� ���
	
	//��� ����
	/*@RequestMapping("/photo/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String eventName){
		//�̺�Ʈ��ǰ ��� ��ü���� ��ȸ
		//1.�Ķ����
		
		//db�۾�-select
		ArrayList<PhotoBean> alist=null;
		try{
			if(eventName != null && !eventName.isEmpty()){
				
			//�̺�Ʈ�� ��ǰ ��ȸ
			alist=(ArrayList<PhotoBean>)ptService.listPhotoByEvent(eventName);
			}else{
				
			//��ü ��ǰ ��ȸ
			alist=ptService.listPhotoAll();
			}
			System.out.println("��ǰ ��� ��ȸ ����, alist.size()="+alist.size());
		}catch(SQLException e){
			System.out.println("���� ��ȸ ����");
			e.printStackTrace();
		}
	
	//3.���.��������
	int pageSize=5;
	int blockSize=10;
	PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
	ModelAndView mav = new ModelAndView();
	mav.addObject("alist", alist);
	mav.addObject("totalRecord", alist.size());
	mav.addObject("pb", pb);
	
	mav.setViewName("/photo/photo/photoList");
	
	return mav;
	}*/
	
/*	@RequestMapping("/photo/photo/searchResult.do")
	public ModelAndView searchPhoto(String ptName, String ptName2, String pageSize,
			HttpServletRequest request){
		
		//1. �Ķ����
		System.out.println("���� �̸� ptName"+ptName+"ptName2"+ptName2);
		
		//2.db�۾�
		ArrayList<PhotoBean> alist = null;
		
		Map<String, String>ptMap = new HashMap<String, String>();
		ptMap.put("ptName", ptName);
		ptMap.put("ptName2", ptName2);
		try{
			alist = ptService.searchPhoto(ptMap);
			System.out.println("���� �˻� ����,alist.size"+alist.size());
		}catch(SQLException e){
			System.out.println("���� �˻� ����");
			e.printStackTrace();
		}
		
		//�����������
		int iPageSize=5;
		if(pageSize!=null && !pageSize.isEmpty()){
			iPageSize=Integer.parseInt(pageSize);
		}
		int blockSize=10;
		PagingBean pb 
		= new PagingBean(request, alist, iPageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptList", alist);
		mav.addObject("pb", pb);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/searchResult");
		
		return mav;
	}*/

	
	
	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String tag, String pageSize, HttpServletRequest request){
		//�Ķ����
		System.out.println("�˻��Ķ���� tag+"+tag+"pageSize"+pageSize);
		
		//db�۾�
		ArrayList <PhotoBean> alist= null;
		
		try{
			if(tag != null && !tag.isEmpty()){
				//�±׹� Ÿ��Ʋ ��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(tag);
			}else{
				//��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.listPhotoAll();
			}
			System.out.println("���� �˻� ����(tag, Ÿ��Ʋ ���)");
		}catch(Exception e){
			System.out.println("���� �˻� ����(tag, Ÿ��Ʋ ���)");
			e.printStackTrace();
		}
		//��� ��������
		int iPageSize=5;
		if(pageSize != null && !pageSize.isEmpty()){
			iPageSize=Integer.parseInt(pageSize);
		}
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, iPageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptTagList", alist);
		mav.addObject("pb", pb);
		mav.addObject("totalRecord", alist.size());
		mav.setViewName("/photo/photo/ptSearchResult");
		
		return mav;
	}
	
}/////////
