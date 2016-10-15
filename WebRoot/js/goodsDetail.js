/**
 * 数组集合操作通用删除方法
 * @param val
 * @returns {Number}
 */
Array.prototype.indexOf = function(val) {              
    for (var i = 0; i < this.length; i++) {  
        if (this[i] == val) return i;  
    }  
    return -1;  
};  
Array.prototype.remove = function(val) {  
    var index = this.indexOf(val);  
    if (index > -1) {  
        this.splice(index, 1);  
    }  
};



var buyerNum = new Object();//采购的单品对象个数
var rootBuyerNum = new Object();//root服务的采购单品对象个数
var skuMap = new Object();//单品信息以map形式存储在变量里面

var page = 1;//根据价格区间获取单品列表页数
var totalPriceSectionArray = new Array();//所有可对比的商品集合
var priceSectionArray = new Object();//右边待对比的
var nowContrastArray = new Object();//当前对比的商品集合
var firstReadDefault = 1;//初始化的单品数据是否第一次加载进对比表格1是，2不是
var showChangePriceMes = false;//记录是否已经提示过

$(function(){
	
	//初始化清空input
	$("#xq_main_02_let_03_ret input").each(function(){
		$(this).val(0);
	});
	$("#go_choose_parts input").eq(0).val("");
	
	
	
	$("img").each(function(){
		if(!$(this).attr("src")){//如果当前图片没值那么就添加默认图片
			$(this).attr("src","images/goodsHotImg.jpg");
		}
	});
	
	filterImg();//过滤网络格式的图片
	
	$(".xq_main_02 input[type='text']").live("keydown",function(e){  //keyup事件处理 
		var val=$(this).val();
		$(this).data('oldVal',val);
    });
	
	$(".xq_main_02 input[type='text']").live("keyup",function(){  //keyup事件处理 
		var num = $(this).val().replace(/\D|^0/g,'');
		$(this).val(num||0);
    }).bind("paste",function(){  //CTR+V事件处理  
    	var num = $(this).val().replace(/\D|^0/g,'');
		$(this).val(num||0);
    });
	
	//console.info(goodsInfoObj);//商品所有信息
	
	//从商品所有信息里面取出所有单品信息放入map
	$(goodsInfoObj.goodsSkus).each(function(i,sku){
		sku["defaultSku"]= "1";//加载网页的时候加载过来的
		skuMap[sku.skuNum] = sku;
	});
	
	//减少购买的商品数量
	$(".xq_main_02_let_03_let_del").bind("click",function(){
		
		letGoodsNum($(this),1,2);
	});
	
	//添加购买的商品数量
	$(".xq_main_02_let_03_let_add").bind("click",function(){
		
		letGoodsNum($(this),1,1);
	});
	
	//直接输入商品数量
	$(".xq_main_02_let #xq_main_02_let_03_ret input").bind("keyup change",function(){
		//console.info("ok");
		letGoodsNum($(this),2,0);
	});
	
	//采购商品右侧增加商品数量操作
	$(".xq_main_02_let_03_ret_buyerAdd").live("click",function(){
		retGoodsNum($(this),1);		
	});
	
	//采购商品左侧减少商品数量操作
	$(".xq_main_02_let_03_ret_buyerDel").live("click",function(){
		retGoodsNum($(this),2);		
	});
	
	//直接输入商品数量
	$(".xq_main_02_ret  #xq_main_02_let_03_ret input").live("keyup change",function(){
		retGoodsNum($(this),0);
	});
	
	//增加root手机的数量
	$(".xq_main_02_ret .xq_main_02_let_03_ret_rootAdd").live("click",function(){
		rootBuyerNums($(this),1,0);
	});
	
	//减少root手机的数量
	$(".xq_main_02_ret .xq_main_02_let_03_ret_rootDel").live("click",function(){
		rootBuyerNums($(this),2,0);
	});
	
	//直接输入商品数量
	$(".xq_main_02_ret  #xq_main_02_let_04_ret input").live("keyup change",function(){
		rootBuyerNums($(this),0,1);
	});
	
	//添加收藏和取消收藏
	$(".xq_main_click_collect").live("click",function(){
		var type = $(this).attr("type");
		//var state = $(this).attr("state");
		var targetid = $(this).attr("targetid");
		add_drop_collect($(this),type,targetid);
	});
	

	
	
	var fristDeal = true;
	//给选中的li添加选中的样式
	$(".xq_main_03_top li").live("click",function(){
		$(this).addClass("selecter").siblings().removeClass("selecter");
		var i = $(this).index();
		//console.info("点击加载数据");
		$(".goods_main_contents").each(function(n,con){
			//console.info(n); 
			//console.info(n==i);
			if(i==n){
				$(con).show();
			}else{ 
				$(con).hide(); 
			}
		});
		
		
		if(i==1){//去请求包装和参数  数据
			packAndParam("bzli");//根据价格区间获取类似商品列表
		}
		
		if(i==2){//去请求评论数据，然后初始化分页插件
			//packAndParam("bzli");//根据价格区间获取类似商品列表
			evaluateAjax();
		}
		
		if(i==3 && fristDeal){//加载成交记录
			//console.info("点击了第四个选项开始加载成交记录");
			fristDeal = false;
			dealFn();
		}
		
	});
	
	//把右边待对比的添加到左边作对比
	$(".bz_mian_02_ret .bz_mian_02_ret_02 li span").live("click",function(){
		//console.info(nowContrastArray);
		if(objectSize(nowContrastArray)>=4){
			alert("请先从左边删除一个要对比的商品");
			return false;
		}
		var skuId = $(this).attr("skuId");//单品id
		var sku = priceSectionArray[skuId];//从待对比里面取出单品
		addSkuContrastParam(sku);
		$(this).closest("li").remove();
	});
	
	//参数对比的展开和收起
	$(".bz_mian_02_let_btxt img").live("click",function(){
		var state = $(this).attr("state");
		if(state==1){//展开状态
			$(this).parent().next().hide();
			$(this).attr("src","images/zhankai.jpg");
			$(this).attr("state","2");
		}else{//关闭状态
			$(this).parent().next().show();
			$(this).attr("src","images/shouqi.jpg");
			$(this).attr("state","1");
		}
	});
	
	//隐藏显示相同的参数
	$(".bz_mian_01 input[type='checkbox']").click(function(){
		//console.info($(this).attr("checked"));
		if($(this).attr("checked")){
			//console.info('隐藏相同属性');
			var divList = $(".bz_mian_02_let").children("div:even");//所有需要遍历的div块
			//console.info(divList.length);
			divList.each(function(i,d){
				if(i>0){
					var rowDiv = $(d).find(".bz_mian_02_let_tupi_04");//每个div块里面需要遍历的列
					var fristLine = $(rowDiv).eq(0).children("div");
					//console.info(fristLine);
					//console.info(fristLine.length);
					//fristLine.each(function(l,ld){
					for(var l=0 ; l<fristLine.length; l++){
						var fullyEqual = true;//是否都相等
						//var backContent = $.trim($(ld).eq(l).text());
						var backContent = $.trim($(fristLine).eq(l).text());//第一列第L行的内容
						rowDiv.each(function(r,rd){
							var lineDiv = $(rd).children("div").eq(l);//每个div块里面需要遍历的行
							var text = $.trim(lineDiv.text());//第N列第L行的内容
							if(backContent!=text){//当前值不等于第一列的值
								//判断当前值是否为“” 如果是，那就查找跟，这一列是否有数据，  //如果这一列有数据，那么就设为false。没数据什么也不做
								var haveData = $(divList).eq(0).children(".bz_mian_02_let_tupi").eq(r).attr("havaData");
								if(haveData!=1){
									fullyEqual = false;
								}
							}
							//console.info("对比的值："+backContent+"    "+text);
						});
						if(fullyEqual){//隐藏这一行相同的属性
							rowDiv.each(function(r,rd){
								$(rd).children("div").eq(l).hide();//每个div块里面需要遍历的行
								$(rd).siblings().eq(0).children("div").eq(l).hide();
							});
						}
					//});
					};
				}
			});
		}else{
			//console.info('显示所有属性');
			var divList = $(".bz_mian_02_let").children("div:even");//所有需要遍历的div块
			divList.each(function(i,d){
				if(i>0){
					var rowDiv = $(d).find("div").show();//每个div块里面需要遍历的列
				}
			});
		}
	});
	
	
	initializeEvaluateNum();//初始化评论总数量
	
	
});





/**
 * 添加或者删除(取消)收藏
 */
var add_drop_collect = function add_drop_collect(obj,type,targetId){
	//console.info("targetId："+targetId);
	$.ajax({
		type: "post", 
		url : "/collect/collect.html",
		dataType:'json',
		data: {'type':type,'targetId':targetId}, 
		success: function(d){
			//console.info(data);
			//var d = $.parseJSON(data);
			//console.info(d);
			if(d.msg=="取消收藏"){
				if(type=="goods"){//操作商品收藏图片
					obj.attr("src","../images/SHOUC.jpg");
				}else{//操作单品收藏图片
					obj.attr("src","../images/hart.jpg");
				}
			}
			if(d.msg=="收藏成功"){
				if(type=="goods"){//操作商品收藏图片
					obj.attr("src","../images/SHOUC_01.jpg");
				}else{//操作单品收藏图片
					obj.attr("src","../images/hart_01.jpg");
				}
			}
		}
	});
};

/** 
 * 采购中root手机服务的数量计算
 * @param obj 当前操作对象
 * @param addOrDel 1增加，2减少
 * @param isClick 1键盘直接输入
 * @returns {Boolean}
 */
function rootBuyerNums(obj,addOrDel,isClick){
	var rootBuyerInput =obj.parent().find("input");
	var num = parseInt(rootBuyerInput.val());//当前的数量
	var skuNum = obj.closest("li").attr("class");//商品编号
	var buyerNums = parseInt(buyerNum[skuNum]["num"]);//当前非root的手机数量
	
	//console.info("当前输入的数量："+num+"  "+buyerNums);
	if(addOrDel!=2){
		if(num>=buyerNums){
			if(isClick!=1){return false}
			rootBuyerInput.val(buyerNums);
			//如果是直接输入的，那么要判断已经存储的和现在非root的数量是否相同
			//return false;
		}
	}
	//console.info("当前输入的数量："+rootBuyerInput.val());
	//数量+or-
	if(addOrDel==1){
		//数量不可以大于非root的数量。
		rootBuyerInput.val(num+1);
	}
	if(addOrDel==2){
		if(num<=0){
			return false;
		}
		rootBuyerInput.val(num-1);
	}
	//如果数量大于0
	if(rootBuyerInput.val()>0){
		var sku = skuMap[skuNum];
		sku["rootNum"] = rootBuyerInput.val();
		rootBuyerNum[sku.skuNum] = sku;
	}else{//移除
		rootBuyerNum[skuNum]["rootNum"] = 0;
		delete rootBuyerNum[skuNum];
	}

	//console.info("当前存储的root的数量："+rootBuyerNum[skuNum]["rootNum"]);
	countBuyerNum();//计算总数量和价格
}

/**
 * 右侧操作采购数量通用方法
 * @param obj 当前对象
 * @param addOrDel 1增加 2减少
 */
function retGoodsNum(obj,addOrDel){
	
	
	var buyerInput =obj.parent().find("input");
	var num = parseInt(buyerInput.val());//当前的数量
	
	if(!compareStock(buyerInput)){//检测库存不足就返回不进行下面操作//此处检测的应该是直接输入的。适用于
		return ;
	}
	
	if(addOrDel==1){//增加数量
		buyerInput.val(num+1);
		if(!compareStock(buyerInput)){//检测库存不足就返回不进行下面操作
			return ;
		}
	}else if(addOrDel==2){//减少数量
		buyerInput.val(num-1);
	}
	var skuNum = obj.closest("li").attr("class");//商品编号
	//对应左边的商品采购数量同步
	$(".xq_main_02_let ."+skuNum).val(buyerInput.val());
	//采购数量小于=0那么就删除
	if(buyerInput.val()<=0){
		//删除右边的商品html记录
		obj.closest("li").remove();
		//console.info(rootBuyerNum[skuNum]);
		if(rootBuyerNum[skuNum]){
			rootBuyerNum[skuNum]["rootNum"] = 0;
		}
		//把此商品从采购记录里面删除掉//把和此商品有关的root采购记录删除掉
		buyerNum[skuNum]["num"] = 0;
		//console.info(buyerNum[skuNum]);
		//if(rootBuyerNum[skuNum]["rootNum"] != undefined){
		
		//}
		delete buyerNum[skuNum];
		delete rootBuyerNum[skuNum]; 
	}else{
		//采购的商品在对象里面的数量
		var sku = skuMap[skuNum];
		sku["num"] = buyerInput.val();
		buyerNum[sku.skuNum] = sku;
		
		//判断root数量不能超过非root的数量//如果一开始root的数量等于非root的数量那么就在改变的时候保持一致
		//如果改变了root的数量那么就改变root的对象里面的数量
		//delete rootBuyerNum[skuNum];
		if(sku["rootNum"]>buyerInput.val()){
			rootBuyerNum[skuNum]["rootNum"] = buyerInput.val();
			obj.closest("li").find("input").eq(1).val(buyerInput.val());
		}
		
		//console.info("存储是否已经提示过的变量："+sku["changePriceWarn"]);
		//如果此单品没有价格改变这个变量就添加这个变量
		//if(!sku["changePriceWarn"]){
		//	sku["changePriceWarn"] = 1;//此单品还未提醒过
		//}
	}
	countBuyerNum();//计算总数量和价格
}

/**
 * 计算商品购买总个数和价格
 */
function countBuyerNum(){
	//console.info("计算购买总数量和价格");
	var totalPrice = 0.00;//总价格
	var buyerTotalNum = 0;//非root总数量
	var buyerTotalPri = 0.00;//非root总价格
	var totalChangePrice = 0;//浮动的总价格
	//var skuData = "";
	var n = 0;//统计采购的个数
	var param = new Object();//统计跳转到专用配件页面需要的参数
	var skuList = new Array();//单品集合
	
	//单品id__颜色_版型_采购数量_需要root的数量
	for (i in buyerNum){
		n++;
		b = buyerNum[i];
	    buyerTotalNum += parseInt(b.num);
	    buyerTotalPri+=Number(b.price)*parseInt(b.num);
	    //console.info("rootNum:"+b.rootNum);
	    var skuObj = new Object();
	    skuObj["id"] = b.id;//单品id
	    if(b.color&&b.color.colorName){
	    	skuObj["color"] = b.color.colorName;//颜色
	    }
	    skuObj["edition"] = b.standard;//单品制式
	    skuObj["quantity"] = b.num;//订购数量
	    if(b.rootNum == undefined){
	    	skuObj["rootQuantity"] = 0;//root数量
	    }else{
	    	skuObj["rootQuantity"] = b.rootNum;//root数量
	    }
	    skuObj["price"] = b.price;//价格
	    skuObj["originalPrice"] = b.originalPrice;//原价格
	   //skuObj[""]//如果不正常这里添加上XXX
	    skuList.push(skuObj);
	    
	    //放在要购买的商品里面遍历
	    //console.info("计算浮动价格之前，查看取到的对象：");
	    //console.info(b);
	    
	    //判断采购数量，附加浮动价格
		var thenBuyNum = parseInt(b.thenBuyNum)||0;//今日已购买的个数
		var changeNum = parseInt(b.changeNum);//浮动的限制数量
		var changePrice = Number(b.changePrice);//浮动的价格
		
		
		if(parseInt(b.num)>changeNum && parseInt(b.changeNum)!=0){//如果现在购买的数量大于限制的数量，那么就加浮动价格
			//把当前页面价格改为基本价格+浮动价格显示
			//if(){//如果为0那么就代表不限制
				//判断是否已经提示过价格浮动提醒
				/*if(b.changePriceWarn==1){//没提醒过
					alert("温馨提醒：因本站手机出货价普遍较低，单一型号每天仅能采购10台，超过10台，价格将会有所变化，建议多次采购，因此带来的不便，敬请谅解！");
					b.changePriceWarn = 2;  
				}
				//console.info(b.skuNum+"   "+Number(b.price)+changePrice);
				//console.info($("#goods_change_price_span_"+b.skuNum));
				
				//$("input."+b.skuNum).clostest("tr").find("#goods_change_price_span").html(Number(b.price)+changePrice);
				$("#goods_change_price_span_"+b.skuNum).css("text-decoration","line-through").css("color","#999");
				$("#goods_changed_price_span_"+b.skuNum).html(Number(b.price)+changePrice).show();
				$("#goods_price_xieXian_"+b.skuNum).show();
				
				//判断当前数量是否大于限制数量，如果大于限制数量，并且没有提示过，那么就提示用户，然后修改价格
				//console.info("  增加的浮动价格："+parseInt(b.num)*changePrice);
				//计算浮动价格总和//浮动价格乘以当前要购买的钱数//添加到总钱数上面
				totalChangePrice += parseInt(b.num)*changePrice;*/
			//}
				
		}else{
			
			//把页面显示的价格改回来
			/*if(b.changePriceWarn==2){
				$("#goods_change_price_span_"+b.skuNum).css("text-decoration","none").css("color","#f00");
				$("#goods_changed_price_span_"+b.skuNum).html(Number(b.price)+changePrice).hide();
				$("#goods_price_xieXian_"+b.skuNum).hide();
				//把是否第一次提醒改为否
				b.changePriceWarn=1;
			} */
			//totalChangePrice=0;
		}
	    
	    
	}
	var rootBuyerTotalNum = 0;//root服务总数量
	var rootBuyerTotalPri = 0.00;//root服务总价格
	for (i in rootBuyerNum){
		b = buyerNum[i];
		rootBuyerTotalNum += parseInt(b.rootNum);
		rootBuyerTotalPri+=mobilePhoneRootPrice*parseInt(b.rootNum);
	}
	
	//如果采购总数量少于3台，付款总金额邮费
	//if(buyerTotalNum<3){
	//	totalPrice = buyerTotalPri+rootBuyerTotalPri+postagePrice+totalChangePrice;
	//}else{
		totalPrice = buyerTotalPri+rootBuyerTotalPri+totalChangePrice;
	//}
	
	
	//把统计结果写入左边html采购记录区
	var root_buyer = $(".xq_main_02_ret .xq_main_02_ret_01");
	var noRoot = root_buyer.find("p").eq(0);
	noRoot.find("span").eq(0).text(buyerTotalNum);//非root手机的数量
	noRoot.find("span").eq(1).text((buyerTotalPri+totalChangePrice).toFixed(2));//非root手机的价格
	
	var noRoot = root_buyer.find("p").eq(1);
	noRoot.find("span").eq(0).text(rootBuyerTotalNum);//root手机的数量
	noRoot.find("span").eq(1).text(rootBuyerTotalPri.toFixed(2));//root手机的价格
	
	//把商品编号，非root数量，root服务的数量写入cookie
	var root_buyer2 = $(".xq_main_02_ret .xq_main_02_ret_02");
	root_buyer2.find("span").eq(0).text(buyerTotalNum);//总数量
	root_buyer2.find("span").eq(1).text(totalPrice.toFixed(2));//总价格
	
	param["goodsId"]=goodsInfoObj.id;
	param["goodsName"]=goodsInfoObj.name;
	param["skuList"]=skuList;
	//console.info("是否有数据："+skuList.length);
	if(skuList.length>0){
		$("#go_choose_parts input").eq(0).val($.toJSON(param));
	}else{
		$("#go_choose_parts input").eq(0).val('');
	}
	//把统计出来的数据放入隐藏的文本框go_choose_parts
	//console.info($("#go_choose_parts input").eq(0).val());
	
}

/**
 * 对比库存数量
 * @param numInput
 * @return false 库存不足  true库存充足
 */
function compareStock(numInput){
	var stock = parseInt($(numInput).data("stock"));//库存
	var num = parseInt($(numInput).val());//当前要购买的数量
	if(num>stock){
		$(numInput).val(stock);
		alert("客官，不好意思，库存不足啦！请调整采购数量或者点击到货提醒，先去看看其他商品吧！");
		return false;
	}else{
		return true;
	}
	
}

//左边增加商品数量obj(当前对象) type类型（1鼠标点击，2手动输入）addPrDel(1增加，2删除)
function letGoodsNum(obj,type,addOrDel){
	var numInput ;
	if(type==1){
		numInput = obj.siblings("#xq_main_02_let_03_ret").find("input");
		var num = parseInt(numInput.val());
		
		
		if(addOrDel==1){//增加采购数量
			numInput.val(num+1);//采购数量+1
			if(!compareStock(numInput)){//检测库存不足就返回不进行下面操作
				return ;
			}
		}else{//减少采购数量
			if(numInput.val()==0){
				return false;
			}
			numInput.val(num-1);
		}
	}else{
		numInput = obj;
		if(!compareStock(numInput)){//检测库存不足就返回不进行下面操作
			return ;
		}
	}
	//右边购买记录区域块
	var rightLet = $(".xq_main_02_ret .xq_main_02_let_03 ul");
	if(numInput.val()>0){//如果此时左边采购数量大于0//无论增减只要数量大于0就执行下面的程序
		var li = rightLet.children("."+numInput.attr("class"));
		if(li.attr("class")==undefined){//如果右边还没有此记录那么就向右边添加此商品的采购记录
			var tr ;
			if(type==1){
				tr = obj.parent().parent().siblings();
			}else{
				tr = obj.parent().parent().parent().siblings();
			}
			var liStr = '<li class="'+numInput.attr("class")+'" ><div class="xq_main_02_let_02"><table border="0" cellspacing="0" cellpadding="0"><tbody><tr><td width="141"  style="padding-left: 38px;" >'+tr.eq(1).text()+'/'+tr.eq(2).text()+'/'+tr.eq(3).text()+'</td><td width="87"><div id="xq_main_02_let_03"><div id="xq_main_02_let_03_let" class="xq_main_02_let_03_ret_buyerDel"><img src="../images/jian.jpg" width="20" height="20"></a></div><div id="xq_main_02_let_03_ret"><input name="" data-stock="'+$(numInput).data("stock")+'" type="text" value="'+numInput.val()+'" onmouseover="this.focus()" onmouseout="if(this.value=="")this.value="0";" onfocus="this.select()" onclick="if(this.value=="0")this.value="""></div><div id="xq_main_02_let_03_let" class="xq_main_02_let_03_ret_buyerAdd"><img src="../images/jia.jpg" width="20" height="20"></div> </div></td><td width="91" style="display:none;"><div id="xq_main_02_let_03"><div id="xq_main_02_let_03_let" class="xq_main_02_let_03_ret_rootDel"><img src="../images/jian.jpg" width="20" height="20"></a></div><div id="xq_main_02_let_04_ret"><input name="" type="text" value="0" onmouseover="this.focus()" onmouseout="if(this.value=="")this.value="0";" onfocus="this.select()" onclick="if(this.value=="0")this.value="""></div><div id="xq_main_02_let_03_let" class="xq_main_02_let_03_ret_rootAdd"><img src="../images/jia.jpg" width="20" height="20"></div></div></td></tr></tbody></table></div></li>';
			rightLet.append(liStr);
		}else{
			li.find("input").eq(0).val(numInput.val());
		}
		
		//采购的商品都放入一个对象里面
		var sku = skuMap[numInput.attr("class")];
		sku["num"] = numInput.val();
		buyerNum[sku.skuNum] = sku;
		
		//console.info("存储是否已经提示过的变量："+sku["changePriceWarn"]);
		//如果此单品没有价格改变这个变量就添加这个变量
		if(!sku["changePriceWarn"]){
			sku["changePriceWarn"] = 1;//此单品还未提醒过
		}
		
	}else{
		var numInputCalss = numInput.attr("class");
		//删除右边的商品html记录
		rightLet.children("."+numInputCalss).remove();
		 
		//把此商品从采购记录里面删除掉//把和此商品有关的root采购记录删除掉
		buyerNum[numInputCalss]["num"] = 0;
		if(rootBuyerNum[numInputCalss] && rootBuyerNum[numInputCalss]["rootNum"]){
			rootBuyerNum[numInputCalss]["rootNum"] = 0;
		}
		delete buyerNum[numInputCalss];
		delete rootBuyerNum[numInputCalss];
	}
	countBuyerNum();//计算总数量和价格
}

/**
 * 点击包装和参数请求某价格区间的类似单品列表
 */
//var countPage = 1;
function packAndParam(clickObj){
	//console.info("触发ajax请求：");
	
	if(firstReadDefault!=1){//不是第一次加载对比数据
		if(clickObj!="more"){//判断是不是点击加载更多触发请求
			return false;
		}
	}
	if(minPrice==maxPrice){
		minPrice = minPrice-100;
		maxPrice = maxPrice+100;
	}

	$.ajax({
		type: "post", 
		url : "/goods/contrast/index.html",
		dataType:'json',
		data: {'price':minPrice,'maxPrice':maxPrice,'page':page}, 
		success: function(data){
			//d = d.trim();	
			//console.info(d);	
			//var data = $.parseJSON(d);	
			//console.info(data);
			//console.info("当前请求的页数"+page);
			page++;//下次请求的页数
			//console.info("下次请求的页数"+page);
			var countPage = (data[0].countPage-1)/data[0].rows+1;
			if(page>countPage){
				page=1;
			}
			//console.info("计算总页数："+countPage +"   当前页数："+page);
			//清空待对比的集合
			for(var retSkuKey in priceSectionArray){
				delete priceSectionArray[retSkuKey];
		    }
			
			var index = 0;
			if(firstReadDefault==1){//第一次点击加载对比数据
				$(goodsInfoObj.goodsSkus).each(function(i,sku){
					//console.info("遍历单品："+sku.id);
					sku["defaultSku"]= "1";//加载网页的时候加载过来的
					if(index<=3){
						addSkuContrastParam(sku);
					}else{//如果初始化的单品数量大于4个，多余的放到右边待对比的对象里面存储
						priceSectionArray[sku.id]=sku;
					}
					index++;
				});
				//判断原有的单品集合是否够4个，不够四个九从新集合里面添加数据，如果超过四个，那么就把剩余的添加到totalPriceSectionArray里面
				$(data).each(function(i,sku2){
					//console.info("遍历ajax加载进来的数据"+sku2.id);
					var id = sku2.id;
					if(index<=3){
						if(nowContrastArray[id]==undefined){
							addSkuContrastParam(sku2);
							index++;
						}
					}else{//如果初始化的单品数量大于4个，多余的放到右边待对比的对象里面存储
						if(nowContrastArray[id]==undefined){//当前对比的单品map里面没有的就存入待对比的里面
							priceSectionArray[id]=sku2;//如果待对比的集合里面有和此时的单品对象重名的就自动覆盖掉了。
						}
					}
				});
				firstReadDefault = 2;
			}else{
				//判断原有的单品集合是否够4个，不够四个九从新集合里面添加数据，如果超过四个，那么就把剩余的添加到totalPriceSectionArray里面
				$(data).each(function(i,sku2){
					if(nowContrastArray[sku2.id]==undefined){//当前对比的单品map里面没有的就存入待对比的里面
						priceSectionArray[sku2.id]=sku2;//如果待对比的集合里面有和此时的单品对象重名的就自动覆盖掉了。
					}
				});
			}
			
			/*//判断原有的单品集合是否够4个，不够四个九从新集合里面添加数据，如果超过四个，那么就把剩余的添加到totalPriceSectionArray里面
			$(data).each(function(i,sku2){
				//console.info("遍历ajax加载进来的数据"+sku2.id);
				var id = sku2.id;
				if(index<=3){
					if(nowContrastArray[id]==undefined){
						addSkuContrastParam(sku2);
						index++;
					}
				}else{//如果初始化的单品数量大于4个，多余的放到右边待对比的对象里面存储
					//过滤正在对比的和待对比的不要有重复数据
					//console.info("过滤数据："+(nowContrastArray[id]==undefined));
					//console.info("过滤数据："+priceSectionArray[id]);
					if(nowContrastArray[id]==undefined){//当前对比的单品map里面没有的就存入待对比的里面
						//console.info("正在对比的里面没有，添入待对比里面");
						priceSectionArray[id]=sku2;//如果待对比的集合里面有和此时的单品对象重名的就自动覆盖掉了。
					}
				}
			});*/
			
			
			var ul = $(".bz_mian_02_ret .bz_mian_02_ret_02 ul");
			ul.html("");
			for(var retSkuKey in priceSectionArray){
				var goods;
				var retSku = priceSectionArray[retSkuKey];
				//如果是默认的单品取已经存储的商品基础信息，如果不是默认的就从单品里面取商品基础信息，赋值给goods。方便下面取属性值
				if(retSku.defaultSku==1){
					goods = goodsInfoObj;
				}else{
					goods = retSku.goods;
				}
				//console.info(retSku);
				//把待对比的单品，遍历显示在右边的待对比框中
				var retSkuStr = '<li><a href="javascript:void(0);">'+goods.name+'（'+retSku.edition+'/'+retSku.standard+'）</a><br><h5>￥'+retSku.price+'</h5><span skuId='+retSku.id+'>加入对比</span></li>';
				ul.append(retSkuStr);
			};
		} ,
		error:function(XMLResponse){
			//console.info("请求数据出错："+arguments[1]);
		}
		
	}); 
}

/**
 * 添加对比的商品到对比表格
 * @param skuObj
 */
function addSkuContrastParam(skuObj){
	
	var nowContrastArraySize = objectSize(nowContrastArray);
	
	//判断当前在对比的单品数量
	if(nowContrastArraySize >= 4){
		return false;
	}
	var goods;
	//如果是默认的单品取已经存储的商品基础信息，如果不是默认的就从单品里面取商品基础信息，赋值给goods。方便下面取属性值
	if(skuObj.defaultSku==1){
		goods = goodsInfoObj;
	}else{
		goods = skuObj.goods;
	}
	
	//如果单品数量大于4，返回false不做操作//否则把当前传入的单品参数赋值到下一列需要对比的表格
	var eqNum = nowContrastArraySize;
	
	bz_SkuContrast(goods,skuObj,eqNum);//向对比框赋值单品信息
		
	var skuId = skuObj.id;
	//把当前单品对象存入正在对比的对象记录里面//如果当前已经存储将覆盖掉
	if(nowContrastArray[skuId]==undefined){
		nowContrastArray[skuId]=skuObj;
	}
	
	
	//从待对比商品集合里面删除此单品记录
	for(var s in priceSectionArray){
		if(s.id == skuId){
			delete priceSectionArray[skuId];
		}
     }
}

/**
 * 清空商品对比框里面的值
 */
function bz_clearSkuContrast(){
	//console.info("开始执行清空");
	
	
	var divList = $(".bz_mian_02_let").children("div:even");//所有需要遍历的div块
	//console.info(divList.length);
	divList.each(function(i,d){
		if(i>0){
			var rowDiv = $(d).find(".bz_mian_02_let_tupi_04");//每个div块里面需要遍历的列
			var fristLine = $(rowDiv).eq(0).children("div");
			for(var l=0 ; l<fristLine.length; l++){
				rowDiv.each(function(r,rd){
					$(rd).children("div").eq(l).html("");
				});
			};
		}else{
			var skuNoImgHtmlStr = '<div class="bz_mian_02_let_07">添加产品</div>';//单品没有图片html标签代码
			var bz_mian_02_let_tupi = $(d).children(".bz_mian_02_let_tupi");
			bz_mian_02_let_tupi.each(function(i2,tupi){
				$(tupi).attr("havaData","1");
				$(tupi).children("div").eq(0).children("div").eq(0).html(skuNoImgHtmlStr);
				$(tupi).children("div").eq(0).children("div").eq(1).html("");
				$(tupi).children("div").eq(0).children("div").eq(2).find("a").attr("href","javascript:void(0)");
				
				$(tupi).children("div").eq(1).find("h4").html("￥0.00");//批发报价
				$(tupi).children("div").eq(1).find("span").html("[市场价]￥0.00");//市场价
				$(tupi).children("div").eq(2).html("");//点评 
			});
		}
	});
}

/**
 * 单品对比赋值
 * @param goods 商品信息
 * @param skuObj 单品信息
 * @param eqNum 选择赋值位置
 */
function bz_SkuContrast(goods,skuObj,eqNum){
	
	var skuNoImgHtmlStr = '<div class="bz_mian_02_let_07">添加产品</div>';//单品没有图片html标签代码
	var skuImgHtmlStr = '<a target="_blank" href="/goods/detail/'+goods.goodsNum+'.html"><img src="'+goods.defaultImg+'" width="117" height="107"></a>';//单品图片html标签代码
	
	var bz_mian_02_let_div = $(".bz_mian_02 .bz_mian_02_let").children("div");
	
	var bz_mian_02_let_tupi = bz_mian_02_let_div.eq(0).children(".bz_mian_02_let_tupi").eq(eqNum);
	
	bz_mian_02_let_tupi.attr("havaData","2");
	
	if(goods.id==undefined||goods.id==""){//没有商品信息
		bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(0).html(skuNoImgHtmlStr);//型号
	}else{//有商品信息
		bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(0).html(skuImgHtmlStr);//型号
	}
	 
	bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(1).html(goods.name);//手机名称
	var letMvMethod = "javascript:nowContrastLetMv('"+skuObj.id+"');";//左移动的方法
	if(eqNum==0){
		letMvMethod = "javascript:void(0);";//左移动的方法
	}
	var retMvMethod = "javascript:nowContrastRetMv('"+skuObj.id+"');";//右移动的方法
	if(eqNum==3){
		retMvMethod = "javascript:void(0);";//左移动的方法
	}
	
	bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(2).find("a").eq(0).attr("href",letMvMethod);//左移动//如果此单品在最左边不可左移动
	bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(2).find("a").eq(1).attr("href","javascript:nowContrastDel('"+skuObj.id+"');");//删除
	bz_mian_02_let_tupi.children("div").eq(0).children("div").eq(2).find("a").eq(2).attr("href",retMvMethod);//右移动//如果此单品在最右边不可右移动

	bz_mian_02_let_tupi.children("div").eq(1).find("h4").html("￥"+skuObj.price);//批发报价
	bz_mian_02_let_tupi.children("div").eq(1).find("span").html("[市场价]￥"+skuObj.originalPrice);//市场价
	bz_mian_02_let_tupi.children("div").eq(2).html("点评，待添加");//点评 
	
	//基本参数
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(2).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	if(goods.exposureDate!=undefined ){
		bz_mian_02_let_tupi.children("div").eq(0).html(goods.exposureDate.substr(0,10));//曝光日期
	}
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.cellphoneType);//手机类型
	
	//屏幕
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(4).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.touchscreenType);//触摸屏类型
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.screenSize);//主屏尺寸
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.resolution);//主屏幕分辨率
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.sreenPixDensity);//屏幕像素密度
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.screenTechnology);//屏幕技术
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.narrowFrame);//窄边框
	bz_mian_02_let_tupi.children("div").eq(6).html(goods.screenRatio);//屏幕占比
	
	//网络
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(6).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(skuObj.netSuitType);//网络类型
	bz_mian_02_let_tupi.children("div").eq(1).html(skuObj.networkThree);//3G网络
	bz_mian_02_let_tupi.children("div").eq(2).html(skuObj.supportChannel);//支持频段
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.theoryRate);//理论速率
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.wlanFunction);//WLAN功能
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.navigation);//导航
	bz_mian_02_let_tupi.children("div").eq(6).html(goods.connectionShare);//连接与共享
	bz_mian_02_let_tupi.children("div").eq(7).html(skuObj.networkFour);//4G网络
	
	//硬件
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(8).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.operationSystem);//操作系统
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.cpuNumber);//核心数
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.cpuModel);//CPU型号
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.cpuRate);//CPU频率
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.gpuModel);//GPU型号
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.ram);//RAM容量
	bz_mian_02_let_tupi.children("div").eq(6).html(skuObj.storage);//ROM容量
	bz_mian_02_let_tupi.children("div").eq(7).html(goods.memoryCard);//存储卡
	bz_mian_02_let_tupi.children("div").eq(8).html(goods.batteryType);//电池类型
	bz_mian_02_let_tupi.children("div").eq(9).html(goods.batteryCapacity);//电池容量
	bz_mian_02_let_tupi.children("div").eq(10).html(goods.talkTime);//理论通话时间
	bz_mian_02_let_tupi.children("div").eq(11).html(goods.theoryStandbyTime);//理论待机时间
	bz_mian_02_let_tupi.children("div").eq(12).html(goods.userInterface);//用户界面
	bz_mian_02_let_tupi.children("div").eq(13).html(goods.extendedCapacity);//扩展容量
	
	//摄像头
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(10).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.camera);//摄像头
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.cameraType);//摄像头类型
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.postCamera);//后置摄像头
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.frontCamera);//前置摄像头
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.cameraCertification);//摄像头认证
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.sensorType);//传感器类型
	bz_mian_02_let_tupi.children("div").eq(6).html(goods.flashLamp);//闪光灯
	bz_mian_02_let_tupi.children("div").eq(7).html(goods.videoShoot);//视频拍摄
	bz_mian_02_let_tupi.children("div").eq(8).html(goods.shootFunction);//拍照功能
	bz_mian_02_let_tupi.children("div").eq(9).html(goods.aperture);//光圈
	bz_mian_02_let_tupi.children("div").eq(10).html(goods.focalLength);//焦距/范围
	bz_mian_02_let_tupi.children("div").eq(11).html(goods.cameraFeatures);//摄像头特色
	bz_mian_02_let_tupi.children("div").eq(12).html(goods.cameraOtherparams);//其他摄像头参数
	
	//外观
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(12).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.modelDesign);//造型设计
	if(skuObj.color && skuObj.color.colorName){
		bz_mian_02_let_tupi.children("div").eq(1).html(skuObj.color.colorName);//机身颜色
	}
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.cellphoneSize);//手机尺寸 
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.weight);//手机重量
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.bodyFeatures);//机身特点
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.operationType);//操作类型
	bz_mian_02_let_tupi.children("div").eq(6).html(goods.outSensorType);//感应器类型
	bz_mian_02_let_tupi.children("div").eq(7).html(goods.simType);//SIM卡类型
	bz_mian_02_let_tupi.children("div").eq(8).html(goods.bodyInterface);//机身接口
	bz_mian_02_let_tupi.children("div").eq(9).html(goods.bodyMaterial);//机身材质
	
	//服务与支持
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(14).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.audioSupport);//音频支持
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.videoSupport);//视频支持
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.imgSupport);//图片支持
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.commonFunctions);//常用功能
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.businessFunctions);//商务功能
	
	//手机附件
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(16).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.optionalAccessory);//包装清单
	
	//保修信息
	bz_mian_02_let_tupi = bz_mian_02_let_div.eq(18).children(".bz_mian_02_let_tupi_04").eq(eqNum);
	bz_mian_02_let_tupi.children("div").eq(0).html(goods.warrantyPolicy);//保修政策
	bz_mian_02_let_tupi.children("div").eq(1).html(goods.warrantyTime);//质保时间
	bz_mian_02_let_tupi.children("div").eq(2).html(goods.warrantyRemark);//质保备注
	bz_mian_02_let_tupi.children("div").eq(3).html(goods.servicePhone);//客服电话
	bz_mian_02_let_tupi.children("div").eq(4).html(goods.phoneRemark);//电话备注
	bz_mian_02_let_tupi.children("div").eq(5).html(goods.detailContents);//详细内容
}


/**
 * 选购专用配件
 */
function chooseDedicatedParts(){
	
	var param =$("#go_choose_parts input").eq(0).val();
	//var skuData = $("#go_choose_parts input").eq(1).val();
	//console.info(param);
	if(param==""){
		alert("请先选择购买的商品！");
		return;
	} 
	
	//清空集合和input
	
	$("#go_choose_parts").submit();
}

/**
 * 删除一个当前对比的单品
 */
function nowContrastDel(skuId){
	//console.info("执行删除");
	//console.info(nowContrastArray);
	delete nowContrastArray[skuId];
	//console.info(nowContrastArray);
	nowContrastAnew();//清空对比框正在对比的单品信息，重新遍历正在对比的存储对象进行赋值
}

/**
 * 向左移动要对比的单品
 */
function nowContrastLetMv(skuId){
	//console.info("开始左移动");
	//根据单品id查找存放的位置
	var mvBeforeSkuIndex = objectIndexOf(nowContrastArray,skuId);
	if(mvBeforeSkuIndex==0){//已经在最左边不可移动
		return false;
	}
	//console.info("开始重新排序");
	var temp = new Object();
	var i = 0;
	$.each(nowContrastArray,function(key,skuObj){
		if(i==(mvBeforeSkuIndex-1)){
			temp[skuId] = getObjByIndex(nowContrastArray,mvBeforeSkuIndex);
		}
		if(i!=mvBeforeSkuIndex){
			temp[key] = skuObj;
		}
		i++;
	});
	$.each(nowContrastArray,function(key,skuObj){
		delete nowContrastArray[key];
	});
	//console.info("查看清空后的正在对比对象里面存储的东西");
	//console.info(nowContrastArray);
	nowContrastArray = temp;
	//console.info(nowContrastArray);
	temp = null;
	//console.info("清空临时对象后");
	//console.info(nowContrastArray);
	
	//移动存储的位置
	//清空对比文本框里面所有的单品值
	//遍历赋值
	nowContrastAnew();//清空对比框正在对比的单品信息，重新遍历正在对比的存储对象进行赋值
}

/**
 * 向右移动要对比的单品
 */
function nowContrastRetMv(skuId){
	//console.info("开始右移动");
	//根据单品id查找存放的位置
	var mvBeforeSkuIndex = objectIndexOf(nowContrastArray,skuId);
	if(mvBeforeSkuIndex==objectSize(nowContrastArray)){//已经在最右边不可移动
		return false;
	}
	var temp = new Object();
	var i = 0;
	$.each(nowContrastArray,function(key,skuObj){
		//如果i等于当前要向右移动的，先把右边的那个取出来放在当前位置，然后把当前要移动的放到后面。i+1的不妨
		if(i==mvBeforeSkuIndex){
			var o = getObjByIndex(nowContrastArray,mvBeforeSkuIndex+1);
			temp[o.id] = o;
		}
		if(i!=(mvBeforeSkuIndex+1)){
			temp[key] = skuObj;
		}
		i++;
	});
	$.each(nowContrastArray,function(key,skuObj){
		delete nowContrastArray[key];
	});
	//console.info("查看清空后的正在对比对象里面存储的东西");
	//console.info(nowContrastArray);
	nowContrastArray = temp;
	//console.info(nowContrastArray);
	temp = null;
	//console.info("清空临时对象后");
	//console.info(nowContrastArray);
	
	//移动存储的位置
	//清空对比文本框里面所有的单品值
	//遍历赋值
	nowContrastAnew();//清空对比框正在对比的单品信息，重新遍历正在对比的存储对象进行赋值
}

/**
 * 清空对比框正在对比的单品信息，重新遍历正在对比的存储对象进行赋值
 */
function nowContrastAnew(){
	//清空对比文本框里面所有的单品值
	bz_clearSkuContrast();
	//重新遍历当前对比存储集合，为对比框赋值
	var i =0;
	$.each(nowContrastArray,function(key,skuObj){
		var goods;
		//如果是默认的单品取已经存储的商品基础信息，如果不是默认的就从单品里面取商品基础信息，赋值给goods。方便下面取属性值
		if(skuObj.defaultSku==1){
			goods = goodsInfoObj;
		}else{
			goods = skuObj.goods;
		}
		bz_SkuContrast(goods,skuObj,i);
		i++;
	});
}


/**
 * 计算Object里面存储了多少。。。
 * @param Obj
 * @returns {Number}
 */
function objectSize(obj){
	var i = 0;
	for(var o in obj){
        i++;
     }
	return i;
}

/**
 * 查询某元素在对象中的位置
 * @param obj 要查询的对象
 * @param key 要查询的元素
 */
function objectIndexOf(obj,key){
	var i = 0;
	for(var k in obj){
		//console.info(k+"   "+key);
		if(key==k){
			return i;
		}
		i++;
     }
	return -1;
}

/**
 * 根据下标位置获取obj对象
 * @param obj 要从某对象里面获取
 * @param index 位置
 */
function getObjByIndex(obj,index){
	var i = 0;
	for(var key in obj){
		if(index==i){
			return obj[key];
		}
		i++;
     }
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
		data: {'targetType':'goods','targetId':goodsInfoObj.id,'page':pingjiaPage}, 
		success: function(d){
			//console.info(d);
			var evaluatePage = d.page;
			var evaluatePageCount = d.pageCount;
			
			//清空以前的评论信息
			var ul = $(".pingj_pingj ul");
			ul.html("");
			//遍历评价列表
			$.each(d.evaluates,function(i,e){
				//console.info("e");//console.info(e);
				var userName ="";
				if(e.members.username){
					var name = e.members.username;
					userName = name.substring(0,2)+"**"+name.substring(name.length-2,name.length);
				}
				var li = 
				  '<li>'+
		            '<table border="0" cellspacing="0" cellpadding="0">'+
		             '<tbody><tr>'+
		              '<td width="228"><span>'+userName+'</span><br>'+
		                e.createtime+'</td>'+
		              '<td width="324">';
				var skuNum = 0;
				//遍历此评论包含的单品列表
				$.each(e.orderItemss,function(i2,o){
					skuNum += parseInt(o.nums);
					li += o.goodsSku.goods.brand.name + o.goodsSku.goods.name + o.goodsSku.color.colorName + o.goodsSku.edition + o.goodsSku.standard +'<br>';
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
		data: {'targetType':'goods','targetId':goodsInfoObj.id,'page':pingjiaPage}, 
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
		data: {'targetType':'sku','targetId':goodsInfoObj.id,'page':dealPageNum}, 
		success: function(data){
			//console.info("月成交记录详情");
			//console.info(data); 
			if(data){
				var pageCount = data.pageCount;
				$("#goods_deal_ul").html("");
				$.each(data.deals,function(i,d){
					//console.info(d);
					var fristName = "";
					var endName = "";
					//判断用户名长度
					if(d && d.USERNAME && d.USERNAME.length<4){//如果用户名长度小于4那么就取前一后一
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
									'<td width="524">网络类型:'+d.EDITION+' '+d.STANDARD+
										'<br> 机身颜色:'+d.GC_NAME+
										'<br> 机身内存:'+d.STORAGE+' </td>'+
									'<td width="113" align="left">'+d.NUMS+' </td>'+
									'<td width="151">'+
										'<h5></h5> </td>'+
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

/**
 * 过滤图片字符串，把 _.webp 结尾的自动删除掉
 */
function filterImg(){
	$("img").each(function(){
		var imgUrl = $(this).attr("src");
		lastUrl = imgUrl.substring(imgUrl.length-6, imgUrl.length);//后六位
		if(lastUrl=="_.webp"){
			imgUrl = imgUrl.substring(0,imgUrl.length-6);
			$(this).attr("src",imgUrl);
		}
	});
}


