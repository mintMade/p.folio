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

<%-- <c:if test="${param.eventName == null || empty param.eventName }">
	전체 상품 ${totalRecord }건 입니다.
</c:if>
<c:if test="${param.eventName!=null && !empty param.eventName }">
	${param.eventName } 상품 ${totalRecord }건 입니다.
</c:if> --%>

<!-- 페이징 처리 form -->
<form name="frmPage" method="post">
	<%-- <input type="hidden" name="eventName" value="${param.eventName }"> --%>
	<input type="hidden" name="cgName" value="${param.cgName}">
	<input type="hidden" name="currentPage">
</form>


<!-- 페이징 처리 form끝 -->
<!-- 카테고리 -->

<div class="container" >

<form name="frmList" method="post">

<div style="border:solid 1px #E5E5E5; height:auto;">
<select style="width:350px;" class="form-control"  name="cgName" onchange="javascript:searchCg(this)" >
	<option value="">모든 카테고리</option>
	<c:forEach var="cgBean" items="${cgList }">
		<option value="${cgBean.categoryName}" <c:if test="${param.cgName==cgBean.categoryName}">selected</c:if>>
			${cgBean.categoryName}
		</option>
	</c:forEach>
</select>
</div> 


<%-- <table width="700">
	<tr>
		<td align="right">
		이벤트 상품 조회
		<select name="eventName">
			<option value=""></option>
			<option value="NEW"	<c:if test="${param.eventName=='NEW' }">selected</c:if> >새로운</option>
			<option value="BEST"
				<c:if test="${param.eventName=='BEST' }">selected</c:if>
				>인기있는</option>
			<option value="MD"
				<c:if test="${param.eventName=='MD' }">selected</c:if>
				>추천</option>
		</select>
		<img src='<c:url value="/images/bt_search3.png"/>'
			border="0"
			align="absmiddle" onclick="searchEvent()"
			style="cursor:pointer">
		</td>
	</tr>
</table> --%>
<div class="container" style="border:solid 1px #E5E5E5; height:20px;">
	<c:set var="curPos" value="${pb.curPos}"/>
	<c:set var="num" value="${pb.num}"/>
	<c:set var="count" value="0"/>
	<c:forEach var="i" begin="1" end="${pb.pageSize}">
		<c:if test="${num >= 1}">
			<c:set var="ptBean" value="${alist[curPos]}"/>
			<c:set var="curPos" value="${curPos+1 }"/>
			<c:set var="num" value="${num-1 }"/>
			<c:set var="count" value="${count+1}"/>
			<div class="col-lg-3 col-md-3 col-sm-6 col-md-4" >
    	<a href ="${pageContext.request.contextPath}/photo/photo/photoDetail.do?ptNo=${ptBean.photoNo}" >
    		<div class="img-responsive img-thumbnail ratio-4-3" style="background-image:url('${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}')">
    		</div>
      	</a>
    </div>
    
    <%-- <div class="col-lg-3 col-md-3 col-sm-6 col-md-4 " style="">
    		<div class="row " style="/* margin: 10px -1px 10px -1px; */ position: relative; border: solid 1px red;
  width: 260px;
  height: 250px;
  margin:8px;
  overflow: hidden;
  ">
    			<a href ="${pageContext.request.contextPath}/photo/photo/photoDetail.do?ptNo=${ptBean.photoNo}" >
    				<img class="portrait" src="${pageContext.request.contextPath}/pt_images/${ptBean.imageURL}" 
    					style="position: absolute;
  left: 50%;
  top: 50%;
  height: 110%;
  width: 200;
  -webkit-transform: translate(-50%,-50%);
      -ms-transform: translate(-50%,-50%);
          transform: translate(-50%,-50%);
   ">

    			</a>
    		</div>
    </div> --%>  
		</c:if>
	</c:forEach>
	<!-- 반복끝  -->
	
	<div>
		<c:forEach var="i" begin="${pb.firstPage}" end="${pb.lastPage }">
			<c:if test="${i<=pb.totalPage }">
				<c:if test="${i==pb.currentPage }">
					<span style="font-weight:bold; color:blue">${i}</span>
				</c:if>
				<c:if test="${i!=pb.currentPage }">
					<a href="javascript:getListByPage(${i})">[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
	</div>
	
</div>

 <%-- <table width="700" class="box2">
	<tr>
		<th>
			<input type="checkbox" name="chkAll" onclick="checkedAll()">
		</th>
		<th>사진이미지</th>
		<th>사진제목</th>
		<th>업로더</th>
		<th>View</th>
	</tr>

	<!-- 반복시작 -->
	<!-- 페이징 처리를 위해 한페이지에 5개식만 보여주기 -->
	<c:set var="curPos" value="${pb.curPos }"/>
	<c:set var="num" value="${pb.num }"/>
	<c:set var="count" value="0"/>
	<c:forEach var="i" begin="1" end="${pb.pageSize }">
		<c:if test="${num>=1 }">
		<c:set var="ptBean" value="${alist[curPos] }"/>
		<c:set var="curPos" value="${curPos+1 }"/>
		<c:set var="num" value="${num-1 }"/>
		<c:set var="count" value="${count+1 }"/>
		<!--=>해당 페이지에서의 레코드수  -->
		<tr>
			<td align="center">
			<!-- Command객체의 List에 값을 담기 위한 처리 -->
<input type="checkbox" id="chk_${i-1}" name="ptItems[${i-1 }].photoNo" value="${ptBean.photoNo }">
<input type="hidden" name="ptItems[${i-1}].imageURL" value="${ptBean.imageURL }">
			<!-- Command객체의 List처리 끝 -->
			</td>
			<!-- iplog -->
			<td>
			<a href="${pageContext.request.contextPath }/photo/photo/photoCountUpdate.do?ptNo=${ptBean.photoNo }&userid=${userid}&ip=<%=ip %>">
				<img src
				='<c:url value="/pt_images/${ptBean.imageURL }"/>' border="0" width="70">
			</a>
			</td>
			<!-- iplog 끝 -->
			<td>${ptBean.photoTitle}</td>
			<!-- 유저닉클릭시 유저홈 -->
			<td>
				<a href="${pageContext.request.contextPath }/user/user/userMain.do?userid=${mbBean.userid}"></a>
			${ptBean.uploader}</td>
			<td>${ptBean.viewCnt}
			</td>
		</tr>	
		</c:if>
	</c:forEach> 
	
	<!-- 반복 끝 -->
	<tr>
		<!-- 페이지 번호 [1][2][3][4][5][6][7][8] -->
		<td colspan="7" align="center">
		<c:forEach var="i" begin="${pb.firstPage }" end="${pb.lastPage }">
			<c:if test="${i<=pb.totalPage }">
				<c:if test="${i==pb.currentPage }">
					<span style="font-weight:bold;color:blue">
					${i}</span>
				</c:if>
				<c:if test="${i!=pb.currentPage }">
					<a href="javascript:getListByPage(${i})">
					[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		</td>
	</tr> 
	

</table> --%>
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
		

		
/* 	function checkedAll(){
		//전체 체크박스를 클릭하면 모든 체크박스가 체크되도록 하자
		//동일한 이름의 태그가 여러개 이면 배열로 인식
		var len = ${count};
		for(i=0;i<len;i++){
			document.getElementById("chk_"+i).checked = frmList.chkAll.checked;
		}//for
	}
	
	function searchEvent(){
		frmList.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmList.submit();
	}
	
	function checkedValidate(){
		var cnt=0;
		var len= ${count};
		for(i=0;i<len ;i++){
			if(document.getElementById("chk_"+i).checked){
				cnt++
				break;
			}//if
		}//for
		
		if(cnt==0){
			alert("처리하고 싶은 사진을 먼저 체크하세요");
			return false;
		}else{
			return true;
		}
	} */
	
	//value값을 넘겨줌
	   /* function searchCg(sel){
		var cgName = sel.value;				
		frmList.action */ 
		/*="<c:url value='/photo/photo/popular.do'/>";
		frmList.submit(); 
		} */   
				///백업
/* window.onload=function(){
	if(!document.frmList.cgName.value){
		document.frmList.cgName.value="카테고리 선택";
		}
	}; 
			
	$(".btn-group > .btn").click(function(){
	    $(".btn-group > .btn").removeClass("active");
	    $(this).addClass("active");
	});
				
	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="<c:url value='/photo/photo/popular.do'/>";
		frmPage.submit();
	} */
	
	function getListByPage(curPage){
		//페이지 번호를 클릭햇을 때 처리
		frmPage.currentPage.value=curPage;
		frmPage.action
		="<c:url value='/photo/photo/photoList.do'/>";
		frmPage.submit();
	}
</script>
 

<%@ include file="../../inc/mainBottom.jsp" %>