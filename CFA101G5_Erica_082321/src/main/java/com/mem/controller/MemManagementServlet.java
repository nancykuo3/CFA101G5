package com.mem.controller;

import java.io.IOException;
import java.util.*;
import java.util.Base64.Decoder;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.email.MailService;
import com.mem.model.MemManagementService;
import com.mem.model.MemVO;

@WebServlet("/mem/MemManagementServlet")
public class MemManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		/**********************************************
		 * insert
		 ***********************************************/
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************** 1.接收請求參數 - 輸入格式的錯誤處理 **************/

				String memAcc = request.getParameter("memAcc");
				if (memAcc == null || memAcc.trim().length() == 0) {
					errorMsgs.add("帳號：請勿空白");
				}

				System.out.println(memAcc);

				String memPwd = request.getParameter("memPwd");
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgs.add("密碼：請勿空白");
				}
				System.out.println(memPwd);

				String memName = request.getParameter("memName").trim();
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("姓名：請勿空白");
				}
				System.out.println(memName);

				String memGender = request.getParameter("memGender");
				if (memGender == null || memGender.trim().length() == 0) {
					errorMsgs.add("姓別：請勿空白");
				}
				System.out.println(memGender);

				String memEmail = request.getParameter("memEmail").trim();
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱：請勿空白");
				}
				System.out.println(memEmail);

				String memMobile = request.getParameter("memMobile").trim();
				if (memMobile == null || memMobile.trim().length() == 0) {
					errorMsgs.add("手機：請勿空白");
				}
				System.out.println(memMobile);

				String memAddr = request.getParameter("memAddr").trim();
				if (memAddr == null || memAddr.trim().length() == 0) {
					errorMsgs.add("地址：請勿空白");
				}
				System.out.println(memAddr);

				String memPic_str = request.getParameter("memPic");
				String[] picArr = null;
				String imageString = null;
				Decoder decoder = null;
				byte[] imageByte = null;
				if (memPic_str.equals("")) {
					System.out.println("沒有選擇要修改的圖片!");
					System.out.println("memPic_str: " + memPic_str.toString());
				} else {
					picArr = memPic_str.split(",");
					imageString = picArr[1];
					decoder = Base64.getDecoder();
					imageByte = decoder.decode(imageString);

				}


				MemVO memVO = new MemVO();
				memVO.setMemAcc(memAcc);
				memVO.setMemPwd(memPwd);
				memVO.setMemName(memName);
				memVO.setMemGender(memGender);
				memVO.setMemEmail(memEmail);
				memVO.setMemMobile(memMobile);
				memVO.setMemAddr(memAddr);
				memVO.setMemPic(imageByte);

				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/addMemRegistered.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 *******************/
				MemManagementService memSvc = new MemManagementService();
				memVO = memSvc.addMem(memAcc, memPwd, memName, memGender, memEmail, memMobile, memAddr,
						memVO.getMemPic());

				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				MailService mailSvc = new MailService();
				mailSvc.sendMail(memEmail, "驗證信",
						request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath() + "/mem/MemManagementServlet?action=VERIFY_EMAIL&memId="
								+ memVO.getMemId());
				// 送回驗證信件JSP
				String url = "/front-end/mem/verifyEmail.jsp";
				response.sendRedirect(request.getContextPath() + url);

				/********************** 其他可能的錯誤處理 *********************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/addMemRegistered.jsp");
				failureView.forward(request, response);
			}
		}

		/*****************************************
		 * getOne_For_Update
		 ******************************************/

		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/
				Integer memId = new Integer(request.getParameter("memId"));
				Byte accStatus = null;
				try {
					accStatus = new Byte(request.getParameter("status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("帳號狀態：請勿空白");
				}
				/*********************** 2.開始查詢資料 **********************/
				MemManagementService memSvc = new MemManagementService();
				memSvc.updateMem(memId, accStatus);

				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				String url = "/back-end/mem/allMem.jsp";
				response.sendRedirect(request.getContextPath() + url);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/mem/allMem.jsp");
				failureView.forward(request, response);
			}
		}
		/****************************************
		 * * getOne_For_Display
		 ******************************************/
		if ("getOne_For_Display".equals(action)) { // 來自selectPage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************** 1.接收請求參數 - 輸入格式的錯誤處理 *************/
				String str = request.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				Integer memId = null;
				try {
					memId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/index.html");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*********************** 2.開始查詢資料 ***********************/
				MemManagementService memSvc = new MemManagementService();
				MemVO memVO = memSvc.getOneMem(memId);
				if (memVO == null) {
					errorMsgs.add("查無資料");
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/index.html");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				memVO.setMemPicSrc(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/images?tableName=MEM&colName=MEM_ID&queryId?" + memVO.getMemId()
						+ "&queryImg=MEM_PIC");
				System.out.println(memVO.toString());
				System.out.println("============");
				/*********** 3.查詢完成,準備轉交(Send the Success view) **********/
				request.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/listOneMem.jsp";
				response.sendRedirect(request.getContextPath() + url);

				/********************** 其他可能的錯誤處理 ***********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/index.html");
				failureView.forward(request, response);
			}
		}
		/*************************************************
		 * delete
		 **********************************************/
		/****************************************************************************************************/
		if ("delete".equals(action)) { // 來自listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer memId = new Integer(request.getParameter("memId"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemManagementService memSvc = new MemManagementService();
				memSvc.deleteMem(memId);
				System.out.println("delete 5000000055");
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/mem/listAllMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("front-end/mem/listAllMem.jsp");
				failureView.forward(request, response);
			}
		}

		/****************************************
		 * * getOnedata_For_Update
		 *****************************/
		/************************************************************************************************/
		if ("getOneData_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/
				Integer memId = new Integer(request.getParameter("memId"));

				/*********************** 2.開始查詢資料 **********************/
				MemManagementService memSvc = new MemManagementService();
				MemVO memVO = memSvc.getOneMem(memId);
				System.out.println("getOneData_For_Update 62000008");
				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				request.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/updateMemDataInput.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 updateMemInput.jsp
				successView.forward(request, response);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(request, response);
			}
		}

		/*****************************************************
		 * Update
		 ****************************************/
		/************************************************************************************************/
		if ("update".equals(action)) {

			System.out.println("MemManagementServlet_update");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/************************* 1.接收請求參數 ********************/
				Integer memId = new Integer(request.getParameter("memId"));

				String memPwd = request.getParameter("memPwd");

				String memName = request.getParameter("memName").trim();
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("姓名：請勿空白");
				}

				String memGender = request.getParameter("memGender").trim();
				if (memGender == null || memGender.trim().length() == 0) {
					errorMsgs.add("姓別：請勿空白");
				}

				String memEmail = request.getParameter("memEmail").trim();
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱：請勿空白");
				}

				String memMobile = request.getParameter("memMobile").trim();
				if (memMobile == null || memMobile.trim().length() == 0) {
					errorMsgs.add("手機：請勿空白");
				}

				String memAddr = request.getParameter("memAddr").trim();
				if (memAddr == null || memAddr.trim().length() == 0) {
					errorMsgs.add("地址：請勿空白");
				}

				String memPic_str = request.getParameter("memPic");

				String[] picArr = null;
				String imageString = null;
				Decoder decoder = null;
				byte[] imageByte = null;
				if (memPic_str.equals("")) {
					System.out.println("沒有選擇要修改的圖片!");
					System.out.println("memPic_str: " + memPic_str.toString());
				} else {
					picArr = memPic_str.split(",");
					imageString = picArr[1];
					decoder = Base64.getDecoder();
					imageByte = decoder.decode(imageString);

				}


				MemVO memVO = new MemVO();

				memVO.setMemPwd(memPwd);
				memVO.setMemName(memName);
				memVO.setMemGender(memGender);
				memVO.setMemEmail(memEmail);
				memVO.setMemMobile(memMobile);
				memVO.setMemAddr(memAddr);
				memVO.setMemPic(imageByte);
				memVO.setMemId(memId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO); //
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/updateMemInput.jsp");
					failureView.forward(request, response);
					return;
				}

				/*********************** 2.開始查詢資料 **********************/
				MemManagementService memSvc = new MemManagementService();
				memSvc.updateMem(memVO);
				System.out.println("updateMem 62000008");

				memVO.setMemPicSrc(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/images?tableName=MEM&colName=MEM_ID&queryId?" + memVO.getMemId()
						+ "&queryImg=MEM_PIC");

				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				String url = "/front-end/mem/listOneMem.jsp";
				response.sendRedirect(request.getContextPath() + url);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/updateMemInput.jsp");
				failureView.forward(request, response);
			}
		}

		if ("VERIFY_EMAIL".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/
				Integer memId = Integer.valueOf(request.getParameter("memId"));
				/*********************** 2.開始查詢資料 **********************/
				MemManagementService memSvc = new MemManagementService();
				memSvc.updateMem(memId, new Byte((byte) 1));

				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				String url = "/front-end/mem/login.jsp";
				response.sendRedirect(request.getContextPath() + url);

				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String url = "/front-end/mem/verifyEmail.jsp";
				response.sendRedirect(request.getContextPath() + url);
			}
		}

	}
}