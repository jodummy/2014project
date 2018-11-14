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
	function succesInsert() {
		var memberForm = $("#ff").serialize();
		$.ajax({
			type : "post",
			url : "./updateDepatrUserPage.do",
			data : memberForm,
			success : function(msg) {
				if (msg.isc) {
					alert("등록 완료");
					opener.location.href = "./listDepartmentClass.do";
					window.close();
				} else {
					alert("등록 실패");
				}
			},
			error : function(textStatus, errorThrown) {
				alert("실패");
			}
		});
	}
</script>
<body>
	<h1>${message}</h1>
	<form action="#" method="post" id='ff'>
		<table class="table table-bordered table-hover">
			<tr>
				<td>현재 강사</td>
				<td>${dto2.mem_name}</td>
			</tr>
			<tr>
				<td>class_name</td>
				<td><input type="text" name="dept_name" required='required'></td>
			</tr>
			<tr>
				<td>선택하시오</td>
				<td><select required name='auth_no'>
						<option value="">선택하세요 </option>
						<option value="3">강사 내리기</option>
						<option value="2">강사 올리기</option>
				</select></td>
			</tr>
			<tr>
				<td>team_master</td>
				<td><select required name='mem_email'>
						<option value="">선택해주세요</option>
						<c:forEach items="${list}" var="nn">
							<option value="${nn.mem_email}">${nn.mem_name}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<div align="center" colspan="3">
			<input type="hidden" value="${dept_no}" name='dept_no'> <input
				type="hidden" value="${dept_top}" name='dept_top'> <input
				type="button" value='수정 완료' onclick="succesInsert()"
				class="btn btn-default">
		</div>
	</form>
</body>
</html>