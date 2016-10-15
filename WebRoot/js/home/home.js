	/**
	 * 获取商家数量,并显示在showId上
	 */
	function GetMemberNum(showId){
		$.ajax({
			type : 'post',
			url : 'home/getMemberNum.html',
			dataType : 'json',
			success : function(v) {
				$("#"+showId).html(v);
			}
		});
	};
	//首页图片轮播
	$(function(){

		$("#xmSlide").xmSlide({
		    width: 692,
		    height: 335,
		    responsiveWidth:692,
		    pagination: {
		        effect: "fade"
		    },
		    effect: {
		        fade: {
		            speed: 400
		        }
		    },
		    play: {
		        effect: "fade",
		        interval: 5000,
		        auto: true,
		        pauseOnHover: true,
		        restartDelay: 2500
		    }
		});
	});
	//------图片轮播结束-----
	
	/**
	 * 获取热销商品
	 * goodsType:商品类型，hotType:热销类型，num:获取数量，<br>
	 * showId:需要显示的ID
	 * @author ZhouZhangbao
	 */
	function selectHot(goodsType,hotType,num,showId){
		var s = "";
		var n = "";
		var edition ="";
		var pic ="images/goodsHotImg.jpg";
		$.ajax({
			type : 'post',
			url : 'goods/selectHotGoods.html',
			data:{
				goodsType:goodsType,
				hotType:hotType,
				num:num
			},
			dataType : 'json',
			success : function(obj) {	
				for(i=0;i<obj.length;i++){
					if(i<10){
						n="0"+(i+1); 
					}else {
						n=i;
					};
				if( undefined !=obj[i].picSrc && obj[i].picSrc != ''){
					pic = obj[i].picSrc;
				};
				edition = obj[i].edition==undefined?'':obj[i].edition;
					s = s+"<li>" +
						"<div class=\"home_main_03_txt_01\">" +
							"<div class=\"home_main_03_txt_02\">" +
								"<img src=\"images/no_"+n+".png\" width=\"33\" height=\"31\" />" +
							"</div>" +
							"<a href=\"goods/detail/"+obj[i].goodsNum+".html?rxcp\" target=\"_blank\">" +
								"<img src=\""+pic+"\" width=\"197\" height=\"197\" alt=\""+obj[i].goodsName+"\"/>" +
							"</a>" +
						"</div>" +
						"<div>" +
							"<a href=\"goods/detail/"+obj[i].goodsNum+".html\" target=\"_blank\"> 热卖爆款   &nbsp;"+obj[i].goodsName+" &nbsp;"+edition+" &nbsp;"+obj[i].colorName+"</a><br />" +
							"<h5>￥"+obj[i].price+"</h5>" /*+
							"&nbsp;&nbsp;&nbsp;&nbsp;<span>关注人数&nbsp;&nbsp;"+11+obj[i].collectNum+1+"</span>"*/ +
						"</div>" +
					"</li>";
					////console.info(s);
					
				};
				$("#"+showId).html(s);
				$('a[id^=admin_home_home_aHot]').removeAttr("style");
				$("#admin_home_home_aHot"+hotType).css({
					"background-color":"#FF0C10",
					"color":"#FFFFFF",
					"border-radius":"5px"
				});
				
			}
		});
		
	};
	
	/**
	 * 首页左侧服务和买家入门鼠标效果
	 * @param showid
	 */
	function showRetTop(showId){
		
	//	$('a[id^=admin_home_homeServe_]').removeClass("border-bottom solid color");
		$('a[id^=admin_home_homeServe_]').css({
			"border-bottom":"#E9E9E9 solid 1px",
			"color":"#999999",
			"display":"block"
		});
		
		
		$('#admin_home_homeServe_'+showId).css({
			"border-bottom":"#ffffff",
			"solid":"1px",
			"color":"#000000"
		});
		if(showId == '1'){
			$('#admin_home_homeFWXS_1').show();
			$('#admin_home_homeFWXS_2').hide();
		}else{
			$('#admin_home_homeFWXS_2').show();
			$('#admin_home_homeFWXS_1').hide();
		}
		
	};
	
	/**
	 * 成交量滚动效果
	 */
	function DealRoll(){
		$('.home_main_01_let_txt').vTicker({
            speed: 500,            //滚动速度，单位毫秒。
            pause: 3000,           //暂停时间，就是滚动一条之后停留的时间，单位毫秒。
            showItems: 9,          //显示内容的条数
            animation: 'fade',      //动画效果，默认是fade，淡出。
            mousePause: false,     //鼠标移动到内容上是否暂停滚动，默认为true。
            height: 300 ,          //滚动内容的高度。
            direction: 'up'       //滚动的方向，默认为up向上，down则为向下滚动。
        });
	};
	
	
	
	function ImgContent(Content,Nav){
		var index = 0;
		var maximg = 5;
		//$('<div id="flow"></div>').appendTo("#myjQuery");

		//滑动导航改变内容	
		$("#"+Nav+" li").hover(function(){
			if(MyTime){
				clearInterval(MyTime);
			}
			index  =  $("#"+Nav+" li").index(this);
			MyTime = setTimeout(function(){
			ShowjQueryFlash(index,Content,Nav);
			$('#'+Content).stop();
			} , 400);

		}, function(){
			clearInterval(MyTime);
			MyTime = setInterval(function(){
			ShowjQueryFlash(index,Content,Nav);
			index++;
			if(index==maximg){index=0;}
			} , 3000);
		});
		//滑入 停止动画，滑出开始动画.
		 $('#'+Content).hover(function(){
				  if(MyTime){
					 clearInterval(MyTime);
				  }
		 },function(){
					MyTime = setInterval(function(){
					ShowjQueryFlash(index,Content,Nav);
					index++;
					if(index==maximg){index=0;}
				  } , 3000);
		 });
		//自动播放
		var MyTime = setInterval(function(){
			ShowjQueryFlash(index,Content,Nav);
			index++;
			if(index==maximg){index=0;}
		} , 2000);
	};
	function ShowjQueryFlash(i,Content,Nav) {
	$("#"+Content+" div").eq(i).animate({opacity: 1},1000).css({"z-index": "1"}).siblings().animate({opacity: 0},1000).css({"z-index": "0"});
	//$("#flow").animate({ left: 652+(i*76) +"px"}, 300 ); //滑块滑动
	$("#"+Nav+" li").eq(i).addClass("current").siblings().removeClass("current");
	};
	
	/**
	 * 加载主页的资讯信息
	 */
	function loadHomeNews(){
		$.ajax({
			type : 'post',
			url : 'nwes/homeNwes.html',
			dataType : 'json',
			success : function(obj) {
				var newsHteml = homeNewsHtml(obj.news_0,'业界动态','images/xiaoshou.jpg')+
						homeNewsHtml(obj.news_1,'技术科普','images/kepu.jpg')+
						homeNewsHtml(obj.news_2,'销售技巧','images/jiqiao.jpg')+
						homeNewsHtml(obj.news_3,'常见问题','images/wenti.jpg')+
						homeNewsHtml(obj.news_4,'最新行情','images/hangqing.jpg')+
						homeNewsHtml(obj.news_5,'评测导购','images/pingce.jpg');
				$("#admin_home_homeNewsHteml").html(newsHteml);
			}
		});
	};
	
	function homeNewsHtml(list,listName,defaultPic){
		if( undefined != list && list.length>0){
			var pic = list[0].newsPic == undefined?defaultPic:list[0].newsPic;
			var strHtml = "<li><div class=\"home_main_new_04\">"+listName+"</div>" +
				"<div class=\"home_main_new_01\">" +
					"<div class=\"home_main_new_02\">" +
						"<a href=\"nwes/view/"+list[0].id+".html\" target=\"_blank\">"+list[0].firstTitle+"</a>" +
					"</div>" +
					"<a href=\"nwes/view/"+list[0].id+".html\" target=\"_blank\"><img src=\""+pic+"\" width=\"356\" height=\"123\" alt=\""+listName+"\"/></a>" +
				"</div>";
				strHtml = strHtml +"<div class=\"home_main_new_03\">";
			for (var i=1;i<list.length;i++){
				strHtml = strHtml+
					"<a href=\"nwes/view/"+list[i].id+".html\" target=\"_blank\">▪ "+list[i].firstTitle+"</a><br/>";
			}
				strHtml = strHtml +"</div></li>";
			return strHtml;
		}else{
			return "";
		}
	};
	
	/**
	 * 楼层资讯
	 */
	function homeFloorNews(){
		$.ajax({
			type : 'post',
			url : 'nwes/FloorNwes.html',
			dataType : 'json',
			success : function(obj) {
				homeFloorNewsHtml(obj.newsFloor_1,"admin_home_homeFloorNews_1F");
				homeFloorNewsHtml(obj.newsFloor_2,"admin_home_homeFloorNews_2F");
				homeFloorNewsHtml(obj.newsFloor_3,"admin_home_homeFloorNews_3F");
				homeFloorNewsHtml(obj.newsFloor_4,"admin_home_homeFloorNews_4F");
				homeFloorNewsHtml(obj.newsFloor_5,"admin_home_homeFloorNews_5F");
				homeFloorNewsHtml(obj.newsFloor_6,"admin_home_homeFloorNews_6F");
			}
		});
	};
	function homeFloorNewsHtml(list,floorId){
		if(list != undefined){
			var strHtml = "";
			var newName = "";
			for (var i=0;i<list.length;i++){
				newName = list[i].firstTitle.length >15?list[i].firstTitle.substr(0, 22):list[i].firstTitle;
				//console.info(newName);
				strHtml = strHtml+
					"<li><a target=\"_blank\" href=\"nwes/view/"+list[i].id+".html\"> "+newName+"</a><li/>";
			}
			$("#"+floorId).html(strHtml);
		}
		
	}
	
/*	*//**
	 * 跳转到列表页 
	 * @param catNames 要查询的类别名称
	 *//*
	function goListView(catNames){
		var form = $('<form id="js_submit_form" action="listView/list.html" method="post" target="_self" style="display:none"></form>');  
	    var catNamesInput = '<input type="text" name="catNames" value="'+catNames+'"/>';
	    var sub = '<input type="submit"';
	    form.append(catNamesInput); 
	    form.append(sub);
	    $("body").append(form);
	    //form.submit().click();
	    document.getElementById("js_submit_form").submit();
	}*/