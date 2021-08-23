package com.productInfo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productInfo.model.ProdInfoService;
import com.productInfo.model.ProdInfoVO;
import com.productType.model.TypeDetService;
import com.productType.model.TypeDetVO;

/**
 * Servlet implementation class UpdateProdInfoServlet
 */
@WebServlet("/productInfo/UpdateProdInfoServlet")
public class UpdateProdInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProdInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 來自 listProdInfoByTypeid.jsp 的請求
		if ("getOne_Info_For_Update".equals(action) || "getOne_Info_For_Update_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				String isbn = request.getParameter("isbn");
				ProdInfoService prodInfoSvc = new ProdInfoService();
				ProdInfoVO prodInfoVO = prodInfoSvc.getOneProdInfo(isbn);

				request.setAttribute("prodInfoVO", prodInfoVO);
				String url = "/back-end/product/updateProdInfo.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		// 來自 updateProdInfo.jsp 的請求
		if ("updateProdInfo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				String isbn = request.getParameter("isbn");
				System.out.println(isbn);

				String prodName = request.getParameter("prodName");
				System.out.println(prodName);

				String prodLang = request.getParameter("prodLang");
//				String prodLangReg = "^[(\u4e00-\u9fa5)(\u3001)]$";
//				if (!prodLang.trim().matches(prodLangReg)) {
//					errorMsgs.add("遊戲語言只能是中文！");
//				}
				System.out.println(prodLang);

				String prodVer = request.getParameter("prodVer");
//				String prodVerReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]$";
//				if (!prodVer.trim().matches(prodVerReg)) {
//					errorMsgs.add("遊戲版本只能是中、英文及數字！");
//				}
				System.out.println(prodVer);

				ProdInfoVO prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(isbn);
				prodInfoVO.setProdName(prodName);
				prodInfoVO.setProdLang(prodLang);
				prodInfoVO.setProdVer(prodVer);

				// 取得 type的checkbox的值，delete後再迭代進行insert
				String[] typeids = request.getParameterValues("typeid");
				TypeDetService typeDetSvc = new TypeDetService();
				typeDetSvc.deleteDetByISBN(isbn);

				TypeDetVO typeDetVO = null;
				for (String typeid : typeids) {
					System.out.println(typeid);
					Integer typeID = new Integer(typeid);
					typeDetVO = new TypeDetVO();
					typeDetVO = typeDetSvc.addTypeDet(typeID, isbn);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("prodInfoVO", prodInfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/product/updateProdInfo.jsp");
					failureView.forward(request, response);
					return; // 程式中斷
				}
				ProdInfoService prodInfoSvc = new ProdInfoService();
				prodInfoVO = prodInfoSvc.updateProdInfo(prodName, prodLang, prodVer, isbn);
				System.out.println(prodInfoVO);

				request.setAttribute("prodInfoVO", prodInfoVO);
				request.setAttribute("listTypesByISBN", prodInfoSvc.getTypesByISBN(isbn));

				String url = "/back-end/product/listOneProdInfo.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/updateProdInfo.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
