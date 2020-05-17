package com.anglewang.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.entity.Book;
import com.anglewang.service.BookService;
import com.anglewang.util.Log;

@WebServlet("/user/shop_car")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopCarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		
		@SuppressWarnings("unchecked")
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		//提取图书的完整信息
		BookService bookService = new BookService();
		try {
			List<Book> books = bookService.getBookList(shopCar.keySet());
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/shop_car.jsp").forward(request, response);		
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}	
		
	}



}
