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
				//��ʾ��¼�ɹ�
				if(user.getRole() == IRole.ADMIN) {
					System.out.println(uname + "��¼�ɹ������ǹ���Ա");
				}else if(user.getRole() == IRole.VIP_USER) {
					System.out.println(uname + "��¼�ɹ�������VIP�û�");
				}else if(user.getRole() == IRole.CUSER) {
					System.out.println(uname + "��¼�ɹ���������ͨ�û�");
				}else {
					
				}
			}else {
				//��ʾ��¼ʧ��
				System.out.println(uname + "��¼ʧ�ܣ�����������...");
			}
		} catch (InputEmptyException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			//�׸�����Ա�����쳣��Ϣ
			Log.logger.error(e.getMessage(),e);
			//���û������쳣��Ϣ
			System.out.println("���緱æ���Ժ�����....");
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
			System.out.println(user.getUname() + "ע��ɹ���");
		}catch(UserVoilateExceptoin e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			System.out.println("�����쳣������ϵ����Ա....");
		}
		
	}
	
	public static void main(String[] args) {
		Test.registTest();
	}
		
}
