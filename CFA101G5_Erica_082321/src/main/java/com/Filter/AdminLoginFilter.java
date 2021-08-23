package com.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminLoginFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		if(uri.contains("/adminLogin.jsp") || uri.contains("/LoginServlet") || uri.contains("/css") || uri.contains("/js") || uri.contains("/img") || uri.contains("/image") || uri.contains(".css") || uri.contains("/back-end/forum/forum.rd") || uri.contains("/back-end/forum/forumreply.rd")) {
			chain.doFilter(request, response);			
		} else {			
			HttpSession session = req.getSession();
			if(session.getAttribute("administratorVO")!= null) {
				chain.doFilter(request, response);
			} else {
				HttpServletResponse res = (HttpServletResponse) response;
				String url ="/back-end/adminLogin.jsp";
				res.sendRedirect(req.getContextPath() + url);
			}
		}
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
