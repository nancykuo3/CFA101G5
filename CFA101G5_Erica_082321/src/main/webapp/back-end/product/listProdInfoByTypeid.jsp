<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productInfo.model.*"%>

<!-- 此頁面被 listAllType.jsp include，因此是接收從該頁來的 request 參數 -->
<jsp:useBean id="typeSvc" scope="page"
	class="com.productType.model.TypeService" />



<html>
<head>
<meta charset="UTF-8">
<title>商品詳細資料列表</title>


</head>
<body>
	<h2>商品詳細資料列表</h2>

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
				<th scope="col">ISBN</th>
				<th scope="col">商品名稱</th>
				<th scope="col">語言</th>
				<th scope="col">版本</th>
				<th scope="col">修改</th>
			</tr>

			<c:forEach var="prodInfoVO" items="${listProdInfoByTypeid}">
				<tr>
					<td>${prodInfoVO.isbn}</td>
					<td>${prodInfoVO.prodName}</td>
					<td>${prodInfoVO.prodLang}</td>
					<td>${prodInfoVO.prodVer}</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/productInfo/UpdateProdInfoServlet">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<!--送出本網頁的路徑給Controller-->
							<!-- 目前尚未用到  -->
							<input type="hidden" name="isbn" value="${prodInfoVO.isbn}">
							<button type="submit" name="action" value="getOne_Info_For_Update" class="btn back-end-btn" style="color:#7f70f5; border-color:#7f70f5">修改</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>