<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/logOutHomeTop.jsp" %>
<head>
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
</head>
<body style="overflow-x:hidden; overflow-y:auto;">

<script type="text/javascript">
	
</script>
<!-- 메인포토이미지 -->
<table class="mainBG" width="100%" height="730">
	<tr>
		<td  style="color:white;  text-align:center;">
			<h2>index test</h2>
			<h2>기억들이 공유되는곳</h2>
			<h4>다양한 사진을 올려보세요.</h4><br>
				<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
					<a href="${pageContext.request.contextPath}/member/login.do" class="btn btn-start btn-lg" style="color:white; font-weight: bold;">시작하기</a>
				</c:if>
				<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
				</c:if>
			
		</td>
	</tr>
</table>

<!-- 설명 -->
<table style="margin-left: auto; margin-right: auto; height:200px; ">
	<tr>
		<td >
			<img src="${pageContext.request.contextPath }/pt_images/camera.png" class="mainInfoBG1">
		</td>
		<td width="300">
				<h3 style="font-size:20px; font-weight:bold; font-family:돋움;">포토그래퍼</h3>
				<p style="font-size:15px; font-family:돋움;">멋진 사진을 많은 사람과 공유합니다.</p><br>
			<a href="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do" class="btn btn-start btn-md" style="color:white;">사진 올리기</a><br>
		</td>
		<td class="line3" width="80" align="right">
			<img src="${pageContext.request.contextPath }/pt_images/search.png" class="mainInfoBG2">
		</td>
		<td >
				<h3 style="font-size:20px; font-weight:bold; font-family:돋움;">기억하고 싶은 순간</h3>
				<p style="font-size:15px; font-family:돋움;">소중한 순간을 소셜친구와 공유합니다.</p><br>
			<a href="${pageContext.request.contextPath}/photo/photo/popular.do" class="btn btn-start btn-md" style="color:white;">구경하기</a><br>
		</td>
	</tr>
</table>

<table style="border:1px solid silver;" class="box6" width="100%">
</table>

<table width="100%"> 
	<tr>
		<td colspan="2">
		 <%@ include file="inc/photoCatalog.jsp"%>
		</td>
	</tr>
</table>


</body>

<%@ include file="inc/mainBottom.jsp" %>