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
import org.springframework.web.bind.annotation.RequestMethod;
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

	//����Ʈ ��� ��� ���� (���������� ī�װ����� �޼ҵ尡 �������� �������� ó��)
	@RequestMapping(value="/photo/photoList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ptList(HttpServletRequest request, String cgName, String sort){
			List<PhotoBean> ptList = null;
			
			CategoryBean cgBean = new CategoryBean();
			//�ε��� �� �޴����� ���� �� order ī�װ� �Ķ���� ���� ��� if�� ���� 
			if(sort==null || sort.isEmpty()){
				cgBean.setSort("pop");
			}
			cgBean.setSort(sort);
			cgBean.setCategoryName(cgName);
			
			System.out.println("cgBean=="+cgBean);
			
				try{
					//"��κ���"�� sql db�� �����Ͱ� ����. db�� �����ؼ� ���� ���� ������, �ȳְ� �غ��°ɷ� ó��
					//select ���ǿ� �Ķ���� ���� ���Ĺ�ĸ� �����ؼ� �Ķ���͸� ftName�� �ް� order���ĸ� Ʋ����
					if("��κ���".equals(cgName)){
						ptList = ptService.listPhotoByAllView(cgBean);
					}else if(cgName != null && !cgName.isEmpty()){
						//cgName�� �Ķ���ͷ� �˻�
						ptList = ptService.listPhotoByCategory(cgBean);
					}else{
						ptList = ptService.listPhotoByAllView(cgBean);
					}
					
					System.out.println("ptList��� ����"+ptList.size());
				}catch(SQLException e){
					System.out.println("ptList��� ����");
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
				mav.addObject("cgName", cgName);//�׽�Ʈ
				mav.addObject("sort", sort);
				mav.setViewName("/photo/photoList");
		
		return mav;
	}

	//search
	@RequestMapping("/photo/photo/ptSearchResult.do")
	public ModelAndView searchPhoto(String kword, String pageSize, HttpServletRequest request){
		//�Ķ����
		System.out.println("�˻��Ķ���� tag+"+kword+"pageSize"+pageSize);
		
		//db�۾�
		ArrayList <PhotoBean> alist= null;
		CategoryBean cgBean = new CategoryBean();
		
		try{
			if(kword != null && !kword.isEmpty()){
				//�±׹� Ÿ��Ʋ ��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.searchPtTag(kword);
			}else{
				//��� �˻�
				alist = (ArrayList<PhotoBean>)ptService.listPhotoByAllView(cgBean);
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
	
}//class
