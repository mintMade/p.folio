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

<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css"> 
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">  
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>

<body leftmargin="0" topmargin="0">
<div id="container" >
	<table  style="border-top:1px solid #E3E3E3; cellpadding:0; cellspacing:0;" class="box5" >
		<!-- 상단 메뉴 시작 -->
		<tr>
			<td colspan="2"  width="100" >
				<a href="<c:url value='/index.do'/>">
					<img src="<c:url value='${pageContext.request.contextPath }/images/picxenLogo.png'/>"border="0">
				</a>
			</td>
			
<!-- 상단 메뉴 -->
		<td width="200" style="  ">
		</td>
			
<!-- 사진검색 -->
		
			<form name="frmSearchPt" method="post" action='<c:url value="/photo/photo/ptSearchResult.do" />'>
				<td class="input-group" style="width:500px; top:8px; position:raletive;">
					<input type="text" class="form-control"  placeholder="검색어를 입력하세요"/>
					<span class="input-group-btn">
					<button type="submit" class="btn btn-primary">검색</button>
					</span>
				</td>
			</form>

<!-- 검색끝 -->
			<td style="width:100%; background-color: #000000; min-width:1000px; word-spacing:30px;" >
				<c:if test="${sessionScope.userid ==null ||
					empty sessionScope.userid}" >
					<%-- <a href="<c:url value='/member/login.do' />" style="font-size:10pt;color:#f5f5f5;">로그인</a> --%> 
					<a href="<c:url value='/member/agreement.do' />" style="font-size:10pt;color:#f5f5f5;">회원가입</a> 
				</c:if>
				<!-- 로그인 된 경우  -->
				<c:if test="${sessionScope.userid != null && 
					!empty sessionScope.userid }">
<!-- 유저홈 --><a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${userid}" style="font-size:10pt;color:#f5f5f5;">${userid}</a>
				<a href="<c:url value='/member/logout.do' />" style="font-size:10pt;color:#f5f5f5;">로그아웃</a> 
				</c:if>
				<a href="<c:url value='/photo/ptUpload/ptUp.do'/>" style="font-size:10pt;color:#f5f5f5;">사진올리기</a> 
				<a href="<c:url value='/photo/photo/popular.do'/>" style="font-size:10pt;color:#f5f5f5;">사진구경하기</a> 
				
			</td>
		</tr>
		
	</table>
</div>
</body>
</html>