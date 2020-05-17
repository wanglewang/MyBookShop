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
		//������ղ���		
		User user = (User)request.getSession().getAttribute("user");
		@SuppressWarnings("unchecked")
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		BookService bookService = new BookService();		
		try {
			//��ȡͼ��ļ۸��ۿ۵���Ϣ
			List<Book> books = bookService.getBookList(shopCar.keySet());			
			//����ͼ���ܼ�
			double total = 0;
			for(Book book : books) {
				book.setPurchaseQuantity(shopCar.get(book.getBookId()));     
				total += book.getPrice() * book.getDiscount() * book.getPurchaseQuantity() ;
			}
			OrderService orderService = new OrderService();
			orderService.payMoney(user.getUserName(), total, books);
			//����ɹ������»Ự�е��û����
			user.setBalance(user.getBalance()-total);
			//����ɹ����ض��򵽳ɹ�ҳ
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath + "user/pay_ok?total=" + total );
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "�����쳣����͹���Ա��ϵ");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}
		
	}

}
