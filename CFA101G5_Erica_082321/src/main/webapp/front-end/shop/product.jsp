<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Integer prodId = new Integer(request.getParameter("prod"));
pageContext.setAttribute("prodId", prodId);
%>

<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService"/>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService"/>
<c:set var="prodVO" value="${shopSvc.getProdDetail(prodId)}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>桌迷藏購物商城</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/shop_page.css">
<script src="<%=request.getContextPath()%>/front-end/assets/jquery-1.12.4.min.js"></script>
 
 <style>
 #alert-cart{
 	width: 150px;
 	height: 50px;
 	background-color: #000000;
 	color: #fff;
 	padding: 15px;
 	font-size: 14px;
 	position: fixed;
 	top: 15%;
 	right: 1%;
 	opacity: .8;
 }
 </style>
 
</head>

<body>

<div id="header" style="height:75px"></div>




<div class="bottom-bar">
  <button class="bottom-bar-btn add-to-fav" type="submit" style="border-right:2px solid #82A1C2">
  	<i class="far fa-heart"></i><span class="btn-hide-word">&ensp;收藏</span>
  </button>
  <button class="bottom-bar-btn add-to-cart" type="submit">
  	<i class="fas fa-shopping-cart"></i><span class="btn-hide-word">&ensp;加入購物車</span>
  </button>
  <button class="bottom-bar-btn bottom-btn-primary enter-cart" type="submit">直接購買</button>
</div>


<main class="page projects-page mt-5">
  <section class="portfolio-block projects-with-sidebar">
    <div class="container">
    
	    <div class="content mt-5 p-3">
	        <div class="storeId">
	        	<a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${prodVO.storeId}">
            		<i class="fas fa-store-alt"></i>&nbsp;${storeSvc.getOneStore(prodVO.storeId).storeName}
           		 </a>
	        </div>
	    </div>
    
      <div class="row">

        <div class="col-sm-12 col-md-5 col-lg-4 project-sidebar-card" style="padding:10px">
        	<div class="img_bax" style="height:300px; width:300px;max-width:100%; max-height:100%; margin:0 auto;">
        	<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodId}" style="width:100%;">
        	</div>
        </div>
        
        <div class="col-sm-12 col-md-7 col-lg-5 project-sidebar-card"> 
          <ul class="prod_info">
          	<li id="prod-id" style="display:none">${prodVO.prodId}</li>
          	<li id="store-id" style="display:none">${prodVO.storeId}</li>
            <li><span class="prod_title">${prodVO.prodName}</span></li>
            <li>版本: ${prodVO.prodVer}</li>
            <li>語言: ${prodVO.prodLang }</li>
            <li>庫存: ${prodVO.prodQty}</li>
            <li>售價:&ensp;<span id="price">${prodVO.price}</span></li>
            
            <li>運送方式: 
          <c:forEach var="shipVO" items="${shopSvc.getShipment(prodVO.storeId)}">
	
				<c:if test="${shipVO.isbn==1001}"><br>全家取貨付款 $ ${shipVO.price}<br> </c:if>
				<c:if test="${shipVO.isbn==1002}">7-11取貨付款 $ ${shipVO.price}<br> </c:if>
				<c:if test="${shipVO.isbn==1003}">萊爾富取貨付款 $ ${shipVO.price}<br> </c:if>
				<c:if test="${shipVO.isbn==1004}">OK Mart取貨付款 $ ${shipVO.price}<br> </c:if>
				<c:if test="${shipVO.isbn==1005}">賣家宅配 $ ${shipVO.price}<br> </c:if>
			
		</c:forEach></li>
<!--             <li>付款方式: 超商取貨付款</li> -->
          </ul>
            <ul class="prod_tag">
            	<c:forEach var="typeVO" items="${shopSvc.getProdType(prodVO.isbn)}">
                <a href="<%=request.getContextPath()%>/front-end/shop/type.jsp?type=${typeVO.typeid}">
				        	<li class="tag"> # ${typeVO.typeName} </li>
                </a>
				</c:forEach>
			</ul>

        </div>

        <div class="col-md-12 col-lg-3 project-sidebar-card right-bar">
				<button class="right-bar-btn add-to-fav" type="submit" name="action" value="add_to_fav_list">
            	<i class="far fa-heart"></i>&ensp;加入收藏</button>
            	
            	<input type="hidden" name="sum" value="1">
            	<button class="right-bar-btn add-to-cart" type="submit" name="action" value="add_to_cart">
            	<i class="fas fa-shopping-cart"></i>&ensp;加入購物車</button>
            	
            	<button class="right-bar-btn right-bar-primary enter-cart" type="submit" name="action" value="go_to_cart">
            	直接購買</button>
		</div>
      
  
      <div class="row mb-5">
      <div id="prod-pics"></div>
      <span style="color:#1B71C0; line-height: 40px; font-size: 20px;">商品簡介</span>
        <div class="project-sidebar-card prod_intro"> ${prodVO.intro}
       </div>
      </div>

    </div>
  </section>  
</main>  

<div id="header" style="height:75px">
<jsp:include page="footer.jsp" />
</div>

<script>

//價格加上千分位符號
$(function(){
	let price = $('#price').text();
	let show_price = format_with_substring(price);
	$('#price').html('$' + show_price);
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


$(function(){
	let prodId = $('#prod-id').text();
	$.ajax({
		url: '/CFA101G5/shop/shop.do',
		type: 'GET',
		data: {'action': 'get_pics_id',
				'prodId': prodId},
		dataType: 'json',
		success: function(data){

			let html = '';
			for (let i = 0; i < data.picId.length; i++){
				html += '<img style="width: 350px" src ="/CFA101G5/shop/shop.do?action=show_prod_pic&prodPicId=' + data.picId[i] + '">';
			console.log(html);
			}
			console.log(html); 
			$('#prod-pics').html(html);

		},
		error: function(){alert("error")}
	});
});

$(function(){
	load_header();
	
});

$('.add-to-fav').click(function(){
	let prodId = $('#prod-id').text();
	console.log('prod id =' + prodId);
	
	$.ajax({
		url: '/CFA101G5/shop/shop.do',
		type: 'GET',
		data: {'action': 'add_to_fav',
				'prodId': prodId},
		dataType: 'json',
		success: function(data){
			console.log(data);
			fav_alert(data);
// 			window.location.href = "/CFA101G5/front-end/cart/step1.jsp";

		},
		error: function(){alert("error")}
	});
});



$('.enter-cart').click(function(){
	let prodId = $('#prod-id').text();
	let storeId = $('#store-id').text();

	$.ajax({
		url: '/CFA101G5/cart/cart.do',
		type: 'GET',
		data: {'action': 'add_to_cart',
				'prodId': prodId,
				'storeId': storeId
				},
		dataType: 'json',
		success: function(data){
			if (data.status === "success"){
				console.log("success");
				window.location.href = "/CFA101G5/front-end/cart/step1.jsp";
			} else if (data.status === "return"){
				if (confirm('購物車已有其他店家的商品．您是否想要清空購物車？')){
					console.log(prodId);
					remove_before_add(prodId, 'enter-cart');
				} else {
					console.log('no');
					return;
				}
			}
		},
		error: function(){alert("error")}
	});
});

$('.add-to-cart').click(function(){
	let prodId = $('#prod-id').text();
	let storeId = $('#store-id').text();

	$.ajax({
		url: '/CFA101G5/cart/cart.do',
		type: 'GET',
		data: {'action': 'add_to_cart',
				'prodId': prodId,
				'storeId': storeId
				},
		dataType: 'json',
		success: function(data){
			if (data.status === "success"){
				load_header();
				cart_alert();
				
			} else if (data.status === "return"){
				if (confirm('購物車已有其他店家的商品．您是否想要清空購物車？')){
					console.log(prodId);
					remove_before_add(prodId, 'add-to-cart');

				} else {
					return;
				}
			}
		},
		error: function(){alert("error")}
	})
})


function load_header() {
    $.get("header.jsp",function (header) {
        $("#header").html(header);
    });
};



function remove_before_add(prodId, btn_name){
	$.ajax({
		url: '/CFA101G5/cart/cart.do',
		type: 'GET',
		data: {'action': 'remove_before_add',
				'prodId': prodId},
		dataType: 'json',
		success: function(data){
			if (btn_name === "enter-cart"){
				window.location.href = '/CFA101G5/front-end/cart/step1.jsp';
			} else {
				load_header();
				cart_alert();
			}
		},
		errer: function(){alert('error')}
	});
};

function cart_alert(){
	if ($('#alert-cart').length === 0){
		$('body').append('<div id="alert-cart">商品已加入購物車!</div>');
	}
	$('#alert-cart').fadeOut(2000, function(){
	$('#alert-cart').remove();
	});
};

function fav_alert(status){
	if (status === 0){
		$('body').append('<div id="alert-cart">您已收藏過此商品!</div>');
	} else {
		$('body').append('<div id="alert-cart">加入收藏成功!</div>');
	}
	$('#alert-cart').fadeOut(2000, function(){
	$('#alert-cart').remove();
	});
};

</script>
            
                             
             
</body>
</html>