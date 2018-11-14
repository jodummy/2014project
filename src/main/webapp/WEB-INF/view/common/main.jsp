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
<title>home</title>

<script src="./js/common/jquery-3.1.1.min.js"></script>

<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">

<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="./js/project/makeRound.js"></script>
<script type="text/javascript" src="./js/project/projectList.js"></script>
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
	<%@ include file='./jumbotron.jsp'%>
	<%@ include file='./navbar.jsp'%>


	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<div class="panel panel-primary">
					<div class="panel-heading">관리</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<tr>
								<td rowspan="5" width="75"><img
									src="https://placehold.it/150x80?text=IMAGE"
									class="img-responsive" style="width: 100%" alt="Image"></td>
							</tr>
							<tr>
								<td width="75">이름</td>
								<td width="150">${dto.mem_name}</td>
							</tr>
							<tr>
								<td width="75">이메일</td>
								<td>${dto.mem_email}</td>
							</tr>
							<tr>
								<td width="75">class</td>
								<td>${dto.dept_name}</td>
							</tr>
						</table>
						<div style="margin: auto; padding-left: 21%;">
							<input type="button" value="내 정보 수정" class="btn btn-success"
								onclick="location.href='./myUpdate.do'" /> <input type="button"
								value="문의하기" class="btn btn-success" />
						</div>
					</div>
					<div class="panel-footer">자세한 사항은 [관리] 메뉴에서 확인하실 수 있습니다.</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="panel panel-success">
					<div class="panel-heading">
						전자결재
					</div>
					<div class="panel-body">
					<br><br>
						<ul>
							<li><h4>결재할 문서 : &nbsp; ${waittotal} 개</h4></li>
							<li><h4>상신한 문서 : &nbsp; ${myingtotal} 개</h4></li>
						</ul>
						<br>
						<div style="margin: auto; padding-left: 21%;">
							<input type="button" value="결재하기" class="btn btn-success"
								onclick="location.href='./doclistWait00.do'" /> <input
								type="button" value="진행상황" class="btn btn-success"
								onclick="location.href='./doclistIngMine00.do'" /> <input
								type="button" value="결재작성" class="btn btn-success"
								onclick="location.href='./docwrite00.do'" />
						</div>
					</div>
					<div class="panel-footer">자세한 사항은 [전자결재] 메뉴에서 확인하실 수 있습니다.</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<div class="panel panel-primary">
					<div class="panel-heading">클래스일정</div>
					<div class="panel-body">
						<iframe
							src="https://calendar.google.com/calendar/embed?src=${deparDto.schc_code}&ctz=Asia/Seoul"
							style="border: 0" width="100%" height="323px" frameborder="0"
							scrolling="no"></iframe>
						<br>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="panel panel-success">
					<div class="panel-heading">프로젝트일정</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr style="background-color: #ffffcc;">
									<th>번호</th>
									<th>프로젝트명</th>
									<th>시작일</th>
									<th>종료일</th>
								</tr>
							</thead>
							<c:forEach items="${prjList}" var="prjDto">
								<tr>
									<td>${prjDto.prj_no}</td>
									<td>${prjDto.prj_name}</td>
									<td>${prjDto.prj_start}<input type="hidden"
										name="progress_start" value="${prjDto.prj_start}"></td>
									<td>${prjDto.prj_last}<input type="hidden"
										name="progress_last" value="${prjDto.prj_last}"></td>
								</tr>
							</c:forEach>
							<thead>
								<tr style="background-color: #ffffcc;">
									<td colspan="2">기여도</td>
									<td colspan="2">비중</td>
								</tr>
							</thead>
							<c:forEach items="${prjList}" var="prjDto">
								<tr>
									<td style="text-align: center;" colspan="2"><a
										onclick="projectSchdulePie(${prjDto.prj_no})"><img
											class="projectIcon" alt="pieChar" src="./images/pieChart.png"
											width="70px" height="70px"></a></td>
									<td style="text-align: center;" colspan="2"><a
										onclick="projectSchduleBar(${prjDto.prj_no})"><img
											class="projectIcon" alt="barChar" src="./images/barChart.png"
											width="70px" height="70px"></a></td>
								</tr>
							</c:forEach>
							<thead>
								<tr style="background-color: #ffffcc;">
									<th colspan="4">진행률</th>
								</tr>
							</thead>
							<c:forEach items="${prjList}" var="prjDto">
								<td colspan="4"><progress style="width: 80%;" value='0'
										max='100'></progress>&nbsp;<span>0%</span><input type="hidden"
									name="progress_term" value="${prjDto.prjs_term}"></td>
							</c:forEach>
						</table>
						<div style="margin: auto; padding-left: 21%; text-align: right;">
							<input type="button" value="프로젝트목록" class="btn btn-warning"
								onclick="location.href='./projectList.do'" />
						</div>
						<!-- 프로젝트 pie chart modal -->
						<div class="modal fade" id="ProjectPieForm" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content"
									style="overflow: hidden; width: 750px;">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">프로젝트 기여도</h4>
									</div>
									<div class="modal-body">
										<div id="pieChart"></div>
									</div>
								</div>
							</div>
						</div>
						<!-- 프로젝트 bar chart modal -->
						<div class="modal fade" id="ProjectBarForm" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content"
									style="overflow: hidden; width: 900px;">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">프로젝트 비중</h4>
									</div>
									<div class="modal-body">
										<div id="barChart"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="panel panel-primary">
					<div class="panel-heading">공지사항</div>
					<div class="panel-body">
						<div style="text-align: right;" class="moreBtnArea">
							<a href="./communityMain.do" class="btn btn-link"> 더보기&raquo;
							</a>
						</div>
						<table class="table table-bordered" id="boardNoticeLists">
							<thead>
								<tr>
									<th style="width: 80px;">구분</th>
									<th>제목</th>
									<th style="width: 170px;">작성자</th>
									<th style="width: 100px;">작성일</th>
									<th style="width: 80px;">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(boardNoticeLists) eq 0}">
									<tr>
										<td></td>
										<td>등록된 글이 없습니다.</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:forEach items="${boardNoticeLists}" var="bnList"
									varStatus="vs">
									<tr>
										<td>공지</td>
										<td class="board_title"><a
											href="boardDetail.do?ctgr_no=1&board_no=${bnList.board_no}&rnum=${bnList.rnum}">
												${bnList.board_title} </a> <c:if
												test="${bnList.board_replycnt ne 0}">
		 						&nbsp;<span class="badge">${bnList.board_replycnt}</span>
											</c:if></td>
										<td>${bnList.dept_name}&nbsp;${bnList.mem_name}</td>
										<td>${bnList.board_regdate}</td>
										<td>${bnList.board_readcnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="panel-footer">자세한 사항은 [커뮤니티] 메뉴에서 확인하실 수 있습니다.</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<br>

	<%@ include file='./footer.jsp'%>
</body>
</html>