package com.icss.filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.icss.util.Log;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter("/*")
public class LogFilter implements Filter {
	
	private AtomicLong pageView;

    /**
     * Default constructor. 
     */
    public LogFilter() {
         Log.logger.info("����LogFilter����.....");
         pageView = new AtomicLong(0);
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		Log.logger.info("LogFilter�ͷ���Դ.....");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//Log.logger.info(req.getRequestURI() + " , ǰ�����ش���.....");
		long pv = pageView.incrementAndGet();
		//Log.logger.info("PV=" + pv);
		chain.doFilter(request, response);
		//Log.logger.info(req.getRequestURI() + " , �������ش���******");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		Log.logger.info("LogFilter�����ʼ��.....");
	}

}
