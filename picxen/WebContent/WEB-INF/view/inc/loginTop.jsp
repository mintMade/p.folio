<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container" style="padding:10px 0 10px 0; /* min-width:420px; */"> 
	<div style="display:flex; align-items:center; float:left;">
		<div class="logicon" style="">
			<a href="${pageContext.request.contextPath }/index.do" style="display:flex; text-decoration: none;">
				<img src="${pageContext.request.contextPath }/images/picxenLogo.png" id="plogo" style="">
						<div class="logoIdle" style="margin-left:5px; margin-top:5px; float:right;">P.folio</div>
			</a>
		</div>
		<div style="margin:0 20px 0 10px;">
			<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath}/photo/photoList.do?ftName=pop">
				<i class="fa fa-picture-o fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 구경하기</span></a>
			<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do">
				<i class="fa fa-cloud-upload fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 사진올리기</span></a>
		</div>

			
<!-- 상단 메뉴 -->
			
<!-- 이미지검색 -->
	<div id="menuDsp" class="input-group" style=" float:left;">
		<form name="frmSearchPt" method="post" action="${pageContext.request.contextPath }/photo/photo/ptSearchResult.do">
			<div class="input-group" style="width:300px; /* top:8px; */ /* position:raletive; */">
				<input type="text" name="kword" class="form-control"  placeholder="검색어를 입력하세요"/>
					<span class="input-group-btn">
						<button type="submit" class="btn btn-primary">검색</button>
					</span>
			</div>
		</form>
	</div>
	</div>
<!-- 검색끝 -->
			<div  style="float:right; margin-top:8px;">
				<c:if test="${sessionScope.userid ==null || empty sessionScope.userid}" >
					<a class="btn btn-menuBtn btn-xs" href="${pageContext.request.contextPath }/member/agreement.do" style="margin-left:10px;">
						<i class="fa fa-heartbeat fa-fw" aria-hidden="true"></i><span class="MenuBtnTxt"> 회원가입</span></a>
				</c:if>
				<!-- 로그인 된 경우  -->
				<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
<!-- 유저홈 -->		<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${userid}" >${userid}</a>
					<a href="${pageContext.request.contextPath }/member/logout.do">로그아웃</a> 
				</c:if>
				
			</div>

		
</div>
</div>
</body>

<script type="text/javascript">

</script>

</html>