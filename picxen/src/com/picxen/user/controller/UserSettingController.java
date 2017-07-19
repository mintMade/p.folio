package com.picxen.user.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.common.Utility;
import com.picxen.member.model.MemberBean;
import com.picxen.user.model.UserMainService;

@Controller
public class UserSettingController {
	private UserMainService umService;
	
	public UserSettingController(){
		System.out.println("생성자:UserSettingContoller");
	}
	//setter
	public void setUserMainService(UserMainService umService){
		this.umService=umService;
		System.out.println("종속객체주입:ReBoardDeleteController,"+"setUmSErvcie()");
	}
	
	@RequestMapping(value="/user/user/userSetting.do", method=RequestMethod.GET)
	public ModelAndView userSettingGet(HttpSession session, String userid){
		System.out.println("파라 미터 유저세팅 GET:userid="+userid);
		
		MemberBean mbBean = new MemberBean();
		try{
			mbBean = umService.userInfo(userid);
			System.out.println("파라미터userid=>Bean성공"+mbBean);
		}catch(SQLException e){
			System.out.println("파라미터userid=>Bean실패"+mbBean);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();	
//로그인체크		
		userid=(String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		mav.addObject("mbBean", mbBean);
		mav.setViewName("/user/user/userSetting");
		return mav;
	}
	
	@RequestMapping(value="/user/user/userSetting.do", method=RequestMethod.POST)
	public ModelAndView userSettingPost(MemberBean mbBean, HttpSession session, HttpServletRequest request, String myIcon , 
				String pwdChk, String newPwd, String newPwdChk/*, String pwd*/){
		System.out.println("파라미터 유저 세팅 post:mbBean="+mbBean);
		System.out.println("파라미터 유저 세팅 post:pwdChk="+pwdChk+", newPwd="+newPwd+", newPwdChk="+ newPwdChk);
		
		ModelAndView mav = new ModelAndView();
//로그인 체크		
		String userid=(String)session.getAttribute("userid");
		System.out.println("유저아이디(로그인체크Post)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "로그인 먼저 하세요");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}//

//아이콘업로드
		
		//사용자가 업로드하려고 선택한 파일-임시파일 형태
		MultipartFile tempFile = mbBean.getUploadIcon();
		System.out.println("up로드아이콘="+mbBean.getUploadIcon());
		
		//sql은 number 컨트롤러 표기는 long
		long iconSize=0;

		//선택한 파일의 이름
		String iconName = tempFile.getOriginalFilename();
		
		//업로드 경로-실제 물리적인 경로 구하기
		//임시 파일 path
		String junkPath = Utility.JUNK_FILES_PATH;

		//view 파일 path
		String viewPath = Utility.MY_ICON;
		
	//업로드한 파일이 있는경우에만 처리
	if(tempFile != null && !tempFile.isEmpty()){	
		
		/*ArrayList<String> iconList = null;*/
		//파일이름이 중복되는 경우, 유일한 이름으로 변경하자=>  _일련번호
		myIcon = Utility.getUniqueFileName(junkPath, iconName, userid);
		
		/*myIcon = iconList.get(iconList.size() -1);*/ 
		
		iconSize = tempFile.getSize();
		System.out.println("파일 사이즈="+iconSize);
		
		//파일 업로드 처리
		File myfile = new File(viewPath, myIcon);
		
		try{
			tempFile.transferTo(myfile);
			System.out.println("아이콘 업로드 성공 myfile="+myfile);
		}catch(IllegalStateException e){
			System.out.println("아이콘 업로드 실패");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("아이콘 업로드 실패!");
			e.printStackTrace();
		}
	}
	
	
//비밀번호	
	String msg="", url="";
	String pwd = mbBean.getPwd();
	
	System.out.println("비밀번호="+pwd);
	try{
	if(pwdChk != null && !pwdChk.isEmpty() || newPwd !=null && !newPwd.isEmpty() || newPwdChk != null && !newPwdChk.isEmpty()){
		if(pwdChk != null && !pwdChk.isEmpty()){ //비밀번호에 값이 있을 경우
			if(!pwd.equals(pwdChk)){ //메인비번 비교 틀릴경우
				msg="비밀번호가 일치하지 않습니다.";
				url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
				System.out.println("msg1"+msg);
			}
		}
		if(pwdChk == null || pwdChk.isEmpty()){ //비밀번호에 값이 없을 경우
			msg="현재 비밀번호를 입력해 주세요";
			System.out.println("msg2="+msg);
			url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
		}
		if(newPwd == null || newPwd.isEmpty()){ //새 비밀번호 값이 없을 경우
			msg="새 비밀번호를 입력해 주세요.";
			url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
		}
		if(newPwd != null && !newPwd.isEmpty()){ //새 비밀번호 값이 있을 경우 
			if(!newPwd.equals(newPwdChk)){
				msg="새 비밀번호가 일치하지 않습니다.";
				url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
			}
		}
		
		if(pwd.equals(pwdChk) && newPwd.equals(newPwdChk) ){
			mbBean.setPwd(newPwd);
			System.out.println("pwd=="+pwd);
			msg="성공";
			
			mbBean.setMyIcon(myIcon);
			mbBean.setIconSize(iconSize);
			
			int n = 0;
			try{
				n=umService.userSetUpdate(mbBean);
				System.out.println("유저 정보 수정 성공 mbBean:"+mbBean+"유저정보수정 n="+n);
						
			}catch(SQLException e){
				System.out.println("유저정보 수정 실패");
				e.printStackTrace();
			}
					
			mav.addObject("mbBean", mbBean);
			mav.setViewName("/user/user/userSetting");
			return mav;
			
		}
		
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.addObject("mbBean", mbBean);
		mav.setViewName("/inc/message");
		
		return mav;	

	} //if
	}catch(Exception e){
		System.out.println("비번 수정 실패");
		e.printStackTrace();
	}
	
//db	
		
		/*mbBean.setBg(bg);
		mbBean.setBgSize(bgSize);*/
		mbBean.setMyIcon(myIcon);
		mbBean.setIconSize(iconSize);
		
		int n = 0;
		try{
			n=umService.userSetUpdate(mbBean);
			System.out.println("유저 정보 수정 성공 mbBean:"+mbBean+"유저정보수정 n="+n);
					
		}catch(SQLException e){
			System.out.println("유저정보 수정 실패");
			e.printStackTrace();
		}
				
		mav.addObject("mbBean", mbBean);
		mav.setViewName("/user/user/userSetting");
		return mav;
	}
}//
