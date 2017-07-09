package com.picxen.photo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.Utility;
import com.picxen.member.model.MemberBean;
import com.picxen.member.model.MemberService;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.CategoryService;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoService;

@Controller
public class PhotoUploadController {
	private CategoryService cgService;
	private PhotoService ptService;
	
	//setter
	public void setCgService(CategoryService cgService){
		this.cgService=cgService;
		System.out.println("생성자:PhotoUploadController");
	}
	
	public void setPtServcie(PhotoService ptService){
		this.ptService=ptService;
	}

	
	@RequestMapping(value="/photo/ptUpload/ptUp.do", method=RequestMethod.GET)
	public ModelAndView ptUploadGet(HttpSession session){
		//상품 등록 화면 보여주기
		//카테고리 목록 조회해야함
		//db작업-select		
/*		List<CategoryBean> alist=null;
		
		try{
			alist=cgService.listCategoryAll();
			System.out.println("ptInsertGet(),카테고리 목록 조회성공, "
			+"alist.size()"+alist.size());
		}catch(SQLException e){
			System.out.println("ptInsertGet(),카테고리 목록 조회실패");
			e.printStackTrace();
		}*/
		
		//결과 뷰페이지
		ModelAndView mav = new ModelAndView();
		/////////////////////
		String userid=(String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		///////////////////// 로그인
		mav.addObject("uploader", userid);
		/*mav.addObject("cgList", alist);*/
		mav.setViewName("/photo/ptUpload/ptUp");
		
		return mav;
	}
	
	@RequestMapping(value="/photo/ptUpload/ptUp.do", method=RequestMethod.POST)
	public ModelAndView ptUploadPost(PhotoBean ptBean, HttpSession session, String uploader){
		//매개변수로 넘어오는 빈 클래스 => Command 클래스(객체)
		
		//db에 상품 insert, 상품이미지 파일 업로드 처리
		//1.파라미터
		//=>업로더 조회
		String userid=(String)session.getAttribute("userid");
		System.out.println("ptUploadPost(),파라미터 ptBean="+ptBean+", 업로더="+uploader);
		
		
		//로그인된 경우에만 처리
		ModelAndView mav = new ModelAndView();
		
		
		//파일 업로드 처리
		//사용자가 업로드하려고 선택한 파일-임시파일 형태
		MultipartFile tempFile = ptBean.getUploadFile();
		System.out.println("getUpload파일="+ptBean.getUploadFile());
		
		//선택한 파일의 이름
		String oName=tempFile.getOriginalFilename();
		
		//업로드 경로-실제 물리적인 경로 구하기
		//String upPath = "/pt_images";
		//upPath = session.getServletContext().getRealPath(upPath);
		String upPath = Utility.PT_IMG_PATH;
		
		//파일이름이 중복되는 경우, 유일한 이름으로 변경하자=>  _일련번호
		String imageUrl = Utility.getUniqueFileName(upPath, oName);
		//ㄴ>저장전에 위imageUrl을 넘겨줘도되나.. 안될것같다 우선 저장하고 그파일을 불러와야하기 때문에 밑에까지 처리후 로딩
		
	
		//파일 업로드 처리
		File myfile=new File(upPath, imageUrl);
		
		try{
			tempFile.transferTo(myfile);
			System.out.println("파일 업로드 처리 성공!"+myfile);
		}catch(IllegalStateException e){
			System.out.println("파일 업로드 처리 실패!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("파일 업로드 처리 실패!!");
			e.printStackTrace();
		}
		
		//2.db작업
/*		ptBean.setImageURL(imageUrl);

		int n=0;
		try{
			n=ptService.uploadPhoto(ptBean);
			System.out.println("사진 업로드 성공!, n="+n);
		}catch(SQLException e){
			System.out.println("사진 등록 실패!!");
			e.printStackTrace();
		}*/
		
		//3.결과 뷰페이지
		//!ptBean에 아직 업로드하지 않은상태
		ptBean.setImageURL(imageUrl);
		ptBean.setUploader(uploader);
		
		
		mav.setViewName("/photo/ptUpload/ptPreview");
		//사진url 파라미터
		mav.addObject("imageUrl", imageUrl);
		System.out.println("imageUrl="+imageUrl);
		mav.addObject("uploader", uploader);
		return mav;
	}

//사진 업로드 정보입력 페이지	
	@RequestMapping(value="/photo/ptUpload/ptPreview.do", method=RequestMethod.GET)
	public ModelAndView getPtUpload(PhotoBean ptBean, String imageUrl, String uploader){
		System.out.println("GET사진업로드 세부페이지 파라미터:uploader="+uploader+", imageUrl="+imageUrl+", photoBean="+ptBean);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("uploader", uploader);
		mav.addObject("imageURL", imageUrl);
		mav.setViewName("/photo/ptUpload/ptPreview");
		return mav;
	}
	
	
	@RequestMapping(value="/photo/ptUpload/ptPreview.do", method=RequestMethod.POST)
	public ModelAndView postPtUpload(PhotoBean ptBean, String imageURL, String uploader){
		System.out.println("POST사진업로드 세부페이지 파라미터:uploader="+uploader+", imageUrl="+imageURL+", photoBean="+ptBean);
		
		//db
		int n=0;
		try{
			n=ptService.uploadPhoto(ptBean);
			System.out.println("사진 업로드 성공!, n="+n);
		}catch(SQLException e){
			System.out.println("사진 등록 실패!!");
			e.printStackTrace();
		}
				
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/user/user/userMain.do?userid="+uploader);
		/*mav.addObject("userid", uploader);*/
		return mav;
	}//ptUpload
	
	
}////
