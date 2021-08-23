<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.order.model.*" %>
<%@ page import="com.store.model.*" %>
<%@ page import="java.util.List" %>

<%
	StoreVO storeVO = (StoreVO)session.getAttribute("StoreVO");

	OrderService ordSvc = new OrderService();
	List<OrderVO> list  = ordSvc.listAllByStoreId(storeVO.getStoreId());
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemManagementService"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>訂單管理</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/store/order/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/store/order/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">

</head>

<body id="page-top">
<div id="header" style="height:70px">
<jsp:include page="/front-end/header-store.jsp" />
</div>
	<nav class="navbar navbar-light navbar-expand-md navigation-clean-search" style="background: rgb(225,231,251);">
		<div class="container">
			<button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1">
				<span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active" href="#">全部</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=0">尚未付款</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=2">待確認</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=3">待出貨</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=4">已完成</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=1">不成立</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<form method="post" action="<%=request.getContextPath()%>/order/storeOrd.do">
		<div class="search-container">
			<input type="text" class="search-input" name="ordId" placeholder="請輸入訂單編號" />
			<button class="btn btn-light search-btn" type="submit" name="action" value="find_by_ordId">
				<i class="fas fa-search"></i>
			</button>
		</div>
	</form>

	<div class="container-fluid">
		<div class="card shadow">
			<div class="card-body">
				<div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
					<table class="table my-0" id="dataTable">
						<thead>
							<tr>
 								<th></th>
								<th>訂單編號</th>
								<th>會員帳號</th>
								<th>訂單日期</th>
								<th>出貨方式</th>
								<th>運費</th>
								<th>總金額</th>
								<th>付款方式</th>
								<th>出貨日期</th>
								<th>訂單狀態</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file" %>
							<c:forEach var="orderVO" items="${list}" varStatus="s" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<c:set var="counter" value="<%=(whichPage-1)*rowsPerPage%>" />
							<tr>
								<th>${s.count+counter}</th>
								<th><a href="<%=request.getContextPath()%>/order/storeOrd.do?ordId=${orderVO.ordId}&action=order_details&whichPage=<%=whichPage%>">${orderVO.ordId}</a></th>
								<td>${memSvc.getOneMem(orderVO.memId).memAcc}</td> 
								<td>
									<fmt:formatDate value="${orderVO.ordDate}" pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long" />
								</td>
						
								<td>
									<c:if test="${orderVO.shipment==1}">全家取貨</c:if>
									<c:if test="${orderVO.shipment==2}">7-11取貨</c:if>
									<c:if test="${orderVO.shipment==3}">萊爾富取貨</c:if>
									<c:if test="${orderVO.shipment==4}">OK Mart取貨</c:if>
									<c:if test="${orderVO.shipment==5}">賣家宅配</c:if>
								</td>
								<td>${orderVO.shipFee}</td>
								<td>${orderVO.amount}</td>
								<td>
									<c:if test="${orderVO.payment==0}">信用卡</c:if>
									<c:if test="${orderVO.payment==1}">銀行轉帳</c:if>
									<c:if test="${orderVO.payment==2}">超商取貨付款</c:if>
								</td>
								<td>${orderVO.shipDate}</td>
								<td>
									<c:if test="${orderVO.status==0}">尚未付款</c:if>
									<c:if test="${orderVO.status==1}">已取消</c:if>
									<c:if test="${orderVO.status==2}">等待賣家確認中</c:if>
									<c:if test="${orderVO.status==3}">出貨準備中</c:if>
									<c:if test="${orderVO.status==4}">已出貨</c:if>
									<c:if test="${orderVO.status==5}">未取貨</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot>
 							<tr></tr>
						</tfoot>
					</table>
				</div>
				<%@ include file="page2.file" %>
			</div>
		</div>
	</div>
</body>

</html>