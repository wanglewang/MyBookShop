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
import com.anglewang.service.UserBiz;
import com.anglewang.util.Log;

/**
 * Servlet implementation class LoginSvl2
 */
@WebServlet("/LoginSvl2")
public class LoginSvl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/login2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		PrintWriter out = response.getWriter();
		try {
			User user = biz.login(uname, pwd);
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
