<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/cart.css">

<style>
.ord-status{
	width: 60%;
	height: 230px;
	margin: 0 auto;
 	border: 1px solid #6C6C6C; 
	text-align: center;
	color: #6C6C6C;
	padding-top: 50px;
	
}

.result{
	font-weight: 600;
	font-size: 60px;
	display: block;
}

.step3-link{	
	padding-top: 20px;
	margin: 0 auto;
	text-align: center;
}

.step3-link a{
	padding:5px;
	color: #1B71C0;
	text-decoration: none;
}

.step3-link a:hover{
	color: #C6C6C6;
}

</style>

</head>
<body>

<div id="header" style="height:75px; margin:0; padding:0">
<jsp:include page="/front-end/shop/header.jsp" />
</div>

<div class="container mt-3">

	<div class="row status-bar">
		<span class="col-12 col-md-4 status">STEP 1. 確認購買清單</span>
		<span class="col-12 col-md-4 status">STEP 2. 填寫運送資料</span>
		<span class="col-12 col-md-4 status status-active">STEP 3. 完成訂購</span>
	</div>
	
</div>

<c:if test="${status == 1}">
	<div class="ord-status mt-3">
		<span class="result">THANK YOU!</span>
		訂單成立！
		訂單編號: <span style="color: #1B71C0">${ordId}</span>
	</div>
	<div class="step3-link">
		<a href="${pageContext.request.contextPath}/front-end/mem/order/order_all.jsp">查看訂單</a>
		<a href="${pageContext.request.contextPath}/front-end/shop/homepage.jsp">商城首頁</a>
	</div>
</c:if>

<c:if test="${status != 1}">
	<div class="ord-status mt-3">
		<span class="result">SORRY...</span>
		訂購失敗，請重新操作
	</div>
	<div class="step3-link">
		<a href="${pageContext.request.contextPath}/front-end/shop/homepage.jsp">商城首頁</a> 
	</div>
</c:if>


</body>
</html>