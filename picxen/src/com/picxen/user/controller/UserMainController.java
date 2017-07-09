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
		System.out.println("생성자:UserMainController()");
	}
	
	//setter
	public void setuMainService(UserMainService uMainService){
		this.uMainService=uMainService;
		System.out.println("종속객체 주입:UserMainService+"+"UserMainController()");
	}
	
	
	@RequestMapping("/user/user/userMain.do")
	public ModelAndView userHome(HttpServletRequest request, String userid, IpLogBean ilBean, String ip,
				Integer mainCg /*mainCg = 유저 메인 카테고리선택번호*/){
		//유저아이디를 이용하여 유저홈으로 이동
		//1.파라미터
		System.out.println("userid="+userid+"유저홈ip="+ip+"ilBean="+ilBean+"mainCg"+mainCg);
				
		//db작업
		ArrayList<PhotoBean> alist = null;//사진 리스트
		List<PhotoBean> blist = null;//앨범리스트
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
			System.out.println("유저메인 카테고리 조회실패");
			e.printStackTrace();
		}
		
		try{
			//userid파라미터 이용해서 유저 정보 출력하기
			mbBean = uMainService.userInfo(umId);
			System.out.println("유저홈메인 이동 성공, mbBean"+mbBean+"umId="+umId);
			
			//유저좋아요 합계
			likeTtl= uMainService.likeTotal(umId);
			System.out.println("좋아요 총합계="+likeTtl);
			
			//사진 뷰합계
			viewTtl= uMainService.viewTotal(umId);
			System.out.println("뷰 총합계="+viewTtl);
			
			//퍼가요
			faveTtl = uMainService.faveTotal(umId);
			System.out.println("퍼가요 총합계"+faveTtl);
			
			//유저홈->유저사진리스트
			//유저아이디를 키로 올린사진 조회
			alist = uMainService.myPtList(umId);
			System.out.println("업로드 리스트 출력성공, alist.size()="+alist.size()+"umId="+umId);
			
			//유저홈->퍼온사진 리스트
			blist =uMainService.myAbmList(umId);
			System.out.println("내 앨범리스트출력 성공, blist.size()="+blist.size()+"umId="+umId);
			
		}catch(SQLException e){
			System.out.println("유저홈:닉 조회 실패@"+mbBean);
			e.printStackTrace();
		}
		
		
		//결과 뷰페이지
		int pageSize=5;//
		int blockSize=10;//
		PagingBean pb = new PagingBean(request, alist, pageSize, blockSize);//
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ipResult", result);
		mav.addObject("alist", alist);//
		mav.addObject("totalRecord", alist.size());//내사진리스트
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
