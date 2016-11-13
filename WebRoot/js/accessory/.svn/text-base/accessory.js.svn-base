
function add2cart(){
	//防止重复提交
	$('#add2cart').attr('href','javascript:void(0);');
	var param={goodsId:goodsId,goodsName:goodsName};
	var skuList=new Array();
	var skuInputs = $("input[name='sku']");
	skuInputs.each(function(i, o) {
		var id=$(o).attr('id').split('_')[2];
		var rootInput=$("#quantity_root_" + id);
		var sku={id:id,quantity:$(o).val(),rootQuantity:rootInput.val()}
		skuList.push(sku);
	});
	param.skuList=skuList;
	var giftMap={};
	var giftInput=$('input[name$="gift_price"]');
	giftInput.each(function(i,o){
		var id=$(o).attr("data-id");
		var quantity=$(o).val();
		var gift={id:id,quantity:quantity};
		var prefix=$(o).attr('name').split("_")[0];
		giftMap[prefix]=gift;
	});
	param.giftMap=giftMap;
	
	var accessoryMap={bhm:[],bhk:[],bht:[],dc:[]};
	var pjInputs=$('input[name$="_detail"]');
	pjInputs.each(function(i,o){
		var data=$(o).attr('name').split('_');
		accessoryMap[data[0]].push({id:data[2],quantity:$(o).val()});
	});
	param.accessoryMap=accessoryMap;
	////console.log($.toJSON(param));
	
    var form = $('<form></form>');  
    form.attr('action', "accessory/add2Cart.html");  
    form.attr('method', 'post');  
    form.attr('target', '_self');  
    var my_input = $('<input type="text" name="param" />');  
    my_input.attr('value', $.toJSON(param));  
    form.append(my_input);  
    form.submit();  
}
function initGiftList() {
	var count = 0;
	$('input[name="bhm_gift_quantity"]').each(function(i, o) {
		count += parseInt($(o).val());
	});
	$('input[name$="gift_price"]').val(count);
}

function jianSku(id) {
	var quantityInput = $("input[id^='quantity_sku_" + id + "']");
	var current = quantityInput.val();
	if (current > 1) {
		quantityInput.val(current - 1);
		if (current - 1 < $("#quantity_root_" + id).val()) {
			jianRoot(id);
		}
		calcPrice();
	}

}
function calcPrice() {
	var totalPrice = 0;
	var totalOriginalPrice = 0;
	var totalCount=0;
	// 单品
	var skuInputs = $("input[name='sku']");
	skuInputs.each(function(i, o) {
		totalPrice += parseFloat($(o).attr('data-price')) * $(o).val();
		totalOriginalPrice += parseFloat($(o).attr('data-originalPrice'))
				* $(o).val();
		totalCount+=parseInt($(o).val());
	});
	// root
	var rootInputs = $("input[name='root']");
	rootInputs.each(function(i, o) {
		totalPrice += parseFloat($(o).val() * rootPrice);
	});
	// 赠品
	var giftInputs = $('input[name$="gift_price"]');
	giftInputs.each(function(i, o) {
		totalPrice += parseFloat($(o).attr('data-price')) * $(o).val();
		totalOriginalPrice += parseFloat($(o).attr('data-originalPrice'))* $(o).val();
		totalCount+=parseInt($(o).val());
	});

	// 配件
	$("#pjxq").find('input').each(function(i, o) {
		totalPrice+=parseFloat($(o).attr('data-price'))*$(o).val();
		totalOriginalPrice += parseFloat($(o).attr('data-originalPrice'))* $(o).val();
		totalCount+=parseInt($(o).val());
	});

	$(".totalPrice").text(totalPrice.toFixed(2));
	$(".totalOriginalPrice").text(totalOriginalPrice.toFixed(2));
	var spare = totalOriginalPrice - totalPrice;
	$(".spare").text(spare.toFixed(2));
	$("#count").text(totalCount);
	initGiftList();
}

function jiaSku(id) {
	var quantityInput = $("input[id^='quantity_sku_" + id + "']");
	var current = quantityInput.val();
	quantityInput.val(parseInt(current) + 1);
	calcPrice();
}

function skuQuantityOnchange(id) {
	var quantityInput = $("#quantity_sku_" + id);
	var rootQuantityInput = $("#quantity_root_" + id);
	if (rootQuantityInput.val() > quantityInput.val()) {
		rootQuantityInput.val(quantityInput.val())

	}
	$("input[id^='quantity_sku_" + id + "']").val(quantityInput.val());
	calcPrice();
}

function rootQuantityOnchange(id) {
	var quantityInput = $("#quantity_sku_" + id);
	var rootQuantityInput = $("#quantity_root_" + id);
	if (rootQuantityInput.val() > quantityInput.val()) {
		rootQuantityInput.val(quantityInput.val())
		calcPrice();
	}
}
function jianRoot(id) {
	var quantityInput = $("#quantity_root_" + id);
	var current = quantityInput.val();
	if (current > 0) {
		quantityInput.val(current - 1);
		calcPrice();
	}
}
function jiaRoot(id) {
	var quantityInput = $("#quantity_root_" + id);
	var next = parseInt(quantityInput.val()) + 1;
	if (next <= $("#quantity_sku_" + id).val()) {
		quantityInput.val(next);
		calcPrice();
	}
}

function jiaPj(idPrfix, id) {
	var sel = idPrfix + id;
	var input = $('input[name=' + sel + ']');
	var next = parseInt(input.val()) + 1;
	input.val(next);
	input.trigger('onchange', idPrfix, id);
	calcPrice();
}
function pjOnChange(idPrfix, id) {
	updatePjDetail(idPrfix, id);
}

function jianPj(idPrfix, id) {
	var sel = idPrfix + id;
	var input = $('input[name=' + sel + ']');
	var next = parseInt(input.val()) - 1;
	if (next >= 0) {
		input.val(next);
		input.trigger('onchange', idPrfix, id);
	}
	calcPrice();
}

function updatePjDetail(idPrfix, id) {
	var ul = $('#pjxq');
	var source = $('input[name=' + idPrfix + id + ']');
	var detail = $('input[name=' + idPrfix + id + '_detail]');
	var catName = source.attr('data-catName');
	var brandName = source.attr('data-brandName');
	var name = source.attr('data-name');
	var description = source.attr('data-description');
	var price = source.attr('data-price');
	var tmallPrice = source.attr('data-tmallPrice');
	var originalPrice = source.attr('data-originalPrice');
	var quantity = source.val();

	if (detail.length == 0) {
		ul
				.append("<li>"
						+ "	<table border='0' cellspacing='0' cellpadding='0'>"
						+ "		<tbody>" + "				<tr>" + "					<td width='92'>"
						+ catName
						+ "</td>"
						+ "					<td width='93'>"
						+ brandName
						+ "</td>"
						+ "					<td width='149'>"
						+ name
						+ "</td>"
						+ "					<td width='199'>"
						+ description
						+ "</td>"
						+ "					<td width='109'><h5>￥"
						+ price
						+ "</h5></td>"
						+ "					<td width='87'>"
						+ "						<div id='xq_main_02_let_03'>"
						+ "							<div id='xq_main_02_let_03_let'>"
						+ "								<a href='javascript:jianPj(\""
						+ idPrfix
						+ "\","
						+ id
						+ ");'><img src='images/jian.jpg' width='20' height='20'></a>"
						+ "							</div>"
						+ "							<div id='xq_main_02_let_03_ret'>"
						+ "								<input name='"
						+ idPrfix
						+ id
						+ "_detail' type='text' data-price='"
						+ price
						+ "' data-originalPrice='"
						+ originalPrice
						+ "' value='"
						+ quantity
						+ "' onmouseover='this.focus()' "
						+ "													onmouseout='if(this.value==\"\")this.value=0;'"
						+ "													onfocus='this.select()'"
						+ "													onclick='if(this.value==\"0\")this.value=\"\";'>"
						+ "							</div>"
						+ "							<div id='xq_main_02_let_03_let'>"
						+ "								<a href='javascript:jiaPj(\""
						+ idPrfix
						+ "\","
						+ id
						+ ");'><img src='images/jia.jpg' width='20' height='20'></a>"
						+ "							</div>"
						+ "							<div class='clear'></div>"
						+ "						</div>"
						+ "					</td>"
						+ "					<td width='105'>件</td>"
						+ "					<td width='87'><a href='#'>￥"
						+ tmallPrice
						+ "</a></td>"
						+ "				</tr>"
						+ "			</tbody>"
						+ "		</table>" + "	</li>").hide().slideDown();
	} else if (quantity <= 0) {
		detail.closest('li').slideUp(function(){
			$(this).remove();
		});
	} else {
		detail.val(quantity);
	}

}