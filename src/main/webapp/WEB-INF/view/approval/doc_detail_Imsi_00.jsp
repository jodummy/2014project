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
</head>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<script src="./js/common/map.js"></script>
<script src="./js/approval/dragDropFile.js"></script>

<script type="text/javascript" src="js/approval/detailApp.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<!-- <script src="js/approval/approvalLine.js"></script> -->

<script type="text/javascript"
	src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<!-- <link rel="stylesheet" href="./css/board/dragDropFile.css"> -->
<link rel="stylesheet" type="text/css" href="css/approval/docWrite.css">
<link rel="stylesheet" type="text/css" href="css/approval/list.css">
<script type="text/javascript"
	src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script>
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
	});
</script>
<style>
.backslash {
  background: url('http://cdn.zetawiki.com/png/backslash.png');
	background-size: 100% 100%;
}
</style>
<body>
	<div class="container">
		<h1>임시 보관 문서</h1>
		<table class="table table-bordered">
			<tr>
				<td colspan="4" ><input type="button" value="종료" class="btn btn-primary" onclick="clo()" />
				<input type="button" class="btn btn-primary" value="수정"
					onclick="location.href='./docmodifyimsi00.do?doc_mngno=${docOne.doc_mngno}'" /></td>
			</tr>

			<form name="approvalForm" id="approvalForm">
				<tr >
					<td colspan="2"><input type="text" id="doc_Template_title"
						name="doc_Template_title" readonly="readonly" value="${docOne.temp_no}" /></td>
					<td colspan="2" style="height:7%">
					<table class="table table-bordered center">
							<tr>
								<td style="width:33%;">담당자</td>
								<td style="width:33%;">결재자</td>
								<td style="width:33%;">책임자</td>
							</tr>
							<tr>
								<td style="height:80%"><p id="app_order_002" >
										<strong id='app2' >${memDto.mem_name}</strong><br>
									</p></td>
								<td class='backslash'><p id="app_order_001" >
									</p> <br></td>
								<td class='backslash'><p id="app_order_000" >
									 </p> <br></td>
							</tr>
						</table>
					</td>
				</tr>
			</form>




			<form name="documentForm" id="documentForm">
				<tr>
					<td>문서번호</td>
					<td colspan="3"><input type="text" id="doc_no" name="doc_no"
						value="${docOne.doc_no}" readonly="readonly"></td>
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
			<td style="width: 17%;">작성일</td>
					<td style="width: 33%;"><input type="text" id="doc_regdate" name="doc_regdate"
						value="${docOne.doc_regdate}" readonly="readonly"></td>
					<td style="width: 17%;">종료일</td>
					<td style="width: 33%;"><input type="text" id="doc_lastdate" name="doc_lastdate"
						value="${docOne.doc_lastdate}" readonly="readonly"></td>
		</tr>
				<tr>
					<td>참조</td>
					<td colspan="3">
						<input type="text" id="doc_reperence" name="doc_reperence" value="" size="56" readonly="readonly" /> 
						<input type="button" value="검색" onclick="chamjosearch()" />
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" id="doc_title"
						name="doc_title" value="${docOne.doc_title}" readonly="readonly" size="65"></td>
				</tr>
				<tr>
					<td colspan="4"><textarea name="editor" id="editor" rows="10"
							cols="100" style="width:766px; height:100%;">${docOne.doc_content}</textarea></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>