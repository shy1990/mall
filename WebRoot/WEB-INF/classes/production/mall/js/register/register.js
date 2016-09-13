	var wait = 120; 
	$(document).ready(function() {
	 
		$("#checkbox").attr("checked",true); 
		
		$("#regMobile").keyup(function(){
			console.info($(this).val());
			var infoBox = $(this).parent().parent().find("#showInfoBox");//提示放置的位置
			var val = $(this).val();
			if(val == ""){
				infoBox.html("<img src='images/huiyuan_dui.png' align='left' />手机号不能为空");
			}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(val)){
				infoBox.html("<img src='images/huiyuan_dui.png' align='left' />手机格式错误");
			}else{
				console.info("验证通过");
				//手机号码格式验证通过
				//远程验证手机号码是否存在
				$.post("member/checkMobile/"+val+".html",function(data){
					var d = $.parseJSON(data);
					console.info(d);
					if(d.success){//手机号验证通过
						//全部验证通过就提示正确
						infoBox.html("<img src='images/huiyuan_sha.png' align='left' />");
					}else{//手机号验证有问题
						infoBox.html("<img src='images/huiyuan_dui.png' align='left' />"+d.msg);
					}
					
				},"text");
				
			}
		});
	});
	
	$(document).ready(function(){
 	    /* var value = document.getElementById("province").value;
 	     if(value == null || value == ""){
 	     	$.ajax({
			type:"post",
			url:"member/gainProvince.html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
				if (obj) {
					var province = $("#province");  
                   province.empty();  
                   province.append("<option value=''>------------请选择省份------------</option>");
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
 	     }*/
		//默认山东省
		var value = document.getElementById("province").value;
		if(value == null || value == ""){
			var province = $("#province"); 
			province.append("<option value = '2182'>山东省</option>");
		}
		//获取市
		var city = document.getElementById("province").value;
			$.ajax({
		type:"post",
		url:"member/gainCity/"+city+".html",
		//data:formValue,
		dataType:"JSON",
		success : function(obj){
		   if (obj) {
				var city = $("#city");  
               city.empty();  
               city.append("<option value = '' selected='selected'>----------请选择城市----------</option>");
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
			
	});
 	//获取市
	
 	/*function queryCity(){
 			var city = document.getElementById("province").value;
 			$.ajax({
			type:"post",
			url:"member/gainCity/"+city+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			   if (obj) {
					var city = $("#city");  
                   city.empty();  
                   city.append("<option value = '' selected='selected'>------------请选择城市------------</option>");
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
 	  			
 	}*/
  //获取区县
 	function queryArea(){
 			var area = document.getElementById("city").value;
 			$.ajax({
			type:"post",
			url:"member/gainArea/"+area+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			        
				 if (obj) {
					var area = $("#area");  
                   area.empty();  
                   area.append("<option value = ''  selected='selected'>----------请选择地区----------</option>");
                   for(var i=0;i<obj.length;i++){
                   area.append("<option value = '"+obj[i].id+"'>"+obj[i].name+"</option>");
					}
                  
				};
				
		 }
		});
 			
 	}
 	//获取乡镇
 	function queryTowns(){
			var town = document.getElementById("area").value;
			
			$.ajax({
		type:"post",
		url:"member/gainTown/"+town+".html",
		
		//data:formValue,
		dataType:"JSON",
		success : function(obj){ 
		        
			 if (obj) {
				var town = $("#town");  
				town.empty();  
				town.append("<option value = ''  selected='selected'>----------请选择乡镇----------</option>");
               for(var i=0;i<obj.length;i++){
            	   town.append("<option value = '"+obj[i].id+"'>"+obj[i].name+"</option>");  
				}
              
			};
			if(obj==null){
				var town = $("#town");
                town.empty();  
				town.append("<option value = ''  selected='selected'>----------请选择乡镇----------</option>");
				checkArea();
			}
	 }
	});
			
	}
	
	//判断用户名是否已被注册
	function existUsername(){
		var form = document.forms[0];
		var username = form.username.value;		
		var viewobj = document.getElementById("checkResult");
		viewobj.innerHTML = "正在检测中...";
		$.post("member/exsitUsername/"+username+".html",function(data){
		$('#checkResult').html(data);
		},"text");
	}
	//用户名格式验证
	function checkUsername(){
		var form = document.forms[0];
		if(form.username.value=="" ){
			document.getElementById("checkResult").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />店铺名不能为空";
		}else if(form.username.value.length<3){
			document.getElementById("checkResult").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度3位以上店铺名";
			return false;
		}else if(!/^[^_][u3E00-\u9FA5]*[A-Za-z]*[a-z0-9_]*$/.test(form.username.value)){
			document.getElementById("checkResult").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />店铺名只能用中文数字字母下划线";
			return false;
		}else{
			existUsername();
			return true;
		}
		
	}
	//密码个时验证
	function checkPassword(){
		var form = document.forms[0];
		if(form.password.value==""){
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />密码不能为空";
		}else if(form.password.value.length<6){
			
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度6位以上的密码";
			return false;
		}else if(!/^[0-9a-zA-Z]+$/.test(form.password.value)){
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />密码只能用数字和字母";
				return false;
			}else{
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_sha.png\" align=\"left\" />";
			return true;
		}
		
	}
	//确认密码格式验证
	function checkrepsw(){
		var form = document.forms[0];
		if(form.repassword.value=="" ){
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />确认密码不能为空";
		}else if(form.repassword.value.length<6){
			
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度6位以上的密码";
			return false;
		}else if(form.password.value!=form.repassword.value){
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />两次密码不一致";
		}else{
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_sha.png\" align=\"left\" />";
		}
		/*if(form.password.value!=form.repassword.value){
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />两次密码不一致";
			alert("两次输入的密码不一致");
			return false;
		}else{
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_sha.png\" align=\"left\" />";
			
	    }*/
	}
	//手机验证码格式验证
	function checkCode(){
		var form = document.forms[0];
		
		if(document.getElementById("showEmployeeImg").style.display == "block"){
			if(form.verificationCode.value==""){
				document.getElementById("existCode").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />手机验证码不能为空";
			}else if(form.verificationCode.value.length<6){
				document.getElementById("existCode").style.display = "block";
				document.getElementById("existCode").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入手机上的验证码";
				}else{
					document.getElementById("existCode").innerHTML="";
					document.getElementById("message").style.display = "none";
				}
			  
		}else if(document.getElementById("showEmployeeImg").style.display == "none"){
			if(form.verificationCode.value==""){
				document.getElementById("existCodeRight").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />手机验证码不能为空";
			}else if(form.verificationCode.value.length<6){
				document.getElementById("existCodeRight").style.display = "block";
				document.getElementById("existCodeRight").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度6位以上的密码";
			}else{
				document.getElementById("existCodeRight").innerHTML="<img src=\"images/huiyuan_sha.png\" align=\"left\" />";	
				document.getElementById("message").style.display = "none";
			}
			
		}
		
	}
	//手机号格式验证
	function checkMobile(){
		var form = document.forms[0];
		if(document.getElementById("showEmployeeImg").style.display == "block"){
			document.getElementById("existMobile").innerHTML="";
		}else if(form.toMobile.value == ""){
			document.getElementById("existMobile").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />手机号不能为空";
		}else if(!/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/.test(form.toMobile.value)){
			document.getElementById("existMobile").style.display = "block";
			document.getElementById("existMobile").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />手机格式错误";
		}else{
			document.getElementById("existMobile").innerHTML = "<img src=\"images/huiyuan_sha.png\" align=\"left\" />";
		}
	}
	//所在地区非空验证
	function checkArea(){
		var form = document.forms[0];
		if(form.province.value!="" && form.city.value !="" && form.area.value != "" && form.town.value != ""){
			document.getElementById("checkArea").innerHTML = "<img src=\"images/huiyuan_sha.png\" align=\"left\" />";
		}
		if(form.province.value == "" || form.city.value == "" || form.area.value == "" || form.town.value == ""){
			document.getElementById("checkArea").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />店铺所在地区不能为空";
		}
		
	}
	
	
	function toSubmit(){
		var form = document.forms[0];
		if(form.province.value == "" || form.city.value == "" || form.area.value == "" || form.town.value == ""){
			document.getElementById("checkArea").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />店铺所在地区不能为空";
		    return;
		}
		if(document.getElementById("showEmployeeImg").style.display == "block"){
			if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			}
			if(form.verificationCode.value=="" ){
				document.getElementById("existCode").style.display = "block";
				return;
			}
		}else if(document.getElementById("showEmployeeImg").style.display == "none"){
			if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
			}
			if(form.verificationCode.value=="" ){
				document.getElementById("existCodeRight").style.display = "block";
				return;
			}
			if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(form.mobile.value)){
				document.getElementById("existMobile").style.display = "block";
				return;
			}
		}
		
		if(!/^[^_][u3E00-\u9FA5]*[A-Za-z]*[a-z0-9_]*$/.test(form.username.value)){
			document.getElementById("checkResult").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />店铺名只能用中文数字字母下划线";
			return;
		}
		if(form.username.value=="" || form.username.value.length<3){
			document.getElementById("checkResult").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度3位以上店铺名";
			return ;
		}
		var val = $("#regMobile").val();
		if(val == ""){
			$("#showInfoBox").html("<img src='images/huiyuan_dui.png' align='left' />手机号不能为空");
			return ;
		}else if(!/^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(val)){
			$("#showInfoBox").html("<img src='images/huiyuan_dui.png' align='left' />手机格式错误");
			return ;
		}
		if(!/^[0-9a-zA-Z]+$/.test(form.password.value)){
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />密码只能用数字和字母";
			return ;
		}
		if(form.password.value=="" || form.password.value.length<6){
			document.getElementById("showimgps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度6位以上的密码";
			return ;
		}
		if(form.password.value=="" || form.repassword.value.length<6){
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />请输入长度6位以上的密码";
			return ;
		}else if(form.password.value!=form.repassword.value){
			document.getElementById("showimgreps").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />两次密码不一致";
			return ;
		}else {
		 	form.submit();
//			$.ajax({
//		        url: form.action,
//		        type: form.method,
//		       // data: dataPara,
//		        success: function(obj){
//		        	if(obj.success){
//		        	  	window.location.href="member/toHome.html"
//		        	}else{
//		        		alert("okk");
//						$("#message").append(obj.msg)
//		        	}
//		        }
//		    });
		}
		
	}
	//获取手机验证码
	function getExistPsw(){
		
		var mobile = $("#mobile").val();
		if(mobile !="" && /^(?:13\d|14\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/.test(mobile)){
		
		
		$.ajax({
			type:"post",
			url:"member/getMobileCode/"+mobile+".html",
			//data:formValue,
			dataType:"JSON",
			success : function(obj){
			        $("#name").empty();
					$("#zhiwei").empty();
					$("#gongpaihao").empty();
					$("#adminId").val("");
				if (obj.success) {
					$("#existMobile").hide();
					$("#existCodeRight").hide();
					$("#name").append(obj.obj.truename);
					$("#zhiwei").append((obj.msg).substring(3));
					$("#gongpaihao").append(obj.obj.userNum);
					$("#adminId").val(obj.obj.id);
					$("#emplyeeImg").attr("src",obj.obj.userUrl);
					$("#showEmployeeImg").css("display", "block");
				//	$("#showEmployeeImg").show();
					countDown();
				}else {
					if(document.getElementById("showEmployeeImg").style.display = "block"){
						$("#showEmployeeImg").hide();
						document.getElementById("existMobile").style.display = "block";
						document.getElementById("existMobile").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />"+obj.msg;
					}else{
						document.getElementById("existMobile").style.display = "block";
						document.getElementById("existMobile").innerHTML="<img src=\"images/huiyuan_dui.png\" align=\"left\" />"+obj.msg;
					}
					
					//alert(obj.msg);
				}
				
		 }
		});
		
		}else{
			//document.getElementById("existMobile").style.display = "block";
			$("#existMobile").show();
		}
	}
	
	function keydown(e){
		if(e.keyCode==13){
			toSubmit();
		}
	}
	//倒计时器
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
