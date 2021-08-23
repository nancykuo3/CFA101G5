package com.report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report.model.MemReportService;
import com.report.model.MemReportVO;


@WebServlet("/report/memReportServlet")
public class memReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("memReportServlet");
		// 新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("memReportServlet_insert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer memid = null;
				try {
					//capacity
					memid = new Integer(req.getParameter("memid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("會員編號：請勿空白及負數");
				}
				
				Integer memidReported = null;
				try {
					//capacity
					memidReported = new Integer(req.getParameter("memidReported").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("被檢舉會員編號：請勿空白及負數");
				}
				
				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("檢舉文字內容：請勿空白");
				}

				
				MemReportVO memReportVO = new MemReportVO();
				memReportVO.setMemid(memidReported);
				memReportVO.setMemidReported(memidReported);
				memReportVO.setContent(content);
				
				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					errorMsgs.stream().forEach(x->System.out.println(x));
					String url = "/front-end/mem/addStore.jsp";
					res.sendRedirect(req.getContextPath() + url);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemReportService MemReportVOSvc = new  MemReportService();
				memReportVO = MemReportVOSvc.addMemReport(memid, memidReported,content );
				
				String url = "/front-end/mem/listOneMem.jsp";
				res.sendRedirect(req.getContextPath() + url);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				String url = "/front-end/store/index.html";
				res.sendRedirect(req.getContextPath() + url);
			}
		}
		
		// TODO  upda func
		// report id
		// note
		// result
		// 接收以上三個參數 req.getParameter();
		if ("update".equals(action)) {
			System.out.println("memReportServlet_update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************* 1.接收請求參數 ********************/

				Integer reportno = new Integer(req.getParameter("reportno"));
				Byte result = null;
				try {
					System.out.println(req.getParameter("result"));
					result  = new Byte(req.getParameter("result").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("處理結果");
				}

				String note = req.getParameter("note").trim();
				if (note == null || note.trim().length() == 0) {
					errorMsgs.add("處理註記：請勿空白");
				}

				MemReportVO memReportVO = new MemReportVO();
				memReportVO.setResult(result);
				memReportVO.setNote(note);
				memReportVO.setReportno(reportno);

				System.out.println(this.getClass().getCanonicalName()+" memReportVO: "+memReportVO);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memReportVO", memReportVO); //
					String url = "/back-end/report/memReport.jsp";
					res.sendRedirect(req.getContextPath() + url);
				}

				/*********************** 2.開始查詢資料 **********************/
				MemReportService MemReportSvc = new MemReportService();
				MemReportSvc.updateMemReport(result , note , reportno);
				/******** 3.查詢完成,準備轉交(Send the Success view) *********/
				req.setAttribute("MemReportVO", memReportVO);
				String url = "/back-end/report/memReport.jsp";
				res.sendRedirect(req.getContextPath() + url);
				/******************* 其他可能的錯誤處理 ************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report/memReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}