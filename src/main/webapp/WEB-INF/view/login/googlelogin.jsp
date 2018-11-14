<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">

<script type="text/javascript"
	src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
</head>
<style type="text/css">
#loginInfoArea {
	text-align: center;
	height: 100%;
}

#loginInfoArea>h1 {
	color: white;
	text-shadow: 3px 2px black;
}

#loginInfoArea>img {
	width: 300px;
}

#loginBody {
	background-color: gray;
}
.psyFooter {
	position: fixed;
	bottom: 0px;
	text-align: center;
}
</style>

<body id="loginBody">
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div id="loginInfoArea">
		<img alt="로고" src="./images/logo/psy_logo_text_center.png">
		<h1>로그인 후 이용 가능합니다.</h1>
		<input class="btn btn-info btn-lg" type="button" value="문의게시판" onclick="location.href='./websquare/websquare.html?w2xPath=/ProjectSupporter/answerboardList.xml'"/>
		<br/>
	</div>
	<div class="psyFooter">
		<%@ include file='../common/footer.jsp'%>
	</div>
</body>
</html>