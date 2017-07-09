<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	
</head>


<body class="upPhotoBody">

<div style="width:100%; height:34px; background-color:#F5F5F5;">
	<span class="fa fa-comments-o fa-2x" style=" vertical-align: middle; padding:3px;">
	<span style="vertical-align: middle; font-weight:bold; font-size:12px;">첫 업로드입니다.</span></span>
	
</div>

<form name="ptUpfrm" id="ptUpfrm" method="post" action="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do" enctype="multipart/form-data">
	<input type="hidden" name="uploader" value="${userid }">
	<div class="container">
		<div style="display:table; margin: 0 auto; margin-top:150px;">
			<button type="button" class="btn btn-photoUp" id="upBtn" style="">업로드 하기</button>
		</div>
		<div style="display:table; margin: 0 auto; margin-top:20px;">
			<span>업로드할 이미지 파일을 선택하세요.</span>
		</div>
		<div style="display:none;">
			<input type="file" name="uploadFile" id="choosePt">
		</div>
	</div>
</form>


<script type="text/javascript">

	/* $("#upBtn").click(function (){
		$("#choosePt").trigger('click');
			$('input[type=file]').change(function(event) { 
			    $("#choosePt").closest("#ptUpfrm").submit();
			});
	});  */

	$("#upBtn").click(function () {
		$('input[type=file]').click();
	});
	
	$('input[type=file]').change(function() {
	    $("#choosePt").closest("#ptUpfrm").submit();
	});
		
 	/* $("#upBtn").click(function (){
		$("#choosePt").trigger('click');
		  $("#upBtn").blur(function (){
				if(!ptUpfrm.uploadFile.value){
					return;
				}else if(ptUpfrm.uploadFile.value) {
						$("#choosePt").closest("#ptUpfrm").submit();
				}; 
		   });
 });  */
	
	/*  $("#upBtn").click(function (){
		$("#choosePt").trigger('click');
		  $("#upBtn").blur(function (){
				if(!ptUpfrm.uploadFile.value){
					return;
				}else if(ptUpfrm.uploadFile.value) {
						$("#choosePt").closest("#ptUpfrm").submit();
				}; 
		   });
 });   */

</script>

</body>

<%@ include file="../../inc/mainBottom.jsp"%>