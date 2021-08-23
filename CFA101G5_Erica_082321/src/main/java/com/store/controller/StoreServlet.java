package com.store.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.email.MailService;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet(name = "StoreServlet", urlPatterns = "/store/StoreServlet")
public class StoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String storeAcc = req.getParameter("storeAcc");
				if (storeAcc == null || storeAcc.trim().length() == 0) {
					errorMsgs.add("帳號：請勿空白");
				}
				String storePwd = req.getParameter("storePwd");
				if (storePwd  == null || storePwd.trim().length() == 0) {
					errorMsgs.add("密碼：請勿空白");
				}
				String storeName = req.getParameter("storeName");
				if (storeName == null || storeName.trim().length() == 0) {
					errorMsgs.add("店家名稱：請勿空白");
				}
				Integer storeGui = null;
				try {
					storeGui = new Integer(req.getParameter("storeGui").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("統一編號：請勿空白");
				}

				String storePic = req.getParameter("storePic");
				if (storePic  == null || storePic.trim().length() == 0) {
					errorMsgs.add("負責人：請勿空白");
				}
				String storeTel = req.getParameter("storeTel");
				if (storeTel  == null || storeTel.trim().length() == 0) {
					errorMsgs.add("電話：請勿空白");
				}
				String storeFax = req.getParameter("storeFax");
				if (storeFax  == null || storeFax.trim().length() == 0) {
					errorMsgs.add("傳真：請勿空白");
				}
				String storeAdd = req.getParameter("storeAdd");
				if (storeAdd  == null || storeAdd.trim().length() == 0) {
					errorMsgs.add("登記地址：請勿空白");
				}
				String storePoc = req.getParameter("storePoc");
				if (storePoc  == null || storePoc.trim().length() == 0) {
					errorMsgs.add("聯絡人：請勿空白");
				}
				String storeConPhone = req.getParameter("storeConPhone");
				if (storeConPhone  == null || storeConPhone.trim().length() == 0) {
					errorMsgs.add("連絡電話：請勿空白");
				}
				String storeConAdd = req.getParameter("storeConAdd");
				if (storeConAdd  == null || storeConAdd.trim().length() == 0) {
					errorMsgs.add("聯絡地址：請勿空白");
				}
				String storeEmail = req.getParameter("storeEmail");
				if (storeEmail  == null || storeEmail.trim().length() == 0) {
					errorMsgs.add("信箱：請勿空白");
				}
				
				String bankAccount = req.getParameter("bankAccount");
				if (bankAccount  == null || bankAccount.trim().length() == 0) {
					errorMsgs.add("轉帳帳號：請勿空白");
				}
				Integer capacity = null;
				try {
					//capacity
					capacity = new Integer(req.getParameter("capacity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("可容納人數：請勿空白及負數");
				}

				Byte dayOff = null;
				try {
					dayOff = new Byte(req.getParameter("dayOff").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("公休日：請勿空白");
				}

				Integer startTime  = null;
				try {
					//capacity
					startTime = new Integer(req.getParameter("startTime").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("營業開始時間：請勿空白及負數");
				}
					
				Integer endTime  = null;
				try {
					//capacity
					endTime = new Integer(req.getParameter("endTime").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("營業結束時間：請勿空白及負數");
				}				
				

				StoreVO storeVO = new StoreVO();
				storeVO.setStoreAcc(storeAcc);
				storeVO.setStorePwd(storePwd);
				storeVO.setStoreName(storeName);
				storeVO.setStoreGui(storeGui);
				storeVO.setStorePic(storePic);
				storeVO.setStoreTel(storeTel);
				storeVO.setStoreFax(storeFax);
				storeVO.setStoreAdd(storeAdd);
				storeVO.setStorePoc(storePoc);
				storeVO.setStoreConPhone(storeConPhone);
				storeVO.setStoreConAdd(storeConAdd);
				storeVO.setStoreEmail(storeEmail);
				storeVO.setBankAccount(bankAccount);
				storeVO.setStartTime(startTime);
				storeVO.setEndTime(endTime);
				


				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					errorMsgs.stream().forEach(x->System.out.println(x));
					String url = "/front-end/store/addStore.jsp";
					res.sendRedirect(req.getContextPath() + url);
				}

				/*************************** 2.開始新增資料 ***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(storeAcc, storePwd, storeName, storeGui, storePic, storeTel, storeFax,
						storeAdd, storePoc, storeConPhone, storeConAdd, storeEmail, bankAccount, capacity, dayOff, 
						startTime,endTime);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				MailService mailSvc = new MailService();
				mailSvc.sendMail(storeEmail, "驗證信",
						req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
								+ req.getContextPath() + "/store/StoreServlet?action=VERIFY_EMAIL&storeId="
								+ storeVO.getStoreId());
				// 送回驗證信件JSP
				String url = "/front-end/store/verifyEmail.jsp";
				res.sendRedirect(req.getContextPath() + url);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				String url = "/front-end/store/addStore.jsp";
				res.sendRedirect(req.getContextPath() + url);
			}
		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("storeId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家會員編號");
				}

				Integer storeId = null;
				try {
					storeId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/index.html");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 2.開始查詢資料
				 *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(storeId);
				if (storeVO == null) {
					errorMsgs.add("查無資料");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/srore/index.html");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO);
				String url = "/front-end/store/listOneStore.jsp";
				res.sendRedirect(req.getContextPath() + url);


				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/index.html");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			System.out.println("update" + 222);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/

				Integer storeId = new Integer(req.getParameter("storeId"));
				
				
				String storePwd = req.getParameter("storePwd").trim();
				if (storePwd == null || storePwd.trim().length() == 0) {
					errorMsgs.add("密碼：請勿空白");
				}

				String storeName = req.getParameter("storeName").trim();
				if (storeName == null || storeName.trim().length() == 0) {
					errorMsgs.add("店家名稱：請勿空白");
				}
				
				Integer storeGui = null;
				try {
					storeGui = new Integer(req.getParameter("storeGui").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("統一編號：請勿空白");
				}

				String storePic = req.getParameter("storePic");
				if (storePic == null || storePic.trim().length() == 0) {
					errorMsgs.add("負責人：請勿空白");
				}
				
				
				String storeTel = req.getParameter("storeTel").trim();
				if (storeTel == null || storeTel.trim().length() == 0) {
					errorMsgs.add("電話：請勿空白");
				}

				String storeFax = req.getParameter("storeFax").trim();
				if (storeFax == null || storeFax.trim().length() == 0) {
					errorMsgs.add("傳真：請勿空白");
				}

				String storeAdd = req.getParameter("storeAdd");
				if (storeAdd  == null || storeAdd.trim().length() == 0) {
					errorMsgs.add("登記地址：請勿空白");
				}
				
				String storePoc = req.getParameter("storePoc").trim();
				if (storePoc == null || storePoc.trim().length() == 0) {
					errorMsgs.add("聯絡人：請勿空白");
				}

				String storeConPhone = req.getParameter("storeConPhone").trim();
				if (storeConPhone == null || storeConPhone.trim().length() == 0) {
					errorMsgs.add("連絡電話：請勿空白");
				}

				String storeConAdd = req.getParameter("storeConAdd").trim();
				if (storeConAdd == null || storeConAdd.trim().length() == 0) {
					errorMsgs.add("聯絡地址：請勿空白");
				}

				String storeEmail = req.getParameter("storeEmail").trim();
				if (storeEmail == null || storeEmail.trim().length() == 0) {
					errorMsgs.add("信箱：請勿空白");
				}

				String bankAccount = req.getParameter("bankAccount").trim();
				if (bankAccount == null || bankAccount.trim().length() == 0) {
					errorMsgs.add("轉帳帳號：請勿空白");
				}

				Integer capacity = null;
				try {
					capacity = new Integer(req.getParameter("capacity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("可容納人數");
				}

				Byte dayOff = null;
				try {
					dayOff = new Byte(req.getParameter("dayOff").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("公休日");
				}

				Integer startTime = new Integer(req.getParameter("startTime").trim());
				Integer endTime = new Integer(req.getParameter("endTime").trim());
				
				
				StoreVO storeVO = new StoreVO();
				storeVO.setStorePwd(storePwd);
				storeVO.setStoreName(storeName);;
				storeVO.setStoreGui(storeGui);
				storeVO.setStorePic(storePic);
				storeVO.setStoreTel(storeTel);
				storeVO.setStoreFax(storeFax);
				storeVO.setStoreAdd(storeAdd);
				storeVO.setStorePoc(storePoc);
				storeVO.setStoreConPhone(storeConPhone);
				storeVO.setStoreConAdd(storeConAdd);
				storeVO.setStoreEmail(storeEmail);
				storeVO.setBankAccount(bankAccount);
				storeVO.setCapacity(capacity);
				storeVO.setDayOff(dayOff);
				storeVO.setStartTime(startTime);
				storeVO.setEndTime(endTime);
				storeVO.setStoreId(storeId);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); //
					String url = "/front-end/store/updateOneStore.jsp";
					res.sendRedirect(req.getContextPath() + url);
				}

				/*********************** 2.開始查詢資料 **********************/
				StoreService storeSvc = new StoreService();
				storeSvc.updateStore(storeVO);
				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				req.setAttribute("StoreVO", storeVO);
				String url = "/front-end/store/listOneStore.jsp";
				res.sendRedirect(req.getContextPath() + url);
				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/updateOneStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求
			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/
				Integer storeId = new Integer(req.getParameter("storeId"));
				Byte accStatus = null;
				try {
					accStatus = new Byte(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("帳號狀態：請勿空白");
				}
				/*********************** 2.開始查詢資料 **********************/
				StoreService memSvc = new StoreService();
				memSvc.updateStore(storeId, accStatus);

				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				String url = "/back-end/store/allStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateMemInput.jsp
				successView.forward(req, res);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/allStore.jsp");
				failureView.forward(req, res);
			}
		}
		if ("VERIFY_EMAIL".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/
				Integer storeId = Integer.valueOf(req.getParameter("storeId"));
				/*********************** 2.開始查詢資料 **********************/
				StoreService storeSvc = new StoreService();
				storeSvc.updateStore(storeId, new Byte((byte) 1));

				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				String url = "/front-end/store/storeLogin.jsp";
				res.sendRedirect(req.getContextPath() + url);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String url = "/front-end/mem/verifyEmail.jsp";
				res.sendRedirect(req.getContextPath() + url);
			}
		}

	}
}