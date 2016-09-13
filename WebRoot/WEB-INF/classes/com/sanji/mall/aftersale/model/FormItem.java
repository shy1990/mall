package com.sanji.mall.aftersale.model;

import com.sanji.mall.model.OrderItems;

public class FormItem {
	private String id;
	private String name;
	private int quantity;
	private String remark;
	private Form form;
	private OrderItems orderItems;
	private ThForm thForm;

	private double price;

	public OrderItems getOrderItems() {
		return orderItems;
	}

	public ThForm getThForm() {
		return thForm;
	}

	public void setThForm(ThForm thForm) {
		this.thForm = thForm;
	}

	public void setOrderItems(OrderItems orderItems) {
		this.orderItems = orderItems;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
