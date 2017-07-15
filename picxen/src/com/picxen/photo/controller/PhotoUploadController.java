package com.picxen.photo.controller;

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
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoUploadController {
	private CategoryService cgService;
	private PhotoService ptService;
	
	//setter
	public void setCgService(CategoryService cgService){
		this.cgService=cgService;
		System.out.println("������:PhotoUploadController");
	}
	
	public void setPtServcie(PhotoService ptService){
		this.ptService=ptService;
	}

	
	@RequestMapping(value="/photo/ptUpload/ptUp.do", method=RequestMethod.GET)
	public ModelAndView ptUploadGet(HttpSession session){
		//��ǰ ��� ȭ�� �����ֱ�
		//ī�װ� ��� ��ȸ�ؾ���
		//db�۾�-select		

		ModelAndView mav = new ModelAndView();
		String userid=(String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũ)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		/// �α���
		mav.addObject("uploader", userid);
		mav.setViewName("/photo/ptUpload/ptUp");
		
		return mav;
	}
	
	@RequestMapping(value="/photo/ptUpload/ptUp.do", method=RequestMethod.POST)
	public ModelAndView ptUploadPost(PhotoBean ptBean, HttpSession session, String uploader){
		//�Ű������� �Ѿ���� �� Ŭ���� => Command Ŭ����(��ü)
		
		//db�� ��ǰ insert, ��ǰ�̹��� ���� ���ε� ó��
		//1.�Ķ����
		//=>���δ� ��ȸ
		/*String userid=(String)session.getAttribute("userid");*/
		System.out.println("ptUploadPost(),�Ķ���� ptBean="+ptBean+", ���δ�="+uploader);
		
		
		//�α��ε� ��쿡�� ó��
		ModelAndView mav = new ModelAndView();
		
		
		//���� ���ε� ó��
		//����ڰ� ���ε��Ϸ��� ������ ����-�ӽ����� ����
		MultipartFile tempFile = ptBean.getUploadFile();
		System.out.println("getUpload����="+ptBean.getUploadFile());
		
		//������ ������ �̸�
		String oName=tempFile.getOriginalFilename();
		
		//���ε� ���-���� �������� ��� ���ϱ�
		//upPath = session.getServletContext().getRealPath(upPath);
		String upPath = Utility.PT_IMG_PATH;
		
		//�����̸��� �ߺ��Ǵ� ���, ������ �̸����� ��������=>  _�Ϸù�ȣ
		String imageUrl = Utility.getUniqueFileName(upPath, oName);
		//��>�������� ��imageUrl�� �Ѱ��൵�ǳ�.. �ȵɰͰ��� �켱 �����ϰ� �������� �ҷ��;��ϱ� ������ �ؿ����� ó���� �ε�
		
	
		//���� ���ε� ó��
		File myfile=new File(upPath, imageUrl);
		
		try{
			tempFile.transferTo(myfile);
			System.out.println("���� ���ε� ó�� ����!"+myfile+", tempFile="+tempFile);
		}catch(IllegalStateException e){
			System.out.println("���� ���ε� ó�� ����!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("���� ���ε� ó�� ����!!");
			e.printStackTrace();
		}
		
		//3.��� ��������
		//!ptBean�� ���� ���ε����� ��������
		ptBean.setImageURL(imageUrl);
		ptBean.setUploader(uploader);
		
		mav.addObject("imageUrl", imageUrl);
		mav.addObject("uploader", uploader);
		mav.setViewName("/photo/ptUpload/ptPreview");
		return mav;
	}

//���� ���ε� �����Է� ������	
	@RequestMapping("/photo/ptUpload/ptPreview.do")
	public ModelAndView postPtUpload(PhotoBean ptBean, String imageURL, String uploader, HttpSession session){
		System.out.println("POST�������ε� ���������� �Ķ����:uploader="+uploader+", imageUrl="+imageURL+", photoBean="+ptBean);
		
		//db
		int n=0;
		try{
			n=ptService.uploadPhoto(ptBean);
			System.out.println("���� ���ε� ����!, n="+n);
		}catch(SQLException e){
			System.out.println("���� ��� ����!!");
			e.printStackTrace();
		}
				
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/user/user/userMain.do?userid="+uploader);
		return mav;
	}//ptUpload
}