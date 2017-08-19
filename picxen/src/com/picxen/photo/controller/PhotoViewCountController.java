package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.CommentsService;
import com.picxen.log.model.IpLogBean;
import com.picxen.log.model.IpLogService;
import com.picxen.photo.model.PhotoLikeBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoViewCountController {
	private PhotoService ptService;
	private IpLogService ilService;
	
	//comments
	private CommentsService cmService;
	
	//생성자
	public PhotoViewCountController(){
		System.out.println("생성자:PhotoViewController()");
	}
	
	//setter
	public void setPhotoService(PhotoService ptService){
		this.ptService=ptService;
		System.out.println("종속객체 주입:PhotoService"+"PhotoViewCountController()");
	}
	
	public void setIpLogService(IpLogService ilService){
		this.ilService=ilService;
		System.out.println("종속객체 주입:IpLogService"+"PhotoViewCountController()");
	}
	
	//setter-comment//코멘트
		public void setCmService(CommentsService cmService){
			this.cmService=cmService;
			System.out.println("종속객체 주입:PhotoDetailController"+"CommentsService()");
	}
	
		
	@RequestMapping(value="/photo/photoCountUpdate.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView photoViewCount(HttpServletRequest request, PhotoLikeBean plBean){

		IpLogBean ilBean = new IpLogBean();
		//parameters
		String sort = (String)request.getParameter("sort");
		String strPtNo = (String)request.getParameter("ptNo");
		Integer ptNo = Integer.parseInt(strPtNo);
		String cgName = (String)request.getParameter("cgName");
		System.out.println("viewPtPARAMETERS sort="+sort+", ptNo="+ptNo+", cgName="+cgName);
		
		
		//ip
		request = (HttpServletRequest) ((ServletRequestAttributes)
					RequestContextHolder.currentRequestAttributes()).getRequest();
		String logIp = request.getHeader("X-FORWARDED-FOR");
		
		if(logIp == null || logIp.length() == 0 ) {
			logIp =request.getHeader("proxy-Client-IP");
		}
		
		if(logIp == null || logIp.length() == 0 ) {
			logIp =request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(logIp == null || logIp.length() == 0) {
			logIp = request.getRemoteAddr();
		}
		
		
		//uid
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		if(userid == null || userid.isEmpty()) {
			userid="";
		}
		
		ilBean.setPtNo(ptNo);
		ilBean.setUserid(userid);
		ilBean.setIp(logIp);
		System.out.println("ilBean="+ilBean);
		//db작업
		int n = 0;
		
		try{
			//iplog에서 ilBean값 없으면 업데이트 있으면 없데이트 x
			//merge로 iplog값 있으면 x없으면 insert, userid에 값이 있으면 userid의 해당값 없데이트
			n = ptService.photoCountView(ilBean);
			ilService.insertPtlog(ilBean);
			System.out.println("아이피 로그 입력 && 조회수 증가="+n);
		}catch(SQLException e){
			System.out.println("아이피로그 입력 성공 실패");
			e.printStackTrace();
		}
		
		//결과뷰
		ModelAndView mav = new ModelAndView();//처리하고 되돌아올때 무슨 사진을 처리했었는지 주소파라미터
		mav.addObject("userid", userid);//detail.do로 넘어갈때 유저가 해당사진의 like를 찍었는지 조회하기 위해 유저 아이디를 파라미터로 넘김
		mav.addObject("ptNo", ptNo);//리스트주소에 찍혀온 get방식의 do?ptNo="+(ptBean.photoNo) 대신 무슨사진이었나 파라미터로 넘김
		mav.addObject("sort", sort);//디테일로 소트이름넘겨주기 new pop upc
		mav.addObject("cgName", cgName);
		/*mav.setViewName("redirect:/photo/photo/photoDetail.do");//Detail넘어갈때 주소뒤에 붙는 파라미터는 컨트롤러 파라미터 순서출력
*/		mav.setViewName("/photo/photoCountUpdate");//Detail넘어갈때 주소뒤에 붙는 파라미터는 컨트롤러 파라미터 순서출력
	    
		
		return mav;
	}//photoViewCount();
	

}//
