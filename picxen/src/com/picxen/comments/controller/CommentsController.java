package com.picxen.comments.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CommentsBean;
import com.picxen.comments.model.CommentsService;
import com.picxen.comments.model.MemCommentViewBean;
import com.picxen.comments.model.MemberViewBean;


/*@Controller
public class CommentsController{
	private CommentsService cmService;
	
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		System.out.println("���Ӱ�ü ����:CommentsController");		
	}
	
	@RequestMapping(value="/photo/comments/comment.do", method=RequestMethod.GET)
	public ModelAndView getComments(HttpServletRequest request, int ptNo, String userid){
		System.out.println("�ڸ�Ʈ �Ķ���� ptNo="+ptNo+", userid="+userid);
		
		MemberViewBean mvBean = null; //MemCommentViewBean�� �ش� �ڸ�Ʈ�� �Է������� ������ ��� 
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("�ڸ�Ʈ����Ʈ �ε� ����"+userid+",mvBean="+mvBean);
			
		}catch(SQLException e){
			System.out.println("�ڸ�Ʈ����Ʈ �ε����� �ڸ�Ʈ��");
			e.printStackTrace();
		}
		
		ArrayList<MemCommentViewBean> cmvList = null;
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("�ڸ�Ʈ����Ʈ �ε� ����"+userid+",mvBean="+mvBean);
			
			cmvList = cmService.getCmList(ptNo);
			System.out.println("�ڸ�Ʈ����Ʈ �ε� ����"+cmvList.size());
		}catch(SQLException e){
			System.out.println("�ڸ�Ʈ����Ʈ �ε����� �ڸ�Ʈ��");
			e.printStackTrace();
		}
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("cmvList", cmvList);
		mav.addObject("mvBean", mvBean);
		mav.setViewName("/photo/photo/photoDetail");
	
		return mav;
	}
	
}//
*/
/*�ʵ�*/


/*
@Controller
public class CommentsController {
	private CommentsService cmService;
	
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		System.out.println("���Ӱ�ü ����:CommentsController");
	}
	
	@RequestMapping(value="/photo/comments/comment.do", method=RequestMethod.GET)
	public ModelAndView getCommets(HttpServletRequest request, CommentsBean cmBean, int ptNo, String userid ){
		//�Ķ����
		System.out.println("CommentsController: �Ķ���� ptNo="+ptNo+", userid="+userid+", cmBean"+cmBean);
		
		//db
		ArrayList<CommentsBean> alist = null;
		MemberViewBean mvBean = null;
		
		int memberNo=0;
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("���� �ƹ�Ÿ ��ȸ���� mvBean="+mvBean+", userid="+userid);
			memberNo = mvBean.getMemberNo();
			System.out.println("memberNo="+memberNo);
		}catch(SQLException e){
			System.out.println("���� �ڸ�Ʈ ��ȸ ����");
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("mvBean", mvBean);
		mav.addObject("userid",userid);
		mav.addObject("ptNo", ptNo);
		mav.setViewName("/photo/photo/photoDetail");
		mav.setViewName("/photo/comments/comment");		
		
		return mav;
	}
	
	@RequestMapping(value="/photo/comments/comment.do", method=RequestMethod.POST)
	public ModelAndView postPhotoDetail(HttpServletRequest request, CommentsBean cmBean,String userid, int ptNo){
		//�Ķ����
		System.out.println("�ڸ�Ʈ �Ķ����: cmBean="+cmBean+", userid="+userid+", ptNo="+ptNo);
		
		//db
		int commentUser = 0;
		try{
			commentUser = cmService.getMemNumber(userid); 
			System.out.println("���No ��ȸ���� memberNo="+commentUser);
		}catch(SQLException e){
			System.out.println("���No ��ȸ ����");
			e.printStackTrace();
		}
		
		cmBean.setCommentUser(commentUser);
		
		int key= 0;
		try{
			key=cmService.insertComments(cmBean);
			System.out.println("�ڸ�Ʈ �Է� ���� key="+key+", cmBean="+cmBean);
		}catch(SQLException e){
			System.out.println("�ڸ�Ʈ �Է� ����"+userid);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user_commentView", key);
		mav.addObject("userid",userid);
		mav.addObject("ptNo",ptNo);
		mav.addObject("cmBean", cmBean);
		mav.setViewName("redirect:/photo/comments/comment.do");//ptNo�� �ּҿ� �پ��־� mav.addobject�� �ʳ־��
		
		return mav;
	}
	
	
}//
*/