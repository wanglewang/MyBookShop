package com.anglewang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anglewang.exception.InputEmptyException;
import com.anglewang.service.UserService;

/**
 * Servlet implementation class UnameValidSvl
 */
@WebServlet("/UnameValidSvl")
public class UnameValidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnameValidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		String uname = request.getParameter("uname");
		PrintWriter out = response.getWriter();
		UserService biz = new UserService();
		try {
			boolean bRet = biz.validUname(uname);	
			if(bRet) {
				out.print("1");      //�û�����ͻ
			}else {
				out.print("0");      //�û���������
			}
		}catch(InputEmptyException e) {	
			out.print("-2");          //��ʾ����Ϊ��
		} catch (Exception e) {
			out.print("-1");         //�쳣����-1
		}		
		out.flush();
		out.close();
	}


}