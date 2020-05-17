package com.anglewang.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.constant.IRole;
import com.anglewang.entity.User;
import com.anglewang.exception.UserVoilateExceptoin;
import com.anglewang.service.UserService;
import com.anglewang.util.Log;

/**
 * Servlet implementation class RegistSvl
 */
@WebServlet("/RegistSvl")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/regist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUname(request.getParameter("uname"));
		user.setPwd(request.getParameter("pwd"));
		user.setTel(request.getParameter("tel"));
		user.setRole(IRole.CUSER);   //����ע��Ķ�����ͨ�û�
		user.setRtime(new Date());
		UserService biz = new UserService();
		try {
			biz.regist(user);
			//ע��ɹ���ת����¼ҳ
			request.setAttribute("msg", "ע��ɹ��������µ�¼...");
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		}catch(UserVoilateExceptoin e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "�û�����ͻ��������¼��...");
			request.getRequestDispatcher("/WEB-INF/main/regist.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "�����쳣����͹���Ա��ϵ");
			request.getRequestDispatcher("/WEB-INF/error/err.jsp").forward(request, response);
		}		
	}

}
