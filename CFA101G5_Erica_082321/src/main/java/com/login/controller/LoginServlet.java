package com.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdministratorService;
import com.admin.model.AdministratorVO;
import com.function.model.FunctionVO;
import com.mem.model.MemManagementService;
import com.mem.model.MemVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2446821516777883745L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("mem".equals(action)) {
			HttpSession session = request.getSession();
//			if (session.getAttribute("memVO") != null) {
//				session.invalidate();
//				System.out.println("session刪除成功");
//			}
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String MEM_ACC = request.getParameter("acc");
			System.out.println(MEM_ACC);
			String MEM_PWD = request.getParameter("pwd");
			System.out.println(MEM_PWD);
			MemManagementService svc = new MemManagementService();
			MemVO memVO = svc.getmem(MEM_ACC, MEM_PWD);
			System.out.println(memVO);

			if (memVO == null) {
				errorMsgs.add("帳號密碼輸入錯誤");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/login.jsp");
				failureView.forward(request, response);
				return;
			}

			if (MEM_ACC.equals(memVO.getMemAcc()) && MEM_PWD.equals(memVO.getMemPwd())) {
				if (memVO.getAccStatus() == 1) {
					session.setAttribute("memVO", memVO);
					System.out.println("session=" + session);
					System.out.println("登入成功");
					String url = "/front-end/index-front.jsp";
					response.sendRedirect(request.getContextPath() + url);
				} else if (memVO.getAccStatus() == 2) {
					errorMsgs.add("帳號已停權");
				} else if (memVO.getAccStatus() == 3) {
					errorMsgs.add("帳號已失效");
				} else {
					errorMsgs.add("帳號未啟用");
				}
			} else {
				errorMsgs.add("輸入錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/login.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		if ("store".equals(action)) {
			HttpSession session = request.getSession();
//			if (session.getAttribute("StoreVO") != null) {
//				session.invalidate();
//				System.out.println("session刪除成功");
//			}
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String STORE_ACC = request.getParameter("acc");
			System.out.println(STORE_ACC);
			String STORE_PWD = request.getParameter("pwd");
			System.out.println(STORE_PWD);
			StoreService svc = new StoreService();
			StoreVO storeVO = svc.getstore(STORE_ACC, STORE_PWD);

			if (storeVO == null) {
				errorMsgs.add("輸入錯誤");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/store/storeLogin.jsp");
				failureView.forward(request, response);
				return;
			}

			if (STORE_ACC.equals(storeVO.getStoreAcc()) && STORE_PWD.equals(storeVO.getStorePwd())) {
				if (storeVO.getAccStatus() == 1) {
					session.setAttribute("StoreVO", storeVO);
					System.out.println("session=" + session);
					System.out.println("登入成功");
					String url = "/front-end/store/listOneStore.jsp";
					response.sendRedirect(request.getContextPath() + url);
				} else if (storeVO.getAccStatus() == 2) {
					errorMsgs.add("帳號已停權");
				} else if (storeVO.getAccStatus() == 3) {
					errorMsgs.add("帳號已失效");
				} else {
					errorMsgs.add("帳號未啟用");
				}
			} else {
				errorMsgs.add("輸入錯誤");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/store/storeLogin.jsp");
				failureView.forward(request, response);
				return;
			}
		}

		if ("admin".equals(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("administratorVO") != null) {
				session.removeAttribute("administratorVO");
				System.out.println("session刪除成功");
			}
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String admin_acc = request.getParameter("acc");
			System.out.println(admin_acc);
			String admin_pwd = request.getParameter("pwd");
			AdministratorService svc = new AdministratorService();
			AdministratorVO administratorVO = svc.getAdmin(admin_acc, admin_pwd);
			System.out.println(administratorVO);
			if (null != administratorVO && admin_acc.equals(administratorVO.getAdminacc()) && admin_pwd.equals(administratorVO.getAdminpwd())) {
				if (administratorVO.getAdminStatus() == 1) {
					session.setAttribute("administratorVO", administratorVO);
					System.out.println("sassion=" + session);
					System.out.println("admin 登入成功");
					System.out.println(administratorVO.getAdminid());
					//取得管理員權限
					List<FunctionVO> funcList = svc.getFuncsByAdminid(administratorVO.getAdminid());
					StringBuilder adminfunc = new StringBuilder("");
					for(FunctionVO func : funcList) {
						adminfunc.append(func.getFuncid().toString());
					}
					System.out.println(adminfunc.toString());
					session.setAttribute("adminfunc", adminfunc.toString());
					//登錄完成跳轉到首頁
					String url = "/back-end/back-end-index.jsp";
					response.sendRedirect(request.getContextPath() + url);
				} else {
					errorMsgs.add("權限不夠");
				}
			} else {
				errorMsgs.add("輸入錯誤");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/adminLogin.jsp");
				failureView.forward(request, response);
				return;
			}
		}
	}
}