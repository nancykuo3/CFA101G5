<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");

ProdPicService picSvc = new ProdPicService();
List<ProdPicVO> list = picSvc.getByProdId(productVO.getProdId());
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>查看單一商品</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
<script
	src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<style>
.updateProd {
	margin: auto 10%;
	text-align: center;
}

table, tr, td {
	text-align: center;
}

img {
	width: 40%;
}

label {
	width: 100% !important; 
}

#firstPic, .prodPics{
	width: 50%;

}
#form-control {
	width: 25% !important;
	
}
</style>
</head>
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
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
	<div class="updateProd">
		<form method="post"
			action="<%=request.getContextPath()%>/product/UpdateProductServlet">
			<table class="table table-borderd table-hover">
				<tr>
					<td class="table-success prodLabel">商品流水號：</td>
					<td>${productVO.prodId}</td>
				</tr>
				<tr>
					<td class="table-success prodLabel">ISBN：</td>
					<td>${productVO.isbn}</td>
				</tr>
				<jsp:useBean id="prodInfoSvc" scope="page"
					class="com.productInfo.model.ProdInfoService" />
				<tr>
					<td class="table-success prodLabel">遊戲名稱：</td>
					<td><c:forEach var="prodInfoVO" items="${prodInfoSvc.all}">
							<c:if test="${productVO.isbn==prodInfoVO.isbn}">
							${prodInfoVO.prodName}
						</c:if>
						</c:forEach></td>
				</tr>
				<tr>
					<td class="table-success prodLabel">日期：</td>
					<td>${productVO.regDate}</td>
				</tr>
				<tr>
					<td class="table-success prodLabel"><label for="price"
						class="col-sm-2 col-form-label">商品售價：</label></td>
					<td><input type="text" id="price" name="price"
						value="${productVO.price}" pattern="^[0-9]{1,}" required>
					</td>
				</tr>
				<tr>
					<td class="table-success prodLabel"><label for="prodQty"
						class="col-sm-2 col-form-label">庫存數量：</label></td>
					<td><input type="text" id="prodQty" name="prodQty"
						value="${productVO.prodQty}" maxlength="3"
						pattern="^[1-9]{1}\d{1,}$" required></td>
				</tr>
				<tr>
					<td class="table-success prodLabel">已售數量：</td>
					<td>${productVO.salesFig}</td>
				</tr>
				<tr>
					<td class="table-success prodLabel"><label for="intro"
						class="col-sm-2 col-form-label">簡介：</label></td>
					<td><textarea rows="20" cols="40" id="intro" name="intro"
							maxlength="800" required>${productVO.intro}</textarea></td>
				</tr>
			</table>
			<input type="hidden" name="requestURL"
				value="<%=request.getParameter("requestURL")%>">
			<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="prodid" value="${productVO.prodId}" />
			<button type="submit" class="btn btn-outline-success" name="action"
				value="updateProduct">送出修改</button>
			<button type="reset" class="btn btn-outline-success">重設</button>

		</form>

<!-- 商品首圖 -->
		<form method="post"
			action="<%=request.getContextPath()%>/product/UpdatePicsServlet" enctype="multipart/form-data">
			<table class="table table-borderd table-hover">
				<tr>
					<td class="table-success prodLabel">商品首圖：</td>
					<td><input id="firstPic" name="firstPic" type="file" class="form-control"
						accept="image/*"> <br /> <img id="demoImg"
						src="<%=request.getContextPath()%>/product/GetFirstPicServlet?prodId=${productVO.prodId}" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="requestURL"
				value="<%=request.getParameter("requestURL")%>">
			<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="prodid" value="${productVO.prodId}" />
			<button type="submit" class="btn btn-outline-success" name="action"
				value="updateFirstPic">確定修改圖片</button>
			<button type="reset" class="btn btn-outline-success">重設</button>
		</form>

	</div>
	<!-- 商品圖片 -->
	<div class="updateProd">
		<form method="post"
			action="<%=request.getContextPath()%>/product/UpdatePicsServlet" enctype="multipart/form-data">
			<table class="table table-borderd table-hover">
				<tr>
					<td class="table-success prodLabel">商品圖片：</td>
					<td>
				<%-- 		<% int count = 5; %>
						<% for (int i = 1; i <= count; i++) {%>
						<input id="pic<%=i%>" name="pic<%=i%>" type="file" accept="image/*">
						<br />
						<% } %> --%>
						
						<c:forEach var="picVO" items="${list}" varStatus="picCount">
							<input id="pic${picCount.count}" name="pic${picCount.count}" type="file" class="form-control prodPics" accept="image/*">
							<img id="demoPic${picCount.count}" src="<%=request.getContextPath()%>/product/GetProdPicsServlet?prodId=${productVO.prodId}&picId=${picVO.picId}">
							<br />
						</c:forEach>
						<% if (list.size() < 5) {
								for(int j = (list.size() + 1); j <= 5; j++) {%>
									<input id="pic<%=j%>" name="pic<%=j%>" type="file" class="form-control prodPics" accept="image/*">
									<img id="demoPic<%=j%>" />
									<br />
								<% } %>
						<% } %>
						</td>
				</tr>
			</table>
			<input type="hidden" name="requestURL"
				value="<%=request.getParameter("requestURL")%>">
			<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="prodid" value="${productVO.prodId}" />
			<button type="submit" class="btn btn-outline-success" name="action"
				value="update_5_ProdPics">確定修改圖片</button>
			<button type="reset" class="btn btn-outline-success">重設</button>
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
		
		<% for (int i = 1; i <= 5; i++) {%>
		$("#pic<%=i%>").change(function() {
			let pic = $("#pic<%=i%>")[0].files[0];
			let reader = new FileReader;
			reader.onload = function(e) {
				$("#demoPic<%=i%>").attr("src", e.target.result);
			};
			reader.readAsDataURL(pic);
		});
		<% } %>
	</script>
</body>
</html>