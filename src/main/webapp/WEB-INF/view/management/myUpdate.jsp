<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="m" uri="http://lsh.math.ceil.function"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./js/common/paging.js"></script>
<script type="text/javascript" src="./js/common/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="./js/common/jquery-3.1.1.min.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script src="./js/common/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="./css/common/sweetalert.css?ver=1.2">

<link rel="stylesheet" href="./css/approval/approval_list.css">
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.projectIcon {
	width: 50px;
	height: 50px;
}

.board_title {
	text-align: left;
}

#boardTable, th {
	text-align: center;
}

#keyWordTextBox {
	border: 1px solid #b3b3b3;
}

.center {
	text-align: center;
}

.pageArrow {
	width: 67px;
}

.pageBlank {
	
}

.pageNumbers {
	text-align: center;
	width: 200px;
}
</style>
</head>
<body>
	<%@ include file="../common/jumbotron.jsp"%>
	<%@ include file="../common/navbar.jsp"%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebar2.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<div style="padding-left: 50px;">
				<h2 id="txttitle">수정</h2>
				<hr>
				<form action="./modyMemberUserPage.do" method="post" id="ff">
					<input type="hidden" name="mem_email" value="${dto.mem_email}">
					<table class="table table-bordered table-hover">
						<tr>
							<td>name</td>
							<td><input type="text" value="${dto.mem_name}"
								name="mem_name" required="required"></td>
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
							<td><input type="text" value="${dto.mem_tel}" name="mem_tel"
								required="required"></td>
						</tr>

						<tr>
							<td>sign</td>
							<td><input type="text" value="${dto.mem_signnm}"
								name="mem_signnm" required="required"></td>
						</tr>
						<tr>
							<td>birth</td>
							<td><input type="date" id="doc_lastdate" name="mem_birth"
								value="${dto.mem_birth}" required="required"></td>
						</tr>
						<tr>
							<td>photo</td>
							<td><input type="text" value="${dto.mem_photonm}"
								name="mem_photonm" required="required"></td>
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
								value="수정이 완료" onclick="succesInsert()"> <input
								type="reset" value="다시쓰기"></td>
						</tr>
					</table>
				</form>


			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>

</body>
</html>