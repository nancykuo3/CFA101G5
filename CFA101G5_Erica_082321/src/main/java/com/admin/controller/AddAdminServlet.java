package com.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.admin.model.AdministratorService;
import com.admin.model.AdministratorVO;

@WebServlet("/admin/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST請求已進入");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");

		if ("testAdmin".equals(action)) {
			Integer inputAdminid = new Integer(request.getParameter("inputAdminid"));

			AdministratorService adminSvc = new AdministratorService();
			List<AdministratorVO> list = new ArrayList<AdministratorVO>();
			list = adminSvc.getAll();

			JSONObject obj = new JSONObject();
			String result = null;

			boolean test = true;
			try {
				for (AdministratorVO item : list) {
					if (test) {
						System.out.println("資料庫拿到的管理員編號 = " + item.getAdminid());
						if (inputAdminid.equals(item.getAdminid())) {
							System.out.println("迴圈進來了");
							result = "fail";
							obj.put("result", result);
							test = false;
						}
					}
				}
				if (test == true) {
					result = "success";
					obj.put("result", result);
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println(obj.toString());

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
		}

		if ("insert".equals(action)) {

			try {
				Integer adminid = new Integer(request.getParameter("inputAdminid"));
				System.out.println("adminid = " + adminid);
				String adminacc = request.getParameter("inputAdminAcc");
				System.out.println("adminAcc = " + adminacc);
				String adminpwd = request.getParameter("inputAdminPwd");
				System.out.println("adminPwd = " + adminpwd);
				Byte adminStatus = new Byte(request.getParameter("inputAdminStatus"));
				System.out.println("adminStatus = " + adminStatus);
				String adminName = request.getParameter("inputAdminName");
				System.out.println("adminName = " + adminName);

				AdministratorVO adminVO = new AdministratorVO();
				adminVO.setAdminid(adminid);
				adminVO.setAdminacc(adminacc);
				adminVO.setAdminpwd(adminpwd);
				adminVO.setAdminStatus(adminStatus);
				adminVO.setAdminName(adminName);

				AdministratorService adminSvc = new AdministratorService();
				adminVO = adminSvc.addAdmin(adminid, adminacc, adminpwd, adminStatus, adminName);

				JSONObject obj = new JSONObject();
				obj.put("adminid", adminVO.getAdminid());

				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
