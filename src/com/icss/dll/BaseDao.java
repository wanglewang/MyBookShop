package com.icss.dll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.icss.util.DbInfo;
import com.icss.util.Log;

public abstract class BaseDao {
	protected Connection conn;	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void openConnection() throws SQLException,ClassNotFoundException {
		if(this.conn == null || this.conn.isClosed()) {
			DbInfo db = DbInfo.instance();
			Class.forName(db.getDriver());
			conn = DriverManager.getConnection(db.getUrl(),db.getUname(),db.getPwd());
			Log.logger.info(Thread.currentThread().getId() + "����һ���µ����ݿ�����....");
		}else {
			Log.logger.info(Thread.currentThread().getId() + "��ʹ��ԭ�����ݿ�����....");
		}		
	}
	
	/**
	 * ��������
	 * @throws Exception
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();    		  //��������ǰ��Ҫ��֤���ݿ���������Ч��
		this.conn.setAutoCommit(false);  //true�����Զ��ύģʽ; false������
		Log.logger.info(Thread.currentThread().getId() + "����������");
	}
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public void commit() throws Exception{
		if(this.conn != null) {
			this.conn.commit();
			Log.logger.info(Thread.currentThread().getId() + ",�ύ����");
		}
	}
	
	/**
	 * �ع�����
	 * @throws Exception
	 */
	public void rollback() throws Exception{
		if(this.conn != null) {
			this.conn.rollback();
			Log.logger.info(Thread.currentThread().getId() + ",�ع�����");
		}
	}
	
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				Log.logger.info(Thread.currentThread().getId() + "���ݿ�ر�...");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}

}
