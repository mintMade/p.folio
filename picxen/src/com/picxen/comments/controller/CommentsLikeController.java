package com.picxen.comments.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.CommentsService;

@Controller
public class CommentsLikeController {
	private CommentsService cmService;
	
	//������
	public CommentsLikeController(){
		System.out.println("������:CommentsLikeController");
	}
		
	//setter
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		System.out.println("���Ӱ�ü ����:CommentsLikeController"+"+CommentsService()");
	}
	
	@RequestMapping("/photo/comments/commentsLike.do")
	public ModelAndView upCommentsLike(int ptNo, int commentNo, int commentUser, CmLikeBean clBean, String userid, int sortNo, String sort){
		//�Ķ����
		System.out.println("CommentLikeController ptNo="+ptNo+", commentNo="+commentNo+", commentUser="+commentUser+/*", clBean="+clBean+*/
				", userid="+userid+", sortNo"+sortNo+", sort="+sort);
		
		//db
		/*CmLikeBean clBean = null;*/
		int n=0;
		
		try{
			n=cmService.upCmLike(clBean);
			System.out.println("�ڸ�Ʈ like ���� n="+n+"clBean"+clBean);
			
		}catch(SQLException e){
			System.out.println("�ڸ�Ʈ like����");
			e.printStackTrace();
		}
		
		//�����������
		ModelAndView mav= new ModelAndView();
		mav.addObject("sort", sort);
		mav.addObject("ptNo", ptNo);
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		return mav;
	}
		
}//
