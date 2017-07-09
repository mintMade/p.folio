<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/logOutHomeTop.jsp" %>

<script type="text/javascript">
	function send(){
		if(!frmLogin.userid.value){
			alert("아이디를 입력하세요");
			frmLogin.userid.focus();
			return;
		}
		if(!frmLogin.pwd.value){
			alert("비밀번호를 입력하세요");
			frmLogin.pwd.focus();
			return;
		}
		frmLogin.submit();
	}
	
	function enterProc(){
		if(event.keyCode==13){//enter 키를 누르면
			return send();
		}
	}
</script>

<body class="lginBG" style="">
	
<div class="loginBox" style="">
 <form  class="form-horizontal" name="frmLogin" method="post" action='<c:url value="/member/login.do"/>'>

<div class="container" style="padding:0 60px 30px 30px;">
		<div style="padding:0 0 20px 0;">
			<h3>로그인</h3>
		</div>

	<div class="form-group">
		<div class="col-xs-10">
		<input type="text" class="form-control" id="usr" name="userid" value="${cookie.ck_userid.value}" placeholder="아이디">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-xs-10">
		<input type="password" class="form-control" id="pwd" name="pwd" onkeydown="enterProc()" placeholder="비밀번호">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-xs-10">
		<label>
			<input type="checkbox" name="idsave" 
				<c:if test="${cookie.ck_userid != null && !empty cookie.ck_userid}">checked</c:if>>  아이디 저장
		</label>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-xs-10">
		<span><input type="button" class="btn btn-logi " value="로그인" onclick="send()"></span>
		</div>
	</div>
		
</div>

</form>
</div>

</body>
<%@ include file="../inc/mainBottom.jsp" %>