<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*" %>

<%
StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
Integer storeId = new Integer(storeVO.getStoreId());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家會員header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<style>
body{ 
	font-family: "微軟正黑體";
	margin: 0;
	padding: 0;
}

div{
	margin: 0;
	padding: 0;
}

.navbar-cart a{
	text-decoration: none;
	color: #000000;
	font-size: 13px;
}

.sp-navbar a:hover{
	color: #C6C6C6;
}

.sp-navbar{
	height: 70px;
	position: fixed;  
	width: 100%;
	background-color: #F8F8F8;
	color: #C6C6C6;
	display: inline-block;
	z-index: 9;
}

.sp-logo{
	float: left; 
	padding: 5px;
	margin-left: 20px;
	width: 70px;
	height: 70px;
	color: #C6C6C6;
}

.sp-navbar img{
	height: 100%;
	width: 100%;
}

.sp-navbar span{
  	padding: 20px 5px;  
	font-size: 18px;
   	float: left; 
	position: relative;

}

.reserve-bar{
	position: absolute;
	/* padding: 0px 20px; */
	/* padding-left: 10px; */
	width: 8%;
	height: 100%;
	

	margin: 0 auto;
}

.reserve-bar span:hover{
	opacity: .5;
}

.reserve-bar span{	
	font-size: 16px;
	padding-top: 24px 10px;
	/* height: 100%; */
	width: 100%;
	/* background-color: #6C6C6C; */
	text-align: center;
	color: #000000;
	cursor: pointer;
	
}

.mem{
	/* float: right; */
	position: absolute;
	right: 0%;
	width: 13%;
	height: 100%;
	background-color: #c4c4c4;
  	margin: 0 auto; 
  	/* position: relative; */
  	cursor: pointer;
}

.mem-login{
	position: absolute;
	right: 0%;
	width: 16%;
	height: 100%;
	background-color: #c4c4c4;
  	margin: 0 auto; 
  	text-align: center;
/*   	cursor: pointer; */
}

.mem .login-:hover{
/* 	background-color: #89A3FC; */
	opacity: .4;
}

.mem .line:hover .mem span{ 
 	color: #fff; 
} 

.mem .line, .mem-login .line{
	width: 100%;
	height: 100%;
 	padding: 0px; 
 	margin-left: 0px; 
 	display: inline-block;
}

.mem span, .mem-login span{
	color: #000000;
	font-size: 16px;
 	padding: 22px 0px;
	text-align: center;
}

.fa-comments{
	font-size: 22px;
	padding: 22px 15px;
	/* float: right; */
	position: absolute;
	left: 45%;
	cursor: pointer;
	color: #000000;
}


.sp-nav-home{
	color: #E1E7FB;
	height: 100%;
}


.nav-cart-item span{
	color: #3b68ff;
	font-size: 14px;
	float: right;
	padding: 2px 3px 0px 8px;
	position: absolute;
	right: 0;
	background-color: #fff;
}



.fa-user-circle{
	font-size: 22px;
	padding: 2px;
}

.mem .left{
	text-align: left;
}
.mem-select{
	display: none;
}

.resv-select{
	display: none;
	position:absolute;
	top: 70px;
	width: 100%;
	cursor: pointer;
}

.resv-select a{
	text-align: center;
}

.mem-select a, .resv-select a{
	font-size: 17px;
	display: block;
	width: 100%;
	height: 60%;
	text-decoration: none;
	background-color: #F8F8F8;
	color: #6C6C6C;
	padding: 8px 10px;
}

.mem-select a:hover, .resv-select a:hover{
	background-color: #868686;
}

.mem:hover .mem-select , .reserve-bar:hover .resv-select{
	display: block;
}



</style>

</head>
<body>




<div class="sp-navbar">
	<a href="<%=request.getContextPath()%>/front-end/index-front.jsp">
		<div class="sp-logo">
			<img src="<%=request.getContextPath()%>/image/logo1.png">
		</div>
		<span style="color:#6C6C6C">桌．迷藏</span>
	</a>
	
	<div class="reserve-bar" style="left: 14%">
		<div>
		<span>商品管理</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/front-end/product/index-prodManagement.jsp">商品清單</a>
			<a href="<%=request.getContextPath()%>/front-end/product/isISBNExist.jsp">新增商品</a>
		</div>
	</div>
	
	<div class="reserve-bar" style="left: 20%">
		<div>
			<a href="<%=request.getContextPath()%>/front-end/store/order/order_all.jsp"><span>訂單管理</span></a>
		</div>
	</div>
	
	<div class="reserve-bar" style="left: 26%">
		<div>
			<a href="<%=request.getContextPath()%>/reservation/findByPage.do?action=storeListReservation&STORE_ID=<%=storeId.toString()%>"><span>預約管理</span></a>
		</div>
	</div>
	<div class="reserve-bar" style="left: 32%">
		<div>
			<span>活動管理</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/front-end/storeEvent/listActivities.jsp">店家活動管理</a>
			<a href="<%=request.getContextPath()%>/front-end/shopEvent/listEvent.jsp">商城活動管理</a>
		</div>
	</div>
 	
 	<div class="reserve-bar" style="left: 38%">
		<div>
			<a href="<%=request.getContextPath()%>/customerservice/NameServletFe?frontaction=mem"><span>線上客服</span></a>
		</div>
	</div>

	

	
		
	
	<c:if test="${not empty StoreVO}">
		<div class="mem">
			<div class="line">
				<span style="width: 30%"><i class="fas fa-user-circle"></i>&nbsp;</span>
				<span style="width: 70%; text-align: left">${StoreVO.storeName}</span>
			</div>
			<div class="mem-select shadow">
				<a href="<%=request.getContextPath()%>/front-end/store/listOneStore.jsp">我的帳戶</a>
				<a href="<%=request.getContextPath()%>/login/logOutServlet?action=store">登出</a>
			</div>
		</div>
	</c:if>
		
	<c:if test="${empty StoreVO}">
		<div class="mem-login">
			<div class="line">
				<a href="#"><span style="width: 40%">註冊</span></a>
				<a href="#"><span style="width: 60%">店家登入</span></a>	

			</div>
			
		</div>			
	</c:if>
	
</div>



</body>
</html>