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
		//컨트롤러전 수행전 수행됨
		//클라이언트 요청을 컨트롤러에 전달하기 전에 호출됨
		System.out.println("preHandle() 메서드 호출");
		//모든 페이지 카테고리 목록이 보여져야 하므로 preHandle에서 처리
		
		//db작업-select
		List<CategoryBean> cgList = new ArrayList<CategoryBean>();
		try{
			cgList = cgService.listCategoryAll();
			System.out.println("카테고리 목록 조회 성공, cgList.size()"+cgList.size());
		}catch(SQLException e){
			System.out.println("카테고리 목록 조회 실패");
			e.printStackTrace();
		}
		
		//유저 아이콘 조회
			String iconUserid = (String)request.getSession().getAttribute("userid");
			System.out.println("iconUserid="+iconUserid);
			
			String userIcon="";
		if(iconUserid != null && !iconUserid.isEmpty()){	
			try{
			userIcon = memService.getMyIcon(iconUserid);
			System.out.println("유저 아바타 조회 성공"+userIcon);
			}catch(SQLException e){
				System.out.println("유저 아이콘 조회 실패");
				e.printStackTrace();
			}
		}
		
		/*//클라이언트 ip => 사진 클릭시 viewUpdateController에서 체크
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
