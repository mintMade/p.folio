package com.picxen.photo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.picxen.comments.model.CmLikeBean;
import com.picxen.comments.model.CommentsBean;
import com.picxen.comments.model.CommentsService;
import com.picxen.comments.model.MemCommentViewBean;
import com.picxen.comments.model.MemberViewBean;
import com.picxen.faves.model.FavesService;
/*import com.picxen.faves.model.FavesService;*/
import com.picxen.member.model.MemberBean;
import com.picxen.photo.model.CategoryBean;
import com.picxen.photo.model.PhotoBean;
import com.picxen.photo.model.PhotoLikeBean;
import com.picxen.photo.model.PhotoService;
import com.picxen.user.model.UserMainService;
import com.sun.org.glassfish.gmbal.ParameterNames;

@Controller
public class PhotoDetailController {
	private PhotoService ptService;
	private CommentsService cmService;
	private UserMainService uMainService;
	private FavesService fvService;//faveliset 2207

	public PhotoDetailController(){
		/*System.out.println("생성자:PhotoDetailController");*/
	}
	//setter
	public void setPtService(PhotoService ptService){
		this.ptService=ptService;
		/*System.out.println("종속객체주입:PhotoDetailController"+"PhotoService()");*/
	}
	//setter-comment//코멘트
	public void setCmService(CommentsService cmService){
		this.cmService=cmService;
		/*System.out.println("종속객체 주입:PhotoDetailController"+"CommentsService()");*/
	}
	
	public void setUmService(UserMainService uMainService){
		this.uMainService=uMainService;
		/*System.out.println("종속객체 주입:PhotoDetailController"+"uMainService()");*/
	}
	
	//faveList2207
	public void setFvService(FavesService fvService){
		this.fvService = fvService;
	}
	
	@RequestMapping(value="/photo/photo/photoDetail.do", method=RequestMethod.GET)
	public ModelAndView photoDetail(HttpServletRequest request, HttpServletResponse response, int ptNo, PhotoLikeBean plBean){
		//파라미터
		String cgName = (String)request.getParameter("cgName");
		String sort = (String)request.getParameter("sort");
		HttpSession session = request.getSession();
		String userid =(String)session.getAttribute("userid");
		
		if(userid == null || userid.isEmpty()) {
			userid="";
		}
		
		//db작업	
		
		MemberBean mbBean = null;
		List<PhotoBean> clist=null;
		CategoryBean cgBean = new CategoryBean();

		cgBean.setSort(sort);
		cgBean.setCategoryName(cgName);
		/*System.out.println("cgBean="+cgBean);*/
		
		String umId=null;
		
		try{
			mbBean = ptService.viewUserId(ptNo);
			/*System.out.println("유저 아이디 닉 조회성공"+mbBean+"ptNo"+ptNo);*/
		}catch(SQLException e){
			System.out.println("유저 아이디 닉 조회실패");
			e.printStackTrace();
		}
		
		if(mbBean!=null){//userid가 null일경우 에러 umId가 null로 들어감
		umId=mbBean.getUserid();//유저메인 아이디 다음사진 보기		
		}
		
		PhotoBean ptBean = new PhotoBean();
		try{
			ptBean = ptService.viewPhoto(ptNo);
			System.out.println("사진 상세보기 성공, ptBean="+ptBean+",ptNo="+ptNo);
			
			//자신의 likecnt 로그 true 누른적없으면 false //현재는 트랜잭션으로 좋아요눌러도 카운팅 안되게 적용//비활성 처리해버림
			/*likeRslt = ptService.listPhotoLike(plBean);
			System.out.println("유저 photo좋아요카운트 조회 성공, result="+likeRslt);*/
			
			//다음사진 리스트 버튼 (해당 소팅사진 리스트)카테고리별 출력은 이후에 처리 (추가 uM, aM)
			//카테고리 method변경 20170729			
			if("모두보기".equals(cgName)){
				clist = ptService.listPhotoByAllView(cgBean);
			}else if("uM".equals(cgName)) {
				clist=uMainService.myPtList(umId);//userMain 추가
				/*System.out.println("umId"+umId);*/
			}else if(cgName != null && !cgName.isEmpty()){
				//cgName을 파라미터로 검색
				clist = ptService.listPhotoByCategory(cgBean);
			}else {
				clist = ptService.listPhotoByAllView(cgBean);
			}
			/*System.out.println("상세보기 카테고리 사진조회 성공clist.Size="+clist);*/
			
		}catch(SQLException e){
			System.out.println("사진 상세보기 실패, 유저 like조회 실패");
			e.printStackTrace();
		}
		
		
		//pre, next 값얻기 
	    int idx=0; //get index
	    int idxSize=clist.size() -1; //마지막 index
	    //현재값 index구하기 current ptNo
		for(PhotoBean goPreBean : clist) {
			if(ptNo == goPreBean.getPhotoNo()) {
				idx =clist.indexOf(goPreBean); //begin 0		
			}
		}
	    
		//이전다음값 구하기 pre, next value
		PhotoBean prev = new PhotoBean();
		PhotoBean nxt = new PhotoBean();
	    ListIterator<PhotoBean> it = clist.listIterator(idx); //현재 ptBean의 index삽입
	    while (it.hasNext()) { //값(ptBean)이 있을 경우 hasPrevious(), hasNext()
	    	if(clist.size()==1) { //리스트에 총 1개만 있을경우 이전값 다음값이 없음
	    		nxt = prev = it.next();
	    		/*System.out.println("next, prv="+nxt+", "+prev);*/
	    		break;
	    	}else if(idx == 0) { //제일 처음 ptBean일 경우
			      it.next(); //다음값을
			      nxt = it.next(); //t로 insert
			      break;
	    	}else if(idx == idxSize) { //마지막 ptBean일 경우
	    		prev = it.previous(); //이전 값을 prev에 넣음
	    		break;
	    	}else { //처음, 마지막 ptBean이 아닐경우
		      prev = it.previous(); //이전값
		      it.next(); //현재값
		      it.next(); //다음값
		      nxt = it.next();
		      break;
	    	}
	    }
	    
	    int prNo = prev.getPhotoNo();
	    int nxtNo = nxt.getPhotoNo();
	    
		//※코멘트
		MemberViewBean mvBean = null;		
		//코멘트 //내아바타정보
		try{
			mvBean = cmService.getMyAvatar(userid);//코멘트의 리플 유저 아바타가 현재 자기아바타로 보이게함:그렇지 않으면 코멘트 유저아바타가 보임
			/*System.out.println("방문유저 아바타 조회성공 userid="+userid);*/
			
		}catch(SQLException e){
			System.out.println("방문유저 아바타 조회 실패");
			e.printStackTrace();
		}
		
		//코멘트//탑코멘트 출력
		ArrayList<MemCommentViewBean> tAlist=null;
		
		try{
			tAlist=cmService.topComments(ptNo);
			System.out.println("TOP 코멘트 출력 성공 tAlist="+tAlist);
		}catch(SQLException e){
			System.out.println("TOP 코멘트 출력 실패");
			e.printStackTrace();
		}
		
		//코멘트 //출력
		ArrayList<MemCommentViewBean> alist = null;
		
		try{
			alist = cmService.getCmList(ptNo);
			/*System.out.println("일반 코멘트 출력 성공 alist="+alist.size());*/
		}catch(SQLException e){
			System.out.println("코멘트 목록 조회 실패");
			e.printStackTrace();
		}
		
		//코멘트 로그2 map : 사진번호와 접속유저 아이디를 뽑은후 해당 코멘트 넘버만 뽑아서 비교
		ArrayList<CmLikeBean> clikelist = null;
		
		try{
			Map<String, Object> clogMap = new HashMap<String, Object>();
			clogMap.put("ptNo1",ptNo);
			clogMap.put("userid1",userid);
			/*clogMap.put("commentNo1",commentNo);*/
			/*System.out.println("clogMap="+clogMap);*/
		
			clikelist = cmService.getclike(clogMap);
			System.out.println("코멘트 좋아요 로그 로딩 성공"+clikelist);
		}catch(SQLException e){
			System.out.println("코멘트 좋아요 로그 로딩 실패"+clikelist);
			e.printStackTrace();
		}


		//결과 뷰페이지
		ModelAndView mav = new ModelAndView();
		/*mav.addObject("chkLike", likeRslt);*/
		mav.addObject("ptBean", ptBean);
		mav.addObject("mbBean", mbBean);
		mav.addObject("userid", userid);
		mav.addObject("ptNo", ptNo);
		mav.addObject("prNo", prNo);
		mav.addObject("nxtNo", nxtNo);
		mav.addObject("sort", sort);
		mav.addObject("cgName", cgName);
		mav.addObject("clist", clist);
		mav.addObject("totalCRecord", clist.size());
		mav.addObject("umId", umId);
		
		//코멘트
		mav.addObject("mvBean", mvBean);
		mav.addObject("tCmSize", tAlist.size());
		mav.addObject("tAlist", tAlist);///탑코멘트
		mav.addObject("cmSize", alist.size());
		mav.addObject("alist", alist);
		mav.addObject("cmlog", clikelist);
		mav.addObject("totalCmlog", clikelist.size());
		mav.setViewName("/photo/photo/photoDetail");
		return mav;
	}
	
	
	@RequestMapping(value="/photo/photo/photoDetail.do", method=RequestMethod.POST)
	public ModelAndView postPhotoDetail(HttpServletRequest request, CommentsBean cmBean){
		
		//parameter
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String strPtNo = (String)request.getParameter("ptNo");
		int ptNo = Integer.parseInt(strPtNo);
		String cgName = (String)request.getParameter("cgName");
		System.out.println("ptNo ="+ptNo+", userid="+userid+", cgName="+cgName+", cmBean="+cmBean);
		
		//멤버넘버조회 (부모키 입력위해 맴버넘버를 구해서 cmBean에 입력)
		int commentUser = 0;
		try{
			commentUser = cmService.getMemNumber(userid);
			/*System.out.println("멤버No 조회성공 memberNo="+commentUser);*/
		}catch(SQLException e){
			System.out.println("멤버No 조회 실패");
			e.printStackTrace();
		}
		
		cmBean.setCommentUser(commentUser);//아래 코멘트 입력 시 cmBean에 commentUser입력

		//코멘트입력 (그대로 입력하면 맴버넘버가 없어 부모키가 없다는 애러떠서 위에 userid로 맴버넘버 구한 후 cmBean에 입력)
		int key= 0;
		
		try{
			key=cmService.insertComments(cmBean);
				/*System.out.println("코멘트 입력 성공 key="+key+", cmBean="+cmBean);*/			
		}catch(SQLException e){
			System.out.println("코멘트 입력 실패"+userid);
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		/*mav.addObject("addLike", cKey);*/
		/*mav.addObject("postComment", key);*/
		mav.addObject("userid", userid);
		mav.addObject("ptNo", ptNo);
		/*mav.addObject("sort", sort);*/
		mav.addObject("cgName", cgName);
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		
		return mav;
	}

	@RequestMapping("/photo/comments/cmDel.do")
	public ModelAndView delCm(int commentNo, String userid, int ptNo, String sort){
		/*System.out.println("delcomment cmNo="+commentNo);*/
		
		//db
		int n=0;
		try{
			n = cmService.delCm(commentNo);
			/*System.out.println("리플삭제 성공"+n);*/
		}catch(SQLException e){
			System.out.println("리플삭제 실패");
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("userid", userid);
		mav.addObject("ptNo", ptNo);
		mav.addObject("sort", sort);
		
		mav.setViewName("redirect:/photo/photo/photoDetail.do");
		return mav;
	}
	
	
	@RequestMapping("/photo/photo/photoImage.do")
	public ModelAndView getImage(String imageUrl){
		//큰이미지 보기
		/*System.out.println("getImage(),파라미터 ="+imageUrl);*/
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/photo/photo/photoImage");
		
		return mav;
	}
	
}///
