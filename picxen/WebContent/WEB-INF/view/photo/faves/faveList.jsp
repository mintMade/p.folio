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
				<c:set var="cnt"  value="0" />
					<c:forEach var="i" begin="1" end="${likeSize }" >
					<c:set var="likeBean" value="${likeList[i-1]}" />
					<c:set var="cnt" value="${ cnt+i}"/>
						<div  style="margin: 4px; float:left; ">
							<img src="${pageContext.request.contextPath }/my_icon/${likeBean.myIcon}" class="img-circle" style="width:44px; height:44px;">
							<input type="hidden" id="lUid_${cnt}" name="luId" value="${likeBean.userid }">
							<input type="hidden" id="lUName_${cnt}" name="lUName" value="${likeBean.fName } ${likeBean.lName}">
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
			<c:set var="cnt"  value="0" />
				<c:forEach var="i" begin="1" end="${faveSize }" >
				<c:set var="fveBean" value="${faveList[i-1]}" />
				<c:set var="cnt" value="${ cnt+i}"/>
					<div style="margin: 4px; float:left;">
						<img src="${pageContext.request.contextPath }/my_icon/${fveBean.myIcon}" class="img-circle" style="width:44px; height:44px;">
						<input type="hidden" id="fUid_${cnt}" name="fuId" value="${fveBean.userid }">
						<input type="hidden" id="fUName_${cnt}" name="fUName" value="${fveBean.fName } ${fveBean.lName}">
					</div>
					
 					<c:if test="${userid == fveBean.userid}">
						<input type="hidden" id="uid" value="${fveBean.userid}">
					</c:if> 
				</c:forEach>
	</div>

		
</form>

</body>
	<script type="text/javascript">
	
	$(document).ready( function(){ //faveList, likeList > 0
		
		var fSize = ${faveSize}; //fave리스트
		var uid = $('#uid').val();//color option for heart.
		
 		if(fSize != 0){
			var fName = $('#fUName_1').val();
			document.getElementById("LFName").innerHTML =fName;
			document.getElementById("LFName1").innerHTML ="님 외에";	
			document.getElementById("LFName2").innerHTML =fSize-1+"명";
			if(uid.length > 0){
				$("#hrtLavel").css("color", "#CC0000");
			};
		}else if(fSize == 0 && $('#linId').val() == $('#uldr').val()){ //업로더 -> 0명, 업로더 아닐경우 텍스트
			document.getElementById("LFName").innerHTML ="0 명";
		}else if(fSize == 0 && $('#linId').val() != $('#uldr').val()){
			document.getElementById("LFName").innerHTML ="가장 먼저 하트를 주세요!";
		};
	});
	
	var lSize = ${likeSize}; //like리스트
	var luid = $('#likeuid').val(); //color option for thumbs.
	
	if(lSize != 0){
		var lName = $('#lUName_1').val();
		document.getElementById("LLName").innerHTML =lName;
		document.getElementById("LLName1").innerHTML ="님 외에";	
		document.getElementById("LLName2").innerHTML =lSize-1+"명";
		if(luid.length > 0){
			$("#thmbLavel").css("color", "#00A5FF");
		};
	}else if(lSize == 0 && $('#linId').val() == $('#uldr').val()){
		document.getElementById("LLName").innerHTML ="0 명";
	}else if(lSize == 0 && $('#linId').val() != $('#uldr').val()){
		document.getElementById("LLName").innerHTML ="가장 먼저 좋아요를 주세요!";
	};
	
	</script>
</html>

