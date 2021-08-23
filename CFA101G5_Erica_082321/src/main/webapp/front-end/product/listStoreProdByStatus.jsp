<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productInfo.model.*"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.store.model.*" %>
<%
StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
System.out.println(storeVO.getStoreId());
// String temp = (String) storeVO.getStoreId();
Integer storeid = new Integer(storeVO.getStoreId());
%>
<jsp:useBean id="listProdsByStatus" scope="request" type="java.util.List<ProductVO>"/>

<jsp:useBean id="prodInfoSvc" scope="page"
	class="com.productInfo.model.ProdInfoService" />

<html>
<head>
<meta charset="UTF-8">
<title>商品狀態列表</title>
<!-- BootStrap 5.0.2 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- jQuery 1.12.4 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<style>
#formStyle {
	width: 30%;
	margin: auto auto;
}
div.prodList {
	margin: auto 2%;
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

	<div class="prodList">
		<table class="table table-striped table-hover">
			<tr class="table-success">
				<th>流水編號</th>
				<th>ISBN</th>
				<th>遊戲名稱</th>
				<th>售價</th>
				<th>已售數量</th>
				<th>剩餘數量</th>
				<th>商品狀態</th>
				<th>查詢</th>
			</tr>

			<%  int rowsPerPage = 5;  //每頁的筆數    
    int rowNumber=0;      //總筆數
    int pageNumber=0;     //總頁數      
    int whichPage=1;      //第幾頁
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
    rowNumber=listProdsByStatus.size();
    if (rowNumber%rowsPerPage !=0)
         pageNumber=rowNumber/rowsPerPage + 1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber];
    for (int i=1 ; i<=pageIndexArray.length ; i++)
         pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>

<%if (pageNumber>0){%>
  <b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁</font></b>
<%}%>

<b>    以下共 <font color=red><%=rowNumber%></font> 筆資料：</b>

			<c:forEach var="productVO" items="${listProdsByStatus}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${productVO.prodId}</td>
					<td>${productVO.isbn}</td>
					<td>
						<c:forEach var="prodInfoVO" items="${prodInfoSvc.all}">
							<c:if test="${productVO.isbn==prodInfoVO.isbn}">
								${prodInfoVO.prodName}
							</c:if>
						</c:forEach>
					</td>
					<td>${productVO.price}</td>
					<td>${productVO.salesFig}</td>
					<td>${productVO.prodQty}</td>
					<td>
						<c:if test="${productVO.status==0}">待審核</c:if>
						<c:if test="${productVO.status==1}">已上架</c:if>
						<c:if test="${productVO.status==2}">已審核</c:if>
						<c:if test="${productVO.status==3}">已下架</c:if>
					</td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/product/UpdateProductServlet">
							<!--送出本網頁的路徑給Controller-->
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>">
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<input type="hidden" name="prodid" value="${productVO.prodId}">
							<button type="submit" class="btn btn-success" name="action"
								value="getOneProduct_B">查看商品</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pages/pageBottom.file"%>
	</div>
</body>