<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="m" uri="http://lsh.math.ceil.function" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board List</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<script src="./js/common/map.js"></script>
<script src="./js/board/dragDropFile.js"></script>
<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="./js/board/boardSmartEditor.js"></script>
<script src="./js/common/paging.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">

<script>
var title = "";
var content = "<p>&nbsp;</p>";
var keyWord = '${keyWord}';
var searchNum = '${searchNum}';
var dept_no = '${memDto.dept_no}';
var mem_no = '${memDto.mem_no}';
var dept_top = '${memDto.dept_top}';
var ctgr_no = '${paging.ctgr_no}';
var fileUri = "./insertBoardFile.do";
var thisDept = "${thisDept}";
var board_no = "";
$(document).ready(function () {	
	if (ctgr_no == 2) {
		var str = dept_no.charAt(0);
		if (str == 'T') {
			$('input[name="dept_no"]').val(dept_top);
		}
	}
});

// 텍스트 내용을 키워드로 나눈 후 지정된 문자열 삽입
// (키워드를 검색하여 모두 수정하는 효과)
function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

// 기존에 검색한 검색번호를 가져와 셀렉트박스에 고정시킴
function searchNumSelected() {
	$(".searchNum option").each(function () {
		if ($(this).val() == searchNum) {
			$(this).prop("selected", true);
		}
	});
}

// 검색을 통해 페이지를 로딩 할 경우 해당 키워드 배경 색 노랑색 표시
$(document).ready(function (){
	if (keyWord != '') {
		// 기존에 검색한 검색어를 인풋박스에 고정시킴
		$('#keyWordTextBox').val(keyWord);
		// 기존에 검색한 검색어를 상단 메뉴에 고정시킴
		$('#keyWordChk').text(" > '" + keyWord + "' 검색 결과");
		// 기존에 검색한 검색번호를 가져와 셀렉트박스에 고정시킴
		searchNumSelected();
		var splitKey = keyWord.split(" ");
		$('.search'+searchNum).each(function () {
			var str = $(this).text();
			var replaceText = '';
			for (var i = 0; i < splitKey.length; i++) {
				var targetStr = splitKey[i];
				var replaceStr = "<span class='searchRst'>"+splitKey[i]+"</span>";
				replaceText = replaceAll(str, targetStr, replaceStr);
				str = replaceText;
			}
			$(this).html(replaceText);
		});
	}
});

// 체크박스 모두 선택 & 취소
function checkAllDel(bool) {
	var chkVal = document.getElementsByName("chkVal");
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}

// 입력 폼 오픈
function writeForm() {
	$("#writeForm").modal();
}

// 선택 글 삭제
function updateDelBoardChecked() {
	var chkVal = document.getElementsByName("chkVal");
	var chkBool = false;
	for (var i = 0; i < chkVal.length; i++) {
		if (chkVal[i].checked) {
			chkBool = true;
			break;
		};
	}
	if (chkBool) {
		swal({
			  title: "게시글 삭제!",
			  text: "선택한 게시글을 정말 삭제하시겠습니까?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "삭제",
			  cancelButtonText: "취소",
			  closeOnConfirm: false,
			  closeOnCancel: false,
			},
			function(isConfirm) {
			  if (isConfirm) {
				swal({
					title: "삭제 완료!",
					text: "선택하신 글의 삭제가 완료됐습니다.",
					type: "success",
					closeOnConfirm: true,
				}, function () {
					$("#frmUpdateDelBoardChecked").submit();
				});
			  } else {
			    swal("삭제 취소!", "삭제가 취소되었습니다.", "error");
			  }
		});
	}
	else {
		swal("실행 오류!", "삭제할 글을 선택해주세요.", "error");
	}
}
</script>

<style type="text/css">
.board_title {
	text-align: left;
}
#board_title{
	border: 1px solid #b3b3b3;
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
.pageBlank {}
.pageNumbers {
	text-align: center;
	width: 200px;
}
.searchRst {
	background: yellow;
}
</style>

</head>
<body>
<jsp:include page="../common/jumbotron.jsp"></jsp:include>
<jsp:include page="../common/navbar.jsp"></jsp:include>

<div class="container-fluid text-center">  
	<div class="row content">
		<div class="col-md-2 sidenav">
			<jsp:include page="./boardSidebar.jsp"></jsp:include>
		</div>
		<div class="col-md-10 text-left">
			<p class="navi">
				<img src="./images/icon/board.png" alt="게시판" width="14px">
				커뮤니티 > 
			<c:if test="${paging.ctgr_no eq 1}">
				<span>공유 게시판</span><span id="keyWordChk"></span>
			</p>
				<h3>공유 게시판</h3>
			</c:if>
			<c:if test="${paging.ctgr_no eq 2}">
				<span>클래스 게시판</span><span id="keyWordChk"></span>
			</p>
				<h3>&ldquo; ${boardTitleName} &rdquo; 클래스 게시판</h3>
			</c:if>
			<c:if test="${paging.ctgr_no eq 3}">
				<span>팀 게시판</span><span id="keyWordChk"></span>
			</p>
				<h3>&ldquo; ${boardTitleName} &rdquo; 팀 게시판</h3>
			</c:if>
			<div style="float: left;">
				<form class="form-inline" action="./boardSearch.do" method="post" id='frmSearch'>
					<select name="searchNum" class="form-control searchNum">
						<option value="1">제목</option>
						<option value="2">내용</option>
						<option value="3">제목+내용</option>
						<option value="4">작성자</option>
					</select>
					<input type="text" class="form-control" name="keyWord" id="keyWordTextBox"/>
					<input type="submit" class="btn btn-default" value="검색" />
					<input type='hidden' name='ctgr_no' value='${paging.ctgr_no}'>
					<input type='hidden' name='listCnt' value='${paging.listCnt}'>
				</form>
			</div>
			<!-- 한번에 출력할 목록 갯수 -->
			<div style="float: right;">
				<select class='btn btn-primary' id='listCount' name='listCount' onchange='listCnt();'>
					<option value='5'>5</option>
					<option value='10'>10</option>
					<option value='15'>15</option>
					<option value='20'>20</option>
				</select>
			</div>
			<div style="clear: both; height: 5px;"></div>
			<form action="./updateDelBoardChecked.do" method="post" id='frmUpdateDelBoardChecked'>
			<div>
			<table class="table table-bordered" id="boardTable">
				<thead>
					<tr>
						<th style="width: 10px;">
							<input type="checkbox" onclick="checkAllDel(this.checked)" />
						</th>
						<th style="width: 80px;">No.</th><th>제목</th><th style="width: 170px;">작성자</th><th style="width: 100px;">작성일</th><th style="width: 80px;">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(boardNoticeLists) ne 0}">
						<c:forEach items="${boardNoticeLists}" var="bnList" varStatus="vs">
						<tr>
							<td>
								<c:if test="${bnList.mem_no eq memDto.mem_no}">
									<input class="checkbox" name="chkVal" value="${bnList.board_no}" type="checkbox" />
								</c:if>
								<c:if test="${bnList.mem_no ne memDto.mem_no}">
									<input class="checkbox disabled" name="chkVal" value="${bnList.board_no}" type="checkbox" />
								</c:if>
							</td>
							<td><b>공지<b></td>
							<td class="board_title">
								<b><a href="boardDetail.do?ctgr_no=${paging.ctgr_no}&index=${paging.index}&board_no=${bnList.board_no}&rnum=${bnList.rnum}">
									${bnList.board_title}
								</a><b>
								<c:if test="${bnList.board_replycnt ne 0}">
							 		&nbsp;<span class="badge">${bnList.board_replycnt}</span>
							 	</c:if>
							 </td>
							 <td>${bnList.dept_name}&nbsp;${bnList.mem_name}</td>
							<td>${bnList.board_regdate}</td>
							<td>${bnList.board_readcnt}</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(boardLists) eq 0}">
						<tr>
							<td></td><td></td>
							<td>등록된 글이 없습니다.</td>
							<td></td><td></td><td></td>
						</tr>
					</c:if>
					<c:forEach items="${boardLists}" var="bList" varStatus="vs">
						<tr>
							<td>
								<c:if test="${bList.mem_no eq memDto.mem_no || memDto.auth_no eq 0}">
									<input class="checkbox" name="chkVal" value="${bList.board_no}" type="checkbox" />
								</c:if>
								<c:if test="${bList.mem_no ne memDto.mem_no}">
									<input class="checkbox disabled" type="checkbox" />
								</c:if>
							</td>
							<td>${bList.rnum}</td>
							<td class="board_title">
								<a class="search1 search3" href="boardDetail.do?ctgr_no=${paging.ctgr_no}&index=${paging.index}&board_no=${bList.board_no}&rnum=${bList.rnum}">
									${bList.board_title}
								</a>
								<c:if test="${bList.board_replycnt ne 0}">
							 		&nbsp;<span class="badge">${bList.board_replycnt}</span>
							 	</c:if>
							 </td>
							<td class="search4">${bList.dept_name}&nbsp;${bList.mem_name}</td>
							<td>${bList.board_regdate}</td>
							<td>${bList.board_readcnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<input type='hidden' name='ctgr_no' value='${paging.ctgr_no}'>
			</form>
			
			<!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->
			<form action="${url}" method="post" id='frmPaging'>
			   	<input type='hidden' name='index' id='index' value='${paging.index}'>
			   	<input type='hidden' name='pageStartNum' id='pageStartNum' value='${paging.pageStartNum}'>
			   	<input type='hidden' name='listCnt' id='listCnt' value='${paging.listCnt}'>
			   	<input type='hidden' name='ctgr_no' value='${paging.ctgr_no}' />
			   	<c:if test="${paging.ctgr_no ne 1}">
					<input type='hidden' name="dept_no" value='${memDto.dept_no}' />
				</c:if>
				<input type='hidden' id='total' name='total' value='${paging.total}'/>
				<input type='hidden' id='pageLastNum' value='${paging.pageLastNum}'/>
			</form>
			
			<div class="center">
				<table>
					<tr>
						<td class="pageBlank"></td>
						<td class="pageArrow">
							<ul class="pagination">
								<c:if test="${paging.pageStartNum ne 1}">
									<!--맨 첫페이지 이동 -->
									<li class="pagePre"><a href='#' onclick='pagePre(${paging.pageCnt+1},${paging.pageCnt});'>&laquo;</a></li>
									<!--이전 페이지 이동 -->
									<li class="pagePre"><a href='#' onclick='pagePre(${paging.pageStartNum},${paging.pageCnt});'>&lsaquo;</a></li>
								 </c:if>
							</ul>
						</td>
						<td class="pageNumbers">
							<ul class="pagination">
								<!--페이지번호 -->
								<c:forEach var='i' begin="${paging.pageStartNum}" end="${paging.pageLastNum}" step="1">
									<li class='pageIndex${i}'><a href='#' onclick='pageIndex(${i});'>${i}</a></li>
								</c:forEach>
							</ul>
						</td>
						<td class="pageArrow">
							<ul class="pagination">
								<c:if test="${paging.pageLastNum ne m:ceil(paging.total/paging.listCnt)}">
									<!--다음 페이지 이동 -->
									<li class="pageNext"><a href='#' onclick='pageNext(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&rsaquo;</a></li>
									<!--마지막 페이지 이동 -->
									<li class="pageNext"><a href='#' onclick='pageLast(${paging.pageStartNum},${paging.total},${paging.listCnt},${paging.pageCnt});'>&raquo;</a></li>
								</c:if>
							</ul>
						</td>
						<td class="pageBlank"></td>
					</tr>
				</table>
			</div>
			
			<div>
				<div style="float: left;">
					<input type="button" class="btn btn-danger" value="삭제" onclick="updateDelBoardChecked()" />
					<input type="button" class="btn btn-default" onclick="writeForm()" class="w3-btn" value="글쓰기" />
				</div>
				<div style="clear: both;"></div>
			</div>
			<!-- 글작성 modal -->
			<div class="modal fade bs-example-modal-lg" id="writeForm" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">글작성</h4>
						</div>
						<div class="modal-body">
							<form action="./insertBoard.do" method="post" id='frmBoard' enctype="multipart/form-data">
								<table class="table table-bordered">
									<tr>
										<td class="boardAreaTitleModal">작성자</td>
										<td>
											<input type="text" id="board_writer" value="${memDto.dept_name} ${memDto.mem_name}" readonly="readonly"/>
											<input type="hidden" id="mem_no" name="mem_no" value="${memDto.mem_no}" />
											<input type="hidden" id="dept_no" name="dept_no" value="${memDto.dept_no}" />
										</td>
										<td style="width: 30px;">
											<input class="checkbox" id="board_notice" name="board_notice" type="checkbox" value="Y"/>
										</td>
										<td style="font-weight: bold;">공지사항</td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">제&nbsp;&nbsp;&nbsp;목</td>
										<td colspan="3" class="boardAreaTitleModal"><input class="form-control" type="text" id="board_title" name="board_title" /></td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">내&nbsp;&nbsp;&nbsp;용</td>
										<td colspan="3"><textarea name="board_content" id="editor" style="width: 766px; height: 205px;"></textarea></td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">첨&nbsp;&nbsp;&nbsp;부</td>
										<td colspan="3">
											<div id="fileListDiv">
												<jsp:include page="./dragDropFile.jsp"></jsp:include>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<input type='hidden' id='ctgr_no' name='ctgr_no' value='${paging.ctgr_no}'>
											<input class="btn btn-default" type="button" id="resetContent" value="재작성" />
											<input class="btn btn-default" type="button" id="savebutton" value="등록" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>