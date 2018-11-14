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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<script type="text/javascript" src="./js/common/paging.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function delDepatrClassPage(code) {
		var obj = document.forms[0];
		obj.action = "./delDepatrClassPage.do?dept_no=" + code;
		obj.method = "post";
		obj.submit();
	}
	
	function modyfyDepatrClassPage(code){
		var obj = document.forms[0];
		obj.action = "./modyfyDepatrClass.do?dept_no=" + code;
		obj.method = "post";
		obj.submit();
	}
	
	function modyfyDepatrClassNamePage(code){
		var obj = document.forms[0];
		obj.action = "./classtUp.do?dept_no=" + code;
		obj.method = "post";
		obj.submit();
	}
	
	
	
</script>
<body>

	<!-- 	한번에 출력할 목록 갯수 -->
	<select class='btn btn-primary' id='listCount' name='listCount'
		onchange='listCnt();'>
		<option>선택</option>
		<option value='5'>5</option>
		<option value='10'>10</option>
		<option value='15'>15</option>
		<option value='20'>20</option>
	</select>


	<form action="#" method="post" id='frmPaging'>
		<table class="table table-bordered table-hover">
			<tr>
				<td>dept_no</td>
				<td>${dto.dept_no}</td>
			</tr>
			<tr>
				<td>dept_name</td>
				<td>${dto.dept_name}</td>
			</tr>
			<tr>
				<td>mem_name</td>
				<td>${dto.mem_name}</td>
			</tr>
			<c:forEach items="${mlist}" var="mem">
				<tr>
					<td>teamwon_name</td>
					<td>${mem.mem_name}</td>
				</tr>
			</c:forEach>
		</table>
		<input type='hidden' name='index' id='index' value='${paging.index}'>
		<input type='hidden' name='pageStartNum' id='pageStartNum'
			value='${paging.pageStartNum}'> <input type='hidden'
			name='listCnt' id='listCnt' value='${paging.listCnt}'> <input
			type='hidden' name='ctgr_no' value='${paging.ctgr_no}'> <input
			type='hidden' id='pageCnt' name='pageCnt' value='${paging.pageCnt}' />
		<input type='hidden' id='total' name='total' value='${paging.total}' />
		<div class="center">
			<ul class="pagination">

				<li><a href='#'
					onclick='pagePre(${paging.pageCnt+1},${paging.pageCnt});'>&laquo;</a></li>

				<li><a href='#'
					onclick='pagePre(${paging.pageStartNum},${paging.pageCnt});'>&lsaquo;</a></li>


				<c:forEach var='i' begin="${paging.pageStartNum}"
					end="${paging.pageLastNum}" step="1">
					<li><a href='#' onclick='pageIndex(${i});'>${i}</a></li>
				</c:forEach>


				<li><a href='#'
					onclick='pageNext(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&rsaquo;</a></li>

				<li><a href='#'
					onclick='pageLast(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&raquo;</a></li>
			</ul>
		</div>
		<div align="center" colspan="3">
			<input type="button" value="class 정보"
				onclick="modyfyDepatrClassNamePage('${dto.dept_no}')"
				class="btn btn-default"> <input type="button" value="팀 삭제"
				onclick="delDepatrClassPage('${dto.dept_no}')"
				class="btn btn-default"> <input type="button"
				value="class 인원 수정"
				onclick="modyfyDepatrClassPage('${dto.dept_no}')"
				class="btn btn-default">
		</div>
	</form>

</body>
</html>