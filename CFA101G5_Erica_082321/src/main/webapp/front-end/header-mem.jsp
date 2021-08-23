<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一般會員header</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"> -->
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
	background-color: #6C6C6C;
	color: #E1E7FB;
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
	cursor: auto;
	
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
	width: 15%;
	height: 100%;
	background-color: #c4c4c4;
  	margin: 0 auto; 
  	text-align: center;
/*   	cursor: pointer; */
}

.mem .line:hover{
/* 	background-color: #89A3FC; */
	opacity: .4;
}

.mem .line:hover .mem span{ 
 	color: #fff; 
} 

.mem .line, .mem-login .line{
	width: 100%;
	height: 100%;
 	margin-left: 0px; 
}

.mem span, .mem-login span{
	color: #000000;
	font-size: 16px;
	padding-top: 22px;
	text-align: right;
}

.fa-comments{
	font-size: 22px;
	padding: 22px 15px;
	/* float: right; */
	position: absolute;
	left: 35%;
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
		<span style="color:#E1E7FB">桌．迷藏</span>
	</a>
	
	<div class="reserve-bar" style="left: 12%">
		<div>
		<span>商城</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/front-end/shop/homepage.jsp">商城首頁</a>
			<a href="<%=request.getContextPath()%>/front-end/mem/order/order_all.jsp">我的訂單</a>
			<a href="<%=request.getContextPath()%>/shop/shop.do?action=get_fav_prod">商品收藏</a>
		</div>
	</div>
	
	<div class="reserve-bar" style="left: 17%">
		<div>
		<span>預約</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/reservation/findByPage.do?action=listStores&ACC_STATUS=1">店家一覽</a>
 			<a href="<%=request.getContextPath()%>/reservation/findByPage.do?action=memListReservation&MEM_ID=<%=session.getAttribute("memVO")!= null ? ((MemVO)session.getAttribute("memVO")).getMemId().toString() : -2 %>">預約記錄</a>
   			<a href="<%=request.getContextPath()%>/reservation/findByPage.do?action=memListFav&MEM_ID=<%=session.getAttribute("memVO")!= null ? ((MemVO)session.getAttribute("memVO")).getMemId().toString() : -2 %>">店家收藏</a>
		</div>
	</div>
	
	<div class="reserve-bar" style="left: 22%">
		<div>
		<span>揪團</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/front-end/group/select_page.jsp">揪團一覽</a>
			<a href="<%=request.getContextPath()%>/front-end/participants/select_page.jsp">我要參團</a>
			<a href="<%=request.getContextPath()%>/front-end/group_collection/select_page.jsp">揪團收藏</a>
		</div>
	</div>

	<div class="reserve-bar" style="left: 27.5%">
		<div>
		<a href="<%=request.getContextPath()%>/front-end/forum/viewForum.jsp"><span>討論區</span></a>
		</div>
	</div>
	<div class="reserve-bar" style="left: 34%">
		<div>
		<span>最新消息</span>
		</div>
		<div class="resv-select shadow">
			<a href="<%=request.getContextPath()%>/front-end/storeEvent/select_all.jsp">店家活動</a>
			<a href="<%=request.getContextPath()%>/front-end/shopEvent/select_page.jsp">商城活動</a>
			<a href="<%=request.getContextPath()%>/front-end/news/select_new.jsp">最新消息</a>
			<a href="<%=request.getContextPath()%>/front-end/news/select_announcement.jsp">系統公告</a>
		</div>
	</div>
	<div class="reserve-bar" style="left: 41%">
		<div>
		<a href="<%=request.getContextPath()%>/customerservice/NameServletFe?frontaction=mem"><span>線上客服</span></a>
		</div>
	</div>
	<div class="reserve-bar" style="left: 48%">
		<div>
		<a href="<%=request.getContextPath()%>/back-end/adminLogin.jsp"><span>後台登入</span></a>
		</div>
	</div>

	<c:if test="${not empty memVO}">
		<div class="mem">
			<div class="line">
				<span class="col-4"><i class="fas fa-user-circle"></i>&nbsp;</span>
				<span class="col-8 left">${memVO.memName}</span>
			</div>
			<div class="mem-select shadow">
				<a href="<%=request.getContextPath()%>/front-end/mem/listOneMem.jsp">我的帳戶</a>
				<a href="<%=request.getContextPath()%>/login/logOutServlet?action=mem">登出</a>
			</div>
		</div>
	</c:if>
		
	<c:if test="${empty memVO}">
		<div class="mem-login">
			<div class="line">
				<a href="<%=request.getContextPath()%>/front-end/mem/login.jsp"><span style="width: 40%">會員登入</span></a>
				<a href="<%=request.getContextPath()%>/front-end/store/storeLogin.jsp"><span style="width: 40%">店家登入</span></a>	
			</div>
		</div>			
	</c:if>
</div>
</body>
</html>