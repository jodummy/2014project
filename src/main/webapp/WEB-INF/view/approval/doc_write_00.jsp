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
<title>Ya!무지개 - [전자결재/문서작성]</title>
</head>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/approval/approval_list.css">
<script src="./js/approval/docWrite.js"></script>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebar.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<div style="padding-left: 4%;">
				<p class="navi">
					<img src="./images/approval/doc.png" alt="문서" width="14px">
					문서함 > <span>문서작성</span>
				</p>
				<h2 id="txttitle">문서작성</h2>
				<hr>

				<h6>&nbsp;&nbsp;&nbsp;작성하고자 하는 문서를 선택해주세요.</h6>
				<hr>
				&nbsp; <a onclick="gian()">기안서</a><br> &nbsp; <a
					onclick="vacation()">휴가신청서</a><br> &nbsp; <a onclick="pay()">지출결의서</a><br>
				&nbsp; <a onclick="etc()">기타양식</a>
			</div>
		</div>
	</div>
	<%@ include file='../common/footer.jsp'%>
</body>
</html>