//CommentsReplyController는 삭제 대상
package com.picxen.comments.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CommentsBean;
import com.picxen.comments.model.CommentsService;

@Controller
@RequestMapping("/photo/comments/cmReply.do")
public class CommentReplyController {
	private CommentsService cmService;
	
	public CommentReplyController(){
		System.out.println("생성자:CommentReplyController");
	}
	//setter
	public void setCmService(CommentsService cmService){
		this.cmService = cmService;
		System.out.println("종속객체 : CommentsService"+"CommentController");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getCmRpl(/*int commentNo*/){
		/*System.out.println("replyController:commentNo"+commentNo);
		
		//db 리플달 코멘트번호
		CommentsBean cmBean = null;
		
		try{
			cmBean = cmService.getComment(commentNo); 
			System.out.println("reply로딩 성공cmBean"+cmBean);
		}catch(SQLException e){
			System.out.println("reply로딩 실패");
			e.printStackTrace();
		}*/
		
		ModelAndView mav = new ModelAndView();
		/*리플 입력 누가입력? bean으로 포스트에 넘겨주기*/
		mav.setViewName("/photo/comments/cmReply");
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertCmtRply(CommentsBean cmBean, String userid, int ptNo, int sortNo, String sort/*, int commentNo, int step*/){
		//파라미터
		System.out.println("리플컨트롤러:cmBean"+cmBean+", userid="+userid+", photoNo="+ptNo+", sortNo="+sortNo+", sort="+sort/*+"commentNo"+commentNo*/);
		
		//db입력
		
		//cmBean에 유저 넘버 구해서 주입
		int commentUser = 0;
		try{
			commentUser = cmService.getMemNumber(userid);
			System.out.println("리플 commentUser 추출 성공:"+commentUser);
		}catch(SQLException e){
			System.out.println("리플 commentUser 추출 실패");
			e.printStackTrace();
		}
		
		//추출한 commentUser를 cmBean에 주입
		cmBean.setCommentUser(commentUser);
		System.out.println("commentUser=="+cmBean);
				
		int key = 0;
		try{
			key=cmService.insertTrply(cmBean);
			System.out.println("리플 입력 성공! cmBean:"+cmBean+", key= "+key);
		}catch(SQLException e){
			System.out.println("리플입력 실패");
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		
		/*mav.addObject("cmBean");*/ //안넘겨줘도 기본적으로 코멘트 출력
		mav.addObject("userid", userid);
		mav.addObject("ptNo", ptNo);
		mav.addObject("sortNo", sortNo);
		mav.addObject("sort", sort);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		
		return mav;
	}
}//
