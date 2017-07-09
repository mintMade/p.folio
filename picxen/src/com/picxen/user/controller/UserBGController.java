package com.picxen.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.user.model.UserMainService;

@Controller
public class UserBGController {
	private UserMainService umService;
	
	public UserBGController(){
		System.out.println("������:UserBGController");
	}
	
	public void setUmService(UserMainService umService){
		this.umService=umService;
		System.out.println("���Ӱ�ü:UserBGController"+"umService");
	}
	
	@RequestMapping("/user/user/userBG.do")
	public ModelAndView uploadUserBG(HttpServletRequest request, int bgNo, String userid){
		System.out.println("userBG �Ķ����:bgNo="+bgNo+", userid="+userid);
	
		//db
		String sort = request.getParameter("sort");
		
		int result=0;
		try{
			//������ȣ �ϳ��� ���� ���δ��� ������Ʈ���� �׷��� �ٸ������ ������Ʈ�ϰ� ���ַ��� Ȯ�强�� ����ؾ��ϱ� ������ ���� ���̵� �ް��� �Ķ���͸� MAP���� ó��
			//bgMap�� bgNo�� userid�� ���� ���̺��ڵ忡 �����ʾ� ������ ó��
			Map<String, Object> bgMap = new HashMap<String, Object>();
			bgMap.put("bgNo", bgNo);
			bgMap.put("userid", userid);
			System.out.println("bgMap�Ķ����="+bgMap);
			result = umService.upUserBG(bgMap);
			
			System.out.println("result= "+result);
			
		}catch(SQLException e){
			System.out.println("���� BG ���� ����");
			e.printStackTrace();
		}
		
		int ptNo = bgNo;//photodetailController �� �Ѱ��� ������ȣ
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ptNo", ptNo);
		mav.addObject("sort", sort);
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		return mav;
	}
}//
