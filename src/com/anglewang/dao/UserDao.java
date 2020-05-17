package com.anglewang.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.anglewang.entity.Book;
import com.anglewang.entity.User;
import com.anglewang.util.OrderUtil;

public class UserDao extends BaseDao{
	
	/**
	 * �û��ۿ����ֵ
	 * @param uname
	 * @param account  ������ʾ��ֵ��������ʾ�ۿ�
	 * @throws Exception
	 */
	public int updateUserAccount(String userName,double balance) throws Exception{
		int iRet;
		
		String sql = "update user set balance=balance+? where user_name=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setDouble(1,balance);
		ps.setString(2,userName);
		iRet = ps.executeUpdate();
		ps.close();
		
		return iRet;
	}

	/**
	 * У���û����Ƿ��ͻ
	 * @param uname
	 * @return  true ��ʾ�û�����ͻ     false����ͻ
	 * @throws Exception
	 */
	public boolean validUname(String userName) throws Exception{
		boolean bRet = false;
		
		String sql = "select user_name from user where user_name=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,userName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		   bRet = true;	
		}
		rs.close();
		ps.close();
		
		return bRet;		
	}
	
	
	/**
	 * �û���¼
	 * @param uname �û���
	 * @param pwd   ����
	 * @return      ���ص�¼�ɹ����û�����
	 * @throws Exception
	 */
	public User login(String userName , String pwd) throws Exception {
		User user = null;
		
		//������¼sql
		String sql = "select * from user where user_name=? and pwd=?";
		//�����ݿ�
		this.openConnection();
		
		//PreparedStatement����ִ��sql���
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, pwd);
		//���ؽ����
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			user = new User();
			user.setUserName(userName);
			user.setPwd(pwd);
			user.setRole(rs.getInt("role"));
			user.setPhone(rs.getString("phone"));
			user.setRegisterTime(rs.getTimestamp("register_time"));
			user.setBalance(rs.getDouble("balance"));
			break;
		}
		rs.close();
		ps.close();
		
		return user;
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception {
		String sql="insert into user values(?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,user.getUserName());
		ps.setString(2, user.getPwd());
		ps.setInt(3,user.getRole());
		ps.setString(4,user.getPhone());
		if(user.getRegisterTime() != null) {
			ps.setTimestamp(5,new java.sql.Timestamp(user.getRegisterTime().getTime()));	
		}else {
			ps.setTimestamp(5, null);
		}
		ps.setDouble(6, 3000);
		ps.executeUpdate();
		ps.close();		
	}
	
	/**
	 * У���û���
	 * @param uname
	 * @return
	 * @throws Exception
	 */
	public boolean validUserName(String userName) throws Exception {
		boolean bRet = false;
		
        String sql = "select user_name from user where user_name=?";		
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		  bRet = true;	
		}
		rs.close();
		ps.close();
		
		return bRet;
	}
}
