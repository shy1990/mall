	var expDays = 7;

    url = window.location;
    title = "三际手机采购网";
	$(document).ready(function() { 
			if ($.cookie("rmbUser") == "true") { 
					document.getElementById("box").innerHTML="<input  type=\"password\" id=\"password\" name=\"password\"  onMouseOver=\"this.focus()\" onMouseOut=\"if(this.value==''){this.value='密码';}\" onFocus=\"this.select()\" onClick=\"this.value='';\" class=\"main_logo_input_zi\" />";
					document.getElementById("password").value="";
					$("#checkbox").attr("checked",true); 
					$("#username").val($.cookie("userName",{path:"/"})); 
			        $("#password").val(utf8to16(base64decode($.cookie("passWord",{path:"/"})))); 
					
				}
			});



         //保存用户信息 
		function savepwd() { 
			if ($("#checkbox").attr("checked") == 'checked') { 
			var userName = $("#username").val(); 
			var passWord = $("#password").val(); 
			$.cookie("rmbUser", "true", { path:"/",expires: 7 }); // 存储一个带7天期限的 cookie 
			$.cookie("userName", userName, { path:"/",expires: 7 }); // 存储一个带7天期限的 cookie 
			$.cookie("passWord", base64encode(utf16to8(passWord)), { path:"/",expires: 7 }); // 存储一个带7天期限的 cookie 
		}else { 
			$.cookie("rmbUser", "false", { path:"/",expires: -1 }); 
			$.cookie("userName", "", { path:"/",expires: -1 }); 
			$.cookie("passWord", "", {path:"/", expires: -1 }); 
			} 
		}
  /*判断checkbox是否选中，选中显示密码(ie不支持document.getElementById('password').type，规定type只允许写一次)*/
   		/*function showpass(){
   		   
   		    var pwd = document.getElementById('password').value;
	   		if(obj.checked){
	   			document.getElementById('password').type = "text";
	   			
	   		}else{
	   			document.getElementById('password').type = "password";
	   		} 
	   		
	   		document.getElementById('Pwd').value=pwd;
	   		}*/
	   		 function showps(){   
                    if (this.form1.password.type="password") {  
                        document.getElementById("box").innerHTML="<input  type=\"text\" id=\"password\" name=\"password\" value="+this.form1.password.value+"  onMouseOver=\"this.focus()\" onMouseOut=\"if(this.value==''){this.value='密码';}\" onFocus=\"this.select()\" onClick=\"if(this.value=='密码')this.value=''\" class=\"main_logo_input_zi\" />";   
                        document.getElementById("click").innerHTML="<input type=\"checkbox\" checked=\"checked\" name=\"checkbox\" id=\"showPwd\" align=\"left\" onclick=\"javascript:hideps();\" />隐藏密码";
                    }  
                }   
                function hideps(){   
                    if (this.form1.password.type="text") {  
                        document.getElementById("box").innerHTML="<input  type=\"password\" id=\"password\" name=\"password\" value="+this.form1.password.value+"  onMouseOver=\"this.focus()\" onMouseOut=\"if(this.value==''){this.value='密码';}\" onFocus=\"this.select()\" onClick=\"if(this.value=='密码')this.value=''\" class=\"main_logo_input_zi\" />";   
                        document.getElementById("click").innerHTML="<input type=\"checkbox\" name=\"checkbox\" align=\"left\" onclick=\"javascript:showps();\" />显示密码"  
                    }   
                }   
	   		
  
        function toSetHome(){
        	var form = document.forms[0];
        	if(form.username.value == "店铺名/手机号" && form.password.value == "密码"){
        	   alert("用户名密码不能为空!");
        	}else if(form.username.value == "店铺名/手机号" || form.username.value == ""){
        	   alert("用户名不能为空!");
        	}else if(form.password.value == "密码" || form.password.value == ""){
		       alert("密码不能为空!");
		    }else{
		    	 /*弹出层设为首页*/
		    	 //bookmarkit("三际数码",window.location);
		    	  // toSubmit();
		    	  /* var top = ($(window).height() - $("#showFa").height())/2;   
                 var left = ($(window).width() - $("#showFa").width())/2; 
                 var scrollTop = $(document).scrollTop();   
                 var scrollLeft = $(document).scrollLeft();   
                 $("#showFa").css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } ).show();*/
		    
		    }
        	
        }
        

   /*设为首页*/
	function SetHome(obj,vrl){
        try{
                obj.style.behavior='url(#default#homepage)';
                obj.setHomePage(vrl);
                toSubmit();
              // $("form1").submit();
        }catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                       			
                        }
                        catch (e) {
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                                toSubmit();
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                        toSubmit();
                 }else{
                     alert("您的浏览器不支持，\n\n您需要手动将【"+vrl+"】设置为首页。");
                     toSubmit();
                 }             
        }
   } 
	function GetCookie (name) {  
		var arg = name + "=";  
		var alen = arg.length;  
		var clen = document.cookie.length;  
		var i = 0;  
		while (i < clen) {    
		var j = i + alen;    
		if (document.cookie.substring(i, j) == arg)      
		return getCookieVal (j);    
		i = document.cookie.indexOf(" ", i) + 1;    
		if (i == 0) break;   
		}  
		return null;
	}
	function SetCookie (name, value) {  
		var argv = SetCookie.arguments;  
		var argc = SetCookie.arguments.length;  
		var expires = (argc > 2) ? argv[2] : null;  
		var path = (argc > 3) ? argv[3] : null;  
		var domain = (argc > 4) ? argv[4] : null;  
		var secure = (argc > 5) ? argv[5] : false;  
		document.cookie = name + "=" + escape (value) + 
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + 
		((path == null) ? "" : ("; path=" + path)) +  
		((domain == null) ? "" : ("; domain=" + domain)) +    
		((secure == true) ? "; secure" : "");
	}
	function DeleteCookie (name) {  
		var exp = new Date();  
		exp.setTime (exp.getTime() - 1);  
		var cval = GetCookie (name);  
		document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
	}
	var exp = new Date(); 
		exp.setTime(exp.getTime() + (expDays*24*60*60*1000));
		function amt(){
		var count = GetCookie('count');
		if(count == null) {
		SetCookie('count','1');
		return 1;
	}
	else {
		var newcount = parseInt(count) + 1;
		DeleteCookie('count');
		SetCookie('count',newcount,exp);
		return count;
	   }
	}
	function getCookieVal(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
	endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
	}

	function keydown(e){
			if(e.keyCode==13){
				toSubmit();
         }
     }
	

	function toSubmit(){
		 var form = document.forms[0];
		 if(form.username.value == "店铺名/手机号" && form.password.value == "密码"){
			 document.getElementById("messTop").innerHTML = "<span>用户名密码不能为空!</span>";
      	}else if(form.username.value == "店铺名/手机号" || form.username.value == ""){
      		 document.getElementById("existUs").innerHTML = "<span>用户名不能为空!</span>";
      	}else if(form.password.value == "密码" || form.password.value == ""){
      		document.getElementById("existPs").innerHTML = "<span>密码不能为空!</span>";
		}else{
			
			var count = GetCookie('count');
			if (count == null) {
			count=1;
			SetCookie('count', count, exp);

			if ((navigator.appName == "Microsoft Internet Explorer") && (parseInt(navigator.appVersion) >= 4)) {
			window.external.AddFavorite (url,title);
			savepwd();
			form.submit();
			}
			else {
			/*var msg = "Don't forget to bookmark us!";
			if(navigator.appName == "Netscape") msg += "  (CTRL-D)";
			alert(msg);*/
				savepwd();
			form.submit();
			   }
			}
			else {
			count++;
			SetCookie('count', count, exp);
			savepwd();
			form.submit();
			   }
		    	  // toSubmit();
		    	  /* var top = ($(window).height() - $("#showFa").height())/2;   
		              var left = ($(window).width() - $("#showFa").width())/2; 
		              var scrollTop = $(document).scrollTop();   
		              var scrollLeft = $(document).scrollLeft();   
		              $("#showFa").css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } ).show();*/
		    
		    }
		    
	}
	 //加入收藏
    function bookmarkit(title,url){
    	 try {  
            window.external.addFavorite(url, title);  
            toSubmit();
         } catch (e) { 
            try {  
               window.sidebar.addPanel(title, url, ""); 
                 }  catch (e) {  
                     alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请进入新网站后使用Ctrl+D进行添加");  
                   }  
                 }

		
         /*  if (document.all)
    	   {
    	      window.external.addFavorite(url,title);
    	   }
    	   else if (window.sidebar)
    	   {
    	      window.sidebar.addPanel(url, title, "");
    	   }  */
     
    }
	
	