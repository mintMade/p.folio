<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/mainTop.jsp" %>

<script type="text/javascript">
	//웹 페이지가 로드될때 특정 컨트롤에 포커스주기
	window.onload = function(){
		frm1.name.focus();
	}
function send(){
	if(!frm1.fName.value){
		alert("성을 입력하세요");
		frm1.fName.focus();
		return;
	}
	if(!frm1.lName.value){
		alert("이름을 입력하세요");
		frm1.lName.focus();
		return;
	}
	if(!frm1.userid.value){
		alert("아이디를 입력하세요");
		frm1.userid.focus();
		return;
	}
	if(!frm1.pwd.value){
		alert("비밀번호를 입력하세요");
		frm1.pwd.focus();
		return;
	}
	if(frm1.pwd.value != frm1.pwd2.value){
		alert("비밀번호가 일치하지 않습니다");
		frm1.pwd2.focus();
		return;
	}
	//중복확인 버튼을 누르지 않은경우 아이디 중복확인을 하지 않은 경우
	if(frm1.chkId.value != "Y"){
		alert("아이디 중복 확인을 하세요");
		frm1.btnChkId.focus();
		return;
		/* alert("아이디 중복 확인을 하세요");
		frm1.btnChkId.focus();
		return; */
	}
	frm1.submit();
}

	function useridCheck(){
		//아이디 중복 확인 창띄우기
		var userid=frm1.userid.value;
		window.open(
"${pageContext.request.contextPath}/member/checkId.do?userid="
		+userid, "idWin", "width=400, height=200, scrillvars=yes, resizeble=yes");
	}/////////////체크아이디 펑선작성완료 , 체크아이디작성예정
	
</script>
<h3><center>회원가입</center></h3>
<form name="frm1" method="post" action='<c:url value="/member/register.do"/>'>
<table cellpadding="5" cellspacing="0" width="600" align="center" class="box2">
	<tr>
		<th width="130" align="right">성</th>
		<td><input type="text" name="fName" style="ime-mode:active">
		<!-- ime-mode 기본 한글 선택 옵션 -->
		</td>
	</tr>
	<tr>
		<th width="130" align="right">이름</th>
		<td><input type="text" name="lName" style="ime-mode:active">
		</td>
	</tr>
	<tr>
		<th align="right">회원ID</th>
		<td><input type="text" name="userid" style="ime-mode:inactive">&nbsp;
			<input type="Button" value="중복확인" name="btnChkid" onclick="useridCheck()">
		</td>
	</tr>
	<tr>
		<th align="right">비밀번호</th>
		<td><input type="Password" name="pwd">
		</td>
	</tr>
	<tr>
		<th align="right">비밀번호 확인</th>
		<td><input type="Password" name="pwd2">
		</td>
	</tr>
	<tr>
		<th align="right">이메일 주소</th>
		<td><input type="text" name="email">
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="35">
			<input type="Button" value="회원가입" onclick="send()">
		</td>
	</tr>
</table>
	<input type="text" name="chkId">
</form>

<%@ include file="../inc/mainBottom.jsp" %>