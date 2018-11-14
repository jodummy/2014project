<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="replyArea">
	<form action="./insertBoardReply.do" method="post" id="frmInsertBoardReply">
	<table>
		<tr>
			<td style="width: 100px;" rowspan="2">
				<div style="text-align: center; border-radius: 8px; border: 1px solid gray; color: gray; font-weight: bold; font-size: 15px;">
					<br/><br/>
					댓&nbsp;글&nbsp;작&nbsp;성
					<br/><br/>&nbsp;
				</div>
			</td>
			<td style="width: 5px;" rowspan="2">
			<td><input type="text" value="${memDto.dept_name} ${memDto.mem_name}" readonly="readonly" /></td>
		</tr>
		<tr>
			<td><textarea style="width: 100%;" rows="3" class="form-control" id="brContent" name="br_content" required="required"></textarea></td>
			<td style="width: 5px;" rowspan="2">
			<td style="width: 90px; vertical-align: middle;">
				<input class="btn btn-default" style="width: 89px; height: 50px;" type="button" value="댓글등록" onclick="insertBoardReply()" />
			</td>
		</tr>
	</table>
		<input type="hidden" id="brBoard_no" name="board_no" value="${boardDto.board_no}" />
		<input type="hidden" id="brMem_no" name="mem_no" value="${memDto.mem_no}" />
		<input type="hidden" id="brDept_no" name="dept_no" value="${memDto.dept_no}" />
	</form>
	<div id="replyAreaList"></div>
</div>