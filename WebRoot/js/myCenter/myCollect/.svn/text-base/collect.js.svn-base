var skuThisPage = 1;//当前单品页码
var acsThisPage = 1;//当前单品页码
var rows = 5;//每页默认显示的数量

var skuCollectNum = Math.ceil(parseInt(skuCollectNums)/rows);//单品收藏总数量
var acsCollectNum = Math.ceil(parseInt(acsCollectNums)/rows);

$(function(){
	
	$("img").each(function(){
		if(!$(this).attr("src")){//如果当前图片没值那么就添加默认图片
			$(this).attr("src","images/goodsHotImg.jpg");
		}
	});
	
	//console.info(skuCollectNum+"   "+acsCollectNum);
	//初始化分页插件
	sku_page(skuThisPage);//重新载入分页差劲
	acs_page(acsThisPage);//重新载入分页差劲
	//初始化调整分页位置
	$(".fenye").each(function(i,fenye){ 
		var liNum = $(fenye).find("li").length; 
		//如果页数小于8那就重新定位页码的位置，使其居中
		if(liNum<9){
			var width = $(fenye).width();
			$(fenye).css('margin-left',(width-liNum*60)+"px");
		}
	});
	
	/** 
	 * 添加收藏取消收藏 
	 */
	$(".acs_detail_collect").live("click",function(){
		add_drop_collect($(this),"accessory",$(this).attr("targetId"));
	});
	
	$("#collect_tab_sku_li").click(function(){
		$("#myTabContent .wodz_ret_01:eq(0)").show();
		$("#myTabContent .wodz_ret_01:eq(1)").hide();
		$("#collect_tab_acs_li").addClass("tabSelect");
		$(this).removeClass("tabSelect");
	});
	
	$("#collect_tab_acs_li").click(function(){
		$("#myTabContent .wodz_ret_01:eq(0)").hide();
		$("#myTabContent .wodz_ret_01:eq(1)").show();
		$("#collect_tab_sku_li").addClass("tabSelect");
		$(this).removeClass("tabSelect");
	}); 
	
	
	
	
	
});

/**
 * 单品分页方法插件
 * @param totalPages 总页数
 * @param visiblePages 默认显示页码数
 * @param currentPage 选中d页码数
 */
function sku_page(currentPage){
	if(skuCollectNums==0){
		skuCollectNums = 1;
	}
	$.jqPaginator('#my_sku_collect_page', {
	    totalPages: Math.ceil(parseInt(skuCollectNums)/rows),//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        ////console.info(type + '：' + num);
	        if(skuThisPage!=num){
	        	skuThisPage=num; 
	        	collectAjax("sku",skuThisPage);
	        }
	    }
	});
	//单品分页点击触发分页请求
	/*$("#my_sku_collect_page li").live("click",function(){
		//console.info("单品点击请求分页加载数据");
		if($(this).attr("jp-data")!=skuThisPage){
			skuThisPage=parseInt($(this).attr("jp-data")); 
        	collectAjax("sku",skuThisPage);
		}
	});*/
}

/**
 * 配件分页方法插件
 * @param totalPages 总页数
 * @param visiblePages 默认显示页码数
 * @param currentPage 选中d页码数
 */
function acs_page(currentPage){
	if(acsCollectNums==0){
		acsCollectNums = 1;
	}
	$.jqPaginator('#my_acs_collect_page', {
	    totalPages: Math.ceil(parseInt(acsCollectNums)/rows),//总页数
	    visiblePages: 8,//默认显示页码数
	    currentPage: currentPage,//默认选中页码数
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        //console.info(type + '：' + num);
	        if(acsThisPage!=num){
	        	acsThisPage=num;
	        	collectAjax("acs",acsThisPage);
	        }
	    }
	});
	//配件分页点击触发分页请求
	/*$("#my_acs_collect_page li").live("click",function(){
		//console.info("配件点击请求分页加载数据!");
		if($(this).attr("jp-data")!=acsThisPage){
			acsThisPage=parseInt($(this).attr("jp-data")); 
        	collectAjax("acs",acsThisPage);
		}
	});*/
}

/**
 * ajax分页请求收藏数据
 */
function collectAjax(type,page){
	//console.info("开始请求收藏数据："+type+"  page:"+page);
	$.ajax({ 
		type: "post", 
		url : "collect/ajaxCollect.html",
		dataType:'json',
		data: {'type':type,'page':page,'rows':rows}, 
		success: function(d){
			//console.info(d); 
			if(d!=null){
				if(type=="sku"){//遍历单品
					var skuUl = $(".sku_collect_list");
					skuUl.html("");
					//清空单品ul
					$.each(d,function(i,sku){
						//console.info("sku");
						//console.info(sku);
						var skuStr = '<li id="'+sku.TARGET_ID+'"><div class="wodz_ret_01_let_txt_01"><a href="goods/detail/'+sku.GOODS_NUM+'.html"><img src="'+sku.PIC_SRC +'" width="74" height="74" alt=""/></a></div><div class="wodz_ret_01_let_txt_02"><a href="goods/detail/'+sku.GOODS_NUM+'.html">'; 
						if(sku.BRANDNAME){skuStr+=sku.BRANDNAME} 
						if(sku.NAME){skuStr+=sku.NAME} 
						if(sku.EDITION){skuStr+=sku.EDITION} 
						if(sku.STANDARD){skuStr+=sku.STANDARD} 
						if(sku.COLOR_NAME){skuStr+=sku.COLOR_NAME} 
						skuStr+='</a><br />收藏时间： '+sku.COLLECT_TIME +' <br /></div><div class="wodz_ret_01_let_txt_04"><a href="goods/detail/'+sku.GOODS_NUM+'.html">立即购买</a></div><div class="wodz_ret_01_let_txt_04"><a href="javascript:;" onclick="drop_collect(this,"sku","'+sku.TARGET_ID+'")" >取消收藏</a>	</div>	</li>';
						skuUl.append(skuStr);
					});
					sku_page(page);//重新载入分页差劲
				}else{//遍历配件
					var acsUl = $(".acs_collect_list");
					acsUl.html("");
					$.each(d,function(i,acs){
						//console.info(acs); 
						var acsStr = '<li id="'+acs.TARGET_ID+'"><div class="wodz_ret_01_let_txt_01"><a href="accessory/detail/'+acs.ACCESSORIES_NUM+'.html"><img src="'+acs.DEFAULT_IMG+'" width="74" height="74" alt=""/></a></div><div class="wodz_ret_01_let_txt_02"><a href="accessory/detail/'+acs.ACCESSORIES_NUM+'.html">'+acs.BRANDNAME +acs.NAME + acs.COLOR_NAME +'</a><br />收藏时间： '+acs.COLLECT_TIME+' <br /></div><div class="wodz_ret_01_let_txt_04"><a href="accessory/detail/'+acs.ACCESSORIES_NUM+'.html">立即购买</a></div><div class="wodz_ret_01_let_txt_04"><a href="javascript:;" onclick="drop_collect(this,"accessories","'+acs.TARGET_ID+'")" >取消收藏</a></div></li>';
						acsUl.append(acsStr);
					});
					acs_page(page);//重新载入分页差劲
				}
			}
		}
	});
}



/**
 * 删除(取消)收藏
 */
 function drop_collect(obj,type,targetId){
	//console.info("targetId："+targetId);
	$.ajax({
		type: "post", 
		url : "/collect/collect.html",
		dataType:'json',
		data: {'type':type,'targetId':targetId}, 
		success: function(d){
			//console.info(d);
			if(d.msg=="取消收藏"){
				$("#"+targetId).remove();
			}
			if(d.msg=="收藏成功"){
				
			}
		}
	});
};

