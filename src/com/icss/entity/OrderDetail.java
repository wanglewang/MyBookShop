package com.icss.entity;

public class OrderDetail {
	private long dno;
	private String oid;
	private String isbn;
	private int   buyNum;
	private double rprice;
	public long getDno() {
		return dno;
	}
	public void setDno(long dno) {
		this.dno = dno;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getRprice() {
		return rprice;
	}
	public void setRprice(double rprice) {
		this.rprice = rprice;
	}
	
	
}
