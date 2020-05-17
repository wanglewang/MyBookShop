package com.icss.bll;

import java.util.List;

import com.icss.dll.UserDao;
import com.icss.dto.Book;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.exception.UserVoilateExceptoin;

public class UserBiz {	
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
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();                      //开启事务
			dao.updateUserAccount(uname, -allMoney);
			String orderNO = dao.addOrder(uname, allMoney);
			for(Book bk : books) {
				//添加订单明细
				dao.addOrderDetail(orderNO, bk);
			}
			dao.commit();                               //提交事务
		} catch (Exception e) {
			dao.rollback();                             //回滚事务
			throw e;                                    //二次抛出异常
		}finally {
			dao.closeConnection();
		}		
	}
	
	/**
	 * 校验用户名是否冲突
	 * @param uname
	 * @return  true 表示用户名冲突     false不冲突
	 * @throws Exception
	 */
	public boolean validUname(String uname) throws InputEmptyException,Exception{
		if(uname==null || uname.equals("")) {
			throw new InputEmptyException("用户名不能为空");
		}
		UserDao dao = new UserDao();
		try {
			return dao.validUname(uname);
		} finally {
			dao.closeConnection();
		}		
	}
	

	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd   密码
	 * @return      返回登录成功的用户对象
	 * @throws Exception
	 */
	public User login(String uname , String pwd) throws InputEmptyException,Exception {
		//判断用户名、密码不能为空
		if(uname == null || pwd == null || uname.trim().equals("") || pwd.trim().equals("")) {
			throw new InputEmptyException("用户名或密码为空,请修改 ...");
		}
		//业务流程控制
		//业务规则处理		
	    UserDao dao = new UserDao();
	    try {
	    	return dao.login(uname, pwd);
		} finally {
			dao.closeConnection();
		}   
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws UserVoilateExceptoin,Exception {
		if(user == null ) {
			throw new InputEmptyException("用户入参不能为空");
		}
		UserDao dao = new UserDao();
		try {
			//查询一下用户名是否重复
			boolean bRet = dao.validUserName(user.getUname());
			//插入新用户
			if(bRet) {				
				System.out.println("用户名重复....");
				throw new UserVoilateExceptoin("用户名重复，请重新输入...");
			}else {
				//无重复
				dao.regist(user);
			}	
		} finally {
			dao.closeConnection();
		}
	}

}
