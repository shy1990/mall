/*
 * 购物车
 */
var Cart = function(option) {
	option = option || {};
	this.items = $("<div>"); //购物项
	if (option.key) {
		this.loadFromCookie(option.key);
	}
}
Cart.prototype.loadFromCookie = function(key) {
	var json = $.cookie(key, {
		path: "/"
	})||"{}";
	var items = $.parseJSON(json);
	for (var key in items) {
		this.items.data(key, new CartItem(items[key]));
	}
	this.items.trigger('loaded');
}
	//保存到cookies
Cart.prototype.save2cookie = function(key) {
		var items = this.items.data();
		var json = $.toJSON(items);
		$.cookie(key, json, {
			path: "/"
		});
		this.items.trigger('saved');
	}
	//添加购物
Cart.prototype.add = function(cartItem) {
		var key = cartItem.goodsId + cartItem.type;
		var item=this.items.data(key);
		this.items.data(key, cartItem);
		this.items.trigger('afteradd');
	}
	//删除购物
Cart.prototype.remove = function(cartItem) {
	var key = cartItem.goodsId + cartItem.type;
	this.items.removeData(key);
	this.items.trigger('afterremove');
}
Cart.prototype.getTotalPrice = function() {
	var items = this.items.data();
	var totalPrice = 0;
	$.each(items, function(i, item) {
		totalPrice += item.getTotalPrice();
	});
	return totalPrice;
}
Cart.prototype.getTotalOriginalPrice = function() {
		var items = this.items.data();
		var totalOriginalPrice = 0;
		$.each(items, function(i, item) {
			totalOriginalPrice += item.getTotalOriginalPrice();
		});
		return totalOriginalPrice;
	}
Cart.prototype.getTotalQuantity = function() {
		var items = this.items.data();
		var totalQuantity = 0;
		$.each(items, function(i, item) {
			totalQuantity += parseInt(item.quantity);
		});
		return totalQuantity;
	}
	/**
	 * 购物项目
	 */
var CartItem = function(option) {
	this.goodsId = "";
	this.type = "手机";
	this.quantity = 0;
	this.price=0;
	this.originalPrice=0;
	$.extend(this, option || {});
}
CartItem.prototype.getTotalPrice = function() {
	var totalPrice = parseFloat(this.price) * this.quantity;
	return totalPrice;
}
CartItem.prototype.getTotalOriginalPrice = function() {
	var totalOriginalPrice = parseFloat(this.originalPrice) * this.quantity;
	return totalOriginalPrice;
}