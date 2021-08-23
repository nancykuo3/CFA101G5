package com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 設定編碼的 Filter

@WebFilter("/Filter/SetCharacterEncodingFilter")
public class SetCharacterEncodingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SetCharacterEncodingFilter() {
		// TODO Auto-generated constructor stub
	}

	protected String encoding = null;
	protected FilterConfig config = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encoding = config.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 使用 Filter 解決 Query String 之編碼問題
		// request.setCharacterEncoding("特定的字碼集");
		request.setCharacterEncoding(encoding);
		// 將程式控制權交給後續的過濾器
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.encoding = null;
		this.config = null;

	}

}
