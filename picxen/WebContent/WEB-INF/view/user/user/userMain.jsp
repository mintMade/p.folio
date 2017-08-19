<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
<style type="text/css">
.userMainBG {
    background: url("${pageContext.request.contextPath}/pt_images/${mbBean.bg}") no-repeat center center;
    background-size: cover;
}
</style>
</head>
	
<!-- 페이징 처리 form -->
<form name="frmPage" method="post">
	<input type="hidden" name="eventName" value="${param.eventName }">
	<input type="hidden" name="currentPage">
</form>
<!-- 페이징 처리 form끝 -->

<form name="frmList" method="post" >
<c:if test="${mbBean.bg == 'defaultBG.jpg'}">
	<div class="userMainBG" style="height:100px;">
	</div>
</c:if>

<c:if test="${mbBean.bg != 'defaultBG.jpg'}">
	<div class="userMainBG" style="">
	</div>
</c:if>

<div style="display:table; margin: 0 auto;">
	<div style="margin-top:-55px;">
			<img src="${pageContext.request.contextPath}/my_icon/${mbBean.myIcon}" class="img-circle" 
				style="border:2px solid white; width:110px; height: 110px; display:block; margin:auto;">
	</div>
</div>

<h2 align="center">${mbBean.fName} ${mbBean.lName}</h2>


<div style="display:table; margin: 0 auto; font-size:14px; text-align:center; max-width:500px;">
		<c:if test="${fn:length(mbBean.descript)>=90}">
			${fn:substring(mbBean.descript, 0, 70)}...
		</c:if>
		<c:if test="${fn:length(mbBean.descript)<70 }">
			${mbBean.descript}
		</c:if>
</div>
<div style="display:table; margin: 0 auto; font-size:13px; text-align:center; max-width:500px;">
	<div style="margin:10px;">
		<button class="btn btn-default btn-sm disabled" style="border: 0 none;">
			<fmt:formatNumber value="${ltotal}" type="Number" pattern="###,###,###" /> 모든 좋아요
		</button>
		<button class="btn btn-default btn-sm disabled" style="border: 0 none;">
			<fmt:formatNumber value="${vtotal}" type="Number" pattern="###,###,###" /> 사진을 본 모든 사람
		</button>
		<button class="btn btn-default btn-sm" style="border: 0 none; color:#808080;">
			<fmt:formatNumber value="${ftotal}" type="Number" pattern="###,###,###" /> 빌려간 사진
		</button>
	</div>
</div>
<div style="width:100%; border-top:1px solid #f5f5f5; border-bottom:1px solid #f5f5f5; min-height:35px;">
	<div style="display:table; margin: 0 auto; text-align:center; max-width:500px;">
		
		<c:set var="mcnt" value="0" />
		<c:set var="btn" value='<%=new String[]{"내사진", "갤러리", "소개"} %>'/>
		<c:forEach var="c" begin="1" end="3" >
			<c:set var="cBtn" value="${btn[c-1]}" />
			<c:set var="mcnt" value="${c}"/>
			
				<button class="btn btn-btmLine <c:if test="${mainCg == mcnt}">active</c:if>" id="${mcnt }" name="mainCg" value="${mcnt}" onclick="javascript:mcg()">
				${cBtn}</button>
		</c:forEach>
				
	</div>
</div>

<c:if test="${mainCg == 1}">
	<c:if test="${totalRecord==0}">
		<div style="font-Size:30px; color:#999999; text-align:center; margin:30px;">사진을 등록 해주세요.</div>
	</c:if> 
	<div style="width:100%;">
		<%@ include file="uMptList.jsp" %>
	</div>
</c:if>

<c:if test="${mainCg == 2}">
	<c:if test="${myAlbmList==0}">
		<div style="font-Size:30px; color:#999999; text-align:center; margin:30px;">앨범을 등록 해주세요.</div>
	</c:if> 
	<div style="width:100%;">
		<%@ include file="uAlbmList.jsp" %>
	</div>
</c:if>

</form>

<script type="text/javascript">
	
	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="${pageContext.request.contextPath}/user/user/userMain.do?userid=${mbBean.userid}";
		frmPage.submit();
	}

</script>

<%@ include file="../../inc/mainBottom.jsp" %>