var replyOpen = false;
function replyAreaToggle() {
	if (replyOpen==false) {
		replyAreaShow();
	}
	else {
		replyAreaHide();
	}
}

function moreReply(num) {
	$(".moreReply"+num).show();
	$(".moreReplyBtn"+num).hide();
}

function replyAreaShow() {
	$.ajax(
			{
				type : "post",
				url : "./selectBoardReply.do",
				data : "board_no=" + board_no,
				async	: false,
				success : function (msg) {
					replyAreaWrite(msg);
				},
				error : function (request,status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
	$("#replyArea").show();
	replyOpen = true;
}

function replyAreaWrite(lists) {
	var output = new StringBuffer();
	var moreStart = 5;
	var moreEnd = moreStart + moreStep;
	var listsLength = lists.length;
	var moreStep = 5;
	
	$.each(lists, function(idx, item) {
		var depth = 20*item.br_depth;
		if (idx == moreStart) {
			output.append('<div class="moreReplyBtn'+ idx +'"><div style="height: 5px;"></div><div style="border-bottom: 1px solid #cccccc;"></div><div style="height: 5px;"></div>');
			
			if (idx+moreStep <= listsLength-1) {
				output.append('<input style="width: 100%;" class="btn btn-info" type="button" onclick="moreReply('+ idx +')" value="댓글 ' + moreStep + '개 더보기"/></div>');
			}
			else {
				output.append('<input style="width: 100%;" class="btn btn-info" type="button" onclick="moreReply('+ idx +')" value="댓글 '+ (listsLength%moreStep) +'개 더보기"/></div>');
			}
			output.append('<div class="moreReply'+ idx +'" style="display: none;">');
			moreStart += moreStep;
		}
		output.append('<div style="height: 5px;"></div><div style="border-bottom: 1px solid #cccccc;"></div><div style="height: 5px;"></div>');
		output.append('<table><tr>');
		if(item.br_depth != 0) {
			output.append('<td style="width: ');
			output.append(depth);
			output.append('px; text-align: right;"><img alt="reply" style="height: 20px;" src="./images/icon/reply.png" /></td>');
		}
		if (item.br_delflag == "Y") {
			output.append('<td rowspan="2"><div style="color: red; border: 1px solid #b3b3b3; text-align: center; border-radius: 5px; background: #f2f2f2;"><br/>삭제된 댓글입니다.<br/>&nbsp;</div></td><td style="width: 94px;"></td></tr><tr><td></td>');
			if(item.br_depth != 0) {
				output.append('<td>&nbsp;</td>');
			}
			output.append('</tr></table>');
		}
		else {
			output.append('<td rowspan="2" style="width: 100px;"><div style="text-align: center; border-radius: 8px;');
			if (item.mem_no == mem_no) {
				/* 현재 접속한 유저의 댓글 표시 */
				output.append('background: black; color: white; font-weight: bold; font-size: 15px;"><span style="font-size: 20px;">&nbsp;</span><br/>');
			}
			else if (item.mem_no == boardMem_no) {
				/* 원글 작성자의 댓글 표시 */
				output.append('background: gray; font-weight: bold; font-size: 15px; color: white;"><span style="font-size: 20px;">&nbsp;</span><br/>');
			}
			else {
				/* 일반 유저의 댓글 표시 */
				output.append('background: #f2f2f2; font-weight: bold; font-size: 15px;"><span style="font-size: 20px;">&nbsp;</span><br/>');
			}
			output.append(item.dept_name);
			output.append('<br/>');
			output.append(item.mem_name);
			output.append('<br/><span style="font-size: 20px;">&nbsp;</span></div></td><td style="width: 5px;" rowspan="2"><td><input type="text" value="');
			output.append(item.br_regdate);
			output.append('" readonly="readonly"/></td><td></td><td></td></tr><tr>');
			if(item.br_depth != 0) {
				output.append('<td></td>');
			}
			output.append('<td><textarea class="form-control" rows="3" readonly="readonly">');
			output.append(item.br_content);
			output.append('</textarea></td><td style="width: 50px;"><input class="btn btn-link" type="button" value="댓글" onclick="replyInputAreaToggle(' + idx + ')" /></td>');
			output.append('<td style="width: 40px;">');
				if (item.mem_no == mem_no || auth_no == 0) {
					output.append('<input class="btn btn-link" type="button" value="X" onclick="updateDelBoardReply('+ item.br_no +')"/>');
				}
			output.append('</td></tr></table>');
			output.append('<form action="./insertBrReply.do" method="post" id="frmInsertBrReply'+ idx +'">');
			output.append('<table class="replyInputArea" id="replyInputArea' + idx + '"><tr>');
			if(item.br_depth != 0) {
				output.append('<td style="width: ');
				output.append(depth+30);
				output.append('px; text-align: right;"><img alt="pencil" style="height: 30px;" src="./images/icon/pencil.png" /></td>');
			}
			else {
				output.append('<td style="width: 30px; text-align: right;"><img alt="pencil" style="height: 30px;" src="./images/icon/pencil.png" /></td>');
			}
			output.append('<td><input type="text" value="');
			output.append(dept_name + ' ');
			output.append(mem_name);
			output.append(' " readonly="readonly" />');
			output.append('</td><td></td><td></td></tr><tr><td></td><td colspan="2">');
			output.append('<textarea class="form-control" id="brContent' + idx + '" name="br_content" rows="3" placeholder="');
			output.append(item.dept_name + ' ');
			output.append(item.mem_name);
			output.append(' 님에게 댓글 쓰기">');
			output.append('</textarea></td><td style="width: 5px;"></td><td style="width: 90px;"><input class="btn btn-default" style="width: 89px; height: 50px;" type="button" value="댓글등록" onclick="insertBrReply(');
			output.append(idx);
			output.append(')" />');
			output.append('<input type="hidden" name="br_no" value="'+ item.br_no +'" />');
			output.append('<input type="hidden" name="board_no" value="'+ item.board_no +'" />');
			output.append('<input type="hidden" name="mem_no" value="'+ mem_no +'" />');
			output.append('<input type="hidden" name="dept_no" value="'+ dept_no +'" />');
			output.append('<input type="hidden" class="idx" name="idx" value="'+ (idx+1) +'" /></td>');
			output.append('</tr></table></form>');
		}
		if (idx == moreEnd || idx == listsLength-1) {
			output.append('</div>');
			mmoreEnd = moreStart + moreStep;
		}
	});
	$("#replyAreaList").html(output.toString());
	var idx = $(".idx").length;
	if (idx != 0) {
		var replyCnt = "&nbsp;<span class='badge'>" + idx +"</span>";
		$("#replyCnt").html(replyCnt);
	}
}

function replyAreaHide() {
	$("#replyArea").hide();
	replyOpen = false;
}

function replyInputAreaToggle(num) {
	$("#replyInputArea"+num).toggle();
	collapse(num);
}

function collapse(num){
   $('.replyInputArea').not('#replyInputArea'+num).each(function(){
      $(this).hide();
   });
}

function insertBoardReply() {
	$.ajax({
		url : "./insertBoardReply.do",
		type : "post",
		async : false,
 		data : $('#frmInsertBoardReply').serialize(),
		success : function (msg) {
			if (msg.isc==true) {
				replyAreaHide();
				replyAreaShow();
				$("#brContent").val("");
			}
			else {
				swal("댓글등록 실패!", "입력하신 댓글 등록이 실패했습니다.", "error");
			}
		},
		error : function (request,status,error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function insertBrReply(num) {
	$.ajax({
		url : "./insertBrReply.do",
		type : "post",
		async : false,
 		data : $('#frmInsertBrReply'+num).serialize(),
		success : function (msg) {
			if (msg.isc==true) {
				replyAreaHide();
				replyAreaShow();
				$("#brContent"+num).val("");
			}
			else {
				swal("댓글등록 실패!", "입력하신 댓글 등록이 실패했습니다.", "error");
			}
		},
		error : function (request,status,error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function updateDelBoardReply(num) {
	$.ajax({
		url : "./updateDelBoardReply.do",
		type : "post",
		async : false,
 		data : "br_no=" + num,
		success : function (msg) {
			if (msg.isc==true) {
				replyAreaHide();
				replyAreaShow();
			}
			else {
				swal("댓글삭제 실패!", "선택하신 댓글 삭제가 실패했습니다.", "error");
			}
		},
		error : function (request,status,error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}