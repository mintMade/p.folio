<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

	
</script>

<h3>사진 등록</h3>

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
		<c:forEach var="mcvBean" items="${tAlist}" >

			
		<tr>
			<td>
				<img src="${pageContext.request.contextPath}/my_icon/${mcvBean.myIcon}" border="0" width="60">
			</td>
			<td width="50">
				유저ID:${mcvBean.userid}
				<fmt:formatDate value="${mcvBean.regdate}" type="Date" pattern="MM"/>
			</td>
			<td width="200">
				<a href="javascript:likeComment(${mcvBean.commentNo})">코멘트No:${mcvBean.commentNo}</a><br>
				좋아요 수: ${mcvBean.commentLike}<br>
				코멘트 No: ${mcvBean.commentNo}<br>
				포토넘버: ${ptNo}<br>
				포토넘버: ${ptBean.photoNo}<br>
				멤버넘버: ${mvBean.memberNo}
			</td>
			<td>
				<textarea cols="50" rows="2">${mcvBean.content}</textarea>
			</td>
		</tr>
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
			<td width="50">
				유저ID: ${mcvBean.userid}<br>
				<fmt:formatDate value="${mcvBean.regdate}" type="Date" pattern="MM"/>
			</td>
			<td width="200">
				<!-- <input type="button" value="좋아요" onclick="likeComment()"/> -->
				<a href="javascript:likeComment(${mcvBean.commentNo})">코멘트No: ${mcvBean.commentNo}</a><br>
				좋아요 수: ${mcvBean.commentLike}<br>
				코멘트 No: ${mcvBean.commentNo}<br>
				포토넘버: ${ptNo}<br>
				포토넘버: ${ptBean.photoNo}<br>
				멤버넘버: ${mvBean.memberNo}
			</td>
			<td>
				<textarea cols="40" rows="2">${mcvBean.content}</textarea>
			</td>
		</tr>
		
	 </c:forEach>
</table>
</form>

<%@ include file="../../inc/mainBottom.jsp"%>