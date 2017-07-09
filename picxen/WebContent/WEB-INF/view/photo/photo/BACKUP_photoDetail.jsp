<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
	
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
</head>
	<% //로그인 체크 여부
		String cmUserid = (String)session.getAttribute("userid");
		boolean cmIsLogin = false;
		if(cmUserid != null && !cmUserid.isEmpty()){
			cmIsLogin = true;
		}
	%>

	<%String ip=request.getHeader("x-forwarded-for");
	
	if(ip==null||ip.length()==0){
		ip=request.getHeader("Proxy-Client-IP");
	}
	
	if(ip==null||ip.length()==0){
		ip=request.getHeader("WL-Proxy-Client-IP");
	}

	if(ip==null||ip.length()==0){
		ip=request.getRemoteAddr();
		System.out.println("ptIp="+ip);
		
	}//ip확인  ip파라미터
	%>

<script type="text/javascript">
	function goPre(){
		frmPre.action
		="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do";
		frmPre.submit();
	}
	
	function goNext(){
		frmNext.action
		="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do";
		frmNext.submit();
	}
	
 	function bgBtn(){
		var bgNo = frmBg.bgNo.value;
		alert("BGparameter=="+ bgNo);
		if(!bgNo){
			return false;
		}
		frmBg.action 
		="${pageContext.request.contextPath}/user/user/userBG.do";
		
		/* "${pageContext.request.contextPath}/user/user/userMain.do?userid=${mbBean.userid}"; */
		frmBg.submit();
	}

</script>

<body>
<form name="frmBg" method="get">	
	<input type="hidden" name="userid" value="${userid}">
	<input type="hidden" name="bgNo" value="${ptBean.photoNo}">
	<input type="hidden" name="sort" value="${sort}">	
</form>

<form name="frmPt" method="post">

<div style="border-bottom:solid 1px #f5f5f5; /* background-color:#000000; */">

<div style=" min-width:1300px;">
<!-- 왼쪽버튼 -->
	 <div style="position:absolute; padding-top:300px;   margin-left:20px; ">
	 	<c:set var="tBean" value="${clist[totalCRecord-totalCRecord]}"/><!-- firstPhoto  -->
	 	<input type="hidden" value="${tBean.photoNo }">
		 <c:if test="${tBean.photoNo==ptNo }">
		 	<button type="button" class="btn btn-default disabled"  value="" onclick="goPre()">
		  	<i class="fa fa-angle-double-left fa-3x" style="color:gray;"></i></button>
		  </c:if>
		 <c:if test="${tBean.photoNo!=ptNo }">
		 	<button type="button" class="btn btn-default"  value="" onclick="goPre()">
		 	<i class="fa fa-angle-double-left fa-3x" style="color:gray;"></i></button>
		 </c:if>
	</div>

<!-- 오른쪽 버튼 -->
	<div style=" float:right; padding-top:300px;   margin-right:20px;">
		<c:set var="tBean" value="${clist[totalCRecord-1]}"/><!-- lastPhoto  -->
		<input type="hidden" value="${tBean.photoNo}">
		 <c:if test="${tBean.photoNo==ptNo }">
		  <button type="button" class="btn btn-default disabled" value="" onclick="goNext()">
			  <i class="fa fa-angle-double-right fa-3x" style="color:gray;"></i></button>
		  </c:if>
		<c:if test="${tBean.photoNo!=ptNo }">
		  <button type="button" class="btn btn-default" value="" onclick="goNext()">
			  <i class="fa fa-angle-double-right fa-3x" style="color:gray;"></i></button>
		</c:if>
  	  
</div>
</div>

<!-- 포토이미지 -->
<div class="" style="display: table; margin: 0 auto; ">
<div class="" style="margin:15px;">
	<img src="${pageContext.request.contextPath }/pt_images/${ptBean.imageURL}" 
		style="  /* width:100%; */ padding-left:50px;  max-width:1200px; min-height:700px; max-height:750px; ">		
</div>
</div>
</div>

<!-- 아이콘 정보 -->
<div style="border-bottom:solid 1px #f5f5f5; min-height:75px;">
<div style="display:table; margin: 0 auto; min-width:1300px;    ">

<div style="margin:15px 10px 10px 30px; float:left; ">
	<img src="${pageContext.request.contextPath}/my_icon/${mbBean.myIcon}" style=" width:55px; height:55px; ">
</div>

<div style="float:left; margin-top:13px;">
<div style="/* float:left; */ font-size:27px; height:35px; color:#575757; width:300px; ">
	${ptBean.photoTitle}
</div>

<div style="font-weight:bold; color:#575757; width:300px; ">
	${mbBean.userid} 
</div>
</div>

<div style=" /* position:relative; left:300px; */ float:right; margin:10px;">

	<c:if test="${mbBean.userid == userid }">
		<div style="float:left; margin-right:12px;"> 
			<div style=" /* margin-right:8px; */  ">
				<button type="button" class="btn btn-default btn-sm" id="bgPtNo" value="${ptBean.photoNo}" onclick="javascript:bgBtn()" 
					style="color:#BEBEBE; width:120px;">배경저장${ptBean.photoNo}</button>
					<%-- <input type="text" name="bgNo" value="${ptBean.photoNo}"> --%>
			</div>
			
			<div style="margin-top:4px;  ">
				<a href="${pageContext.request.contextPath }/user/user/setPhoto.do?userid=${userid}"
					type="button" class="btn btn-default btn-sm" style="color:#BEBEBE; width:120px;">사진수정</a>
			</div>
		</div>
	</c:if>
	
	<div style="float:left; width:90px; font-weight:bold; padding-top:12px;">
		<span style="color:#708090; ">조회수: ${ptBean.viewCnt }</span><br>
		<span style="color:#708090; ">좋아요: ${ptBean.likeCnt }</span><br>
		<span style="color:#708090; ">퍼가요: ${ptBean.faves }</span><br>
	</div>
	
	<div style="float:left; margin-top:10px; margin-right:15px;">
		<span id="rankScore" style="/* float:left; */ font-size:35px; color:#708090;">
			<fmt:formatNumber value="${ptBean.popular }" type="Number" pattern="###.#"/></span>
		<span style="/* float:left; */ font-size:15px; color:#708090;">Pops</span>
	</div>
	
<!-- LIKE  -->	
	<c:if test="${mbBean.userid == userid }">
		<div style="float:left; margin-right:8px;  ">
			<a href="#" type="button" class="btn btn-dtailDis btn-lg disabled" style="color:white; font-size:10pt;">
				<i class="glyphicon glyphicon-thumbs-up" style="font-size:20px; color:white;"></i><br>Like</a>
		</div>
		
	</c:if>
	<c:if test="${mbBean.userid != userid }">
		<div style="float:left; margin-right:8px;  ">
			<a href="${pageContext.request.contextPath }/photo/photo/photoLikeUpdate.do?ptNo=${ptNo}&userid=${userid}&sort=${sort}"
				type="button" id="idLike" class="btn btn-info btn-lg" style="color:white; font-size:10pt;">
				<i class="glyphicon glyphicon-thumbs-up" style="font-size:20px; color:white;"></i><br>Like</a>
		</div>
	</c:if>

<!-- HEART  -->	
	<c:if test="${mbBean.userid == userid }">
		<div style="float: left; margin-right:8px;">
			<a href="#" type="button" class="btn btn-dtailDis btn-lg disabled" style="font-size:10pt;">
			<i class="glyphicon glyphicon-heart white" style="font-size:20px; color:white; height:33px; margin-top:10px;"></i></a>
		</div>
	</c:if>
	
	<c:if test="${mbBean.userid != userid }">
		<div style="float: left; margin-right:8px;">
			<a href="${pageContext.request.contextPath }/photo/faves/favesAdd.do?photoNo=${ptNo}&fUserId=${userid}&sort=${sort}" 
				type="button" id="idHeart" class="btn btn-danger btn-lg" style="font-size:10pt;">
			<i class="glyphicon glyphicon-heart white" style="font-size:20px; color:white; height:33px; margin-top:10px;"></i>${param.uid }</a>
		</div>
	</c:if>
	
	<!--edit  -->
	
</div>


</div>
</div>

<!-- description -->
<div style="/* border-bottom:solid 1px #f5f5f5; */">
<div style="display:table; margin: 0 auto; min-width:1300px;    ">

	<div>
		<c:if test="${ptBean.description==null}">
		</c:if>
		<c:if test="${ptBean.description!=null}">
			<div style="margin:20px 10px 10px 30px; width:600px; color:#708090; /* border:solid 1px blue; */">
				<h5 style="font-weight:bold;">DESCRIPTION</h5>
				<div style="">
					${ptBean.description}
				</div>
			</div>
		</c:if>
	</div>
	<div>
		<c:if test="${ptBean.tag==null}">
		</c:if>
		<c:if test="${ptBean.tag!=null}">
			<div style="margin:15px 10px 10px 30px; width:400px; color:#708090; /* border:solid 1px blue; */">
				<h5 style="font-weight:bold;">Tags</h5>
				<div style="">
					${ptBean.tag}
				</div>
			</div>
		</c:if>
	</div>

</div>
</div>

</form>

  <form name="frmPre" method="post">
		  <c:forEach var="i" begin="1" end="${totalCRecord}">
			<c:if test="${ptNo >=1 }"> 
				<c:set var="pBean" value="${clist[i-1]}" /> 
					<c:if test="${pBean.photoNo==ptNo }">
						<c:set var="pBean" value="${clist[i-2]}"/>
							<h3><input type="hidden" id="pre" name="ptNo" value="${pBean.photoNo}"></h3>
					</c:if>
			 </c:if>
		</c:forEach>  
		<input type="hidden" name="userid" value="${userid }">
		<input type="hidden" name="ip" value="<%=ip %>">
		<input type="hidden" name="sort" value="${sort}">
 </form> 
 
 <form name="frmNext" method="post">
		 <c:forEach var="i" begin="1" end="${totalCRecord}">
			<c:if test="${ptNo >=1 }">
				<c:set var="pBean" value="${clist[i-1]}" /> 
					<c:if test="${pBean.photoNo==ptNo }">
						<c:set var="pBean" value="${clist[i]}"/>
							<h3><input type="hidden" id="next" name="ptNo" value="${pBean.photoNo}"></h3>
					</c:if>
			</c:if>
		</c:forEach>  
		<input type="hidden" name="userid" value="${userid }">
		<input type="hidden" name="ip" value="<%=ip %>">
		<input type="hidden" name="sort" value="${sort}">
 </form> 

</body>


<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
		<div id="comments" >
			<%@ include file="../../photo/comments/comment.jsp" %>
		</div><!-- comments -->
</c:if>

<script type="text/javascript" >
	$(document).ready(function(){
		var ckptNo={"ptNo":"<c:out value='${ptNo}'/>"} ;
		var uid = "<c:out value='${userid}'/>";
		
		 $.ajax({
			url:"${pageContext.request.contextPath}/photo/faves/faveList.do",
			type:'GET',
			data:ckptNo,
			success:function(data){
				var emFl = $(data).find('#uid').val();
					if(emFl != null && emFl == uid){
						$('#idHeart').attr('class', 'btn btn-dtailDis btn-lg disabled');
					}
			}
		});
	}); //ptno 애정컨트롤러 파라미터
	
	//likeLog
	var chkL = "<c:out value='${chkLike}'/>";
	if(chkL == "true"){
		$('#idLike').attr('class', 'btn btn-dtailDis btn-lg disabled');
	};
	
</script>
	

<%@ include file="../../inc/mainBottom.jsp" %>