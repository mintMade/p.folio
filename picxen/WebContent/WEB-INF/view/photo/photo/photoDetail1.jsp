<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
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

<head>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css"><!-- ori bootstrap -->
</head>

<script type="text/javascript">
	function goNext(){
		/* frmPt.next.value=pre; */
		e = document.getElementById("next");
		alert(e.value);
		<%-- g = ${totalCRecord};
		frmPt.action
		/* ="${pageContext.request.contextPath}/user/user/userMain.do?userid=${mbBean.userid}"; */
		="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=e&userid=${userid}&sort=pop&ip=<%=ip%>";
		frmPt.submit(); --%>
	}

	function bigImg(imgUrl){
		window.open("${pageContext.request.contextPath}/photo/photo/photoImage.do?imgUrl="
+imgUrl,"win",
"width=400, height=500, scrollbars=yes, resizable=yes, statusbar=yes, location=yes");
	}
</script>

<body>

<form name="frmPt" method="post">
<div id="container">
<div id="header">
<!-- 포토정보  -->
	<div class="title1" style="font-size:20pt; font-weight:bold; color:#ffffff;">
		${ptBean.photoTitle}
	</div>
	<!-- name1 css -->
	<div class="name1" style="font-size:13pt; font-weight:bold; color:#ffffff;">
		<a href="${pageContext.request.contextPath}/user/user/userMain.do?userid=${mbBean.userid}">
			${mbBean.lName}&nbsp;${mbBean.fName}</a>
	</div>
		
 	<div id="ptInfo1" style="float:left;"><!-- 출력겹침  -->
 		<span>
			이 사진을 ${ptBean.viewCnt}명이 보았습니다.
		</span>
	</div>
</div>
<table width="100%">
<tr>
<td>
	<table width="600" border="0">
		<tr>
			<td rowspan="6" width="45%" align="center">
				<!-- 사진 이미지 -->
				<a href="javascript:bigImg('${ptBean.imageURL}')">
				<img src="${pageContext.request.contextPath }/pt_images/${ptBean.imageURL}"
					border="0" width="250">
				</a>
				<br>
				<a href="javascript:bigImg('${ptBean.imageURL }')">큰이미지 보기</a>
		</tr>

		<tr>
			<td class="line">
			이 사진을 ${ptBean.viewCnt}명이 보았습니다.</td>
		</tr>

<!-- like -->
		<tr>
			<td class="line">
<%-- 			<c:if test="${likeCnt==false}">
				<a class="btn btn-info btn-lg" type="button" style="font-size:12px; color:white;"
					href="${pageContext.request.contextPath}/photo/photo/photoLikeUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}">
						<i class="glyphicon glyphicon-thumbs-up" style="font-size:24px; color:white;"></i>
					<br>Like</a>
				이 사진을 ${ptBean.likeCnt}명이 좋아합니다.
			</c:if>
			<c:if test="${likeCnt==true}">
				<a class="btn btn-info btn-lg" type="button" style="font-size:12px; color:gray;
					href="#">
						<i class="glyphicon glyphicon-thumbs-up" style="font-size:24px; color:gray;"></i>
					<br>Liked</a>
				이 사진을 ${ptBean.likeCnt}명이 좋아합니다.
			</c:if> --%>
			
				<a class="btn btn-info btn-lg" type="button" style="font-size:12px; color:white;"
					href="${pageContext.request.contextPath}/photo/photo/photoLikeUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}">
						<i class="glyphicon glyphicon-thumbs-up" style="font-size:24px; color:white;"></i>
					<br>Like</a>
				이 사진을 ${ptBean.likeCnt}명이 좋아합니다.
			</td>
		</tr>
<!-- like 끝-->
		
<!-- 즐겨찾기 -->
		<tr>
			<td class="line">
				<a class="btn btn-danger btn-lg" type="button" style="font-size:12px; color:white;"
					href="${pageContext.request.contextPath }/photo/faves/favesAdd.do?photoNo=${ptBean.photoNo}&fUserId=${userid}">
						<i class="glyphicon glyphicon-heart white" style="font-size:24px;"></i>
					<br>faves
				</a>
				이 사진을 ${ptBean.faves}명이 즐겨찾기 하였습니다.
			</td>
		</tr>		
<!-- 즐겨찾기 끝-->	
		<%-- <c:set var="count" value="0"/>
		<c:forEach var="i" begin="0" end="${totalCRecord -1}">
			<c:if test="${ptNo >=1 }">
				<c:set var="count" value="${count+1}" />
				<c:set var="ptBean" value="${clist[i]}" />
					<c:if test="${ptBean.photoNo==ptNo }">
						<div >
							<button class="btn btn-danger btn-lg">
								<br>${count}</button>
								${ptBean.photoNo}
							<input type="text" id="${count}" name="next" value="${ptBean.photoNo}">
						</div>
					</c:if>
			</c:if>
		</c:forEach> --%> 
			
		<c:set var="count" value="0"/>
		<c:forEach var="i" begin="0" end="${totalCRecord -1}">
			<c:if test="${ptNo >=1 }">
				<c:set var="count" value="${count+1}" />
				<c:set var="ptBean" value="${clist[i]}" />
					<c:if test="${ptBean.photoNo==ptNo }">					
						<div >
							<button class="btn btn-danger btn-lg">
								<br>${count}</button>
								${ptBean.photoNo}
							<input type="text"  id="next" value="${ptBean.photoNo}">
						</div>
					</c:if>
			</c:if>
		</c:forEach> 
			
			
			<div>
			<input type="button" class="btn btn-default" name="nextpreBtn" value="next" onclick="goNext()">
			 
			</div>
			
			<button class="btn btn-danger btn-lg">
			
				<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&ip=<%=ip %>" >
			←이전</a>			
			</button>
		
		
			<button class="btn btn-danger btn-lg">
				<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&ip=<%=ip %>" >
			다음→</a>			
			</button>
		
		<tr>
			<td class="line">
			<button class="btn btn-danger btn-lg">
				<i class="glyphicon glyphicon-heart white" style="font-size:24px;"></i>
					<br>${ptBean.faves}</button>
			이 사진을 ${ptBean.faves}명이 즐겨찾기를 하였습니다.
			</td>
		</tr>
		
		<tr>
			<td class="line"><img src="${pageContext.request.contextPath }/images/dot2.JPG">
			이 사진의 인기도는 ${ptBean.popular}입니다.
			</td>
		</tr>
		<tr>
			<td class="line"><img src="${pageContext.request.contextPath }/images/dot2.JPG">
			인기도 업데이트${ptBean.popDate}
			</td>
		</tr>
		
		
		<tr>
			<td class="line">
			<button class="btn btn-danger btn-lg">
				<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&ip=<%=ip %>" >
			←이전</a>			
			</button>
	
			</td>
		</tr>
		
		<tr>
			<td class="line">
			<button class="btn btn-danger btn-lg">
				<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&ip=<%=ip %>" >
			다음→</a>			
			</button>
			
			</td>
		</tr>
		
	</table>
	<!-- 사진넘기기  -->

	
	
	<br><br>
	<img src="../../images/dot6.JPG">
	<span style="font-size:12pt;font-weight:bold">요약</span>
	<br><br>
	<table width="600" border="0">
		<tr>
			<td>${ptBean.description}
				ptNo:${ptNo}
				userid:${userid }
			</td>
		</tr>
	</table>
</td>

</tr>

</table>
	
	<%-- <!-- commentsPage  -->
	<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
		<div id="comments">
			<%@ include file="../../photo/comments/comment.jsp" %>
		</div><!-- comments -->
	</c:if> --%>

</div><!-- container -->
</form>
</body>
   <c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
		<div id="comments">
			<%@ include file="../../photo/comments/comment.jsp" %>
		</div><!-- comments -->
	</c:if> 

<%@ include file="../../inc/mainBottom.jsp" %>