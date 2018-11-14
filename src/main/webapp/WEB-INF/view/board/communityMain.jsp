<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Community Main</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<style type="text/css">
.moreBtnArea {
	text-align: right;
}
.board_title {
	text-align: left;
}
#shareBoardTable, #classBoardTable, th {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="../common/jumbotron.jsp"></jsp:include>
	<jsp:include page="../common/navbar.jsp"></jsp:include>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-md-2 sidenav sidebar">
				<jsp:include page="./boardSidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10 text-left">
				<p class="navi">
					<img src="./images/icon/board.png" alt="게시판" width="14px">
					커뮤니티 
				</p>
				<div>
					<h3>공유 게시판</h3>
					<div class="moreBtnArea">
						<a href="./boardList.do?ctgr_no=1" class="btn btn-link">
							더보기&raquo;
						</a>
					</div>
					<table class="table table-bordered" id="shareBoardTable">
						<thead>
							<tr>
								<th style="width: 80px;">No.</th>
								<th>제목</th>
								<th style="width: 170px;">작성자</th>
								<th style="width: 100px;">작성일</th>
								<th style="width: 80px;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(shareBoardLists) eq 0}">
								<tr>
									<td></td>
									<td>등록된 글이 없습니다.</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</c:if>
							<c:forEach items="${shareBoardLists}" var="bList" varStatus="vs">
								<tr>
									<td>${bList.rnum}</td>
									<td class="board_title">
										<a href="boardDetail.do?ctgr_no=1&board_no=${bList.board_no}&rnum=${bList.rnum}">
											${bList.board_title} </a>
										<c:if test="${bList.board_replycnt ne 0}">
					 						&nbsp;<span class="badge">${bList.board_replycnt}</span>
										</c:if>
									</td>
									<td>${bList.dept_name}&nbsp;${bList.mem_name}</td>
									<td>${bList.board_regdate}</td>
									<td>${bList.board_readcnt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<hr/>
				<div>
					<h3>&ldquo; ${boardTitleName} &rdquo; 클래스 게시판</h3>
					<div class="moreBtnArea">
						<c:if test="${fn:substring(memDto.dept_no, 0, 1) eq 'C'}">
							<a href="./boardList.do?ctgr_no=2&dept_no=${memDto.dept_no}" class="btn btn-link">
								더보기&raquo;
							</a>
						</c:if>
						<c:if test="${fn:substring(memDto.dept_no, 0, 1) ne 'C'}">
							<a href="./boardList.do?ctgr_no=2&dept_no=${memDto.dept_top}" class="btn btn-link">
								더보기&raquo;
							</a>
						</c:if>
					</div>
					<table class="table table-bordered" id="classBoardTable">
						<thead>
							<tr>
								<th style="width: 80px;">No.</th>
								<th>제목</th>
								<th style="width: 170px;">작성자</th>
								<th style="width: 100px;">작성일</th>
								<th style="width: 80px;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(classBoardLists) eq 0}">
								<tr>
									<td></td>
									<td>등록된 글이 없습니다.</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</c:if>
							<c:forEach items="${classBoardLists}" var="bList" varStatus="vs">
								<tr>
									<td>${bList.rnum}</td>
									<td class="board_title">
										<a href="boardDetail.do?ctgr_no=2&board_no=${bList.board_no}&rnum=${bList.rnum}">
											${bList.board_title}
										</a>
										<c:if test="${bList.board_replycnt ne 0}">
					 						&nbsp;<span class="badge">${bList.board_replycnt}</span>
										</c:if>
									</td>
									<td>${bList.dept_name}&nbsp;${bList.mem_name}</td>
									<td>${bList.board_regdate}</td>
									<td>${bList.board_readcnt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>