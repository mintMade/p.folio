// faveList.js

var fSize = $('input[name="faveSize"]').val(); //faveSzie
var lSize = $('input[name="likeSize"]').val(); //like리스트
	$(document).ready( function(){ //faveList, likeList > 0
		var uid = $('#uid').val();//color option for heart.
 		if(fSize != 0){
			var fName = $('#fUName_1').val();
			$("#LFName").html(fName);
			$("#LFName1").html("님 외에");	
			$("#LFName2").html(fSize-1+"명");
			/* 찍었을 경우 불켜짐*/
			if(uid != null ){
				$("#hrtLavel").css("color", "#CC0000");
			};
		}else if(fSize == 0 && $('#linId').val() == $('#uldr').val()){ //업로더 -> 0명, 업로더 아닐경우 텍스트
			$("#LFName").html("0 명");
		}else if(fSize == 0 && $('#linId').val() != $('#uldr').val()){
			$("#LFName").html("가장 먼저 하트를 주세요!");
		};
	});
	
	/*var lSize = $('#likeSize').val(); //likeSize
*/	var luid = $('#likeuid').val(); //color option for thumbs.
	
	if(lSize != 0){
		var lName = $('#lUName_1').val();
		$("#LLName").html(lName);
		$("#LLName1").html("님 외에");	
		$("#LLName2").html(lSize-1+"명");
		if(luid != null){
			$("#thmbLavel").css("color", "#00A5FF");
		};
	}else if(lSize == 0 && $('#linId').val() == $('#uldr').val()){
		$("#LLName").html("0 명");
	}else if(lSize == 0 && $('#linId').val() != $('#uldr').val()){
		$("#LLName").html("가장 먼저 좋아요를 주세요!");
	};

	
	$(window).load(function(){
		var Greetings = ["HI", "hi", "안녕!", "방가워!", "ㅋㅋㅋ" ];
		var ranGrts =[];
		var cnt = 0;
		for(var i=0; i < lSize; i++ ){
			cnt = cnt + 1 
			var ids = $('[id="lf_'+cnt+'"]').attr("title");
			ranGrts.push(Greetings[Math.floor(Math.random()*Greetings.length)]);
			$('[id="lf_'+cnt+'"]').attr("title", ids+":"+ranGrts[i]);
			/* console.log(ids+":"+ranGrts[i]); */
		}
		var cntf = 0;
		for(var i=0; i < fSize; i++ ){
			cntf = cntf + 1 
			var ids = $('[id="ff_'+cntf+'"]').attr("title");
			ranGrts.push(Greetings[Math.floor(Math.random()*Greetings.length)]);
			$('[id="ff_'+cntf+'"]').attr("title", ids+":"+ranGrts[i]);
			/* console.log(ids+":"+ranGrts[i]); */
		}
		
		$('[data-toggle="like-fave-tooltip"]').tooltip();
	});
	