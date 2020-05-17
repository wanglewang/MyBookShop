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
				String purchaseQuantity = request.getParameter(name);  //ͼ�鹺������
				//�����ݴ���
				book.setStock(1);                                //Ĭ�Ϲ�������Ϊ1
				try {
					int num = Integer.parseInt(purchaseQuantity);
					if(num>0) {
						book.setStock(num);	
						//ͼ�鹺���������浽session�еĹ�����
						shopCar.put(book.getBookId(), num);
					}										
				} catch (Exception e) {
					Log.logger.info("ͼ�������������....buynum=" + purchaseQuantity );
				}				
			}
			//����ͼ���ܼ�
			double total = 0;
			for(Book bk : books) {
				total += bk.getPrice() * bk.getDiscount() * bk.getStock();
			}
			request.setAttribute("total", total);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/check_out.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "�����쳣����͹���Ա��ϵ");
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}		
	}

}
