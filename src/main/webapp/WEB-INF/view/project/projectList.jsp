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
<%@taglib prefix="m" uri="http://lsh.math.ceil.function"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트목록</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="./js/common/paging.js"></script>
<script type="text/javascript" src="./js/project/makeRound.js"></script>
<script type="text/javascript" src="./js/project/projectList.js"></script>
<style type="text/css">
.projectIcon {
	width: 50px;
	height: 50px;
}

.board_title {
	text-align: left;
}

#boardTable, th {
	text-align: center;
}

#keyWordTextBox {
	border: 1px solid #b3b3b3;
}

.center {
	text-align: center;
}

.pageArrow {
	width: 67px;
}

.pageBlank {
	
}

.pageNumbers {
	text-align: center;
	width: 200px;
}
</style>
</head>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebarStart.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<div style="padding-left: 50px;">
				<h2 id="txttitle">프로젝트 일정</h2>
				<hr/>
				<!-- 한번에 출력할 목록 갯수 -->
				<div align="right" style="padding: 0px 10px 10px 0px;">
					<select class='btn btn-primary' id='listCount' name='listCount'
						onchange='listCnt();'>
						<option value='5'>5</option>
						<option value='10'>10</option>
						<option value='15'>15</option>
						<option value='20'>20</option>
					</select>
				</div>
				<!-- delflag처리 form -->
				<form action="./updateDelProject.do" method="post"
					id='frmUpdateDelProject'>
					<!-- 목록 폼 -->
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><input type='checkbox'
									onclick='checkAllDel(this.checked)' />전체</th>
								<th>번호</th>
								<th>프로젝트명</th>
								<th>WBS</th>
								<td>기여도</td>
								<td>비중</td>
								<th>시작일</th>
								<th>종료일</th>
								<th>진행률</th>
							</tr>
						</thead>
						<c:forEach items="${prjLists}" var="prjDto">
							<tr>
								<td><input type='checkbox' name='chkVal'
									value="${prjDto.prj_no}" /></td>
								<td>${prjDto.prj_no}</td>
								<td>${prjDto.prj_name}</td>
								<td><a onclick="projectSchdule(${prjDto.prj_no})"><img
										class="projectIcon" alt="WBS" src="./images/WBS.png" /></a></td>
								<td><a onclick="projectSchdulePie(${prjDto.prj_no})"><img
										class="projectIcon" alt="pieChar" src="./images/pieChart.png"></a></td>
								<td><a onclick="projectSchduleBar(${prjDto.prj_no})"><img
										class="projectIcon" alt="barChar" src="./images/barChart.png"></a></td>
								<td>${prjDto.prj_start}<input type="hidden"
									name="progress_start" value="${prjDto.prj_start}"></td>
								<td>${prjDto.prj_last}<input type="hidden"
									name="progress_last" value="${prjDto.prj_last}"></td>
								<td><progress value='0' max='100'></progress>&nbsp;<span>0%</span><input
									type="hidden" name="progress_term" value="${prjDto.prjs_term}"></td>
							</tr>
						</c:forEach>
					</table>
				</form>
				<!-- 프로젝트일정 상세보기 -->
				<form action="./projectSchdule.do" method="post" id='projectSchdule'>
					<input type="hidden" name="prj_no" id='prj_no' />
				</form>
				<!-- 페이징 폼 -->
				<div class="center">
					<table>
						<tr>
							<td class="pageBlank"></td>
							<td class="pageArrow">
								<ul class="pagination">
									<c:if test="${p.pageStartNum ne 1}">
										<!--맨 첫페이지 이동 -->
										<li class="pagePre"><a
											onclick='pagePre(${p.pageCnt+1},${p.pageCnt});'>&laquo;</a></li>
										<!--이전 페이지 이동 -->
										<li class="pagePre"><a
											onclick='pagePre(${p.pageStartNum},${p.pageCnt});'>&lsaquo;</a></li>
									</c:if>
								</ul>
							</td>
							<td class="pageNumbers">
								<ul class="pagination">
									<!--페이지번호 -->
									<c:forEach var='i' begin="${p.pageStartNum}"
										end="${p.pageLastNum}" step="1">
										<li class='pageIndex${i}'><a onclick='pageIndex(${i});'>${i}</a></li>
									</c:forEach>
								</ul>
							</td>
							<td class="pageArrow">
								<ul class="pagination">
									<c:if test="${p.pageLastNum ne m:ceil(p.total/p.listCnt)}">
										<!--다음 페이지 이동 -->
										<li class="pageNext"><a
											onclick='pageNext(${p.pageStartNum},${p.total},${p.listCnt},${p.pageCnt});'>&rsaquo;</a></li>
										<!--마지막 페이지 이동 -->
										<li class="pageNext"><a
											onclick='pageLast(${p.pageStartNum},${p.total},${p.listCnt},${p.pageCnt});'>&raquo;</a></li>
									</c:if>
								</ul>
							</td>
							<td class="pageBlank"></td>
						</tr>
					</table>
				</div>
				<!-- 페이징처리 form -->
				<form action="./projectList.do" method="post" id="frmPaging">
					<!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->
					<input type='hidden' name='index' id='index' value='${p.index}' />
					<input type='hidden' name='pageStartNum' id='pageStartNum'
						value='${p.pageStartNum}' /> <input type='hidden' name='listCnt'
						id='listCnt' value='${p.listCnt}' />
					<!-- 화살표 화면표시 연산에 필요한 출력할 페이지 갯수, 리스트 총 갯수 -->
					<input type='hidden' id='total' value='${p.total}' /> <input
						type='hidden' id='pageLastNum' value='${p.pageLastNum}' />
				</form>
				<!-- chk된 목록 권한 비교 데이터 -->
				<c:forEach items="${prjLists}" var="prjDto">
					<input type="hidden" name="dept_nos" value="${prjDto.dept_no}">
				</c:forEach>
				<input class='btn btn-sm btn-warning btn-center' type='button'
					value='생성' onclick='insertProjectForm()'> <input
					class='btn btn-sm btn-warning btn-center' type='button' value='삭제'
					onclick='updateDelProject()'>
				<!-- 프로젝트 생성 modal -->
				<div class="modal fade" id="insertProjectForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">프로젝트 생성</h4>
							</div>
							<div class="modal-body">
								<!-- 프로젝트 생성처리 form -->
								<form action="./insertProject.do" method='post'
									id='frmInsertProjcet'>
									<div class='form-group'>
										<label>프로젝트명</label> <input class='form-control' type="text"
											name='prj_name' id='prj_name' />
									</div>
									<div class='form-group'>
										<label>시작일</label> <input class='form-control' type="date"
											name='prj_start' id='prj_start' />
									</div>
									<div class='form-group'>
										<label>종료일</label> <input class='form-control' type="date"
											name='prj_last' id='prj_last' />
									</div>
									<input type='hidden' name='dept_no' value='${memDto.dept_no}'>
									<div class='modal-footer'>
										<input type="button" class='btn btn-sm btn-warning' value="확인"
											onclick="insertProject()" /> <input type="reset"
											class='btn btn-sm btn-warning' value="초기화" /> <input
											type='button' class='btn btn-sm btn-warning'
											data-dismiss='modal' value="취소" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 프로젝트 pie chart modal -->
				<div class="modal fade" id="ProjectPieForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content" style="overflow: hidden; width: 750px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">프로젝트 기여도</h4>
							</div>
							<div class="modal-body">
								<div id="pieChart"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 프로젝트 bar chart modal -->
				<div class="modal fade" id="ProjectBarForm" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" style="overflow: hidden; width: 750px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">프로젝트 비중</h4>
							</div>
							<div class="modal-body">
								<div id="barChart"></div>
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