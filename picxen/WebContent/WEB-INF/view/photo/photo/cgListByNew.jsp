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
			    <a href="#" name="cgName" id="cgName" style="" data-toggle="dropdown">
			 		<i class="fa fa-caret-down"></i>
					 	<c:if test="${param.cgName==null}">카테고리 선택</c:if>
					 	${param.cgName}
			 	</a>
				    <ul class="dropdown-menu" role="menu">
				      <li ><a href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=모두보기">모두보기</a></li>
				      <li class="divider"></li>
					      <c:forEach var="cgBean" items="${cgList}">
					      	 <li><a href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=${cgBean.categoryName}"> 
					      		${cgBean.categoryName}</a></li>
					      </c:forEach>
				    </ul>
		</div>
	</div>
	
	
  	<div id="horiz-menu" style="float:left; ">
		    <c:if test="${param.cgName==null}">
				 <div class="" style="margin-right: 20px; float:left; ">
				    <a href="${pageContext.request.contextPath}/photo/photo/popular.do" 
				    	id="pt_list_Btn" >인기 사진</a>
				 </div>
		    </c:if>
		
		    <c:if test="${param.cgName!=null }">
				<div class="" style="margin-right: 20px; float:left;">		    
		    		<a href="${pageContext.request.contextPath}/photo/photo/popular.do?cgName=${param.cgName}" 
		    			id="pt_list_Btn" >인기 사진</a>
				</div>		    		
		    </c:if>
		
		  	<c:if test="${param.cgName==null }">
		  		<div class="" style="margin-right: 20px; float:left;">		  	
			  		<a href="${pageContext.request.contextPath}/photo/photo/new.do" style="color: #ff1493;"
		  				id="pt_list_Btn">새로 올라온 사진</a>
				</div>		  				
		  	</c:if>
		
			
		  	<c:if test="${param.cgName!=null }">
		  	  	<div class="" style="margin-right: 20px; float:left;">
		    		<a href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=${param.cgName}" style="color: #ff1493;"
		    			id="pt_list_Btn">새로 올라온 사진</a>
		    	</div> 
		 	</c:if> 
			
		  	<c:if test="${param.cgName==null }">
		  		<div class="" style="margin-right: 20px; float:left;">
			    	<a href="${pageContext.request.contextPath }/photo/photo/upcoming.do"
		    			id="pt_list_Btn">뜨고 있는 사진</a>
				</div>			    			
		    </c:if>
	    		
		    <c:if test="${param.cgName!=null }">
		      	<div class="" style="margin-right: 20px; float:left;">
		    		<a href="${pageContext.request.contextPath }/photo/photo/upcoming.do?cgName=${param.cgName}"
		    			id="pt_list_Btn">뜨고 있는 사진</a>
				</div>		    		    			
		    </c:if>
	 </div>
	
	<div id="verti-menu" style="float:left; ">
		<div id="accBtn" class="btn-group" style="margin-right: 10px;">
		
			<c:if test="${param.cgName==null}">
				<a href="${pageContext.request.contextPath}/photo/photo/new.do" style="color: #ff1493;"
				    id="pt_list_Btn" data-toggle="dropdown">
				    <i class="fa fa-caret-down"></i>
				    새로운 사진 </a>
		    </c:if>
		
		    <c:if test="${param.cgName!=null }">	    
		    	<a href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=${param.cgName}" style="color: #ff1493;"
		    		id="pt_list_Btn" data-toggle="dropdown">
		    		<i class="fa fa-caret-down"></i>
		    	   새로운 사진 </a>
		    </c:if>
				<ul class="dropdown-menu" role="menu">
				    
				    <c:if test="${param.cgName==null }">
				  		<li class="" style="">		  	
					  		<a href="${pageContext.request.contextPath}/photo/photo/new.do" style=""
				  				id="">새로 올라온 사진</a>
						</li>		  				
				  	</c:if>
				
					
				  	<c:if test="${param.cgName!=null }">
				  	  	<li class="" style="">
				    		<a href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=${param.cgName}" style=""
				    			id="">새로 올라온 사진</a>
				    	</li> 
				 	</c:if>
				    
				    <li class="divider"></li>
				
					<c:if test="${param.cgName==null}">
						 <li class="" style="">
						    <a href="${pageContext.request.contextPath}/photo/photo/popular.do" style=""
						    	id="" >인기 사진</a>
						 </li>
				    </c:if>
				
				    <c:if test="${param.cgName!=null }">
						<li class="" style="">		    
				    		<a href="${pageContext.request.contextPath}/photo/photo/popular.do?cgName=${param.cgName}" style=""
				    			id="" >인기 사진</a>
						</li>		    		
				    </c:if>
					
				  	<c:if test="${param.cgName==null }">
				  		<li class="" style="">
					    	<a href="${pageContext.request.contextPath }/photo/photo/upcoming.do"
				    			id="">뜨고 있는 사진</a>
						</li>			    			
				    </c:if>
			    		
				    <c:if test="${param.cgName!=null }">
				      	<li class="" style="">
				    		<a href="${pageContext.request.contextPath }/photo/photo/upcoming.do?cgName=${param.cgName}"
				    			id="">뜨고 있는 사진</a>
						</li>		    		    			
				    </c:if>
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
