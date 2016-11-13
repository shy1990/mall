 var wait = 120;
 
 
   function checkDitchEncode(){
	   var form = document.forms[0];
       if(form.ditchEncode.value==""){
       	 document.getElementById("checkDitchEncode").innerHTML="<span style=\"color:black\">（渠道编码不能为空）</span>";
       }else{
    	   document.getElementById("checkDitchEncode").innerHTML="";
       }
   }
   
   function checkBankCardNo(){
	   var form = document.forms[0];
       if(form.bankCardNo.value==""){
       	 document.getElementById("checkBankCardNo").innerHTML="<span style=\"color:black\">（银行卡号信息不能为空）</span>";
       }else{
    	   document.getElementById("checkBankCardNo").innerHTML="";
       }
   }
   
   function checkBankAccount(){
	   var form = document.forms[0];
       if(form.bankAccount.value==""){
       	 document.getElementById("checkBankAccount").innerHTML="<span style=\"color:black\">（银行卡户名信息不能为空）</span>";
       }else{
    	   document.getElementById("checkBankAccount").innerHTML="";
       }
   }
   
   function checkBankDeposit(){
	   var form = document.forms[0];
       if(form.bankDeposit.value==""){
       	 document.getElementById("checkBankDeposit").innerHTML="<span style=\"color:black\">（银行开户行信息不能为空）</span>";
       }else{
    	   document.getElementById("checkBankDeposit").innerHTML="";
       }
   }
   
   function cleanBankCardNo(){
	   var form = document.forms[0];
	   form.bankCardNo.value = "";
   }
   
   function cleanBankDeposit(){
	   var form = document.forms[0];
	   form.bankDeposit.value = "";
   }
   
   function cleanBankAccount(){
	   var form = document.forms[0];
	   form.bankAccount.value = "";
   }
   
   function checkAddress(){
       var form = document.forms[0];
       if(form.address.value==""){
       	 document.getElementById("checkAddress").innerHTML="<span style=\"color:black\">（详细地址不能为空）</span>";
       }else{
    	   document.getElementById("checkAddress").innerHTML="";
       }
   }
   
   function checkTrueName(){
   		
   		var form = document.forms[0];
		if(form.truename.value=="" ){
			document.getElementById("checkResult").innerHTML="<span style=\"color:black\">（收件人不能为空）</span>";
		}else if(!/^[u3E00-\u9FA5]*$/.test(form.truename.value)){
			document.getElementById("checkResult").innerHTML="<span style=\"color:black\">（收件人只能用中文）<span/>";
			return false;
		}else{
			document.getElementById("checkResult").innerHTML="";
			return true;
		}
   }
   
   function checkMobile(){
   		var form = document.forms[0];
	    if(form.mobile.value == ""){
			document.getElementById("existMobile").innerHTML="<span style=\"color:black\">（手机号不能为空）</span>";
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			//document.getElementById("existMobile").style.display = "block";
			document.getElementById("existMobile").innerHTML="<span style=\"color:black\">（手机格式错误）<span>";
		}else{
			document.getElementById("existMobile").innerHTML = "";
		}	
   }
   
   function checkComPhone(){
  		var form = document.forms[0];
	    if(form.comPhone.value == ""){
			document.getElementById("checkComPhone").innerHTML="<span style=\"color:black\">（常用手机号不能为空）</span>";
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.comPhone.value)){
			//document.getElementById("existMobile").style.display = "block";
			document.getElementById("checkComPhone").innerHTML="<span style=\"color:black\">（常用手机格式错误）<span>";
		}else{
			document.getElementById("checkComPhone").innerHTML = "";
		}	
  }
  	 
  		function getvalidatedCode(){
  		
  	    var form = document.forms[0];
	    if(form.mobile.value == ""){
			document.getElementById("existMobile").innerHTML="<span style=\"color:black\">（手机号不能为空）</span>";
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			//document.getElementById("existMobile").style.display = "block";
			document.getElementById("existMobile").innerHTML="<span style=\"color:black\">（手机格式错误）<span>";
		}else{
			document.getElementById("existMobile").innerHTML = "";
			countDown();
		}	
  			var mobile = document.getElementById("mobile").value;
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
  			var mobile = document.getElementById("mobile").value;
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
  	  					//document.getElementById("rexistMessage").style.display="block";
  	    				document.getElementById("rexistMessage").innerHTML = "<span style=\"color:black\">"+obj.msg+"</span>";
  	  					};
  	  					
  	  			 }
  	  			});
  			}else{
  				//document.getElementById("rexistMessage").style.display="block";
  				document.getElementById("rexistMessage").innerHTML = "<span style=\"color:black\">（验证码不能为空）</span>";
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
  	
  	$(function(){
  	     var value = document.getElementById("pro").value;
  	     if(value == null || value == ""){
  	     	$.ajax({
			type:"post",
			url:"member/queryProvince.html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
				if (obj) {
					var province = $("#province");  
                    province.empty();  
                    province.append("<option value=''>--请选择--</option>");
                    for(var i=0;i<obj.length;i++){
                    province.append("<option value = '"+obj[i].id+"'>"+obj[i].name+"</option>");
					}
                   
				}else {
					//alert(obj.length);
					//for(var i=0;i<obj.length;i++){
					//	alert(obj[i].name);
					//}
					
				};
				
		 }
		});
  	     }
  	     
  	 //获取乡镇
  	 	

	var town = document.getElementById("area").value;
	var tn = document.getElementById("town").value;
	
	if (town != null && tn == "") {
		$.ajax({
					type : "post",
					url : "member/queryTown/" + town + ".html",
					
					// data:formValue,
					dataType : "JSON",
					success : function(obj) {
						if (obj) {
							var town = $("#town");
							town.empty();
							town.append("<option value = ''  selected='selected'>--请选择乡镇--</option>");
							for ( var i = 0; i < obj.length; i++) {
								town.append("<option value = '" + obj[i].id+ "'>" + obj[i].name + "</option>");
							}

						};
						/*if(obj==null){
							var town = $("#town");
			                town.empty();  
							town.append("<option value = ''  selected='selected'>--请选择乡镇--</option>");
							checkArea();
						}*/
					}
				});
	}
  		
  	});
  	
  	function queryCity(){
  			var city = document.getElementById("province").value;
  			$.ajax({
			type:"post",
			url:"member/queryCity/"+city+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			   if (obj) {
					var city = $("#city");  
                    city.empty();  
                    city.append("<option>--请选择--</option>");
                    for(var i=0;i<obj.length;i++){
                    city.append("<option value = '"+obj[i].id+"'>"+obj[i].name+"</option>");
					}
                   
				}else {
					//alert(obj.length);
					//for(var i=0;i<obj.length;i++){
					//	alert(obj[i].name);
					//}
					
				};
		 }
		});
  	  			
  	}
   
  	function queryArea(){
  			var area = document.getElementById("city").value;
  			$.ajax({
			type:"post",
			url:"member/queryArea/"+area+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			        
				 if (obj) {
					var area = $("#area");  
                    area.empty();  
                    area.append("<option>--请选择--</option>");
                    for(var i=0;i<obj.length;i++){
                    area.append("<option value = '"+obj[i].id+"'>"+obj[i].name+"</option>");
					}
                   
				};
				
		 }
		});
  	  			
  	}
  	
  
 	
  	function toSubmit(){
  		 var form = document.forms[0];
  	    if((form.ditchEncode.value!=null && form.ditchEncode.value!="")
  	    &&(form.username.value!=null && form.username.value!="")
  	    &&(form.province.value!=null && form.province.value!="")
  	    &&(form.city.value!=null && form.city.value!="")
  	    &&(form.area.value!=null && form.area.value!="")
  	    &&(form.address.value!=null && form.address.value!="")
  	    &&(form.mobile.value!=null && form.mobile.value!="")
  	    &&(form.truename.value!=null && form.truename.value!="")
  	    &&(form.verificationCode.value!=null && form.verificationCode.value!="")){
  	    	existCode();
  	    }else{
  	    if(form.ditchEncode.value==null || form.ditchEncode.value==""){
  	    	document.getElementById("checkDitchEncode").innerHTML = "<span style=\"color:black\">（渠道编码不能为空）</span>";
  	    }
  	    if(form.username.value==null || form.username.value==""){
  	    	document.getElementById("checkUsername").innerHTML = "<span style=\"color:black\">（店铺名不能为空）</span>";
  	   		
  	    } 
  	    if((form.province.value==null || form.province.value=="")
  	       ||(form.city.value==null || form.city.value=="") 
  	       ||(form.area.value==null || form.area.value=="")){
  	       
  	    	document.getElementById("checkArea").innerHTML = "<span style=\"color:black\">（所在地区不能为空）</span>";
  	    	
  	    } 
  	    if(form.address.value==null || form.address.value==""){
  	    	document.getElementById("checkAddress").innerHTML = "<span style=\"color:black\">（详细地址不能为空）</span>";
  	   		
  	    }
  	    if(form.mobile.value==null || form.mobile.value==""){
  	    	document.getElementById("existMobile").innerHTML = "<span style=\"color:black\">（手机不能为空）</span>";
  	   		
  	    }
  	    if(form.truename.value==null || form.truename.value==""){
  	    	document.getElementById("checkResult").innerHTML = "<span style=\"color:black\">（收件人不能为空）</span>";
  	   		
  	    }
  	    if(form.verificationCode.value==null || form.verificationCode.value==""){
  	    	document.getElementById("rexistMessage").innerHTML = "<span style=\"color:black\">（手机验证码不能为空）</span>";
  	   		
  	    }
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
    	 
   	    if((form.username.value!=null && form.username.value!="")
   	    &&(form.province.value!=null && form.province.value!="")
   	    &&(form.city.value!=null && form.city.value!="")
   	    &&(form.area.value!=null && form.area.value!="")
   	    &&(form.town.value!=null && form.town.value!="")
   	    &&(form.address.value!=null && form.address.value!="")
   	    &&(form.mobile.value!=null && form.mobile.value!="")
   	    &&(form.truename.value!=null && form.truename.value!="")){
   	    	document.forms[0].submit();
   	    }else{
	   	
   	    if(form.username.value==null || form.username.value==""){
   	    	document.getElementById("checkUsername").innerHTML = "<span style=\"color:black\">（店铺名不能为空）</span>";
   	   		
   	    } 
   	    if((form.province.value==null || form.province.value=="")
   	       ||(form.city.value==null || form.city.value=="") 
   	       ||(form.area.value==null || form.area.value=="")
   	       ||(form.town.value==null || form.town.value=="")){
   	    	document.getElementById("checkArea").innerHTML = "<span style=\"color:black\">（所在地区不能为空）</span>";
   	    	
   	    } 
   	    if(form.address.value==null || form.address.value==""){
   	    	document.getElementById("checkAddress").innerHTML = "<span style=\"color:black\">（详细地址不能为空）</span>";
   	   		
   	    }
   	    if(form.mobile.value==null || form.mobile.value==""){
   	    	document.getElementById("existMobile").innerHTML = "<span style=\"color:black\">（手机不能为空）</span>";
   	   		
   	    }
   	    if(form.truename.value==null || form.truename.value==""){
   	    	document.getElementById("checkResult").innerHTML = "<span style=\"color:black\">（收件人不能为空）</span>";
   	   		
   	    }
   	    
   	    }
    }