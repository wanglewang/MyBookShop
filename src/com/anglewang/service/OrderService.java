package com.anglewang.service;

import java.util.List;

import com.anglewang.dao.OrderDao;
import com.anglewang.dao.OrderDetailsDao;
import com.anglewang.dao.UserDao;
import com.anglewang.entity.Book;
import com.anglewang.exception.InputEmptyException;

public class OrderService {
	/**
	 * ͼ�鹺��
	 * @param uname
	 * @param allMoney
	 * @param books
	 * @throws Exception
	 */
	public void payMoney(String uname,double allMoney,List<Book> books) throws Exception {
		if(uname == null || uname.equals("")) {
			throw new InputEmptyException("���uname����Ϊ��...");
		}
		if(allMoney <= 0) {
			throw new InputEmptyException("�������쳣...");
		}
		if(books==null || books.size()==0) {
			throw new InputEmptyException("����ͼ������쳣...");
		}
		UserDao userDao=new UserDao();
		OrderDao orderDao = new OrderDao();
		OrderDetailsDao orderDetailsDao=new OrderDetailsDao();
		try {
			userDao.beginTransaction();                      //��������
			userDao.updateUserAccount(uname, -allMoney);
			String orderNO = orderDao.addOrder(uname, allMoney);
			for(Book bk : books) {
				//��Ӷ�����ϸ
				orderDetailsDao.addOrderDetail(orderNO, bk);
			}
			userDao.commit();                               //�ύ����
		} catch (Exception e) {
			userDao.rollback();                             //�ع�����
			throw e;                                    	//�����׳��쳣
		}finally {
			userDao.closeConnection();
		}		
	}
}
