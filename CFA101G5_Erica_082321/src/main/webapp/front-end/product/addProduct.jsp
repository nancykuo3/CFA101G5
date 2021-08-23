
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productInfo.model.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.store.model.*" %>
<%
StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
/* System.out.println(storeVO.getStoreId()); */
// String temp = (String) storeVO.getStoreId();
Integer storeid = new Integer(storeVO.getStoreId());
String isbn = null;

// 如果 ISBN 在資料庫內：
if (session.getAttribute("isbn") != null) {
	isbn = (String) session.getAttribute("isbn");

	ProdInfoService prodInfoSvc = new ProdInfoService();
	ProdInfoVO prodInfoVO = prodInfoSvc.getOneProdInfo(isbn);
	pageContext.setAttribute("prodInfoVO", prodInfoVO);

	Set<TypeVO> set = prodInfoSvc.getTypesByISBN(isbn);
	pageContext.setAttribute("set", set);
	
	//如果 ISBN 不在資料庫內，新增商品類型後：
}
if (request.getAttribute("prodInfoVO") != null) {
	ProdInfoVO prodInfoVO = (ProdInfoVO) request.getAttribute("prodInfoVO");
	isbn = prodInfoVO.getIsbn();
/* 	System.out.println(isbn); */
	pageContext.setAttribute("prodInfoVO", prodInfoVO);

	ProdInfoService prodInfoSvc = new ProdInfoService();
	Set<TypeVO> set = prodInfoSvc.getTypesByISBN(isbn);
	pageContext.setAttribute("set", set);
}



%>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
<script
	src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<style>
img {
	width: 300px;
}

.picsDemo {
	
}

#formStyle {
	width: 30%;
	margin: auto auto;
}

#prodLabel {
	width: 75px;
}
#addProdHint {
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
			action="<%=request.getContextPath()%>/product/AddProductServlet"
			enctype="multipart/form-data">
			<div class="col" id="addProdHint">
				<h4>新增商品資訊</h4>
			</div>
			<div class="mb-3 row">
				<label for="isbn2" class="col-sm-2 col-form-label prodLabel">ISBN</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="isbn2" id="isbn2"
						maxlength="20" size="20" value="${isbn}" disabled />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="prodName" class="col-sm-2 col-form-label prodLabel">遊戲名稱：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="prodName"
						id="prodName" value="${prodInfoVO.prodName}" disabled />
				</div>
			</div>
			<jsp:useBean id="typeSvc" scope="page"
				class="com.productType.model.TypeService" />
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label prodLabel">標籤</label>
				<div class="col-sm-10">
					<c:forEach var="tag" items="${set}">
						${tag.typeName}
						<br />
					</c:forEach>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="price" class="col-sm-2 col-form-label prodLabel">售價：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="price" id="price"
						maxlength="10" size="10" pattern="^[0-9]{1,}" required />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="prodQty" class="col-sm-2 col-form-label prodLabel">庫存數量：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="prodQty" id="prodQty"
						maxlength="3" pattern="^[1-9]{1}\d{1,}$" required />
				</div>
			</div>
			<div class="mb-3 row">
				<label for="intro" class="col-sm-2 col-form-label prodLabel">簡介：</label>
				<div class="col-sm-10">
					<textarea rows="20" cols="40" id="intro" name="intro"
						maxlength="800" required></textarea>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="firstPic" class="form-label prodLabel">商品首圖：(必需)</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="firstPic"
						id="firstPic" accept="image/*" required />
				</div>
			</div>
			<img id="demoImg">
			<%
			int count = 5;
			for (int i = 1; i <= count; i++) {
			%>
			<div class="mb-3 row">
				<label for="pic<%=i%>" class="form-label prodLabel">商品圖片：</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="pic<%=i%>"
						id="pic<%=i%>" accept="image/*" />
				</div>
			</div>
			<%}%>
			<%
			for (int i = 1; i <= count; i++) {
			%>
			<img class="picsDemo" id="demoPic<%=i%>" />
			<%}%>
			<div class="col">
				<input type="hidden" name="isbn" value="${isbn}"> <input
					type="hidden" name="status" value="0">
				<!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="requestURL"
					value="<%=request.getServletPath()%>">
				<button class="btn btn-outline-success" type="submit" name="action"
					value="insert">送出</button>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$("#firstPic").change(function() {
			let firstPic = $("#firstPic")[0].files[0];
			let reader = new FileReader;
			reader.onload = function(e) {
				$("#demoImg").attr("src", e.target.result);
			};
			reader.readAsDataURL(firstPic);
		});
		
		<%for (int i = 1; i <= 5; i++) {%>
		$("#pic<%=i%>").change(function() {
			let pic = $("#pic<%=i%>")[0].files[0];
			let reader = new FileReader;
			reader.onload = function(e) {
				$("#demoPic<%=i%>").attr("src", e.target.result);
			};
			reader.readAsDataURL(pic);
		});
	<%}%>
		
	</script>
</body>
</html>