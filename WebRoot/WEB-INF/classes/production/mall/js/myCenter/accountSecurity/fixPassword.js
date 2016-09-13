function validatedPsw(){
  	  	 var mobile = document.getElementById("tel").value;
  	     var psw = document.getElementById("oldpssword").value;
  	     if(psw.length > 0){
  	     	$.ajax({
			type:"post",
			url:"member/validatePsw/"+psw+"_"+mobile+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			        
				if (obj.success) {
					
					document.getElementById("existPsw").innerHTML = "<span style=\"color:red\"></span>";
				}else {
			  	    document.getElementById("existPsw").innerHTML = "<span style=\"color:red\">"+obj.msg+"</span>";
				}
				
		 }
		});
  	     }else{
  	     document.getElementById("existPsw").innerHTML = "<span style=\"color:red\">*密码不能为空</span>";
  	     }
  	  }
  	  
  	  function checNewPsw(){
  	  	var form = document.forms[0];
  	  	//var pass = document.getElementById("newpassword").value;
  	  	
  	  	if(form.newpassword.value != "" && form.newpassword.length < 6){
  	  		document.getElementById("existNewPsw").innerHTML = "<span style=\"color:red\">*密码必须6-12位</span>";
  	  	}else if(!/^[0-9a-zA-Z]+$/.test(form.newpassword.value)){
  	  		document.getElementById("existNewPsw").innerHTML = "<span style=\"color:red\">*密码只能用数字和字母</span>";
  	  	}else{
  	  		document.getElementById("existNewPsw").innerHTML = "";
  	  	}
  	  }
  	  
  	  function checkRePsw(){
  	    var form = document.forms[0];
  	  	if(form.repassword.value=="" || form.repassword.value.length<6){
  	  		document.getElementById("existRePsw").innerHTML = "<span style=\"color:red\">*密码必须6-12位</span>";
  	  	}else if(form.newpassword.value!=form.repassword.value){
  	  		document.getElementById("existRePsw").innerHTML = "<span style=\"color:red\">*密码不一致</span>";
  	  	}else{
  	  		document.getElementById("existRePsw").innerHTML = "";
  	  	}
  	  }
  	  
  	  function toSubmit(){
  	    var form = document.forms[0];
  	    
  	    if((form.oldpassword.value!=null && form.oldpassword.value!="")
  	    &&(form.newpassword.value!=null && form.newpassword.value!="")
  	    &&(form.repassword.value!=null && form.repassword.value!="")
  	    &&(form.newpassword.value==form.repassword.value)){
  	    	form.submit();
  	    }else{
  	    	 if(form.oldpassword.value==null || form.oldpassword.value==""){
  	    	document.getElementById("existPsw").innerHTML = "<span style=\"color:red\">*密码不能为空</span>";
  	   		
  	    } 
  	    if(form.newpassword.value==null || form.newpassword.value==""){
  	    	document.getElementById("existNewPsw").innerHTML = "<span style=\"color:red\">*新密码不能为空</span>";
  	    	
  	    } 
  	    if(form.repassword.value==null || form.repassword.value==""){
  	    	document.getElementById("existRePsw").innerHTML = "<span style=\"color:red\">*确认密码不能为空</span>";
  	   		
  	    }
  	    }
  	  }
    function keydown(e){
  		
  		if(e.keyCode==13){
  			toSubmit();
  		}
  	}