package com.picxen.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.picxen.member.model.MemberService;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;

public class CommonHandlerInterceptor extends HandlerInterceptorAdapter {
	private CategoryService cgService;
	
	private MemberService memService;
	
	//setter
	public void setCgService(CategoryService cgService){
		this.cgService = cgService;
	}
	
	public void setMemService(MemberService memService){
		this.memService = memService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{ //
		//��Ʈ�ѷ��� ������ �����
		//Ŭ���̾�Ʈ ��û�� ��Ʈ�ѷ��� �����ϱ� ���� ȣ���
		System.out.println("preHandle() �޼��� ȣ��");
		//��� ������ ī�װ� ����� �������� �ϹǷ� preHandle���� ó��
		
		//db�۾�-select
		List<CategoryBean> cgList = new ArrayList<CategoryBean>();
		try{
			cgList = cgService.listCategoryAll();
			System.out.println("ī�װ� ��� ��ȸ ����, cgList.size()"+cgList.size());
		}catch(SQLException e){
			System.out.println("ī�װ� ��� ��ȸ ����");
			e.printStackTrace();
		}
		
		//���� ������ ��ȸ
			String iconUserid = (String)request.getSession().getAttribute("userid");
			System.out.println("iconUserid="+iconUserid);
			
			String userIcon="";
		if(iconUserid != null && !iconUserid.isEmpty()){	
			try{
			userIcon = memService.getMyIcon(iconUserid);
			System.out.println("���� �ƹ�Ÿ ��ȸ ����"+userIcon);
			}catch(SQLException e){
				System.out.println("���� ������ ��ȸ ����");
				e.printStackTrace();
			}
		}
		
		/*//Ŭ���̾�Ʈ ip => ���� Ŭ���� viewUpdateController���� üũ
		request = (HttpServletRequest) ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.isEmpty()) {
			ip = request.getRemoteAddr();
		}
		System.out.println("preIp="+ip);
		request.setAttribute("preIp", ip);*/
		request.setAttribute("userIcon", userIcon);
		request.setAttribute("cgList", cgList);
		request.setAttribute("totalRecord", cgList.size());
		
		return true;
	}
	
	
}//class
