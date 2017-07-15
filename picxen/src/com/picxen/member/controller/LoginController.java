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
    private static String RSA_WEB_KEY = "_RSA_WEB_Key_"; // 개인키 session key
    private static String RSA_INSTANCE = "RSA"; // rsa transformation
	
	private MemberService memberService;
	
	public LoginController(){
		System.out.println("생성자:LoginController");
	}
		
	//setter
	public void setMemberService(MemberService memberService){
		this.memberService = memberService;
		System.out.println("종속객체 주입: MemberController"+"setMemberService()");
	}
 
    // 로그인 폼 호출
    @RequestMapping(value="/member/login.do", method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        // RSA 키 생성
        initRsa(request);
        System.out.println("initRsa="+request);
 
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login/login");
        return mav;
    }
 
    // 로그인
    @RequestMapping(value="/member/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String userid = (String) request.getParameter("USER_ID");
        String pwd = (String) request.getParameter("USER_PW");
        String idSave = (String) request.getParameter("idSave");
 
        HttpSession session = request.getSession();
        PrivateKey privateKey = (PrivateKey) session.getAttribute(LoginController.RSA_WEB_KEY);
 
        // 복호화
        userid = decryptRsa(privateKey, userid);
        pwd = decryptRsa(privateKey, pwd);
 
        // 개인키 삭제
        session.removeAttribute(LoginController.RSA_WEB_KEY);
 
        // 로그인 처리
        
      //[1]db에서 해당 아이디가 존재하는지 확인
		int result = 0;
		
		try{
			result = memberService.checkIdPwd(userid,pwd);
			System.out.println("로그인 처리 성공!, result="+result);
		}catch(SQLException e){
			System.out.println("로그인 처리 실패");
			e.printStackTrace();
		}
		
		String msg="", url="";
		try{
		if(result==MemberService.LOGIN_OK){
			//[2]session에 아이디 저장
			session.setAttribute("userid", userid);
			
			//[3]쿠키에 아이디 저장
			Cookie cookie = new Cookie("ck_userid", URLEncoder.encode(userid, "euc-kr"));
			if(idSave != null){ //아이디 저장을 체크한 경우
				//쿠키 저장
				cookie.setMaxAge(1000*24*60*60);//초=>1000일
				response.addCookie(cookie);
			}else{//쿠키삭제
				cookie.setMaxAge(0);//쿠키 파일 제거
				response.addCookie(cookie);
			}
			
		/*}*/
			msg=userid + "로 로그인 되었습니다";
			url=request.getContextPath()+"/index.do"; //유저홈으로 이동
			url=request.getContextPath()+"/user/user/userMain.do?userid="+userid;
			}else if(result==MemberService.ID_NONE){
				msg="해당 아이디가 없습니다.";
				url="login.do";
			}else if(result==MemberService.PWD_DISAGREE){
				msg="비밀번호가 일치하지 않습니다.";
				url="login.do";
			}else{
				msg="로그인 처리 실패!";
				url="login.do";
			}
			System.out.println("아이디 체크 성공");
		}catch(Exception e){
			System.out.println("예외발생 아이디 체크");
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
    
    //로그아웃 처리
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpSession session){
		//로그아웃 처리
		session.invalidate();//세션 무효
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");
		
		return mav;
	}//로그아웃 처리
 
    /**
     * 복호화
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
        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
        
        return decryptedValue;
    }
 
    /**
     * 16진 문자열을 byte 배열로 변환한다.
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
     * rsa 공개키, 개인키 생성
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
 
            session.setAttribute(LoginController.RSA_WEB_KEY, privateKey); // session에 RSA 개인키를 세션에 저장
 
            RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            String publicKeyModulus = publicSpec.getModulus().toString(16);
            String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
 
            request.setAttribute("RSAModulus", publicKeyModulus); // rsa modulus 를 request 에 추가
            request.setAttribute("RSAExponent", publicKeyExponent); // rsa exponent 를 request 에 추가
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	System.out.println("키생성 실패"+request);
            e.printStackTrace();
        }
    }
}


