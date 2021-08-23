package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.product.model.ProdPicVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.store.model.StoreVO;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/product/AddProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
		System.out.println(storeVO.getStoreId());
		Integer storeid = new Integer(storeVO.getStoreId());

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				ProductVO prodVO = null;
				ProdPicVO picVO = null;
				ProductService prodSvc = null;

				String isbn = request.getParameter("isbn");
				System.out.println("ISBN = " + isbn);

				Byte status = new Byte(request.getParameter("status"));
				System.out.println("Status = " + status);

				Integer price = new Integer(request.getParameter("price"));
				System.out.println("Price = " + price);

				Integer prodQty = new Integer(request.getParameter("prodQty"));
				System.out.println("ProdQty = " + prodQty);

				String intro = request.getParameter("intro");
				System.out.println("Intro = " + intro);

				InputStream in = null;
				byte[] firstPic = null;
				byte[] pics = null;
				List<ProdPicVO> picList = new ArrayList<ProdPicVO>();
//				取得圖片
				Collection<Part> parts = request.getParts();
				boolean check = true;
				
				for (Part part : parts) {
					if (part.getSize() != 0) {
						if (part.getContentType() != null) {
							
							if (check) {
								System.out.println("開始新增商品及首圖：");
								
								System.out.println("Size = " + part.getSize());
								System.out.println("Name = " + part.getName());
								System.out.println("ContentType = " + part.getContentType());
								System.out.println();
								
								prodSvc = new ProductService();
								prodVO = new ProductVO();
//								寫入圖片
								in = part.getInputStream();
								firstPic = new byte[in.available()];
								in.read(firstPic);

								prodVO.setIsbn(isbn);
								prodVO.setStoreId(storeid);
								prodVO.setStatus(status);
								prodVO.setPrice(price);
								prodVO.setordQty(prodQty);
								prodVO.setIntro(intro);
								prodVO.setFirstPic(firstPic);

								check = false;
							} else if (!check) {
								System.out.println("開始新增商品圖片：");
								System.out.println("Size = " + part.getSize());
								System.out.println("Name = " + part.getName());
								System.out.println("ContentType = " + part.getContentType());
								System.out.println();
								
								picVO = new ProdPicVO();
								in = part.getInputStream();
								pics = new byte[in.available()];
								in.read(pics);
								picVO.setPic(pics);
								picList.add(picVO);
							}
						}
					}
				}
				in.close();
				Integer prodid = prodSvc.insertWithPics(isbn, storeid, status, price, prodQty, intro, firstPic, picList);

				request.setAttribute("prodid", prodid);
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/addProduct.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
