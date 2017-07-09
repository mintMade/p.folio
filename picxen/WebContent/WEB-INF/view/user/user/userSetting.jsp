<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../inc/mainTop.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">

</head>
<script type="text/javascript">
	function postEdit(){
		if(!frmEdit.fName.value){
			document.getElementById('fNError').innerHTML="성을 입력해 주세요.";
			document.getElementById('fNError').style.color="#708090";
				frmEdit.fName.focus();
				return;
		}if(!frmEdit.lName.value){
			document.getElementById('lNError').innerHTML="이름을 입력해 주세요";
			document.getElementById('lNError').style.color="#708090";
				frmEdit.lName.focus();
				return;
		}
		
		document.frmEdit.submit();
	}
	
</script>

<body style="background-color: #f5f5f5;">
<form name="frmEdit" method="post" enctype="multipart/form-data"><!-- !!멀티파트 -->
<div class="form-group" style="margin-top:30px;">
				
</div>
<div class="editContainer">
	<div class="editContainer" style="border-top:solid 1px #d3d3d3; padding-top:20px;">
		<div class="form-group" style="float:left; font-size:15px; font-weight:bold; color: #778899; ">
			${mbBean.fName} ${mbBean.lName} 님의 정보
		</div>
	</div>
		<div class="editBox" style="float:left; ">
			<div class="form-group" style="margin-top:30px; padding-left:20px;">					
					<div class="form-group">
					<div style="float:left; padding-right:20px;">
						<i class="fa fa-user fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px; ">성</label>
							<input type="text" id="fName" name="fName" class="form-control" style="width:300px; " value="${mbBean.fName}">
								<div id="fNError" style="float:left; font-size:12px;">
								</div>
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:20px;">
						<i class="fa fa-user fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px; ">이름</label>
							<input type="text" id="lName" name="lName"class="form-control" style="width:300px;" value="${mbBean.lName }">
								<div id="lNError" style="float:left; font-size:12px;">
								</div>
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:15px;">
						<i class="fa fa-envelope-o fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px; ">이메일</label>
							<input type="text" id="email" name="email" class="form-control" style="width:300px;" value="${mbBean.email }">
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:15px;">
						<i class="fa fa-pencil-square-o fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px; ">한줄 소개</label>
							<textarea id="abtMe" class="form-control" name="descript" rows="4" style="width:400px;" >${mbBean.descript }</textarea>
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:20px;">
						<i class="fa fa-gear fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px;">비밀번호 확인</label>
							<input type="password" class="form-control" name="pwdChk" style="width:300px;">
					</div>
					<div>
						<input type="hidden" name="pwd" value="${mbBean.pwd }">
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:20px;">
						<i class="fa fa-gear fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px;">새 비밀번호</label>
							<input type="password" class="form-control" name="newPwd" style="width:300px;">
					</div>
					
					<div class="form-group">
					<div style="float:left; padding-right:20px;">
						<i class="fa fa-gear fa-3x"></i>
					</div>
						<label class="control-label" style="font-size: 14px;">새 비밀번호 확인</label>
							<input type="password" class="form-control" name="newPwdChk" style="width:300px;">
					</div>
					
			</div>		
		</div>
	
	<div class="editBox" style=" padding-left:20px; padding-top:30px; float:right; width:420px;">
				<div class="form-group" style="">
					<div style="/* border:1px solid red; */">
						<img src="${pageContext.request.contextPath}/my_icon/${mbBean.myIcon}" style="width: 130px; height: 130px;">
						<div style="margin-top:10px;">
							<input type="file" id="myIcon" name="uploadIcon">
							<p class="help-block">최대 500Kb 이미지 파일 업로드</p>
							<input type="hidden" name="myIcon" value="${mbBean.myIcon }">								
						</div>
					</div>
			   </div>
			   <div class="form-group" style="margin-top:30px;">
			   		<div style="float:left; padding-right:10px;">
			   			<img src="${pageContext.request.contextPath }/my_icon/pixelP.jpg" style="width:40px; height:40px;">
			   		</div>
						<!-- <label class="control-label" style="font-size: 15px;"></label> -->
						<div style="font-family:impact; font-size:30px; float:left; ">
							57${mbBean.mileage}
							<span class="control-label" style="font-size: 15px;">Pixels</span>
							<input type="hidden" name="mileage" value="${mbBean.mileage }">
						</div>
							<div style="float:left; padding-left:20px; margin-top:15px;">
								<button type="button" class="btn btn-warning btn-xs">
								<i class="fa fa-eyedropper"></i>픽셀 충전</button>
							</div>

			   </div>
	</div>

</div>	<!-- end -->
					<div class="editContainer" style="border-top:solid 1px #d3d3d3; padding-top:15px; margin-top:20px;">
						<div style="float:right;">
						<button type="button" class="btn btn-success" id="edit" onclick="postEdit()" style="width:80px;">확인</button>
						</div>
					</div>

</form>
</body>
</html>

<%@ include file="../../inc/mainBottom.jsp" %>