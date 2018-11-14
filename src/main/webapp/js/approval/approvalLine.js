/**
 * 
 */

/*
 *  app_find_00 : 결재라인을 선택하여 부모창에 값을 뿌려주는 함수
 */
function selApproval000(){
	var selapp = $(':input[name=selapp]:radio:checked').val();
 	window.opener.document.getElementById("app_order_000").innerHTML="<input type='text' id='app0' name='app0' value='"+selapp+"'/><br><br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd0()\" />";
	self.close();    //자식창 닫기
}


function selApproval111(){
	var selapp = $(':input[name=selapp]:radio:checked').val();
	window.opener.document.getElementById("app_order_001").innerHTML="<input type='text' id='app1' name='app1' value='"+selapp+"'/><br><br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd1()\" />";
	
	self.close();    //자식창 닫기
}

/*
 * cham_search_00 : 참조할 때 여러 명을 가지고 부모 페이지에 뿌려주는 함수
 */
function sel(form) {
	var etc = document.getElementById("selcham");
	var str = "";
	for (var i = 0; i < etc.length; i++) {
		if (etc.options[i].selected == true) {
			str += etc.options[i].value +",";
		}
	}
	window.opener.document.getElementById("doc_reperence").value=str;
	self.close(); 
}

function detailViewApp0(){
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("app_order_000").innerHTML="<input type='text' id='app0' name='app0' value='"+option_text+"'/><br><br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd0()\" />";
	
	self.close();    //자식창 닫기
}

function detailViewApp1(){
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("app_order_001").innerHTML="<input type='text' id='app1' name='app1' value='"+option_text+"'/><br><br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd1()\" />";
	
	self.close();    //자식창 닫기
}

function detailView(){ 
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("doc_reperence").value=option_text;
	self.close();    //자식창 닫기
}


/*
 * doc_detail_App_00 : 
 */

function approval1(){	
	document.getElementById("demo").innerHTML ="<img src='./images/appStamp.jpg' width='60px'/>";
}

function approval2(){	
	document.getElementById("demo2").innerHTML ="<img src='./images/appStamp.jpg' width='60px'/>";
}

function appSave(){
	var yn = confirm("결재 상신 하시겠습니까?");
	if(yn){
		var sessionMemno = document.getElementById("session_memno").value;		//${memDto.mem_no}
		var doclist1memno = document.getElementById("doclist1_memno").value; 	//${doclist[1].mem_no}
		var doclist2memno = document.getElementById("doclist2_memno").value; 	//${doclist[2].mem_no} 
		var doc_mngno = document.getElementById("doc_mngno").value;
		doc_mngno= "doc_mngno=" + doc_mngno;
		var mem_no = 0;
		var order = 0;
		// 문서 번호를 어떻게 처리하지 늅뉴부ㅜ
		if(sessionMemno==doclist1memno){
			mem_no = document.getElementById("mem_no_1").value;
			order = 1;
		} else if(sessionMemno==doclist2memno){
			mem_no = document.getElementById("mem_no_2").value;
			order = 2;
		}
		
		mem_no="&mem_no="+mem_no;
		order="&order="+order;
		alert(doc_mngno+mem_no+order);
		$.ajax({
			type : 'post',
			url : './appProcess.do',  //url : './app2chaProcess.do',
			data : doc_mngno+mem_no+order,
			success : function(data) {
				alert("상신이 완료되었습니다.");
				opener.location.reload();
				window.close();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("상신 처리 실패");
			}
		});
	}
}

function appBan(){
	alert("반려 처리 중입니다.");
	
	var doc_mngno = document.getElementById("doc_mngno").value;
	doc_mngno= "doc_mngno=" + doc_mngno;
	//alert(doc_mngno);
	$.ajax({
		type : 'post',
		url : './appBanProcess.do',
		data : doc_mngno,
		success : function(data) {
			alert("반려가 완료되었습니다.");
			opener.location.reload();
			window.close();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("반려 처리 실패");
		}
	});
}

function printService(){
	alert("인쇄 서비스를 준비 중입니다.");
}

function clo(){
	opener.location.reload(); 
	self.close();
}

