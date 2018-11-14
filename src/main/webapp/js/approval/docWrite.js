function gian() {
	window.open('./docwritegian01.do?temp_no=1', '타겟이름',
					'scrollbars=yes,toolbar=yes,resizable=yes,width=900,height=680,right=150,top=0');
}
function vacation() {
	window.open('./docwritev02.do?temp_no=2', '타겟이름',
					'scrollbars=yes,toolbar=yes,resizable=yes,width=900,height=680,right=150,top=0');
}
function pay() {
	window.open('./docwritepay03.do?temp_no=3', '타겟이름',
					'scrollbars=yes,toolbar=yes,resizable=yes,width=900,height=680,right=150,top=0');
}
function etc() {
	window.open('./docwriteetc00.do?temp_no=4', '타겟이름',
					'scrollbars=yes,toolbar=yes,resizable=yes,width=900,height=680,right=150,top=0');
}

function imsisave() {
	var doc_status="0";
	var hiddenForm = $('form[name=hiddenForm]').serialize();
	var queryString = $('form[name=documentForm]').serialize();
	var allis="temp_no="+temp_no+"&mem_no="+mem_no+"&doc_status="+doc_status;
	var youth=hiddenForm+"&"+allis+"&"+queryString;
		alert(hiddenForm);
	$.ajax({
		type:'post',
		url:'./imsiSaveProcess.do',
		data: youth,
		success:function(data){
			alert("테스트");
			location.href="./test.do";
		},
		error:function(jqXHR, textStatus, errorThrown){
            alert("에러 발생");
        }
	});
}

$(function() {
	// 전역변수
	var obj = [];
	// 스마트에디터 프레임생성
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : obj,
		elPlaceHolder : "editor",
		sSkinURI : "./resources/editor/SmartEditor2Skin.html",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,
		}
	});

	// 전송버튼
	$("#savebutton").click(function() {
		theForm = document.documentForm;
		
		// 참조값을 저장하여 처리하여 주기 위한 과정 중에 있달까
		var doc_reperence = $('#doc_reperence').val().split(" ");
	
		// 작성일과 종료일을 비교해주기 위해 데이터 값을 처리
		var doc_regdate = $('#doc_regdate').val();
        var doc_lastdate = $('#doc_lastdate').val();
        var startArray = doc_regdate.split('-');
        var endArray = doc_lastdate.split('-');   
	    var start_date = new Date(startArray[0], startArray[1], startArray[2]);
		var end_date = new Date(endArray[0], endArray[1], endArray[2]);
		
	    if(start_date.getTime() > end_date.getTime()) {
            alert("종료날짜보다 시작날짜가 작아야합니다.");
            return false;
	    }else if(theForm.doc_lastdate.value==""){
			alert("종료일을 지정해주세요.");
		}else if(theForm.doc_title.value==""){
			alert("제목을 입력해주세요.");
		}else{
			obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
			var doc_status = "2";
			var editor = $('#editor').val();
			var documentForm = $('form[name=documentForm]').serialize();
			$.ajax({
				type : 'post',
				url : './saveProcess.do',
				data : documentForm + "&editor="+ editor+"&doc_status="+ doc_status,
				success : function(data) {
					alert("결재상신이 완료되었습니다. 감사합니다.");
					opener.location.href = "./doclistIngMine00.do";
					window.close();
				},
				error : function(textStatus, errorThrown) {
					alert("결재라인을 선택해주세요.");
				}
			});	
		}
	});

	$("#imsisave").click(function() {
		theForm = document.documentForm;
		
		var doc_reperence = $('#doc_reperence').val().split(" ");
		
		// 작성일과 종료일을 비교해주기 위해 데이터 값을 처리
		var doc_regdate = $('#doc_regdate').val();
        var doc_lastdate = $('#doc_lastdate').val();
        var startArray = doc_regdate.split('-');
        var endArray = doc_lastdate.split('-');   
	    var start_date = new Date(startArray[0], startArray[1], startArray[2]);
		var end_date = new Date(endArray[0], endArray[1], endArray[2]);

	    if(start_date.getTime() > end_date.getTime()) {
            alert("종료날짜보다 시작날짜가 작아야합니다.");
            return false;
	    }else if(theForm.doc_lastdate.value==""){
			alert("종료일을 지정해주세요.");
		}else if(theForm.doc_title.value==""){
			alert("제목을 입력해주세요.");
		}else{
			obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
			var editor = $('#editor').val();

			var doc_status = "0";
			var documentForm = $('form[name=documentForm]').serialize();

			$.ajax({
				type : 'post',
				url : './imsiSaveProcess.do',
				data : documentForm + "&editor="+ editor+"&doc_status="+ doc_status,
				success : function(data) {
					alert("임시 저장되었습니다. 임시문서함에서 확인 가능합니다.\n [임시 저장시, 결재라인은 저장되지 않습니다.]");
					opener.location.href = "./doclistimsiMine00.do";
					window.close();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("잘못된 값입니다. 문서 내용을 확인해주세요.");
				}
			});
		}
	});
});


function applineadd0() {
	var myWindow = window.open("./appfind00.do", "", "width=600, height=500");
}
function applineadd1() {
	var myWindow = window.open("./appfind001.do", "", "width=600, height=500");
}
function chamjosearch(){
	var myWindow = window.open("./chamsearch00.do", "", "width=420, height=260");
}