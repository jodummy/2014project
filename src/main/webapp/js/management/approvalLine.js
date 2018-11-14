/**
 * 
 */
function detailViewApp1(mem_no){
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("app_order_001").innerHTML="<input type='text' id='app1' name='app1' value='"+option_text+"'/><br>"+mem_no+"<br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd1()\" />";
	
	self.close();    // 자식창 닫기
}

function detailViewApp2(){ 
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("app_order_000").innerHTML="<input type='text' id='app0' name='app0' value='"+option_text+"'/><br>"+mem_no+"<br><input type=\"button\" value=\"결재라인 변경\" onclick=\"applineadd0()\" />";
	self.close();    // 자식창 닫기
}

function detailView(){ 
	var select = document.getElementById("com_chs");
	var option_text=select.options[select.selectedIndex].value;
	window.opener.document.getElementById("doc_reperence").value=option_text;
	self.close();    // 자식창 닫기
}

function sendit(){
 var right_list = document.form1.elements["com_chs"];
 alert(right_list.value);
 // B 목록의 데이터를 전송하고자 할때 list를 selected 시켜준다
 for(i=0; i<right_list.length; i++){
  right_list.options[i].selected = "true";
 }

 document.form1.action ="";
 document.form1.submit();  
}


function addCom(){
 var left_list  = document.form1.elements["com_list"];
 var right_list  = document.form1.elements["com_chs"];
    var str   = new Array();
    var str2   = new Array();
    var str3   = new Array();
    var idx   = 0;
    
   for ( i=0; i<left_list.length; i++ ){
    if( left_list.options[i].selected==true ){
     str[idx]  = left_list.options[i].value;
     str2[idx] = left_list.options[i].innerText;
     str3[idx] = left_list.options[i].index;
     idx++;
    }
   }   
   
   for ( i=0;i< str3.length;i++ ){
    left_list.remove(str3[i]-i);
   }
     
   if( right_list.length==0 ) {
    for( i=0; i<str.length; i++ ) {
     right_list.options[i] = new Option(str2[i],str[i]);
    }
   }else if( right_list.length>0 ) {
    for ( i=0; i<str.length; i++ ) {
     right_list.options[right_list.length] = new Option(str2[i],str[i]);
    }
   }
}

function delCom(){
 var right_list = document.form1.elements["com_list"];
 var left_list = document.form1.elements["com_chs"];
    var str    = new Array();
    var str2   = new Array();
    var str3   = new Array();
    var idx   = 0;
    
   for( i=0; i<left_list.length; i++ ){
    if( left_list.options[i].selected==true ){
     str[idx]  = left_list.options[i].value;
     str2[idx] = left_list.options[i].innerText;
     str3[idx] = left_list.options[i].index;
     idx++;
    }
   }   
   
   for( i=0;i< str3.length;i++ ){
    left_list.remove(str3[i]-i);
   }
     
   if( right_list.length==0 ) {
    for( i=0; i<str.length; i++ ) {
     right_list.options[i] = new Option(str2[i],str[i]);
    }
   }else if( right_list.length>0 ) {
    for( i=0; i<str.length;i++ ) {
     right_list.options[right_list.length] = new Option(str2[i],str[i]);
    }
   }
}
    
    function moveItem(goto, elementId) {
        var element = document.getElementById(elementId);  // Multiple Select
															// Element
        var selIndex = element.selectedIndex;              // Selected Index
        var elementLength = element.options.length;        // Select Element
															// Item Length
        var selText = element.options[selIndex].text;      // Selected Item
															// Text
        var selValue = element.options[selIndex].value;    // Selected Item
															// Value

        if(selIndex < 0) {
            alert("선택해 주세요.");
            return;
        }

        if(goto == "top") {  // 최상위로 이동

            var index = selIndex;
            while(index > 0) {
                element.options[index].text = element.options[index-1].text;
                element.options[index].value = element.options[index-1].value;
                index--;
            }

            element.options[0].text = selText;
            element.options[0].value = selValue;
            element.selectedIndex = 0;

        } else if(goto == "up") {  // 위로 이동

            if(selIndex-1 < 0) return;

            var oldText = element.options[selIndex-1].text;
            var oldValue = element.options[selIndex-1].value;
            element.options[selIndex-1].text = selText;
            element.options[selIndex-1].value = selValue;
            element.options[selIndex].text = oldText;
            element.options[selIndex].value = oldValue;

            element.selectedIndex = selIndex-1;

        } else if(goto == "down") {  // 아래로 이동

            if(selIndex+2 > elementLength) return;

            var oldText = element.options[selIndex+1].text;
            var oldValue = element.options[selIndex+1].value;
            element.options[selIndex+1].text = selText;
            element.options[selIndex+1].value = selValue;
            element.options[selIndex].text = oldText;
            element.options[selIndex].value = oldValue;

            element.selectedIndex = selIndex+1;

        } else if(goto == "bottom") {  // 최하위로 이동

            var index = selIndex;
            while(index < elementLength-1) {
                element.options[index].text = element.options[index+1].text;
                element.options[index].value = element.options[index+1].value;
                index++;

           }

            element.options[element.options.length-1].text = selText;
            element.options[element.options.length-1].value = selValue;

            element.selectedIndex = element.options.length-1;

        } else return;
	}