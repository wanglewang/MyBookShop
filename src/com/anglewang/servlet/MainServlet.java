package com.anglewang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.entity.Book;
import com.anglewang.service.BookService;
import com.anglewang.util.Log;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		BookService bookService = new BookService();
		try {
			List<Book> books = bookService.getMainBooks();	
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}		
	}

	

}
