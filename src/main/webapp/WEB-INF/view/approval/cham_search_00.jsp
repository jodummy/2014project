<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/common/jquery-3.1.1.min.js"></script>
<script src="js/approval/approvalLine.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ya!무지개 -[참조자 선택]</title>
</head>
<body>
	<form name="form" method="post">
		<select multiple id="selcham" name="selcham" size="9">
			<c:forEach var="mList" items="${memberList}">
				<option><c:out value="${mList.mem_no} ${mList.dept_name} ${mList.mem_name}" escapeXml="false"/></option>
			</c:forEach>
		</select> <input type="button" name='btn' class="btn btn-primary" value="완료" onclick="sel()" /> &nbsp;
		<input type="button" value="취소" onclick="self.close()" />
	</form>
</body>
</html>