<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>

</head>
<!-- select design -->
<%-- <div class="container" style="">
<select style="width:350px;" class="form-control"  name="cgName" onchange="javascript:searchCg(this)" >
	<option value="">모든 카테고리</option>
	<c:forEach var="cgBean" items="${cgList }">
		<option value="${cgBean.categoryName}" <c:if test="${param.cgName==cgBean.categoryName}">selected</c:if>>
			${cgBean.categoryName}
		</option>
	</c:forEach>
</select>
</div> --%>

<div class="container" style="">

<%-- <ul class="btn-group " style="float:left; margin: 0 20px 0 0;">
  <!-- <div class="btn-group" > -->
    <li style="float:left;" ><a type="button" class="btn btn-default" style="width:160px;"
    	href="${pageContext.request.contextPath}/photo/photo/popular.do">인기 사진</a></li>
  <!-- </div> -->
  <!-- <div class="btn-group" > -->
    <li style="float:left;"><a type="button" class="btn btn-default" style="width:160px;"
 	   href="${pageContext.request.contextPath}/photo/photo/new.do">새로 올라온 사진</a></li>
  <!-- </div> -->
  <!-- <div class="btn-group" > -->
    <li style="float:left;"><a type="button" class="btn btn-default" style="width:160px;">뜨고 있는 사진</a></li>
  <!-- </div> -->
</ul>  --%>

<div class="btn-group " style="float:left; margin: 0 20px 0 0;">
  <div class="btn-group" >    
    <c:if test="${param.cgName==null}">
    <a type="button" class="btn btn-default" style="width:160px;"
    		href="${pageContext.request.contextPath}/photo/photo/popular.do">인기 사진</a>
    </c:if>
    <c:if test="${param.cgName!=null }">
    	<a type="button" class="btn btn-default" style="width:160px;" 
    		href="${pageContext.request.contextPath}/photo/photo/popular.do?cgName=${param.cgName}">인기 사진</a>
    </c:if>
  </div>
  
  <div class="btn-group" >
  	<c:if test="${param.cgName==null }">
  		<a type="button" class="btn btn-default" style="width:160px;"
 	   		href="${pageContext.request.contextPath}/photo/photo/new.do">새로 올라온 사진</a>
  	</c:if>
  	<c:if test="${param.cgName!=null }">
    	<a type="button" class="btn btn-default" style="width:160px;"
 		   href="${pageContext.request.contextPath}/photo/photo/new.do?cgName=${param.cgName}">새로 올라온 사진</a>
 	</c:if> 
  </div>
  
  <div class="btn-group" > 
  	<c:if test="${param.cgName==null }">
    	<a type="button" class="btn btn-default" style="width:160px;"
    		href="${pageContext.request.contextPath }/photo/photo/upcoming.do">뜨고 있는 사진</a>
    </c:if>
    <c:if test="${param.cgName!=null }">
    	<a type="button" class="btn btn-default" style="width:160px;"
    		href="${pageContext.request.contextPath }/photo/photo/upcoming.do?cgName=${param.cgName}">뜨고 있는 사진</a>
    </c:if>
  </div>
</div> 

  
 <div class="btn-group" style="float:left; ">
 	<button type="button" class="btn btn-default dropdown-toggle" name="cgName" id="cgName" style="width:160px; text-align:left;" data-toggle="dropdown">
 	<i class="fa fa-caret-down"></i>
 	<c:if test="${param.cgName==null}">카테고리 선택</c:if>
 	${param.cgName}
 	</button>
 	<%-- !!<input type="button" class="btn btn-default dropdown-toggle" name="cgName" id="cgName" value="${param.cgName}" style="width:250px; text-align:left;" data-toggle="dropdown"> --%>
    <ul class="dropdown-menu" role="menu">
      <li ><a href="${pageContext.request.contextPath}/photo/photo/popular.do?cgName=모두보기">모두보기</a></li>
      <li class="divider"></li>
      <c:forEach var="cgBean" items="${cgList}">
      	 <li><a href="${pageContext.request.contextPath}/photo/photo/popular.do?cgName=${cgBean.categoryName}"> 
      		${cgBean.categoryName}</a></li>
      </c:forEach>
    </ul>
  </div>
 
</div>

</html>
