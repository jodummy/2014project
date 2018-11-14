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
<script type="text/javascript"
	src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="js/common/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<script src="js/approval/approvalLine.js"></script>
<script type="text/javascript" src="js/approval/docWrite.js"></script>

<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<!-- <link rel="stylesheet" href="./css/board/dragDropFile.css"> -->
<link rel="stylesheet" type="text/css" href="css/approval/docWrite.css">

<body>
	<div class="container">
		<h1>기타 양식</h1>
		<form name="documentForm" id="documentForm">
			<input type="hidden" id="doc_mngno" name="doc_mngno"
				value="${doc_mngno}" /> <input type="hidden" id="temp_no"
				name="temp_no" value='${temp_no}' /> <input type="hidden"
				id="mem_no" name="mem_no" value='${memDto.mem_no}' />
			<table class="table table-bordered">
				<tr>
					<td colspan="4" style="text-align:right;"> 
					<input type="button" value="임시저장" class="btn btn-primary" id="imsisave" /> 
					<input type="button" value="결재상신"	class="btn btn-primary" id="savebutton" /></td>
				 </tr>
				<tr>
					<td colspan="4">
						<table class="table table-bordered center">
							<tr>
								<td style="width:33%;">담당자</td>
								<td style="width:33%;">결재자</td>
								<td style="width:33%;">책임자</td>
							</tr>
							<tr>
								<td><p id="app_order_002">
										<br><input type='text' id='app2' name='app2'
											value="${memDto.mem_no} ${memDto.mem_name}" readonly="readonly"/><br> <br>
									</p></td>
								<td><p id="app_order_001">
										<br> <input type="button" class="btn btn-primary"
											value="결재 추가" onclick="applineadd1()" />
									</p> <br></td>
								<td><p id="app_order_000">
										<br> <input type="button" class="btn btn-primary"
											value="결재 추가" onclick="applineadd0()" />
									</p> <br></td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td>문서번호</td>
					<td colspan="3"><input type="text" id="doc_no" name="doc_no"
						value="상신 후 자동 부여" readonly="readonly"></td>
				</tr>
				<tr>
					<td>기안부서</td>
					<td colspan="3"><input type="text" id="doc_deptnm"
						name="doc_deptnm" value="${memDto.dept_name}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>기안자</td>
					<td colspan="3"><input type="text" id="doc_writer"
						name="doc_writer" value="${memDto.mem_name}" readonly="readonly"></td>
				</tr>

				<tr>
					<td style="width: 17%;">작성일</td>
					<td style="width: 33%;"><input type="date" id="doc_regdate"
						name="doc_regdate" value="${today}" readonly="readonly"></td>
					<td style="width: 17%;">종료일</td>
					<td style="width: 33%;"><input type="date" id="doc_lastdate"
						name="doc_lastdate" value=""></td>
				</tr>
				<tr>
					<td>참조</td>
					<td colspan="3"> <input type="text" id="doc_reperence"
						name="doc_reperence" value="" size="56" readonly="readonly" /> <input
						type="button" value="검색" class="btn btn-primary"
						onclick="chamjosearch()" /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" id="doc_title"
						name="doc_title" value="" size="65"></td>
				</tr>
				<tr>
					<td colspan="4"><textarea name="editor" id="editor" rows="10"
							cols="100" style="width:766px; height:100%;">${tempDto.temp_content}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>