package com.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;




@WebFilter(urlPatterns={
		
		"/front-end/mem/listOneMem.jsp",
		"/front-end/mem/updateMemInput.jsp",
		
//		討論區
		"/front-end/forum/listArticleCollection.jsp",
		"/front-end/forum/listSelfPostedArticles.jsp",
//		"/front-end/forum/clickTitleShowArticleFe.jsp", // 等 Mike 改好
		"/front-end/forum/postArticle.jsp",
//		"/front-end/forum/forumfe.re",
		"/front-end/forum/ForumArticleFeServletMustLock",
		
//		預約系統
		"/front-end/reservation/mem/*",
		
//		商城
		"/front-end/cart/step2.jsp",
		"/front-end/cart/step3.jsp",
		"/front-end/shop/prod_fav_record.jsp",
		"/front-end/mem/order/order_all.jsp",
		"/front-end/mem/order/pay.jsp"
		
////		線上客服
//		"/customerservice/NameServletFe"

})
public class memFilter extends HttpFilter {

	private static final long serialVersionUID = -6616800865746645499L;

	@Override
    protected void doFilter(
         HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		System.out.println("memFilter doFilter"); 
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		System.out.println(memVO);
		if (null == memVO) {
			// 跳回登入
			String redirectURL = request.getContextPath() + "/front-end/mem/login.jsp";
			response.sendRedirect(redirectURL);
		}else {
			chain.doFilter(request, response);
		}
    }
}