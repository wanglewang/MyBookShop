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
import com.anglewang.service.UserService;
import com.anglewang.util.Log;

/**
 * Servlet implementation class PaySvl
 */
@WebServlet("/user/PaySvl")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//������ղ���		
		User user = (User)request.getSession().getAttribute("user");
		Map<String,Integer> shopCar = (Map<String,Integer>)request.getSession().getAttribute("shopCar");
		BookService bookBiz = new BookService();		
		try {
			//��ȡͼ��ļ۸��ۿ۵���Ϣ
			List<Book> books = bookBiz.getBookList(shopCar.keySet());			
			//����ͼ���ܼ�
			double allMoney = 0;
			for(Book bk : books) {
				bk.setBuynum(shopCar.get(bk.getIsbn()));     
				allMoney += bk.getPrice() * bk.getDiscount() * bk.getBuynum() ;
			}
			UserService userBiz = new UserService();
			userBiz.payMoney(user.getUname(), allMoney, books);
			//����ɹ������»Ự�е��û����
			user.setAccount(user.getAccount()-allMoney);
			//����ɹ����ض��򵽳ɹ�ҳ
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath + "user/PayOkSvl?allMoney=" + allMoney );
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.setAttribute("msg", "�����쳣����͹���Ա��ϵ");
			request.getRequestDispatcher("/WEB-INF/error/err.jsp").forward(request, response);
		}
		
	}

}
