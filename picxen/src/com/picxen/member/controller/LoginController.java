package com.picxen.member.controller;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.SQLException;

import javax.crypto.Cipher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.member.model.MemberService;

@Controller
public class LoginController {
    private static String RSA_WEB_KEY = "_RSA_WEB_Key_"; // ����Ű session key
    private static String RSA_INSTANCE = "RSA"; // rsa transformation
	
	private MemberService memberService;
	
	public LoginController(){
		System.out.println("������:LoginController");
	}
		
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService = memberService;
		System.out.println("���Ӱ�ü ����: MemberController"+"setMemberService()");
	}
 
    // �α��� �� ȣ��
    @RequestMapping(value="/member/login.do", method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        // RSA Ű ����
        initRsa(request);
        System.out.println("initRsa="+request);
 
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login/login");
        return mav;
    }
 
    // �α���
    @RequestMapping(value="/member/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String userid = (String) request.getParameter("USER_ID");
        String pwd = (String) request.getParameter("USER_PW");
        String idSave = (String) request.getParameter("idSave");
 
        HttpSession session = request.getSession();
        PrivateKey privateKey = (PrivateKey) session.getAttribute(LoginController.RSA_WEB_KEY);
 
        // ��ȣȭ
        userid = decryptRsa(privateKey, userid);
        pwd = decryptRsa(privateKey, pwd);
 
        // ����Ű ����
        session.removeAttribute(LoginController.RSA_WEB_KEY);
 
        // �α��� ó��
        
      //[1]db���� �ش� ���̵� �����ϴ��� Ȯ��
		int result = 0;
		
		try{
			result = memberService.checkIdPwd(userid,pwd);
			System.out.println("�α��� ó�� ����!, result="+result);
		}catch(SQLException e){
			System.out.println("�α��� ó�� ����");
			e.printStackTrace();
		}
		
		String msg="", url="";
		try{
		if(result==MemberService.LOGIN_OK){
			//[2]session�� ���̵� ����
			session.setAttribute("userid", userid);
			
			//[3]��Ű�� ���̵� ����
			Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));
			if(idSave != null){ //���̵� ������ üũ�� ���
				//��Ű ����
				cookie.setMaxAge(1000*24*60*60);//��=>1000��
				response.addCookie(cookie);
			}else{//��Ű����
				cookie.setMaxAge(0);//��Ű ���� ����
				response.addCookie(cookie);
			}
			
		/*}*/
			msg=userid + "�� �α��� �Ǿ����ϴ�";
			url=request.getContextPath()+"/index.do"; //����Ȩ���� �̵�
			url=request.getContextPath()+"/user/user/userMain.do?userid="+userid;
			}else if(result==MemberService.ID_NONE){
				msg="�ش� ���̵� �����ϴ�.";
				url="login.do";
			}else if(result==MemberService.PWD_DISAGREE){
				msg="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				url="login.do";
			}else{
				msg="�α��� ó�� ����!";
				url="login.do";
			}
			System.out.println("���̵� üũ ����");
		}catch(Exception e){
			System.out.println("���ܹ߻� ���̵� üũ");
			e.printStackTrace();
		}
		
		System.out.println("userid=="+userid);
 
        ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/inc/message");
        /*mav.setViewName("redirect:/user/user/userMain.do?userid="+userid);*/
        return mav;
    }
    
    //�α׾ƿ� ó��
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpSession session){
		//�α׾ƿ� ó��
		session.invalidate();//���� ��ȿ
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");
		
		return mav;
	}//�α׾ƿ� ó��
 
    /**
     * ��ȣȭ
     * 
     * @param privateKey
     * @param securedValue
     * @return
     * @throws Exception
     */
    private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {//private null
    	/*System.out.println("securedValue="+privateKey);*/
        Cipher cipher = Cipher.getInstance(LoginController.RSA_INSTANCE);
        byte[] encryptedBytes = hexToByteArray(securedValue);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);//private null
        System.out.println("securedValue="+cipher);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedValue = new String(decryptedBytes, "utf-8"); // ���� ���ڵ� ����.
        
        return decryptedValue;
    }
 
    /**
     * 16�� ���ڿ��� byte �迭�� ��ȯ�Ѵ�.
     * 
     * @param hex
     * @return
     */
    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) { return new byte[] {}; }
 
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
 
    /**
     * rsa ����Ű, ����Ű ����
     * 
     * @param request
     */
    public void initRsa(HttpServletRequest request) {
        HttpSession session = request.getSession();
 
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance(LoginController.RSA_INSTANCE);
            generator.initialize(1024);
 
            KeyPair keyPair = generator.genKeyPair();
            KeyFactory keyFactory = KeyFactory.getInstance(LoginController.RSA_INSTANCE);
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
 
            session.setAttribute(LoginController.RSA_WEB_KEY, privateKey); // session�� RSA ����Ű�� ���ǿ� ����
 
            RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            String publicKeyModulus = publicSpec.getModulus().toString(16);
            String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
 
            request.setAttribute("RSAModulus", publicKeyModulus); // rsa modulus �� request �� �߰�
            request.setAttribute("RSAExponent", publicKeyExponent); // rsa exponent �� request �� �߰�
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	System.out.println("Ű���� ����"+request);
            e.printStackTrace();
        }
    }
}


