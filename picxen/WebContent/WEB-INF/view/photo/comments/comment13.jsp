<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>						
<body >
<script type="text/javascript">
	function postComment(){
		if(!frmCmt.content.value){
			document.getElementById('cError').innerHTML="내용을 입력하십시오.";
			document.getElementById('cError').style.color="#708090";
			frmCmt.content.focus();
			return false;
		}
		frmCmt.submit();
	}
	
	function submitTrple(cnt){
		var c = cnt;
		var tr = document.getElementById("treply_"+c); 
		if(!tr.value){
			document.getElementById("trError_"+c).innerHTML="내용을 입력하십시오.";
			document.getElementById("trError_"+c).style.color="#708090"; 
			tr.focus();
			return false;
		}
		document.forms['formTopRply'+c].submit();
	}
		
	
	 function cmLikeBtn(cnt){
		 var c = cnt;
		 var f =document.getElementById("tCmId"+c);
		 var g = 1; 
		 var h = +f.value + g;
		 f.value = h;
		 document.getElementById("tCmId2"+c).innerHTML=h;
		 /* alert(c); */
		 /*click disabled  */
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
				 /* alert("완료!"); */
				 window.opener.location.reload();
				 self.close();
			 },
			 error:function(jqXHR, textStatus, errorThrown){
				 alert("에러발생 \n" + textStatus+ " : " + errorThrown); 
				 self.close();
			 }
			 
		 });		
	  }

	 function tReplybtn(cnt) {
		 var z = 0;
		 var t = ${tCmSize};
		 for(var g = 0; g<t; g++){
			z++;
			var s = document.getElementById("rpId_"+z);
			var e = document.getElementById("rpId_"+cnt);
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
			 error:function(jqXHR, textStatus, errorThrown){
				 alert("에러발생 \n" + textStatus+ " : " + errorThrown); 
				 self.close();
			 	}
			 });
		 
		 document.getElementById("tCmBox"+f).style.display='none';
	 }//
	 
	 ///////////일반코멘트

	 
</script>
<form name="frmTopLike" method="post">
	<input type="hidden" name="ptNo"  value="${ptNo}"/>
	<input type="hidden" name="userid"  value="${userid}"/>
	<input type="hidden" name="sort" value="${sort}"/>
</form>

<form name="frmCmt" method="post" >
<div style="background-color:#f5f5f5; min-height:75px; /* border:1px solid blue; */">
<div style="display:table; margin: 0 auto; min-width:1300px; ">
<div style="margin:10px 10px 10px 30px; color:#708090;/*  border:1px solid blue; */">
			
			<div style="">
				<h5 style="font-weight:bold;">Comments</h5>
			</div>
	
				<div style="float:left;">
					<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" style=" width:70px; height:70px;">
				</div>
				
				<div style="float:left; width:700px; margin-left:15px; " >
					<textarea class="form-control"  rows="3" name="content" placeholder="댓글 입력"></textarea>
					<div id="cError" style="float:left;">
					</div>
					<div>
						<input type="button" class="btn btn-info btn-m" value="입력" id="insertCm"
							style="float:right; margin-top:10px; width:100px;" onclick="postComment()">
					</div>
				</div>
</div>
</div> 
</div>

</form>

<div style="display:table; margin: 0 auto; min-width:1300px; ">
<div style="margin:20px 10px 10px 30px; color:#708090;">
<!-- hashMap -->
	<input type="hidden" name="userid2" id ="userid" value="${param.userid}"/>
	<input type="hidden" name="ptNo2" id ="ptNo" value="${param.ptNo}"/>
	
	
		
	<c:if test="${tCmSize >= 1}">
		<div style="font-weight:bold; margin-bottom:-30px;">
			TOP Comments
		</div>
		<div style=" margin:70px;">
		</div>
			<c:set var="cnt" value="0"/>
			<c:forEach var="i" begin="0" end="${tCmSize-1}" >
				<c:set var="cnt" value="${cnt+1 }" />
				<c:set var="mcvBean" value="${tAlist[i]}"  />
				<c:set var="clikeBean" value="${cmlog[i]}" /><!-- commentlog삽입 -->
									
 <!--탑 코멘트겉 테두리 float 옵션으로 테두리넘어가서 overflow: auto; 적용 -->									
					<div id="tCmBox${cnt}" style="overflow: auto; margin-top:30px; width:750px;">
					
					
<!-- 유저아이콘 -->			<div style="float:left;">
								<img src="${pageContext.request.contextPath }/my_icon/${mcvBean.myIcon}" style=" width:70px; height:70px;">	
						</div>

						<div style="/* float:left; */ margin-left:80px;/* 아이디간격 */  width:240px; color:#4682b4; font-size:10pt; font-weight:bold;" >
							<c:if test="${fn:length(mcvBean.userid)>=7}">
							<!-- 유저아이디 15자 넘으면 이후글자 말줄임표 --><!-- 회원가입에서 20varchar넘으면에에러수정 -->
								${fn:substring(mcvBean.userid, 0, 7)}...
							</c:if>
							<c:if test="${fn:length(mcvBean.userid)<7 }">
							${mcvBean.userid} 
							</c:if>
<!-- 유저 넥네임  -->		</div>
						
<!-- before내용 -->						
						<%-- <div style="float:right;  width:530px; /* margin-left:90px; */ /* border:1px solid green; */">	<!-- 내용 -->
							${mcvBean.content}
						</div> --%>

			
<!-- 탑 좋아요 추천-->			
						<%-- <c:if test="${mcvBean.commentLike >= 1 }"> --%>  	<!--불필요 -->													
						<div style=" /* float:left; */   margin-left:80px; /* padding-top:15px; */" > <!-- 좋아요 버튼  -->	
							<c:if test="${mcvBean.userid == userid || clikeBean.userid == userid}">
								<button type="button" class="btn btn-cmBtn btn-xs disabled" id ="tCmId${cnt}" onclick="cmLikeBtn(${cnt})"
								value="${mcvBean.commentLike}" style="color:#00688B;"><i class="fa fa-thumbs-up fa-lg" style="" >
								</i>
									<span id="tCmId2${cnt}" style="margin-top:2px; font-size:10pt; color:#00688B;">
									${mcvBean.commentLike}</span>
								좋아요</button>
							</c:if><!-- 정상과 비정상 테이블 비교 -->
							
							<c:if test="${mcvBean.userid != userid && clikeBean.userid != userid}">
								<button type="button" class="btn btn-cmBtn btn-xs" id ="tCmId${cnt}" onclick="cmLikeBtn(${cnt})"
								value="${mcvBean.commentLike}" style="color:#00bfFf;"><i class="fa fa-thumbs-o-up fa-lg" style="" >
								</i> 
									<span id="tCmId2${cnt}" style="margin-top:2px; font-size:10pt; color:#00bfFf;">
									${mcvBean.commentLike}</span>
								좋아요</button>
							</c:if>																										
							
							<input type="hidden" name="commentNo" id ="topCmNo${cnt}" value="${mcvBean.commentNo}"/> <!-- 코멘트넘버 -->
							<input type="hidden" name="sortNo" id ="topCmSoNo${cnt}" value="${mcvBean.sortNo }">
							<input type="hidden" name="commentUser" id ="topCmUsr${cnt}" value="${mcvBean.commentUser }"> <!-- 코멘트 작성자 회원넘버 -->
							<input type="hidden" name="userid" id ="topUsrid${cnt}" value="${mcvBean.userid}"> <!-- 코멘트작성자 아이디 -->		
							
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
						<div style="float:left; margin-left:10px; width:530px; /* border:1px solid green; */">	<!-- 내용 -->
							${mcvBean.content}
						</div>						
						
						<!-- <div style="position:absolute; float:left; border:1px solid red; width:10px; height:80px;">
						</div> 댓글 연결선--> 

<!-- 코멘트리플 간격 -->						
						<div style="float:left; width:700px;">
						</div>					
<!-- 코멘트리플입력창 -->						
<!-- treply 별도 컨트롤러 안거치고 다이렉트로 디테일 컨트롤러로 보냄 -->
<form name="formTopRply${cnt}" id="formTopRply${cnt}" method="post" action='<c:url value="/photo/comments/cmReply.do" />'>
	<input type="hidden" name="ptNo"  value="${ptNo}"/>
	<input type="hidden" name="userid"  value="${userid}"/>
	<input type="hidden" name="sort" value="${sort}"/><!-- sort, userid, ptNo입력은 ajax로 변경 예정 -->
	<input type="hidden" name="sortNo" value="${mcvBean.sortNo}"/>
	<input type="hidden" name="commentNo" value="${mcvBean.commentNo}"/>
	<input type="hidden" name="groupNo" value="${mcvBean.groupNo}"/>
	
						<div id="rpId_${cnt}" style="float:left; margin-top:10px; class:form-group; display:none;">		
							<label style="float:left;">
								<img src="${pageContext.request.contextPath}/my_icon/${mcvBean.myIcon}" 
									style="float:left; width:50px; height:50px;">
							</label>
							<div style="float:left; margin-left:15px; width:500px;" >
								<textarea class="form-control" name="content" id="treply_${cnt}" rows="2" placeholder="댓글 입력"></textarea>
								<div id="trError_${cnt}" style="float:left;"></div>
							</div>
							<div style="float:right; margin-left:5px;">
								<input type="button" class="btn btn-info btn-m" style="height:45px;" value="입력" id="tRple"
									onclick="submitTrple(${cnt})"><!-- submitTrple리플넘기기 -->
							</div>
						</div>
</form>													
								
						<%-- </c:if> --%>
<!--리플/삭제 끝  -->
<!-- 탑 추천 끝-->													
					</div>
<!-- 탑 추천 끝 -->				
					</c:forEach>
					
		<div style="border:0.5px solid silver; width:800px; margin-top:30px;">
		</div>
					
<!-- log --></c:if>  
</div>
</div>

<!-- 일반 코멘트 -->


</body>