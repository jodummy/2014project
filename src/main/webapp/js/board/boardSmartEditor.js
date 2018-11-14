
$(function(){
    //전역변수
    var obj = [];              
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "editor",
        sSkinURI: "./resources/editor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
    
    //전송버튼
    $("#savebutton").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대
        obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
        
        var board_title = $("#board_title").val();
        var board_content = $("#editor").val();
        var board_notice = "N";
        if ($("#board_notice").prop("checked")) {
        	board_notice = "Y";
        }
        if (title == "") {
        	if (title == board_title) {
        		swal("입력 확인!", "제목을 입력해주세요.", "error");
        	}
        	else if (content == board_content) {
        		swal("입력 확인!", "내용을 입력해주세요.", "error");
        	}
        	else {
        		if (fileCnt == 0) {
                	// 첨부한 파일이 없다면, 폼 submit 실행
                	$("#frmBoard").submit();
                }
                else {
                	submitFile();
                }
        	}
        }
        else {
        	if (board_title == title && board_content == content && board_notice == notice && fileCnt == 0) {
        		swal("입력 확인!", "수정된 내용이 없습니다.", "error");
        	}
        	else {
        		if (fileCnt == 0) {
                	// 첨부한 파일이 없다면, 폼 submit 실행
                	$("#frmBoard").submit();
                }
                else {
                	submitFile();
                }
        	}
        }
        
    });
    
 // 에디터 내용 초기화
    $("#resetContent").click(function() {
    	var checked = false;
    	var yn = '${boardDto.board_notice}';
    	if (yn == "Y") {
    		checked = true;
    	}
    	obj.getById["editor"].exec("SET_CONTENTS", [content]);
    	$("#board_notice").prop("checked", checked);
    	$("#board_title").val(title);
    });
});