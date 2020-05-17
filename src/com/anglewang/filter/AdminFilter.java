package com.anglewang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.anglewang.constant.IRole;
import com.anglewang.entity.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/back/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Object object = request.getSession().getAttribute("user");
		if(object != null) {
			//�ѵ�¼,�жϵ�¼�ߵ�Ȩ���ǲ��ǹ���Ա
			User user = (User)object;
			if(user.getRole() == IRole.ADMIN) {
				chain.doFilter(req, res);	
			}else {
				request.setAttribute("msg", "Ȩ�޲��㣬�����µ�¼...");
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, res);
			}			
		}else {
			//δ��¼��ת���¼ҳ
			request.setAttribute("msg", "��������ҳ�棬���ȵ�¼...");
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
