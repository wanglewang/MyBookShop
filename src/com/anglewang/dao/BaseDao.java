package com.anglewang.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.anglewang.util.DbInfo;
import com.anglewang.util.Log;

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
			Log.logger.info(Thread.currentThread().getId() + "，打开一个新的数据库连接....");
		}else {
			Log.logger.info(Thread.currentThread().getId() + "，使用原有数据库连接....");
		}		
	}
	
	/**
	 * 开启事务
	 * @throws Exception
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();    		  //开启事务前，要保证数据库连接是有效的
		this.conn.setAutoCommit(false);  //true启用自动提交模式; false禁用它
		Log.logger.info(Thread.currentThread().getId() + "，开启事务");
	}
	/**
	 * 提交事务
	 * @throws Exception
	 */
	public void commit() throws Exception{
		if(this.conn != null) {
			this.conn.commit();
			Log.logger.info(Thread.currentThread().getId() + ",提交事务");
		}
	}
	
	/**
	 * 回滚事务
	 * @throws Exception
	 */
	public void rollback() throws Exception{
		if(this.conn != null) {
			this.conn.rollback();
			Log.logger.info(Thread.currentThread().getId() + ",回滚事务");
		}
	}
	/**
	 * 关闭连接
	 */
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				Log.logger.info(Thread.currentThread().getId() + "数据库关闭...");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}

}
