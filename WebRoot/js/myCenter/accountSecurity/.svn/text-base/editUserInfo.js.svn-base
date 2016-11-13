   function checkDitchEncode(){
	   var form = document.forms[0];
       if(form.ditchEncode.value==""){
       	 document.getElementById("checkDitchEncode").innerHTML="<span style=\"color:red\">*渠道编码不能为空</span>";
       }else{
    	   document.getElementById("checkDitchEncode").innerHTML="";
       }
   }
  
  	function toSubmit(){
  		 var form = document.forms[0];
  	    if((form.ditchEncode.value!=null && form.ditchEncode.value!="")){
  	    	document.forms[0].submit();
  	    }else{
  	   // if(form.ditchEncode.value==null || form.ditchEncode.value==""){
  	    	document.getElementById("checkDitchEncode").innerHTML = "<span style=\"color:red\">*渠道编码不能为空</span>";
  	   // }
  	    }
  	  }
    function keydown(e){
  		
  		if(e.keyCode==13){
  			 forSubmit();
  			/* var form = document.forms[0];
  		
  		if((form.mobile.value!=null && form.mobile.value!="")
  		  	    &&(form.verificationCode.value!=null && form.verificationCode.value!="")){
  			existCode();
  		}else{
  			forSubmit();
  		}*/
  		}
  	}
    
    function forSubmit(){
    	 var form = document.forms[0];
   	    if((form.ditchEncode.value!=null && form.ditchEncode.value!="")){
   	    	document.forms[0].submit();
   	    }else{
   	   	    document.getElementById("checkDitchEncode").innerHTML = "<span style=\"color:red\">*渠道编码不能为空</span>";
   	    }
    }