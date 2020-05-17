package com.anglewang.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orederId;	//订单序号
	private double total;		//总价
	private Date   payTime;		//支付时间
	private String userName;	//用户名
	
	public String getOrederId() {
		return orederId;
	}
	public void setOrederId(String orederId) {
		this.orederId = orederId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
