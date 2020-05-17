package com.anglewang.service;

import java.util.List;

import com.anglewang.dao.OrderDao;
import com.anglewang.dao.OrderDetailsDao;
import com.anglewang.dao.UserDao;
import com.anglewang.entity.Book;
import com.anglewang.exception.InputEmptyException;

public class OrderService {
	/**
	 * 图书购买
	 * @param uname
	 * @param allMoney
	 * @param books
	 * @throws Exception
	 */
	public void payMoney(String uname,double allMoney,List<Book> books) throws Exception {
		if(uname == null || uname.equals("")) {
			throw new InputEmptyException("入参uname不能为空...");
		}
		if(allMoney <= 0) {
			throw new InputEmptyException("付款金额异常...");
		}
		if(books==null || books.size()==0) {
			throw new InputEmptyException("付款图书入参异常...");
		}
		UserDao userDao=new UserDao();
		OrderDao orderDao = new OrderDao();
		OrderDetailsDao orderDetailsDao=new OrderDetailsDao();
		try {
			userDao.beginTransaction();                      //开启事务
			userDao.updateUserAccount(uname, -allMoney);
			String orderNO = orderDao.addOrder(uname, allMoney);
			for(Book bk : books) {
				//添加订单明细
				orderDetailsDao.addOrderDetail(orderNO, bk);
			}
			userDao.commit();                               //提交事务
		} catch (Exception e) {
			userDao.rollback();                             //回滚事务
			throw e;                                    	//二次抛出异常
		}finally {
			userDao.closeConnection();
		}		
	}
}
