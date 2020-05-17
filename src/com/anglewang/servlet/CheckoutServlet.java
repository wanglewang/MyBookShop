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

@WebServlet("/user/check_out")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		BookService biz = new BookService();
		try {
			List<Book> books = biz.getBookList(shopCar.keySet());			
			for(Book book : books) {
				String name = "bk-" + book.getBookId();
				String purchaseQuantity = request.getParameter(name);  //图书购买数量
				//数量容错处理
				book.setStock(1);                                //默认购买数量为1
				try {
					int num = Integer.parseInt(purchaseQuantity);
					if(num>0) {
						book.setStock(num);	
						//图书购买数量，存到session中的购物里
						shopCar.put(book.getBookId(), num);
					}										
				} catch (Exception e) {
					Log.logger.info("图书数量输入错误....buynum=" + purchaseQuantity );
				}				
			}
			//计算图书总价
			double total = 0;
			for(Book bk : books) {
				total += bk.getPrice() * bk.getDiscount() * bk.getStock();
			}
			request.setAttribute("total", total);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/check_out.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}		
	}

}
