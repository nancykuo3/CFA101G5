package com.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdministratorVO;




//"/LoginServlet"
@WebFilter(urlPatterns={
//		---------------XXXX----------------
//		"/LoginServlet",
		"/back-end/admin/adimMgntMemAndStore.jsp",
		"/admin/adminLoginServlet",
		"/back-end/mem/allMem.jsp",
		"/back-end/store/allStore.jsp",
		"/back-end/report/memRepore.jsp",
		"/back-end/report/storeRepore"
//		------------------------------


})
public class adminFilter extends HttpFilter {

	private static final long serialVersionUID = -6616800865746645499L;

	@Override
    protected void doFilter(
         HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		System.out.println("adminFilter doFilter"); 
		
		AdministratorVO adminVO = (AdministratorVO) session.getAttribute("administratorVO");
		
		System.out.println(adminVO);
		if (null == adminVO) {
			// 跳回登入
			String redirectURL = request.getContextPath() + "/back-end/admin/adminLogin.jsp";
			response.sendRedirect(redirectURL);
		}else {
			chain.doFilter(request, response);
		}
    }
}