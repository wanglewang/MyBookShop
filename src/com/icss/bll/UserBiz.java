package com.icss.bll;

import java.util.List;

import com.icss.dll.UserDao;
import com.icss.dto.Book;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.exception.UserVoilateExceptoin;

public class UserBiz {	
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
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();                      //��������
			dao.updateUserAccount(uname, -allMoney);
			String orderNO = dao.addOrder(uname, allMoney);
			for(Book bk : books) {
				//��Ӷ�����ϸ
				dao.addOrderDetail(orderNO, bk);
			}
			dao.commit();                               //�ύ����
		} catch (Exception e) {
			dao.rollback();                             //�ع�����
			throw e;                                    //�����׳��쳣
		}finally {
			dao.closeConnection();
		}		
	}
	
	/**
	 * У���û����Ƿ��ͻ
	 * @param uname
	 * @return  true ��ʾ�û�����ͻ     false����ͻ
	 * @throws Exception
	 */
	public boolean validUname(String uname) throws InputEmptyException,Exception{
		if(uname==null || uname.equals("")) {
			throw new InputEmptyException("�û�������Ϊ��");
		}
		UserDao dao = new UserDao();
		try {
			return dao.validUname(uname);
		} finally {
			dao.closeConnection();
		}		
	}
	

	/**
	 * �û���¼
	 * @param uname �û���
	 * @param pwd   ����
	 * @return      ���ص�¼�ɹ����û�����
	 * @throws Exception
	 */
	public User login(String uname , String pwd) throws InputEmptyException,Exception {
		//�ж��û��������벻��Ϊ��
		if(uname == null || pwd == null || uname.trim().equals("") || pwd.trim().equals("")) {
			throw new InputEmptyException("�û���������Ϊ��,���޸� ...");
		}
		//ҵ�����̿���
		//ҵ�������		
	    UserDao dao = new UserDao();
	    try {
	    	return dao.login(uname, pwd);
		} finally {
			dao.closeConnection();
		}   
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws UserVoilateExceptoin,Exception {
		if(user == null ) {
			throw new InputEmptyException("�û���β���Ϊ��");
		}
		UserDao dao = new UserDao();
		try {
			//��ѯһ���û����Ƿ��ظ�
			boolean bRet = dao.validUserName(user.getUname());
			//�������û�
			if(bRet) {				
				System.out.println("�û����ظ�....");
				throw new UserVoilateExceptoin("�û����ظ�������������...");
			}else {
				//���ظ�
				dao.regist(user);
			}	
		} finally {
			dao.closeConnection();
		}
	}

}
