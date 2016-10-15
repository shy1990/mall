(function($){
	var pluginName = "SJDataView";
	//默认设置

	var generateHtml=function(item){
		var html ="	<li class=\"class"+item.id+"\"> "+
				"		<div class='xgzy_main_06'>" +
				"			<img src='"+item.imgSrc+"' width='225' height='175'>" +
				"		</div>" +
				"		<div class='xgzy_main_02'>" +
				"			<div class='xgzy_main_03'>" +
				"				<img src='images/huax.png' width='60' height='10'>" +
				"			</div>" +
				"			<span>¥"+item.price+"</span> ¥" +item.originalPrice+
				"		</div>" +
				"		<div class='xgzy_main_04'>" +
				"			"+item.name+"<br> 购买数量" +
				"		</div>" +
				"		<div class='xgzy_main_05'>" +
				"		<div id='xq_main_02_let_03' class='xq_main_02_let_03'>" +
				"			<div id='xq_main_02_let_03_let'>" +
				"				<a href='javascript:void(0);'>" +
				"					<img src='images/jian.jpg' width='20' height='20'>" +
				"				</a>" +
				"			</div>" +
				"			<div id='xq_main_02_let_03_ret'>" +
				"				<input name=\"\" type=\"text\" value=\""+item.number+"\" onmouseover=\"this.focus()\" onmouseout=\"if(this.value=='')this.value='0';\" onfocus=\"this.select()\" onclick=\"if(this.value=='0')this.value=''\">" +
				"			</div>" +
				"			<div id='xq_main_02_let_03_let'>" +
				"				<a href='javascript:void(0);'>" +
				"					<img src='images/jia.jpg' width='20' height='20'>" +
				"				</a>" +
				"			</div>" +
				"			<div class='clear'></div>" +
				"		</div>" +
				"	</div>" +
				"</li>";
		return $(html);
	};

	var generateDetailHtml=function(item){
		var html ="<li id=\"id"+item.id+"\">"+
								"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
									"<tbody>"+
										"<tr>"+
										"	<td width=\"92\">"+item.type+"</td>"+
										"	<td width=\"93\">"+item.brandName+"</td>"+
										"	<td width=\"149\">"+item.name+"</td>"+
										"	<td width=\"199\">"+item.standard+"</td>"+
										"	<td width=\"109\"><h5>￥"+item.price+"</h5></td>"+
										"	<td width=\"87\">"+
											"	<div id=\"xq_main_02_let_03\" class=\"xq_main_02_let_03\">"+
											"		<div id=\"xq_main_02_let_03_let\">"+
											"			<a href=\"javascript:void(0);\"><img src=\"images/jian.jpg\" width=\"20\" height=\"20\"></a>"+
											"		</div>"+
											"		<div id=\"xq_main_02_let_03_ret\">"+
											"			<input name=\"\" type=\"text\" value=\""+item.number+"\" onmouseover=\"this.focus()\" onmouseout=\"if(this.value=='')this.value='0';\" onfocus=\"this.select()\" onclick=\"if(this.value=='0')this.value=''\">"+
											"		</div>"+
											"		<div id=\"xq_main_02_let_03_let\">"+
											"			<a href=\"javascript:void(0);\"><img src=\"images/jia.jpg\" width=\"20\" height=\"20\"></a>"+
											"		</div>"+
											"		<div class=\"clear\"></div>"+
											"	</div>"+
										"	</td>"+
										"	<td width=\"105\">件</td>"+
										"	<td width=\"87\"><a href=\"javascript:void(0);\">￥"+item.tmallPrice+"</a></td>"+
										"</tr>"+
								"	</tbody>"+
								"</table>"+
							"</li>";
		return $(html);
	};
	var methods = {
		init : function(options) {
			var defaults = {
					min : 0,//小数量
					max : Number.MAX_VALUE,//最大数量
					model:'block',//渲染模式 block,detail
					onNumberChange : function(item,number) {//数量改变时触发 
					}
				};
				var settings;
				
			return this.each(function() {
				var $this = $(this);
				// 尝试去获取settings，如果不存在，则返回“undefined”
				settings = $this.data(pluginName+'settings');
				// 如果获取settings失败，则根据options和default创建它
				if (typeof settings === "undefined") {
					settings = $.extend({}, defaults, options);
					// 保存我们新创建的settings
					$this.data(pluginName+'settings', settings);
				} else {
					// 如果我们获取了settings，则将它和options进行合并
					settings = $.extend({}, settings, options);
				}
			});
		},
		destroy : function(options) {
			return $(this).each(function() {
				var $this = $(this);
				//解绑事件
				$this.unbind('.'+pluginName);
				// 删除元素对应的数据
				$this.removeData(pluginName);
			});
		},
		generateDetail:function(options){
			return $(this).each(function(){
				var $this=$(this);
				if($this.is("ul")){
					
				}else{
					$.error("必须传入ul标签.");
				}
			});
		}
		,
		render : function(options) {
			if (!$.isArray(options)) {
				$.error("必须传入数组数据.");
				return;
			}
			
			return $(this).each(function() {
					var $this = $(this);
					var settings=$this.data(pluginName+'settings');
					$.each(options, function(i, item) {
						//默认数据格式
						var defaultData={id:"defaultId",name:'请设置名字',type:'设置品类',brandName:'设置品牌',standard:'设置规格',imgSrc:'http://www.doubean.com/face/store/like/0000/000/923.jpg',price:0.00,originalPrice:0.00,tmallPrice:0.00,number:0};
						
						//合并数据项
						var item=$.extend(defaultData,item);
						var html;
						//生成li标签并绑定数据
						if(settings.model==='detail'){
							html=generateDetailHtml(item).data(pluginName,item);
						}else{
							html=generateHtml(item).data(pluginName,item);
						}
						
						//减按钮
						var subButton=html.find("a").eq(0);
						//加按钮
						var addButton=html.find("a").eq(1);
						//输入框banding数据
						var input=html.find("input").data('data',item);
						
						subButton.click(function(){
							var currentVal = parseInt(input.val());
							var next = currentVal - 1;
							if (next >=settings.min) {
								input.val(next);
								input.trigger('change');
							}
						});
						addButton.click(function(){
							var currentVal = parseInt(input.val());
							var next = currentVal + 1;
							if (next < settings.max) {
								input.val(next);
								input.trigger('change');
							}
						});
						
						input.change(function(){
							//验证数据
							var tmptxt = input.val();
							input.val(tmptxt.replace(/\D|^0/g, ''));
							if(input.val()==''){
								input.val(0);
							}
							//修改绑定数据
							var data=input.data('data');
							data.number=input.val();
							input.data('data',data);
							
							//触发绑定事件
							settings.onNumberChange.call($this,data,input.val());
						});
						if(settings.model==='block'){
							$this.append(html);
						}else{
							html.appendTo($this).hide().slideDown();
						}
						
					});
					//清除浮动
					if(settings.model==='block')
						$this.children('li').wrapAll("<ul></ul>");
						$this.append("<div class='clear'></div>");
				});
		}
	};

	$.fn.SJDataView = function() {
		var method = arguments[0];
		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof method === "object" || !method) {
			method = methods.init;
		} else {
			$.error("Method" + method + "does not exist on jQuery.SJDataView");
			return this;
		}
		return method.apply(this, arguments);
	}

})(jQuery);

