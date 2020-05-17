package com.anglewang.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/shop_car_remove")
public class ShopCarRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopCarRemoveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		//从sessin中的购物车移除某本书
		@SuppressWarnings("unchecked")
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		shopCar.remove(bookId);
		request.getRequestDispatcher("/user/shop_car").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
