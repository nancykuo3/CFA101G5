package login;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.model.OrderService;
import com.order.model.OrderVO;

@WebServlet("/test/login.do")
public class Test_login extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//模擬登入 (用session存storeId)
		if ("check_storeId".equals(action)){
			System.out.println(req.getParameter("storeId"));
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********1.接收請求參數 - 輸入格式的錯誤處理*************/
				String str = req.getParameter("storeId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/order/test_page.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer storeId = null;
				try {
					storeId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("店家編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/order/test_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/********************2.開始查詢資料********************/
				OrderService ordSvc = new OrderService();
				List<OrderVO> list = ordSvc.listAllByStoreId(storeId);
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/order/test_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/******3.查詢完成, 準備轉交(Send the Success view)******/
				HttpSession session = req.getSession();
				session.setAttribute("storeId", storeId);
				String url = "/front-end/store/order/order_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//模擬登出
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/front-end/store/order/test_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
