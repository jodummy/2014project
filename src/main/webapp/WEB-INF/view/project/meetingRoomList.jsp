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
<title>회의실 목록</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<script type="text/javascript" src="./js/project/meetingRoomList.js"></script>
</head>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebarStart.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-6">
			<div style="padding-left: 50px;">
				<h2 id="txttitle">회의실 예약</h2>
				<hr/>
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