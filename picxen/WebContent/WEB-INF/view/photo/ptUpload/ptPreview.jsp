<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
</head>
<script type="text/javascript">
	function ptUpBtn(){
		if(!upForm.photoTitle){
			alert("제목을 입력하세요");
			upForm.photoTitle.focus();
			return;
		}
		if(!upForm.imageURL){
			alert("예상치 못한 문제로 이미지 업로드에 실패했습니다. 이미지를 다시 올려주세요.");
			return;
		}
		document.upForm.submit();
	} 
	
</script>

<body class="upPhotoViewBody">

<form class="upPhotoViewBody" name="upForm" method="post" action='<c:url value="/photo/ptUpload/ptPreview.do"/>' enctype="multipart/form-data">

<div style="position: absolute; margin-right:420px; ">
	<div style="width:100%; margin: 20px; ">	
		<div class="col-md-3 col-sm-6 col-md-4" style="">
			<div style="">
				<img src="${pageContext.request.contextPath}/pt_images/${imageUrl}" class="upView" >
			</div>
			<div style="line-height:40px; font-weight:bold;">
				${imageUrl}
				<input type="hidden" name="imageURL" value="${imageUrl}">
			</div>
		</div>
		<div class="col-md-3 col-sm-6 col-md-4" style="">
			<div class="" style="">
				<a href="#" >
					<img src="${pageContext.request.contextPath}/pt_images/addphoto.jpg" class="addPhoto">
				</a>
			</div>
		</div>
	</div>
</div>


	<div class="ptDetailBox" style="position: relative; float:right; min-height:100%;">
		<div class="upBtn">
			<button type="button" class="btn btn-success"  style="font-weight:bold; width:400px; height:50px; margin-top:10px;" onclick="ptUpBtn()">
				사진 올리기
			</button>
		</div>
			<div style="border-top:solid 1px #DEDEDE; margin-top:15px; color:#9C9C9C;">
				<div style="margin-left:25px; margin-right:25px;">
					<h4 style="font-weight:bold;">정보 입력</h4>
						<div class="editList" style="">
							<span class="editList" >키워드 입력</span><br><br>
							<textarea name="tag" class="form-control" rows="3"></textarea>
						</div>
						
						<div class="editList" style="">
							<span class="editList" >제목</span><br><br>
							<input type="text" name="photoTitle" class="form-control" placeholder="제목 입력">
						</div>
						<div class="editList" style="">
							<span class="editList" >카테고리</span><br>
						</div>
						<div class="btn-group" style="margin-top:15px;">
							<select class="form-control" name="categoryNo" >
								<c:forEach var="cgBean" items="${cgList }">
									<option value="${cgBean.categoryNo}">${cgBean.categoryName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="editList">
							<span class="editList">설명</span>
						</div>
						<div style="margin-top:15px;">
							<textarea class="form-control" name="description" rows="3"></textarea>
						</div>
						<div class="editList">
							<span class="editList">공개여부</span>					
						</div>
						<div class="btn-group" style="margin-top:15px;">
							<select class="form-control" name="publicSet">
								<option value="0">공개</option>
								<option value="1">비공개</option>
							</select>
						</div>
				</div>
			</div>
	</div>
	<div>
		<input type="hidden" name="uploader" value="${uploader }">
	</div>


</form>
</body>

</html>

<%@ include file="../../inc/mainBottom.jsp" %>