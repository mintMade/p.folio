<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	function postComment(){
		
		document.formComments.submit();
	}
	
	function likeComment(commentNo){
		commentLike.commentNo.value=commentNo;
		commentLike.action
		="${pageContext.request.contextPath}/photo/comments/commentsLike.do?ptNo=${ptBean.photoNo}&userid=${userid}&commentUser=${mvBean.memberNo}";
		
		document.commentLike.submit();
	}

 	
/*  	function Reply(){
 		var e = document.getElementById("repleTextArea");
 		if(e.style.visibility=='visible')
 			e.style.visibility='hidden';
 		else
 			e.style.visibility='visible';
 	} */
 	
 	function Reply(id){
 			//개수를 구해서 tCmSize를 안쓰고 tAlist 사용하기?
			/* var len = '${tCmSize}';
			for(var i=0;i<len ;i++){ */
  			e = document.getElementById("rpId_"+id);
 			if(e.style.visibility=='visible')
 				e.style.visibility='hidden';
 			else
 				e.style.visibility='visible';
 			
			
 		}
	
</script>

<h3>사진 등록</h3>

${tCount.length}
<form name="commentLike" method="post">
	<input type="text" name="commentNo"/>
</form>

<form name="formComments" method="post">
<table width="680" class="box2">
	<tr>
		<td width="120">사진제목</td>
		<td>
			<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" border="0" width="60">
		</td>
	</tr>

	<tr>
		<td>요약설명</td>
		<td>
			${ptBean.photoNo}<br>
			${userid }<br>
		</td>
	</tr>

	<tr>
		<td>코멘트</td>
		<td>
			<textarea cols="84" rows="3" name="content"></textarea>
		</td>
	</tr>

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="등록" onclick="postComment()">
			<input type="reset" value="취소">
		</td>
	</tr>	
</table>

<table width="700" border="1">
<!-- 반복 시작 -->
		<%-- <c:forEach var="mcvBean" items="${tAlist}" > --%>
		<%-- <c:forEach var="i" begin="1" end="${tCmSize}" > --%>
		
		
			<%-- <c:forEach var="i" begin="1" end="${tCmSize}" >		 --%>
			
			<%-- <c:forEach var="mcvBean" items="${tAlist}"> --%>
			
			<%-- <c:if test="${tCmSize>=1 }" >
			<c:set var="mcvBean" value="${tAlist}"/> --%>
			
			<!-- 중복 -->
				 
				 
				 <c:forEach var="i" begin="1" end="${tCmSize}">
				 <c:if test="${tCmSize>=1 }" > 
				 <c:set var="mcvBean" value="${tAlist}"/>

				 
			<%-- <c:forEach var="mcvBean" items="${tAlist}" > --%>
				<%-- <c:if test="${tCmSize>=1 }">
				<c:set var="i" value="${tCmSize}" /> --%>
				 
					
			
		<tr>
			<td>
				<img src="${pageContext.request.contextPath}/my_icon/${mcvBean.myIcon}" border="0" width="60">
			</td>
			<td width="80">
				${mcvBean.userid}
				좋아요 수: ${mcvBean.commentLike}
				코멘트 No: ${mcvBean.commentNo}
				포토넘버: ${ptNo}
				포토넘버: ${ptBean.photoNo}
				멤버넘버: ${mvBean.memberNo}
			</td>
			<td>
				<textarea cols="80" rows="2">${mcvBean.content}</textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="4">
				<input type="button" id="${i-1}" value="Reple" onclick="Reply(this.id)" />	
				<input type="text" id="rpId_${i-1}" size="60" style="visibility:hidden">
				
			</td>
		</tr>
		</c:if>
		</c:forEach>

			
		
</table>
==============================================================
<table width="700" border="1">
<!-- 반복 시작 -->
	<c:forEach var="mcvBean" items="${alist}" >
		<tr>
			<td>
				<img src="${pageContext.request.contextPath}/my_icon/${mcvBean.myIcon}" border="0" width="60">
			</td>
			<td width="60">
				${mcvBean.userid}
				<fmt:formatDate value="${mcvBean.regdate}" type="Date" pattern="MM"/>
			</td>
			<td>
				<!-- <input type="button" value="좋아요" onclick="likeComment()"/> -->
				<a href="javascript:likeComment(${mcvBean.commentNo})">${mcvBean.commentNo}</a>
				좋아요 수: ${mcvBean.commentLike}
				코멘트 No: ${mcvBean.commentNo}
				포토넘버: ${ptNo}
				포토넘버: ${ptBean.photoNo}
				멤버넘버: ${mvBean.memberNo}
			</td>
			<td>
				<textarea cols="80" rows="2">${mcvBean.content}</textarea>
			</td>
		</tr>
		
	 </c:forEach>
</table>
</form>

<%@ include file="../../inc/mainBottom.jsp"%>