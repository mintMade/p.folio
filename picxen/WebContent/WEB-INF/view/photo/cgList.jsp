<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>

</head>


<div class="container" style="">
<div class="" style="margin:20px; float:left;">
	<div class="" style="float:left;  border-right: 1px solid #E5E5E5; margin-right: 10px;">
		<div id="accBtn" class="btn-group" style="margin-right: 10px;"> <!--css listPhoto ctrl menu-->   
			    <a href="#" id="cgName" style="" data-toggle="dropdown">
			 		<i class="fa fa-caret-down"></i>
					 	<c:if test="${param.cgName==null || empty param.cgName}">카테고리 선택</c:if>
					 	${param.cgName}
			 	</a>
				    <ul class="dropdown-menu" role="menu">
				      <% String ftName = (String)request.getAttribute("ftName"); %><!-- ftName추가 -->
					      <li ><a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=모두보기&ftName=${param.ftName}">모두보기</a></li>
					      <li class="divider"></li>
						      <c:forEach var="cgBean" items="${cgList}">
						      	 <li><a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${cgBean.categoryName}&ftName=${param.ftName}">
						      		${cgBean.categoryName}</a></li>
						      </c:forEach>
				    </ul>
		</div>
	</div>
	
	
  	<div id="horiz-menu" style="float:left; ">
		<div class="pop" style="margin-right: 20px; float:left; ">
			<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=pop" style="/* color: #ff1493; */"
				id="pt_list_Btn" >인기 사진</a>
		 </div>
		
		 <div class="new" style="margin-right: 20px; float:left;">		    
			<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=new" style="/* color: #ff1493; */"
				id="pt_list_Btn" >새로 올라온 사진</a>
		 </div>		    		
		
		 <div class="upcom" style="margin-right: 20px; float:left;">
			<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=upcom" style=""
				id="pt_list_Btn">뜨고 있는 사진</a>
		 </div>		  				
	</div>
	
	<div id="verti-menu" style="float:left; ">
		<div id="accBtn" class="btn-group" style="margin-right: 10px;">    
		  	<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=${param.ftName}" style="color: #ff1493;"
		   		id="pt_list_Btn" data-toggle="dropdown">
		   		<i class="fa fa-caret-down"></i>
			   		<c:if test="${param.ftName == 'pop'}">인기사진</c:if>
		    		<c:if test="${param.ftName == 'new'}">새로 올라온 사진</c:if>
		    		<c:if test="${param.ftName == 'upcom'}">뜨고 있는 사진</c:if>
		    		<c:if test="${param.ftName == null || empty param.ftName}">인기사진</c:if>
		    	</a>
				<ul class="dropdown-menu" role="menu">
						<li class="" style="">
						    <a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=pop" style=""
						    	id="" >인기 사진</a>
						</li>
				    	<li class="divider"></li>				
						<li class="" style="">		    
				    		<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=new" style=""
				    			id="" >새로 올라온 사진</a>
						</li>		    	
				  		<li class="" style="">		  	
					  		<a href="${pageContext.request.contextPath}/photo/photoList.do?cgName=${param.cgName}&ftName=upcom" style=""
				  				id="">뜨고 있는 사진</a>
						</li>		  				
				</ul>
		</div>
	</div>
	
</div>
  
</div><!-- container -->

<script type="text/javascript">

	$(window).on('resize', function(){
		var win_widht= $(window).width();
		if(win_widht <= 516){
			$('#horiz-menu').css("display", "none");
			$('#verti-menu').css("display", "block");
		}else{
			$('#horiz-menu').css("display", "block");
			$('#verti-menu').css("display", "none");		
		}
	});
		$(document).ready(function() {
	    $(window).trigger('resize');
	});
	
	$(document).ready(function(){
		$.urlParam = function(name){
		    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		    if (results==null){
		       return null;
		    }
		    else{
		       return decodeURI(results[1]) || 0;
		    }
		};
		var f = $.urlParam('ftName');
		
		$("."+f).children('a').css("color","#ff1493");
	});
		

	$(document).ready(function(){
		$(window).resize(function(){
			var dSize = $(window).width();
			if(dSize >= 752){
				$('#accBtn').attr('class','btn-group');
			}
			if(dSize < 752){
				$('#accBtn').attr('class','btn-group pull-left');
			}
				
		}).resize();
	});		
		
</script>

</html>
