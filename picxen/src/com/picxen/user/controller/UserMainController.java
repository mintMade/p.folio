package com.picxen.user.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.PagingBean;
import com.picxen.faves.model.FavesBean;
import com.picxen.log.model.IpLogBean;
import com.picxen.log.model.IpLogService;
import com.picxen.member.model.MemberBean;
import com.picxen.photo.model.PhotoBean;
import com.picxen.user.model.UserMainService;

@Controller
public class UserMainController {
	private UserMainService uMainService;
	private IpLogService ilService;
	
	public UserMainController(){
		System.out.println("������:UserMainController()");
	}
	
	//setter
	public void setuMainService(UserMainService uMainService){
		this.uMainService=uMainService;
		System.out.println("���Ӱ�ü ����:UserMainService+"+"UserMainController()");
	}
	
	
	@RequestMapping("/user/user/userMain.do")
	public ModelAndView userHome(HttpServletRequest request, String userid, IpLogBean ilBean, String ip,
				Integer mainCg /*mainCg = ���� ���� ī�װ����ù�ȣ*/){
		//�������̵� �̿��Ͽ� ����Ȩ���� �̵�
		//1.�Ķ����
		System.out.println("userid="+userid+"����Ȩip="+ip+"ilBean="+ilBean+"mainCg"+mainCg);
				
		//db�۾�
		ArrayList<PhotoBean> alist = null;//���� ����Ʈ
		List<PhotoBean> blist = null;//�ٹ�����Ʈ
		MemberBean mbBean=new MemberBean();
		boolean result=false;
		int likeTtl = 0;
		int viewTtl = 0;
		int faveTtl = 0;
		String umId=null;
		umId=userid;
		
		try{
			if(mainCg == null){
				mainCg = 1;
				System.out.println("mainCCCG="+mainCg);
			}
		}catch(Exception e){
			System.out.println("�������� ī�װ� ��ȸ����");
			e.printStackTrace();
		}
		
		try{
			//userid�Ķ���� �̿��ؼ� ���� ���� ����ϱ�
			mbBean = uMainService.userInfo(umId);
			System.out.println("����Ȩ���� �̵� ����, mbBean"+mbBean+"umId="+umId);
			
			//�������ƿ� �հ�
			likeTtl= uMainService.likeTotal(umId);
			System.out.println("���ƿ� ���հ�="+likeTtl);
			
			//���� ���հ�
			viewTtl= uMainService.viewTotal(umId);
			System.out.println("�� ���հ�="+viewTtl);
			
			//�۰���
			faveTtl = uMainService.faveTotal(umId);
			System.out.println("�۰��� ���հ�"+faveTtl);
			
			//����Ȩ->������������Ʈ
			//�������̵� Ű�� �ø����� ��ȸ
			alist = uMainService.myPtList(umId);
			System.out.println("���ε� ����Ʈ ��¼���, alist.size()="+alist.size()+"umId="+umId);
			
			//����Ȩ->�ۿ»��� ����Ʈ
			blist =uMainService.myAbmList(umId);
			System.out.println("�� �ٹ�����Ʈ��� ����, blist.size()="+blist.size()+"umId="+umId);
			
		}catch(SQLException e){
			System.out.println("����Ȩ:�� ��ȸ ����@"+mbBean);
			e.printStackTrace();
		}
		
		
		//��� ��������
		int pageSize=5;//
		int blockSize=10;//
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);//
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ipResult", result);
		mav.addObject("alist", alist);//
		mav.addObject("totalRecord", alist.size());//����������Ʈ
		mav.addObject("pb", pb);//
		mav.addObject("mbBean", mbBean);
		mav.addObject("ltotal", likeTtl);
		mav.addObject("vtotal", viewTtl);
		mav.addObject("ftotal", faveTtl);
		mav.addObject("mainCg", mainCg);
		mav.addObject("blist", blist);
		mav.addObject("myAlbmList", blist.size());
		
		mav.setViewName("/user/user/userMain");
		
		return mav;
	}
	
}/////
