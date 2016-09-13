	var wait = 120;
  	 
  		function getvalidatedCode(){
  			var mobile = document.getElementById("tel").value;
  			if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(mobile)){
  				 document.getElementById("existTel").innerHTML = "<span>*手机号码不能为空或手机格式不正确</span>";
  				
  			}else{
  			
  			$.ajax({
				type:"post",
				url:"member/getPhoneCode/"+mobile+".html",
				//data:formValue,
				dataType:"JSON",
				success : function(obj){
		        
				if (obj.success) {
				 document.getElementById("existTel").innerHTML = "";
			 	 countDown();
				}else {
				document.getElementById("existTel").innerHTML = "<span>"+obj.msg+"</span>";
				//alert(obj.msg);
				};
			
	 			}
				});
  			}
  			
  		};
  		function existCode(){
  			var verificationCode = document.getElementById("code").value;
  			var mobile = document.getElementById("tel").value;
  			if(verificationCode.length > 0){
  				$.ajax({
  	  				type:"post",
  	  				url:"member/existPhoneCode/"+mobile+"_"+verificationCode+".html",
  	  				//data:formValue,
  	  				dataType:"JSON",
  	  				success : function(obj){
  	  				        
  	  					if (obj.success) {
  	  						
  	  					document.forms[0].submit();
  	  					}else {
  	  					document.getElementById("rexistMessage").style.display="block";
  	    				document.getElementById("rexistMessage").innerHTML = "<span>"+obj.msg+"</span>";
  	  					};
  	  					
  	  			 }
  	  			});
  			}else{
  				document.getElementById("rexistMessage").style.display="block";
  				document.getElementById("rexistMessage").innerHTML = "<span>*验证码不能为空</span>";
  			};
  		}
  		
  		function time(o) { 
        if (wait == 0) {
            o.removeAttribute("disabled");           
            o.value="获取验证码"; 
            document.getElementById("timer").style.display="none";
            document.getElementById("show").style.display="block";
            wait = 120; 
            
        } else { 
            o.setAttribute("disabled", true); 
            o.value="("+wait+"秒)获取验证码"; 
            wait--; 
            setTimeout(function() { 
                time(o) ;
            }, 
            1000) ;
        } 
    } 
	
  		function countDown(){
			document.getElementById("timer").style.display="block";
        	document.getElementById("show").style.display="none";
       		var obj = document.getElementById("btn");
			time(obj);
	}
  	function keydown(e){
  		
  		if(e.keyCode==13){
  			existCode();
  		}
  	}