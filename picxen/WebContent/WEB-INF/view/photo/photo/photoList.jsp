<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%String lUserid=(String)session.getAttribute("userid");
	boolean lIsLogin=false;
		if(lUserid != null && !lUserid.isEmpty()){
			lIsLogin=true;
		}
		System.out.println(lIsLogin);
		System.out.println(lUserid);
	%>
	<%-- <%=(String)session.getAttribute("userid") %> --%>

	<%String ip=request.getHeader("x-forwarded-for");
	
	if(ip==null||ip.length()==0){
		ip=request.getHeader("Proxy-Client-IP");
	}
	
	if(ip==null||ip.length()==0){
		ip=request.getHeader("WL-Proxy-Client-IP");
	}

	if(ip==null||ip.length()==0){
		ip=request.getRemoteAddr();
		System.out.println("ptIp="+ip);
		
	}//ip확인  ip파라미터
	%>
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

<form name="frmList" method="post" >


<!-- 카테고리 -->
<div style=" background-color:#FFFFFF;">
	<%@include file="../cgList.jsp" %>
</div>

<div style="border-top:solid 1px #E5E5E5;">

 <div class="container" style="background-color:#F5F5F5;">
<!-- <div style="margin: 0 auto; width:1300px; background-color:#F5F5F5;"> -->

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
    		<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&sort=pop&ip=<%=ip %>" >
    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}');">
    				<div class="caption">
    					<fmt:formatNumber value="${ptBean.popular}" type="Number" pattern="###.#" />
    				</div>
    					<img src="${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}">
    			</div>
    			</a>
  					<a href="${pageContext.request.contextPath }/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&sort=pop&ip=<%=ip %>" 
  						class="btn btn-poplistT btn-xs" style=" color:#ffffff;" >${ptBean.photoTitle}</a>  						   				
  					<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${ptBean.uploader}" 
  						class="btn btn-poplistB btn-xs" style=" color:#ffffff; " >${ptBean.uploader}</a>
    		</div>
    </div> 
		</c:if>
	</c:forEach>
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
	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmPage.submit();
	}
</script>
 

<%@ include file="../../inc/mainBottom.jsp" %>