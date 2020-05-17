package com.anglewang.dao;

import java.sql.PreparedStatement;

import com.anglewang.entity.Book;

public class OrderDetailsDao extends BaseDao {
	/**
	 * 添加一条订单明细
	 * @param orderId
	 * @param book
	 * @throws Exception
	 */
	public void addOrderDetail(String orderId,Book book) throws Exception{		
		//添加订单明细
		String sql = "insert into order_details (order_id,book_id,purchase_quantity,discounted_price) values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,orderId);
		ps.setString(2,book.getBookId());
		ps.setInt(3,book.getPurchaseQuantity());
		ps.setDouble(4,book.getDiscount()*book.getPrice());
		ps.executeUpdate();
		ps.close();			
		//更新图书库存(在一个事务中，数据库的连接必须一致)
		BookDao bookDao = new BookDao();
		bookDao.setConn(this.getConn());  //把当前对象的数据库连接赋值给bookDao
		bookDao.updateStock(book.getBookId(),-book.getPurchaseQuantity());
	}
}
