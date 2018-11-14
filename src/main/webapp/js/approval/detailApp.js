/**
 * 
 */
$(function(){
	if(${memDto.mem_no}==${doclist[1].mem_no}){
		if(${doclist[1].app_status}==2){
			document.getElementById("demo00").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
			document.getElementById("demo").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
			document.getElementById("demo2").innerHTML ="<img src='./images/approval/wait.jpg' width='60px'/>";
			} else{
			document.getElementById("demo00").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
		 	document.getElementById("demo").innerHTML ="<button id=\"bnt1\" onclick=\"approval1()\" class=\"btn btn-primary\">결재하기</button>";
		 	document.getElementById("demo2").innerHTML ="<img src='./images/approval/wait.jpg' width='60px'/>";
			}
	} else if(${memDto.mem_no}==${doclist[2].mem_no}){
	//	alert("실패하였습니다.");
		if(${doclist[1].app_status}==2){
			document.getElementById("demo00").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
			document.getElementById("demo").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
			document.getElementById("demo2").innerHTML ="<button id=\"bnt2\" onclick=\"approval2()\" class=\"btn btn-primary\">결재하기</button>";
		} else if(${doclist[1].app_status}==1) {
			document.getElementById("demo00").innerHTML ="<img src='./images/approval/appStamp.jpg' width='60px'/>";
			document.getElementById("demo").innerHTML ="<img src='./images/approval/wait.jpg' width='60px'/>";
			document.getElementById("demo2").innerHTML ="<img src='./images/approval/wait.jpg' width='60px'/>";
		}
	}
});


//replyForm
function replyService(){
	var theForm = document.replyForm;
	var theData = $("#replyForm").serialize();

    if(theForm.ar_content.value==""){
		alert("답글 내용을 입력해주세요.");
	}else{
		$.ajax({
			type : 'post',
			url : './replyProcess.do',
			data : theData,
			success : function(data) {
				alert("성공");
				window.location.reload(true);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("페이지에 오류가 발생했습니다.");
			}
		});
	}
}

function replydelete(ar_no){
	var yn = confirm("삭제하시겠습니까?");
	alert(ar_no);
	var doc_mngno = ${doclist[0].doc_mngno};
	alert(doc_mngno);
	
	if(yn){
		$.ajax({
			type : 'post',
			url : './replydeleteProcess.do',
			data : "ar_no="+ar_no+"&doc_mngno="+doc_mngno,
			success : function(data) {
				alert("성공");
				window.location.reload(true);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("페이지에 오류가 발생했습니다.");
			}
		});
	}
}