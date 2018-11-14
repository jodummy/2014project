//폼에 데이터를 추가하기 위해 (파일을 전송하기 전 정보 유지)
var fd = new FormData();
//파일 중복 업로드 방지용 맵을 선언한다. 
var map = new Map();
//form 에 데이터 추가 시 동적으로 name 을 주기 위해 사용
var k = 0;
// fd에 포함된 파일이 있는지 카운트하기 위해 사용
var fileCnt = 0;

//업로드 할 파일을 제거할 때 수행되는 함수
function delFile(e){
   //tr을 삭제하기 위해
   var tr = $(e).parent().parent();
   //파일 이름을 받아오기 위해 string 변수 선언
   var formName = '';
   //선택한 창의 아이디가 파일의 form name이므로 아이디를 받아온다.
   formName = e.id;
   //form에서 데이터를 삭제한다.
   fd.delete(formName);
   fileCnt--;
   if (fileCnt >= 3) {
 	  dragAndDropDiv_height = $(".dragAndDropDiv").css("height");
 	  newHeight = parseInt(dragAndDropDiv_height) - 22 + "px";
 	  $(".dragAndDropDiv").css("height", newHeight);
 	 fileAreaAlign();
 	  
   }
   //맵에서도 올린 파일의 정보를 삭제한다.
   map.remove(formName);
   tr.remove();
   swal("파일 정보삭제!", "파일 정보삭제가 완료되었습니다.", "success");
}
//submit 버튼을 눌렀을 때 
function submitFile(){
   //추가적으로 보낼 파라미터가 있으면 formData에 넣어준다. 
   //예를들어 , 게시판의 경우 게시글 제목 , 게시글 내용 등등
   var board_notice = 'N';
   var checked = $('#board_notice').prop("checked");
   if (checked) {
	   board_notice = 'Y';
   }
   fd.append('ctgr_no', ctgr_no);
   fd.append('mem_no', mem_no);
   if (ctgr_no == 2) {
		var str = dept_no.charAt(0);
		if (str == 'T') {
			 fd.append('dept_no', dept_top);
		}
		else {
			fd.append('dept_no', dept_no);
		}
	}
   fd.append('board_notice', board_notice);
   fd.append('board_title',$('#board_title').val());
   fd.append('board_content', $('#editor').val());
   fd.append('board_no',$('#board_no').val());
   //ajax로 이루어진 파일 전송 함수를 수행시킨다.
	sendFileToServer(fd);
}

//파일 전송 함수이다.
function sendFileToServer(formData) {
   var uploadURL = fileUri;
   jQuery.ajax({
      type : "POST",
      url : uploadURL,
      data : formData,
      contentType : false,
      processData : false,
      cache : false,
      success : function(msg) {
    	  if (msg.isc==true) {
    		if (board_no == "") {
    			swal("등록 성공!", "작성하신 게시글 등록이 완료되었습니다.", "success");
    			location.href='./boardList.do?ctgr_no=' + ctgr_no + thisDept;
    		}
    		else {
    			swal("수정 성공!", "작성하신 게시글 수정이 완료되었습니다.", "success");
    			location.href='./boardDetail.do?ctgr_no='+ctgr_no+'&board_no='+board_no+'&rnum='+rnum+'&index='+index + thisDept;
    		}
    	  }
    	  else {
    		   swal("등록 실패!", "게시글 수정 및 파일 수정 오류입니다.", "error");
    	  }
      }
   });
}
$(document).ready(function() {
   var objDragAndDrop = $(".dragAndDropDiv");
   $(document).on("dragenter", ".dragAndDropDiv",
         function(e) {
            e.stopPropagation();
            e.preventDefault();
            $(this).css('border', '2px solid red');
         });
   $(document).on("dragover", ".dragAndDropDiv",
         function(e) {
            e.stopPropagation();
            e.preventDefault();
         });
   $(document).on("drop", ".dragAndDropDiv", function(e) {
      $(this).css('border', '2px solid #b3b3b3');
      //브라우저로 이동되는 이벤트를 방지하고 드랍 이벤트를 우선시 한다.
      e.preventDefault();
      //DROP 되는 위치에 들어온 파일 정보를 배열 형태로 받아온다.
      var files = e.originalEvent.dataTransfer.files;
      //DIV에 DROP 이벤트가 발생 했을 때 다음을 호출한다.
      handleFileUpload(files);
   });

   $(document).on('dragenter', function(e) {
      e.stopPropagation();
      e.preventDefault();
   });
   $(document).on('dragover', function(e) {
      e.stopPropagation();
      e.preventDefault();
      objDragAndDrop.css('border', '2px solid #b3b3b3');
   });
   $(document).on('drop', function(e) {
      e.stopPropagation();
      e.preventDefault();
   });
   
   function handleFileUpload(files) {
      //파일의 길이만큼 반복하며 formData에 셋팅해준다.
      var megaByte = 1024*1024;
      for (var i = 0; i < files.length; i++) {
         if(map.containsValue(files[i].name) == false && (files[i].size/megaByte) <= 20 ){
            fd.append('file'+k, files[i]);
            //파일 중복 업로드를 방지하기 위한 설정
            map.put('file'+k,files[i].name);
            // 파일 이름과 정보를 추가해준다.
            var fileList = new createFileList(); 
            fileList.setFileNameSize(files[i].name,files[i].size);
         }else{
            //중복되는 정보 확인 위해 콘솔에 찍음
            if((files[i].size/megaByte) > 20){
            	swal("등록 실패!", files[i].name+"은(는) \n 20메가 보다 커서 업로드 할 수 없습니다.", "error");
            }
            else{
            	swal("등록 실패!", files[i].name + "와(과) \n 같은 이름의 파일이 이미 추가되어 있습니다.", "error");
            }
         }
      }
   }
   
   function createFileList(obj) {
      var fileTable = $('#fileTable');
      this.inputFileInfo = $("<tr></tr>");
      this.filename = $("<td class='filename'></td>")
            .appendTo(this.inputFileInfo);
      this.size = $("<td class='filesize'></td>")
            .appendTo(this.inputFileInfo);
      this.fileCancelBtn = $("<td class='delBtn'>"
            +"<button class='btn btn-default btn-xs' id='file"+k+"' onclick='delFile(this)'>삭제</button>"
      +"</td>").appendTo(this.inputFileInfo);
      k++;
      fileCnt++;
      if (fileCnt > 3) {
    	  dragAndDropDiv_height = $(".dragAndDropDiv").css("height");
    	  newHeight = parseInt(dragAndDropDiv_height) + 22 + "px";
    	  $(".dragAndDropDiv").css("height", newHeight);
    	  fileAreaAlign()
      }
      fileTable.append(this.inputFileInfo);
      this.setFileNameSize = function(name, size) {
         var sizeStr = "";
         var sizeKB = size / 1024;
         if (parseInt(sizeKB) > 1024) {
            var sizeMB = sizeKB / 1024;
            sizeStr = sizeMB.toFixed(2) + " MB";
         } else {
            sizeStr = sizeKB.toFixed(2) + " KB";
         }
         //파일이름이 너무 길면 화면에 표시해주는 이름을 변경해준다.
         if(name.length > 70){
            name = name.substr(0,68)+"...";
         }
         this.filename.html(name);
         this.size.html(sizeStr);
      }
   }
});


function fileDownload(num) {
 	$('#frmFileDownload'+num).submit();
}

function deleteBoardFile(num, str) {
	swal({
		  title: "첨부파일 삭제!",
		  text: "삭제된 파일은 복구 할 수 없습니다. 파일을 정말 삭제하시겠습니까?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "삭제",
		  cancelButtonText: "취소",
		  closeOnConfirm: false,
		  closeOnCancel: false
		},
		function(isConfirm) {
			if (isConfirm) {
				$.ajax({
					url : "./deleteBoardFile.do",
					type : "post",
					async : false,
			 		data : "bf_no=" + num + "&bf_savenm=" + str,
					success : function (msg) {
						if (msg.isc==true) {
							fileAreaNone(num);
							swal("삭제 완료!", "선택한 파일의 삭제가 완료되었습니다.", "success");
						}
						else {
							swal("삭제 실패!", "선택하신 파일 삭제가 실패하였습니다.", "error");
						}
					},
					error : function (request,status,error) {
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			} else {
				swal("삭제 취소!", "삭제가 취소되었습니다.", "error");
			}
	});
}

function openFileArea() {
	fileAreaAlign()
	$("#fileArea").toggle();
}

function fileAreaNone(num) {
	$("#bfTr"+num).remove();
	$("#parentBfTr"+num).remove();
	if($("#bfTrs").find('tr').length == 0) {
		$("#fileListTable").hide();
		$('#fileNoneMsg').text("첨부파일이 없습니다.").css("color", "red");
		$('#parentFileListDiv').text("첨부파일이 없습니다.").css("color", "red");
	}
}

function fileAreaAlign() {
	var text_bg_font_size = $(".text_bg").css("font-size");
	var dragAndDropDiv_height = $(".dragAndDropDiv").css("height");
	var text_margin_top = (parseInt(dragAndDropDiv_height)/2)-(parseInt(text_bg_font_size));
	$(".text_bg").css("margin-top", text_margin_top);
}

$(document).ready(function() {
	fileAreaAlign();
});