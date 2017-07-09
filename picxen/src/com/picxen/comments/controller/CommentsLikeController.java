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
	
	//생성자
	public CommentsLikeController(){
		System.out.println("생정자:CommentsLikeController");
	}
		
	//setter
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		System.out.println("종속객체 주입:CommentsLikeController"+"+CommentsService()");
	}
	
	@RequestMapping("/photo/comments/commentsLike.do")
	public ModelAndView upCommentsLike(int ptNo, int commentNo, int commentUser, CmLikeBean clBean, String userid, int sortNo, String sort){
		//파라미터
		System.out.println("CommentLikeController ptNo="+ptNo+", commentNo="+commentNo+", commentUser="+commentUser+/*", clBean="+clBean+*/
				", userid="+userid+", sortNo"+sortNo+", sort="+sort);
		
		//db
		/*CmLikeBean clBean = null;*/
		int n=0;
		
		try{
			n=cmService.upCmLike(clBean);
			System.out.println("코멘트 like 성공 n="+n+"clBean"+clBean);
			
		}catch(SQLException e){
			System.out.println("코멘트 like실패");
			e.printStackTrace();
		}
		
		//결과뷰페이지
		ModelAndView mav= new ModelAndView();
		mav.addObject("sort", sort);
		mav.addObject("ptNo", ptNo);
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		return mav;
	}
		
}//
