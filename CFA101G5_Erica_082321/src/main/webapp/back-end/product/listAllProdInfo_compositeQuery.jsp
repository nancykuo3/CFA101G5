<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productInfo.model.*"%>
<jsp:useBean id="listProdInfoByCompositeQuery" scope="request" type="java.util.List<ProdInfoVO>" />
<jsp:useBean id="prodInfoSvc" scope="page"
	class="com.productInfo.model.ProdInfoService" />
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細資料清單</title>

<style>
	#formStyle {
		width: 30%;
		margin: auto auto;
	}

	.back-end-btn:hover{
		background-color: #7f70f5;
		color: #ffffff !important;
	}
</style>
</head>
<body>
	<div>
		<table class="table table-striped table-sm table-hover">
			<tr>
				<th scope="col">ISBN</th>
				<th scope="col">商品名稱</th>
				<th scope="col">語言</th>
				<th scope="col">版本</th>
				<th scope="col">查詢</th>
				<th scope="col">修改</th>
			</tr>
			<%@ include file="pages/pageTop_CompositeQuery.file"%>
			<c:forEach var="prodInfoVO" items="${listProdInfoByCompositeQuery}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${prodInfoVO.isbn}</td>
					<td>${prodInfoVO.prodName}</td>
					<td>${prodInfoVO.prodLang}</td>
					<td>${prodInfoVO.prodVer}</td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/product/ListProdsByISBNServlet">
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>">
							<!--送出本網頁的路徑給Controller-->
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="isbn" value="${prodInfoVO.isbn}">
							<button type="submit" class="btn back-end-btn" name="action" value="listProds_By_ISBN_B" style="color:#7f70f5; border-color:#7f70f5" >查詢商品</button>
						</form>
					</td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/productInfo/UpdateProdInfoServlet">
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>">
							<!--送出本網頁的路徑給Controller-->
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="isbn" value="${prodInfoVO.isbn}">
							<button type="submit" class="btn back-end-btn" name="action" style="color:#7f70f5; border-color:#7f70f5"  
								value="getOne_Info_For_Update">修改</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pages/pageBottom_CompositeQuery.file" %>
	</div>
<%-- 	<%
	if(request.getAttribute("listProds_By_ISBN") != null) {
	%>
	<jsp:include page="listProdsByISBN.jsp" />
	<%
	}
	%> --%>
</body>
</html>