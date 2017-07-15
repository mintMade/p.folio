<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/logOutHomeTop.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/RSA/rsa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/RSA/jsbn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/RSA/prng4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/RSA/rng.js"></script>

<script type="text/javascript">
	function send(){
		if(!$('#usr').val()){
			alert("아이디를 입력하세요");
			frmLogin.userid.focus();
			return;
		}
		if(!$('#usrPwd').val()){
			alert("비밀번호를 입력하세요");
			frmLogin.usrPwd.focus();
			return;
		}
	
        // rsa 암호화
        var rsa = new RSAKey();
        rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
        
        $("#USER_ID").val(rsa.encrypt($('#usr').val()));
        $("#USER_PW").val(rsa.encrypt($('#usrPwd').val()));
        
        $('#usr').val("");
        $('#usrPwd').val("");
        
		frmLogin.submit();
		
		return true;
	}
	
	function enterProc(){
		if(event.keyCode==13){//enter 키를 누르면
			return send();
		}
	}
</script>

<body class="lginBG" style="">
	
<div class="loginBox" style="">
<form  class="form-horizontal" name="frmLogin" method="post" action="${pageContext.request.contextPath }/member/login.do" onsubmit="return send()">
	<input type="hidden" id="RSAModulus" value="${RSAModulus}">
	<input type="hidden" id="RSAExponent" value="${RSAExponent}">
	
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
				<input type="password" class="form-control" id="usrPwd" name="usrPwd" onkeydown="enterProc()" placeholder="비밀번호">
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

	<input type="hidden" id="USER_ID" name="USER_ID" >
	<input type="hidden" id="USER_PW" name="USER_PW">
</form>
</div>

</body>
<%@ include file="../inc/mainBottom.jsp" %>