// 회의실 예약 이동 처리
function moveBooking(br_no) {
	$('#br_no').val(br_no);
	$('#meetingRoomForm').submit();
}