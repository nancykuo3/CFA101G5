<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.productInfo.model.*"%>
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
<title>新增商品詳細資料</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
			<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
			<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<style>
#formStyle {
	width: 30%;
	margin: auto auto;
}
#Intro {
	margin-top: 3%;
}
</style>
</head>
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>
	
	<div id="formStyle">
		<form method="post"
			action="<%=request.getContextPath()%>/product/Front_AddProdInfoServlet">
			<div class="col" id="Intro">
				<h4>請於下方輸入欲新增的商品詳細資料：</h4>
			</div>
			<div class="mb-3 row">
				<label for="isbn" class="col-sm-2 col-form-label">ISBN</label>

				<div class="col-sm-10">
					<input type="text" class="form-control" name="isbn" id="isbn"
						maxlength="20" size="20" pattern="^[1-9]{1}\d{1,}$"
						placeholder="請輸入數字如：4712946397453" required />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="prodName" class="col-sm-2 col-form-label">名稱</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="prodName"
						id="prodName" maxlength="50" size="50" placeholder="如：狼人殺 口袋版"
						required />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="prodLang" class="col-sm-2 col-form-label">語言</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="prodLang"
						id="prodLang" maxlength="20" size="20" placeholder="如：繁體中文、英文...等" />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="prodVer" class="col-sm-2 col-form-label">版本</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="prodVer" id="prodVer"
						maxlength="20" size="20"
						pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{0,20}"
						placeholder="如：年度版、2020年" />
				</div>
			</div>
			<jsp:useBean id="typeSvc" scope="page"
				class="com.productType.model.TypeService" />
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">標籤</label>
				<div class="col-sm-10">
					<c:forEach var="typeVO" items="${typeSvc.all}">
						<input type="checkbox" name="typeid" value="${typeVO.typeid}">${typeVO.typeName}
					</c:forEach>
				</div>
			</div>
			<div class="col">
				<!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<button class="btn btn-outline-success" type="submit" name="action"
					value="insert">送出</button>
			</div>
		</form>
	</div>
</body>
</html>