<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="text" id="usrFlg" value="${userFlag}">
<input type="hidden" id="emlFlg" value="${emailFlag}">
<%-- <link rel="stylesheet" type="text/css"	href="<c:url value='/css/mainstyle.css' />">
			
<input type="text" id="result" name="userFlag" value="${userFlag}"> --%>

<%-- <script>
	function send(){
		if(!frmId.userid.value){
		alert("아이디를 입력하세요");
		frmId.userid.focus();
		return;
		}
		frmId.submit();
	}
	function setUserid(){
		opener.frm1.userid.value=frmId.userid.value;//창이 열리고 작성한 유저id가 출력
		opener.frm1.chkId.value="Y";//중복확인을 한경우
		self.close();
	}
	
</script>

<h3>아이디 중복검사</h3>
<form name="frmId" method="post"
	action='<c:url value="/member/checkId.do"/>'>
	<input type="text" name="userid" value="${param.userid }">
	<input type="Button" value="아이디 확인" onclick="send()">
	
	
	<c:if test="${userFlag == false && param.userid != null && !empty param.userid}">
		  
		<input type="Button" value="사용하기" onclick="setUserid()">
		<br><br>
		사용가능한 아이디 입니다.[사용하기] 버튼을 눌러주세요.
	</c:if>
	
	<c:if test="${userFlag == true }">
		<br><br>
		이미 등록된 아이디 입니다.다른 아이디 입력하세요.
	</c:if>
	
</form> --%>