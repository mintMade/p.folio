<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type='text/javascript' src='/js/lib/dummy.js'></script>


<style>
	#cm-warp { width:100%; height:100%; background-color:gray; position:absolute;}
	#cm-container{background-color:#f0fff0; border:1px solid#FFBB00;
				  width:1200px; height:100%; margin:0 auto; /* position:relative; */ }
	#cm-contents{ background-color:#f5f5f5; border:1px solid#6a5acd;
				  width:697px; height:100%; /* margin:0 auto; position:relative; */ top:10px; float:left}	
	#cm-sidebar{background-color:yellow; border:1px solid#FFBB00;
				  width:497px; height:100%; /* margin:0 auto; position:relative; */ top:10px; float:left}	  			  
	
	.comments{padding:10px;background-color:green; /* margin-top:300; */}				  
 	.commCNT{ padding:10px; color:orange; font-size:15px; /* position:relative; */
					  background-color:#9F3;}/* div #cmtsHeader클래스속에 있는 span */
	.user_avatar_wrap{padding:10px; width:700px; height:120px; background-color:#FFBB00; /* position:relative; */}
	.my_top_avatar{border: 1px solid#487be1; width:80px; float:left;}
	.my_top_comment{border: 1px solid#487be1; width:594px; float:left;}
	.post_comment_btn{padding:10px; float:right;}
    				  
/*  textarea*/		

</style>

</head>

<body>
<div id="cm-warp">
<form name="formComments" method="post" action='<c:url value="/photo/photo/photoDetail.do"/>'>
<div id="cm-container">

<!-- comment contents Left-->
	<div id="cm-contents">
		<div class="comments">
			<span class="commCNT">
					COMMENTS
					<span class="badge">100</span>
			</span>
		</div>
		<div class="user_avatar_wrap">
			<div class="my_top_avatar">
				<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" border="0" width="60">
			</div>
			
			<div class="my_top_comment">
   				<textarea class="form-control" rows="3" style="resize: none;"placeholder="코맨트를 입력 해주세요"></textarea>
   			</div>
   			<div class="post_comment_btn" >
   				<button class="btn btn-success btn-lg" style="font-size:13px" onClick="user_postComment()">
   				코맨트 입력</button>
   			</div>
		</div>	
	</div>
	
<!-- faves like side Right-->
	<div id="cm-sidebar">
				<div class="user_avatar_container">
					<img src="${pageContext.request.contextPath}/my_icon/${mvBean.myIcon}" border="0" width="60">
				</div>
	</div>
	
</div>

</form>

</div>

<script type="text/javascript">
	function user_postComment(){
		document.formComments.submit();
		
	}		
</script>

</body>


</html>