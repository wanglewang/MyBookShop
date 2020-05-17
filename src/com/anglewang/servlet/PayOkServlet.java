package com.anglewang.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/pay_ok")
public class PayOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayOkServlet() {
        super();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String total = request.getParameter("total");
		request.setAttribute("total", total);
		request.getRequestDispatcher("/WEB-INF/main/pay_ok.jsp").forward(request, response);
	}

}
