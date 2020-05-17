package com.anglewang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.entity.User;
import com.anglewang.exception.InputEmptyException;
import com.anglewang.service.UserService;
import com.anglewang.util.Log;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		UserService userService = new UserService();
		PrintWriter out = response.getWriter();
		try {
			User user = userService.login(userName, pwd);
			if(user != null) {
				//��¼�ɹ����ѵ�¼�������session��
				request.getSession().setAttribute("user",user);		
				//��¼�ɹ������ϴ���һ�����ﳵ
				request.getSession().setAttribute("shopCar",new HashMap<String,Integer>());
				//������AJAX���������ת��������ת����
				out.print("1");                     //��¼�ɹ�
			}else {
				//��ʾ��¼ʧ��
				out.print("0");						//��¼ʧ��			
			}
		} catch (InputEmptyException e) {
			out.print("-2");						//�û���������Ϊ��		
		} catch (Exception e) {
			//�׸�����Ա�����쳣��Ϣ
			Log.logger.error(e.getMessage(),e);
			//���û������쳣��Ϣ		
			out.print("-1");                          //�쳣
		}	
		out.flush();
		out.close();
	}

}
