package com.shop.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.product.model.ProductVO;
import com.productFavRecord.model.ProdFavRecordService;
import com.productFavRecord.model.ProdFavRecordVO;
import com.productType.model.TypeVO;
import com.shop.model.ShopDAO;
import com.shop.model.ShopService;

@WebServlet ("/shop/shop.do")
public class ShopServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
				
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		//取得商品首圖
		if ("show_pic".equals(action)) {
			try {
				Integer prodId = new Integer(req.getParameter("prodId"));
				ShopDAO dao = new ShopDAO();
				byte[] pic = dao.getFirstPic(prodId);
				res.setContentType("image/jpeg");
				OutputStream out = res.getOutputStream();
				out.write(pic);
				out.flush();
				out.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//取得商品圖片id
		if ("get_pics_id".equals(action)) {
			try {
				Integer prodId = new Integer(req.getParameter("prodId"));
				ShopService shopSvc = new ShopService();
				List<Integer> picsId = shopSvc.getProdPics(prodId);
				
				JSONObject obj = new JSONObject();
				obj.put("picId", picsId);
				PrintWriter out = res.getWriter();

				out.write(obj.toString());
				System.out.println(obj.toString());
				out.flush();
				out.close();
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//取得商品圖片
		if ("show_prod_pic".equals(action)) {
			try {
				Integer prodPicId = new Integer(req.getParameter("prodPicId"));
				System.out.println(prodPicId);
				ShopService shopSvc = new ShopService();
				byte[] pic = shopSvc.showProdPic(prodPicId);
				System.out.println(pic.length);
				res.setContentType("image/jpeg");
				OutputStream out = res.getOutputStream();
				out.write(pic);
				out.flush();
				out.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("search_prod".equals(action)) {
			try {
				String keyword = req.getParameter("keyword");
				ShopService shopSvc = new ShopService();
				List<ProductVO> list = shopSvc.searchProd(keyword);
				
				req.setAttribute("keyword", keyword);
				req.setAttribute("searchList", list);
				RequestDispatcher view = req.getRequestDispatcher("/front-end/shop/search_prod.jsp");
				view.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("get_type".equals(action)) {
			try {
				ShopService shopSvc = new ShopService();
				Map<String, List<TypeVO>> map = shopSvc.listType();
			
				JSONObject obj = new JSONObject();
				obj.put("listA", map.get("listA"));
				obj.put("listB", map.get("listB"));
				obj.put("listC", map.get("listC"));
			
				PrintWriter out = res.getWriter();

				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		

		if ("get_fav_prod".equals(action)) {
			try {
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				ShopService shopSvc = new ShopService();
				List<ProductVO> list = shopSvc.getFavProd(memVO.getMemId());
				req.setAttribute("favList", list);
				
				RequestDispatcher view = req.getRequestDispatcher("/front-end/shop/prod_fav_record.jsp");
				view.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
			
		if ("add_to_fav".equals(action)) {
			try {
				String status = "0";
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
System.out.println(memId);
				Integer prodId = new Integer(req.getParameter("prodId"));

				ProdFavRecordService favSvc = new ProdFavRecordService();
				ProdFavRecordVO prodFavVO = favSvc.getOneProdFavRec(memId, prodId);
				System.out.println("prodFavVO" + prodFavVO);
				if (prodFavVO == null) {
System.out.println("尚未收藏");
					favSvc.addProdFavRec(memId, prodId);
					status = "1";
				}
				PrintWriter out = res.getWriter();
				
				out.write(status);
				System.out.println(status);
				out.flush();
				out.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("remove_fav".equals(action)) {
			try {
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
System.out.println(memId);
				Integer prodId = new Integer(req.getParameter("prodId"));

				ProdFavRecordService favSvc = new ProdFavRecordService();
				favSvc.deleteProdFavRec(memId, prodId);
				System.out.println("移除成功");

				PrintWriter out = res.getWriter();
				
				out.write("1");

				out.flush();
				out.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
