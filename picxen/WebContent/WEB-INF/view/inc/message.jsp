<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//forward 시킨 페이지와 request를 공유하므로 setAttribute로 넣은 값을
	//getAttribute로 읽어올 수 있다
	//String msg = (String)request.getAttribute("msg");
	//String url = (String)request.getAttribute("url");
	
	// if(msg!=null && !msg.isEmpty()){
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">  
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<c:if test="${msg != null && !empty msg }">

	<div id="confirm" class="modal hide fade">
		
	</div>

	<script type="text/javascript">
		alert("${msg}");
		location.href="${url}";
	</script>
</c:if>

<c:if test="${msg==null || empty msg }">
	<script type="text/javascript">
		location.href="${url}";
	</script>
</c:if>
</html>