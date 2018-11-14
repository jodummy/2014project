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
<title>프로젝트 home</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="./js/project/makeRound.js"></script>
<script type="text/javascript" src="./js/project/projectList.js"></script>
<script type="text/javascript" src="./js/common/stringBuffer.js"></script>
<script type="text/javascript" src="./js/project/calendar.js"></script>
<script type="text/javascript" src="./js/project/calendarSchdule.js"></script>
<script type="text/javascript" src="./js/project/meetingRoomList.js"></script>
<style type="text/css">
thead {
	text-align: center;
}

thead td {
	width: 100px;
}

#tbody td {
	height: 150px;
}

#yearMonth {
	font: bold;
	font-size: 18px;
}

.projectIcon {
	width: 50px;
	height: 50px;
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
				<h3 style="padding: 0px 0px 0px 7px; font-weight: bold;">프로젝트일정</h3>
				<hr />
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
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
				<!-- 프로젝트일정 상세보기 -->
				<form action="./projectSchdule.do" method="post" id='projectSchdule'>
					<input type="hidden" name="prj_no" id='prj_no' />
				</form>
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
					<div class="modal-dialog">
						<div class="modal-content" style="overflow: hidden; width: 900px;">
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
			<div class="col-sm-12">
				<h3 style="padding: 0px 0px 0px 7px; font-weight: bold;">클래스일정</h3>
				<hr />
				<table class="table table-bordered">
					<thead id='thead'>
						<tr>
							<td colspan="7"><span id='yearMonth'></span>
								<div style="text-align: right;">
									<span>${deparDto.dept_name}</span>
								</div></td>
						</tr>
						<tr>
							<td>일<span class='week'></span></td>
							<td>월<span class='week'></span></td>
							<td>화<span class='week'></span></td>
							<td>수<span class='week'></span></td>
							<td>목<span class='week'></span></td>
							<td>금<span class='week'></span></td>
							<td>토<span class='week'></span></td>
						</tr>
					</thead>
					<tbody id='tbody'></tbody>
				</table>
				<!-- 일정 생성 modal -->
				<div class="modal fade" id="schduleForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">일정등록</h4>
							</div>
							<div class="modal-body">
								<form class='form-margin40' role='form' action="#" method='post'
									id='frmSchdule'>
									<div class='form-group'>
										<label>제목</label> <input type='text' class='form-control'
											id='summary' name='summary'
											placeholder="예: 오후 7시에 멕시코 음식점에서 저녁식사">
									</div>
									<div class='form-group'>
										<label>시작시간</label> <input class='form-control' type="time"
											id='startTime' name='startTime'>
									</div>
									<div class='form-group'>
										<label>시작날짜</label> <input class='form-control startDate'
											type="date" id='startDate' name='startDate'
											readonly="readonly">
									</div>
									<div class='form-group'>
										<label>종료시간</label> <input class='form-control' type="time"
											id='endTime' name='endTime'>
									</div>
									<div class='form-group'>
										<label>종료날짜</label> <input class='form-control startDate'
											type="date" id='endDate' name='endDate'>
									</div>
									<div class='form-group'>
										<label>내용</label>
										<textarea rows="7" class='form-control' id="description"
											name='description'></textarea>
									</div>
									<div class='modal-footer'>
										<input type="button" class='btn btn-sm btn-warning' value="확인"
											onclick="calendarSchduleAdd()" /> <input type="reset"
											class='btn btn-sm btn-warning' value="초기화" /> <input
											type='button' class='btn btn-sm btn-warning'
											data-dismiss='modal' value="취소" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 일정 수정 modal -->
				<div class="modal fade" id="schduleFormModify" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">일정수정</h4>
							</div>
							<div class="modal-body">
								<form class='form-margin40' role='form' action="#" method='post'
									id='frmSchduleModify'>
									<div class='form-group'>
										<label>제목</label> <input type='text' class='form-control'
											id='modifySummary' name='summary'>
									</div>
									<div class='form-group'>
										<label>내용</label>
										<textarea rows="7" class='form-control' id="modifyDescription"
											name='description'></textarea>
									</div>
									<input type="hidden" id="modifyEventId" name="eventId" />
									<div class='modal-footer'>
										<input type="button" class='btn btn-sm btn-warning' value="확인"
											onclick="modifyEvent()" /> <input type="reset"
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
			<div class="col-sm-12">
				<h3 style="padding: 0px 0px 0px 7px; font-weight: bold;">회의실예약</h3>
				<hr />
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>회의실 목록</th>
						</tr>
					</thead>
					<c:forEach items="${bkrLists}" var="bkrDto">
						<tr>
							<td><a onclick="moveBooking(${bkrDto.br_no})">${bkrDto.br_name}</a></td>
						</tr>
					</c:forEach>
				</table>
				<form action="./meetingRoom.do" method="post" id="meetingRoomForm">
					<input type='hidden' id="br_no" name="br_no" />
				</form>
			</div>
		</div>
	</div>
	<%@ include file='./footer.jsp'%>
</body>
</html>