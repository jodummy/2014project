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
<title>테스트 페이지</title>
</head>
<style>
#ss {
	text-align: left;
	width: 150px;
	padding-left: 20px;
}

#txt {
	padding-top: 10px;
	padding-bottom: 1px;
	font-weight: bold;
	text-align: center;
}

#btn1 {
	font-weight: bolder;
}

a {
	padding-left: 5px;
}

.pad {
	width: 160%;
	padding-bottom: 1px;
}

.padddd {
	padding-left: 16px;
}

.dept1 {
	font-size: large;
	font-weight: bold;
}
</style>
<body>
	<div id="ss" class="nav-side-menu">
		<div class="pad titleApproval">
			<hr>
			<h2 id="txt"><a href="./managementMain.do" class="list-group-item">사용자 관리</a></h2>
		</div>

		<div style="width: 150%;">
			<hr>
			<div class="list-group">
				<!-- 				<a class='padddd'> <input type="button" id="btn1" -->
				<!-- 					class="btn btn-danger btn-lg" value="사용자 등록" -->
				<!-- 					onclick="location.href='./insertUser.do'" style="width: 176px" /> -->
				<!-- 				</a> -->
				<br> <br> <span class="list-group-item dept1">사용자</span> <a
					href="./mainUser.do" class="list-group-item">사용자 리스트 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표">
				</a> <span class="list-group-item dept1">팀</span> <a
					href="./listDepartment.do" class="list-group-item">팀 리스트 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <span class="list-group-item dept1">클래스</span> <a
					href="./listDepartmentClass.do" class="list-group-item">클래스 리스트
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <span class="list-group-item dept1">캘린더</span> <a
					href="./calendarList.do" class="list-group-item">캘린더 관리 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <span class="list-group-item dept1">예약일정</span> <a
					href="./bookingResourceList.do" class="list-group-item">예약일정 관리
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a>

			</div>
		</div>
	</div>
</body>
</html>

