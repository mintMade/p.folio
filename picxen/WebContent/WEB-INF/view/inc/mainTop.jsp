<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<% //로그인한 경우에는 로그아웃, 호원정보변경 메뉴가 보이도록 하고
   //로그인 안된 경우에는 로그인, 회원가입 메뉴가 보이게 하자
   String tUserid=(String)session.getAttribute("userid");
   boolean tIsLogin=false;
   if(tUserid!=null && !tUserid.isEmpty()){//세션에 userid값이 있으면
	  tIsLogin=true;//로그인된 경우
   }
%>

<html lang="ko">
<head>
<title>Picxen</title>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">  
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>

<body leftmargin="0" topmargin="0">
<div style="background-color:#000000;">
<div class="container" style="padding:10px 0 10px 0; min-width:300px;"> 
	<div style="display:flex; align-items:center; float:left;">
	
	<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
<!-- 로그인하면 홈으로 이동 -->	
		<div class="logicon" style="">
			<a href="${pageContext.request.contextPath }/index.do" style="display:flex; text-decoration: none;">
				<img src="${pageContext.request.contextPath }/images/picxenLogo.png" id="plogo" style="">
						<div class="logoIdle" style="margin-left:5px; margin-top:5px; float:right;">P.folio</div>
			</a>
		</div>
	</c:if>
	
	<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
<!-- 메세지 -->	
		<div class="logicon" style="">
			<a href="${pageContext.request.contextPath }/index.do" style="display:flex; text-decoration: none;">
				<img src="${pageContext.request.contextPath }/images/picxenLogo.png" id="plogo" style="">
						<div class="logoIdle" style="margin-left:5px; margin-top:5px; float:right; ">P.folio</div>
			</a>
		</div>
	</c:if>
		
		<div style="margin:0 5px 0 5px;">
			<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath}/photo/photoList.do?sort=pop">
				<i class="fa fa-picture-o fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 구경하기</span></a>
			<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do">
				<i class="fa fa-cloud-upload fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 사진올리기</span></a>
		</div>
			
<!-- 상단 메뉴 -->
			
<!-- 이미지검색 -->
	<div id="menuDsp" class="input-group" style=" /* float:left; */">
		<form name="frmSearchPt" method="post" action="${pageContext.request.contextPath }/photo/photo/ptSearchResult.do">
			<div class="input-group" style="width:300px;">
				<input type="text" name="kword" value="${param.kword}" class="form-control"  placeholder="검색어를 입력하세요"/>
					<span class="input-group-btn">
						<button type="submit" class="btn btn-primary">검색</button>
					</span>
			</div>
		</form>
	</div>
	</div>
<!-- 검색끝 -->

		<!-- 로그인 된 경우 아이디 드롭다운버튼 -->
				<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
				<div class="btn-group" style="margin:4px 0 0 0; float:right;">
					<button type="button" class="btn btn-navBtn" data-toggle="dropdown">
						<c:if test="${fn:length(userid)>=7}">
							<img src="${pageContext.request.contextPath}/my_icon/${userIcon}" style=" width:25px; height:25px; margin-right:5px;">
							<span id="menufncId">${fn:substring(userid,0,6)}...</span>
						</c:if>
						<c:if test="${fn:length(userid)<=6 }">
							<img src="${pageContext.request.contextPath}/my_icon/${userIcon}" style=" width:25px; height:25px; margin-right:5px;">
							<span id="menuId">${userid}</span>
						</c:if>
					</button>
						<ul class="dropdown-menu" style="/* width:130px; */">
							<li>
								<a href="${pageContext.request.contextPath}/user/user/userMain.do?userid=${userid}" style="color:#696969; font-weight:bold;">홈이동</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/user/userSetting.do?userid=${userid}" style="color:#696969; font-weight:bold;">내정보</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/user/setPhoto.do?userid=${userid}" style="color:#696969; font-weight:bold;">이미지관리</a>
							</li>
							<li >
								<a href="${pageContext.request.contextPath}/member/logout.do" style="font-size:10pt; color:#696969; font-weight:bold;">로그아웃</a>							
							</li>							
						</ul>
				</div>
				</c:if>
				

				<c:if test="${sessionScope.userid ==null || empty sessionScope.userid}" >
					<div  style="float:right; margin-top:11px;">
						<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath }/member/login.do" style="font-weight:bold;">
							<i class="fa fa-sign-in fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 로그인</span></a>
						<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath }/member/agreement.do" style="margin-left:10px;">
							<i class="fa fa-heartbeat fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 회원가입</span></a>
					</div>
				</c:if>				
			

		
</div>
</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function(){
		var dSize = $(window).width();
		if(dSize >= 752){
			$('.btn-group').attr('class','btn-group');
		}
		if(dSize < 752){
			$('.btn-group').attr('class','btn-group pull-right');
		}
		
	}).resize();
});
</script>

</html>