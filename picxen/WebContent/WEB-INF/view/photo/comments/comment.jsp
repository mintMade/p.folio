<%@page import="java.util.ArrayList"%>
<%@page import="com.picxen.comments.model.CmLikeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>					

<script type="text/javascript">

	function postComment(){
		if(!frmCmt.content.value){
			$('#cError').html("내용을 입력하세요.");
			$('#cError').css("color", "#708090");
			frmCmt.content.focus();
			return false;	
		}		
		var logUid = $('#logUser').val();
		if(logUid == null || logUid == ""){
			alert("로그인 하세요.");
			return false;
		};
		
		frmCmt.submit();
	}
	
	function submitTrple(cnt){
		var c = cnt;
		var tr = document.getElementById("treply_"+c);
		var logUid = $('#logUser').val();
		if(!tr.value){
			document.getElementById("trError_"+c).innerHTML="내용을 입력하십시오.";
			document.getElementById("trError_"+c).style.color="#708090"; 
			tr.focus();
			return false;
		}

		if(logUid == null || logUid == ""){
			alert("로그인 하세요.");
			return false;
		};
		
		document.forms['formTopRply'+c].submit();
	}
		

	
	/* 코멘트 추천버튼 */	
	 function tCmLikeBtn(cnt){
		var logUid = $('#logUser').val();
		if(logUid == null || logUid == ""){
			alert("로그인 하세요.");
			return false;
		};
		
		if(logUid != null || logUid != ""){
			 var c = cnt;
			 var f =document.getElementById("tCmId"+c);
			 var g = 1; 
			 var h = +f.value + g;
			 f.value = h;
			 document.getElementById("tCmId2"+c).innerHTML=h;
			 f.disabled=true;
			 f.style.color="#00688B";
			 
			 var topCmData = {
					 "commentNo": document.getElementById("topCmNo"+c).value,
					 "sortNo": document.getElementById("topCmSoNo"+c).value,
					 "commentUser":document.getElementById("topCmUsr"+c).value,
					 "ptNo": frmTopLike.ptNo.value,
					 "userid":frmTopLike.userid.value,
					 "sort":frmTopLike.sort.value,
			 };
			 
			 $.ajax({
				 url:"${pageContext.request.contextPath}/photo/comments/commentsLike.do",
				 type:'GET',
				 data:topCmData,
				 success:function(data){
				 }				 
			 });		
		};
		 
	  }

	 function tReplybtn(cnt) {
		 var z = 0;
		 var t = ${tCmSize};
		 var tc = ${cmSize};
		 for(var g = 0; g<t; g++){
			z++;
			var s = document.getElementById("trpId_"+z);
			var e = document.getElementById("trpId_"+cnt);
			if(s!=e && s.style.display=='block'  ){
				 s.style.display = 'none';
			 }
		 }
		 	if(e != null) {
		 		if(e.style.display == 'none') { 
		 			e.style.display = 'block'; 
		 		} else { 
		 			e.style.display = 'none'; 
		 		} 
		 	}
		 	if(tc != null && tc != 0){
		 		$("[id^=rpId_]").css('display','none');
		 	}
	 }
	 
	 function delTCm(cnt){
		 var f = cnt;
		 var tCmDel = {
			"commentNo": document.getElementById("tDelcmNo"+f).value,
			"ptNo": frmTopLike.ptNo.value,
			"userid":frmTopLike.userid.value,
			"sort":frmTopLike.sort.value,	 
		 };
		 $.ajax({
			 url:"${pageContext.request.contextPath}/photo/comments/cmDel.do",
			 type:'GET',
			 data:tCmDel,
			 success:function(data){
			 }
			});
		 
		 document.getElementById("tCmBox"+f).style.display='none';
	 }//
	 
	 ///////////일반코멘트
	 function delCm(cn){
		 var f = cn;
		 var cmDel = {
			"commentNo": document.getElementById("delcmNo"+f).value,
			"ptNo": frmTopLike.ptNo.value,
			"userid":frmTopLike.userid.value,
			"sort":frmTopLike.sort.value,	 
		 };
		 $.ajax({
			 url:"${pageContext.request.contextPath}/photo/comments/cmDel.do",
			 type:'GET',
			 data:cmDel,
			 success:function(data){
			 }
		});
		 
		 document.getElementById("cmBox"+f).style.display='none';
	 }
	 
 	 function cmLikeBtn(cnt){
 		var logUid = $('#logUser').val();
		if(logUid == null || logUid == ""){
			alert("로그인 하세요.");
			return false;
		};
 		 
		if(logUid != null || logUid != ""){ 		 
			 var c = cnt;
			 var f =document.getElementById("cmId"+c);
			 var g = 1; 
			 var h = +f.value + g;
			 f.value = h;
			 document.getElementById("cmId2"+c).innerHTML=h;
			 f.disabled=true;
			 f.style.color="#00688B";
			 
			 var topCmData = {
					 "commentNo": document.getElementById("cmNo"+c).value,
					 "sortNo": document.getElementById("cmSoNo"+c).value,
					 "commentUser":document.getElementById("cmUsr"+c).value,
					 "ptNo": frmTopLike.ptNo.value,
					 "userid":frmTopLike.userid.value,
					 "sort":frmTopLike.sort.value,
			 };
			 
			 $.ajax({
				 url:"${pageContext.request.contextPath}/photo/comments/commentsLike.do",
				 type:'GET',
				 data:topCmData,
				 success:function(data){
				 }
			 });//
		 }
	  }
 	 
 	function replybtn(cnt) {
		 var z = 0;
		 var t = ${cmSize};
		 var tc = ${tCmSize};
		 	for(var g = 0; g<t; g++){
			z++;
			var s = document.getElementById("rpId_"+z);
			var e = document.getElementById("rpId_"+cnt);
				if(s!=e && s.style.display=='block'  ){
					s.style.display = 'none';
			 	};
		   };
		 	if(e != null && e != 0) {
		 		if(e.style.display == 'none') { 
		 			e.style.display = 'block'; 
		 		} else { 
		 			e.style.display = 'none'; 
		 		}; 
		 	};
		 	if(tc != null && tc != 0){
		 		$("[id^=trpId_]").css('display','none');
		 	}
	 };
 	
	function submitrple(cn){
		var c = cn;
		var tr = document.getElementById("reply_"+c);
 		var logUid = $('#logUser').val();
		if(!tr.value){
			document.getElementById("rError_"+c).innerHTML="내용을 입력하십시오.";
			document.getElementById("rError_"+c).style.color="#708090"; 
			tr.focus();
			return false;
		}

		if(logUid == null || logUid == ""){
			alert("로그인 하세요.");
			return false;
		};
		document.forms['formRply'+c].submit();
	}
	
	$(window).ready(function(){
		$('input[name=logPtNo]').each(function(){
			var likeLog=$(this).val();			
			$("button[data-lilog='" + likeLog +"']").attr('disabled', 'true').css('color', "#00688B")
				.children('span').css('color', "#00688B");
		});
	});
	
</script>

<body >
	<div style=" background-color:#f5f5f5;">
		<div class="container" style="max-width:1300px;">
			<div class="cmtList" style="float:left; min-width:300px; width:51%;">
				<% 
					ArrayList<CmLikeBean> cmlog = (ArrayList)request.getAttribute("cmlog");
					ArrayList loglist = new ArrayList();
					for(CmLikeBean clBean : cmlog){
						loglist.add(clBean.getCommentNo());
					};
				%>
				 <c:set var="logCmN" value="<%=loglist%>" />
				 <c:forEach var = "f" begin="1" end="${logCmN.size() }">
				 	<input type="hidden" name="logPtNo" value="${logCmN[f-1]}">
				 </c:forEach>
				 
				
				<form name="frmTopLike" method="post">
					<input type="hidden" name="ptNo"  value="${ptNo}"/>
					<input type="hidden" id="logUser" name="userid"  value="${userid}"/>
					<input type="hidden" name="sort" value="${sort}"/>
				</form>
			
				<form name="frmCmt" method="post" >
					<div style=" margin:10px 10px 10px 0px; color:#708090; ">		
						<div style="">
							<h5 style="font-weight:bold; margin-left:63px;">Comments</h5>
						</div>
						<c:if test="${sessionScope.userid != null && !empty sessionScope.userid}">
							<div style="float:left;">
								<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" class="img-circle" 
									style=" width:44px; height:44px;">
							</div>						
						</c:if>
						<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
							<div style="float:left;">
								<img src="${pageContext.request.contextPath}/my_icon/defaultIcon.jpg" class="img-circle" 
									style=" width:44px; height:44px;">
							</div>					
						</c:if>	
									
						<div class="ptComment" style="float:left; width:70%; margin-left:15px; margin-bottom:20px;" >
							<textarea class="form-control"  rows="3" name="content" placeholder="댓글 입력"></textarea>
							<div id="cError" style="float:left; margin-top:5px;">
							</div>
							<div>
								<input type="button" class="btn btn-info btn-m" value="입력" id="insertCm"
									style="float:right; margin-top:10px; width:100px;" onclick="postComment()">
							</div>
						</div>
					</div>
				</form>
			
<!-- Top comment -->			
		<div id="topLine" style="clear:both; max-width:500px; border-top: solid #cccccc 1px;">
			<div id="gryFont" >
			<!-- hashMap -->
				<input type="hidden" name="userid2" id ="userid" value="${param.userid}"/>
				<input type="hidden" name="ptNo2" id ="ptNo" value="${param.ptNo}"/>
				<input type="hidden" name="tCmSize" value="${tCmSize}"/>
				<input type="hidden" name="cmSize" value="${cmSize}"/>
				
				<c:if test="${tCmSize >= 1}">
					<div style="padding:20px 10px 0px 63px; color:#708090; ">
						TOP Comments 
					</div>
					
						<c:set var="cnt" value="0"/>
						<c:forEach var="i" begin="0" end="${tCmSize-1}" >
							<c:set var="cnt" value="${cnt+1 }" />
							<c:set var="mcvBean" value="${tAlist[i]}"  />
							<c:set var="clikeBean" value="${cmlog[i]}" /><!-- commentlog삽입 -->				
								<div class="tCmB" id="tCmBox${cnt}" data-sortNo="${mcvBean.sortNo}" style="padding:15px 0 25px 0;">
			<!-- 유저아이콘 -->			<div style="float:left;">
										<a href="${pageContext.request.contextPath}/user/user/userMain.do?userid=${mcvBean.userid}">
											<img src="${pageContext.request.contextPath }/my_icon/${mcvBean.myIcon}" id="trIcon" class="img-circle" 
												style="width:44px; height:44px;">
										</a>	
									</div>
									
			<!-- 리플 아이디출력 -->
									<div id="trId" style=" margin-left:63px; color:#4682b4; font-size:10pt; font-weight:bold;" >
										<div  style="float: left; ">
											<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${mcvBean.userid}" style="text-decoration: none;">
												<c:set var="flName" value="${mcvBean.fName} ${mcvBean.lName}" />								
												<c:if test="${fn:length(flName)>=20}">
													${fn:substring(flName, 0, 20)}...
												</c:if>
												<c:if test="${fn:length(flName)<20 }">
												${flName}
												</c:if>
											</a>
										</div>
			<!-- 리플화살표/아이디 보류-->						
											<%-- <div id="arrowId_${mcvBean.sortNo }" style="visibility:visible; ">
												<i style="margin-left:10px;" class="fa fa-arrow-right"></i>
												<c:if test="${fn:length(mcvBean.userid)>=7}">
													<!-- 회원가입에서 20varchar넘으면에에러수정 -->
													${fn:substring(mcvBean.userid, 0, 7)}...
												</c:if>
												<c:if test="${fn:length(mcvBean.userid)<7 }">
												${mcvBean.userid} 
												</c:if>
											</div> --%>
										
			<!-- 유저 넥네임  -->		</div>
										
			<!-- 탑 좋아요 추천-->													
									<div style="" > <!-- 좋아요 버튼  -->
											<c:if test="${mcvBean.userid==userid}">
												<c:if test="${mcvBean.sortNo == 0}">
													<button type="button" class="btn btn-cmBtn btn-xs disabled" id ="tCmId${cnt}" onclick="tCmLikeBtn(${cnt})"
														value="${mcvBean.commentLike}" style="color:#00688B;">
															<i class="fa fa-thumbs-up fa-lg" style="" ></i>
																<span id="tCmId2${cnt}" style="margin-top:2px; font-size:10pt; color:#00688B;">
																	${mcvBean.commentLike}</span>
													좋아요</button>
												</c:if>
											</c:if><!-- 정상과 비정상 테이블 비교 -->

											<c:if test="${mcvBean.userid != userid}">
												<c:if test="${mcvBean.sortNo == 0}">
													<button type="button" class="btn btn-cmBtn btn-xs" id ="tCmId${cnt}" data-liLog="${mcvBean.commentNo}" onclick="tCmLikeBtn(${cnt})"
														value="${mcvBean.commentLike}" style="color:#00bfFf;">
															<i class="fa fa-thumbs-o-up fa-lg" style="" ></i> 
																<span id="tCmId2${cnt}" style="margin-top:2px; font-size:10pt; color:#00bfFf;">
																	${mcvBean.commentLike}</span>
													좋아요</button>
												</c:if>
											</c:if>																										
										<input type="hidden" name="commentNo" id ="topCmNo${cnt}" value="${mcvBean.commentNo}"/> <!-- 코멘트넘버 -->
										<input type="hidden" name="sortNo" id ="topCmSoNo${cnt}" value="${mcvBean.sortNo }">
										<input type="hidden" name="commentUser" id ="topCmUsr${cnt}" value="${mcvBean.commentUser }"> <!-- 코멘트 작성자 회원넘버 -->
										<input type="hidden" name="userid" id ="topUsrid${cnt}" value="${mcvBean.userid}"> <!-- 코멘트작성자 아이디 -->
										<input type="hidden" name="userid" id ="cmUsrid${cn}" value="${clikeBean.userid}">		
										
			<!-- 리플버튼 -->	
																		
										<span style=""><!-- 리플  -->
											<button type="button" class="btn btn-cmBtn btn-xs" id="${cnt}" onclick="tReplybtn(${cnt})"
												style="color:#708090;">
											<i class="fa fa-pencil fa-lg"></i> 리플</button>
										</span>
			<!-- 삭제버튼  -->						
										<c:if test="${mcvBean.userid==userid }">
											<input type="hidden" id="tDelcmNo${cnt}" name="commentNo" value="${mcvBean.commentNo }">
												<span style=""><!-- 삭제 -->
													<button type="button" class="btn btn-cmBtn btn-xs" value="${mcvBean.commentNo}" onclick="delTCm(${cnt})"
														style="color:#ADADAD;">
													<i class="fa fa-times fa-lg"></i></button>
												</span>
										</c:if>				
									</div>
									
			<!-- 코멘트 내용 -->
									<div style=" margin-left:63px;font-weight:normal; color:#708090;">	<!-- 내용 -->
										${mcvBean.content}
									</div>
									
<!-- 탑 코멘트리플입력창 -->						
			<!-- treply 별도 컨트롤러 안거치고 다이렉트로 디테일 컨트롤러로 보냄 -->
			<form name="formTopRply${cnt}" id="formTopRply${cnt}" method="post" action='<c:url value="/photo/comments/cmReply.do" />'>
				<input type="hidden" name="ptNo"  value="${ptNo}"/>
				<input type="hidden" name="userid"  value="${userid}"/>
				<input type="hidden" name="sort" value="${sort}"/><!-- sort, userid, ptNo입력은 ajax로 변경 예정 -->
				<input type="hidden" name="sortNo" value="${mcvBean.sortNo}"/>
				<input type="hidden" name="commentNo" value="${mcvBean.commentNo}"/>
				<input type="hidden" name="groupNo" value="${mcvBean.groupNo}"/>
				<input type="hidden" name="sortGroup" value="${mcvBean.sortGroup}"/>
				<input type="hidden" name="step" value="${mcvBean.step}"/>
				
				
									<div class="form-group" id="trpId_${cnt}" style=" margin:20px 0 0 0; display:none;">	
										<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
											<label style="float:left;">
												<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" class="img-circle" 
													style="float:left; width:44px; height:44px;">
											</label>
										</c:if>
										
										<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
											<label style="float:left;">
												<img src="${pageContext.request.contextPath}/my_icon/defaultIcon.jpg" class="img-circle" 
													style="float:left; width:44px; height:44px;">
											</label>
										</c:if>
										
										<div style="float:left; margin-left:15px; width:80%; margin-bottom:20px;" >
											<textarea class="form-control" name="content" id="treply_${cnt}" rows="2" placeholder="댓글 입력"></textarea>
											<span id="trError_${cnt}" style="float:left; margin-top:5px;"></span>
											<div style="float:right; margin:5px 0 0 0;">
												<input type="button" class="btn btn-info btn-m" style="height:33px;" value="입력" id="tRple"
													onclick="submitTrple(${cnt})"><!-- submitTrple리플넘기기 -->
											</div>
										</div>
									</div>
			</form>													
											
									<%-- </c:if> --%>
			<!--리플/삭제 끝  -->
			<!-- 탑 추천 끝-->													
		</div>
			<!-- 탑 추천 끝 -->				
						</c:forEach>
								
					<!-- <div style="border-top:1px solid #CCCCCC; min-width:300px; margin:10px 0 0 60px;">
					</div>underline -->
								
			<!-- log --></c:if>  
			</div>
		</div>
			
<!-- 일반 코멘트 -->
			
		<div class="commentDiv" style="clear:both; max-width:500px; border-top: solid #cccccc 1px;">
			<div style="margin:20px 0 10px 0px; color:#708090;">
			<!-- hashMap -->
				<input type="hidden" name="userid2" id ="userid" value="${param.userid}"/>
				<input type="hidden" name="ptNo2" id ="ptNo" value="${param.ptNo}"/>
				
				
					
				<c:if test="${cmSize > 0}">
					<c:set var="cn" value="0"/>
						<c:forEach var="i" begin="0" end="${cmSize-1}" >
							<c:set var="cn" value="${cn+1 }" />
							<c:set var="mcvBean" value="${alist[i]}"  />
							<c:set var="clikeBean" value="${cmlog[i]}" /><!-- commentlog삽입 -->
																				
								<div class="cmB" data-sortNo="${mcvBean.sortNo}" id="cmBox${cn}" style="padding:15px 0 25px 0;">
								
			<!-- 유저아이콘 -->			<div style="float:left;">
										<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${mcvBean.userid}">
											<img src="${pageContext.request.contextPath }/my_icon/${mcvBean.myIcon}" id="rIcon" class="img-circle"  
												style=" width:44px; height:44px;">
										</a>
									</div>
			
			<!--아이디간격 -->			
			<!-- 리플 아이디출력 --><!-- 유저아이디 15자 넘으면 이후글자 말줄임표 --><!-- 회원가입에서 20varchar넘으면에에러수정 -->
									<div id="trId" style="margin-left:63px; color:#4682b4; font-size:10pt; font-weight:bold;" >
										<div  style="float: left; ">
											<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${mcvBean.userid}" style="text-decoration: none;">
												<c:set var="flName" value="${mcvBean.fName} ${mcvBean.lName}" />								
												<c:if test="${fn:length(flName)>=20}">
													${fn:substring(flName, 0, 20)}...
												</c:if>
												<c:if test="${fn:length(flName)<20 }">
												${flName}
												</c:if>
											</a>
										</div>
									</div>
				
			<!-- 좋아요 추천-->															
									<div style="" > <!-- 좋아요 버튼  -->
										<c:if test="${mcvBean.userid == userid  }">
											<c:if test="${mcvBean.sortNo == 0}">
											<button type="button" class="btn btn-cmBtn btn-xs disabled" id ="cmId${cn}" onclick="cmLikeBtn(${cn})"
											value="${mcvBean.commentLike}" style="color:#00688B;"><i class="fa fa-thumbs-up fa-lg" style="" >
											</i>
												<span id="cmId2${cn}" style="margin-top:2px; font-size:10pt; color:#00688B;">
												${mcvBean.commentLike}</span>
											좋아요</button>
											</c:if>
										</c:if>
									
										
										<c:if test="${mcvBean.userid != userid }">
											<c:if test="${mcvBean.sortNo == 0}">
											<button type="button" class="btn btn-cmBtn btn-xs" id ="cmId${cn}" data-liLog="${mcvBean.commentNo}" onclick="cmLikeBtn(${cn})"
											value="${mcvBean.commentLike}" style="color:#00bfFf;"><i class="fa fa-thumbs-o-up fa-lg" style="" >
											</i> 
												<span id="cmId2${cn}" style="margin-top:2px; font-size:10pt; color:#00bfFf;">
												${mcvBean.commentLike}</span>
											좋아요</button>
											</c:if>
										</c:if>																										
										
										<input type="hidden" name="commentNo" id ="cmNo${cn}" value="${mcvBean.commentNo}"/> <!-- 코멘트넘버 -->
										<input type="hidden" name="sortNo" id ="cmSoNo${cn}" value="${mcvBean.sortNo }">
										<input type="hidden" name="commentUser" id ="cmUsr${cn}" value="${mcvBean.commentUser }"> <!-- 코멘트 작성자 회원넘버 -->
										<input type="hidden" name="userid" id ="cmUsrid${cn}" value="${mcvBean.userid}"> <!-- 코멘트작성자 아이디 -->	
										<input type="hidden" name="userid" id ="cmUsrid${cn}" value="${clikeBean.userid}">
										<!-- 좋아요 활성 비활성 cmlog에서 걸리고 다른것도 테스트-->	
										
			<!-- 리플버튼 -->	
																	
									<span style=""><!-- 리플  -->
										<button type="button" class="btn btn-cmBtn btn-xs" id="${cn}" onclick="replybtn(${cn})"
										style="color:#708090;">
										<i class="fa fa-pencil fa-lg"></i> 리플</button>
									</span>
			<!-- 삭제버튼  -->						
									<c:if test="${mcvBean.userid == userid }">
										<input type="hidden" id="delcmNo${cn}" name="commentNo" value="${mcvBean.commentNo }">
											<span style=""><!-- 삭제 -->
												<button type="button" class="btn btn-cmBtn btn-xs" value="${mcvBean.commentNo}" onclick="delCm(${cn})"
													style="color:#ADADAD;">
												<i class="fa fa-times fa-lg"></i></button>
											</span>
									</c:if>				
									</div>
									
			<!-- 코멘트 내용 -->
									<div style="margin-left:63px; margin-bottom:10px;*/">	<!-- 내용 -->
										${mcvBean.content}						
									</div>						
			
							
<!--일반 코멘트리플입력창 -->						
			<!-- reply 별도 컨트롤러 안거치고 다이렉트로 디테일 컨트롤러로 보냄 -->
			<form name="formRply${cn}" id="formRply${cn}" method="post" action='<c:url value="/photo/comments/cmReply.do" />'>
				<input type="hidden" name="ptNo"  value="${ptNo}"/>
				<input type="hidden" name="userid" value="${userid}"/>
				<input type="hidden" name="sort" value="${sort}"/><!-- sort, userid, ptNo입력은 ajax로 변경 예정 -->
				<input type="hidden" name="sortNo" value="${mcvBean.sortNo}"/>
				<input type="hidden" name="commentNo" value="${mcvBean.commentNo}"/>
				<input type="hidden" name="groupNo" value="${mcvBean.groupNo}"/>
				
									<div class="form-group" id="rpId_${cn}" style="clear:both; margin:20px 0 0 0; display:none;  ">
										
										<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
											<label style="float:left;">
												<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" class="img-circle" 
													style="float:left; width:44px; height:44px;">
											</label>
										</c:if>
										<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
											<label style="float:left;">
												<img src="${pageContext.request.contextPath}/my_icon/defaultIcon.jpg" class="img-circle" 
													style="float:left; width:44px; height:44px;">
											</label>
										</c:if>										
										<div style="float:left; margin-left:15px; width:80%; margin-bottom:20px;" >
											<textarea class="form-control" name="content" id="reply_${cn}" rows="2" placeholder="댓글 입력"></textarea>
											<span id="rError_${cn}" style="float:left; margin-top:5px;"></span>
											<div style="float:right; margin:5px 0 0 0;">
												<input type="button" class="btn btn-info btn-m" style="height:33px;" value="입력" id="rple"
												onclick="submitrple(${cn})"><!-- submitTrple리플넘기기 --><!-- tRple201706 -->
											</div>
										</div>
									</div>
			</form>													
											
									<%-- </c:if> --%>			
			</div>
								</c:forEach>
								<!-- <div style="margin:20px;"></div> -->
					
			<!-- log --></c:if>
			</div>
			</div>
			
		</div>
		
			<!-- user view log -->
			<div class="faveList" style="display: inline-block; min-height: 75px; float:right; min-width:300px; width:45%; backgrouond-color:#f5f5f5;">
				<div class="faveDiv" style="float:left;">
					<c:import url="/photo/faves/faveList.do" />
				</div>
			</div>
		
		</div>
	</div>
</body>
<script type="text/javascript">

$(window).load(function(){
	$(window).resize(function(){
		var diSize = $(window).width();
		if(diSize >= 850){
			$('.cmtList').css({'width':'51%'});
			$('.faveList').css({'width':'45%'});
			$('.faveList').css({'float':'right', 'padding-top':''});
			$('.commentDiv').css({'border-bottom':''});
		}
		if(diSize < 850){
			$('.cmtList').css({'width':'80%'});
			$('.faveList').css({'width':'100%'});
			$('.faveList').css({'float':'left', 'padding-top':'10px', 'margin-left':'-25px'});
			$('.commentDiv').css({'border-bottom':'solid 1px #cccccc'});
		}
	}).resize();
});

$(".ptComment").click(function(){
	var t = ${cmSize};
	var tc = ${tCmSize};
	if(t != 0 && t != null ){
		$('[id^=rpId_]').css('display','none');	
	}
	if(tc != 0 && tc != null ){
		$('[id^=trpId_]').css('display','none');
	}
});

$(document).ready(function(){
	var topSize = $('input[name="tCmSize"]').val();
	var commonSize = $('input[name="cmSize"]').val();
	if(topSize == 0){
		$("#topLine").css('border-top', '');
	}else{
		$("#topLine").css('border-top', 'solid #cccccc 1px');
	};
	if(commonSize == 0){
		$(".commentDiv").css('border-top', '');
	}else{
		$(".commentDiv").css('border-top', 'solid #cccccc 1px');
	};
});

</script>
