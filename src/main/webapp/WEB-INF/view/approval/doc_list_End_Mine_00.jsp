<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="m" uri="http://lsh.math.ceil.function"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ya!무지개 - [전자결재/결재완료함]</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<script src="js/approval/doclist.js"></script>
<script type="text/javascript" src="./js/common/paging.js"></script>

<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/approval/list.css">
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">
<link rel="stylesheet" href="./css/approval/paging.css">
</head>
<body>
	<%@ include file='../common/jumbotron.jsp'%>
	<%@ include file='../common/navbar.jsp'%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebar.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">

			<div class="totalpadleft">
				<p class="navi">
					<img src="./images/approval/doc.png" alt="문서" width="14px">
					문서함 > <span>결재완료함</span>
				</p>
				<h2 class="txttitle">결재완료함</h2>
				<hr>
				<div class="doctotalgauge">
					<img src="./images/approval/document.png" alt="문서" width="14px">
					<span> 총 문서수 : ${paging.total} 개</span>
				</div>
				<div align="right" class="selectdoccount">
					<select class='btn btn-primary' id='listCount' name='listCount'
						onchange='listCnt();'>
						<option>선택</option>
						<option value='5'>5</option>
						<option value='10'>10</option>
						<option value='15'>15</option>
						<option value='20'>20</option>
					</select>
				</div>

				<form action="./doclistEndMine00.do" method="post" id='frmPaging'>
					<table class="table table-bordered">
						<tr>
							<td style="width:8%">번 호</td>
							<td style="width:16%;">문서번호</td>
							<td style="width:36%;">제 목</td>
							<td style="width:10%;">부 서</td>
							<td style="width:8%;">작성자</td>
							<td style="width:22%;">작 성 일</td>
						</tr>
						<c:forEach items="${doclist}" var="doc">
							<tr>
								<td>${doc.doc_mngno}</td>
								<td>${doc.doc_no}</td>
								<td><a onclick="detailSave(${doc.doc_mngno})">${doc.doc_title}</a></td>
								<td>${doc.doc_deptnm}</td>
								<td>${doc.doc_writer}</td>
								<td>${doc.doc_regdate}</td>
							</tr>
						</c:forEach>
					</table>

					<!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->

					<input type='hidden' name='index' id='index'
						value='${paging.index}'> <input type='hidden'
						name='pageStartNum' id='pageStartNum'
						value='${paging.pageStartNum}'> <input type='hidden'
						name='listCnt' id='listCnt' value='${paging.listCnt}'> <input
						type='hidden' name='ctgr_no' value='${paging.ctgr_no}' /> <input
						type='hidden' id='total' name='total' value='${paging.total}' />
					<input type='hidden' id='pageLastNum' value='${paging.pageLastNum}' />
				</form>

				<div>
						<table>
							<tr>
								<td class="pageBlank"></td>
								<td class="pageArrow">
									<ul class="pagination">
										<c:if test="${paging.pageStartNum ne 1}">
											<!--맨 첫페이지 이동 -->
											<li class="pagePre"><a href='#'
												onclick='pagePre(${paging.pageCnt+1},${paging.pageCnt});'>&laquo;</a></li>
											<!--이전 페이지 이동 -->
											<li class="pagePre"><a href='#'
												onclick='pagePre(${paging.pageStartNum},${paging.pageCnt});'>&lsaquo;</a></li>
										</c:if>
									</ul>
								</td>
								<td class="pageNumbers">
									<ul class="pagination">
										<!--페이지번호 -->
										<c:forEach var='i' begin="${paging.pageStartNum}"
											end="${paging.pageLastNum}" step="1">
											<li class='pageIndex${i}'><a href='#'
												onclick='pageIndex(${i});'>${i}</a></li>
										</c:forEach>
									</ul>
								</td>
								<td class="pageArrow">
									<ul class="pagination">
										<c:if
											test="${paging.pageLastNum ne m:ceil(paging.total/paging.listCnt)}">
											<!--다음 페이지 이동 -->
											<li class="pageNext"><a href='#'
												onclick='pageNext(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&rsaquo;</a></li>
											<!--마지막 페이지 이동 -->
											<li class="pageNext"><a href='#'
												onclick='pageLast(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&raquo;</a></li>
										</c:if>
									</ul>
								</td>
								<td class="pageBlank"></td>
							</tr>
						</table>
					</div>
			</div>
		</div>
	</div>
	<%@ include file='../common/footer.jsp'%>
</body>
</html>