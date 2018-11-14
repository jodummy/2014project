<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="fileArea" class="dragAndDropDiv">
	<table id='fileTable'>
		<tr>
			<td id='tabFileName'>파일명</td>
			<td id='tabFileSize'>사이즈</td>
			<td id='tabFileDel'>삭제</td>
		</tr>
	</table>
	<div class="text_bg">
		<span>File Drag &amp; Drop</span>
	</div>
</div>