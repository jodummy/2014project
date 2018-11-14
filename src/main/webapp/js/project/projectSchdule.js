var subData = {} // List<projectSchduleDto>
/////////////////////////////////////////////////////// 테이블 thead 그리기위해 쓰는 function
// 배열을 받아 태그로 만들어준다.
function tagMonthOne(dates) {
	var tag = new StringBuffer();
	for(var i=0; i<dates.length; i++) {
		tag.append(tdTagFormatDay(dates[i]));
	}
	return tag.toString();
}
// 매개변수로 받은 월부터 마지막 12까지
function LastMonths(year, month) {
	var tag = new StringBuffer();
	for(var i=Number(month); i<=12; i++) {
		tag.append(tagMonthOne(calendar.makeOne(year, i)));
	}
	return tag.toString();
}
// 매개변수로 받은 월이하 1이상
function startMonths(year, month) {
	var tag = new StringBuffer();
	for(var i=1; i<=Number(month); i++){
		tag.append(tagMonthOne(calendar.makeOne(year, i)));
	}
	return tag.toString();
}
// 숫자 5 -> 05 변경
function numFormat(num) {
	var str = ''+num;
	if(num<10 && str.indexOf('0') == -1) {
		str = '0'+num;
	}
	return str;
}
// 달력의 해당 날짜의 요일을 구하기위해 현재위치 반환
function monthDayIndex(month, day) {
	for(var i=0; i<month.length; i++) {
		if(month[i]==day) {
			return i;
		}
	}
}
// 시작월부터 종료월의 colspan값을 구해서 td 반환
function yearMonth(year, startMonth, lastMonth) {
	var tag = new StringBuffer();
	if(calendar.iscLeafCheck(year)) {
		for(var i=Number(startMonth)-1; i<Number(lastMonth); i++) {
			tag.append(tdTagFormatMonth(calendar.LEAF[i], year, i+1));
		}
	}else {
		for(var i=Number(startMonth)-1; i<Number(lastMonth); i++) {
			tag.append(tdTagFormatMonth(calendar.PLAIN[i], year, i+1));
		}
	}
	return tag.toString();
}
// td 월 한칸 포맷
function tdTagFormatMonth(colspan, year, month) {
	return "<td class='removeThead' colspan='"+colspan+"'>"+year+"-"+numFormat(month)+"</td>";
}
// td 주차 한칸 포맷
function tdTagFormatWeek(colspan, week) {
	return "<td class='removeThead' colspan='"+colspan+"'>"+week+"주차</td>";
}
// td 날짜 한칸 포맷
function tdTagFormatDay(day) {
	return "<td class='removeThead'><div style='width: 60px;'>"+numFormat(day)+"</div></td>";
}
// 테이블 thead 화면에 그리는 함수
function screenWriteThead() {
	// 월처리
	var tag = new StringBuffer();
	if(baseData.start[0] != baseData.last[0]) {
		if(calendar.iscLeafCheck(baseData.start[0])) {
			var startMonthCnt = calendar.LEAF[baseData.start[1]-1] - baseData.start[2] + 1;
			tag.append(tdTagFormatMonth(startMonthCnt, baseData.start[0], baseData.start[1]));
		}else {
			var startMonthCnt = calendar.PLAIN[baseData.start[1]-1] - baseData.start[2] + 1;
			tag.append(tdTagFormatMonth(startMonthCnt, baseData.start[0], baseData.start[1]));
		}
		tag.append(yearMonth(baseData.start[0], Number(baseData.start[1])+1, 12));
		if(Number(baseData.last[0])-Number(baseData.start[0]) > 1) {
			for(var i=Number(baseData.start[0])+1; i<Number(baseData.last[0]); i++) {
				tag.append(yearMonth(i, 1, 12));
			}
		}
		tag.append(yearMonth(baseData.last[0], 1, Number(baseData.last[1])-1));
		if(calendar.iscLeafCheck(baseData.last[0])) {
			tag.append(tdTagFormatMonth(baseData.last[2], baseData.last[0], baseData.last[1]));
		}else {
			tag.append(tdTagFormatMonth(baseData.last[2], baseData.last[0], baseData.last[1]));
		}
	}else if(baseData.start[1] == baseData.last[1]) {
		tag.append(tdTagFormatMonth(baseData.schdule(), baseData.start[0], baseData.start[1]));
	}else {
		if(calendar.iscLeafCheck(baseData.start[0])) {
			var startMonthCnt = calendar.LEAF[baseData.start[1]-1] - baseData.start[2] + 1;
			tag.append(tdTagFormatMonth(startMonthCnt, baseData.start[0], baseData.start[1]));
			tag.append(yearMonth(baseData.start[0], Number(baseData.start[1])+1, Number(baseData.last[1])-1));
			tag.append(tdTagFormatMonth(baseData.last[2], baseData.last[0], baseData.last[1]));
		}else {
			var startMonthCnt = calendar.PLAIN[baseData.start[1]-1] - baseData.start[2] + 1;
			tag.append(tdTagFormatMonth(startMonthCnt, baseData.start[0], baseData.start[1]));
			tag.append(yearMonth(baseData.start[0], Number(baseData.start[1])+1, Number(baseData.last[1])-1));
			tag.append(tdTagFormatMonth(baseData.last[2], baseData.last[0], baseData.last[1]));
		}
	}
	$("#thead tr:eq(0)").append(tag.toString());
	// 주차처리
	tag = new StringBuffer();
	var startDayIndex = monthDayIndex(calendar.make(baseData.start[0], baseData.start[1]), baseData.start[2]);
	var lastDayIndex = monthDayIndex(calendar.make(baseData.last[0], baseData.last[1]), baseData.last[2]);
	var startDayOfWeek = 7 - startDayIndex % 7;
	var lastDayOfWeek = lastDayIndex % 7;
	var remain = (baseData.schdule() - startDayOfWeek) / 7;
	tag.append(tdTagFormatWeek(startDayOfWeek, 1));
	for(var i=2; i<remain+2; i++) {
		tag.append(tdTagFormatWeek(7, i));
	}
	$("#thead tr:eq(1)").append(tag.toString());
	// 날짜처리
	var tag = new StringBuffer();
	if(baseData.start[0] != baseData.last[0]) {
		for(var i=Number(baseData.start[2]); i<=calendar.lastDay(baseData.start[0], baseData.start[1]); i++) {
			tag.append(tdTagFormatDay(i));
		}
		if(Number(baseData.last[0])-Number(baseData.start[0]) > 1) {
			tag.append(LastMonths(baseData.start[0], Number(baseData.start[1])+1));
			for(var i=Number(baseData.start[0])+1; i<Number(baseData.last[0]); i++) {
				tag.append(startMonths(i, 12));
			}
			tag.append(startMonths(baseData.last[0], Number(baseData.last[1])-1));
		}else {
			tag.append(LastMonths(baseData.start[0], Number(baseData.start[1])+1));
			tag.append(startMonths(baseData.last[0], Number(baseData.last[1])-1));
		}
		for(var i=1; i<=Number(baseData.last[2]); i++) {
			tag.append(tdTagFormatDay(i));
		}
	}else if(baseData.start[1] == baseData.last[1]) {
		for(var i=Number(baseData.start[2]); i<=Number(baseData.last[2]); i++){
			tag.append(tdTagFormatDay(i));
		}
	}else {
		var lastDay = calendar.lastDay(baseData.start[0], baseData.start[1]);
		for(var i=Number(baseData.start[2]); i<=lastDay; i++) {
			tag.append(tdTagFormatDay(i));
		}
		for(var i=Number(baseData.start[1])+1; i<Number(baseData.last[1]); i++) {
			tag.append(tagMonthOne(calendar.makeOne(baseData.start[0], i)));
		}
		for(var i=1; i<=Number(baseData.last[2]); i++){
			tag.append(tdTagFormatDay(i));
		}
	}
	$("#thead tr:eq(2)").append(tag.toString());
	// 총, 일간 진척률 처리
	tag = new StringBuffer();
	for(var i=0; i<baseData.schdule(); i++) {
		tag.append("<td class='removeThead progressDaySum'></td>");
	}
	$("#thead tr:eq(3)").append(tag.toString());
	tag = new StringBuffer();
	for(var i=0; i<baseData.schdule(); i++) {
		tag.append("<td class='removeThead progressDay'></td>");
	}
	$("#thead tr:eq(4)").append(tag.toString());
	tag = new StringBuffer();
	for(var i=0; i<baseData.schdule(); i++) {
		tag.append("<td class='removeThead'></td>");
	}
	$("#thead tr:eq(5)").append(tag.toString());
	
}
/////////////////////////////////////////////////////// 테이블 tbody 그리기위해 쓰는 function
// 배열로 반환한다.
function byNameArray(data) {
	var dataArray = new Array();
	for(var i=0; i<data.length; i++) {
		dataArray[i] = data[i].value;
	}
	return dataArray;
}
// 문자열을쪼개서 값을 검사한다.
function termAnalsis(data, cnt) {
	var tag = new StringBuffer();
	for(var i=0; i<cnt; i++) {
		if(data.charAt(i)=='1') {
			tag.append("<td style='background-color: yellow;'><input type='hidden' name='inPrjs_term' value='1'></td>");
		}else {
			tag.append("<td><input type='hidden' name='inPrjs_term' value='0'></td>");
		}
	}
	return tag.toString();
}
// 일정체크 이벤트
function schduleEvent() {
	$('input[name=inPrjs_term]').parent('td').off().click(function(){
		if($(this).find('input').val() == 0) {
			$(this).css('background-color','yellow').find('input').attr('value', 1);
			progress();
		}else {
			$(this).css('background-color', '').find('input').attr('value', 0);
			progress();
		}
	});
}
// 색갈만 처리
function schduleColor() {
	$('input[name=inPrjs_term]').parent('td').each(function(){
		if($(this).find('input').val() == 0) {
			$(this).css('background-color', '');
		}else {
			$(this).css('background-color','yellow');
		}
	});
	progress();
}
// 전체체크
function checkAllDel(bool) {
	var chkVal = document.getElementsByName("chkVal");
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}
// 삭제처리
function checkListRemove() {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var isc = 0;
		while(true) {
			var chkVal = document.getElementsByName("chkVal");
			for (var i=0,cnt=0; i<chkVal.length; i++) {
				if(chkVal[i].checked) {
					cnt++;
				}
			}
			if(cnt==0) {
				break;
			}
			isc++;
			for (var i=0; i<chkVal.length; i++) {
				if(chkVal[i].checked) {
					var myPrjs_refer = $('#tbody tr:eq('+chkVal[i].value+')').find('input[name=inPrjs_refer]').val();
					var myPrjs_step = $('#tbody tr:eq('+chkVal[i].value+')').find('input[name=inPrjs_step]').val();
					$('#tbody tr:gt('+chkVal[i].value+')').attr('id',function(){
						return Number($(this).attr('id')) - 1;
					}).find('input[name=chkVal]').attr('value', function(){
						return Number($(this).val()) - 1;
					}).end().find('input[name=inPrjs_refer]').attr('value',function(){
						if(myPrjs_refer == $(this).val() && myPrjs_step != '0') {
							$(this).parent('td').find('input[name=inPrjs_step]').attr('value', function(){
								return Number($(this).parent('td').find('input[name=inPrjs_step]').val()) - 1;
							});
							return $(this).val();
						}else if(myPrjs_step != '0') {
							return $(this).val();
						}else {
							return Number($(this).val()) - 1;
						}
					}).end().find('button').text(function(){
						return numFormat(Number($(this).text()) - 1);
					}).end().find('a').attr('onclick', function(){
						if($(this).attr('onclick').indexOf('listCreateDepth') != -1) {
							return 'listCreateDepth('+$(this).parents("tr").attr("id")+')';
						}else if($(this).attr('onclick').indexOf('listCreateDiff') != -1) {
							return 'listCreateDiff('+$(this).parents("tr").attr("id")+')';
						}else {
							return 'listCreate('+$(this).parents("tr").attr("id")+')';
						}
					});
					$('#tbody tr:eq('+chkVal[i].value+')').remove();
					break;
				}
			}
		}
		if(isc==0) {
			swal("삭제", '선택해주세요');
		}
		if($('#tbody tr').length == 0) {
			baseData.chk = false;
		}
		subData.cnt = $('#tbody tr').length;
//	}else {
//		swal('삭제', '자신의 팀이 아닙니다');
//	}
}
// 프로젝트 기간수정 팝업창
function projectSchduleModal() {
	$("#projectSchduleModal").modal();
}
// 프로젝트 기간 수정 처리
function reScreenWriteThead() {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var prj_start = $('#prj_start').val();
		var prj_last = $('#prj_last').val();
		var start = new Date(prj_start);
		var last = new Date(prj_last);
		if(prj_start == ''){
			swal('시작일', '입력해주세요');
			return false;
		}else if(prj_last == ''){
			swal('종료일', '입력해주세요');
			return false;
		}else if((last.getTime()-start.getTime())/(1000*60*60*24) < 0){
			swal('프로젝트 기간', '시작일이 종료일보다 늦습니다');
			return false;
		}else if((((last.getTime()-start.getTime())/(1000*60*60*24)))+1 > 4000){
			swal('프로젝트 기간', '4000일 초과입니다.');
			return false;
		}
		$("#projectSchduleModal").modal('hide');
		$("#thead .removeThead").remove();
		baseData.start = $('#prj_start').val().split("-");
		baseData.last = $('#prj_last').val().split("-");
		screenWriteThead();
		if(baseData.chk) {
			var reallyTdCnt = $('#tbody tr:eq(0) td:gt(4)').length;
			var tag = new StringBuffer();
			if(reallyTdCnt != baseData.schdule() && reallyTdCnt < baseData.schdule()) {
				for(var i=0; i<baseData.schdule()-reallyTdCnt; i++) {
					tag.append("<td><input type='hidden' name='inPrjs_term' value='0'></td>");
				}
				for(var i=0; i<subData.cnt; i++) {
					$('#tbody tr:eq('+i+')').append(tag.toString());
				}
				schduleEvent();
			}else {
				for(var i=0; i<subData.cnt; i++) {
					$('#tbody tr:eq('+i+') td:gt('+(baseData.schdule()+4)+')').remove();
				}
			}
		}
//	}else {
//		swal('기간수정', '자신의 팀이 아닙니다');
//	}
}
// 처음 목록 생성
function listCreateTop() {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		if(baseData.chk) {
			$('#tbody tr').attr('id',function(){
				return Number($(this).attr('id'))+1;
			}).find('input[name=chkVal]').attr('value', function(){
				return Number($(this).val()) + 1;
			}).end().find('input[name=inPrjs_refer]').attr('value', function(){
				return Number($(this).val()) + 1;
			}).end().find('button').text(function(){
				return numFormat(Number($(this).text()) + 1);
			}).end().find('a').attr('onclick', function(){
				if($(this).attr('onclick').indexOf('listCreateDepth') != -1) {
					return 'listCreateDepth('+$(this).parents("tr").attr("id")+')';
				}else if($(this).attr('onclick').indexOf('listCreateDiff') != -1) {
					return 'listCreateDiff('+$(this).parents("tr").attr("id")+')';
				}else {
					return 'listCreate('+$(this).parents("tr").attr("id")+')';
				}
			});
		}
		$('#tbody').prepend(screenWriteTbody(0, 0, 0, 0, '', '', ''));
		schduleEvent();
		subData.cnt = $('#tbody tr').length;
		baseData.chk = true;
		progress();
//	} else {
//		swal('생성', '자신의 팀이 아닙니다');
//	}
}
// 상위추가
function listCreateDiff(num) {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var myPrjs_refer = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_refer]').val();
		var myPrjs_step = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_step]').val();
		var myPrjs_depth = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_depth]').val();
		var sumStep = 1;
		$('#tbody tr:gt('+num+')').attr('id',function(){
			return Number($(this).attr('id')) + 1;
		}).find('input[name=chkVal]').attr('value', function(){
			return Number($(this).val()) + 1;
		}).end().find('input[name=inPrjs_refer]').attr('value', function(){
			if(myPrjs_depth == '0') {
				return Number($(this).val()) + 1;
			}else if(myPrjs_depth == '1') {
				if(myPrjs_refer == $(this).val()) {
					$(this).parent('td').find('input[name=inPrjs_step]').attr('value', function(){
						return sumStep++;
					});				
				}
				return Number($(this).val()) + 1;
			}else {
				if(myPrjs_refer == $(this).val) {
					$(this).parent('td').find('input[name=inPrjs_step]').attr('value', function(){
						return Number($(this).parent('td').find('input[name=inPrjs_step]').val()) + 1;
					});				
				}
				return $(this).val();
			}
		}).end().find('button').text(function(){
			return numFormat(Number($(this).text()) + 1);
		}).end().find('a').attr('onclick', function(){
			if($(this).attr('onclick').indexOf('listCreateDepth') != -1) {
				return 'listCreateDepth('+$(this).parents("tr").attr("id")+')';
			}else if($(this).attr('onclick').indexOf('listCreateDiff') != -1) {
				return 'listCreateDiff('+$(this).parents("tr").attr("id")+')';
			}else {
				return 'listCreate('+$(this).parents("tr").attr("id")+')';
			}
		});
		if(myPrjs_depth == '0') {
			$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, Number(myPrjs_refer)+1, 0, 0, '', '', ''));
		}else if(myPrjs_depth == '1') {
			$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, Number(myPrjs_refer)+1, 0, 0, '', '', ''));
		}else {
			$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, myPrjs_refer, Number(myPrjs_step)+1, Number(myPrjs_depth)-1, '', '', ''));
		}
		schduleEvent();
		subData.cnt = $('#tbody tr').length;
		progress();
//	}else {
//		swal('상위추가', '자신의 팀이 아닙니다');
//	}
}
// 목록추가
function listCreate(num) {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var myPrjs_refer = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_refer]').val();
		var myPrjs_step = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_step]').val();
		var myPrjs_depth = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_depth]').val();
		$('#tbody tr:gt('+num+')').attr('id',function(){
			return Number($(this).attr('id')) + 1;
		}).find('input[name=chkVal]').attr('value', function(){
			return Number($(this).val()) + 1;
		}).end().find('input[name=inPrjs_refer]').attr('value', function(){
			if($(this).val() == myPrjs_refer) {
				if(myPrjs_step == '0') {
					return Number($(this).val()) + 1;
				}else {
					$(this).parent('td').find('input[name=inPrjs_step]').attr('value', function(){
						return Number($(this).parent('td').find('input[name=inPrjs_step]').val()) + 1;
					});
					return $(this).val();
				}
			}else if(myPrjs_step != '0') {
				return $(this).val();
			}else {
				return Number($(this).val()) + 1;
			}
		}).end().find('button').text(function(){
			return numFormat(Number($(this).text()) + 1);
		}).end().find('a').attr('onclick', function(){
			if($(this).attr('onclick').indexOf('listCreateDepth') != -1) {
				return 'listCreateDepth('+$(this).parents("tr").attr("id")+')';
			}else if($(this).attr('onclick').indexOf('listCreateDiff') != -1) {
				return 'listCreateDiff('+$(this).parents("tr").attr("id")+')';
			}else {
				return 'listCreate('+$(this).parents("tr").attr("id")+')';
			}
		});
		if(myPrjs_step == '0') {
			$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, Number(myPrjs_refer)+1, 0, 0, '', '', ''));
		}else {
			$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, myPrjs_refer, Number(myPrjs_step)+1, myPrjs_depth, '', '', ''));
		}
		schduleEvent();
		subData.cnt = $('#tbody tr').length;
		progress();
//	}else {
//		swal('목록추가', '자신의 팀이 아닙니다');
//	}
}
// 하위추가
function listCreateDepth(num) {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var myPrjs_refer = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_refer]').val();
		var myPrjs_step = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_step]').val();
		var myPrjs_depth = $('#tbody tr:eq('+num+')').find('input[name=inPrjs_depth]').val();
		$('#tbody tr:gt('+num+')').attr('id',function(){
			return Number($(this).attr('id'))+1;
		}).find('input[name=chkVal]').attr('value', function(){
			return Number($(this).val()) + 1;
		}).end().find('input[name=inPrjs_step]').attr('value', function(){
			if($(this).parent('td').find('input[name=inPrjs_refer]').val() == myPrjs_refer) {
				return Number($(this).val()) + 1;
			}else {
				return $(this).val();
			}
		}).end().find('button').text(function(){
			return numFormat(Number($(this).text()) + 1);
		}).end().find('a').attr('onclick', function(){
			if($(this).attr('onclick').indexOf('listCreateDepth') != -1) {
				return 'listCreateDepth('+$(this).parents("tr").attr("id")+')';
			}else if($(this).attr('onclick').indexOf('listCreateDiff') != -1) {
				return 'listCreateDiff('+$(this).parents("tr").attr("id")+')';
			}else {
				return 'listCreate('+$(this).parents("tr").attr("id")+')';
			}
		});
		$('#tbody tr:eq('+num+')').after(screenWriteTbody(num+1, myPrjs_refer, Number(myPrjs_step)+1, Number(myPrjs_depth)+1, '', '', ''));
		schduleEvent();
		subData.cnt = $('#tbody tr').length;
		progress();
//	} else {
//		swal('하위추가', '자신의 팀이 아닙니다');
//	}
}
// 저장하기전 유효성검사
function insertProjectSchdule() {
//	if($('#prjDept_no').val() == $('#memDept_no').val()) {
		var prjs_items = byNameArray(document.getElementsByName("inPrjs_item"));
		var chk = true;
		for(var i=0; i<prjs_items.length; i++) {
			if(prjs_items[i].trim() == '' || prjs_items[i].trim().length == 0) {
				chk = false;
				break;
			}
		}
		if(chk) {
			ajaxSchdule();
		}else {
			swal('저장', '업무구분을 모두 입력해주세요');
		}
//	}else {
//		swal('저장', '자신의 팀이 아닙니다');
//	}
}
// 저장 ajax
var ajaxSchdule = function() {
	$.ajax({
		url: './insertProjectSchdule.do',
		type: 'post',
		async: false,
		data: $('#insertProjectSchdule').serialize(),
		success: function(msg) {
			if(msg.isc) {
				swal('저장', '성공하였습니다');
			}else {
				swal('저장', '실패하였습니다');
			}
		}
	});
}
// 테이블 tbody 화면에 그리는 함수
function screenWriteTbody(num, refer, step, depth, item, name, term) {
	var tag = new StringBuffer();
	tag.append("<tr id='"+num+"'>");
	tag.append("<td><input type='checkbox' name='chkVal' value='"+num+"'/></td>")
	tag.append("<td>");
	tag.append('<div class="dropdown">');
	tag.append('<button class="btn btn-info" id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">');
	tag.append(numFormat(Number(num)+1));
	tag.append('</button>');
	tag.append('<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">');
	tag.append('<li><a onclick="listCreate('+num+')">목록추가</a></li>');
	tag.append('<li><a onclick="listCreateDepth('+num+')">하위추가</a></li>');
	tag.append('<li><a onclick="listCreateDiff('+num+')">상위추가</a></li>');
	tag.append('</ul></div>');
	tag.append("<input type='hidden' name='inPrjs_refer' value='"+refer+"'>");
	tag.append("<input type='hidden' name='inPrjs_step' value='"+step+"'>");
	tag.append("<input type='hidden' name='inPrjs_depth' value='"+depth+"'>");
	tag.append("</td>");
	tag.append("<td style='overflow: hidden;'><input style='margin-left: "+(25*depth)+"px;' type='text' name='inPrjs_item' value='"+item+"'></td>");
	tag.append("<td><input type='text' name='inPrjs_name' value='"+name+"'></td>");
	tag.append("<td class='progressList'></td>");
	tag.append(termAnalsis(term, baseData.schdule()));
	tag.append("</tr>");
	return tag.toString();
}
// 최초 화면 그리기
$(document).ready(function() {
	// 기본데이터 저장 객체
	baseData = {
			name : $('#prj_name').val(),
			start : $('#prj_start').val().split("-"),
			last : $('#prj_last').val().split("-"),
			schdule : function() {
				var start = new Date(this.start[0], this.start[1]-1, this.start[2]);
				var last = new Date(this.last[0], this.last[1]-1, this.last[2]);
				return ((last.getTime() - start.getTime()) / (1000*60*60*24)) + 1;
			}
	}
	// 테이블 thead부분을 그려준다
	screenWriteThead();
	$.ajax({
		url: './selectListProjectSchdule.do',
		type: 'post',
		async: false,
		data : {
			"prj_no" : $('#prj_no').val()
		},
		success: function(lists) {
			if(lists.length != 0) {
				baseData.chk = true;
				subData.cnt = lists.length;
				subData.prjs_item = new Array();
				subData.prjs_name = new Array();
				subData.prjs_refer = new Array();
				subData.prjs_step = new Array();
				subData.prjs_depth = new Array();
				subData.prjs_term = new Array();
				$.each(lists, function(i, prjsDto){
					subData.prjs_item[i] = prjsDto.prjs_item;
					subData.prjs_name[i] = prjsDto.prjs_name;
					subData.prjs_refer[i] = prjsDto.prjs_refer;
					subData.prjs_step[i] =  prjsDto.prjs_step;
					subData.prjs_depth[i] = prjsDto.prjs_depth;
					subData.prjs_term[i] =  prjsDto.prjs_term;
				});
			}else {
				baseData.chk = false;
			}
		}
	});
	if(baseData.chk) {
		// 테이블 tbody부분을 그려준다
		for(var i=0; i<subData.cnt; i++) {
			$('#tbody').append(screenWriteTbody(i, subData.prjs_refer[i], subData.prjs_step[i], subData.prjs_depth[i], subData.prjs_item[i], subData.prjs_name[i], subData.prjs_term[i]));
		}
		// 일정체크 이벤트
//		if($('#prjDept_no').val() == $('#memDept_no').val()) {
			schduleEvent();
//		}else {
//			schduleColor();
//		}
		// 통계를 그려준다
		progress();
	}
});
// 화면에 통계를 그려준다
function progress() {
	var cntListArray = new Array();
	for(var i=0; i<subData.cnt; i++) {
		cntListArray[i] = 0;
	}
	var cntDayArray = new Array();
	var cntDaySumArray = new Array();
	for(var i=0; i<baseData.schdule(); i++) {
		cntDayArray[i] = 0;
		cntDaySumArray[i] = 0;
	}
	var cnt = 0;
	$('input[name=inPrjs_term]').each(function(i){
		if(i%baseData.schdule() == baseData.schdule()-1) {
			cnt++;
		}
		if($(this).val() == '1') {
			cntListArray[cnt]++;
			cntDayArray[i%baseData.schdule()]++;
		}
	});
	for(var i=0; i<baseData.schdule(); i++) {
		if(i==0) {
			cntDaySumArray[i] = cntDayArray[i];
		}else {
			cntDaySumArray[i] = cntDaySumArray[i-1] + cntDayArray[i];
		}
	}
	var sumTerm = 0;
	for(var i=0; i<subData.cnt; i++) {
		sumTerm += cntListArray[i];
	}
	var percent = makeRound(sumTerm);
	var oldPercent = 100 / sumTerm;
	$('input[name=inPrjs_term]').each(function(){
		if($(this).val() == '1') {
			$(this).parent('td').html(percent+"%<input type='hidden' name='inPrjs_term' value='1'>");
		}else {
			$(this).parent('td').html("<input type='hidden' name='inPrjs_term' value='0'>");
		}
	});
	if(isFinite(percent)) {
		$('.progressList').each(function(i){
			$(this).text(makeBaseRound(cntListArray[i]*oldPercent)+'%');
		}).parents('table').find('.progressDay').each(function(i){
			$(this).text(makeBaseRound(cntDayArray[i]*oldPercent)+'%');
		}).parents('thead').find('.progressDaySum').each(function(i){
			$(this).text(makeBaseRound(cntDaySumArray[i]*oldPercent)+'%');
		});
		$('#thead tr:eq(5) td:eq(4)').text(makeBaseRound(cntDaySumArray[cntDaySumArray.length-1]*oldPercent)+'%');
	}else {
		$('.progressList').each(function(i){
			$(this).text('0%');
		}).parents('table').find('.progressDay').each(function(i){
			$(this).text('0%');
		}).parents('thead').find('.progressDaySum').each(function(i){
			$(this).text('0%');
		});
		$('#thead tr:eq(5) td:eq(4)').text('0%');		
	}
}