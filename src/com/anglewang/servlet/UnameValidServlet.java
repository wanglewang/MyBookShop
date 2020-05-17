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

@WebServlet("/user_name_valid")
public class UnameValidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UnameValidServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		String userName = request.getParameter("userName");
		PrintWriter out = response.getWriter();
		UserService userService = new UserService();
		try {
			boolean bRet = userService.validUname(userName);	
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
