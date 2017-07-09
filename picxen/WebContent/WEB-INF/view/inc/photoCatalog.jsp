<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

</head>

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
<body>
<div  style=" background-color: white; color:#5C5C5C;">
		<div style="text-align:center; padding:5px;">
			<h3 style="text-align:center; padding:5px;">사진과 사람이 연결되는 커뮤니티</h3>
			<h4 style="text-align:center; padding:5px;">사진 전문가와 애호가, 그리고 사진을 즐기는 모든이의 공간으로 초대합니다.</h4><br>
		</div>
</div>

<!-- image different Ratio gallery css html -->

<div style="background-color:#F5F5F5; padding:30px; border-top:solid 1px #E5E5E5;" >
<div class="container" style="background-color:#F5F5F5;">
  <!-- 	<ul class="row"> -->
  		<c:set var="count" value="0"/>
			<c:forEach var="ptBean" items="${alist }">
			<c:set var="count" value="${count+1 }"/>
    	<div class="col-lg-3 col-md-3 col-sm-6 col-md-4" >
    	<%-- <a href ="${pageContext.request.contextPath}/photo/photo/photoDetail.do?ptNo=${ptBean.photoNo}" > --%>
    	<a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo}&userid=${userid}&sort=pop&ip=<%=ip %>" >
    	<div class="img-responsive img-thumbnail ratio-4-3" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}')">
    	</div>
      		</a>
    </div>
			</c:forEach>
  <!-- </ul> -->
</div> 
</div>
</body>