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
		System.out.println("종속객체 주입:CommentsController");		
	}
	
	@RequestMapping(value="/photo/comments/comment.do", method=RequestMethod.GET)
	public ModelAndView getComments(HttpServletRequest request, int ptNo, String userid){
		System.out.println("코멘트 파라메터 ptNo="+ptNo+", userid="+userid);
		
		MemberViewBean mvBean = null; //MemCommentViewBean은 해당 코멘트에 입력한유저 정보만 출력 
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("코멘트리스트 로딩 성공"+userid+",mvBean="+mvBean);
			
		}catch(SQLException e){
			System.out.println("코멘트리스트 로딩실패 코멘트뷰");
			e.printStackTrace();
		}
		
		ArrayList<MemCommentViewBean> cmvList = null;
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("코멘트리스트 로딩 성공"+userid+",mvBean="+mvBean);
			
			cmvList = cmService.getCmList(ptNo);
			System.out.println("코멘트리스트 로딩 성공"+cmvList.size());
		}catch(SQLException e){
			System.out.println("코멘트리스트 로딩실패 코멘트뷰");
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
/*않됨*/


/*
@Controller
public class CommentsController {
	private CommentsService cmService;
	
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		System.out.println("종속객체 주입:CommentsController");
	}
	
	@RequestMapping(value="/photo/comments/comment.do", method=RequestMethod.GET)
	public ModelAndView getCommets(HttpServletRequest request, CommentsBean cmBean, int ptNo, String userid ){
		//파라미터
		System.out.println("CommentsController: 파라미터 ptNo="+ptNo+", userid="+userid+", cmBean"+cmBean);
		
		//db
		ArrayList<CommentsBean> alist = null;
		MemberViewBean mvBean = null;
		
		int memberNo=0;
		try{
			mvBean = cmService.getMyAvatar(userid);
			System.out.println("유저 아바타 조회성공 mvBean="+mvBean+", userid="+userid);
			memberNo = mvBean.getMemberNo();
			System.out.println("memberNo="+memberNo);
		}catch(SQLException e){
			System.out.println("유저 코멘트 조회 실패");
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
		//파라미터
		System.out.println("코맨트 파라미터: cmBean="+cmBean+", userid="+userid+", ptNo="+ptNo);
		
		//db
		int commentUser = 0;
		try{
			commentUser = cmService.getMemNumber(userid); 
			System.out.println("멤버No 조회성공 memberNo="+commentUser);
		}catch(SQLException e){
			System.out.println("멤버No 조회 실패");
			e.printStackTrace();
		}
		
		cmBean.setCommentUser(commentUser);
		
		int key= 0;
		try{
			key=cmService.insertComments(cmBean);
			System.out.println("코멘트 입력 성공 key="+key+", cmBean="+cmBean);
		}catch(SQLException e){
			System.out.println("코멘트 입력 실패"+userid);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user_commentView", key);
		mav.addObject("userid",userid);
		mav.addObject("ptNo",ptNo);
		mav.addObject("cmBean", cmBean);
		mav.setViewName("redirect:/photo/comments/comment.do");//ptNo가 주소에 붙어있어 mav.addobject에 않넣어도됨
		
		return mav;
	}
	
	
}//
*/