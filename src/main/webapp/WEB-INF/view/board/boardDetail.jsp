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
<title>Board Detail</title>
<script src="./js/common/jquery-3.1.1.min.js"></script>
<script src="./js/common/bootstrap.min.js"></script>
<script type="text/javascript" src='./js/common/sweetalert.min.js?ver=1'></script>
<script src="./js/common/map.js"></script>
<script src="./js/board/dragDropFile.js"></script>
<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="./js/board/boardSmartEditor.js"></script>
<script src="./js/board/replyArea.js"></script>
<link rel="stylesheet" href="./css/common/bootstrap.min.css">
<link rel="stylesheet" href="./css/common/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href='./css/common/sweetalert.css?ver=1.2'>
<link rel="stylesheet" href="./css/board/dragDropFile.css">
<link rel="stylesheet" href="./css/board/modalArea.css">
<link rel="stylesheet" href="./css/board/replyArea.css">
<script src="./js/common/stringBuffer.js"></script>
<script>
var ctgr_no = '${paging.ctgr_no}';
var board_no = '${boardDto.board_no}';
var rnum = '${boardDto.rnum}';
var index = '${paging.index}';
var dept_name = '${memDto.dept_name}';
var dept_no = '${memDto.dept_no}';
var dept_top = '${memDto.dept_top}';
var mem_name = '${memDto.mem_name}';
var mem_no = '${memDto.mem_no}';
var boardMem_no = '${boardDto.mem_no}';
var content = '${boardDto.board_content}';
var title = "${boardDto.board_title}";
var auth_no = "${memDto.auth_no}";
var keyWord = '${keyWord}';
var notice = "${boardDto.board_notice}";
var fileUri = "./updateBoardFile.do";
var thisDept = "${thisDept}";
$(document).ready(function (){
	if (keyWord != '') {
		// 기존에 검색한 검색어를 상단 메뉴에 고정시킴
		$('#keyWordChk').text(" > '" + keyWord + "' 검색 결과");
	}
});
function updateWriteForm() {
	$("#updateWriteForm").modal();
	if ("${boardDto.board_notice}" == "Y") {
		$("#board_notice").prop("checked", true);
	}
}
function delContent() {
	swal({
		title: "게시글 삭제!",
		text: "게시글을 정말 삭제하시겠습니까?",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "삭제",
		cancelButtonText: "취소",
		closeOnConfirm: false,
		closeOnCancel: false
		},
		function(isConfirm) {
			if (isConfirm) {
				swal("삭제 완료!", "선택한 글의 삭제가 완료되었습니다.", "success");
				location.href="updateDelBoard.do?board_no="+${boardDto.board_no}+"&ctgr_no="+${paging.ctgr_no};	
		  	} else {
		    	swal("삭제 취소!", "삭제가 취소되었습니다.", "error");
			}
	});
}


</script>
<style type="text/css">
	.boardDetailAreaTitle {
		text-align: center;
		font-weight: bold;
		width: 105px;
	}
	#fileArea {
		display: none;
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
				커뮤니티 > 
			<c:if test="${paging.ctgr_no eq 1}">
				<span>공유 게시판</span><span id="keyWordChk"></span><span> > 상세페이지</span>
			</p>
				<h3>공유 게시판</h3>
			</c:if>
			<c:if test="${paging.ctgr_no eq 2}">
				<span>클래스 게시판</span><span id="keyWordChk"></span><span> > 상세페이지</span>
			</p>
				<h3>&ldquo; ${boardTitleName} &rdquo; 클래스 게시판</h3>
			</c:if>
			<c:if test="${paging.ctgr_no eq 3}">
				<span>팀 게시판</span><span id="keyWordChk"></span><span> > 상세페이지</span>
			</p>
				<h3>&ldquo; ${boardTitleName} &rdquo; 팀 게시판</h3>
			</c:if>
			<table id="boardDetailArea" class="table table-bordered">
				<tr>
					<td class="boardDetailAreaTitle">No.</td><td>
						<c:if test="${boardDto.board_notice eq 'N'}">
							<input type="text" value="${boardDto.rnum}" readonly="readonly"/>
						</c:if>
						<c:if test="${boardDto.board_notice eq 'Y'}">
							<input type="text" value="공지" readonly="readonly"/>
						</c:if>
						<input type="hidden" id="board_no" value="${boardDto.board_no}" />
					</td>
					<td class="boardDetailAreaTitle">등록일</td><td><input type="text" value="${boardDto.board_regdate}" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="boardDetailAreaTitle">작성자</td><td><input type="text" value="${boardDto.dept_name} ${boardDto.mem_name}" readonly="readonly"/></td>
					<td class="boardDetailAreaTitle">조&nbsp;&nbsp;&nbsp;회</td><td><input type="text" value="${boardDto.board_readcnt}" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="boardDetailAreaTitle">제&nbsp;&nbsp;&nbsp;목</td><td colspan="3"><input type="text" value="${boardDto.board_title}" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="boardDetailAreaTitle">내&nbsp;&nbsp;&nbsp;용</td><td colspan="3"><div class="contentArea">${boardDto.board_content}</div></td>
				</tr>
				<c:if test="${bfLists.size() ne 0}">
					<tr>
						<td class="boardDetailAreaTitle">첨&nbsp;&nbsp;&nbsp;부</td>
						<td colspan="3">
							<div id="parentFileListDiv">
								<table id="parentFileListTable">
									<c:forEach items="${bfLists}" var="bfList" varStatus="vs">
										<tr id="parentBfTr${bfList.bf_no}">
											<td>${bfList.bf_oldnm}</td>
											<td style="width: 75px;">
												${bfList.bf_regdate}
											</td>
											<td style="width: 65px;">
												<input class="btn btn-link btn-xs" type="button" onclick="fileDownload(${vs.count})" value="다운로드" />
												<form action="./BoardFileDown.do" id="frmFileDownload${vs.count}" method="post">
												<input type="hidden" name="bf_no" value="${bfList.bf_no}" />
												<input type="hidden" name="bf_oldnm" value="${bfList.bf_oldnm}" />
												<input type="hidden" name="bf_savenm" value="${bfList.bf_savenm}" />
												</form>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</td>
					</tr>
				</c:if>
			</table>
			<div>
				<div style="float: left">
					<c:if test="${fn:length(keyWord) eq 0}">
						<form action="./boardList.do" method="post" id='listFrm'>
					</c:if>
					<c:if test="${keyWord ne ''}">
						<form action="./boardSearch.do" method="post" id='searchListFrm'>
					</c:if>
					<c:if test="${boardDto.mem_no eq memDto.mem_no || memDto.auth_no eq 0}">
						<input class="btn btn-danger" type="button" value="삭제" onclick="delContent()" />
						<input class="btn btn-default" type="button" value="수정" onclick="updateWriteForm()" />
					</c:if>
						<input class="btn btn-default" type="submit" value="글목록" />
						<input type='hidden' name='index' id='index' value='${paging.index}'>
				       	<input type='hidden' name='pageStartNum' id='pageStartNum' value='${paging.pageStartNum}'>
				       	<input type='hidden' name='listCnt' id='listCnt' value='${paging.listCnt}'>
				       	<input type='hidden' name='ctgr_no' value='${paging.ctgr_no}'>
						<c:if test="${paging.ctgr_no eq 2}">
							<c:if test="${fn:substring(memDto.dept_no, 0, 1) eq 'C'}">
				       			<input type='hidden' name='dept_no' value='${memDto.dept_no}'>
				       		</c:if>
				       		<c:if test="${fn:substring(memDto.dept_no, 0, 1) ne 'C'}">
				       			<input type='hidden' name='dept_no' value='${memDto.dept_top}'>
				       		</c:if>
				       	</c:if>
				       	<c:if test="${paging.ctgr_no eq 3}">
				       		<input type='hidden' name='dept_no' value='${memDto.dept_no}'>
				       	</c:if>
					</form>
				</div>
				<button class="btn btn-default" style="float: right;" onclick="replyAreaToggle()" >
					댓글
					<span id="replyCnt">
					<c:if test="${boardDto.board_replycnt ne 0}">
						<span class="badge">${boardDto.board_replycnt}</span>
					</c:if>
					</span>
				</button>
				<div style="clear: both;"></div>
			</div>
			&nbsp;<br/>
			<!-- 글수정 modal -->
			<div class="modal fade" id="updateWriteForm" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">글 수정</h4>
						</div>
						<div class="modal-body">
							<form action="./updateBoard.do" method="post" id='frmBoard'>
								<table class="table table-bordered">
									<tr>
										<td class="boardAreaTitleModal">작성자</td>
										<td>
											<input type="text" value="${boardDto.dept_name} ${boardDto.mem_name}" readonly="readonly"/>
										</td>
										<td style="width: 30px;"><input id="board_notice" name="board_notice" type="checkbox" value="Y"/></td><td style="font-weight: bold;">공지사항</td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">제&nbsp;&nbsp;&nbsp;목</td><td colspan="3"><input id="board_title" type="text" name="board_title" value="${boardDto.board_title}" /></td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">내&nbsp;&nbsp;&nbsp;용</td>
										<td colspan="3">
											<textarea name="board_content" id="editor" style="width: 766px; height: 205px;">${boardDto.board_content}</textarea>
										</td>
									</tr>
									<tr>
										<td class="boardAreaTitleModal">
											첨&nbsp;&nbsp;&nbsp;부<br/>
											<input class="btn btn-link btn-xs" type="button" onclick="openFileArea()" value="[ 추가 ]"/>
										</td>
										<td colspan="3">
											<div id="fileListDiv">
											<div id="fileNoneMsg"></div>
											<c:if test="${bfLists.size() ne 0}">
												<table id="fileListTable">
													<tbody id="bfTrs">
													<c:forEach items="${bfLists}" var="bfList" varStatus="vs">
														<tr id="bfTr${bfList.bf_no}">
															<td>${bfList.bf_oldnm}</td>
															<td style="width: 75px;">${bfList.bf_regdate}</td>
															<td style="width: 40px; text-align: right;">
																<input type="button" class="btn btn-default btn-xs" onclick="deleteBoardFile(${bfList.bf_no}, '${bfList.bf_savenm}')" value="삭제" />
															</td>
														</tr>
													</c:forEach>
													</tbody>
												</table>
											</c:if>
											<c:if  test="${bfLists.size() eq 0}">
												<div style="color: red;">첨부파일이 없습니다.</div>
											</c:if>
												<jsp:include page="./dragDropFile.jsp"></jsp:include>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<input type='hidden' id="ctgr_no" name='ctgr_no' value='${paging.ctgr_no}'>
											<input type='hidden' id="board_no" name='board_no' value='${boardDto.board_no}'>
											<input type='hidden' name='rnum' value='${boardDto.rnum}'>
											<input class="btn btn-default" type="button" id="resetContent" value="재작성" />
											<input class="btn btn-default" type="button" id="savebutton"  value="수정등록" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="./replyArea.jsp"></jsp:include>
		</div>
	</div>
</div>
<br/>
</body>
</html>