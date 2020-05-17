package com.anglewang.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.anglewang.entity.Book;
import com.anglewang.entity.User;
import com.anglewang.util.OrderUtil;

public class UserDao extends BaseDao{
	
	/**
	 * 用户扣款与充值
	 * @param uname
	 * @param account  正数表示充值，负数表示扣款
	 * @throws Exception
	 */
	public int updateUserAccount(String uname,double account) throws Exception{
		int iRet;
		
		String sql = "update tuser set account=account+? where uname=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setDouble(1,account);
		ps.setString(2,uname);
		iRet = ps.executeUpdate();
		ps.close();
		
		return iRet;
	}
	
	/**
	 * 生成新订单
	 * @param uname
	 * @param allMoney
	 * @return    返回订单编号
	 * @throws Exception
	 */
	public String addOrder(String uname,double allMoney) throws Exception{
		//获得订单号
		String oid = OrderUtil.createNewOrderNo();
		String sql = "insert into torder values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, oid);
		ps.setString(2,uname);
		ps.setDouble(3,allMoney);
		ps.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));
		ps.executeUpdate();
		ps.close();
		
		return oid;
	}
	
	/**
	 * 添加一条订单明细
	 * @param orderNo
	 * @param book
	 * @throws Exception
	 */
	public void addOrderDetail(String orderNo,Book book) throws Exception{		
		//添加订单明细
		String sql = "insert into TOrderDetail(oid,isbn,buyNum,rprice) values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,orderNo);
		ps.setString(2,book.getIsbn());
		ps.setInt(3,book.getBuynum());
		ps.setDouble(4,book.getDiscount()*book.getPrice());
		ps.executeUpdate();
		ps.close();			
		//更新图书库存(在一个事务中，数据库的连接必须一致)
		BookDao bookDao = new BookDao();
		bookDao.setConn(this.getConn());  //把当前对象的数据库连接赋值给bookDao
		bookDao.updateBookNum(book.getIsbn(),-book.getBuynum());
	}
	

	/**
	 * 校验用户名是否冲突
	 * @param uname
	 * @return  true 表示用户名冲突     false不冲突
	 * @throws Exception
	 */
	public boolean validUname(String uname) throws Exception{
		boolean bRet = false;
		
		String sql = "select uname from tuser where uname=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,uname);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		   bRet = true;	
		}
		rs.close();
		ps.close();
		
		return bRet;		
	}
	
	
	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd   密码
	 * @return      返回登录成功的用户对象
	 * @throws Exception
	 */
	public User login(String uname , String pwd) throws Exception {
		User user = null;
		
		//创建登录sql
		String sql = "select * from tuser where uname=? and pwd=?";
		//打开数据库
		this.openConnection();
		
		//PreparedStatement用于执行sql语句
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
		//返回结果集
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			user = new User();
			user.setUname(uname);
			user.setPwd(pwd);
			user.setRole(rs.getInt("role"));
			user.setTel(rs.getString("tel"));
			user.setRtime(rs.getTimestamp("rtime"));
			user.setAccount(rs.getDouble("account"));
			break;
		}
		rs.close();
		ps.close();
		
				
		return user;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception {
		String sql="insert into tuser values(?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,user.getUname());
		ps.setString(2, user.getPwd());
		ps.setInt(3,user.getRole());
		ps.setString(4,user.getTel());
		if(user.getRtime() != null) {
			ps.setTimestamp(5,new java.sql.Timestamp(user.getRtime().getTime()));	
		}else {
			ps.setTimestamp(5, null);
		}
		ps.executeUpdate();
		ps.close();		
	}
	
	/**
	 * 
	 * @param uname
	 * @return
	 * @throws Exception
	 */
	public boolean validUserName(String uname) throws Exception {
		boolean bRet = false;
		
        String sql = "select uname from tuser where uname=?";		
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		  bRet = true;	
		}
		rs.close();
		ps.close();
		
		return bRet;
	}
}
