package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.CommentsService;
import com.picxen.log.model.IpLogBean;
import com.picxen.log.model.IpLogService;
import com.picxen.photo.model.PhotoLikeBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoViewCountController {
	private PhotoService ptService;
	private IpLogService ilService;
	
	//comments
	private CommentsService cmService;
	
	//������
	public PhotoViewCountController(){
		System.out.println("������:PhotoViewController()");
	}
	
	//setter
	public void setPhotoService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("���Ӱ�ü ����:PhotoService"+"PhotoViewCountController()");
	}
	
	public void setIpLogService(IpLogService ilService){
		this.ilService=ilService;
		System.out.println("���Ӱ�ü ����:IpLogService"+"PhotoViewCountController()");
	}
	
	//setter-comment//�ڸ�Ʈ
		public void setCmService(CommentsService cmService){
			this.cmService=cmService;
			System.out.println("���Ӱ�ü ����:PhotoDetailController"+"CommentsService()");
	}
	

	@RequestMapping("/photo/photo/photoCountUpdate.do")
	public ModelAndView photoViewCount(HttpServletRequest request, int ptNo, IpLogBean ilBean, String ip,
			PhotoLikeBean plBean, String userid, String sort){//userMain.jsp, list.jsp���� ���� Ŭ�� �� detail������ ���� ��ȸ�� ������ ���� �Ķ���� ip, ptNo, ��ȸ����id�� �ѱ�
		//�Ķ����
		System.out.println("PhotoViewController �Ķ����:photoNo="+ptNo+", ip="+ip+", userid="+userid+", ilBean="+ilBean+", sort="+sort);
		
		//db�۾�
		int n = 0;
		int key = 0;
		
		try{
			key = ilService.insertPtlog(ilBean);
			System.out.println("������ �α� �Է� key="+key);
			
		}catch(SQLException e){
			System.out.println("�����Ƿα� �Է� ���� ����");
			e.printStackTrace();
		}
		
		try{			
			n = ptService.photoCountView(ilBean);
			System.out.println("��ȸ�� ���� ���� n="+n+", photoNo="+ilBean);//ptNo=>ilBean���� ��ü, �α״� �ԷµǴµ� ��ȸ���� �����ȵ�
			
		}catch(SQLException e){
			System.out.println("��ȸ�� ���� ����");
			e.printStackTrace();
		}
				
		
		//�����
		ModelAndView mav = new ModelAndView();//ó���ϰ� �ǵ��ƿö� ���� ������ ó���߾����� �ּ��Ķ����
		mav.addObject("userid", userid);//detail.do�� �Ѿ�� ������ �ش������ like�� ������� ��ȸ�ϱ� ���� ���� ���̵� �Ķ���ͷ� �ѱ�
		mav.addObject("ptNo", ptNo);//����Ʈ�ּҿ� ������ get����� do?ptNo="+(ptBean.photoNo) ��� ���������̾��� �Ķ���ͷ� �ѱ�
		mav.addObject("sort", sort);//�����Ϸ� ��Ʈ�̸��Ѱ��ֱ� new pop upc
		mav.setViewName("redirect:/photo/photo/photoDetail.do");//Detail�Ѿ�� �ּҵڿ� �ٴ� �Ķ���ʹ� ��Ʈ�ѷ� �Ķ���� �������
															    
		
		return mav;
	}//photoViewCount();
	

}//
