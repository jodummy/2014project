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
			url : "./modyMemberUserPage.do",
			data : memberForm,
			success : function(msg) {
				if (msg.isc) {
					alert("등록 완료");
					opener.location.href = "./mainUser.do";
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
	<form action="./modyMemberUserPage.do" method="post" id="ff">
		<input type="hidden" name="mem_email" value="${dto.mem_email}">
		<table class="table table-bordered table-hover">
			<tr>
				<td>name</td>
				<td><input type="text" value="${dto.mem_name}" name="mem_name"></td>
			</tr>
			<tr>
				<td>mem_sex</td>
				<td><select name='mem_sex' required>
						<option value="">선택해주세요</option>
						<option value="M">남성</option>
						<option value="F">여성</option>
				</select></td>
			</tr>
			<tr>
				<td>tel</td>
				<td><input type="text" value="${dto.mem_tel}" name="mem_tel"></td>
			</tr>

			<tr>
				<td>sign</td>
				<td><input type="text" value="${dto.mem_signnm}"
					name="mem_signnm"></td>
			</tr>
			<tr>
				<td>birth</td>
				<td><input type="date" id="doc_lastdate" name="mem_birth"
					value="${dto.mem_birth}"></td>
			</tr>
			<tr>
				<td>photo</td>
				<td><input type="text" value="${dto.mem_photonm}"
					name="mem_photonm"></td>



			</tr>
			<tr>
				<td>dept_no</td>
				<td><select required name='dept_no'>
						<option value="">선택해주세요</option>
						<c:forEach items="${Dlist}" var="nn">
							<option value="${nn.dept_no}">${nn.dept_name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="center" colspan="3"><input type="button"
					value="수정이 완료" onclick="succesInsert()" class="btn btn-default">
					<input type="reset" value="다시쓰기" class="btn btn-default"></td>
			</tr>
		</table>
	</form>
</body>
</html>