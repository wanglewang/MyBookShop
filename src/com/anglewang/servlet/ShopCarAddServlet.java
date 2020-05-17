package com.anglewang.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/shop_car_add")
public class ShopCarAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopCarAddServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String bookId = request.getParameter("bookId");
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		shopCar.put(bookId, 1);   //默认购买数量为1		
		request.getRequestDispatcher("/user/shop_car").forward(request, response);			
	}
}
