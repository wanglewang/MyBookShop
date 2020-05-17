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
import com.anglewang.entity.User;
import com.anglewang.service.BookService;
import com.anglewang.service.OrderService;
import com.anglewang.util.Log;

@WebServlet("/user/pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//无需接收参数		
		User user = (User)request.getSession().getAttribute("user");
		@SuppressWarnings("unchecked")
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		BookService bookService = new BookService();		
		try {
			//提取图书的价格、折扣等信息
			List<Book> books = bookService.getBookList(shopCar.keySet());			
			//计算图书总价
			double total = 0;
			for(Book book : books) {
				book.setPurchaseQuantity(shopCar.get(book.getBookId()));     
				total += book.getPrice() * book.getDiscount() * book.getPurchaseQuantity() ;
			}
			OrderService orderService = new OrderService();
			orderService.payMoney(user.getUserName(), total, books);
			//付款成功，更新会话中的用户余额
			user.setBalance(user.getBalance()-total);
			//付款成功，重定向到成功页
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath + "user/pay_ok?total=" + total );
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}
		
	}

}
