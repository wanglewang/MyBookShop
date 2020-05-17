package com.icss.ui;

import java.util.Date;

import com.icss.bll.UserBiz;
import com.icss.constant.IRole;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.exception.UserVoilateExceptoin;
import com.icss.util.Log;

public class Test {
	
	public static void loginTest() {
		UserBiz biz = new UserBiz();
		String uname = "admin";
		String pwd = "123456";
		try {
			User user = biz.login(uname, pwd);
			if(user != null) {
				//表示登录成功
				if(user.getRole() == IRole.ADMIN) {
					System.out.println(uname + "登录成功，你是管理员");
				}else if(user.getRole() == IRole.VIP_USER) {
					System.out.println(uname + "登录成功，你是VIP用户");
				}else if(user.getRole() == IRole.CUSER) {
					System.out.println(uname + "登录成功，你是普通用户");
				}else {
					
				}
			}else {
				//表示登录失败
				System.out.println(uname + "登录失败，请重新输入...");
			}
		} catch (InputEmptyException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			//抛给程序员看的异常信息
			Log.logger.error(e.getMessage(),e);
			//给用户看的异常信息
			System.out.println("网络繁忙，稍后再试....");
		}
		
	}
	public static void registTest() {
		UserBiz biz = new UserBiz();
		User user = new User();
		user.setUname("tom2");
		user.setPwd("123");
		user.setRole(IRole.CUSER);
		user.setRtime(new Date());
		try {
			biz.regist(user);
			System.out.println(user.getUname() + "注册成功！");
		}catch(UserVoilateExceptoin e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			System.out.println("网络异常，请联系管理员....");
		}
		
	}
	
	public static void main(String[] args) {
		Test.registTest();
	}
		
}
