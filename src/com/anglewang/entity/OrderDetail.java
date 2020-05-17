package com.anglewang.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long	orderDate;			//订单日期
	private String 	orderId;			//订单ID
	private String 	bookId;				//书籍ID
	private double 	discountedPrice;	//折后价
	private int  	purchaseQuantity;	//购买数量
	
	public long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	
}
