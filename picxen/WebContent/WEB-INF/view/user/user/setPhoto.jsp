<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
	<style>
		<%@ include file="/css/mainstyle.css" %>
	</style>
</head>


<body class="upPhotoViewBody" >
	
<div style="display: flex;">
<form id="setPtForm" class="upPhotoViewBody" method="get">
<div style="display: flex; border-top:3px solid #00A5FF;">
		<div style="float:left; min-height:100%; min-width:260px; ">
				<div style="min-width:260px; min-height:50px; margin:10px 0 10px 0;">
					<a href="${pageContext.request.contextPath}/photo/ptUpload/ptUp.do" type="button" class="btn btn-setPtUp">이미지 업로드</a>	
				</div>
				<div style="min-width:260px; padding:10px 0 0 0; border-top:1px solid #CFCFCF;">
					<a href="#" type="button" class="btn btn-primary" style="margin-left:15px; width:230px;">
						<span style="float:left; margin-left:10px; color:white; font-size:13px;">내 이미지</span> 
						<span style="float:right; margin-left:10px; color:white; font-size:13px;">${totalOfPtList}</span>
					</a>
				</div>
		</div>

<!-- 이미지 리스트  -->
		<div id="ptSize" style="/* position:absolute; */ min-height:100%; max-width:500px; float:left; border-left:1px solid #CFCFCF;">
		 	<c:forEach	var="e" begin="1" end="${totalB }">
		 		<c:set var="blist" value="${bList[e-1] }" />

			 		  <div class="regList" style="">${blist}</div>
					
					<c:set var="cnt" value="0"/>
			 		<c:forEach	var="i" begin="1" end="${totalOfPtList }">
						<c:set var="setPtBean" value="${ptList[i-1] }" />
						<c:set var="regStr" value="${ptList[i-1].regdate }"/>
						<%SimpleDateFormat inFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
						  SimpleDateFormat outFormat =  new SimpleDateFormat("yyyy년 MM월");
						  
						  String input = String.valueOf(pageContext.getAttribute("regStr"));
						  Date date = inFormat.parse(input);
						  String dStr = outFormat.format(date);%>
						 <c:set var="strD" value="<%=dStr%>"/>
						 <c:set var="cnt" value="${cnt+i}"/>
					
	 					<c:if test="${blist eq strD}" >
							<div class="col-lg-3 col-md-3 col-sm-6 col-md-4" style="" >
								<div class="row" style="margin: 5px -3px 5px -3px; ">
						   			<div class="ratioPt" data-idx="${cnt}" id="cnt_${cnt}" style="background-image:url('${pageContext.request.contextPath}/pt_images/${setPtBean.imageURL}');"
						   			onClick="ptInf(${cnt})">
						    			<img src="${pageContext.request.contextPath}/pt_images/${setPtBean.imageURL}">
						    		</div>
						    		
						    		<input type="hidden" id="ptInf_${cnt }" value="${setPtBean.photoNo}" >
						    		<input type="hidden" id="tag_${cnt}" name="tag" value="${setPtBean.tag}" >
						    		<input type="hidden" id="pTitle_${cnt}" name="photoTitle" value="${setPtBean.photoTitle}" >
						    		<input type="hidden" id="cateNo_${cnt}" name="categoryNo" value="${setPtBean.categoryNo}" >
						    		<input type="hidden" id="dscrt_${cnt}" name="description" value="${setPtBean.description}" >
						    		<input type="hidden" id="pubSet_${cnt}" name="publicSet" value="${setPtBean.publicSet}" >
						    		<input type="hidden" id="imageURL_${cnt}" name="imageURL" value="${setPtBean.imageURL}" >
						    		
						    		
					   			 </div>
							</div>
					 	</c:if>
						 			
			 		</c:forEach>
		 	</c:forEach>
		</div>		
	</div> 
</form>	

<!-- edit -->
<div style="width:100%; float:left; border-top:3px solid #00A5FF;">
</div>

<form id="ptInfrm" method="post" style="z-index:2;">
	<input type="hidden" id="dePtNo" name="ptNo" value="">
	
	<div class="ptDetailBox" id="tgSize" style="float:right; ">
			<div style="border-top:solid 1px #DEDEDE; color:#9C9C9C;">
				<div style="margin-left:25px; margin-right:25px;">
					<h4 style="font-weight:bold; margin-top:25px;">정보 입력</h4>
						<div class="editList" style="">
							<span class="editList" >키워드 입력</span><br><br>
							<textarea id="edTag" name="tag" class="form-control" rows="3"></textarea>
						</div>
						
						<div class="editList" style="">
							<span class="editList" >제목</span><br><br>
							<input type="text" id="edTitle" name="photoTitle" class="form-control" placeholder="제목 입력">
						</div>
						<div class="editList" style="">
							<span class="editList" >카테고리</span><br>
						</div>
						<div class="btn-group" style="margin-top:15px;">
							<select class="form-control" id="edCate" name="categoryNo" >
								<c:forEach var="cgBean" items="${cgList }">
									<option value="${cgBean.categoryNo}">${cgBean.categoryName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="editList">
							<span class="editList">설명</span>
						</div>
						<div style="margin-top:15px;">
							<textarea id="edDscrt" class="form-control" name="description" rows="3"></textarea>
						</div>
						<div class="editList">
							<span class="editList">공개여부</span>					
						</div>
						<div class="btn-group" style="margin-top:15px;">
							<select class="form-control" id="edPubSet" name="publicSet">
								<option value="0">공개</option>
								<option value="1">비공개</option>
							</select>
						</div>
				</div>
			</div>
			<div class="upBtn" style="margin-top:40px;">
				<button type="button" class="btn btn-success"  style="font-weight:bold; width:400px; height:50px; margin:10px;" onclick="ptEdt()">
					정보 수정
				</button>
			</div>
			<div class="upBtn">
				<button type="button" id="delBtn" class="btn btn-default" style="font-weight:bold; width:400px; height:50px; margin:10px; color:gray;">
					이미지 삭제
				</button>
			</div>
		</div>
	</form>
	
	<form id="delPtfrm" action="${pageContext.request.contextPath}/user/user/ptDel.do" method="GET">
		<input type="hidden" name="delPtNo" id="dPtNo" value="" >
		<input type="hidden" name="imageURL" id="imageURL" value="" >
	</form>
</div>

</body>

<script type="text/javascript">

function ptEdt(){
	var upPtData = {
			"photoNo" : ptInfrm.ptNo.value,
			"tag" : ptInfrm.tag.value,
			"photoTitle" : ptInfrm.photoTitle.value,
			"categoryNo" : ptInfrm.categoryNo.value,
			"description" : ptInfrm.description.value,
			"publicSet" : ptInfrm.publicSet.value,
	};
	/* alert("uptest"+upPtData); */
	$.ajax({
		url:"${pageContext.request.contextPath}/user/user/setPhoto.do",
		type : 'POST',
		data : upPtData,
		success:function(data){
			window.opener.location.reload();
			self.close();
		},
		error:function(jqXHR, textStatus, errorThrown){
			alert("에러\n"+textStatus+":"+errorThrown);
			self.close();
		}
	});
}//edit

	$("#delBtn").click(function(){
		var delPtNo=document.getElementById("dePtNo").value;
		 if(!delPtNo){
			 $(".ratioPt").css("border", "2px solid #00A5FF");
			 alert("삭제하실 이미지를 선택 해 주세요.");
			 return;
		};
		$("#delPtfrm").submit();
	});
	

	$(window).resize(function(){
		var menuHeight = $(window).height();
		$('#ptSize, #tgSize').height(menuHeight);
	}).resize();
	
	function ptInf(cnt){
		var g = document.getElementById("ptInf_"+cnt).value;
		if(g!=null){
			var pInfo = {
					"ptNo" : g,
					"tag": document.getElementById("tag_"+cnt).value,
					"photoTitle": document.getElementById("pTitle_"+cnt).value,
					"categoryNo": document.getElementById("cateNo_"+cnt).value,
					"description": document.getElementById("dscrt_"+cnt).value,
					"publicSet": document.getElementById("pubSet_"+cnt).value,
					"imageURL": document.getElementById("imageURL_"+cnt).value,
			};
			
			document.getElementById("dePtNo").value=pInfo.ptNo;
			document.getElementById("edTag").value=pInfo.tag;
			document.getElementById("edTitle").value=pInfo.photoTitle;
			document.getElementById("edCate").value=pInfo.categoryNo;
			document.getElementById("edDscrt").value=pInfo.description;
			document.getElementById("edPubSet").value=pInfo.publicSet;
			
			document.getElementById("dPtNo").value=pInfo.ptNo;
			document.getElementById("imageURL").value=pInfo.imageURL;
		};
	};
	
	
	$(".ratioPt").click(function (){
		var idx = $(this).data("idx");

 		$("#cnt_"+idx).css("border", "2px solid #00A5FF");
 		$(".ratioPt").not("#cnt_"+idx).css("border", "2px solid #F5F5F5");

	});
	
</script>


</html>