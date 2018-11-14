<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<script type="text/javascript">
	function navbarChk() {
		swal('로그인', '로그인후 이용해주세요');
	}
</script>
<nav class='navbar navbar-inverse'>
	<div class='container-fluid'>
		<ul class='nav navbar-nav'>
			<c:if test="${fn:length(memDto.mem_email) ne 0}">
				<li class='active'><a href='./main.do'>home</a></li>
				<c:choose>
					<c:when test="${memDto.auth_no eq 0}">
						<li><a href='./mainUser.do'>정보관리</a></li>
					</c:when>
					<c:when test="${memDto.auth_no ne 0}">
						<li><a href='./myPage.do'>정보관리</a></li>
					</c:when>
				</c:choose>
				<li><a href='./appmain.do'>전자결재</a></li>
				<li><a href='./projectMain.do'>프로젝트</a></li>
				<li><a href='./communityMain.do'>커뮤니티</a></li>
			</c:if>
			<c:if test="${fn:length(memDto.mem_email) eq 0}">
				<li class='active'><a onclick="navbarChk()">home</a></li>
				<li><a onclick="navbarChk()">정보관리</a></li>
				<li><a onclick="navbarChk()">전자결재</a></li>
				<li><a onclick="navbarChk()">프로젝트</a></li>
				<li><a onclick="navbarChk()">커뮤니티</a></li>
			</c:if>
		</ul>
		<ul class='nav navbar-nav navbar-right'>
			<li><a href='./websquare/websquare.html?w2xPath=/ProjectSupporter/answerboardList.xml'>문의게시판</a></li>
		</ul>
	</div>
</nav>
