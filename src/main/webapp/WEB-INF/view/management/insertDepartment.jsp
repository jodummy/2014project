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
			url : "./insertDeparPage.do",
			data : memberForm,
			success : function(isc) {
				if (isc) {
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
	<form action="./insertDeparPage.do" method="post" id="ff">
		<table class="table table-bordered table-hover">
			<tr>
				<td>dept_no</td>
				<td><input type="text" name="dept_no" required="required"></td>
			</tr>
			<tr>
				<td>dept_name</td>
				<td><input type="text" name="dept_name" required="required"></td>
			</tr>
			<!--셀렉트 박스 dept C%시작하는 쿼리문을 찾아라  -->
			<tr>
				<td>dept_top</td>
				<td><select required name='dept_top'>
						<option value="">선택해주세요</option>
						<c:forEach items="${Dlist}" var="nn">
							<option value="${nn.dept_no}">${nn.dept_name}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<div align="center" colspan="3">
			<input type="button" value="글입력" onclick="succesInsert()"
				class="btn btn-default"><input type="reset" value="다시쓰기"
				class="btn btn-default">
		</div>
	</form>
</body>
</html>