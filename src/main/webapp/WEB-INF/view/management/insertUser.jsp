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
<script type="text/javascript" src="./js/common/sweetalert.min.js?ver=1"></script>
<link rel="stylesheet" type="text/css"
	href="./css/common/sweetalert.css?ver=1.2">
<script type="text/javascript" src="./js/common/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	alert('실행');
	function validityCheck() {
		$.ajax({
			type : "post",
			url : "./validityCheck.do",
			data : {
				"mem_email" : $("#mem_email").val()
			},
			success : function(msg) {
				if (msg.isc) {
					alert("써라");
				} else {
					alert("쓰지마.");
				}
			},
			error : function() {
				alert("ajax 실패");
			}
		});
	}

	function succesInsert() {
		var memberForm = $("#ff").serialize();
		$.ajax({
			type : "post",
			url : "./insertUserPage.do",
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
	<form id="ff" action="./insertUserPage.do" method="post">
		<table class="table table-bordered table-hover">
			<tr>
				<td>mem_email</td>
				<td><input type="text" name="mem_email" id="mem_email"
					required="required"><input type="button"
					onclick="validityCheck()" value="중복체크"></td>
			</tr>
			<tr>
				<td>dept_no</td>
				<td><select required name="dept_no">
						<option value="">선택해주세요</option>
						<c:forEach items="${Dlist}" var="nn">
							<option value="${nn.dept_no}">${nn.dept_name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>mem_name</td>
				<td><input type="text" name="mem_name" required="required"></td>
			</tr>
			<tr>
				<td>mem_tel</td>
				<td><input type="text" name="mem_tel" required="required"></td>
			</tr>
			<tr>
				<td>mem_sex</td>
				<td><select name="mem_sex" required>
						<option value="">선택해주세요</option>
						<option value="M">남성</option>
						<option value="F">여성</option>
				</select></td>
			</tr>
			<tr>
				<td>mem_birth</td>
				<td><input type="date" id="doc_lastdate" name="mem_birth"
					value="${dto.mem_birth}" required="required"></td>
			</tr>
			<tr>
				<td>mem_photonm</td>
				<td><input type="text" name="mem_photonm" required="required"></td>
			</tr>
			<tr>
				<td>mem_signnm</td>
				<td><input type="text" name="mem_signnm" required="required"></td>
			</tr>
			<tr>
				<td align="center" colspan="3"><input type="button" value="글입력"
					onclick="succesInsert()"> <input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>