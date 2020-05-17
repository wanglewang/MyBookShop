package com.anglewang.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.anglewang.entity.Book;

public class BookDao extends BaseDao{
	
	/**
	 * 更新图书数量
	 * @param bookId
	 * @param num  正数为补库存，负数是出货
	 * @return
	 * @throws Exception
	 */
	public int updateStock(String bookId , int num) throws Exception {
		int iRet = 0;
		
		String sql = "update book set stock=stock+? where book_id=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1,num);
		ps.setString(2,bookId);
		iRet = ps.executeUpdate();
		ps.close();
		if(iRet > 0) {
			//throw new RuntimeException("事务回滚测试....");
		}		   
		
		return iRet;
	}
	/**
	 * 提取购物车中的图书信息
	 * @param bookIdSet
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBookList(Set<String> bookIdSet) throws Exception {
		List<Book> books;
		
		int i=0;
		String allISBN = "";
		for(String bookId : bookIdSet) {
			if(i==0) {
				allISBN = "'" + bookId + "'";
			}else {
				allISBN = allISBN + ",'" + bookId + "'"; 
			}
			i++;
		}		
		String sql = "select * from book where book_id in (" + allISBN + ")";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<>();
		while(rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getString("book_id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setPress(rs.getString("press"));
			book.setPublishDate(rs.getDate("publish_date"));
			book.setCover(rs.getString("cover"));
			book.setInformation(rs.getString("information"));
			book.setCategoryId(rs.getString("category_id"));
			book.setStock(rs.getInt("stock"));
			book.setPrice(rs.getDouble("price"));
			book.setDiscount(rs.getDouble("discount"));
			books.add(book);
		}
		rs.close();
		ps.close();	
		
		return books;
	}
	
	
	/**
	 * 
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String bookId)throws Exception {
		Book book = new Book();
		
		String sql = "select * from book where book_id=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, bookId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			book.setBookId(rs.getString("book_id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setPress(rs.getString("press"));
			book.setPublishDate(rs.getDate("publish_date"));
			book.setCover(rs.getString("cover"));
			book.setInformation(rs.getString("information"));
			book.setCategoryId(rs.getString("category_id"));
			book.setStock(rs.getInt("stock"));
			book.setPrice(rs.getDouble("price"));
			book.setDiscount(rs.getDouble("discount"));
		}
		rs.close();
		ps.close();
		
		return book;
	}
	
	/**
	 * 获得主页图书列表
	 * @return
	 * @throws Exception
	 */
	public List<Book> getMainBooks( ) throws Exception {
		List<Book> books;
		
		String sql = "select * from book";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<>();
		while(rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getString("book_id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setPress(rs.getString("press"));
			book.setPublishDate(rs.getDate("publish_date"));
			book.setCover(rs.getString("cover"));
			book.setInformation(rs.getString("information"));
			book.setCategoryId(rs.getString("category_id"));
			book.setStock(rs.getInt("stock"));
			book.setPrice(rs.getDouble("price"));
			book.setDiscount(rs.getDouble("discount"));
			books.add(book);
		}
		rs.close();
		ps.close();
		
		return books;
	}
}
