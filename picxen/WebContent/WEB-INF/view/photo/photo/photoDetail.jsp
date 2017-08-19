<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/mainTop.jsp" %>
	
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css' />">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
	
</head>
<%-- 	<% //로그인 체크 여부
		String cmUserid = (String)session.getAttribute("userid");
		boolean cmIsLogin = false;
		if(cmUserid != null && !cmUserid.isEmpty()){
			cmIsLogin = true;
		}
	%>
 --%>
<script type="text/javascript">	
	function goPrNxt(obj){
		var prNxParams = $("form[name=dTailForm]").serialize();
		var preNxNo = obj.value;
		var nxData = prNxParams;
		var nxUrl = "${pageContext.request.contextPath}/photo/photoCountUpdate.do?ptNo="+preNxNo;
		$.ajax({
			type : "POST",
			url : nxUrl, 
			data : nxData,
			success:function(data){
				window.location.href="${pageContext.request.contextPath}/photo/photo/photoDetail.do?ptNo="+preNxNo+"&"+this.data
			}
		});
	};
	
 	function bgBtn(){
		var bgNo = frmBg.bgNo.value;
		alert("BGparameter=="+ bgNo);
		if(!bgNo){
			return false;
		}
		frmBg.action 
		="${pageContext.request.contextPath}/user/user/userBG.do";
		
		/* "${pageContext.request.contextPath}/user/user/userMain.do?userid=${mbBean.userid}"; */
		frmBg.submit();
	}

</script>

<body>
	<form name="frmBg" method="get">	
		<input type="hidden" name="userid" value="${userid}">
		<input type="hidden" name="bgNo" value="${ptBean.photoNo}">
		<input type="hidden" name="sort" value="${sort}">	
	</form>
	
	<form name="frmPt" method="post">
	
	<div style="/* border-bottom:solid 1px #f5f5f5; */  background-color:#000000;"><!-- 3 -->
	<!-- 1 -->
		<div style="">
		<!-- 왼쪽버튼 -->
			 <div class="lineH" style="position:absolute; vertical-align:middle; float:left;">
			 	<c:set var="tBean" value="${clist[totalCRecord-totalCRecord]}"/>
			 	<input type="hidden" value="${tBean.photoNo }">
				 <c:if test="${tBean.photoNo==ptNo }">
				 	<button type="button" class="btn btn-primary-outline disabled" style="background-color: transparent;" value="" onclick="goPrNxt()">
				  	<i class="fa fa-angle-left fa-3x" style="color:gray;"></i></button>
				  </c:if>
				 <c:if test="${tBean.photoNo!=ptNo }">
				 	<button type="button" class="btn btn-primary-outline" style="background-color: transparent;" value="${prNo }" onclick="goPrNxt(this)">
				 	<i class="fa fa-angle-left fa-3x" style="color:gray;"></i></button>
				 </c:if>
			</div>
				
		<!-- 오른쪽 버튼 -->
			<div class="lineH" style="position:absolute; right:0; margin-right:20px; vertical-align:middle; float:right;">
					<c:set var="tBean" value="${clist[totalCRecord-1]}"/><!-- lastPhoto  -->
					<input type="hidden" value="${tBean.photoNo}">
					    <c:if test="${tBean.photoNo==ptNo }">
					     <button type="button" class="btn btn-primary-outline disabled" style="background-color: transparent;" value="" onclick="goPrNxt()">
					  	    <i class="fa fa-angle-right fa-3x" style="color:gray; width:0px;"></i></button>
					    </c:if>
						<c:if test="${tBean.photoNo!=ptNo }">
						  <button type="button" class="btn btn-primary-outline" style="background-color: transparent; " value="${nxtNo }" onclick="goPrNxt(this)">
							<i class="fa fa-angle-right fa-3x" style="color:gray; width:0px;"></i></button>
						</c:if>
			</div>
		</div>
	
	<!-- 포토이미지 -->
	<!-- 2 -->
	 	<div id="contnr" style="">
	<!-- 3 -->	
			<div id="d_width" >
					<div id="d_pt_cnter" >
							<img id="ptHgt" src="${pageContext.request.contextPath }/pt_images/${ptBean.imageURL}">
					</div>
			</div>
		</div>
	</div>
	
	<!-- <div id="width1">
	</div> -->
	
	<!-- 아이콘 정보 -->
	
	<!-- pt info -->
		<div class="container" style="max-width:1300px;">
			<div id="ptinfo1" style="margin:15px 0 10px 0px; float:left; display: inline-block;">
				<div style="float:left; position:absolute;">
					<img src="${pageContext.request.contextPath}/my_icon/${mbBean.myIcon}" id ="icle" class="img-circle" style="">
				</div>
				
					<div style="/* float:left; */ /* margin-left:5px; */ /* width:100%; */ /* border:1px solid blue; */ ">
						<div style="font-size:18px; font-weight:bold; color:#575757; margin:0 0 8px 63px;">
							${mbBean.fName} ${mbBean.lName}
						</div>
						<div style="color:#575757; font-size:13px; font-weight:bold; /* width:100%; */ margin-left:63px; /* border:1px solid blue; */">
							<c:if test="${fn:length(ptBean.photoTitle) >= 29 }">
								<span id="ptTtle" style="font-size:16px; font-weight:bold; color:#575757;">
									${fn:substring(ptBean.photoTitle,0,28)}...
								</span>
							</c:if>
		 					<c:if test="${fn:length(ptBean.photoTitle) <= 28 }">
								<span id="ptTtle" style="font-size:16px; font-weight:bold; color:#575757;">
									${ptBean.photoTitle}
								</span>
							</c:if>
						</div>
					</div>
					
					<div >
						<div>
							<c:if test="${ptBean.description==null}">
							</c:if>
							<c:if test="${ptBean.description!=null}">
								<div style="margin:20px 10px 10px 63px; /* width:600px; */ color:#708090;">
									<h5 style="font-weight:bold;">DESCRIPTION</h5>
									<div style="">
										${ptBean.description}
									</div>
								</div>
							</c:if>
						</div>
						<div>
							<c:if test="${ptBean.tag==null}">
							</c:if>
							<c:if test="${ptBean.tag!=null}">
								<div style="margin:15px 10px 10px 63px; color:#708090;">
									<h5 style="font-weight:bold;">Tags</h5>
									<div style="">
										${ptBean.tag}
									</div>
								</div>
							</c:if>
						</div>
					</div>
			</div>
			
			<div id="ptinfo2" style=" margin:10px 0 10px 0px; display: inline-block; /* border:solid gray 1px; */ /* float:right; */"><!--  -->
				<div id="fo2" style="float:right; display: flex; align-items: center;">
					<!-- <div style=" float:left; "> -->
	  					<c:if test="${mbBean.userid == userid }">
							<div id="ptBgEdt" style="margin-right:12px;"> 
									<button type="button" class="btn btn-default btn-sm" id="bgPtNo" value="${ptBean.photoNo}" onclick="javascript:bgBtn()" 
										style="color:#575757;">배경 저장</button><br>
								
									<a type="button" class="btn btn-default btn-sm" href="${pageContext.request.contextPath }/user/user/setPhoto.do?userid=${userid}"  
										style="color:#575757; margin-top:4px;">사진 수정</a>
							</div>
						</c:if>
						
						<div style="display: inline-block; min-width:70px; margin-right:5px; text-align:center;">
							<span style="color:#708090; font-size:14px;">조회수</span><br>
							<span style="color:#708090; font-size:18px;">${ptBean.viewCnt }</span>
						</div>
						
						
						
						<div style="/* width:60px; */ /* font-weight:bold; */ display: inline-block; min-width:70px; margin-right:5px; text-align:center;/*  border:solid red 1px; */">
							<span style="color:#708090; font-size:14px;">좋아요</span><br>
							<span id="cngLikeCnt" style="color:#708090; font-size:18px;">${ptBean.likeCnt }</span>
						</div>
						<div id="takePt" style="/* width:60px; */ /* font-weight:bold; */ display: inline-block; min-width:70px; text-align:center; /* border:solid red 1px; */">
							<span style="color:#708090; font-size:14px;">퍼가요</span><br>
							<span id="cngFavCnt" style="color:#708090; font-size:18px;">${ptBean.faves }</span>
						</div>
						
	 					<div id="rankSr" style="margin:0px 10px 0 10px; /* display: inline-block; */ /* border:solid red 1px; */ /* text-align:center; */">
	 						<div id="rankScore" style="margin-right:2px; display: inline-block; color:#708090;">
									<%-- <fmt:formatNumber value="${ptBean.popular }" type="Number" pattern="###.#"/> --%>
									${ptBean.popular }
							</div>
							
							<div id="rslabel" style="margin-right:2px; /* display: inline-block; */">
								<span  style=" font-size:15px; color:#708090;">Pops</span>
							</div>
						</div>
						
					<!-- </div> -->
					
				<!-- LIKE  -->
					<div id ="liTaWrapper" style="">
						<c:if test="${mbBean.userid == userid }"> <!-- 작성자 -->
							<div id="li_ta_keBtn1" style=" margin-right:3px;  display: inline-block;">
								<button type="button" class="btn btn-info btn-lg disabled" style="width:54px; line-height:42px; vertical-align: middle;">
									<i class="fa fa-thumbs-up" style="font-size:20px;"></i></button>
							</div>
						</c:if>
						
						<c:if test="${mbBean.userid != userid }">
							<div id="li_ta_keBtn2" style=" margin-right:3px; display: inline-block;">
								<button type="button" id="idLike" data-btn="idLike" data-lBtn="like" class="btn btn-info btn-lg" style="width:54px; line-height:42px; vertical-align: middle;">
									<i class="fa fa-thumbs-up" style="font-size:20px; color:#FFFFFF; "></i></button>
							</div>
						</c:if>					
				
					<!-- HEART  -->	
						<c:if test="${mbBean.userid == userid }"> <!-- 작성자 -->
							<div id="li_ta_keBtn3" style="display: inline-block;">
								<button type="button" class="btn btn-danger btn-lg disabled" style="width:54px; line-height:42px; vertical-align: middle;">
								<i class="fa fa-heart" style="font-size:20px;"></i></button>
							</div>
						</c:if>
						
						<c:if test="${mbBean.userid != userid }">
							<div id="li_ta_keBtn4" style="display: inline-block;">
								<button type="button" id="idHeart" data-btn="idHeart" class="btn btn-danger btn-lg" style="width:54px; line-height:42px; vertical-align: middle;">
								<i class="fa fa-heart" style="font-size:20px; color:#FFFFFF;"></i></button>
							</div>
						</c:if>
					</div>	<!--like  -->
					
				</div><!-- align-items: center; -->
						
			</div> <!-- ptinfo2  -->
			
			<div id="miniSize" style="padding-left:63px; margin-bottom: 10px;">
			</div><!-- miniSize  -->
			
		</div><!--all wrap -->
	
	<!-- description -->
	
	</form>
	
	 <form name="dTailForm" method="POST">
	 	<input type="hidden" name="sort" value="${sort}">
		<input type="hidden" name="cgName" value="${cgName}">
	 </form>	
</body>


		<div id="comments" style="border-top:solid 1px #f5f5f5; /* margin-top:10px; */">
			<%@ include file="../../photo/comments/comment.jsp" %>
		</div><!-- comments -->

<script type="text/javascript" >

	$("#idLike, #idHeart").on('click', function(e){
		var btnUid = $('input[name=userid]').val();
		console.log(btnUid);
		if(btnUid == null || btnUid == ""){
			alert("로그인 하세요.")
			window.location.href = "${pageContext.request.contextPath}/member/login.do"
			return false;
		};
		
		var btnId = $(this).data("btn");
		if(btnId == "idLike"){
			var upUrl = "${pageContext.request.contextPath}/photo/photo/photoLikeUpdate.do"
					$('#idLike').attr('disabled', 'true');		
		};
		if(btnId == "idHeart"){
			var upUrl = "${pageContext.request.contextPath}/photo/faves/favesAdd.do"
					$('#idHeart').attr('disabled', 'true');
		};
  		$.ajax({
			url : upUrl,
			type:'POST',
			data:{"ptNo" : "<c:out value='${ptNo}'/>"},
			success:function(data){
					var dataPop = $(data).filter('#popScore').val();
					var dataLikeCnt = $(data).filter('#likeCount').val();
					var dataFvs = $(data).filter('#favCount').val();
					$('#rankScore').text(dataPop);
					$('#cngLikeCnt').text(dataLikeCnt);
					$('#cngFavCnt').text(dataFvs);
			}
		});
	});

	
	$(window).load(function(){
		var ckptNo={"ptNo":"<c:out value='${ptNo}'/>"} ;
		var uid = "<c:out value='${userid}'/>";
		
		 $.ajax({
			url:"${pageContext.request.contextPath}/photo/faves/faveList.do",
			type:'GET',
			data:ckptNo,
			success:function(data){
				var emFl = $(data).find('#uid').val();
				var likeChk = $(data).find('#likeuid').val();
					if(emFl != null && emFl == uid){	//fave
						if($(window).width() >= 1181)
							$('#idHeart').attr('class', 'btn btn-danger btn-lg disabled');
						if($(window).width() < 1181)
							$('#idHeart').attr('class', 'btn btn-danger btn-sm disabled');
					}
					
					if(likeChk != null && likeChk == uid){
						if($(window).width() >= 1181)
							$('#idLike').attr('class', 'btn btn-info btn-lg disabled');
						if($(window).width() < 1181)
							$('#idLike').attr('class', 'btn btn-info btn-sm disabled');						
					}
					
			}
		});
	}); //ptno 애정컨트롤러 파라미터
	
/* width테스트  */	
$(window).load(function(){
	var width = $(window).width();
	$('#width1').html(width);
	$(window).resize(function(){
		var width = $(window).width();
		$('#width1').html(width);
	});
}); 
	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 752){
				$('#d_width').css({"height":"700px"});
				$('.lineH').css({"line-height":"700px"});
				$('#icle').css({"width":"55px", "height":"55px"});
				$('#ptHgt').css({"max-height":"700px"});
			}
			if(diSize < 752){
				var dCrtion = 752 - $(window).width();
				var dHght = 600-dCrtion;
				$('#d_width').css({"height":dHght+"px"});
				$('.lineH').css({"line-height":dHght+"px"});
				$('#icle').css({"width":"44px", "height":"44px"});
				$('#ptHgt').css({"max-height":dHght+"px"});
			}
		}).resize();
	});
	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 1181){
				$('#ptBgEdt').children('button').text('배경 저장');
				$('#ptBgEdt').children('a').text('사진 수정');
				$('#liTaWrapper button').removeClass('btn-sm').addClass('btn-lg');
				
				$('#li_ta_keBtn1, #li_ta_keBtn2, #li_ta_keBtn3, #li_ta_keBtn4').children('button').css({'line-height':'42px', 'height':''});
				$('#li_ta_keBtn3, #li_ta_keBtn4').children('button').css({'margin-top':''});
				$('#li_ta_keBtn3, #li_ta_keBtn4').css({'display':'inline-block'});
			}
			if(diSize < 1181){
				$('#ptBgEdt').children('button').text('배경');
				$('#ptBgEdt').children('a').text('수정');
				$('#liTaWrapper button').removeClass('btn-lg').addClass('btn-sm');
				
				$('#li_ta_keBtn1, #li_ta_keBtn2, #li_ta_keBtn3, #li_ta_keBtn4').children('button').css({'line-height':'', 'height':'29px'});
				$('#li_ta_keBtn3, #li_ta_keBtn4').children('button').css({'margin-top':'4px'});
				$('#li_ta_keBtn3, #li_ta_keBtn4').css({'display':''});
			}
		}).resize();
	});
	
	
	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 973){
				$('#rankScore').css({'font-size':'35px'});
				$('#rslabel').css({'display':'inline-block'});
				$('#rankScore').insertBefore('#rslabel');
			}
			if(diSize < 973){
				$('#rankScore').css({'font-size':'18px'});
				$('#rslabel').css({'display':'','min-width':'50px'});
				$('#rslabel').insertBefore('#rankScore');				
			}
		}).resize();
	});

	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 850){
				$('#ptinfo1').css({'width':'50%'});
				$('#ptinfo2').css({'width':'50%'});
				$('#ptinfo2').children('div').css({'float':'right', 'padding-left':""});
			}
			if(diSize < 850){
				$('#ptinfo1').css({'width':'100%'});
				$('#ptinfo2').css({'width':'100%'});
				$('#ptinfo2').children('div').css({'float':'left', 'padding-left':"60px"});			
			}
		}).resize();
	});
	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 495){
				$('#ptBgEdt').prependTo('#fo2');				
				$('#ptBgEdt').css({'float':'', 'margin-right': '12px'});
				$('#rankSr').insertAfter('#takePt');
				$('#rankSr').css('display','');
				$('#liTaWrapper').insertAfter('#rankSr');
				$('#liTaWrapper').css('display','');
			}
			if(diSize < 495){
				$('#ptBgEdt').prependTo('#miniSize');
				$('#ptBgEdt').css({'float':'left', 'margin-right': '32px'});
				$('#rankSr').prependTo('#miniSize');
				$('#rankSr').css('display','inline-block');
				$('#liTaWrapper').insertAfter('#rankSr');
				$('#liTaWrapper').css('display','inline-block');
			}
		}).resize();
	});
	
</script>
	

<%@ include file="../../inc/mainBottom.jsp" %>