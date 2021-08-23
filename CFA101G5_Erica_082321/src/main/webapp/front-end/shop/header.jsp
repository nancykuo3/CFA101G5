<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商城NAVBAR</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<style>
body{ 
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
	background-color: #3b68ff;
	color: #E1E7FB;
	display: inline-block;
	z-index: 9;
}

.sp-logo{
	float: left; 
	padding: 5px;
	margin-left: 20px;
	width: 70px;
	height: 100%;
	color: #E1E7FB;
}

.sp-navbar img{
	height: 100%;
	width: 100%;
}

.sp-navbar span{
  	padding: 20px 5px;  
	font-size: 18px;
   	float: left; 

}

.mem{
	/* float: right; */
	position: absolute;
	right: 0%;
	width: 13%;
	height: 100%;
	background-color: #89A3FC;
  	margin: 0 auto; 
  	/* position: relative; */
  	cursor: pointer;
}

.mem .row:hover{
	background-color: #89A3FC;
	opacity: .4;
}

.mem .row:hover .mem span{
	color: #fff;
}

.mem .row{
	width: 100%;
	height: 100%;
 	padding: 22px 0px; 
 	margin-left: 0px; 
}

.mem span{
	color: #000000;
	font-size: 16px;
	padding: 0;
	text-align: right;
}



.sp-nav-home span{
	font-size: 17px;

	padding: 21px 12px;
	position: absolute;
	color: #E1E7FB;
	height: 100%;
	letter-spacing: 1px;
}

.sp-nav-home{
	color: #E1E7FB;
	height: 100%;
}

.sp-nav-home span:hover{
	color: #E1E7FB;
	background-color: #000000;
	opacity: .2;
}

.fa-comments:hover{
	background-color: #000000;
	opacity: .2;
}


.navbar-cart .fa-shopping-cart{
  	padding: 23px 15px;
	  color: #E1E7FB; 
	  font-size: 22px;
}
.navbar-cart{
	width: 5%;
	/* float: right;  */
	position: absolute;
	left: 83%;
 	font-size: 22px;
 	cursor: pointer;
}  

.nav-cart-item a{
	display: block;
	height: 50px;
	/* width: 320%; */
	background-color: #fff;
	padding: 10px;
	overflow: hidden;
	white-space:nowrap;
	position: relative;
	font-size: 13px;

}

.nav-cart-item a:hover{
	background-color: #fff;
	opacity: .5;
	/* cursor: pointer; */
}


.nav-cart-item{
	width: 320%;
	background-color: #fff;
	margin-top: 5px;
	display: none;
	/* position: absolute;
	left: 80%; */
}

.navbar-cart:hover .nav-cart-item{
	display: block;
}

.navbar-cart:hover .fa-shopping-cart{
	background-color: #000000;
	opacity: .2;
}


.sp-nav-check a{
	background-color: #3b68ff;
	text-align: center;
	height: 40px;
	font-size: 15px;
	font-weight: 200;
	padding: 6px 0px;
	color: #fff;
}

.sp-nav-check a:hover{
	background-color: #0c43f5;
}

.nav-cart-item img{
	width: 30px;
	height: 30px;
	margin-right: 5px;
	
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
	
.mem-select a{
	font-size: 17px;
	display: block;
	width: 100%;
	height: 40px;
	text-decoration: none;
	background-color: #E1E7FB;
	color: #6C6C6C;
	padding: 8px 10px;
}

.mem-select a:hover{
	background-color: #89A3FC;
}

.mem:hover .mem-select {
	display: block;
}

#cart-circle{
	height:20px; 
	width:20px; 
	background-color: #ff3b68; 
	border-radius: 10px; 
	position: absolute; 
	left: 45%; 
	top: 15%;
	padding: 0px;
	font-size: 10px; 
	text-align: center; 
	font-weight: 900; 
	color: #E1E7FB;
	line-height: 20px;
}

</style>

</head>
<body>




<div class="sp-navbar">
	<a href="<%=request.getContextPath()%>/front-end/shop/homepage.jsp">
		<div class="sp-logo">
			<img src="<%=request.getContextPath()%>/image/logo1.png">
		</div>
		<span style="color:#E1E7FB">桌．迷藏</span>
		<span style="color: yellow">購物商城</span>
	</a>
	
	<a class="sp-nav-home" href="<%=request.getContextPath()%>/front-end/index-front.jsp"><span style="left:22%">首頁</span></a>
	<a class="sp-nav-home" href="<%=request.getContextPath()%>/customerservice/NameServletFe?frontaction=mem"><span style="left:27%">線上客服</span></a>
	
	<div style=" position: absolute; top: 50%; transform:translateY(-50%); left: 60%">
	<form method="get" action="<%=request.getContextPath()%>/shop/shop.do">
		<input type="text" name="keyword" placeholder="請輸入商品關鍵字" style="width:250px;">
		<button type="submit" name="action" value="search_prod" style="background-color: #89A3FC; width:40px; margin-left:-5px; border: 1px solid #89A3FC">
		<i class="fas fa-search"></i></button>
	</form>
	</div>


	
	<div class="navbar-cart">
		<a href="<%=request.getContextPath()%>/front-end/cart/step1.jsp">
			<i class="fas fa-shopping-cart"></i>
		</a>
		<div class="nav-cart-item shadow">
			
			<c:if test="${not empty list}">

				<c:forEach var="prodVO" items="${list}">
					<div>
						<a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${prodVO.prodId}">
							<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
							${prodVO.prodName}
							<span class="navbar-price">${prodVO.price}</span>
						</a>
					</div>
				</c:forEach>

					<div class="sp-nav-check">
						<a href="<%=request.getContextPath()%>/front-end/cart/step1.jsp">查看購物車</a>
					</div>

			</c:if>

			<c:if test="${empty list}">
				<div style="color: #6C6C6C; font-size: 14px; width: 200%; height: 40px;">尚未新增商品至購物車</div>

			</c:if>
		</div>
	</div>
	

	
	<div class="mem">
		
	
		<c:if test="${not empty memVO}">
			<div class="row">
				<span class="col-3"><i class="fas fa-user-circle"></i>&nbsp;</span>
				<span class="col-9 left">${memVO.memName}</span>
			</div>
			<div class="mem-select shadow">
				<a href="<%=request.getContextPath()%>/front-end/mem/listOneMem.jsp">我的帳戶</a>
				<a href="<%=request.getContextPath()%>/front-end/mem/order/order_all.jsp">我的訂單</a>
				<a href="<%=request.getContextPath()%>/shop/shop.do?action=get_fav_prod">收藏清單</a>
				<a href="<%=request.getContextPath()%>/login/logOutServlet?action=mem">登出</a>
			</div>
		</c:if>
		
		
		<c:if test="${empty memVO}">
			<a href="<%=request.getContextPath()%>/front-end/mem/login.jsp">	
				<div class="row">
					<span class="col-3"><i class="fas fa-user-circle"></i>&nbsp;</span>
					<span class="col-9 left">會員登入</span>
				</div>
			</a>
		</c:if>

	</div>
	
</div>

<script>

$(function(){
	let num = $('.nav-cart-item').find('a').length;
	num -= 1;
	if (num > 0){
		$('.navbar-cart').find('.fa-shopping-cart').append('<span id="cart-circle">' + num + '</span>');
	}
});

//價格加上千分位符號
$(function(){
	let num = $('.navbar-price').length;
	for (i=0; i<num; i++){
		let price = $('.navbar-price').eq(i).text();
		let show_price = format_with_substring(price);
		$('.navbar-price').eq(i).html('$' + show_price);
	}
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
    }
    if (f === 0) {
        r = r.substring(1);
    }
    return r + (!!fraction ? "." + fraction : '');
};
</script>

</body>
</html>