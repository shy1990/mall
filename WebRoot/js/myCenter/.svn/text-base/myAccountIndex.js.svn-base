

$(function(){
	
	
	
});


/**
 * 根据选择的条件重新加载数据
 */
function againData(type,val,sel){
	
	$("img").each(function(){
		if(!$(this).attr("src")){//如果当前图片没值那么就添加默认图片
			$(this).attr("src","images/goodsHotImg.jpg"); 
		} 
	});
	
	////console.info("模拟提交数据");
	var form = $('<form id="js_submit_form"></form>');  
    form.attr('action', "order/orders.html");  
    form.attr('method', 'post');  
    form.attr('target', '_self');  
    var paramInput = '<input type="text" name="'+type+'" value="'+val+'"/>';
    var selectedInput = '<input type="text" name="selected" value="'+sel+'"/>';
    form.append(paramInput);  
    form.append(selectedInput); 
    ////console.info("直接请求数据"+form);
    //form.submit();
    $("body").append(form);
    //form.submit().click();
    document.getElementById("js_submit_form").submit();
}





/**
 * 查看订单详情
 * @param id 订单id
 */
function orderDetail(id){
	var form = $('<form id="js_submit_form" method="post" target="_self" action="order/detail.html"></form>');  
    var idInput = '<input type="text" name="id" value="'+id+'"/>';
    form.append(idInput);  
    //////console.info("点击分页重新请求参数"+form);
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
    //////console.info("点击分页重新请求参数"+form);
    //form.submit();
    $("body").append(form);
    document.getElementById("js_submit_form").submit();
}

