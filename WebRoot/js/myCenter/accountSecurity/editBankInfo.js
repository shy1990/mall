   function checkbankCardNo(){
	   var form = document.forms[0];
       if(form.bankCardNo.value==""){
       	 document.getElementById("checkBankCardNo").innerHTML="<span style=\"color:red\">*银行卡号信息不能为空</span>";
       }else{
    	   document.getElementById("checkBankCardNo").innerHTML="";
       }
   }
   function checkBankAccount(){
	   var form = document.forms[0];
       if(form.bankAccount.value==""){
       	 document.getElementById("checkBankAccount").innerHTML="<span style=\"color:red\">*银行卡户名信息不能为空</span>";
       }else{
    	   document.getElementById("checkBankAccount").innerHTML="";
       }
   }
   
   function checkBankDeposit(){
	   var form = document.forms[0];
       if(form.bankDeposit.value==""){
       	 document.getElementById("checkBankDeposit").innerHTML="<span style=\"color:red\">*银行开户行信息不能为空</span>";
       }else{
    	   document.getElementById("checkBankDeposit").innerHTML="";
       }
   }
  
  	function toSubmit(){
  		 var form = document.forms[0];
  	    if((form.bankCardNo.value!=null && form.bankCardNo.value!="")
  	    		&&(form.bankDeposit.value!=null && form.bankDeposit.value!="")
  	     	    &&(form.bankAccount.value!=null && form.bankAccount.value!="")){
  	    	document.forms[0].submit();
  	    }else{
  	    	if(form.bankDeposit.value==null || form.bankDeposit.value==""){
  	    		document.getElementById("checkBankDeposit").innerHTML = "<span style=\"color:red\">*银行开户行信息不能为空</span>";
  	    	}
  		   	
  		   	if(form.bankCardNo.value==null || form.bankCardNo.value==""){
  		    	document.getElementById("checkBankCardNo").innerHTML = "<span style=\"color:red\">*银行卡号信息不能为空</span>";
  		    }
  		   	if(form.bankAccount.value==null || form.bankAccount.value==""){
  		   		document.getElementById("checkBankAccount").innerHTML = "<span style=\"color:red\">*银行卡户名信息不能为空</span>";
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
   	    if((form.bankCardNo.value!=null && form.bankCardNo.value!="")
   	    		&&(form.bankDeposit.value!=null && form.bankDeposit.value!="")
  	     	    &&(form.bankAccount.value!=null && form.bankAccount.value!="")){
   	    	document.forms[0].submit();
   	    }else{
   	    	if(form.bankDeposit.value==null || form.bankDeposit.value==""){
  	    		document.getElementById("checkBankDeposit").innerHTML = "<span style=\"color:red\">*银行开户行信息不能为空</span>";
  	    	}
  		   	
  		   	if(form.bankCardNo.value==null || form.bankCardNo.value==""){
  		    	document.getElementById("checkBankCardNo").innerHTML = "<span style=\"color:red\">*银行卡号信息不能为空</span>";
  		    }
  		   	if(form.bankAccount.value==null || form.bankAccount.value==""){
  		   		document.getElementById("checkBankAccount").innerHTML = "<span style=\"color:red\">*银行卡户名信息不能为空</span>";
  			}
  		 }
    }