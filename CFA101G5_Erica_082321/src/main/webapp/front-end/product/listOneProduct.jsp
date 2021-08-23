<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
Integer prodid = (Integer) request.getAttribute("prodid");
ProductService prodSvc = new ProductService();
ProductVO productVO = prodSvc.getOneProduct(prodid);

pageContext.setAttribute("productVO", productVO);

ProdPicService picSvc = new ProdPicService();
List<ProdPicVO> list = picSvc.getByProdId(productVO.getProdId());
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品結果</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<style>
div {
	margin: auto 10%;
	text-align: center;
}

table, tr, td, #returnToProdList{
	text-align: center;
}

.imgDemo {
	width: 300px;
}
</style>
</head>
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>
	
	<div>
		<h4 class="text-center">以下為新增之商品資料：</h4>
		<table class="table table-borderd table-hover">
			<tr>
				<td class="table-success">商品編號：</td>
				<td>${productVO.prodId}</td>
			</tr>
			<tr>
				<td class="table-success">ISBN：</td>
				<td>${productVO.isbn}</td>
			</tr>
			<jsp:useBean id="prodInfoSvc" scope="page" class="com.productInfo.model.ProdInfoService" />
			<tr>
				<td class="table-success">遊戲名稱：</td>
				<td><c:forEach var="prodInfoVO" items="${prodInfoSvc.all}">
						<c:if test="${productVO.isbn==prodInfoVO.isbn}">
							${prodInfoVO.prodName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="table-success">商品售價：</td>
				<td>${productVO.price}</td>
			</tr>
			<tr>
				<td class="table-success">數量：</td>
				<td>${productVO.prodQty}</td>
			</tr>
			<tr>
				<td class="table-success">簡介：</td>
				<td>${productVO.intro}</td>
			</tr>
			<tr>
				<td class="table-success">商品首圖：</td>
				<td>
					<img class="imgDemo" src="<%=request.getContextPath()%>/product/GetFirstPicServlet?prodId=${productVO.prodId}">
				</td>
			</tr>
			<tr>
				<td class="table-success">商品圖片：</td>
				<td>
					<c:forEach var="picVO" items="${list}">
						<img class="imgDemo" src="<%=request.getContextPath()%>/product/GetProdPicsServlet?prodId=${productVO.prodId}&picId=${picVO.picId}">
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
	<a href="<%=request.getContextPath()%>/front-end/product/index-prodManagement.jsp">
		<button type="button" class="btn btn-outline-success" id="returnToProdList">回商品清單</button>
	</a>

</body>
</html>