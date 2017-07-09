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
				<h3>${ptNo}</h3>
		</tr>
	
			
			<div>
				<c:set var="ptBean" value="${clist[totalCRecord-totalCRecord]}"/>
				<c:if test="${ptBean.photoNo==ptNo}">
					<input type="button" class="btn btn-default disabled" value="pre" onclick="goPre()">
				</c:if>
				<c:if test="${ptBean.photoNo!=ptNo}">
				<input type="button" class="btn btn-default" value="pre" onclick="goPre()">
				</c:if>
			</div>
			<div>
				<input type="button" class="btn btn-default" value="next" onclick="goNext()">
			 
			</div>
			
		
		
	</table>
	<!-- 사진넘기기  -->

</td>

</tr>



</form>


 <!--                                           다음버튼  -->
 <form name="frmPre" method="get">
		  <c:forEach var="i" begin="0" end="${totalCRecord -1}">
			<c:if test="${ptNo >=1 }"> 
				<c:set var="ptBean" value="${clist[i]}" /> 
					<c:if test="${ptBean.photoNo==ptNo }">
						<c:set var="ptBean" value="${clist[i-1]}"/>
							<h3><input type="text" id="pre" name="ptNo" value="${ptBean.photoNo}"></h3>
					</c:if>
			 </c:if>
		</c:forEach>  
		<input type="text" name="userid" value="${userid }">
		<input type="text" name="ip" value="<%=ip %>">
		<input type="text" name="sort" value="${sort}">
 </form>
 
  <form name="frmNext" method="get">
		 <c:forEach var="i" begin="0" end="${totalCRecord -1}">
			<c:if test="${ptNo >=1 }">
				<c:set var="ptBean" value="${clist[i]}" /> 
					<c:if test="${ptBean.photoNo==ptNo }">
						<c:set var="ptBean" value="${clist[i+1]}"/>
							<h3><input type="text" id="next" name="ptNo" value="${ptBean.photoNo}"></h3>
					</c:if>
			</c:if>
		</c:forEach>  
		<input type="text" name="userid" value="${userid }">
		<input type="text" name="ip" value="<%=ip %>">
		<input type="text" name="sort" value="${sort}">
 </form>
 <!--                                           다음버튼  -->
</table>
</body>
   <c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
		<div id="comments">
			<%@ include file="../../photo/comments/comment.jsp" %>
		</div><!-- comments -->
	</c:if> 

<%@ include file="../../inc/mainBottom.jsp" %>