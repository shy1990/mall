$(function(){
	////console.info("加载配件js文件");
	//禁止输入非数字字符
	$(".xq_main_02 input[type='text']").live("keyup",function(){  //keyup事件处理 
		var num = $(this).val().replace(/\D|^0/g,'0');
		if(num==$(this).val()==0){
			$(this).val(0);
		}
    }).bind("paste",function(){  //CTR+V事件处理 
    	var num = $(this).val().replace(/\D|^0/g,'0');
		if(num==$(this).val()==0){
			$(this).val(0);
		}
    }).css("ime-mode", "disabled");  //CSS设置输入法不可用
	
	var fristDeal = true;
	//给选中的li添加选中的样式
	$(".xq_main_03 .xq_main_03_top li").live("click",function(){
		
		////console.info("点击切换");
		
		$(this).addClass("selecter").siblings().removeClass("selecter");
		var i = $(this).index();
		////console.info("点击切换："+i);
		$(".goods_main_contents").each(function(n,con){
			////console.info(n);
			////console.info(n==i);
			if(i==n){
				$(con).show();
			}else{
				$(con).hide();
			}
		});
		if(i==3){//去请求评论数据，然后初始化分页插件
			evaluateAjax();//加载此评论相关信息
		}
		if(i==4 && fristDeal){//加载成交记录
			fristDeal = false;
			dealFn();
		}
	});
	
	
	initializeEvaluateNum();//初始化评论总数量
	
	//添加收藏和取消收藏
	$(".xq_main_click_collect").live("click",function(){
		var type = $(this).attr("type");
		var targetid = $(this).attr("targetid");
		add_drop_collect($(this),type,targetid);
	});
	
	$("#gou_mai_num").keyup(function(e) {
		if (e.which == 13) {
			////console.info("检测到回车，阻止回车提交");
			return false;
		}else{
			var stock = parseInt($(this).data("stock"));
			if(stock<parseInt($(this).val())){
				$(this).val(stock);
				alert("库存不足！");
			}
		}
	});
});



/**
 * 添加或者删除(取消)收藏
 */
var add_drop_collect = function add_drop_collect(obj,type,targetId){
	////console.info("targetId："+targetId);
	$.ajax({
		type: "post", 
		url : "/collect/collect.html",
		dataType:'json',
		data: {'type':type,'targetId':targetId}, 
		success: function(d){ 
			////console.info(d);
			if(d.msg=="取消收藏"){
				obj.attr("src","../images/SHOUC.jpg");
			}
			if(d.msg=="收藏成功"){
				obj.attr("src","../images/SHOUC_01.jpg");
			}
		}
	});
};





//统计购买数量
function statsBuyNum(num){
	
	var input = $("#xq_main_02_let_03_ret input[type='text']");
	////console.info(input.val());
	var i = parseInt(num)+parseInt(input.val());
	
	var stock = parseInt(input.data("stock"));
	////console.info("我累个去"+num+"  :"+stock);
	
	if(stock<parseInt(i)){
		alert("库存不足！");
		return; 
		
	}
	
	////console.info(i);
	if(i<=0){
		input.val(0);
	}else{
		input.val(i);
	}
}


/**
 * 加入购物车跳转到购物车页面
 */
function add_cart_show(){
	var numInput = $("#xq_main_02_let_03_ret input[type='text']");
	var num = numInput.val();
	
	var targetId = $(".sanji_clxx_box_right_06 input").val();
	////console.info(num+"  "+targetId);
	if(num==""||parseInt(num)==0){
		alert("请先选择购买的数量！");
		return;
	}
	
	var stock = parseInt(numInput.data("stock"));
	
	if(stock<parseInt(num)){
		alert("库存不足！");
		return; 
	}
	
	$("#go_add_cart").submit();
}

/**
 * 通过ajax获取评论信息列表
 */
var pingjiaPage = 1;
function evaluateAjax(){ 
	$.ajax({
		type: "post", 
		url : "evaluate/evaluates.html",
		dataType:'json',
		data: {'targetType':'acs','targetId':acsId,'page':pingjiaPage}, 
		success: function(d){
			////console.info(d);
			var evaluatePage = d.page;
			var evaluatePageCount = d.pageCount;
			
			//清空以前的评论信息
			var ul = $(".bz_mian_02 .pingj_pingj ul");
			ul.html("");
			//遍历评价列表
			$.each(d.evaluates,function(i,e){
				////console.info("e");////console.info(e);
				var li =  
				  '<li>'+
		            '<table border="0" cellspacing="0" cellpadding="0">'+
		             '<tbody><tr>'+
		              '<td width="228"><span>'+e.members.username+'</span><br>'+
		                e.createtime+'</td>'+
		              '<td width="324">';
				var skuNum = 0;
				//遍历此评论包含的单品列表
				$.each(e.orderItemss,function(i2,o){
					skuNum += parseInt(o.nums);
					li += o.accessory.brand.name + o.accessory.name + o.accessory.color.colorName +'<br>';
				});
		             li+=' <td width="250" align="center">{ <h5>'+skuNum+' </h5>件 }</td>'+
		              '<td width="334"><div class="pingj_pingj_01"><img src="';
		            if(e.totality==1){
		            	 li+='images/happy.jpg';
		            }
					if(e.totality==2){
						li+='images/engry.jpg';	            	 
					}
					if(e.totality==3){
						li+='images/chaping.jpg';
					}
		              li+='" width="25" height="28" align="left">'+e.content+'</div></td>'+
		             '</tr>'+
		           '</tbody></table>'+
		          '</li>';
				
				//添加到ul里面
		        ul.append(li);
			});
			
			//重新加载分页插件
			pageMethod(d.pageCount,d.page);
			//初始化调整分页位置
			$("#goods_pingjia_fenye").each(function(i,fenye){
				var liNum = $(fenye).find("li").length; 
				//如果页数小于8那就重新定位页码的位置，使其居中
				if(liNum<9){ 
					var width = $(fenye).width();
					//$(fenye).css('margin-left',(width-liNum*60)/2+"px");
					$(fenye).css('margin-left',(width-liNum*60)+"px");
				} 
			}); 
		}
	});
}

/**
 * 初始化评论总数量
 */
function initializeEvaluateNum(){
	$.ajax({
		type: "post", 
		url : "evaluate/countNum.html",
		dataType:'json',
		data: {'targetType':'acs','targetId':acsId,'page':pingjiaPage}, 
		success: function(d){
			$("#goods_pingjia_num_span").append(d);
		}
	}); 
}

function pageMethod(totalPages,currentPage){
	$.jqPaginator('#goods_pingjia_page_ul', { 
	    totalPages: totalPages,//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        if(pingjiaPage!=num){ 
	        	//dataPage(num);
	        	pingjiaPage = num;
	        	evaluateAjax();
	        }
	    } 
	});
}



/**
 * 月成交记录
 */
var dealPageNum=1;//当前月成交记录的数据页数
function dealFn(){
	$.ajax({
		type: "post", 
		url : "order/deal.html",
		dataType:'json',
		data: {'targetType':'acs','targetId':acsId,'page':dealPageNum}, 
		success: function(data){
			if(data){
				var pageCount = data.pageCount;
				$("#goods_deal_ul").html("");
				$.each(data.deals,function(i,d){ 
					var fristName = "";
					var endName = "";
					//判断用户名长度
					if(d.USERNAME.length<4){//如果用户名长度小于4那么就取前一后一
						fristName = d.USERNAME.substr(0,1);
						endName = d.USERNAME.substr(d.USERNAME.length-1,d.USERNAME.length);
					}else{//如果用户名长度大于等于4那么就取前二后二
						fristName = d.USERNAME.substr(0,2);
						endName = d.USERNAME.substr(d.USERNAME.length-2,d.USERNAME.length);
					}
					
					var dealHtml = 
					'<li>'+
						'<table width="1147" height="110" border="0" cellpadding="0" cellspacing="0">'+
							'<tbody>'+
								'<tr>'+
									'<td width="197">'+fristName+'**'+endName+'</td>'+
									'<td width="524">配件:'+d.ACS_NAME+
										'<br> 机身颜色:'+d.AC_NAME+
									'<td width="113" align="left">'+d.NUMS+' </td>'+
									'<td width="151">'+
										'<h5>¥'+d.DEAL_PRICE+'</h5> </td>'+
									'<td width="162">'+d.CREATETIME+
									'</td>'+ 
								'</tr>'+
							'</tbody>'+
						'</table>'+
					'</li>';
					$("#goods_deal_ul").append(dealHtml);
				});
				
				dealPageMethod(pageCount,dealPageNum);
			}
		}
	});
}

/**
 * 月成交记录分页
 * @param totalPages
 * @param currentPage
 */
function dealPageMethod(totalPages,currentPage){
	$.jqPaginator('#goods_deal_page_ul', {
	    totalPages: totalPages,//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        if(dealPageNum!=num){ 
	        	//dataPage(num);
	        	dealPageNum = num;
	        	dealFn(); 
	        }
	    } 
	});
}

