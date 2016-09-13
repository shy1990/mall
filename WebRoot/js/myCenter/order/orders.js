
var paramInputName ;
var paramInputVal ;
$(function(){
	
	$("img").each(function(){
		if(!$(this).attr("src")){//如果当前图片没值那么就添加默认图片
			$(this).attr("src","images/goodsHotImg.jpg");
		}
	});
	
	//折叠订单
	$(".order_zhe_die_img").click(function(){
		var state = $(this).data("state");//1待折叠，2已折叠
		var parent = $(this).parent().parent();
		var detail = parent.next();
		
		if(state==1){
			//更新data-state状态为2
			$(this).data("state","2");
			//更换成+号图片
			$(this).attr("src","images/zhankai.jpg");
			//隐藏下面的详情
			detail.hide();
			//显示总价格，总数量
			parent.find(".caigou_main_top_num").show();
			parent.find(".caigou_main_top_price").show();
			var num = 0;
			detail.find(".order_detail_nums").each(function(){
				//console.info();
				num+=parseInt($(this).text());
			});
			parent.find(".caigou_main_top_num").text(num);
		}else{
			//更新data-state状态为1
			$(this).data("state","1");
			//更换成+号图片
			$(this).attr("src","images/shouqi.jpg");
			//隐藏下面的详情
			detail.show();
			//隐藏总价格，总数量
			parent.find(".caigou_main_top_num").hide();
			parent.find(".caigou_main_top_price").hide();
		}
		
	});
	
	//初始化分页
	//初始化选中的选项
	
	$(".ddgl_main_nav li").each(function(i,li){
		if(i==selected){
			$(li).find("a").addClass("selected");
			if(i!=0){
				if(i==1){ 
					paramInputName="payStatus";
					paramInputVal="1";
				}
				if(i==2){
					paramInputName="payStatus";
					paramInputVal="0";
				}
				if(i==3){
					paramInputName="shipStatus";
					paramInputVal="0";
				}
			}
		}
		////console.info(paramInputName+"  "+paramInputVal);
	});
	
	ordersPage(pageCount,page);//重新载入分页差劲
	
	//初始化调整分页位置
	$(".fenye").each(function(i,fenye){
		var liNum = $(fenye).find("li").length; 
		//如果页数小于8那就重新定位页码的位置，使其居中
		if(liNum<9){
			var width = $(fenye).width();
			//$(fenye).css('margin-left',(width-liNum*60)/2+"px");
			$(fenye).css('margin-left',(width-liNum*60)+"px");
		} 
	});
	
	//全选
	$("#orders_select_all").live("click",function(){
		//console.info("点击全选按钮："+$(this).attr("checked"));
		//判断是选中还是取消选中
		if($(this).attr("checked")!="checked"){
			//console.info("取消选中");
			$(".caigou_main").each(function(i,caigou){
				$(caigou).find("#order_select_input").removeAttr('checked');
			});
		}else{
			//console.info("选中全选按钮");
			$(".caigou_main").each(function(i,caigou){
				$(caigou).find("#order_select_input").attr("checked",true);
			});
		}
	});
	
	
	$(".order_zhe_die_img").each(function(i){
		if(i!=0){
			$(this).click();
		}
	});
	
	
	
	
	
});

/**
 * 单品分页方法插件
 * @param totalPages 总页数
 * @param visiblePages 默认显示页码数
 * @param currentPage 选中d页码数
 */
function ordersPage(totalPages,currentPage){
	$.jqPaginator('#mycenter_orders_page', {
	    totalPages: totalPages,//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        ////console.info(type + '：' + num);
	        if(page!=num){ 
	        	//page=num; 
	        	againDataPage(num)
	        }
	        if(selected==0 && page!=num){//如果选中的是第一个那么就，加分页然后跳转
	        	var form = $('<form id="js_submit_form"></form>');  
	            form.attr('action', "order/orders.html");  
	            form.attr('method', 'post');  
	            form.attr('target', '_self');  
	            //如果page等于1  那么就传入page是1 name和val直接取传入的。如果page不等于1
	            var pageInput = '<input type="text" name="page" value="'+num+'"/>';
	            form.append(pageInput);  
	            //form.submit();
	            $("body").append(form);
	    	    //form.submit().click();
	    	    document.getElementById("js_submit_form").submit();
	        }
	    } 
	});
}

/**
 * 根据选择的条件重新加载数据
 */
function againData(type,val,sel){
	//console.info("模拟提交数据");
	var form = $('<form id="js_submit_form" style="display: none;" ></form>');  
    form.attr('action', "order/orders.html");  
    form.attr('method', 'post');  
    form.attr('target', '_self');  
    var paramInput = '<input style="display: none;" type="text" name="'+type+'" value="'+val+'"/>';
    var selectedInput = '<input style="display: none;" type="text" name="selected" value="'+sel+'"/>';
    var searchParamInput = '<input style="display: none;" type="text" name="searchParam" value="'+$("#orders_searchParam_input").val()+'"/>';
    form.append(paramInput);  
    form.append(selectedInput+searchParamInput); 
    //console.info("直接请求数据"+form);
    //form.submit();
    $("body").append(form);
    //form.submit().click();
    document.getElementById("js_submit_form").submit();
}

function search_order_by_time(){
	var form = $("#search_order_by_time");
	var isSubmit = true;
	if(!form.find("input").eq(0).val()){
		alert("请选择开始时间");
		isSubmit = false;
		return false;
	}
	if(!form.find("input").eq(1).val()){
		alert("请选择结束时间");
		isSubmit = false;
		return false;
	}
	if(isSubmit){
		form.submit();
	}
	
	
}


/**
 * 根据选择的条件重新加载数据
 */
function againDataPage(pages){

	var form = $('<form id="js_submit_form"></form>');  
    form.attr('action', "order/orders.html");  
    form.attr('method', 'post');  
    form.attr('target', '_self');  
    
    var paramInput = '<input type="text" name="'+paramInputName+'" value="'+paramInputVal+'"/>';
    var pageInput = '<input type="text" name="page" value="'+pages+'"/>';
    var selectedInput = '<input type="text" name="selected" value="'+selected+'"/>';
    var searchParamInput = '<input type="text" name="searchParam" value="'+$("#orders_searchParam_input").val()+'"/>';
    form.append(paramInput);
    form.append(pageInput);
    form.append(selectedInput+searchParamInput);
    ////console.info("点击分页重新请求参数"+form);
    //form.submit();
    $("body").append(form);
    //form.submit().click();
    document.getElementById("js_submit_form").submit();
}

//根据搜索条件搜索数据
function searchOrderFun(){
	if($("#orders_searchParam_input").val()){
		$("#orders_searchParam_form").submit();
	}else{
		alert("请输入搜索条件");
	}
}



/**
 * 删除订单
 */
 function delOrder(id){
	 
	 //console.info("开始删除");
	 
	var ids = new Array();
	if(id==undefined){//批量删除
		$("#order_select_input:checked").each(function(i,input){
			 ids.push($(input).val());
		 });
	}else{//删除单个
		ids.push(id);
	}
	
	$.ajax({ 
		type: "post", 
		url : "order/delOrder.html",
		dataType:'json', 
		data: {'ids':ids.toString()}, 
		success: function(d){
			//var d = $.parseJSON(data);
			//console.info(d);
			if(id==undefined){//批量删除
				$("#order_select_input:checked").each(function(){
					$(this).closest(".caigou_main").remove();
					 ids.push($(this).val());
				 });
			}else{//删除单个
				$("#"+id).remove();
			}
			//判断当前选中的是那个状态的数据，为了最后删除成功以后修改上面的数量显示
			$(".ddgl_main_nav_box .ddgl_main_nav li:eq(1) h4").html(d.obj.paidNum);
			$(".ddgl_main_nav_box .ddgl_main_nav li:eq(2) h4").html(d.obj.obligationNum);
			$(".ddgl_main_nav_box .ddgl_main_nav li:eq(3) h4").html(d.obj.staySendNum);
		}
	});
}; 

/**
 * 查看订单详情
 * @param id 订单id
 */
function orderDetail(id){
	var form = $('<form id="js_submit_form" method="post" target="_self" action="order/detail.html"></form>');  
    var idInput = '<input type="text" name="id" value="'+id+'"/>';
    form.append(idInput);  
    ////console.info("点击分页重新请求参数"+form);
    //form.submit();
    $("body").append(form);
    document.getElementById("js_submit_form").submit();
}


/**
 * 去评价
 * @param id 订单id
 */
function goEvaluate(id){
	var form = $('<form id="js_submit_form" method="post" target="_self" action="evaluate/evaluate.html"></form>');  
    var idInput = '<input type="text" name="id" value="'+id+'"/>';
    form.append(idInput);  
    ////console.info("点击分页重新请求参数"+form);
    //form.submit();
    $("body").append(form);
    document.getElementById("js_submit_form").submit();
}

