<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.productInfo.model.*" %>

<jsp:useBean id="listProds_By_ISBN" scope="request" type="java.util.List<ProductVO>"/>

<html>
<head>
<meta charset="UTF-8">
<title>後台商品列表</title>

<style>
	.back-end-btn:hover{
		background-color: #7f70f5;
		color: #ffffff !important;
	}
</style>

</head>
<body>
<!-- 錯誤訊息顯示 -->
	<c:if test="${not empty errorMsgs}">
		<b>請修正以下錯誤：</b>
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li style="color: red">${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div>
		<table class="table table-striped table-sm table-hover">
			<tr>
				<th scope="col">商品流水號</th>
				<th scope="col">ISBN</th>
				<th scope="col">店家ID</th>
				<th scope="col">價格</th>
				<th scope="col">商品狀態</th>
				<th scope="col">商品審核</th>
			</tr>
			<c:forEach var="productVO" items="${listProds_By_ISBN}">
				<tr>
					<td>${productVO.prodId}</td>
					<td>${productVO.isbn}</td>
					<td>${productVO.storeId}</td>
					<td>${productVO.price}</td>
					<td>
						<c:if test="${productVO.status==0}">待審核</c:if>
						<c:if test="${productVO.status==1}">已上架</c:if>
						<c:if test="${productVO.status==2}">已審核</c:if>
						<c:if test="${productVO.status==3}">已下架</c:if>
					</td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/product/UpdateProdStatusServlet">
							<!--送出本網頁的路徑給Controller-->
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>">
							<input type="hidden" name="prodid" value="${productVO.prodId}">
							<button type="submit" class="btn back-end-btn" name="action" value="getProds_By_Prodid" style="color:#7f70f5; border-color:#7f70f5">查詢商品</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>