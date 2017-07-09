package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	

	@RequestMapping("/photo/photo/photoCountUpdate.do")
	public ModelAndView photoViewCount(HttpServletRequest request, int ptNo, IpLogBean ilBean, String ip,
			PhotoLikeBean plBean, String userid, String sort){//userMain.jsp, list.jsp에서 사진 클릭 후 detail페이지 볼때 조회수 증가에 따른 파라미터 ip, ptNo, 조회유저id를 넘김
		//파라미터
		System.out.println("PhotoViewController 파라미터:photoNo="+ptNo+", ip="+ip+", userid="+userid+", ilBean="+ilBean+", sort="+sort);
		
		//db작업
		int n = 0;
		int key = 0;
		
		try{
			key = ilService.insertPtlog(ilBean);
			System.out.println("아이피 로그 입력 key="+key);
			
		}catch(SQLException e){
			System.out.println("아이피로그 입력 성공 실패");
			e.printStackTrace();
		}
		
		try{			
			n = ptService.photoCountView(ilBean);
			System.out.println("조회수 증가 성공 n="+n+", photoNo="+ilBean);//ptNo=>ilBean으로 대체, 로그는 입력되는데 조회수가 증가안됨
			
		}catch(SQLException e){
			System.out.println("조회수 증가 실패");
			e.printStackTrace();
		}
				
		
		//결과뷰
		ModelAndView mav = new ModelAndView();//처리하고 되돌아올때 무슨 사진을 처리했었는지 주소파라미터
		mav.addObject("userid", userid);//detail.do로 넘어갈때 유저가 해당사진의 like를 찍었는지 조회하기 위해 유저 아이디를 파라미터로 넘김
		mav.addObject("ptNo", ptNo);//리스트주소에 찍혀온 get방식의 do?ptNo="+(ptBean.photoNo) 대신 무슨사진이었나 파라미터로 넘김
		mav.addObject("sort", sort);//디테일로 소트이름넘겨주기 new pop upc
		mav.setViewName("redirect:/photo/photo/photoDetail.do");//Detail넘어갈때 주소뒤에 붙는 파라미터는 컨트롤러 파라미터 순서출력
															    
		
		return mav;
	}//photoViewCount();
	

}//
