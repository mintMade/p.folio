<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>

<body>

<div class="container">
<c:set var="cnt" value="0" />
<c:forEach var="i" begin="1" end="${myAlbmList}" >
	<c:set var="cnt" value="${cnt +1}" />
	<c:set var="albmBean" value="${blist[i-1]}" />

     <div class="col-lg-3 col-md-3 col-sm-6 col-md-4" style="">
    		<div class="row" style="margin: 10px -3px 10px -3px;">
	    		<%-- <a href ="${pageContext.request.contextPath}/photo/photo/photoCountUpdate.do?ptNo=${albmBean.photoNo}&userid=${userid}&sort=pop&ip=${ip}" > --%>
	    		<a href ="#" data-idx="${cnt}" class="dTailView" >
	    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${albmBean.imageURL}');">
	    					<img src="${pageContext.request.contextPath}/pt_images/${albmBean.imageURL}">
	    			</div>
	    		</a>
	  			<a href="#" data-idx="${cnt}" class="btn btn-poplistT btn-xs" style=" color:#ffffff;" >${albmBean.photoTitle}
	  			</a>  						   				
    		</div>
     		    <form name="frmList_${cnt}" method="POST">
					<input type="hidden" name="ptNo_${cnt }" value="${albmBean.photoNo }">
		  			<input type="hidden" name="sort_${cnt }" value="pop">
		  		</form> 
    </div>  
	
</c:forEach>
</div>

</body>
<script type="text/javascript">

	$(".dTailView, .btn-poplistT").each(function(){
		$(this).on('click', function(){
			var idx=$(this).data('idx');
			var detailPtData={"ptNo"   : $('input[name="ptNo_'+idx+'"]').val(), 
							  "sort"   : $('input[name="sort_'+idx+'"]').val(),
			};
			/* console.log(detailPtData); */
			 $.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/photo/photoCountUpdate.do",
				data: detailPtData,
				success:function(data){
					window.location.href="${pageContext.request.contextPath}/photo/photo/photoDetail.do?"+this.data
				}
			  })
			})
		});
	
</script>


</html>