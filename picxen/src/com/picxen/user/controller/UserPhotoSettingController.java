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
		System.out.println("생성자:UserPhotoSettingController");
	}
	
	@RequestMapping(value="/user/user/setPhoto.do", method=RequestMethod.GET)
	public ModelAndView getUserPtSetting(HttpSession session){
		System.out.println("이미지 edit 페이지 GET");
		
		//db
		String userid = (String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크)="+userid);
				
		ArrayList<PhotoBean> ptList = null;
		/*try{ 
			if("ptNo" != null && !"ptNo".isEmpty()){//null일경우 코멘트로  submit 할때 에러
				Integer ptNo = Integer.parseInt(request.getParameter("ptNo"));//int값 parseInt로 적용
				System.out.println("PtNo= "+ptNo);
			}
		}catch(Exception e){
			System.out.println("ptNo request.getParameter 실패");
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
			/*ptBean = ptService.viewPhoto(ptNo);*///이미지수정		
						
			if(ptList != null && !ptList.isEmpty()){//ptList에 값이 있을 경우(Timestamp를 String으로 변환후 중복제거리스트 넘기기및 기타뻘리스트) 
				for(int i=0; i<ptList.size();i++){ //다른 for문 수정불가
					
					ptBean = (PhotoBean)ptList.get(i);
					Date rDate = ptBean.getRegdate();//Timestamp를 Date로 변환
										
					SimpleDateFormat regFmat = new SimpleDateFormat("yyyy년 MM월");
					String strDate = regFmat.format(rDate); //String으로 변환
					
					listA.add(strDate);
				
												
				}//for()
				listB = new ArrayList<String>(new LinkedHashSet<String>(listA));//중복제거
				
				
				List<Integer> liCnt = new ArrayList<Integer>();//중복 갯수
				for(int r = 0; r<listB.size(); r++){
					String sCnt = listB.get(r);
					
					liCnt.add(Collections.frequency(listA, sCnt));
				}
				
				///////////////// [ [AA], [BB] ] 중복끼리 2차 리스트만들기 =>input Map
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
					for(int i = 0; i < v; i++) /*{*/// 한번만 반복
						list.add(k);
						lol.add(list);
				}
				
			}//if()
									
			System.out.println("이미지 리스트 조회 성공 ptList.size()="+ptList.size());
			
		}catch(SQLException e){
			System.out.println("이미지수정리스트 조회실패");
			e.printStackTrace();
		}
		
		//결과
		ModelAndView mav = new ModelAndView();
		/*String userid = (String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크)="+userid);*/
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요");
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
		System.out.println("파라미터 유저이미지세팅Post PhotoBean="+ptBean);
		
		//db
		String userid = (String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크)="+userid);
				
		int n = 0;
		try{
			n = umService.upUserPt(ptBean);
			System.out.println("이미지 수정완료"+n);
		}catch(SQLException e){
			System.out.println("유저 이미지 세팅 실패");
			e.printStackTrace();
		}
		
		//결과
				ModelAndView mav = new ModelAndView();
				/*String userid = (String)session.getAttribute("userid");
				System.out.println("유저아이디(로그인체크)="+userid);*/
				if(userid == null || userid.isEmpty()){
					mav.addObject("msg", "로그인 먼저 하세요");
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
		System.out.println("이미지삭제 ptNo = "+delPtNo+", imageURL= "+imageURL);
		String userid = (String)session.getAttribute("userid");
		System.out.println("이미지 삭제 유저체크= "+userid);
		
		ModelAndView mav = new ModelAndView();
		
			if(userid == null || userid.isEmpty()){//로그인체크
				mav.addObject("msg", "로그인 먼저 하세요");
				mav.addObject("url", "../../member/login.do");
				mav.setViewName("/inc/message");
				
				return mav;
			}
		
		//파일삭제
		String delPath = Utility.PT_IMG_PATH;
		System.out.println("ptImgPath"+delPath);
		
		if(imageURL != null && !imageURL.isEmpty())
		{
			File delFile = new File(delPath, imageURL);
			if(delFile.exists()){
				boolean delChk = delFile.delete();
				System.out.println("삭제성공 여부="+delChk);
			}
		} //무한 로딩 조건문 추가
		
		//DB삭제
		int n = 0;
		int ptNo = delPtNo;
		try{
			n = umService.delPt(ptNo);
			System.out.println("이미지 삭제 성공"+n);
		}catch(SQLException e){
			System.out.println("이미지 삭제 실패");
			e.printStackTrace();
		}
		

		
		mav.addObject("userid", userid);
		mav.setViewName("redirect:/user/user/setPhoto.do");
		
		return mav;
		
	}//photoDelete
	
}//
