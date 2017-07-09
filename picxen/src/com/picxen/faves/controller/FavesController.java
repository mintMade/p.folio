package com.picxen.faves.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.MemberViewBean;
import com.picxen.faves.model.FavesBean;
import com.picxen.faves.model.FavesService;
import com.picxen.photo.model.PhotoLikeBean;

@Controller
public class FavesController {
	private FavesService fvService;
	/*private PhotoService ptService;*/
	
	public void setFvService(FavesService fvService){
		this.fvService=fvService;
	}
	
	/*public void setPtService(PhotoService ptService){
		this.ptService=ptService;
	}*/
	
	@RequestMapping("/photo/faves/favesAdd.do")
	public ModelAndView insertFaves(HttpServletRequest request, HttpSession session, String fUserId, int photoNo, FavesBean fvBean, String sort){
		//�Ķ����
		System.out.println("FavesController:fUserId="+fUserId+", photoNo="+photoNo+"fvBean"+fvBean+", sort="+sort);
		
		ModelAndView mav = new ModelAndView();
	
		String userid= (String) session.getAttribute("userid");
		if(userid==null || userid.isEmpty()){
			mav.addObject("msg", "�α��� �����ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("inc/message");
			
			return mav;
		}
		
		//db�۾�
		/*FavesBean fvBean = new FavesBean(); ���ã�� �߰� ���� �α��μ�Ʈ �� ���ã�� ī��Ʈ������Ʈ�ϴ� ���*/
		int key = 0;
		
		try{
			key = fvService.insertFaves(fvBean);
			System.out.println("���ã�� �߰� ����, key="+key+", fvBean="+fvBean);
		}catch(SQLException e){
			System.out.println("���ã�� �߰� ����");
			e.printStackTrace();
		}
		//�����������
		mav.addObject("userid", userid);//detail.do�� �����Ҷ� ������ �ش������ like�� ������� ��ȸ�ϱ� ���� ���� ���̵� �Ķ���ͷ� �ѱ� viewCntController�� ����
		mav.addObject("sort", sort);
		mav.setViewName("redirect:/photo/photo/photoDetail.do?ptNo="+photoNo);//���ã�� Ŭ���� ���µǴ� �ּ�, �Ķ����
		return mav;
	}
	
	//���� �����̹�������Ʈ �� ��ũ��Ʈ�ѷ�
	@RequestMapping("/photo/faves/faveList.do")
	public ModelAndView listByfaves(int ptNo, HttpServletRequest request){
		System.out.println("faveList���� ��Ʈ�ѷ� �Ķ����"+ptNo);
		List<MemberViewBean> fList = null;
		List<MemberViewBean> lList = null;
		String uploader = null;//uploader;
		
		try{
			fList = fvService.getFaveList(ptNo);
			lList = fvService.getLikeList(ptNo);
			uploader = fvService.getUploader(ptNo);
			System.out.println("���� ����Ʈ �ε� ����"+fList.size()+", ���δ�="+uploader);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("���� ����Ʈ �ε� ����");
		}
		request.setAttribute("uploader", uploader);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("faveList", fList);
		mav.addObject("faveSize", fList.size());
		mav.addObject("likeList", lList);
		mav.addObject("likeSize", lList.size());
		mav.setViewName("/photo/faves/faveList");
		
		return mav;
	}
}//
