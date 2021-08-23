package com.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.model.StoreVO;



@WebFilter(urlPatterns={
		
		"/front-end/store/listOneStore.jsp",
		"/front-end/product/index-prodManagement.jsp",
		"/front-end/store/updateOneStore.jsp",
		
//		活動
		"/front-end/storeEvent/listActivities.jsp",
		"/front-end/storeEvent/addActivities.jsp",
		"/front-end/storeEvent/update_activities_input.jsp",
		"/front-end/shopEvent/listEvent.jsp",
		"/front-end/shopEvent/addEvent.jsp",
		"/front-end/shopEvent/update_event_input.jsp",
		
//		預約系統
		"/front-end/reservation/store/*",
		
//		商品管理
		"/front-end/product/*"
		
////		線上客服
//		"/customerservice/NameServletFe"

})
public class storeFilter extends HttpFilter {

	private static final long serialVersionUID = -6616800865746645499L;

	@Override
    protected void doFilter(
         HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		System.out.println("storeFilter doFilter"); 
		StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
		System.out.println(storeVO);
		if (null == storeVO) {
			// 跳回登入
			String redirectURL = request.getContextPath() + "/front-end/store/storeLogin.jsp";
			response.sendRedirect(redirectURL);
		}else {
			chain.doFilter(request, response);
		}
    }
}