<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<title></title>
</head>

<body>

<form id="fvForm" action="${pageContext.request.contextPath}/photo/faves/faveList.do" method="get">

<!-- like리스트 -->	
	<div class="container" style="/* border:solid 1px blue; */">	
		<div class="likeMs" style="margin:10px; margin-top:20px; ">
			<i class="fa fa-thumbs-up fa-lg" id="thmbLavel" style="color:#999999;"></i>
			<!--ListLikeName  -->
			<span id="LLName" style="font-size:12px; margin-left:10px; font-weight:bold;">
			</span>	
			<span id="LLName1" style="font-size:12px;"></span>	
			<span id="LLName2" style="font-size:12px; color:#00A5FF; "></span>		
		</div>	
				<input type="hidden" name="likeSize" value="${likeSize}">
				<c:set var="cnt"  value="0" />
					<c:forEach var="g" begin="1" end="${likeSize }" >
					<c:set var="likeBean" value="${likeList[g-1]}" />
					<c:set var="cnt" value="${ cnt+1}"/>			
							<div class="like-fave-circle" style="margin: 4px; float:left; ">
								<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${likeBean.userid }"
									id="lf_${cnt }" data-toggle="like-fave-tooltip" data-placement="top" title="${likeBean.lName}">
										<img src="${pageContext.request.contextPath }/my_icon/${likeBean.myIcon}" 
										  class="img-circle" style="">
											<input type="hidden" id="lUid_${cnt}" name="luId" value="${likeBean.userid }">
											<input type="hidden" id="lUName_${cnt}" name="lUName" value="${likeBean.fName } ${likeBean.lName}">
								</a>
							</div>
						
						
	 					<c:if test="${userid == likeBean.userid}">
							<input type="hidden" id="likeuid" value="${likeBean.userid}">
						</c:if> 
					</c:forEach>
	</div>


	<div class="container">
		<input type="hidden" id="linId" value="${userid }">
		<%String uldr=(String)request.getAttribute("uploader"); %>
		<input type="hidden" id="uldr" value="<%=uldr%>">

	<div class="faveMs" style="margin:10px; margin-top:20px; ">
		<i class="fa fa-heart fa-lg" id="hrtLavel" style="color:#999999;"></i>
			<span id="LFName" style="font-size:12px; margin-left:10px; font-weight:bold;">
			</span>	
			<span id="LFName1" style="font-size:12px;"></span>	
			<span id="LFName2" style="font-size:12px; color:#00A5FF; "></span>		
	</div>	
<!-- fava리스트 -->	
			<input type="hidden" name="faveSize" value="${faveSize}">
			<c:set var="cnt"  value="0" />
				<c:forEach var="i" begin="1" end="${faveSize }" >
				<c:set var="fveBean" value="${faveList[i-1]}" />
				<c:set var="cnt" value="${ cnt+1}"/>
					<div class="like-fave-circle" style="margin: 4px; float:left;">
						<a href="${pageContext.request.contextPath}/user/user/userMain.do?userid=${fveBean.userid}"
							id="ff_${cnt }" data-toggle="like-fave-tooltip" data-placement="top" title="${fveBean.lName} : ">
							<img src="${pageContext.request.contextPath }/my_icon/${fveBean.myIcon}" class="img-circle" >
							<input type="hidden" id="fUid_${cnt}" name="fuId" value="${fveBean.userid }">
							<input type="hidden" id="fUName_${cnt}" name="fUName" value="${fveBean.fName } ${fveBean.lName}">
						</a>
					</div>
					
 					<c:if test="${userid == fveBean.userid}"><!-- 로그인유저 == 좋아요유저 -->
						<input type="hidden" id="uid" value="${fveBean.userid}">
					</c:if> 
				</c:forEach>
	</div>
		
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/picxen/faveList.js"></script>
</html>

