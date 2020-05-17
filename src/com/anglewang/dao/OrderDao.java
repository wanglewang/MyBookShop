package com.anglewang.dao;

import java.sql.PreparedStatement;
import java.util.Date;

import com.anglewang.util.OrderUtil;

public class OrderDao extends BaseDao{
	/**
	 * �����¶���
	 * @param userName
	 * @param total
	 * @return    ���ض������
	 * @throws Exception
	 */
	public String addOrder(String userName,double total) throws Exception{
		//��ö�����
		String orderId = OrderUtil.createNewOrderNo();
		String sql = "insert into order values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, orderId);
		ps.setString(2,userName);
		ps.setDouble(3,total);
		ps.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));
		ps.executeUpdate();
		ps.close();
		
		return orderId;
	}
}
