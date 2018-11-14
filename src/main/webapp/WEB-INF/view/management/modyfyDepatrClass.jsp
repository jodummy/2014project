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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<title>Insert title here</title>
</head>
<body>
	<h3>class detail</h3>
	<form action="./memdellistpage.do" method="post" id='memdelfrm'>
		<input type='hidden' name='dept_no' value='${dept_no}'>
		<table class="table table-bordered table-hover">
			<tr>
				<td><input type="checkbox"></td>
				<td>번호</td>
				<td>이름</td>
			</tr>
			<c:forEach items="${mlist}" var="mem">
				<tr>
					<td><input type="checkbox" id='delmem_no' name="delmem_no"
						value="${mem.mem_no}"></td>
					<td>${mem.mem_no}</td>
					<td>${mem.mem_name}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value='팀원 제거'>
	</form>
	<hr></hr>
	<h3>미등록 회원들</h3>
	<!--유저 추가  -->
	<form action="./memlistpage.do" method="post" id='memlistfrm'>
		<input type='hidden' name='dept_no' value='${dept_no}'>
		<table class="table table-bordered table-hover">
			<tr>
				<td><input type="checkbox"></td>
				<td>번호</td>
				<td>이름</td>
				<td>소속</td>
			</tr>
			<c:forEach items="${lists}" var="nn">
				<tr>
					<td><input type="checkbox" id='mem_no' name="mem_no"
						value="${nn.mem_no}"></td>
					<td>${nn.mem_no}</td>
					<td>${nn.mem_name}</td>
					<td>${nn.dept_name}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value='팀원 추가' class="btn btn-default">
	</form>
</body>
</html>