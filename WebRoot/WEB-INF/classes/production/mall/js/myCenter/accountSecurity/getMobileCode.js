var wait = 120;
  	 
  		function getvalidatedCode(){
  			var mobile = document.getElementById("tel").value;
  			$.ajax({
			type:"post",
			url:"member/getValidateCode/"+mobile+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			        
				if (obj.success) {
					
				  countDown();
				}else {
					alert(obj.msg);
				};
				
		 }
		});
  		};
  		function existCode(){
  			var verificationCode = document.getElementById("code").value;
  			var mobile = document.getElementById("tel").value;
  			if(verificationCode.length > 0){
  				$.ajax({
  	  				type:"post",
  	  				url:"member/existMobileCode/"+mobile+"_"+verificationCode+".html",
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