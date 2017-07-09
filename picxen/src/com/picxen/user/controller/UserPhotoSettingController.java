package com.picxen.user.controller;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javafx.scene.control.ListCell;

import javax.servlet.http.HttpSession;
import javax.swing.event.ListDataEvent;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.Utility;
import com.picxen.photo.model.PhotoBean;
import com.picxen.user.model.UserMainService;

@Controller
public class UserPhotoSettingController {
	private UserMainService umService;
	
	public void setUmService(UserMainService umService){
		this.umService = umService;
	}
	
	public UserPhotoSettingController(){
		System.out.println("������:UserPhotoSettingController");
	}
	
	@RequestMapping(value="/user/user/setPhoto.do", method=RequestMethod.GET)
	public ModelAndView getUserPtSetting(HttpSession session){
		System.out.println("�̹��� edit ������ GET");
		
		//db
		String userid = (String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũ)="+userid);
				
		ArrayList<PhotoBean> ptList = null;
		/*try{ 
			if("ptNo" != null && !"ptNo".isEmpty()){//null�ϰ�� �ڸ�Ʈ��  submit �Ҷ� ����
				Integer ptNo = Integer.parseInt(request.getParameter("ptNo"));//int�� parseInt�� ����
				System.out.println("PtNo= "+ptNo);
			}
		}catch(Exception e){
			System.out.println("ptNo request.getParameter ����");
			e.printStackTrace();
		}*/
		/*Integer ptNo = Integer.parseInt(request.getParameter("ptNo"));*/
		
		/*int ptNo =*/ 
		
		PhotoBean ptBean = new PhotoBean();
		List<String> listA = new ArrayList<String>();
		List<String> listB = new ArrayList<String>();
		List<List<String>> lol = new ArrayList<>();
		try{
			ptList = umService.listPtByTerm(userid);
			/*ptBean = ptService.viewPhoto(ptNo);*///�̹�������		
						
			if(ptList != null && !ptList.isEmpty()){//ptList�� ���� ���� ���(Timestamp�� String���� ��ȯ�� �ߺ����Ÿ���Ʈ �ѱ��� ��Ÿ������Ʈ) 
				for(int i=0; i<ptList.size();i++){ //���� for���� �����Ұ�
					
					ptBean = (PhotoBean)ptList.get(i);
					Date rDate = ptBean.getRegdate();//Timestamp�� Date�� ��ȯ
										
					SimpleDateFormat regFmat = new SimpleDateFormat("yyyy�� MM��");
					String strDate = regFmat.format(rDate); //String���� ��ȯ
					
					listA.add(strDate);
				
												
				}//for()
				listB = new ArrayList<String>(new LinkedHashSet<String>(listA));//�ߺ�����
				
				
				List<Integer> liCnt = new ArrayList<Integer>();//�ߺ� ����
				for(int r = 0; r<listB.size(); r++){
					String sCnt = listB.get(r);
					
					liCnt.add(Collections.frequency(listA, sCnt));
				}
				
				///////////////// [ [AA], [BB] ] �ߺ����� 2�� ����Ʈ����� =>input Map
				Map<String, Integer> countMap = new HashMap<String, Integer>();
				
				for(int e=0; e<listA.size();e++){
					/*String ds = listA.get(e);*/
					Integer dayCnt = countMap.get(listA.get(e));
					
					countMap.put(listA.get(e), dayCnt==null?1: dayCnt+1);
				}
				
				//map=>ouput
				for(Map.Entry<String, Integer> elem : countMap.entrySet()){
					String k = elem.getKey();
					Integer v = elem.getValue();
					List<String> list = new ArrayList<>();
					for(int i = 0; i < v; i++) /*{*/// �ѹ��� �ݺ�
						list.add(k);
						lol.add(list);
				}
				
			}//if()
									
			System.out.println("�̹��� ����Ʈ ��ȸ ���� ptList.size()="+ptList.size());
			
		}catch(SQLException e){
			System.out.println("�̹�����������Ʈ ��ȸ����");
			e.printStackTrace();
		}
		
		//���
		ModelAndView mav = new ModelAndView();
		/*String userid = (String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũ)="+userid);*/
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		
		mav.addObject("userid", userid);
		mav.addObject("ptList", ptList);
		mav.addObject("totalOfPtList", ptList.size());
		mav.addObject("bList", listB);
		mav.addObject("totalB", listB.size());
		
		/*mav.addObject("ptBean", ptBean);
		mav.addObject("ptNo", ptNo);*/
		
		mav.setViewName("/user/user/setPhoto");
	
		return mav;
	}
	




	@RequestMapping(value="/user/user/setPhoto.do", method=RequestMethod.POST)
	public ModelAndView postUserPtSetting(HttpSession session, PhotoBean ptBean){
		System.out.println("�Ķ���� �����̹�������Post PhotoBean="+ptBean);
		
		//db
		String userid = (String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũ)="+userid);
				
		int n = 0;
		try{
			n = umService.upUserPt(ptBean);
			System.out.println("�̹��� �����Ϸ�"+n);
		}catch(SQLException e){
			System.out.println("���� �̹��� ���� ����");
			e.printStackTrace();
		}
		
		//���
				ModelAndView mav = new ModelAndView();
				/*String userid = (String)session.getAttribute("userid");
				System.out.println("�������̵�(�α���üũ)="+userid);*/
				if(userid == null || userid.isEmpty()){
					mav.addObject("msg", "�α��� ���� �ϼ���");
					mav.addObject("url", "../../member/login.do");
					mav.setViewName("/inc/message");
					
					return mav;
				}
				
		mav.addObject("userid", userid);
		mav.setViewName("/user/user/setPhoto");
		return mav;
	}
	
	@RequestMapping("/user/user/ptDel.do")
	public ModelAndView ptDel(HttpSession session, int delPtNo, String imageURL){
		System.out.println("�̹������� ptNo = "+delPtNo+", imageURL= "+imageURL);
		String userid = (String)session.getAttribute("userid");
		System.out.println("�̹��� ���� ����üũ= "+userid);
		
		ModelAndView mav = new ModelAndView();
		
			if(userid == null || userid.isEmpty()){//�α���üũ
				mav.addObject("msg", "�α��� ���� �ϼ���");
				mav.addObject("url", "../../member/login.do");
				mav.setViewName("/inc/message");
				
				return mav;
			}
		
		//���ϻ���
		String delPath = Utility.PT_IMG_PATH;
		System.out.println("ptImgPath"+delPath);
		
		if(imageURL != null && !imageURL.isEmpty())
		{
			File delFile = new File(delPath, imageURL);
			if(delFile.exists()){
				boolean delChk = delFile.delete();
				System.out.println("�������� ����="+delChk);
			}
		} //���� �ε� ���ǹ� �߰�
		
		//DB����
		int n = 0;
		int ptNo = delPtNo;
		try{
			n = umService.delPt(ptNo);
			System.out.println("�̹��� ���� ����"+n);
		}catch(SQLException e){
			System.out.println("�̹��� ���� ����");
			e.printStackTrace();
		}
		

		
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/user/user/setPhoto.do");
		
		return mav;
		
	}//photoDelete
	
}//
