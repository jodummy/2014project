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
<title>회의실 예약</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<script type="text/javascript" src="./js/common/stringBuffer.js"></script>
<script type="text/javascript" src="./js/project/calendar.js"></script>
<script type="text/javascript" src="./js/project/meetingRoom.js"></script>
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
				<h2 id="txttitle">회의실 예약</h2>
				<hr/>
				<input type="hidden" id="chk" value="0" />
				<table class="table table-bordered">
					<thead id='thead'>
						<tr>
							<td colspan="7">
								<button type='button' class='btn btn-sm btn-warning'
									id='moveFastPre' onclick="moveFastMonthPre()">&laquo;</button>
								&nbsp;
								<button type='button' class='btn btn-sm btn-warning'
									id='movePre' onclick="moveMonthPre()">&lsaquo;</button>
								&nbsp;&nbsp; <span id='yearMonth'></span> &nbsp;&nbsp;
								<button type='button' class='btn btn-sm btn-warning'
									id='moveNext' onclick="moveMonthNext()">&rsaquo;</button>
								&nbsp;
								<button type='button' class='btn btn-sm btn-warning'
									id='moveFastNext' onclick="moveFastMonthNext()">&raquo;</button>
								<div style="text-align: right;">
									<span>${bkrDto.br_name}</span> <input type="hidden" id="br_no"
										value="${bkrDto.br_no}" /> <input class='btn btn-sm btn-info'
										type="button" value="주" onclick='tabWeek()' /> <input
										class='btn btn-sm btn-info' type="button" value="월"
										onclick='tabMonth()' /> <input class='btn btn-sm btn-info'
										type="button" value="목록"
										onclick='location.href="./meetingRoomList.do"' />
								</div>
							</td>
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
				<!-- 회의실 예약 modal -->
				<div class="modal fade" id="schduleForm" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">회의실 예약</h4>
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
										<label>시작시간</label> <select id='startTime' name='startTime'>
											<option value="09:00">09:00</option>
											<option value="09:30">09:30</option>
											<option value="10:00">10:00</option>
											<option value="10:30">10:30</option>
											<option value="11:00">11:00</option>
											<option value="11:30">11:30</option>
											<option value="12:00">12:00</option>
											<option value="12:30">12:30</option>
											<option value="13:00">13:00</option>
											<option value="13:30">13:30</option>
											<option value="14:00">14:00</option>
											<option value="14:30">14:30</option>
											<option value="15:00">15:00</option>
											<option value="15:30">15:30</option>
											<option value="16:00">16:00</option>
											<option value="16:30">16:30</option>
											<option value="17:00">17:00</option>
											<option value="17:30">17:30</option>
											<option value="18:00">18:00</option>
										</select>
									</div>
									<div class='form-group'>
										<label>시작날짜</label> <input class='form-control startDate'
											type="date" id='startDate' name='startDate'
											readonly="readonly">
									</div>
									<div class='form-group'>
										<label>종료시간</label> <select id='endTime' name='endTime'>
											<option value="09:00">09:00</option>
											<option value="09:30">09:30</option>
											<option value="10:00">10:00</option>
											<option value="10:30">10:30</option>
											<option value="11:00">11:00</option>
											<option value="11:30">11:30</option>
											<option value="12:00">12:00</option>
											<option value="12:30">12:30</option>
											<option value="13:00">13:00</option>
											<option value="13:30">13:30</option>
											<option value="14:00">14:00</option>
											<option value="14:30">14:30</option>
											<option value="15:00">15:00</option>
											<option value="15:30">15:30</option>
											<option value="16:00">16:00</option>
											<option value="16:30">16:30</option>
											<option value="17:00">17:00</option>
											<option value="17:30">17:30</option>
											<option value="18:00">18:00</option>
										</select>
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
				<!-- 회의실 예약 modal -->
				<div class="modal fade" id="schduleFormModify" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">회의실 예약</h4>
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
		</div>
	</div>
	<%@ include file='./footer.jsp'%>
</body>
</html>