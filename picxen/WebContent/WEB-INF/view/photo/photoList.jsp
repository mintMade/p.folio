<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/mainTop.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%String lUserid=(String)session.getAttribute("userid");
	boolean lIsLogin=false;
		if(lUserid != null && !lUserid.isEmpty()){
			lIsLogin=true;
		}
		System.out.println(lIsLogin);
		System.out.println(lUserid);
	%>
<!DOCTYPE html>
<html lang="ko" data-contextPath="${pageContext.request.contextPath}">	
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
</head>

<body style="background-color:#F5F5F5; overflow-x:hidden; overflow-y:auto; ">

<!-- 페이징 처리 form -->
<%-- <form name="frmPage" method="post">
	<input type="hidden" name="cgName" value="${param.cgName}"> 
	<input type="hidden" name="currentPage">
</form> --%>
<!-- 페이징 처리 form끝 -->

<!-- 카테고리 -->
<div style=" background-color:#FFFFFF;">
	<jsp:include page="cgList.jsp" flush="false">
		<jsp:param name="cgName" value="${param.cgName}" /> 
	</jsp:include>
</div>

	<div style="border-top:solid 1px #E5E5E5;">
		 <div class="container" style="background-color:#F5F5F5;">
			<div style="">
				<c:set var="curPos" value="${pb.curPos}"/>
				<c:set var="num" value="${pb.num}"/>
				<c:set var="count" value="0"/>
				<c:forEach var="i" begin="1" end="${pb.pageSize}">
					<c:if test="${num >= 1}">
						<c:set var="ptBean" value="${alist[curPos]}"/>
						<c:set var="curPos" value="${curPos+1 }"/>
						<c:set var="num" value="${num-1 }"/>
						<c:set var="count" value="${count+1}"/>
						
			     <div class="col-lg-3 col-md-3 col-sm-6 col-md-4" style="">
			    		<div class="row" style="margin: 10px -3px 10px -3px;"> 
			    			<a href ="#" 
			    				class="dTailView" data-idx="${count}" data-params="{'ptNo': ${ptBean.photoNo},'userid': ${userid}, 'sort': ${sort}, 'cgName': ${cgName}}">
				    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}');">
				    				<div class="caption">
				    					<fmt:formatNumber value="${ptBean.popular}" type="Number" pattern="###.#" />
				    				</div>
				    					<img src="${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}">
				    			</div>
			    			</a> 
			  					<a href="#" data-idx="${count}"
			  						class="btn btn-poplistT btn-xs" style=" color:#ffffff;" >${ptBean.photoTitle}</a>  						   				
			  					<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${ptBean.uploader}" 
			  						class="btn btn-poplistB btn-xs" style=" color:#ffffff; " >${ptBean.uploader}</a>
			    		</div>
			    		<form name="frmList_${count}" method="post">
							<input type="hidden" name="ptNo" value="${ptBean.photoNo}">
				    		<input type="hidden" name="userid" value="${userid}">
				    		<input type="hidden" name="sort" value="${sort}">
				    		<input type="hidden" name="cgName" value="${cgName}">
			    		</form>
			    </div> 
					</c:if>
				</c:forEach>
				<!-- 반복끝  -->
			</div>	
		</div>
	</div>

</body>

<script type="text/javascript">	
	$(document).ready(function() { 
	    $('.ratio').hover(
	        function(){
	            $(this).find('.caption').slideDown(250); //.fadeIn(250)
	        },
	        function(){
	            $(this).find('.caption').slideUp(250); //.fadeOut(205)
	        }
	    ); 
	});


	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmPage.submit();
	}
	
	$(".dTailView, .btn-poplistT").each(function(){
		$(this).on('click', function(){
			var idx=$(this).data('idx');
			var detailPtData=$('form[name="frmList_'+idx+'"]').serialize();
			 $.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/photo/photoCountUpdate.do",
				data: detailPtData,
				success:function(data){
					window.location.href=
						"${pageContext.request.contextPath}/photo/photo/photoDetail.do?"+this.data
				}
			  });
			})
		});
	
	
</script>
 
</html>
<%@ include file="../inc/mainBottom.jsp" %>