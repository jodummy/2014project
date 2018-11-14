<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   request.setCharacterEncoding("UTF-8"); %>
<%   response.setContentType("text/html; charset=UTF-8");  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h2><a href="./doclistTotal00.do">통합문서함</a><br></h2>
		<h2>나의 문서함</h2>
			<a href="./doclistimsiMine00.do">임시보관함</a><br>
			<a href="./doclistIngMine00.do">결재진행함</a><br>
			<a href="./doclistEndMine00.do">결재완료함</a><br>
			<a href="./doclistBanMine00.do">반려함</a><br>
		<h2>받은 문서함</h2>
			<a href="./test.do">참조문서함</a><br>
			<a href="./doclistWait00.do">결재예정함</a><br>
			<a href="./doclistIng00.do">결재진행함</a><br>
			<a href="./doclistEnd00.do">결재완료함</a><br>
			<a href="./doclistBan00.do">반려함</a><br>
	</body>
</html>