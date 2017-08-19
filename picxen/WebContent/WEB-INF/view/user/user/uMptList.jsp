<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>

	<div class="container">
		<c:set var="cnt" value="0" />
		<c:forEach var="i" begin="1" end="${totalRecord }" >
			<c:set var="cnt" value="${cnt +1}" />
			<c:set var="ptBean" value="${alist[i-1]}" />
		
		     <div class="col-lg-3 col-md-3 col-sm-6 col-md-4" style="">
		    		<div class="row" style="margin: 10px -3px 10px -3px;">
			    		<a href ="#" class="dTailView" data-idx="${cnt }">
			    			<div class="ratio" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}');">
			    					<img src="${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}">
			    			</div>
			    		</a>
			  			<a href="#" class="btn btn-poplistT btn-xs" data-idx="${cnt }" style=" color:#ffffff;" >${ptBean.photoTitle}
			  			</a> 			   				
		    		</div>
 		   			<form name="frmList_${cnt}" method="POST">
						<input type="hidden" name="ptNo_${cnt }" value="${ptBean.photoNo }">
		  				<input type="hidden" name="sort_${cnt }" value="uM">
		  				<input type="hidden" name="cgName_${cnt }" value="uM">
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
							  "cgName" : $('input[name="cgName_'+idx+'"]').val(),
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