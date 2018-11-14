<%@page import="ya.rain.bow.dtos.MemberDto"%>
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
	function delflagUser(email) {
		window
				.open("./delflagMemberUser.do&mem_email=" + email, "선택",
						"scrollbars=yes,toolbar=yes,resizable=yes,width=450,height=500,right=150,top=0");
	}

	function delflagUser(email) {
		var memberForm = $("#ff").serialize();
		$.ajax({
			type : "post",
			url : "./delflagMemberUser.do",
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

	function modifyUser(email) {
		window
				.open("./modyMemberUser.do?&mem_email=" + email, "선택",
						"scrollbars=yes,toolbar=yes,resizable=yes,width=450,height=500,right=150,top=0");
	}
</script>
<body>
	<table class="table table-bordered table-hover">
		<tr>
			<td>이메일</td>
			<td>${dto.mem_email}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.mem_name}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${dto.mem_tel}</td>
		</tr>
		<tr>
			<td>성</td>
			<td>${dto.mem_sex}</td>
		</tr>
		<tr>
			<td>생일</td>
			<td>${dto.mem_birth}</td>
		</tr>
		<tr>
			<td>사진이름</td>
			<td>${dto.mem_photonm}</td>
		</tr>
		<tr>
			<td>도장 이름</td>
			<td>${dto.mem_signnm}</td>
		</tr>
		<tr>
			<td>클래스</td>
			<td>${dto.dept_no}</td>
		</tr>
	</table>
	<form action="./mainUser.do" method="post" id="ff">
		<input type="hidden" value="${dto.mem_email}" name='mem_email'>
		<div align="center" colspan="3">
			<input type="button" value="삭제" onclick="delflagUser()"
				class="btn btn-default"> <input type="button" value="수정"
				onclick="modifyUser('${dto.mem_email}')" class="btn btn-default">
		</div>
	</form>
</body>
</html>