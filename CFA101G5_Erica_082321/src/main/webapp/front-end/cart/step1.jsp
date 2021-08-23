<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productInfo.model.*" %>
<%@ page import="com.product.model.*" %>
<%@ page import="java.util.Vector" %>


<% 
Vector<ProductVO> list = (Vector<ProductVO>) session.getAttribute("list");
Integer storeId = null;
String storeName = null;
if (list != null) {
	ProductService prodSvc = new ProductService();
	for (ProductVO existVO: list){
		ProductVO prodVO = prodSvc.getOneProduct(existVO.getProdId());
		existVO.setStatus(prodVO.getStatus());
		existVO.setPrice(prodVO.getPrice());
		existVO.setProdQty(prodVO.getProdQty());
		storeId = existVO.getStoreId();
		storeName = existVO.getStoreName();
	}
	pageContext.setAttribute("storeId", storeId);
	pageContext.setAttribute("storeName", storeName);
}

%>
<jsp:useBean id="prodInfoSvc" scope="page" class="com.productInfo.model.ProdInfoService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>

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

<div id="header" style="height:75px; margin:0; padding:0">
<jsp:include page="/front-end/shop/header.jsp" />
</div>

<div class="container cart" style="margin-top:30px">

	<div class="row status-bar">
		<span class="col-12 col-md-4 status status-active">STEP 1. 確認購買清單</span>
		<span class="col-12 col-md-4 status">STEP 2. 填寫運送資料</span>
		<span class="col-12 col-md-4 status">STEP 3. 完成訂購</span>
	</div>
	
	<c:if test="${not empty list}">
		<span class="storeId">
			<a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${storeId}">
				<i class="fas fa-store"></i>&nbsp; ${storeName}
			</a>
		</span>
	</c:if>
	
	<table>
		<thead>
			<tr class="row">
				<td class="col-lg-5">商品資訊</td>
				<td class="col-lg-2">數量</td>
				<td class="col-lg-1">價格</td>
				<td class="col-lg-1">小計</td>
				<td class="col-lg-1">庫存</td>
				<td class="col-lg-1">收藏</td>
				<td class="col-lg-1">刪除</td>
			</tr>
		</thead>
	<c:if test="${empty list}">
	<tr class="row" style="padding: 20px"><td>尚未新增商品至購物車<td></tr>
	</c:if>
	<c:if test="${not empty list}">
		<tbody>
			<c:forEach var="productVO" items="${list}">
			<tr class="row item">
				<td class="prodId" style="display:none">${productVO.prodId}</td>
				<td class="col-4 col-lg-2" style="vertical-align:middle">
					<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${productVO.prodId}" />
				</td>
				<td class="col-8 col-lg-3 prod_name" style="margin-top:-20px;">
					<span><a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${productVO.prodId}">${prodInfoSvc.getOneProdInfo(productVO.isbn).prodName}</a></span>
				</td>

				<td class="col-6 col-md-3 col-lg-2 td_ordQty">
					<button class="btn btn-sm subOne" type="submit" value="sub_one"><i class="fas fa-minus"></i></button>
					<input style="display: none;" class="curr_ordQty" type=text name="curr_ordQty" value="${productVO.ordQty}">
					<input class="ordQty" type=text name="ordQty" value="${productVO.ordQty}">
	
					<c:if test="${productVO.prodQty == productVO.ordQty}">
						<button class="btn btn-sm disabled addOne" type="submit" value="add_one" ><i class="fas fa-plus"></i></button>
					</c:if>
					<c:if test="${productVO.prodQty < productVO.ordQty}">	
						<button class="btn btn-sm disabled addOne" type="submit" value="add_one" ><i class="fas fa-plus"></i></button><br>
						<span>請修改訂購數量</span>
					</c:if>
					<c:if test="${productVO.prodQty > productVO.ordQty}">
						<button class="btn btn-sm addOne" type="submit" value="add_one" ><i class="fas fa-plus"></i></button>
					</c:if>
				</td>
				<td class="price" style="display:none">${productVO.price}</td>
				<td class="col-3 col-md-2 col-lg-1 show_price"></td>
				<td class="col-3 col-md-2 col-lg-1 subtotal"></td>
				
				<td class="col-6 col-md-2 col-lg-1"><span class="hidden">庫存: </span>${productVO.prodQty}</td>
				<td class="col-3 col-md-2 col-lg-1">
					<button class="btn likeOne" type="submit"><i class="fas fa-heart"></i></button>
				<td class="col-3 col-md-1 col-lg-1">
					<button class="btn deleteOne" type="submit"><i class="fas fa-times"></i></button>
				</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="row" id="total"></tr>
		</tfoot>
	</table>




	<div class="row">
			<ul class="bottom-btn">
	
			<li class="col-6 col-sm-4 col-md-6 col-lg-8">
		<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
			<button class="btn btn-gray" type="submit" name="action" value="clear_all">清空購物車</button>
		</form>
			</li>
			<li class="col-6 col-sm-4 col-md-3 col-lg-2">
			<a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${storeId}"><button class="btn btn-gray" style="float:right" type="submit" name="action" value="check">繼續選購</button></a>
			</li>
			<li class="col-12 col-sm-4 col-md-3 col-lg-2">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
			<button class="btn check" type="submit" name="action" value="check">結帳</button>
		</form>
			</li>

		</ul>

	</div>
	</c:if>

</div>

<script>

	//顯示店家名稱
	$(function(){

	});

	//頁面載入後先計算小計和總金額 & 價格加上千分位符號
	$(function(){
		//計算小計、總金額
		count();
		//價格加千分位符號
		let num = $('table').find('.item').length;
		for (i=0; i<num; i++){
			let price = $('.item').eq(i).find('.price').text();
			let show_price = format_with_substring(price);
			$('.item').eq(i).find('.show_price').text(show_price);
		}
	});
	
	//方法-計算小計和總金額
	function count(){
		let num = $('table').find('.item').length;
		console.log(num);
		let total = 0;
			for (i=0; i<num; i++){
				let price = $('.item').eq(i).find('.price').text();
				let ordQty = $('.item').eq(i).find('.ordQty').val();
				let subtotal = price * ordQty;
				// 小計加千分位符號
				let show_subtotal = format_with_substring(subtotal);
				$('.item').eq(i).find('.subtotal').text(show_subtotal);
				total += subtotal;
			}
		$('#total').html("<td>總計 <span>"+num+"</span> 項商品, <span>NT$ "+format_with_substring(total)+"</td>");
	}
	
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
	}
	
	$(".ordQty").blur(function(){
		let prodId = $(this).parent().parent('.item').find('.prodId').text();
		//若更改數量為0，詢問是否移除商品
		if ($(this).val() === "0"){
			if (confirm('確定移除此項商品?')){
				$(this).parent().parent('.item').attr('id','del_item');
				deleteItem(prodId);
				return;
			} else {
				let curr_ordQty = $(this).siblings('.curr_ordQty').val();
				$(this).val(curr_ordQty);
				return;
			}
		}
		$(this).siblings('.addOne').attr('id', 'addOne_btn')
		$(this).attr('id', 'this_ordQty');
		console.log('prodId= ' + prodId);
		console.log('#this_ordQty= ' + $('#this_ordQty').val());
		
		$.ajax({
			url: "/CFA101G5/cart/cart.do",
			type: "GET",
			data: {'action': 'adj_ordQty',
					'prodId': prodId,
					'ordQty': $('#this_ordQty').val()
					},
			dataType: 'json',
			success: function(data){
				console.log(data);
				let ordQty = data.ordQty;
 				let prodQty = data.prodQty;
				
				if (data.status === "failure"){
					alert("商品庫存量不足");
					$('#this_ordQty').val(ordQty);
				} 
// 				else if (data.status === "return"){
// 					$('#this_ordQty').val(ordQty);			
// 				}
				if (ordQty <= prodQty){
					$('#this_ordQty').siblings('span').remove();
				}
				if (ordQty < prodQty){
					$('#addOne_btn').attr('class', 'btn btn-sm addOne');
				}
				$('#this_ordQty').removeAttr('id');
				$('#addOne_btn').removeAttr('id');
				count();
			},
			error:  function(){window.location.reload();}
		});
	});

	//數量+1
	$(".addOne").click(function(){
// 		$(this).siblings('input[name="ordQty"]').attr('id','add_ordQty');
		$(this).attr('id', 'addOne_btn');
		$(this).siblings('.ordQty').attr('id','add_ordQty');
		console.log($('#add_ordQty').val());
		
		let prodId = $(this).parent().parent('.item').find('.prodId').text();
		console.log(prodId);
	
		$.ajax({
			url: "/CFA101G5/cart/cart.do",
			type: "GET",
			data: { 'action': $(this).val(), 
					'prodId': prodId, 
					'ordQty': $('#add_ordQty').val() },
			dataType: 'json',
			success: function(data){
				console.log(data);
				let ordQty = data.ordQty;
				let prodQty = data.prodQty;
				console.log('購買數量: ' + ordQty);
				console.log('庫存數量: ' + prodQty);
				
				//因庫存量不足，購買數量沒有更改
				if (data.status !== "success"){
					//如原購買數量 ＝ 庫存量 
					if (ordQty === prodQty){
 						alert('商品庫存量不足');
					} else { 
					//原購買數量 > 庫存量	
						let errMsg = "<br><span>請修改訂購數量</span>";
						$('#addOne_btn').after(errMsg);
					}
					$('#addOne_btn').attr('class', 'btn btn-sm disabled addOne');
				} else {
					$('#add_ordQty').attr({"value": ordQty});
					//若購買數量 ＝ 庫存量 ->將增加數量按鈕改為disabled
					if (ordQty === prodQty){
						$('#addOne_btn').attr('class', 'btn btn-sm disabled addOne');
					}
				}
				$('#add_ordQty').removeAttr('id');
				$('#addOne_btn').removeAttr('id');
				count();
			},
			error:  function(){window.location.reload();}
		});
	});
	
	//數量-1
	$(".subOne").click(function(){
		$(this).siblings('.addOne').attr('id', 'addOne_btn')
		$(this).siblings('input[name="ordQty"]').attr('id','sub_ordQty');
		let sub_ordQty = $('#sub_ordQty').val();
		let prodId = $(this).parent().parent('.item').find('.prodId').text();
		
		//若數量原本是1，詢問是否移除此項品項
		if (sub_ordQty === '1') {
			if (confirm('確定移除此項商品?')){
				$(this).parent().parent('.item').attr('id','del_item');
				deleteItem(prodId);
				return;
			} else {
				return;
			}
		}
		
		//若數量不是1，執行數量-1
		$.ajax({
			url: "/CFA101G5/cart/cart.do",
			type: "GET",
			data: { 'action': $(this).val(), 
					'prodId': prodId, 
					'ordQty': $('#sub_ordQty').val() },
			dataType: "json",
			success: function(data){
				console.log(data);
				let ordQty = data.ordQty;
				let prodQty = data.prodQty;
				console.log('購買數量: ' + ordQty);
				console.log('庫存數量: ' + prodQty);
				//如購買數量-1後，等於庫存量 -> 去掉庫存量不足的錯誤訊息
				if (ordQty === prodQty){
					$('#addOne_btn').siblings('span').remove();
				}
				//如購買數量-1後，少於庫存量 -> 去掉增加數量按鈕的disabled設定
				if (ordQty < prodQty){
					$('#addOne_btn').attr('class', 'btn btn-sm addOne');
				}
				$('#sub_ordQty').attr({"value": ordQty});
				$('#sub_ordQty').removeAttr('id');
				$('#addOne_btn').removeAttr('id');
				count();
			},
			error:  function(){window.location.reload();}
		});
	});

	//刪除某品項
	$('.deleteOne').click(function(){
		$(this).parent().parent('.item').attr('id','del_item');
		let prodId = $(this).parent().parent('.item').find('.prodId').text();
		console.log(prodId);
		deleteItem(prodId);
	});
	
	//方法-刪除某品項
	function deleteItem(prodId){
		$.ajax({
			url: "/CFA101G5/cart/cart.do",
			type: "GET",
			data: {'action': 'delete_item',
					'prodId': prodId},
			dataType: "json",
			success: function(){
				$('#del_item').remove();
				if ($('table').find('.item').length === 0){
					window.location.reload();
					return;
				}
				count();
			},
			error: function(){
				window.location.reload();
			}
		});
	}
	
	$('.likeOne').click(function(){
		let prodId = $(this).parent().siblings('.prodId').text();
// 		let prodId = $('.prodId').text();
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
//	 			window.location.href = "/CFA101G5/front-end/cart/step1.jsp";

			},
			error: function(){alert("error")}
		});
	});
	
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