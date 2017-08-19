<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<%String lUserid=(String)session.getAttribute("userid");
	boolean lIsLogin=false;
		if(lUserid != null && !lUserid.isEmpty()){
			lIsLogin=true;
		}
		System.out.println(lIsLogin);
		System.out.println(lUserid);
	%>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
</head>
<body style="background-color:#F5F5F5; overflow-x:hidden; overflow-y:auto; ">

<form name="frmList" method="post" >


<!-- 카테고리 -->
<div style=" background-color:#FFFFFF;">
	<%@include file="../cgList.jsp" %>
</div>

<div style="border-top:solid 1px #E5E5E5;">

 <div class="container" style="background-color:#F5F5F5;">

<div style="">
	<c:set var="count" value="0"/>
	<c:forEach var="i" begin="1" end="${totalRecord}">
			<c:set var="count" value="${count}+1"/>
			<c:set var="ptBean" value="${ptTagList[i-1]}"/>
     <div class="col-lg-3 col-md-3 col-sm-6 col-md-4" style="">
    		<div class="row" style="margin: 10px -3px 10px -3px;">
    		<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&sort=${sort}&cgName=${cgName}" >
    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}');">
    				<div class="caption">
    					<fmt:formatNumber value="${ptBean.popular}" type="Number" pattern="###.#" />
    				</div>
    					<img src="${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}">
    			</div>
    		</a>
  				<a href="${pageContext.request.contextPath }/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&sort=${sort}&cgName=${cgName}"
  					class="btn btn-poplistT btn-xs" style=" color:#ffffff;" >${ptBean.photoTitle}</a>  						   				
  				<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${ptBean.uploader}" 
  					class="btn btn-poplistB btn-xs" style=" color:#ffffff; " >${ptBean.uploader}</a>
    	   </div>
    </div> 
	</c:forEach>
	<c:if test="${totalRecord==0}">
		<div style="font-Size:30px; color:#999999; text-align:center; margin:30px;">
			검색 결과가 업습니다."${param.kword}"
		</div>
	</c:if>
	<!-- 반복끝  -->
</div>	

<%-- <div class="" style="">
	<div class="col-xs-10" style="position:relative; left:10%; text-align:center;">
		<c:forEach var="i" begin="${pb.firstPage}" end="${pb.lastPage }">
		<c:choose>
			<c:when test="${pb.totalRecord <=10 }">
			</c:when>
			<c:otherwise>
			<c:if test="${i<=pb.totalPage }"> 
				<c:if test="${i==pb.currentPage }">
					<span style="font-weight:bold; color:blue">${i}</span>
				</c:if>
				<c:if test="${i!=pb.currentPage }">
					<a href="javascript:getListByPage(${i})">[${i}]</a>
				</c:if>
			</c:if>
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</div>
</div> --%>
</div>
</div>
</form>


</body>

<script type="text/javascript">	

$( document ).ready(function() {
    /* $("[rel='tooltip']").tooltip();     */
 
    $('.ratio').hover(
        function(){
            $(this).find('.caption').slideDown(250); //.fadeIn(250)
        },
        function(){
            $(this).find('.caption').slideUp(250); //.fadeOut(205)
        }
    ); 
});
</script>
 



<%@ include file="../../inc/mainBottom.jsp" %>