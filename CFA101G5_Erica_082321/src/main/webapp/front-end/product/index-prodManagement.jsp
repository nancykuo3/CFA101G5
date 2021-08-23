<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*" %>
<%
StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
System.out.println(storeVO.getStoreId());
// String temp = (String) storeVO.getStoreId();
Integer storeid = new Integer(storeVO.getStoreId());
System.out.println("印出" + storeid);
%>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<!-- BootStrap 5.0.2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
			<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
			<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<style>
div.prodList {
	margin: auto 2%;
}
#loginBar {
	background-color: #e1e7fb;

}

#formStyle {
	width: 30%;
	margin: auto auto;
}

nav {
	background-color: #e1e7fb;
	margin: auto 2%;
}
</style>
</head>
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>

	<div class="prodList">
			<h4>商品管理</h4>
	<a
		href="<%=request.getContextPath()%>/front-end/product/isISBNExist.jsp">
		<button type="button" class="btn btn-outline-success">新增商品</button>
	</a>
	</div>


	<!-- 錯誤訊息顯示 -->
	<c:if test="${not empty errorMsgs}">
		<b>請修正以下錯誤：</b>
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li style="color: red">${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<nav class="nav">
		<a class="nav-link"
			href="<%=request.getContextPath()%>/product/ListAllProdsByStoreidServlet?storeid=<%=storeVO.getStoreId()%>">全部</a>
		<a class="nav-link"
			href="<%=request.getContextPath()%>/product/ListProdsByStatusServlet?storeid=<%=storeVO.getStoreId()%>&status=0">待審核</a>
		<a class="nav-link"
			href="<%=request.getContextPath()%>/product/ListProdsByStatusServlet?storeid=<%=storeVO.getStoreId()%>&status=2">已審核</a>
		<a class="nav-link"
			href="<%=request.getContextPath()%>/product/ListProdsByStatusServlet?storeid=<%=storeVO.getStoreId()%>&status=1">上架中</a>
		<a class="nav-link"
			href="<%=request.getContextPath()%>/product/ListProdsByStatusServlet?storeid=<%=storeVO.getStoreId()%>&status=3">已下架</a>
	</nav>

	<%
	if (request.getAttribute("listProdsByStatus") != null) {
	%>
	<jsp:include page="listStoreProdByStatus.jsp" />
	<%
	} else if ("listProdsByStoreid" != null){
	%>
	<jsp:include page="listAllProductByStoreid.jsp?storeid=<%=storeVO.getStoreId()%>" />
	<%
	} else {
	%>
	<jsp:include page="listAllProductByStoreid.jsp?storeid=<%=storeVO.getStoreId()%>" />
	<%
	}
	%>
</body>
</html>
</html>