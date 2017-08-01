<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../inc/logOutHomeTop.jsp" %>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js">
	</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainstyle.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

</head>

<!-- 회원가입 형식 변경 -->

<body class="lginBG" style="overflow-x:hidden; overflow-y:auto;">
<div class="loginBox" id="loginBox" style="width:400px;">
	
<form class="form-horizontal" name="frm1" id=submitJoin method="post" action="${pageContext.request.contextPath}/member/agreement.do" >
		<div class="registBoxText" style="">
			<h4>가입하기</h4>
		</div>

	<div class="container" style="/* padding: 0 30px 30px 30px; */" >
		<div class="form-group" style=" text-align:center;">
			<div class="col-xs-10">
				<input type="email" class="form-control" id="agrChkIc_off" name="email" placeholder="이메일">
			</div>
<!-- 		</div> -->

			<div class="col-xs-10">
				<input type="text" class="form-control" id="agrChkIc_off" name="userid" value="${cookie.ck_userid.value}" placeholder="유저 아이디" >
			</div>
<!-- chkId -->

				<div id="chkUid" style="color:blue;">
				</div>

			<div class="col-xs-10">
				<input type="text" class="form-control" id="agrChkIc_off" name="fName" placeholder="성">
			</div>

			<div class="col-xs-10">
				<input type="text" class="form-control" id="agrChkIc_off" name="lName" placeholder="이름">
			</div>

			<div class="col-xs-10">
				<input type="password" class="form-control" id="agrChkIc_off" name="pwd" onkeydown="enterProc()" placeholder="비밀번호 (6자 이상)">
			</div>

				<div id ="errors" class="form-group col-xs-10">
		     	</div>

			<div class="col-xs-10">
				<input type="button" class="btn btn-logi" style="color:#ffffff;" value="완료" onclick="send()">

			</div>

		</div>
	</div>
</form>
</div>	

</body>


<script type="text/javascript">
	
	/* email */
	$('input[name=email]').on('keyup', function(){
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			var mailChk = $('input[name=email]').val();

				if (mailChk.length == 0 || !re.test(mailChk)) {
					$('input[name=email]').attr('id', 'agrChkIc_off');
					return;
				}
				if (mailChk.length > 0 || re.test(mailChk)) {
					var chkMail = {"email": mailChk};		
					
 					$.ajax({
						url:"${pageContext.request.contextPath}/member/checkId.do",	
						type:'GET',
						data:chkMail,
						success:function(data){
							var emFlg = $(data).filter('#emlFlg').val();
							
							if(emFlg == "201"){
								$('input[name=email]').attr('id', 'agrChkIc_off');
							}
							
							if(emFlg == "200"){
								$('input[name=email]').attr('id', 'agrChkIc_on');
							}
							
						}
					});
				}
	});
	
	
	/* idCheck for sighUp */
	$('input[name=userid]').on('keyup', function(){				
		var uid = this.value;
 		var g = {"userid":uid};
 		if(uid)  {
 			
 			if(uid.indexOf(' ') >=0 || uid.length <= 4 ||  uid.length >= 20){
				$('input[name=userid]').attr('id', 'agrChkIc_off');
				return;
			}
			
 			$.ajax({
				url:"${pageContext.request.contextPath}/member/checkId.do",
				type:'POST',
				data:g,
				success:function(data){
					var usid = $(data).filter('#usrFlg').val();
					/* $("#idChk").val($.trim(data)); */
					
					if(usid == "101"){
						$('input[name=userid]').attr('id', 'agrChkIc_off');
					}
					
					if(usid == "100"){
						$('input[name=userid]').attr('id', 'agrChkIc_on');
					}
				}
			});
		}
	});
	
	/*Nick Name  */
	$('input[name=fName]').on('keyup', function(){
		var fname = this.value;
		if(fname.indexOf(' ') >=0 || fname.length <= 2 || fname.length >= 13){
			$('input[name=fName]').attr('id', 'agrChkIc_off');
			
		}else{
			$('input[name=fName]').attr('id', 'agrChkIc_on');
		};
	});
	
	$('input[name=lName]').on('keyup', function(){
		var lname = this.value;
		if(lname.indexOf(' ') >=0 || lname.length <= 2 || lname.length >= 13){
			$('input[name=lName]').attr('id', 'agrChkIc_off');
			
		}else{
			$('input[name=lName]').attr('id', 'agrChkIc_on');
		};
	});
	
	$('input[name=pwd]').on('keyup', function(){
		var pw = this.value;
		if(pw.indexOf(' ') >=0 || pw.length <= 5 || pw.length >= 25){
			$('input[name=pwd]').attr('id', 'agrChkIc_off');
		}else{
			$('input[name=pwd]').attr('id', 'agrChkIc_on');
		};
	});
	
	
	$('.btn-logi').on('click', function(){
		var idCnt = $('input[id=agrChkIc_off]').length;
			//체크 비활성 아이콘이 1개이상
			if(idCnt > 0){ 
				alert("양식을 모두 작성해 주십시오.");
					$('.loginBox').find('#agrChkIc_off').focus();
			};
			//체크 비활성 아이콘이 0개 : 모두체크
			if(idCnt == 0){
				$('form').submit();
			}; 
	});
	
	$(window).load(function(){
		$(window).resize(function(){
			var diSize = $(window).width();
			if(diSize >= 495){
				$('.loginBox, .registBoxText').css({'width':'400px','margin-top':'120px'});
			}
			if(diSize < 495){
				$('.loginBox, .registBoxText').css({'width':'300px','margin-top':'20px'});
			}
		}).resize();
	});
	
</script>
<%@ include file="../inc/mainBottom.jsp" %>