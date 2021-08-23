package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProdPicService;
import com.product.model.ProdPicVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class UpdateFirstPicServlet
 */
@WebServlet("/product/UpdatePicsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class UpdatePicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");

		if ("updateFirstPic".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");
			try {
//			取得商品流水號
				Integer prodid = new Integer(request.getParameter("prodid"));
//			取得照片
				Part part = request.getPart("firstPic");
//			開啟水管
				InputStream in = null;

				if (part != null) {
					System.out.println("Size = " + part.getSize());
					System.out.println("Name = " + part.getName());
					System.out.println("ContentType = " + part.getContentType());

// 				寫入圖片
					in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);

//				將資料放入 prodVO
					ProductVO prodVO = new ProductVO();
					prodVO.setFirstPic(buf);
					prodVO.setProdId(prodid);

//				呼叫 Service 進行更新
					ProductService prodSvc = new ProductService();
					prodVO = prodSvc.updateFirstPic(buf, prodid);

//				關閉水管
					in.close();

					request.setAttribute("productVO", prodVO);

					RequestDispatcher successView = request.getRequestDispatcher(requestURL);
					successView.forward(request, response);
				}

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		if ("update_5_ProdPics".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
//				取得商品流水號
				Integer prodid = new Integer(request.getParameter("prodid"));

				ProdPicService picSvc = new ProdPicService();
				List<ProdPicVO> list = picSvc.getAll();

//				呼叫方法先刪除全部圖片
				picSvc.deleteProdPic(prodid);
				
//				取得圖片
				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
//					System.out.println(part);
//				開啟水管
					InputStream in = null;
					ProdPicVO picVO = new ProdPicVO();

					if (part.getSize() != 0) {
						if (part.getContentType() != null) {

//							ProdPicService picSvc = new ProdPicService();
//							List<ProdPicVO> list = picSvc.getAll();
//							if (parts.size() == 5) {
////							呼叫方法先刪除全部圖片
//								picSvc.deleteProdPic(prodid);
//							}

							System.out.println("Size = " + part.getSize());
							System.out.println("Name = " + part.getName());
							System.out.println("ContentType = " + part.getContentType());
							System.out.println();

// 						寫入圖片
							in = part.getInputStream();
							byte[] buf = new byte[in.available()];
							in.read(buf);

//						將資料放入 picVO

							picVO.setProdId(prodid);
							picVO.setPic(buf);

//						呼叫 Service 進行insert
							picVO = picSvc.addProdPic(prodid, buf);

//						關閉水管
							in.close();
						}
					}
				}

				String url = "/front-end/product/index-prodManagement.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}
	}

}
