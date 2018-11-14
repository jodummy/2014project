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
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/approval/approvalLine.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결재선 선택 페이지</title>
</head>
<body>
	<table class="table table-bordered backto">
		<tr><td style="text-align:center;"><h1>조직도</h1></td></tr>
		<tr>
		<td style="text-align:right;">
		<input type="button" class="btn btn-primary" value="완료" onclick="selApproval000()" /> &nbsp;
		<input type="button" class="btn btn-primary" value="취소" onclick="self.close()" />
		</td>
		</tr>
		<tr>
		<td>
		<c:forEach var="mList" items="${memberList}">
			<c:out value="<input type='radio' name='selapp' value='${mList.mem_no} ${mList.dept_name} ${mList.mem_name}' /> ${mList.mem_no} ${mList.dept_name} ${mList.mem_name}<br/>" escapeXml="false"/>
		</c:forEach>
		</td>
		</tr>
</table>		
</body>
</html>