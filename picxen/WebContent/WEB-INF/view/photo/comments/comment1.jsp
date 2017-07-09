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

 	
/*  	function Reply(){
 		var e = document.getElementById("repleTextArea");
 		if(e.style.visibility=='visible')
 			e.style.visibility='hidden';
 		else
 			e.style.visibility='visible';!!!!!!!!!!!!for 버튼
 	} */
 	
 	/* function Reply(){
 		var len=${tCmSize};
 		for(i = 0; i<len; i++){
  			var e = document.getElementById("rpId_"+i);
 			if(e.style.visibility=='visible')
 				e.style.visibility='hidden';
 			else
 				e.style.visibility='visible';
 			
 				i++;
 				break;
 			}
 		} */
 	///////////////////////////////////어래이로 아이디넣기
 	///아니면 for문에서 +1넣기
 	function Reply(){
 		var len=${tCmSize};
 		for(i = 0; i<len; i++){
 			/* rpCnt=new Array(i.length); */
 			
  			e = document.getElementById("rpId_"+i);
 			if(e.style.visibility=='visible')
 				e.style.visibility='hidden';
 			else
 				e.style.visibility='visible';
 			
 				i++;
 				break;
 			}
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
		<%-- <c:forEach var="mcvBean" items="${tAlist}" > --%>
		<c:forEach var="i" begin="1" end="${tCmSize}" >
			<%-- <c:set var="len" value="${tAlist[tCmSize]}"/> --%>
		
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
				<input type="button" id="" value="Reple" onclick="Reply()" />	
				<input type="text" id="rpId_${i-1}" size="60" style="visibility:hidden">
				
			</td>
		</tr>
		
	 </c:forEach>
</table>

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