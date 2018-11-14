<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="147908485399-3snpkldfbv2ukascgss4n35hfgn0nebd.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>

<style type="text/css">
#menuTopArea {
	background: #fff2e6;
}

#sessionName {
	background: black;
	border-radius: 8px;
	padding: 3px;
	font-size: 18px;
	font-weight: bold;
	color: white;
}

#sessionDataArea {
	float: right;
	text-align: right;
	margin-bottom: 10px;
}

@media ( max-width : 600px) {
	#sessionDataArea {
		display: none;
	}
}

#sessionDataArea>div {
	margin-top: 25px;
	font-size: 16px;
}
</style>

<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		var name = profile.getName();
		var email = profile.getEmail();
		document.getElementById("email").value = email;
		var obj = document.forms[0];
		obj.method = "get";
		obj.submit();
	}

	function signOut() {
		document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8091/ProjectSupporter/logout.do";
	}
</script>

<!-- <div class="jumbotron"> -->
<div id="menuTopArea">
	<c:if test="${fn:length(memDto.mem_email) eq 0}">
		<img alt="psy_logo" style="height: 80px;" src="./images/logo/psy_logo_text_2_lines.png" />
		<div style="float: right; margin-top: 20px; margin-right: 10px" class="g-signin2" data-onsuccess="onSignIn"></div>
	</c:if>

	<c:if test="${fn:length(memDto.mem_email) ne 0}">
		<a href="./main.do"> <img alt="psy_logo" style="height: 80px;" src="./images/logo/psy_logo_text_2_lines.png" />
		</a>
		<input class="btn btn-link" type="button" onclick="signOut()" value="로그아웃" style="float: right; margin-top: 20px; margin-right: 10px" />
	<div id="sessionDataArea">
		<div>
			<span id="sessionDeptName">${memDto.dept_name}</span>&nbsp; <span
				id="sessionName">${memDto.mem_name}</span>님 환영합니다.&nbsp;&nbsp;
		</div>
	</div>			
	</c:if>

	<form action="./logintest.do" method="get">
		<input type="hidden" id="email" name="email" value="">
	</form>
</div>
<div style='clear: both;'></div>
<!-- </div> -->
</html>


