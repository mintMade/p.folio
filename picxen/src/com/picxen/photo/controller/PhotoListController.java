package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.PagingBean;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
public class PhotoListController {
	private PhotoService ptService;
	
	public PhotoListController(){
		System.out.println("������:PhotoListController");
	}
		
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("���Ӱ�ü ����:PhotoListController"+"-setPhotoListService()");
	}
	
		
	//ī�װ����� �˻� //////////// ��������
	@RequestMapping("/photo/photo/photoLists.do")//("/photo/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String cgName, String popName, 
			String newName, String upcomName){
		//�Ķ����
		System.out.println("�Ķ����:ī�װ� �̸�1="+cgName+", �α��="+popName+", ���ο��="+newName
				+", �ߴ¼�="+upcomName);
		
		//db�۾�
		ArrayList<PhotoBean> alist = null;
		
		try{
			if(cgName != null && !cgName.isEmpty()){
				//�̺�Ʈ�� ��ǰ ��ȸ
				alist = ptService.listPhotoByCategory(cgName); //���� ��� xml�������� ����
			}else {
				//��ü ��ǰ ��ȸ
				alist = ptService.ptlistPop();
			}
			System.out.println("���� ����Ʈ �ε� ����:�⺻�α���� ����Ʈ ������"+alist.size());
		}catch(SQLException e){
			System.out.println("ī�װ� ��������Ʈ �ε� ����"+alist);
			e.printStackTrace();
		}
		
		//��� ��
		//����¡
		int pageSize=40;
		int blockSize=10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/photoLists");
		
		return mav;
	}
	
/*	@RequestMapping("/photo/photoList.do")
	public ModelAndView ptList(HttpServletRequest request, String cgName, String ftName){
		System.out.println("�Ķ���� CategoryName="+cgName+", ftName="+ftName);
			List<PhotoBean> ptList = null;
		
			CategoryBean cgBean = new CategoryBean();	
			System.out.println("cgBean="+cgBean);
			
				try{
					if(!"��κ���".equals(cgName)){
						if(cgName != null){ 
							cgBean = ptService.getCgBean(cgName);   /////
							System.out.println("cgBBean="+cgBean);
							cgBean.setFilterName(ftName);
							System.out.println("ffftName="+ftName);
						}
						System.out.println("cgBena=="+cgBean);
							ptList = ptService.listPhotoBySearch(cgBean);/////
					}
					if("��κ���".equals(cgName)){
						ptList = ptService.listPhotoBySearch(cgBean);  ////
					}
					System.out.println("filterList��� ����"+ptList.size());
				}catch(SQLException e){
					System.out.println("filterList��� ����"+ptList);
					e.printStackTrace();
				}
				
				//���� ���� ���(����¡)
				int pageSize=40;
				int blockSize=10;
				PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);
				PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);
		
				ModelAndView mav = new ModelAndView();
				mav.addObject("alist", ptList);
				mav.addObject("totalRecord", ptList.size());
				mav.addObject("pb", pb); //���� ���� �Ķ����(����¡)
				mav.setViewName("/photo/photoList");
		
		return mav;
	}*/ ///
	
	@RequestMapping("/photo/photoList.do")
	public ModelAndView ptList(HttpServletResponse response, HttpServletRequest request, String cgName, String ftName){
		System.out.println("�Ķ���� CategoryName="+cgName+", ftName="+ftName);
			List<PhotoBean> ptList = null;
		
			CategoryBean cgBean = new CategoryBean();
			//ftName ����ó��
			if(ftName==null || ftName.isEmpty()){
				cgBean.setFilterName("pop");
			}
			cgBean.setFilterName(ftName);
			cgBean.setCategoryName(cgName);
			
			System.out.println("cgBean=="+cgBean);
			
				try{
					//"��κ���"�� sql db�� �����Ͱ� ����. db�� ���� ���� ������, �ʳְ� �غ��°ɷ�
					//select ���ǿ� �Ķ���� ���� ���Ĺ�ĸ� �����ؼ� �Ķ���͸� ftName�� �ް� order���ĸ� Ʋ����
					if("��κ���".equals(cgName)){
						ptList = ptService.listPhotoByAllView(cgBean);
					}else if(cgName != null && !cgName.isEmpty()){
						//cgName�� �Ķ���ͷ� �˻�
						ptList = ptService.listPhotoByCategory(cgBean);
					}else{
						ptList = ptService.listPhotoByAllView(cgBean);
					}
					
					System.out.println("filterList��� ����"+ptList.size());
				}catch(SQLException e){
					System.out.println("filterList��� ����"+ptList);
					e.printStackTrace();
				}
				
				//���� ���� ���(����¡)
				int pageSize=40;
				int blockSize=10;
				/*PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);*/
				PagingBean pb = new PagingBean(request, ptList, pageSize, blockSize);
		
				ModelAndView mav = new ModelAndView();
				mav.addObject("alist", ptList);
				mav.addObject("totalRecord", ptList.size());
				mav.addObject("pb", pb); //���� ���� �Ķ����(����¡)
				mav.setViewName("/photo/photoList");
		
		return mav;
	}
	
	
	//�α� ���� �˻�
	@RequestMapping("/photo/photo/popular.do")
	public ModelAndView ptPopList(HttpServletRequest request, String cgName){
	System.out.println("�α���� ī�װ�="+cgName);
	ArrayList<PhotoBean> alist = null;
	
	try{
		if("��κ���".equals(cgName)){
			alist=ptService.listPhotoByPop();	
		}else if(cgName != null && !cgName.isEmpty()){
			alist=ptService.listPhotoByCtPop(cgName);
		}else{
			alist=ptService.listPhotoByPop();
		}
		System.out.println("�α� ���� �ε� ����="+alist.size());
	}catch(SQLException e){
		System.out.println("�α� ���� �ε� ����"+alist);
		e.printStackTrace();
	}
	
	int pageSize=10;
	int blockSize=10;
	PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
	System.out.println("pb="+pb);
	
	ModelAndView mav = new ModelAndView();
	mav.addObject("alist", alist);
	mav.addObject("cgName", cgName);
	mav.addObject("totalRecord", alist.size());
	mav.addObject("pb", pb);
	mav.setViewName("/photo/photo/popular");
	
	return mav;
	}
	
	//���ο� ���� �˻�
	@RequestMapping("/photo/photo/new.do")
	public ModelAndView ptNewList(HttpServletRequest request, String cgName){
		System.out.println("���ο� ���� ī�װ� �Ķ����"+cgName);
		ArrayList<PhotoBean> alist=null;
		try{
			if("��κ���".equals(cgName)){
				alist=ptService.listPhotoByNew();	
			}else if(cgName != null && !cgName.isEmpty()){
				alist=ptService.listPhotoByCtNew(cgName);
			}else{
				alist=ptService.listPhotoByNew();
			}
			System.out.println("���ο���� �ε� ����"+alist.size());
		}catch(SQLException e){
			System.out.println("���ο���� �ε�����"+alist);
			e.printStackTrace();
		}
		int pageSize = 40;
		int blockSize = 10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("cgName", cgName);
		mav.addObject("totalRocord", alist.size());
		mav.addObject("pb",pb);
		mav.setViewName("/photo/photo/new");
		
		return mav;
	}
	
	
	//�߰��ִ� ���� �˻�
	@RequestMapping("/photo/photo/upcoming.do")
	public ModelAndView ptUpcomList(HttpServletRequest request, String cgName){
		System.out.println("�ߴ»����Ķ����"+cgName);
		ArrayList<PhotoBean> alist = null;
		try{
			if("��κ���".equals(cgName)){
				alist=ptService.listPhotoByUpCom();
			}else if(cgName != null && !cgName.isEmpty()){
				alist=ptService.listPhotoByCtUpcom(cgName);
			}else{
				alist=ptService.listPhotoByUpCom();
			}
			System.out.println("�ߴ� ���� �ε� ����"+alist.size());
		}catch(SQLException e){
			System.out.println("�ߴ� ���� �ε� ����"+alist);
			e.printStackTrace();
		}
		
		int pageSize = 40;
		int blockSize =10;
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", alist);
		mav.addObject("cgName", cgName);
		mav.addObject("totalRecord", alist.size());
		mav.addObject("pb", pb);
		mav.setViewName("/photo/photo/upcoming");
		return mav;
	}
	

	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String kword, String pageSize, HttpServletRequest request){
		//�Ķ����
		System.out.println("�˻��Ķ���� tag+"+kword+"pageSize"+pageSize);
		
		//db�۾�
		ArrayList <PhotoBean> alist= null;
		
		try{
			if(kword != null && !kword.isEmpty()){
				//�±׹� Ÿ��Ʋ ��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(kword);
			}else{
				//��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.listPhotoAll();
			}
			System.out.println("���� �˻� ����(tag, Ÿ��Ʋ ���)="+alist);
		}catch(Exception e){
			System.out.println("���� �˻� ����(tag, Ÿ��Ʋ ���)="+alist.size());
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
