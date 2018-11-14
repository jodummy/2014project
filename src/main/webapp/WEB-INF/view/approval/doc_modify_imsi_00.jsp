<%@page import="ya.rain.bow.dtos.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문서작성/기타양식 선택 시</title>
<link rel="stylesheet" type="text/css" href="css/approval/docWrite.css">
</head>
<script type="text/javascript"
	src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="js/common/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		//전역변수
		var obj = [];
		//스마트에디터 프레임생성
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

		//전송버튼
		$("#savebutton").click(function() {
			theForm = document.documentForm;
			
			// 작성일과  종료일을 비교해주기 위해 데이터 값을 처리
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
				var app1 = $('#app1').val();
				var app0 = $('#app0').val();
				var approvalForm = $('form[name=approvalForm]').serialize();
				var documentForm = $('form[name=documentForm]').serialize();
				$.ajax({
					type : 'post',
					url : './saveProcessAgain.do',
					data : approvalForm + "&" + documentForm + "&editor="+ editor+ "&app1="+ app1+ "&app0="+ app0+"&doc_status="+ doc_status,
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
					url : './imsiSaveProcessAgain.do',
					data : documentForm + "&editor="+ editor+"&doc_status="+ doc_status,
					success : function(data) {
						alert("임시 저장되었습니다. 임시문서함에서 확인 가능합니다.\n [임시 저장시, 결재라인은 저장되지 않습니다.]");
						opener.location.href = "./doclistimsiMine00.do";
						window.close();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("결재라인을 선택해주세요.");
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
</script>
<body>
	<div width="700px">
		<h1>임시 보관 문서</h1>
		<table>
			<tr>
					<td colspan="4"><input type="button" value="결재상신"
						id="savebutton" /> <input type="button" value="임시저장"
						id="imsisave" /></td>
				</tr>

			<form name="approvalForm" id="approvalForm">
				<tr>
					<td colspan="2"><input type="text" id="doc_Template_title"
						name="doc_Template_title" readonly="readonly" value="상신 후 자동 부여" /></td>
					<td colspan="2">
						<table style="width: 310px;">
							<tr>
								<td>담당자</td>
								<td>결재자</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td><p id="app_order_002" name="app_order_002">
										<input type='text' readonly="readonly" id='app2' name='app2' value='${memDto.mem_name}' /><br>
										<br>
									</p></td>
								<td><p id="app_order_001" name="app_order_001">
										<br> <input type="button" value="결재라인 추가"
											onclick="applineadd1()" />
									</p> <br></td>
								<td><p id="app_order_000" name="app_order_000">
										<br> <input type="button" value="결재라인 추가"
											onclick="applineadd0()" />
									</p> <br></td>
							</tr>
						</table>
					</td>
				</tr>
			</form>




			<form name="documentForm" id="documentForm">
				<tr>
					<td>문서번호</td>
					<td colspan="3"><input type="text" id="doc_no" name="doc_no" value="${docOne.doc_no}" readonly="readonly">
					<input type="hidden" id="doc_mngno" name="doc_mngno" value="${docOne.doc_mngno}">
					<input type="hidden" id="temp_no" name="temp_no" value="${docOne.temp_no}">
					<input type="hidden" id="mem_no" name="mem_no" value="${docOne.mem_no}"></td>
				</tr>
				<tr>
					<td>기안부서</td>
					<td colspan="3"><input type="text" id="doc_deptnm"
						name="doc_deptnm" value="${docOne.doc_deptnm}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>기안자</td>
					<td colspan="3"><input type="text" id="doc_writer"
						name="doc_writer" value="${docOne.doc_writer}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><input type="text" id="doc_regdate" name="doc_regdate"
						value="${docOne.doc_regdate}" readonly="readonly"></td>
					<td>종료일</td>
					<td><input type="date" id="doc_lastdate" name="doc_lastdate" value="${docOne.doc_lastdate}"></td>
				</tr>
				<tr>
					<td>참조</td>
					<td colspan="3">
						<input type="text" id="doc_reperence" name="doc_reperence" value="${docOne.doc_reperence}" size="56" readonly="readonly" /> 
						<input type="button" value="검색" onclick="chamjosearch()" />
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" id="doc_title"
						name="doc_title" value="${docOne.doc_title}" size="65"></td>
				</tr>
				<tr>
					<td colspan="4"><textarea name="editor" id="editor" rows="10"
							cols="100" style="width: 766px; height: 412px;"  >${docOne.doc_content}</textarea></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>