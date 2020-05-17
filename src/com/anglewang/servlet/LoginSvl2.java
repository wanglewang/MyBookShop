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
				//登录成功，把登录对象存在session中
				request.getSession().setAttribute("user",user);		
				//登录成功，马上创建一个购物车
				request.getSession().setAttribute("shopCar",new HashMap<String,Integer>());
				//必须由AJAX引擎进行跳转，服务器转向不行
				out.print("1");                     //登录成功
			}else {
				//表示登录失败
				out.print("0");						//登录失败			
			}
		} catch (InputEmptyException e) {
			out.print("-2");						//用户名或密码为空		
		} catch (Exception e) {
			//抛给程序员看的异常信息
			Log.logger.error(e.getMessage(),e);
			//给用户看的异常信息		
			out.print("-1");                          //异常
		}	
		out.flush();
		out.close();
	}

}
