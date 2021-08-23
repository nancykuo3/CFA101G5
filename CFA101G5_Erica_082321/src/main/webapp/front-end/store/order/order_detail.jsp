<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.order.model.*"%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemManagementService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單明細</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/store/order/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/store/order/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">

<style>
	div.hide{
		margin-bottom: 30px;
		text-align: center;
		display: none;
	}
	.right{
		text-align: right;
		}
	.center{
		text-align: left;
		}
</style>


</head>
<body id="page-top">

<div id="header" style="height:70px">
<jsp:include page="/front-end/header-store.jsp" />
</div>

	<nav class="navbar navbar-light navbar-expand-md navigation-clean-search" style="background: rgb(225,231,251);">
		<div class="container"><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/store/order/order_all.jsp">全部</a></li>
					<li class="nav-item"><a class="nav-link" id="type0" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=0">尚未付款</a></li>
					<li class="nav-item"><a class="nav-link" id="type2" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=2">待確認</a></li>
					<li class="nav-item"><a class="nav-link" id="type3" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=3">待出貨</a></li>
					<li class="nav-item"><a class="nav-link" id="type4" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=4">已完成</a></li>
					<li class="nav-item"><a class="nav-link" id="type1" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=1">不成立</a></li>
				</ul>
			</div>
		</div>
	</nav>
	

	
	<div id="previous">
	<c:if test="${empty type}">
		<a id="prevpage" href="<%=request.getContextPath()%>/front-end/store/order/order_all.jsp?whichPage=${whichPage}">
			<i class="fas fa-chevron-left"></i>返回
		</a>
	</c:if>	
	<c:if test="${not empty type}">
		<a id="prevpage" href="<%=request.getContextPath()%>/order/storeOrd.do?action=order_type&type=${type}">
			<i class="fas fa-chevron-left"></i>返回
		</a>
	</c:if>	
	</div>

	<div class="container-fluid">
		<div class="card shadow">
			<div class="card-body">
				<div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">

	<div style="display: inline-block; width: 49%">	

	<span style="padding: 0.5rem; display: block">訂單編號: ${orderVO.ordId}   |   訂單狀態:<b style="color: #627cf7">					    		
		<c:if test="${orderVO.status==0}">等待付款中</c:if>
		<c:if test="${orderVO.status==1}">已取消</c:if>
		<c:if test="${orderVO.status==2}">等待賣家確認中</c:if>
		<c:if test="${orderVO.status==3}">出貨準備中</c:if>
		<c:if test="${orderVO.status==4}">已出貨</c:if>
		<c:if test="${orderVO.status==5}">未取貨，商品退回賣家</c:if>
		</b></span><br>
	<span style="padding: 0.5rem; margin-top: -1rem;display: block">會員編號: ${memSvc.getOneMem(orderVO.memId).memAcc} </span>
	</div>

	



<div class="hide" id="confirmOrd" style="width: 49%; text-align: right">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/storeOrd.do">
			<input type="hidden" name="ordId" value="${orderVO.ordId}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="type" value="${type}">
			<input type="hidden" name="whichPage" value="${whichPage}">
			<button class="btn btn-primary btn-sm" style="background-color: #627cf7" type="submit" name="action" value="confirm_order">確認訂單</button>
			<button class="btn btn-primary btn-sm" style="background-color: #8a8d9f" type="submit" name="action" value="cancel_order">取消訂單</button>
		</FORM>
</div>

<div class="hide" id="shipOrd" style="width: 49%; text-align: right">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/storeOrd.do">
			<input type="TEXT" class="search-input" style="width:20rem; height:2rem; border: 1px solid #ddd;'" name="trackNo" size="25" maxlength="20" placeholder="貨運追蹤號碼">
			<input type="hidden" name="ordId" value="${orderVO.ordId}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="type" value="${type}">
			<input type="hidden" name="whichPage" value="${whichPage}">
			<button class="btn btn-primary btn-sm" style="background-color: #627cf7" type="submit" name="action" value="order_shipment">通知出貨</button>
			<button class="btn btn-primary btn-sm" style="background-color: #8a8d9f" type="submit" name="action" value="cancel_order">取消訂單</button>
			<br>${errorMsg}
		</FORM>
</div>


					<table class="table my-0" id="dataTable">
  						<thead>

  						  <tr>		
    					 	<th scope="col"></th>
    						  <th scope="col">商品編號</th>
						      <th scope="col">商品名稱</th>
						      <th scope="col" class="right">數量</th>
						      <th scope="col" class="right">單價</th>
						      <th scope="col" class="right">小計</th>
						      <th></th>
						    </tr>
    
						  </thead>
						  <tbody>
							<c:forEach var="ordDetVO" items="${order_items}" varStatus="s" >
						    <tr>
						    	<td>${s.count}</td>
						    	<th>${ordDetVO.prodId}</th>
								<td>${ordDetVO.prodName}</td>
								<td class="right">${ordDetVO.ordQty}</td>
								<td class="right">${ordDetVO.ordPrice}</td>
								<td class="right">${ordDetVO.ordQty*ordDetVO.ordPrice}</td>
								<th></th>
						    </tr>
						    </c:forEach>
						    <tr>
						    	<th colspan="4"></th>
						    	<th class="right">運費</th>
						    	<td class="right">${orderVO.shipFee}</td>
						    	<td></td>
						    </tr>
						    <tr>
						    	<th colspan="4"></th>
						    	<th class="right">總金額</th>
						    	<td class="right">${orderVO.amount}</td>
						    	<td></td>
						    </tr>
						    <tr>
						    	<td></td>
						    	<th colspan="1" class="center">出貨方式</th>
						    	<td colspan="5">			
									<c:if test="${orderVO.shipment==1}">全家取貨</c:if>
									<c:if test="${orderVO.shipment==2}">7-11取貨</c:if>
									<c:if test="${orderVO.shipment==3}">萊爾富取貨</c:if>
									<c:if test="${orderVO.shipment==4}">OK Mart取貨</c:if>
									<c:if test="${orderVO.shipment==5}">賣家宅配</c:if>
								</td>
						    </tr>
					        <tr>
					        <td></td>
					    	<th colspan="1" class="center">付款方式</th>
					    	<td colspan="5">			
					    		<c:if test="${orderVO.payment==0}">
									<c:out value="信用卡"/>
								</c:if>
								<c:if test="${orderVO.payment==1}">
									<c:out value="銀行轉帳"/>
								</c:if>
								<c:if test="${orderVO.payment==2}">
									<c:out value="超商取貨付款"/>
								</c:if>
							</td>
						    </tr>
						    <tr>
						    	<td></td>
						    	<th class="center">收件人</th>
						    	<td colspan="5">${orderVO.ordName}</td>
						    </tr>
						    <tr>
						    	<td></td>
						    	<th class="center">手機</th>
						    	<td colspan="5">${orderVO.ordMobile}</td>
						    </tr>
						    <tr>
						    	<td></td>
						    	<th class="center">收件地址</th>
						    	<td colspan="5">${orderVO.ordAddr}</td>
						    </tr>
						    <tr>
						    	<td></td>
						    	<th colspan="1" class="center">訂單備註</th>
						    	<td colspan="5">${orderVO.memo}</td>
						    </tr>
						    <tr>
					    	<td></td>
					    	<th colspan="1" class="center">出貨日期</th>
					    	<td colspan="5">${orderVO.shipDate}</td>
					    </tr>
					     <tr>
					    	<td></td>
					    	<th colspan="1" class="center">貨運追蹤號碼</th>
					    	<td colspan="5">${orderVO.trackNo}</td>
					    </tr>

					  </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
					
<script>
	window.addEventListener('load', hideButton);
	let status = ${orderVO.status};
	function hideButton(){
		if (status == 2){
			document.getElementById('confirmOrd').style.display='inline-block';
		} else if (status == 3){
			document.getElementById('shipOrd').style.display='inline-block';
		}
	};
	
</script>
</body>
</html>