package com.anglewang.service;

import com.anglewang.dao.UserDao;
import com.anglewang.entity.User;
import com.anglewang.exception.InputEmptyException;
import com.anglewang.exception.UserVoilateExceptoin;

public class UserService {	
	
	
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
	public void register(User user) throws UserVoilateExceptoin,Exception {
		if(user == null ) {
			throw new InputEmptyException("�û���β���Ϊ��");
		}
		UserDao dao = new UserDao();
		try {
			//��ѯһ���û����Ƿ��ظ�
			boolean bRet = dao.validUserName(user.getUserName());
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
