package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	
		
	@RequestMapping(value="/photo/photoCountUpdate.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView photoViewCount(HttpServletRequest request, PhotoLikeBean plBean){

		IpLogBean ilBean = new IpLogBean();
		//parameters
		String sort = (String)request.getParameter("sort");
		String strPtNo = (String)request.getParameter("ptNo");
		Integer ptNo = Integer.parseInt(strPtNo);
		String cgName = (String)request.getParameter("cgName");
		System.out.println("viewPtPARAMETERS sort="+sort+", ptNo="+ptNo+", cgName="+cgName);
		
		
		//ip
		request = (HttpServletRequest) ((ServletRequestAttributes)
					RequestContextHolder.currentRequestAttributes()).getRequest();
		String logIp = request.getHeader("X-FORWARDED-FOR");
		
		if(logIp == null || logIp.length() == 0 ) {
			logIp =request.getHeader("proxy-Client-IP");
		}
		
		if(logIp == null || logIp.length() == 0 ) {
			logIp =request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(logIp == null || logIp.length() == 0) {
			logIp = request.getRemoteAddr();
		}
		
		
		//uid
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		if(userid == null || userid.isEmpty()) {
			userid="";
		}
		
		ilBean.setPtNo(ptNo);
		ilBean.setUserid(userid);
		ilBean.setIp(logIp);
		System.out.println("ilBean="+ilBean);
		//db�۾�
		int n = 0;
		
		try{
			//iplog���� ilBean�� ������ ������Ʈ ������ ������Ʈ x
			//merge�� iplog�� ������ x������ insert, userid�� ���� ������ userid�� �ش簪 ������Ʈ
			n = ptService.photoCountView(ilBean);
			ilService.insertPtlog(ilBean);
			System.out.println("������ �α� �Է� && ��ȸ�� ����="+n);
		}catch(SQLException e){
			System.out.println("�����Ƿα� �Է� ���� ����");
			e.printStackTrace();
		}
		
		//�����
		ModelAndView mav = new ModelAndView();//ó���ϰ� �ǵ��ƿö� ���� ������ ó���߾����� �ּ��Ķ����
		mav.addObject("userid", userid);//detail.do�� �Ѿ�� ������ �ش������ like�� ������� ��ȸ�ϱ� ���� ���� ���̵� �Ķ���ͷ� �ѱ�
		mav.addObject("ptNo", ptNo);//����Ʈ�ּҿ� ������ get����� do?ptNo="+(ptBean.photoNo) ��� ���������̾��� �Ķ���ͷ� �ѱ�
		mav.addObject("sort", sort);//�����Ϸ� ��Ʈ�̸��Ѱ��ֱ� new pop upc
		mav.addObject("cgName", cgName);
		/*mav.setViewName("redirect:/photo/photo/photoDetail.do");//Detail�Ѿ�� �ּҵڿ� �ٴ� �Ķ���ʹ� ��Ʈ�ѷ� �Ķ���� �������
*/		mav.setViewName("/photo/photoCountUpdate");//Detail�Ѿ�� �ּҵڿ� �ٴ� �Ķ���ʹ� ��Ʈ�ѷ� �Ķ���� �������
	    
		
		return mav;
	}//photoViewCount();
	

}//
