<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.leftSidebar {
	float: left;
}
.leftSidebarIcon {
	float: right;
}
</style>

<div id="ss" class="nav-side-menu">
	<div class="pad titleApproval">
		<hr/>
		<h3 id="txt">
			<a href="./communityMain.do" class="list-group-item"><b>커뮤니티</b></a>
		</h3>
	</div>
	<hr/>
	<div>
		<c:if test="${fn:substring(memDto.dept_no, 0, 1) eq 'T'}">
			<a href="./boardList.do?ctgr_no=3&dept_no=${memDto.dept_no}" class="list-group-item">
				<span class="leftSidebar">팀 게시판</span>
				<img class="leftSidebarIcon" src="./images/approval/arrowright.png" width="13px" alt="우측화살표"> <br/>
			</a>
			<div style="clear: both;"></div>
		</c:if>

		<c:if test="${fn:substring(memDto.dept_no, 0, 1) eq 'C'}">
			<a href="./boardList.do?ctgr_no=2&dept_no=${memDto.dept_no}" class="list-group-item">
				<span class="leftSidebar">클래스 게시판</span>
				<img class="leftSidebarIcon" src="./images/approval/arrowright.png" width="13px" alt="우측화살표" > <br/>
			</a>
			<div style="clear: both;"></div>
		</c:if>
		<c:if test="${fn:substring(memDto.dept_no, 0, 1) ne 'C'}">
			<a href="./boardList.do?ctgr_no=2&dept_no=${memDto.dept_top}" class="list-group-item">
				<span class="leftSidebar">클래스 게시판</span>
				<img class="leftSidebarIcon" src="./images/approval/arrowright.png" width="13px" alt="우측화살표"> <br/>
			</a>
			<div style="clear: both;"></div>
		</c:if>
		<a href="./boardList.do?ctgr_no=1" class="list-group-item">
			<span class="leftSidebar">공유 게시판</span>
			<img class="leftSidebarIcon" src="./images/approval/arrowright.png" width="13px" alt="우측화살표"> <br/>
		</a>
		<div style="clear: both;"></div>
	</div>
	<hr/>
</div>
