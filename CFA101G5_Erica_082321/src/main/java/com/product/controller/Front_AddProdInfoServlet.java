package com.product.controller;

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
 * Servlet implementation class Front_AddProdInfoServlet
 */
@WebServlet("/product/Front_AddProdInfoServlet")
public class Front_AddProdInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			try {
				String isbn = request.getParameter("isbn");
				System.out.println(isbn);

				String prodName = request.getParameter("prodName");
				System.out.println(prodName);

				String prodLang = request.getParameter("prodLang");
				System.out.println(prodLang);

				String prodVer = request.getParameter("prodVer");
				System.out.println(prodVer);

				ProdInfoVO prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(isbn);
				prodInfoVO.setProdName(prodName);
				prodInfoVO.setProdLang(prodLang);
				prodInfoVO.setProdVer(prodVer);

				ProdInfoService prodInfoSvc = new ProdInfoService();
				prodInfoVO = prodInfoSvc.addProdInfo(isbn, prodName, prodLang, prodVer);
				System.out.println(prodInfoVO);

				// 取得 type的checkbox的值後，迭代進行insert
				String[] typeids = request.getParameterValues("typeid");
				TypeDetService typeDetSvc = new TypeDetService();

				TypeDetVO typeDetVO = null;
				for (String typeid : typeids) {
					System.out.println(typeid);
					Integer typeID = new Integer(typeid);
					typeDetVO = new TypeDetVO();
					typeDetVO = typeDetSvc.addTypeDet(typeID, isbn);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("prodInfoVO", prodInfoVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/addProdInfo.jsp");
					failureView.forward(request, response);
					return; // 程式中斷
				}

				request.setAttribute("prodInfoVO", prodInfoVO);
				request.setAttribute("listTypesByISBN", prodInfoSvc.getTypesByISBN(isbn));

				String url = "/front-end/product/addProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/addProdInfo.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
