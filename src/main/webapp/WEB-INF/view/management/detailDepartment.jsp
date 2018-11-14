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
<script type="text/javascript">
	function updateDepatrUser() {
		var obj = document.getElementById('memdelfrm');
		obj.action = "./updateDepatrUser.do";
		obj.method = "post";
		obj.submit();
	}



	function delDepatrTeam() {
		var obj = document.forms[0];
		obj.action = "./delDepatrTeamPage.do";
		obj.method = "post";
		obj.submit();
	}
</script>
<body>
	<h3>team-detail</h3>
	<form action="./memdellistpage.do" method="post" id='memdelfrm'>
		<input type='hidden' name='dept_top' value='${dto.dept_top}'>
		<input type='hidden' name='dept_no' value='${dto.dept_no}'>
		<table class="table table-bordered table-hover">
			<tr>
				<td>team_detail</td>
				<td>dept_no</td>
				<td>${dto.dept_no}</td>
			</tr>
			<tr>
				<td>team_detail</td>
				<td>dept_name</td>
				<td>${dto.dept_name}</td>
			</tr>
			<tr>
				<td>team_detail</td>
				<td>팀장</td>
				<td>${dto2.mem_name}</td>
			</tr>

			<c:forEach items="${mlist}" var="mem">
				<tr>
					<td><input type="checkbox" id="delmem_no" name="delmem_no"
						value="${mem.mem_no}"></td>
					<td>팀원</td>
					<td>${mem.mem_name}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value='팀원 제거' class="btn btn-default">
	</form>
	<hr>
	<h3>추가할 인원</h3>
	<!--유저 추가  -->
	<form action="./memlistpage.do" method="post" id='memlistfrm'>
		<input type='hidden' name='dept_no' value='${dto.dept_no}'> <input
			type='hidden' name='dept_top' value='${dto.dept_top}'>
		<table class="table table-bordered table-hover">
			<tr>
				<td><input type="checkbox"></td>
				<td>번호</td>
				<td>이름</td>
				<td>소속</td>
			</tr>
			<c:forEach items="${memberList}" var="nn">
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
	<!--키 값을 가지고 있다  -->
	<div align="center" colspan="3">
		<input type="button" value="수정"
			onclick="updateDepatrUser('${dto.dept_no} ${dto.dept_top}')"
			class="btn btn-default"> <input type="button" value="팀 삭제"
			onclick="delDepatrTeam('${dto.dept_no}')" class="btn btn-default">
	</div>
</body>
</html>