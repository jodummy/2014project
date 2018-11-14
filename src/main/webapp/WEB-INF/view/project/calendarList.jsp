<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캘린더 관리</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<script type="text/javascript" src="./js/project/calendarList.js"></script>
</head>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='../management/sidebar.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<div style="padding-left: 50px;">
				<h2 id="txttitle">캘린더 관리</h2>
				<hr/>
				<table class="table table-bordered">
					<tr>
						<th>선택</th>
						<th>부서번호</th>
						<th>클래스이름</th>
						<th>캘린더코드</th>
					</tr>
					<c:forEach items="${deparLists}" var="deparDto">
						<tr>
							<td><input type='checkbox' name='chkValDept_no'
								value="${deparDto.dept_no}" /></td>
							<td>${deparDto.dept_no}</td>
							<td>${deparDto.dept_name}</td>
							<td>${deparDto.schc_code}</td>
						</tr>
					</c:forEach>
				</table>
				<form action="./calendarRemove.do" method="post"
					id="frmCalendarRemove">
					<table class="table table-bordered">
						<tr>
							<th><input type='checkbox'
								onclick='checkAllDel(this.checked)' />전체</th>
							<th>캘린더이름</th>
							<th>캘린더코드</th>
						</tr>
						<c:forEach items="${items}" var="item">
							<tr>
								<td><input type='checkbox' name='chkVal' value="${item.id}" /></td>
								<td><input type="hidden" name='summarys'
									value="${item.summary}" />${item.summary}</td>
								<td><input type="hidden" name='listCtgr' value="0" />${item.id}</td>
							</tr>
						</c:forEach>
					</table>
				</form>
				<input type="button" class='btn btn-sm btn-warning' value="캘린더 생성"
					onclick="calendarAddForm()" /> <input type="button"
					class='btn btn-sm btn-warning' value="캘린더 수정"
					onclick="calendarModifyForm()" /> <input type="button"
					class='btn btn-sm btn-warning' value="캘린더 삭제"
					onclick="calendarRemove()" /> <input type="button"
					class='btn btn-sm btn-warning' value="캘린더 코드등록"
					onclick="calendarCodeUpdate()" />
				<form action="./updateSchdulecode.do" method="post"
					id="frmUpdateSchdulecode">
					<input type="hidden" id="schc_code" name="schc_code" /> <input
						type="hidden" id="dept_no" name="dept_no" />
				</form>
				<!-- 캘린더 생성 modal -->
				<div class="modal fade" id="calendarAddForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">캘린더 생성</h4>
							</div>
							<div class="modal-body">
								<!-- 캘린더 생성처리 form -->
								<form action="./calendarAdd.do" method='post'
									id='frmCalendarAdd'>
									<div class='form-group'>
										<label>캘린더이름</label> <input type="hidden" name='listCtgr'
											value="0" /> <input class='form-control' type="text"
											name='summary' id='summary' />
									</div>
									<div class='modal-footer'>
										<input type="button" class='btn btn-sm btn-warning' value="확인"
											onclick="calendarAdd()" /> <input type="reset"
											class='btn btn-sm btn-warning' value="초기화" /> <input
											type='button' class='btn btn-sm btn-warning'
											data-dismiss='modal' value="취소" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 캘린더 수정 modal -->
				<div class="modal fade" id="calendarModifyForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">캘린더 수정</h4>
							</div>
							<div class="modal-body">
								<!-- 캘린더 생성처리 form -->
								<form action="./calendarModify.do" method='post'
									id='frmCalendarModify'>
									<div class='form-group'>
										<label>캘린더이름</label> <input type="hidden" name='listCtgr'
											value="0" /> <input class='form-control' type="text"
											name='summary' id='summaryModify' />
									</div>
									<input type="hidden" name="schc_code" id='schc_codeModify' />
									<div class='modal-footer'>
										<input type="button" class='btn btn-sm btn-warning' value="확인"
											onclick="calendarModify()" /> <input type="reset"
											class='btn btn-sm btn-warning' value="초기화" /> <input
											type='button' class='btn btn-sm btn-warning'
											data-dismiss='modal' value="취소" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file='./footer.jsp'%>
</body>
</html>