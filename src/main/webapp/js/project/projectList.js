// 프로젝트 생성 폼
function insertProjectForm() {
	$("#insertProjectForm").modal();
}
// 프로젝트 생성
function insertProject() {
	var prj_name = $('#prj_name').val();
	var prj_start = $('#prj_start').val();
	var prj_last = $('#prj_last').val();
	var start = new Date(prj_start);
	var last = new Date(prj_last);
	if(prj_name == ''){
		swal('프로젝트명', '입력해주세요');
		return false;
	}else if(prj_name.indexOf(" ") != -1){
		swal('프로젝트명', '공백을 포함 할 수 없습니다');
		return false;
	}else if(prj_name.length > 33){
		swal('프로젝트명', '길이 제한을 벗어났습니다');
		return false;
	}else if(prj_start == ''){
		swal('시작일', '입력해주세요');
		return false;
	}else if(prj_last == ''){
		swal('종료일', '입력해주세요');
		return false;
	}else if(last.getTime()-start.getTime() < 0){
		swal('프로젝트 기간', '시작일이 종료일보다 늦습니다');
		return false;
	}else if((((last.getTime()-start.getTime())/(1000*60*60*24)))+1 > 4000){
		swal('프로젝트 기간', '4000일 초과입니다.');
		return false;
	}else if($('input[name=dept_no').val().charAt(0) == 'C') {
		swal('프로젝트 생성', '팀에 속해 있지 않습니다');
		return false;
	}
	$('#frmInsertProjcet').submit();
}
// 전체체크
function checkAllDel(bool) {
	var chkVal = document.getElementsByName("chkVal");
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}
// 프로젝트 삭제
function updateDelProject() {
	var chkVal = document.getElementsByName("chkVal");
	var n = 0;
	var dept_no = $('input[name=dept_no').val();
	var dept_nos = document.getElementsByName("dept_nos");
	var dept_nosChk = new Array();
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked == true){
			n++;
			if(dept_no!=dept_nos[i].value) {
				swal("프로젝트 삭제", '자신의 팀만 삭제 가능합니다');
				return false;
			}
		}
	}
	if(n>0){
		$('#frmUpdateDelProject').submit();
	}else {
		swal("프로젝트 삭제", '선택해주세요');
	}
}
// 프로젝트일정 상세 이동
function projectSchdule(prj_no) {
	$("#prj_no").attr('value', prj_no);
	$('#projectSchdule').submit();
}
// pie chart ajax 호출
function projectSchdulePie(prj_no) {
	$.ajax({
		url: './projectSchdulePie.do',
		type: 'post',
		async: false,
		data : {
			"prj_no" : prj_no
		},
		success: function(lists) {
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				var dataChart = [['Task', 'Hours per Day']];
				if(lists.length != 0) {
					$.each(lists, function(i, item){
						dataChart.push([item.prjs_name, item.prjs_cnt]);
					});
				}else {
					dataChart.push(['입력해주세요', 1]);
				}
				var data = google.visualization.arrayToDataTable(dataChart);
				var options = {
						title: $('input[value='+prj_no+']').parents('tr').find('td:eq(2)').text(),
						width: 900,
						height: 500,
						pieHole: 0.4,
				};
				var chart = new google.visualization.PieChart(document.getElementById('pieChart'));
				chart.draw(data, options);
			}
			$('#ProjectPieForm').modal();
		}
	});
}
//bar chart ajax 호출
function projectSchduleBar(prj_no) {
	$.ajax({
		url: './projectSchduleBar.do',
		type: 'post',
		async: false,
		data : {
			"prj_no" : prj_no
		},
		success: function(lists) {
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				var dataChart = [['Element', '일수']];
				if(lists.length != 0) {
					$.each(lists, function(i, item){
						dataChart.push([item.prjs_item, item.prjs_cnt]);
					});
				}else {
					dataChart.push(['입력해주세요', 1]);
				}
				var data = google.visualization.arrayToDataTable(dataChart);
				var view = new google.visualization.DataView(data);
				var options = {
						title: $('input[value='+prj_no+']').parents('tr').find('td:eq(2)').text(),
						width: 900,
						height: 300,
						bar: {groupWidth: "95%"},
						legend: { position: "none" },
				};
				var chart = new google.visualization.BarChart(document.getElementById("barChart"));
				chart.draw(view, options);
			}
			$('#ProjectBarForm').modal();
		}
	});
}
// Array Object객체를 Array value로 반환
function byNameArray(data) {
	var dataArray = new Array();
	for(var i=0; i<data.length; i++) {
		dataArray[i] = data[i].value;
	}
	return dataArray;
}
// num번째 프로젝트 진행률 계산
function progress(num) {
	var cntDayArray = new Array();
	var cnt = 0;
	var total = 0;
	for(var i=0; i<data.totalDay(num); i++) {
		cntDayArray[i] = 0;
	}
	for(var i=0; i<data.term[num].length; i++) {
		if(data.term[num].charAt(i) == '1') {
			cntDayArray[i%data.totalDay(num)]++;
		}
	}
	for(var i=0; i<data.nowDay(num); i++) {
		if(i < cntDayArray.length) {
			cnt += cntDayArray[i];
		}
	}
	for(var i=0; i<data.totalDay(num); i++) {
		total += cntDayArray[i];
	}
	var percent = 100 / total;
	if(isFinite(percent)) {
		$('progress').eq(num).attr('max', total).attr('value', cnt).parent('td').find('span').text(makeBaseRound(percent*cnt)+'%');
	}else {
		$('progress').eq(num).attr('max', 100).attr('value', 0).parent('td').find('span').text('0%');
	}
}
$(document).ready(function(){
	data = {
			start : byNameArray(document.getElementsByName("progress_start")),
			last : byNameArray(document.getElementsByName("progress_last")),
			term : byNameArray(document.getElementsByName("progress_term")),
			cnt : document.getElementsByName("progress_start").length,
			totalDay : function(i){
				var start = new Date(this.start[i]);
				var last = new Date(this.last[i]);
				return ((last.getTime() - start.getTime()) / (1000*60*60*24)) + 1;				
			},
			nowDay : function(i){
				var start = new Date(this.start[i]);
				return Math.round(((new Date().getTime() - start.getTime()) / (1000*60*60*24)) + 1);
			}
	}
	for(var i=0; i<data.cnt; i++) {
		progress(i);
	}
});