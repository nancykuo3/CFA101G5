<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.admin.model.*"%>
<%
	AdministratorVO adminVO = null;
	adminVO = (AdministratorVO) session.getAttribute("administratorVO");
	if (null == adminVO) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/back-end/admin/adminLogin.jsp";
		response.sendRedirect(redirectURL);
	} else {

	}

	// 有

	// 	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<td>
	<a href="<%=request.getContextPath() %>/back-end/mem/allMem.jsp"> 
	<input type="button" value="一般會員" style="width: 120px; height: 40px; font-size: 20px;"></a>
	</td>


	<td>
	<a href="<%=request.getContextPath() %>/back-end/store/allStore.jsp"> 
	<input type="button" value="店家會員" style="width: 120px; height: 40px; font-size: 20px;"></a>
	</td>
</body>
</html>