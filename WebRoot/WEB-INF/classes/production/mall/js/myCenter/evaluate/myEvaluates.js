
$(function(){
	
	//初始化分页
	//初始化选中的选项
	//console.info("开始初始化分页");
	pageMethod(countNum,page);//重新载入分页差劲
	
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
	


});

/**
 * 单品分页方法插件
 * @param totalPages 总页数
 * @param visiblePages 默认显示页码数
 * @param currentPage 选中d页码数
 */
function pageMethod(totalPages,currentPage){
	$.jqPaginator('#mycenter_myEvaluates_page', {
	    totalPages: totalPages,//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        if(page!=num){ 
	        	dataPage(num);
	        }
	    } 
	});
}

/**
 * 根据选择的条件重新加载数据
 */
function dataPage(num){ 
	//console.info("模拟提交数据");
	var form = $('<form action="evaluate/myEvaluates.html" method="post" target="_self"></form>');  
    var paramInput = '<input type="text" name="page" value="'+num+'"/>';
    form.append(paramInput);  
    //console.info("直接请求数据"+form);
    form.submit(); 
}

