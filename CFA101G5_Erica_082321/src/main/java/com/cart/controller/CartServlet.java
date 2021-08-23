package com.cart.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.order.model.OrdDetVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.shop.model.ShopDAO;
import com.shop.model.ShopService;

@WebServlet("/cart/cart.do")
public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		@SuppressWarnings("unchecked")
		Vector<ProductVO> list = (Vector<ProductVO>) session.getAttribute("list");

		// 新增商品至購物車
		if ("add_to_cart".equals(action) || "go_to_cart".equals(action)) {
			boolean match = false;

			Integer prodId = new Integer(req.getParameter("prodId"));
			Integer storeId = new Integer(req.getParameter("storeId"));
			System.out.println("request to add: " + prodId + "," + storeId);
			ShopService shopSvc = new ShopService();

			JSONObject obj = new JSONObject();
			try {
				// 新增第一個商品
				ProductVO productVO = shopSvc.getProdInfo(prodId);
				if (list == null) {
					list = new Vector<ProductVO>();
					productVO.setordQty(1);
					list.add(productVO);
				} else {
					// 購物車內已有商品: 檢查品項是否重複
					for (ProductVO existVO : list) {
						System.out.println("購物車已存在的商品" + existVO.getProdId());
						//
						if (!existVO.getStoreId().equals(storeId)) {
							System.out.println("不同店家商品");
							obj.put("status", "return");
							PrintWriter out = res.getWriter();

							out.write(obj.toString());
							out.flush();
							out.close();
							return;
						}
						// 新增重複商品->購買數量+1
						if (existVO.getProdId().equals(prodId)) {
//						existVO.setordQty(existVO.getordQty()+1);
							match = true;
							System.out.println("新稱重複商品");
						}
					}
					// 新增不重複商品
					if (!match) {
						productVO.setordQty(1);
						list.add(productVO);
						System.out.println("新稱不重複商品");
					}
				}
				session.setAttribute("list", list);
				obj.put("status", "success");
				PrintWriter out = res.getWriter();

				out.write(obj.toString());
				System.out.println(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println("error");
				throw new ServletException(e);
			}
		}
		//新增不同店家商品前 先清空購物車 
		if ("remove_before_add".equals(action)) {
			try {
			list.clear();
			System.out.println("list.size()" + list.size());
			Integer prodId = new Integer(req.getParameter("prodId"));
			ShopService shopSvc = new ShopService();
			ProductVO productVO = shopSvc.getProdInfo(prodId);
			productVO.setordQty(1);
			list.add(productVO);
			
			PrintWriter out = res.getWriter();

			out.write('1');
			out.flush();
			out.close();
			} catch (Exception e){
				throw new ServletException (e);
			}
		}

		// 更改數量 或 數量+1、-1 (ajax)
		if ("add_one".equals(action) || "sub_one".equals(action) || "adj_ordQty".equals(action)) {
			try {
				Integer prodId = new Integer(req.getParameter("prodId"));
				Integer curr_ordQty = new Integer(req.getParameter("ordQty"));
				// 去資料庫查 (庫存量)
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodId);
				Integer prodQty = prodVO.getProdQty();
	
				JSONObject obj = new JSONObject();

				// 更改數量
				if ("adj_ordQty".equals(action)) {
					// 從list中找出要更改數量的商品
					for (ProductVO existVO : list) {
						if (existVO.getProdId().equals(prodId)) {
							// 如果欲修改的數量小於等於庫存量 -> 修改數量
							if (curr_ordQty > 0 && curr_ordQty <= prodQty) {
								existVO.setordQty(curr_ordQty);
								obj.put("ordQty", existVO.getordQty());
								break;
							} else if (curr_ordQty == 0) {
								obj.put("status", "return");
								obj.put("ordQty", existVO.getordQty());
								break;
							} else {
								obj.put("status", "failure");
								obj.put("ordQty", existVO.getordQty());
								break;
							}
						}
					}
					obj.put("prodQty", prodQty);
				}
				
				// 數量+1
				if ("add_one".equals(action)) {
					// 從list中找出要+1的商品
					for (ProductVO existVO : list) {
						if (existVO.getProdId().equals(prodId)) {
							// 如果購買數量小於庫存量 -> 購買數量+1
							if (existVO.getordQty() < prodQty) {
								existVO.setordQty(curr_ordQty + 1);
								obj.put("status", "success");
							}
							// 如果購買數量大於等於庫存量 -> 不+1 ＆ 於前端發錯誤訊息
							obj.put("prodQty", prodQty);
							obj.put("ordQty", existVO.getordQty());
							break;
						}
					}
				}
				// 數量-1
				else if ("sub_one".equals(action)) {
					for (ProductVO existVO : list) {
						if (existVO.getProdId().equals(prodId)) {
							existVO.setordQty(curr_ordQty - 1);
							obj.put("prodQty", prodVO.getProdQty());
							obj.put("ordQty", existVO.getordQty());
							break;
						}
					}
				}
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println("error");
				throw new ServletException(e);
			}
		}
		//清空購物車
		if ("clear_all".equals(action)) {
			try {
				session.removeAttribute("list");
				res.sendRedirect("/CFA101G5/front-end/cart/step1.jsp");
			} catch (Exception e) {
				System.out.print("error");
				throw new ServletException(e);
			}
		}

		if ("delete_item".equals(action)) {
			try {
				Integer prodId = new Integer(req.getParameter("prodId"));
				for (ProductVO existVO : list) {
					if (existVO.getProdId().equals(prodId)) {
						list.remove(existVO);
						break;
					}
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//前往結帳頁面
		if ("check".equals(action)) {
			Integer storeId = null;
			String storeName = null;
			try {
				// 檢查登入狀態
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				if (memVO == null) {
					req.setAttribute("requestURL", req.getParameter("requestURL"));
					RequestDispatcher view = req.getRequestDispatcher("/front-end/mem/order/test_page.jsp");
					view.forward(req, res);
					return;
				}
				//未登入 ->導回登入頁面
				if (list == null) {
					res.sendRedirect("/CFA101G5/front-end/cart/step1.jsp");
					return;
				}

				ProductService prodSvc = new ProductService();
				for (ProductVO existVO : list) {
					// 檢查庫存量
					ProductVO prodVO = prodSvc.getOneProduct(existVO.getProdId());
					if (existVO.getordQty() > prodVO.getProdQty()) {
						res.sendRedirect("/CFA101G5/front-end/cart/step1.jsp");
						return;
					}
					existVO.setPrice(prodVO.getPrice());
					storeId = existVO.getStoreId();
					storeName = existVO.getStoreName();
				}
				req.setAttribute("storeId", storeId);
				req.setAttribute("storeName", storeName);
				RequestDispatcher view = req.getRequestDispatcher("/front-end/cart/step2.jsp");
				view.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//送出訂單
		if ("confirm_check".equals(action)) {
			try {
				OrderVO ordVO = new OrderVO();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				if (memVO == null) {
					req.setAttribute("requestURL", req.getParameter("requestURL"));
					RequestDispatcher view = req.getRequestDispatcher("/front-end/mem/order/test_page.jsp");
					view.forward(req, res);
					return;
				}
				ordVO.setMemId(memVO.getMemId());
				Integer storeId = new Integer (req.getParameter("storeId"));
				ordVO.setStoreId(storeId); 
				String shipType = req.getParameter("shipType");
				Byte ship = Byte.parseByte(shipType.substring(3, 4));
				ordVO.setShipment(ship);

				String name = req.getParameter("name");
				ordVO.setOrdName(name);
				System.out.println(name);

				String mobile = req.getParameter("mobile");
				ordVO.setOrdMobile(mobile);
				System.out.println(mobile);

				if (req.getParameter("cvs") != "") {
					String cvs = req.getParameter("cvs");
					ordVO.setOrdAddr(cvs);
				} else {
					String city = req.getParameter("city");
					String dist = req.getParameter("dist");
					String addr = req.getParameter("addr");
					StringBuffer sb = new StringBuffer(city).append(dist).append(addr);
					ordVO.setOrdAddr(sb.toString());
					System.out.println(sb.toString());
				}
				Integer shipFee = new Integer(req.getParameter("shipfee"));
				ordVO.setShipFee(shipFee);
				Integer amount = new Integer(req.getParameter("amount"));
				ordVO.setAmount(amount);
				System.out.println("shipFee: " + shipFee + ", amount: " + amount);

				Byte payType = new Byte(req.getParameter("payType"));
				ordVO.setPayment(payType);
				String memo = req.getParameter("memo");
				ordVO.setMemo(memo);
				if (!payType.equals((byte) 2)) {
					ordVO.setStatus((byte) 0);
				} else {
					ordVO.setStatus((byte) 2);
				}

				List<OrdDetVO> ordList = new ArrayList<OrdDetVO>();
				Integer count = new Integer(req.getParameter("count"));
				for (int i = 1; i <= count; i++) {
					OrdDetVO ordDetVO = new OrdDetVO();
					Integer prodId = new Integer(req.getParameter("prodId" + i));
					ordDetVO.setProdId(prodId);
					Integer ordQty = new Integer(req.getParameter("ordQty" + i));
					ordDetVO.setOrdQty(ordQty);
					Integer price = new Integer(req.getParameter("price" + i));
					ordDetVO.setOrdPrice(price);
					ordList.add(ordDetVO);
					System.out.println(prodId + ", " + ordQty + ", " + price);
				}

				OrderService ordSvc = new OrderService();
				Integer ordId = ordSvc.insertWithOrdDet(ordVO, ordList);
				//清空購物車session
				session.removeAttribute("list");
				System.out.println("memIdsession" + session.getAttribute("memId"));
				req.setAttribute("status", 1);
				req.setAttribute("ordId", ordId);

				RequestDispatcher view = req.getRequestDispatcher("/front-end/cart/step3.jsp");
				view.forward(req, res);

			} catch (Exception e) {
				req.setAttribute("status", 0);
				System.out.println("新增訂單失敗");
				RequestDispatcher view = req.getRequestDispatcher("/front-end/cart/step3.jsp");
				view.forward(req, res);
			}
		}
	}
}
