<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="m" uri="http://lsh.math.ceil.function"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src="./js/common/paging.js"></script>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href='./css/common/sweetalert.css?ver=1.2'>

<link rel="stylesheet" href="./css/approval/approval_list.css">
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">

<title>Insert title here</title>
<style type="text/css">
.projectIcon {
	width: 50px;
	height: 50px;
}

.board_title {
	text-align: left;
}

#boardTable, th {
	text-align: center;
}

#keyWordTextBox {
	border: 1px solid #b3b3b3;
}

.center {
	text-align: center;
}

.pageArrow {
	width: 67px;
}

.pageBlank {
	
}

.pageNumbers {
	text-align: center;
	width: 200px;
}
</style>
</head>
<script type="text/javascript">
	function insertDepartmentClass() {
		window.open("./insertDepartmentClass.do", "선택",
		"scrollbars=yes,toolbar=yes,resizable=yes,width=450,height=500,right=150,top=0");
	}

// 	function detailDepartClass(code) {
// 		alert(code);
// 		var obj = document.forms[0];
// 		obj.action = "./detailDepartClass.do?dept_no=" + code;
// 		obj.method = "post";
// 		obj.submit();
// 	}
	
	
	function detailDepartClass(code) {
		var obj = document.forms[0];
		window.open("./detailDepartClass.do?dept_no=" + code, "선택",
						"scrollbars=yes,toolbar=yes,resizable=yes,width=450,height=500,right=150,top=0");
	}

	
	
</script>
<body>
	<%@ include file="../common/jumbotron.jsp"%>
	<%@ include file="../common/navbar.jsp"%>
	<div class="col-sm-2 sidenav">
		<%@ include file='./sidebar.jsp'%>
	</div>
	<div class="col-sm-10 text-left">
		<div class="col-sm-12">
			<div style="padding-left: 50px;">
				<h2 id="txttitle">반 리스트</h2>
				<hr>
				<!-- 한번에 출력할 목록 갯수 -->
				<div align="right" style="padding: 0px 10px 10px 0px;">
					<select class='btn btn-primary' id='listCount' name='listCount'
						onchange='listCnt();'>
						<option value='5'>5</option>
						<option value='10'>10</option>
						<option value='15'>15</option>
						<option value='20'>20</option>
					</select>
				</div>
				<form action="#" method="post" id='frmPaging'>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" onclick="checkAll(this.checked)"></th>
								<th>dept_no</th>
								<th>dept_name</th>
								<th>조회</th>
							</tr>
						</thead>
						<c:forEach items="${list}" var="nn">
							<tr>
								<th><input type="checkbox" onclick="checkAll(this.checked)"></th>
								<th>${nn.dept_no}</th>
								<th>${nn.dept_name}</th>
								<th><input type="button" value="조회"
									onclick="detailDepartClass('${nn.dept_no}')"
									class="btn btn-default"></th>
							</tr>
						</c:forEach>
					</table>

					<!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->
					<input type='hidden' name='index' id='index'
						value='${paging.index}'> <input type='hidden'
						name='pageStartNum' id='pageStartNum'
						value='${paging.pageStartNum}'> <input type='hidden'
						name='listCnt' id='listCnt' value='${paging.listCnt}'> <input
						type='hidden' name='ctgr_no' value='${paging.ctgr_no}'> <input
						type='hidden' id='pageCnt' name='pageCnt'
						value='${paging.pageCnt}' /> <input type='hidden' id='total'
						name='total' value='${paging.total}' />
					<div class="center">
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

				</form>
				<input type="button" value="class생성"
					onclick="insertDepartmentClass()" class='btn btn-sm btn-warning'>
			</div>
		</div>
	</div>
	<%@ include file='../common/footer.jsp'%>
</body>
</html>