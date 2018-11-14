<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <style>
#ss {
	text-align: left;
	width: 150px;
	padding-left: 20px;
	/* 	background-color: silver; */
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

<div id="ss" class="nav-side-menu">
		<div class="pad titleApproval">
			<hr>
			<h2 id="txt"><a href="./projectMain.do" class="list-group-item">프로젝트</a></h2>
		</div>

	<div style="width: 160%;"> 
			<hr>
			<div class="list-group">
				<a href="./projectList.do" class="list-group-item">프로젝트 일정<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"> <br>
				</a>
				<a href="./calendarSchdule.do" class="list-group-item">클래스 일정<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"> <br>
				</a>
				<a href="./meetingRoomList.do" class="list-group-item">회의실 예약<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"> <br>
				</a>
			</div>
		</div>
	</div>