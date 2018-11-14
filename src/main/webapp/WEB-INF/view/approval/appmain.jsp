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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ya!무지개 - [전자결재]</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<script src="./js/approval/doclist.js"></script>

<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/approval/appmain.css">
</head>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebar.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">

			<div class="mainWaitContainer">
				<h3 class="mainh3">
					<img src="./images/approval/doc.png" width="24px" alt="문서함">
					결재예정함
				</h3>

				<a href="./doclistWait00.do" class="floatright"> 더 보기 
				<img src="./images/approval/arrow.png" width="13px" alt="문서함"></a>

				<hr>

				<table class="table table-bordered">
					<tr>
						<td style="width:20%;">문서번호</td>
						<td style="width:50%;">제 목</td>
						<td style="width:8%;">작성자</td>
						<td style="width:22%;">작 성 일</td>
					</tr>
					<c:forEach items="${waitlist}" var="doc2" begin="0" varStatus="status" end="4">
						<tr>
							<td>${doc2.doc_no}</td>
							<td><a onclick="detailApp(${doc2.doc_mngno})">${doc2.doc_title}</a></td>
							<td>${doc2.doc_writer}</td>
							<td>${doc2.doc_regdate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>


			<div class="mainIngContainer">
				<h3 class="mainh3">
					<img src="./images/approval/doc.png" width="24px" alt="문서함">
					결재진행함
				</h3>
				
				<a href="./doclistIngMine00.do" class="floatright">더 보기
				<img src="./images/approval/arrow.png" width="13px" alt="문서함"></a>
				
				<hr>
				
				<table class="table table-bordered">
					<tr>
						<td style="width:20%;">문서번호</td>
						<td style="width:40%;">제 목</td>
						<td style="width:10%;">부 서</td>
						<td style="width:8%;">작성자</td>
						<td style="width:22%;">작 성 일</td>
					</tr>
					<c:forEach items="${inglist}" var="doc" begin="0"
						varStatus="status" end="4">
						<tr>
							<td>${doc.doc_no}</td>
							<td><a onclick="detailSave(${doc.doc_mngno})">${doc.doc_title}</a></td>
							<td>${doc.doc_deptnm}</td>
							<td>${doc.doc_writer}</td>
							<td>${doc.doc_regdate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<%@ include file='../common/footer.jsp'%>
</body>
</html>