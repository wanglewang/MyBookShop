package com.anglewang.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.entity.Book;
import com.anglewang.service.BookService;
import com.anglewang.util.Log;

@WebServlet("/book_info")
public class BookInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public BookInfoServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		BookService bookService = new BookService();
		try {
			Book book = bookService.getBookInfo(bookId);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/WEB-INF/main/book_info.jsp").forward(request, response);			
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
