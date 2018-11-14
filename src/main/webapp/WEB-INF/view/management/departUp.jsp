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
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<form action="./departUpPage.do" method="post">
				<table class="table table-bordered table-hover">
					<tr>
						<td>team_master</td>
						<td><select required name='mem_no'>
								<option value="">선택해주세요</option>
								<c:forEach items="${list}" var="nn">
									<option value="${nn.mem_no}">${nn.mem_name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>dept_name</td>
						<td><input type="text" value="${dto.dept_name}"
							name="dept_name"></td>
					</tr>


				</table>
				<input type="submit" value="완료">
			</form>
		</div>
	</div>
	<%@ include file='../common/footer.jsp'%>
</html>