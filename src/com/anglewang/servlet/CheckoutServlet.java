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

/**
 * Servlet implementation class CheckoutSvl
 */
@WebServlet("/user/CheckoutSvl")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		BookService biz = new BookService();
		try {
			List<Book> books = biz.getBookList(shopCar.keySet());			
			for(Book bk : books) {
				String name = "bk-" + bk.getIsbn();
				String buyNum = request.getParameter(name);  //图书购买数量
				//数量容错处理
				bk.setNum(1);                                //默认购买数量为1
				try {
					int num = Integer.parseInt(buyNum);
					if(num>0) {
						bk.setNum(num);	
						//图书购买数量，存到session中的购物里
						shopCar.put(bk.getIsbn(), num);
					}										
				} catch (Exception e) {
					Log.logger.info("图书数量输入错误....buynum=" + buyNum );
				}				
			}
			//计算图书总价
			double allMoney = 0;
			for(Book bk : books) {
				allMoney += bk.getPrice() * bk.getDiscount() * bk.getNum();
			}
			request.setAttribute("allMoney", allMoney);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/checkOut.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/WEB-INF/error/err.jsp").forward(request, response);
		}		
	}

}
