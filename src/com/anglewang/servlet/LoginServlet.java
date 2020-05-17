package com.anglewang.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.constant.IRole;
import com.anglewang.entity.User;
import com.anglewang.exception.InputEmptyException;
import com.anglewang.service.UserService;
import com.anglewang.util.Log;

/**
 * Servlet implementation class LoginSvl
 */
@WebServlet(urlPatterns="/LoginSvl",loadOnStartup=1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        Log.logger.info("LoginSvl初始化..............");
    }

	/**
	 * 浏览器地址栏发送的请求是get
	 * GET请求转向登录页
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
	}

	/**
	 * 表单提交一般用post请求
	 * 在post请求中访问数据库，进行登录校验
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserService biz = new UserService();
		try {
			User user = biz.login(uname, pwd);
			if(user != null) {
				//表示登录成功，转向书城主页				
				if(user.getRole() == IRole.ADMIN) {
					request.setAttribute("msg", uname + "登录成功，你是管理员");
				}else if(user.getRole() == IRole.VIP_USER) {
					request.setAttribute("msg", uname + "登录成功，你是VIP用户");
				}else if(user.getRole() == IRole.CUSER) {
					request.setAttribute("msg",uname + "登录成功，你是普通用户");
				}else {					
					request.setAttribute("msg",uname + "登录成功，身份异常");
				}
				//登录成功，把登录对象存在session中
				request.getSession().setAttribute("user",user);
				//登录成功，马上创建一个购物车
				request.getSession().setAttribute("shopCar",new HashMap<String,Integer>());
				request.getRequestDispatcher("/MainSvl").forward(request, response);
			}else {
				//表示登录失败
				request.setAttribute("msg", "登录失败，请重新输入...");
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}
		} catch (InputEmptyException e) {			
			request.setAttribute("msg",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		} catch (Exception e) {
			//抛给程序员看的异常信息
			Log.logger.error(e.getMessage(),e);
			//给用户看的异常信息
			request.getRequestDispatcher("/WEB-INF/error/err.jsp").forward(request, response);
		}		
	}

}
