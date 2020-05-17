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
			//已登录,判断登录者的权限是不是管理员
			User user = (User)object;
			if(user.getRole() == IRole.ADMIN) {
				chain.doFilter(req, res);	
			}else {
				request.setAttribute("msg", "权限不足，请重新登录...");
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, res);
			}			
		}else {
			//未登录的转向登录页
			request.setAttribute("msg", "访问受限页面，请先登录...");
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
