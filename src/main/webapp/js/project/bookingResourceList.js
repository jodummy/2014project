// 예약일정 생성폼 호출
function insertBookingResourceModal() {
	$('#insertBookingResourceForm').modal();
}
//예약일정 생성 처리
function insertBookingResource() {
	var br_name = $('#br_name').val();
	if(br_name.trim() == '' || br_name.trim().length == 0) {
		swal('이름','입력해주세요');
		return false;
	}
	$('#frmInsertBookingResource').submit();
}
//전체체크
function checkAllDelBooking(bool) {
	var chkVal = document.getElementsByName("chkValBooking");
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}
//예약일정 삭제
function deleteBookingResource() {
	var chkVal = document.getElementsByName("chkValBooking");
	var n = 0;
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked == true){
			n++;
		}
	}
	if(n>0){
		$('#frmDeleteBookingResource').submit();
	}else {
		swal("예약일정 삭제", '선택해주세요');
	}
}
//예약일정 수정 호출
function updateBookingResourceModal() {
	var chkValBooking = document.getElementsByName("chkValBooking");
	var br_names = document.getElementsByName("br_names");
	var n = 0;
	var br_no = '';
	var br_name = '';
	for (var i = 0; i < chkValBooking.length; i++) {
		if(chkValBooking[i].checked == true){
			n++;
			br_no = chkValBooking[i].value;
			br_name = br_names[i].value;
		}
	}
	if(n==1) {
		$('#frmUpdateBookingResource').find('#br_nameModify').val(br_name);
		$('#frmUpdateBookingResource').find('#br_noModify').val(br_no);
	}else if(n>1) {
		swal("예약일정 수정", '1개만 선택해주세요');
		return false;
	}else {
		swal("예약일정 수정", '선택해주세요');
		return false;
	}
	$('#updateBookingResourceForm').modal();
}
//예약일정 수정 처리
function updateBookingResource() {
	$('#frmUpdateBookingResource').submit();
}
//캘린더 생성폼 호출
function calendarAddForm() {
	$('#calendarAddForm').modal();
}
// 캘린더 생성 처리
function calendarAdd() {
	var summary = $('#summary').val();
	if(summary.trim() == '' || summary.trim().length == 0) {
		swal('이름','입력해주세요');
		return false;
	}
	$('#frmCalendarAdd').submit();
}
//전체체크
function checkAllDel(bool) {
	var chkVal = document.getElementsByName("chkVal");
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}
//캘린더 삭제
function calendarRemove() {
	var chkVal = document.getElementsByName("chkVal");
	var n = 0;
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked == true){
			n++;
		}
	}
	if(n>0){
		$('#frmCalendarRemove').submit();
	}else {
		swal("캘린더 삭제", '선택해주세요');
	}
}
// 캘린더 수정 호출
function calendarModifyForm() {
	var chkVal = document.getElementsByName("chkVal");
	var summarys = document.getElementsByName("summarys");
	var n = 0;
	var schc_code = '';
	var summary = '';
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked == true){
			n++;
			schc_code = chkVal[i].value;
			summary = summarys[i].value;
		}
	}
	if(n==1) {
		$('#frmCalendarModify').find('#summaryModify').val(summary);
		$('#frmCalendarModify').find('#schc_codeModify').val(schc_code);
	}else if(n>1) {
		swal("캘린더 수정", '1개만 선택해주세요');
		return false;
	}else {
		swal("캘린더 수정", '선택해주세요');
		return false;
	}
	$('#calendarModifyForm').modal();
}
// 캘린더 수정 처리
function calendarModify() {
	$('#frmCalendarModify').submit();
}
//캘린더 코드 등록
function calendarCodeUpdate() {
	var chkVal = document.getElementsByName("chkVal");
	var chkValBooking = document.getElementsByName("chkValBooking");
	var schc_code = '';
	var br_no = '';
	var n = 0;
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked == true){
			n++;
			schc_code = chkVal[i].value;
		}
	}
	if(n>1) {
		swal("캘린더 코드등록", '캘린더코드를 1개만 선택해주세요');
		return false;
	}else if(n<1) {
		swal("캘린더 코드등록", '캘린더코드를 선택해주세요');
		return false;
	}
	n = 0;
	for (var i = 0; i < chkValBooking.length; i++) {
		if(chkValBooking[i].checked == true){
			n++;
			br_no = chkValBooking[i].value;
		}
	}
	if(n>1) {
		swal("캘린더 코드등록", '예약목록를 1개만 선택해주세요');
		return false;
	}else if(n<1) {
		swal("캘린더 코드등록", '예약목록를 선택해주세요');
		return false;
	}
	$('#br_no').val(br_no);
	$('#schc_code').val(schc_code);
	$('#frmUpdateBookingResouceCode').submit();
}