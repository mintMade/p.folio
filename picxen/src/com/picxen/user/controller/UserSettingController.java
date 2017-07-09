package com.picxen.user.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
/*@RequestMapping("user/user/userSetting.do")*/
public class UserSettingController {
	private UserMainService umService;
	
	public UserSettingController(){
		System.out.println("������:UserSettingContoller");
	}
	//setter
	public void setUserMainService(UserMainService umService){
		this.umService=umService;
		System.out.println("���Ӱ�ü����:ReBoardDeleteController,"+"setUmSErvcie()");
	}
	
	@RequestMapping(value="/user/user/userSetting.do", method=RequestMethod.GET)
	public ModelAndView userSettingGet(HttpSession session, String userid){
		System.out.println("�Ķ� ���� �������� GET:userid="+userid);
		
		MemberBean mbBean = new MemberBean();
		try{
			mbBean = umService.userInfo(userid);
			System.out.println("�Ķ����userid=>Bean����"+mbBean);
		}catch(SQLException e){
			System.out.println("�Ķ����userid=>Bean����"+mbBean);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();	
//�α���üũ		
		userid=(String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũ)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}
		mav.addObject("mbBean", mbBean);
		/*mav.addObject("pwd", mbBean.getPwd());
		System.out.println("mbBean.getPwd()="+mbBean.getPwd());*/
		mav.setViewName("/user/user/userSetting");
		return mav;
	}
	
	@RequestMapping(value="/user/user/userSetting.do", method=RequestMethod.POST)
	public ModelAndView userSettingPost(MemberBean mbBean, HttpSession session, HttpServletRequest request, String myIcon , 
				String pwdChk, String newPwd, String newPwdChk/*, String pwd*/){
		System.out.println("�Ķ���� ���� ���� post:mbBean="+mbBean);
		System.out.println("�Ķ���� ���� ���� post:pwdChk="+pwdChk+", newPwd="+newPwd+", newPwdChk="+ newPwdChk);
		
		ModelAndView mav = new ModelAndView();
//�α��� üũ		
		String userid=(String)session.getAttribute("userid");
		System.out.println("�������̵�(�α���üũPost)="+userid);
		if(userid == null || userid.isEmpty()){
			mav.addObject("msg", "�α��� ���� �ϼ���");
			mav.addObject("url", "../../member/login.do");
			mav.setViewName("/inc/message");
			
			return mav;
		}//

//�����ܾ��ε�
		//����ڰ� ���ε��Ϸ��� ������ ����-�ӽ����� ����
		MultipartFile tempFile = mbBean.getUploadIcon();
		System.out.println("up�ε������="+mbBean.getUploadIcon());
		
		//sql�� number ��Ʈ�ѷ� ǥ��� long
		long iconSize=0;

		//������ ������ �̸�
		String iconName = tempFile.getOriginalFilename();
		
		//���ε� ���-���� �������� ��� ���ϱ�
		String upPath = Utility.MY_ICON;
		
	//���ε��� ������ �ִ°�쿡�� ó��
	if(tempFile != null && !tempFile.isEmpty()){	
		
		//�����̸��� �ߺ��Ǵ� ���, ������ �̸����� ��������=>  _�Ϸù�ȣ
		myIcon = Utility.getUniqueFileName(upPath, iconName);
		
		iconSize = tempFile.getSize();
		System.out.println("���� ������="+iconSize);
		
		//���� ���ε� ó��
		File myfile = new File(upPath, myIcon);
		
		try{
			tempFile.transferTo(myfile);
			System.out.println("������ ���ε� ���� myfile="+myfile);
		}catch(IllegalStateException e){
			System.out.println("������ ���ε� ����");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("������ ���ε� ����!");
			e.printStackTrace();
		}
	}
	
//BG���ε�
/*	MultipartFile bgFile = mbBean.getUploadBG();
	System.out.println("BG���ε�"+mbBean.getUploadBG());
	
	//bgSize
	long bgSize=0;
	
	//bg���� ����-����
	String bgName=bgFile.getOriginalFilename();

	//bg���� ���
	String bgPath = Utility.MY_BG;
	
	//������ �ִ� ��쿡�� ó��
	if(bgFile !=null && !bgFile.isEmpty()){
		//�̸� �ߺ�ó��
		bg = Utility.getUniqueFileName(bgPath, bgName);
		bgSize = bgFile.getSize();
		
		//���� ���ε�
		File myBg = new File(bgPath, bg);
		
		try{
			bgFile.transferTo(myBg);
			System.out.println("bg���ε� ����"+bgFile);
		}catch(IllegalStateException e){
			System.out.println("bg���ε� ����");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println();
			e.printStackTrace();
		}
	}
	*/
	
	
//��й�ȣ	
	String msg="", url="";
	String pwd = mbBean.getPwd();
	
	System.out.println("��й�ȣ="+pwd);
	try{
	if(pwdChk != null && !pwdChk.isEmpty() || newPwd !=null && !newPwd.isEmpty() || newPwdChk != null && !newPwdChk.isEmpty()){
		if(pwdChk != null && !pwdChk.isEmpty()){ //��й�ȣ�� ���� ���� ���
			if(!pwd.equals(pwdChk)){ //���κ�� �� Ʋ�����
				msg="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
				System.out.println("msg1"+msg);
			}
		}
		if(pwdChk == null || pwdChk.isEmpty()){ //��й�ȣ�� ���� ���� ���
			msg="���� ��й�ȣ�� �Է��� �ּ���";
			System.out.println("msg2="+msg);
			url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
		}
		if(newPwd == null || newPwd.isEmpty()){ //�� ��й�ȣ ���� ���� ���
			msg="�� ��й�ȣ�� �Է��� �ּ���.";
			url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
		}
		if(newPwd != null && !newPwd.isEmpty()){ //�� ��й�ȣ ���� ���� ��� 
			if(!newPwd.equals(newPwdChk)){
				msg="�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				url=request.getContextPath()+"/user/user/userSetting.do?userid="+userid;
			}
		}
		
		if(pwd.equals(pwdChk) && newPwd.equals(newPwdChk) ){
			mbBean.setPwd(newPwd);
			System.out.println("pwd=="+pwd);
			msg="����";
			
			mbBean.setMyIcon(myIcon);
			mbBean.setIconSize(iconSize);
			
			int n = 0;
			try{
				n=umService.userSetUpdate(mbBean);
				System.out.println("���� ���� ���� ���� mbBean:"+mbBean+"������������ n="+n);
						
			}catch(SQLException e){
				System.out.println("�������� ���� ����");
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
		System.out.println("��� ���� ����");
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
			System.out.println("���� ���� ���� ���� mbBean:"+mbBean+"������������ n="+n);
					
		}catch(SQLException e){
			System.out.println("�������� ���� ����");
			e.printStackTrace();
		}
				
		mav.addObject("mbBean", mbBean);
		mav.setViewName("/user/user/userSetting");
		return mav;
	}
}//
