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

<h3>사진 리스트</h3>
<body style="overflow-x:hidden; overflow-y:auto;">

<!-- 페이징 처리 form -->
<form name="frmPage" method="post">
	<input type="hidden" name="cgName" value="${param.cgName}">
	<input type="hidden" name="currentPage">
</form>


<!-- 페이징 처리 form끝 -->
<!-- 카테고리 -->

<div class="container" style="">

<form name="frmList" method="post" >

<!-- 소팅버튼 -->
<div>
	<ul>
	<li>pop</li>
	<li>new</li>
	<li>upcom</li>
	</ul>
</div>



<div style="">
<select style="width:350px;" class="form-control"  name="cgName" onchange="javascript:searchCg(this)" >
	<option value="">모든 카테고리</option>
	<c:forEach var="cgBean" items="${cgList }">
		<option value="${cgBean.categoryName}" <c:if test="${param.cgName==cgBean.categoryName}">selected</c:if>>
			${cgBean.categoryName}
		</option>
	</c:forEach>
</select>
</div> 

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
    		<div class="row" style="margin: 10px;">
    		<a href ="${pageContext.request.contextPath}/photo/photo/photoDetail.do?ptNo=${ptBean.photoNo}" >
    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}');">
    			</div>
    			</a>
    		</div>
    </div>
		</c:if>
	</c:forEach>
	<!-- 반복끝  -->
</div>	



<div class="" style="">
	<div class="col-xs-10" style="position:relative; left:10%; text-align:center;">
		<c:forEach var="i" begin="${pb.firstPage}" end="${pb.lastPage }">
		<c:choose>
			<c:when test="${pb.totalRecord <=40 }">
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
</div>
</form>
</div>

</body>
<script type="text/javascript">
	//value값을 넘겨줌
	function searchCg(sel){
		var cgName = sel.value;				
		frmList.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmList.submit(); 
		}  
		
	
	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmPage.submit();
	}
</script>
 

<%@ include file="../../inc/mainBottom.jsp" %>