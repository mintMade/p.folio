<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/logOutHomeTop.jsp" %>
<head>
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
</head>
<body style="overflow-x:hidden; overflow-y:auto;">
<!-- 메인포토이미지 -->

<div class="mainBG" style="width:100%; height:600px; display: table; ">
	<div  style="color:white;  text-align:center; display: table-cell; vertical-align: middle;">
		<h2>기억들이 공유되는곳</h2>
		<h4>다양한 사진을 올려보세요.</h4><br>
		<c:if test="${sessionScope.userid == null && empty sessionScope.userid }">
			<a href="${pageContext.request.contextPath}/member/login.do" id="main-str" class="btn btn-default-start" style="">시작하기</a>
		</c:if>
		<c:if test="${sessionScope.userid != null && !empty sessionScope.userid }">
		</c:if>
	</div>
</div>

<!-- 설명 -->

<div class="container" style="display: table; margin: 0 auto; ">
	<div class="col-xs-6 align-center" style="padding-right: 35px; width:50%; /* border: 1px solid blue; */">
	 	<div class="col-sm-2" style="/* float:left; */text-align:center; margin:20px 10px -10px 10px; /* border: 1px solid red; */">
			<img src="${pageContext.request.contextPath }/pt_images/camera.png" class="mainInfoBG1">
		</div>
		<div id="idx_mn_sizeFont1" style="text-align:left; margin-left: 5px; display: table-cell; /* vertical-align: middle; */  /* border: 1px solid blue; */ ">
			<div style=" margin: 20px 0px 20px 0px;">
				<h4 style="">포토그래퍼</h4>
					<div id="idx_Page_upload_Menu" style="max-width:250px; /* border: 1px solid red; */">멋진 사진을 많은 사람과 공유합니다.</div><br>
				<a href="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do" id="subtn1" class="btn btn-sub-start" style="">사진 올리기</a><br>
			</div>
		</div>
	</div>

	<div class="col-xs-6 align-center" style="padding-left: 35px; width:50%;  /* border: 1px solid blue; */border-left:solid 1px #E5E5E5;">
	 	<div class="col-sm-2" style="/* float:left; */text-align:center; margin:20px 10px -10px 10px;  /* border: 1px solid red; */ ">
			<img src="${pageContext.request.contextPath }/pt_images/search.png" class="mainInfoBG2">
		</div>
		<div id="idx_mn_sizeFont2" style="text-align:left; display: table-cell; /* vertical-align: middle; */ /* border: 1px solid red; */">
			<div style="margin: 20px 0px 20px 0px;/* float:left; */  /* border: 1px solid blue; */">
				<h4 style="/* font-weight:bold; color:#555555; */">기억의 순간</h4>
					<div id="idx_Page_view_Menu" style="max-width:250px; /* border: 1px solid red; */">다양한 추억의 순간을 소셜친구와 공유합니다.</div><br>
				<a href="${pageContext.request.contextPath}/photo/photoList.do?sort=pop" id="subtn2" class="btn btn-sub-start" style="">구경하기</a><br>
			</div>
		</div>
	</div>
</div>


<!-- <div id="width1">
</div> -->

<div style="width:100%; clear:both; border-top:solid 1px #E5E5E5;"> 
	 <%@ include file="inc/photoCatalog.jsp"%>
</div>

</body>

<script type="text/javascript">
/*RESIZE  */
	
	$(document).ready(function(){
		$(window).resize(function(){
			var tsize = $(window).width();
			if(tsize < 752){
				$('#idx_Page_upload_Menu, #idx_Page_view_Menu').css({"font-size":"12px", "text-align": "center", "max-width":"150px"});
				$('#idx_mn_sizeFont1, #idx_mn_sizeFont2').css("text-align","center");
				$('#subtn1, #subtn2').attr('class','btn btn-sub-smstart');
			}
			if(tsize >= 752){
				$('#idx_Page_upload_Menu, #idx_Page_view_Menu').css({"font-size":"15px", "text-align": "left", "max-width":"250px"});
				$('#idx_mn_sizeFont1, #idx_mn_sizeFont2').css("text-align","left");
				$('#subtn1, #subtn2').attr('class','btn btn-sub-start');
			}
			
		}).resize();
	});
	
	//test size check
/* 	 $(document).ready(function(){
		var width = $(window).width();
		$('#width1').html(width);
		$(window).resize(function(){
			var width = $(window).width();
			$('#width1').html(width);
		});
	}); */
	
	$(window).on('resize',function() {
		var hidA = $('#idx_Page_upload_Menu'); 
		var hidB = $('#idx_Page_view_Menu');
		
		var maxH  = Math.max(hidA.height(), hidB.height());
			if($(hidA).height() != maxH){
				$(hidA).height($(hidB).height());
			}
			if($(hidB).height() != maxH){
				$(hidB).height($(hidA).height());
			}
	});
	$(document).ready(function() {
	    $(window).trigger('resize');
	});

</script>

<%@ include file="inc/mainBottom.jsp" %>