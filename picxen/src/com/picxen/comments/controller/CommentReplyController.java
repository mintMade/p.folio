//CommentsReplyController�� ���� ���
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
		System.out.println("������:CommentReplyController");
	}
	//setter
	public void setCmService(CommentsService cmService){
		this.cmService = cmService;
		System.out.println("���Ӱ�ü : CommentsService"+"CommentController");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getCmRpl(/*int commentNo*/){
		/*System.out.println("replyController:commentNo"+commentNo);
		
		//db ���ô� �ڸ�Ʈ��ȣ
		CommentsBean cmBean = null;
		
		try{
			cmBean = cmService.getComment(commentNo); 
			System.out.println("reply�ε� ����cmBean"+cmBean);
		}catch(SQLException e){
			System.out.println("reply�ε� ����");
			e.printStackTrace();
		}*/
		
		ModelAndView mav = new ModelAndView();
		/*���� �Է� �����Է�? bean���� ����Ʈ�� �Ѱ��ֱ�*/
		mav.setViewName("/photo/comments/cmReply");
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertCmtRply(CommentsBean cmBean, String userid, int ptNo, int sortNo, String sort/*, int commentNo, int step*/){
		//�Ķ����
		System.out.println("������Ʈ�ѷ�:cmBean"+cmBean+", userid="+userid+", photoNo="+ptNo+", sortNo="+sortNo+", sort="+sort/*+"commentNo"+commentNo*/);
		
		//db�Է�
		
		//cmBean�� ���� �ѹ� ���ؼ� ����
		int commentUser = 0;
		try{
			commentUser = cmService.getMemNumber(userid);
			System.out.println("���� commentUser ���� ����:"+commentUser);
		}catch(SQLException e){
			System.out.println("���� commentUser ���� ����");
			e.printStackTrace();
		}
		
		//������ commentUser�� cmBean�� ����
		cmBean.setCommentUser(commentUser);
		System.out.println("commentUser=="+cmBean);
				
		int key = 0;
		try{
			key=cmService.insertTrply(cmBean);
			System.out.println("���� �Է� ����! cmBean:"+cmBean+", key= "+key);
		}catch(SQLException e){
			System.out.println("�����Է� ����");
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		
		/*mav.addObject("cmBean");*/ //�ȳѰ��൵ �⺻������ �ڸ�Ʈ ���
		mav.addObject("userid", userid);
		mav.addObject("ptNo", ptNo);
		mav.addObject("sortNo", sortNo);
		mav.addObject("sort", sort);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		
		return mav;
	}
}//
