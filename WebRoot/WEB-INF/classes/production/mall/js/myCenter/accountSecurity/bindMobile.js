 function checkMobile(){
      	var form = document.forms[0];
      	
		 if(form.mobile.value == ""){
			document.getElementById("existMobile").innerHTML="<span style=\"color:red\">手机号不能为空</span>";
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			document.getElementById("existMobile").innerHTML="<span style=\"color:red\">手机格式错误</span>";
		}else{
			document.getElementById("existMobile").innerHTML = "";
    }
 }
    function toSubmit(){
    	 var form = document.forms[0];
    	 if(form.mobile.value == ""){
			document.getElementById("existMobile").innerHTML="<span style=\"color:red\">手机号不能为空</span>";
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			document.getElementById("existMobile").innerHTML="<span style=\"color:red\">手机格式错误</span>";
		}else{
			$.ajax({
    			type:"post",
    			url:"member/existMemberMobile/"+form.mobile.value+".html",
    			//data:formValue,
    			dataType:"JSON",
    			success : function(obj){
    				if (obj.success) {
    					form.submit();
    				}else {
    					document.getElementById("existMobile").innerHTML = obj.msg;
    				};
    				
    		 }
    		});
		}	
			
    }
    
    function keydown(e){
  		
  		if(e.keyCode==13){
  			toSubmit();
  		}
    };