<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.order.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.List" %>

<% 
MemVO memVO = (MemVO) session.getAttribute("memVO");
if(memVO == null){
	response.sendRedirect("/CFA101G5/front-end/mem/order/test_page.jsp");
	return;
}
OrderService ordSvc = new OrderService();
List<OrderVO> list = ordSvc.listAllByMemId(memVO.getMemId());
pageContext.setAttribute("list", list);
System.out.println(list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/mem_ord.css">
</head>
<body>

<div id="header" style="height:75px; margin:0; padding:0">
<jsp:include page="/front-end/shop/header.jsp" />
</div>

<div class="container mt-3">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link active" href="#">全部</a></li>
		<li class="nav-item"><a class="nav-link" href="#">尚未付款</a></li>
		<li class="nav-item"><a class="nav-link" href="#">待確認</a></li>
		<li class="nav-item"><a class="nav-link" href="#">待出貨</a></li>
		<li class="nav-item"><a class="nav-link" href="#">已完成</a></li>
		<li class="nav-item"><a class="nav-link" href="#">不成立</a></li>
	</ul>
</div>

<div class="container mem-ord">

<%@ include file="page1.file" %>

<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<div class="mb-3 p-0">
	<div class="card shadow">
	<div class="m-3">

		<div class="row storeId">
			<div class="col-8">
				<span><a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${ordVO.storeId}"><i class="fas fa-store"></i>&nbsp; ${ordVO.storeName}</a></span>
			</div>
			<div class="col-4 div-r">
				<span class="status">
					<c:if test="${ordVO.status == 0}">等待付款中</c:if>
					<c:if test="${ordVO.status == 1}">已取消</c:if>
					<c:if test="${ordVO.status == 2}">等待賣家確認中</c:if>
					<c:if test="${ordVO.status == 3}">出貨準備中</c:if>
					<c:if test="${ordVO.status == 4}">已出貨</c:if>
					<c:if test="${ordVO.status == 5}">未取貨</c:if>
				</span>
				<span>&nbsp;|&nbsp;訂單編號: ${ordVO.ordId}</span>
			</div>
		</div>
		
		<jsp:useBean id="orderSvc" scope="page" class="com.order.model.OrderService" />
		
		<c:forEach var="ordDetVO" items="${orderSvc.getOrdDetsByOrdId(ordVO.ordId)}">
		<div class="row item">
			<div class="col-2">
				<div class="ord-all-img">
					<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${ordDetVO.prodId}" />
				</div>	
			</div>
			<div class="row col-10">
				<div class="col-12">
					<span><a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${ordDetVO.prodId}">${ordDetVO.prodName}</a></span>
				</div>
				<div class="col-8">	
					<span>x ${ordDetVO.ordQty}</span>
				</div>
				<div class="col-4 div-r">
					<span class="price">${ordDetVO.ordPrice}</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</div>

	<div class="card shadow foot">
		<div class="row">
			<div class="col-6 foot-l">
				<div>
					<span>
						運送方式: 
						<c:if test="${ordVO.shipment == 1}">全家</c:if>
						<c:if test="${ordVO.shipment == 2}">7-11</c:if>
						<c:if test="${ordVO.shipment == 3}">萊爾富</c:if>
						<c:if test="${ordVO.shipment == 4}">OK Mart</c:if>
						<c:if test="${ordVO.shipment == 5}">賣家宅配</c:if>
					</span>
				</div>
				<div>
					<span>
						付款方式: 						
						<c:if test="${ordVO.payment == 0}">信用卡</c:if>
						<c:if test="${ordVO.payment == 1}">銀行轉帳</c:if>
						<c:if test="${ordVO.payment == 2}">超商取貨付款</c:if>
					</span>
				</div>
				<div><span>收件人: ${ordVO.ordName}</span></div>
				<div><span>手機: ${ordVO.ordMobile}</span></div>
				<div><span>地址: ${ordVO.ordAddr}</span></div>
				<div><span>訂單備註: ${ordVO.memo}</span></div>
				<div>
					<span>訂單日期: 
				<fmt:formatDate value="${ordVO.ordDate}" pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long" />
					</span>
				</div>
				<div><span>出貨日期: ${ordVO.shipDate}</span></div>
				<div><span>貨運追蹤號碼: ${ordVO.trackNo}</span></div>
			</div>
			<div class="col-6 foot-r">
				<div><span>運費: ${ordVO.shipFee}</span></div>
				<div>
					<span>訂單金額: 
						<span class="amount">${ordVO.amount}</span>
					</span>
				</div>
				<c:if test="${ordVO.status == 0}">
					<div>
					<form method="post" action="<%=request.getContextPath()%>/order/memOrd.do">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="ordId" value="${ordVO.ordId}">
						<input type="hidden" name="amount" value="${ordVO.amount}">
						<button class="btn btn-blue" type="submit" name="action" value="pay_by_card">前往付款</button>
					</form>
					</div>
				</c:if>
				<c:if test="${ordVO.status == 0 || ordVO.status == 2}">
					<div>
					<form method="post" action="<%=request.getContextPath()%>/order/memOrd.do">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="ordId" value="${ordVO.ordId}">
						<button class="btn btn-gray" type="submit" name="action" value="cancel_order">取消訂單</button>
					</form>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</c:forEach>
<%@ include file="page2.file" %>
</div>

<script>
	window.addEventListener('load', function(){
		let sum = document.getElementsByClassName('price').length;
		for (i=0; i<sum; i++){
		let price = document.getElementsByClassName('price')[i].innerHTML;
		let new_price = '$' + format_with_substring(price);
		document.getElementsByClassName('price')[i].innerHTML = new_price;
		};
	});
	
	window.addEventListener('load', function(){
		let sum = document.getElementsByClassName('amount').length;
		for (i=0; i<sum; i++){
		let amount = document.getElementsByClassName('amount')[i].innerHTML;
		amount = Number(amount);
		let new_amount = '$' + format_with_substring(amount);
		document.getElementsByClassName('amount')[i].innerHTML = new_amount;
		};
	});
	
	//方法-金額加千分位符號
	function format_with_substring(number) {
	    let arr = (number + '').split('.');
	    let num = arr[0] + '';
	    let fraction = arr[1] || '';
	    let f = num.length % 3;
	    let r = num.substring(0, f);
	
	    for (let i = 0; i < Math.floor(num.length / 3); i++) {
	        r += ',' + num.substring(f + i * 3, f + (i + 1) * 3)
	    };
	    if (f === 0) {
	        r = r.substring(1);
	    };
	    return r + (!!fraction ? "." + fraction : '');
	};
</script>
</body>
</html>