//手机列表js

var param = new Object();

var sort = "";
var order = "desc";
var ajaxUrtType = "godos";
var page = 1 ;
var rows = 20;
var lastPage = 1;

$(function(){
	
	//过滤，input只可以输入数字
	$(".phone_list_price").live("keyup",function(){  //keyup事件处理 
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
	
	
	
	//模拟点击事件，判断要请求配件还是手机   qingqiuType请求类型//从别的地方跳过来的时候，把参数从后台传入到前台，然后再由js摸你点击加载数据
	
	//模拟点击手机初始化加载数据
	/*$(".phone_main_01_txt:eq(0) .phone_main_01_txt_ret_01 a").each(function(){
		//判断如果当前遍历对象在默认查询里面有，那么就摸你点击查询加载数据
		if(defaultCatIds.indexOf($(this).attr("paramVal"))>=0){
			selectedScreen($(this));//调用方法但是不进行远程服务器请求
			return true;
		}  
	});*/
	//判断默认品牌是否为空，如果不为空那么就在继续遍历品牌
	$(".phone_main_01_txt:eq(0) .phone_main_01_txt_ret_01 a").each(function(){
		////console.info("遍历品牌");
		//判断如果当前遍历对象在默认查询里面有，那么就摸你点击查询加载数据
		if(defaultBrandIds.indexOf($(this).attr("paramVal"))>=0){
			////console.info("查找到了相同的品牌");
			selectedScreen($(this));//调用方法但是不进行远程服务器请求
			return true;
		}  
	}); 
	//判断价格 
	$(".phone_main_01_txt:eq(1) .phone_main_01_txt_ret_01 a").each(function(){
		//判断如果当前遍历对象在默认查询里面有，那么就摸你点击查询加载数据
		if(defaultPrices.indexOf($(this).attr("paramVal"))>=0){
			selectedScreen($(this));//调用方法但是不进行远程服务器请求
			return true;
		}  
	});
	//关键词搜索
	if(searchParam!=undefined && searchParam!='' && searchParam!="undefined"){
		$("#head_searchParam").val(searchParam);
		//隐藏列表页搜索框
		//把搜索的关键词放上去
		$(".phone_main_01_top_fit").hide();
		
		var searchHtml = '<div class="phone_main_01_top_mid"><a href="javascript:delSearchParamShow(this);"><strong>'+searchParam+'</strong><b></b></a></div>';
		
		//向头部添加
		$(".phone_main_01_top_param").append(searchHtml);
	}
	
	//最后请求服务器
	screen();
	
	//填写价格
	$(".phone_list_price").live("keyup",function(){
		var price = "";
		var go = true;
		//检测两个是否都有值
		$(".phone_list_price").each(function(i,input){
			if(!$(input).val()){
				$("#phone_list_price_but").attr("disabled","true");
				go = false;
			}
			if(i==0){
				price += $(input).val();
			}else{
				price += "-"+$(input).val();
			}
		});
		if(go){
			//两个都有值那么就解锁b按钮 吗、缺值就锁定按钮
			$("#phone_list_price_but").removeAttr("disabled");
			$("#phone_list_price_but").attr("paramVal",price);
		}
	});
	
	
	//多选
	$(".phone_main_01_txt_ret_03").live("click",function(){
		
		cancelSlected($(".selectedShow .btn-red"));//取消已经展开的多选选择
		var parent = $(this).parent();
		parent.siblings(".v-btns").show().addClass("selectedShow");
		parent.siblings("ul").find("a").addClass("checkbox");
		$(this).parent().hide();
		
	});
	
	//确定多选结果
	$(".v-btns .btn-red").live("click",function(){
		////console.info("隐藏自己的父元素，显示多选选项，遍历是否选中了元素");
		//遍历选中的条件，添加到上面，然后请求ajax加载数据
		var checkboxs = $(this).parent().siblings("ul").find(".checkboxSelected");
		if(checkboxs.length>0){
			var phone_main_01_txt = $(this).closest(".phone_main_01_txt");
			var topShowStr = "";//上部显示选中的筛选条件
			var keyArray = new Array();
			checkboxs.each(function(i,a){
				if($(a).attr("paramVal")==undefined){//没有值取text
					topShowStr+=$(a).text();
					//放进keyArray
					keyArray.push($(a).text());
				}else{//取值
					topShowStr+=$(a).text();
					//放进keyArray
					keyArray.push($(a).attr("paramVal"));
				}
				if(i+1!=checkboxs.length || i!=0){
					topShowStr+="、";
				} 
			});
			param[$(this).attr("key")] = keyArray;
			//放到头部显示
			var htmlStr = '<div class="phone_main_01_top_mid" key="'+$(this).attr("key")+'"><a href="javascript:;">'+phone_main_01_txt.children(".phone_main_01_txt_let").text()+':<strong>'+topShowStr+'</strong><b></b></a></div>';
			$(".phone_main_01_top .phone_main_01_top_param").append(htmlStr);
			phone_main_01_txt.addClass($(this).attr("key")).hide();
			
			screen();//去服务器请求数据
		}
		
		//隐藏掉已经选中的a//还是把这一行都隐藏掉
		cancelSlected($(this));
	});
	
	//删除筛选条件
	$(".phone_main_01_top_mid a").live("click",function(){
		var key = $(this).parent().attr("key");
		////console.info("key:"+key);
		$(this).parent().remove();
		//从param里面根据key删除此记录
		delete param[key]; 
		searchParam = null;//把模糊查询条件删除掉
		//删除此条记录
		$(".phone_main_01").children("div").eq(1).children("."+key).show();
		screen();//重新加载数据
	});
	
	//取消选择的筛选条件
	$(".v-btns .btn-gray").live("click",function(){
		cancelSlected($(this));
	});
	
	//选择筛选条件
	$(".phone_main_01_txt_ret_01 a").live("click",function(){
		selectedScreen($(this));
		screen();
	}); 
	
	//选择筛选条件
	$("#phone_list_price_but").live("click",function(){
		selectedScreen($(this));
		screen();
	});
	
	
	
	//排序字段和方式
	$(".ny_box_let_che_01_let li").live("click",function(){
		var a = $(this).children("a");
			sort = a.attr("sort");
			//判断此时是否为选中//已选中就改为asc未选中就选中undefined
			if($(this).attr("class") != "sortSelected"){//没有被选中
				$(this).addClass("sortSelected");
				$(this).css({'background':'url(../images/desc_01.jpg) no-repeat center right'});
			}else{//已经选中
				
				if(a.attr("order")=="desc"){
					$(this).css({'background':'url(../images/asc_01.jpg) no-repeat center right'}).css({'color':'#F30'});
					order ="asc";
					a.attr("order","asc");
				}else{
					$(this).css({'background':'url(../images/desc_01.jpg) no-repeat center right'}).css({'color':'#F30'});
					order ="desc";
					a.attr("order","desc");
				}
			}
			var siblingsLi = $(this).siblings(".sortSelected"); 
			
			siblingsLi.children("a").attr("order","desc").css({'color':'#333'});
			siblingsLi.css({'background':'url(../images/desc.jpg) no-repeat center right'}).removeClass("sortSelected");
			
			screen();//重新加载数据
			
			////console.info("开始重新加载数据："+sort+"   "+order);
	});
	
	/**
	 * 点击分页请求数据
	
	$("#phone_list_page_ul li").live("click",function(){
		var num = parseInt($(this).attr("jp-data"));
		//console.info("num:"+num+"  "+page);
		if(page != num){
			page = num;
			screen();
		}
		
	}); */
	
	 
	
	
});


/**
 * 在当前条件下搜索
 */
function pSearchParam(){
	//判断当前是否有查询条件//如果已经有查询条件那么就覆盖查询
	searchParam = $("#phone_list_search_param").val();
	
	$(".phone_main_01_top_fit").hide();
	
	var searchHtml = '<div class="phone_main_01_top_mid"><a href="javascript:delSearchParamShow(this);"><strong>'+searchParam+'</strong><b></b></a></div>';
	
	//向头部添加
	$(".phone_main_01_top_param").append(searchHtml);
	
	screen();//重新加载数据
}

function keyDownSearch(e) {
  var ev= window.event||e;
  //13是键盘上面固定的回车键
  if (ev.keyCode == 13) {
	  pSearchParam();
  }
}

function delSearchParamShow(obj){
	screen();//重新加载数据
	$(obj).parent("div").remove();
	searchParam = null;
	$(".phone_main_01_top_fit").show();
	
}

/**
 * 选择筛选
 * @param obj
 */
function selectedScreen(obj){
	var classStr = obj.attr("class");
	
	if(classStr==undefined){//单选模式 
		
		if(obj.attr("type")=="cat"){
			if(obj.text().trim()=="手机"){
				ajaxUrtType = "goods";//后期改进为直接写请求地址
			}else{
				ajaxUrtType = "accessory";//后期改进为直接写请求地址
			}
		}
		
		var phone_main_01_txt = obj.closest(".phone_main_01_txt");
		var red = obj.closest("ul").siblings(".v-btns").find(".btn-red");
		var topShowStr = "";
		var keyArray = new Array();
		
		if(obj.attr("paramVal")==undefined){//没有值取text
			topShowStr+=obj.text();
			//放进keyArray
			keyArray.push(obj.text());
		}else{//取值 
			if(obj.text()){//如果text有值就取text
				topShowStr+=obj.text();
			}else{//没值取val
				topShowStr+=obj.attr("paramVal");
			}
			//放进keyArray
			keyArray.push(obj.attr("paramVal")); 
		}
		param[red.attr("key")] = keyArray;
		//放到头部显示
		var htmlStr = '<div class="phone_main_01_top_mid" key="'+red.attr("key")+'"><a href="javascript:;">'+phone_main_01_txt.children(".phone_main_01_txt_let").text()+':<strong>'+topShowStr+'</strong><b></b></a></div>';
		$(".phone_main_01_top .phone_main_01_top_param").append(htmlStr);
		phone_main_01_txt.addClass(red.attr("key")).hide();
		
		//screen();//重新加载数据
		
	}else{//多选模式
		if(classStr=="checkbox"){//选中
			obj.attr("class","checkboxSelected");	
		}else{//取消选中
			obj.attr("class","checkbox");
		}
	}
}

/**
 * 筛选数据，重新加载
 */
function screen(){
	////console.info("ajax开始请求数据sort:"+sort+"  order:"+order+"   page:"+page+"   lastPage:"+lastPage+"    searchParam:"+searchParam);
	////console.info(param);
	//直接点击的分页请求数据  这时候的page大于上次请求的数据。
	if(lastPage != 1 && lastPage == page){
		page = 1;
	}
	////console.info(searchParam=="undefined");
	if(searchParam=="undefined"){
		searchParam=null;
	}
	$.ajax({
		type: "post", 
		url : "listView/listGoods.html",
		dataType:'json',
		data: {'paramStr':$.toJSON(param),'sort':sort,'order':order,'page':page,'rows':rows,'searchParam':searchParam}, 
		success: function(d){
			var ul = $(".phone_main_03 ul");
			ul.html("");
			if(d!=null){
				////console.info(d);
				////console.info(d.aa);
				//把查询出来的数据总数量放入右上角
				$(".phone_main_01_top .phone_main_01_top_ret h5").text(d.countNum);
				
				lastPage = page;
				//初始化分页
				phone_list_page(Math.ceil(parseInt(d.countNum)/rows),8,page);
				////console.info("数据长度");
				////console.info("数据长度："+(d.goodsList.length));
				if(d.goodsList.length<=0){
					$(".phone_main_01_top .phone_main_01_top_ret h5").text("0");
					$(".phone_main_03 ul").append("没有相关产品");
					return true;
				}
				//accessproes
				//遍历查询出来的商品数据
				$.each(d.goodsList,function(i,g){
					//取当前li的个数，然后取摸，如果li个数不等于0取摸等于0那么就增加class
					var i = ul.children("li").length;
					var url = d.urlType+"/detail/"+g.goodsNum+".html";
					//遍历单品， 版本，制式，颜色 最低价格 //sort=s.price order asc  取最高价格
					
					var color = "";
					var edition =""; 
					var price = 0.00;
					var stock = 0;//库存
					
					//如果请求的是配件
					if(d.urlType=="accessory"){
						if(g.price){
							price = g.price;
						}
						if(g.stock){
							stock += g.stock;
						}
					}
					
					
					
					$.each(g.goodsSkus,function(i2,sku){
						//不等于undefined就是有数据
						if(i2==0){
							if(sku.price!=undefined){
								price = parseFloat(sku.price);
							}
							if(sku.color && sku.color.colorName){
								color += sku.color.colorName;
							}
							if(sku.edition!=undefined && sku.edition!=''){
								edition = sku.edition;
							}
						}else{
							if(sku.price){
								////console.info("遍历取最小价格："+parseFloat(sku.price)+"   "+price);
								if(price==0){//如果当前价格为0那么就把
									price = parseFloat(sku.price);
								}else{
									//遍历取最小的价格
									if(parseFloat(sku.price)<price && parseFloat(sku.price)>0){
										price = parseFloat(sku.price);
									}
								}
							}
							if(sku.color){
								if(sku.color.colorName){
									////console.info("color:"+sku.color.colorName+"|");
									//判断是否已经存储了这个颜色的字段
									if(color.indexOf(sku.color.colorName)<0){
										if(color!=""){
											color += "、"+sku.color.colorName;
										}else{
											color += sku.color.colorName;
										}
									}
								}
							}
							if(sku.edition!=undefined && sku.edition!=''){
								edition = "、"+sku.edition;
							}
						}
						if(sku.stock){
							stock += parseInt(sku.stock);
						}
						
						
					});
					
					if(stock<=5){
						stock = "仅剩"+stock+"台";
					}else{
						stock = "有货";
					}
					
					
					var defaultImg = "images/goodsHotImg.jpg";
					if(g.defaultImg){
						defaultImg = g.defaultImg; 
					}
					var brandName = "";
					if(g.brand && g.brand.name){
						brandName = g.brand.name;
					}
					
					var htmlStr = '<li title="'+brandName +' '+ g.name +' '+ edition+' （'+color+'）">';
					////console.info(i)
					if((i+1)%5 == 0){
						htmlStr = '<li id="phone_main_06" title="'+brandName +' '+ g.name +' '+ edition+' （'+color+'）">';
					}
					htmlStr +='<div><a target="_blank" href="'+url+'"><img src="'+defaultImg+'" width="228" height="228" /></a></div><div class="phone_main_04"><a target="_blank" href="'+url+'">'+ brandName +' '+ g.name +' '+ edition+' （'+color+'） </a><br></div><div class="phone_main_05"><span>库存：'+stock+'</span>￥'+price+'</div></li>';
					ul.append(htmlStr);
				}); 
			}else{
				$(".phone_main_01_top .phone_main_01_top_ret h5").text("0");
				$(".phone_main_03 ul").append("没有相关产品");
			}
		}
	});
}

/**
 * 分页方法插件
 * @param totalPages 总页数
 * @param visiblePages 默认显示页码数
 * @param currentPage 默认选中页码数
 */
function phone_list_page(totalPages,visiblePages,currentPage){
	////console.info("开始初始化分页:"+totalPages+"   "+visiblePages+"   "+currentPage);
	if(totalPages==0){
		totalPages=1;
	}
	$.jqPaginator('#phone_list_page_ul', {
	    totalPages: totalPages,
	    visiblePages: visiblePages,
	    currentPage: currentPage,
	    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
	    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	    onPageChange: function (num, type) {
	        ////console.info(type + '：' + num); 
	        if(page!=num){
		        page=num;
		        screen();
	        }
	    }
	});
	
	//初始化调整分页位置
	$(".fenye").each(function(i,fenye){
		var liNum = $(fenye).find("li").length; 
		//如果页数小于8那就重新定位页码的位置，使其居中
		if(liNum<9){
			var width = $(fenye).width(); 
			$(fenye).css('margin-left',(width-liNum*40)+"px");
		}
	});
}

/**
 * 取消选中的赛选条件
 * @param obj
 */
function cancelSlected(obj){
	obj.parent().hide().removeClass("selectedShow");
	obj.parent().siblings(".phone_main_01_txt_ret_02").show();
	obj.parent().siblings("ul").find("a").removeClass();
}




