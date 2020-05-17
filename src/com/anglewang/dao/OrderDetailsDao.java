package com.anglewang.dao;

import java.sql.PreparedStatement;

import com.anglewang.entity.Book;

public class OrderDetailsDao extends BaseDao {
	/**
	 * ���һ��������ϸ
	 * @param orderId
	 * @param book
	 * @throws Exception
	 */
	public void addOrderDetail(String orderId,Book book) throws Exception{		
		//��Ӷ�����ϸ
		String sql = "insert into order_details (order_id,book_id,purchase_quantity,discounted_price) values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,orderId);
		ps.setString(2,book.getBookId());
		ps.setInt(3,book.getPurchaseQuantity());
		ps.setDouble(4,book.getDiscount()*book.getPrice());
		ps.executeUpdate();
		ps.close();			
		//����ͼ����(��һ�������У����ݿ�����ӱ���һ��)
		BookDao bookDao = new BookDao();
		bookDao.setConn(this.getConn());  //�ѵ�ǰ��������ݿ����Ӹ�ֵ��bookDao
		bookDao.updateStock(book.getBookId(),-book.getPurchaseQuantity());
	}
}
