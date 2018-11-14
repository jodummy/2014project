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
	width: 85%;
	padding-left: 13%;
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

a :active{
	padding-left: 5px;
	background-color: silver;
}

.pad {
	width: 160%;
	padding-bottom: 1px;
}

.pad2 {
	padding-left: 16px;
}

.dept1 {
	font-size: large;
	font-weight: bold;
}
</style>
<body>
	<div id="ss" class="nav-side-menu">
		<!-- <div class="pad titleApproval">
			<hr>
			<h2 id="txt">전자결재</h2>
		</div> -->

		<div class="pad titleApproval">
			<hr>
			<h2 id="txt"><a href="./appmain.do" class="list-group-item">전자결재</a></h2>
		</div>
		
		<div style="width: 160%;">
			<hr>
			<div class="list-group">
				<a class='pad2'> <input type="button" id="btn1"
					class="btn btn-danger btn-lg" value="문서 작성"
					onclick="location.href='./docwrite00.do'" style="width: 176px" />
				</a><br> <br> <a href="./doclistTotal00.do"
					class="list-group-item dept1">통합문서함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표">
				</a> <span class="list-group-item dept1">나의 문서함</span> <a
					href="./doclistimsiMine00.do" class="list-group-item">임시보관함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <a href="./doclistIngMine00.do" class="list-group-item">결재진행함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"> <br>
				</a> <a href="./doclistEndMine00.do" class="list-group-item">결재완료함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <a href="./doclistBanMine00.do" class="list-group-item">반려함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <span class="list-group-item dept1">받은 문서함</span> <a href="./doclistWait00.do" class="list-group-item">결재예정함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <a href="./doclistIng00.do" class="list-group-item">결재진행함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <a href="./doclistEnd00.do" class="list-group-item">결재완료함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a> <a href="./doclistBan00.do" class="list-group-item">반려함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"> </a>
					<a onclick="alert('페이지 준비 중입니다')" class="list-group-item">참조문서함 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<img src="./images/approval/arrowright.png" width="13px"
					alt="우측화살표"><br>
				</a>
			</div>
		</div>
	</div>
</body>
</html>

