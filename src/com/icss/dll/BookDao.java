package com.icss.dll;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.icss.dto.Book;

public class BookDao extends BaseDao{
	
	/**
	 * 更新图书数量
	 * @param isbn
	 * @param num  正数为补库存，负数是出货
	 * @return
	 * @throws Exception
	 */
	public int updateBookNum(String isbn , int num) throws Exception {
		int iRet = 0;
		
		String sql = "update tbook set num=num+? where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1,num);
		ps.setString(2,isbn);
		iRet = ps.executeUpdate();
		ps.close();
		if(iRet > 0) {
			//throw new RuntimeException("事务回滚测试....");
		}		   
		
		return iRet;
	}
	/**
	 * 提取购物车中的图书信息
	 * @param isbns
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBookList(Set<String> isbns) throws Exception {
		List<Book> books;
		
		int i=0;
		String allISBN = "";
		for(String isbn : isbns) {
			if(i==0) {
				allISBN = "'" + isbn + "'";
			}else {
				allISBN = allISBN + ",'" + isbn + "'"; 
			}
			i++;
		}		
		String sql = "select * from tbook where isbn in (" + allISBN + ")";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<>();
		while(rs.next()) {
			Book bk = new Book();
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setCid(rs.getString("cid"));
			bk.setDiscount(rs.getDouble("discount"));
			bk.setInfo(rs.getString("info"));
			bk.setIsbn(rs.getString("isbn"));
			bk.setNum(rs.getInt("num"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));
			books.add(bk);
		}
		rs.close();
		ps.close();	
		
		return books;
	}
	
	
	/**
	 * 
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String isbn)throws Exception {
		Book bk = null;
		
		String sql = "select * from tbook where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bk = new Book();
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setCid(rs.getString("cid"));
			bk.setDiscount(rs.getDouble("discount"));
			bk.setInfo(rs.getString("info"));
			bk.setIsbn(rs.getString("isbn"));
			bk.setNum(rs.getInt("num"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));
		}
		rs.close();
		ps.close();
		
		return bk;
	}
	
	/**
	 * 获得主页图书列表
	 * @return
	 * @throws Exception
	 */
	public List<Book> getMainBooks( ) throws Exception {
		List<Book> books;
		
		String sql = "select * from tbook";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<>();
		while(rs.next()) {
			Book bk = new Book();
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setCid(rs.getString("cid"));
			bk.setDiscount(rs.getDouble("discount"));
			bk.setInfo(rs.getString("info"));
			bk.setIsbn(rs.getString("isbn"));
			bk.setNum(rs.getInt("num"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));
			books.add(bk);
		}
		rs.close();
		ps.close();
		
		return books;
	}
}
