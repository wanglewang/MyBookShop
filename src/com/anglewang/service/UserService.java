package com.anglewang.service;

import com.anglewang.dao.UserDao;
import com.anglewang.entity.User;
import com.anglewang.exception.InputEmptyException;
import com.anglewang.exception.UserVoilateExceptoin;

public class UserService {	
	
	
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
	public void register(User user) throws UserVoilateExceptoin,Exception {
		if(user == null ) {
			throw new InputEmptyException("用户入参不能为空");
		}
		UserDao dao = new UserDao();
		try {
			//查询一下用户名是否重复
			boolean bRet = dao.validUserName(user.getUserName());
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
