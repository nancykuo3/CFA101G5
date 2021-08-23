<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productInfo.model.*" %>
<%@ page import="com.product.model.*" %>
<%@ page import="com.shop.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>


<% 
// MemVO memVO = (MemVO) session.getAttribute("memVO");
// MemManagementService memSvc = new MemManagementService();
// MemVO memVO = memSvc.getOneMem(memVO.getmemId());
// pageContext.setAttribute("memVO", memVO);
%>

<jsp:useBean id="prodInfoSvc" scope="page" class="com.productInfo.model.ProdInfoService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/front-end/assets/jquery.twzipcode.min.js"></script>

</head>
<body>

<div id="header" style="height:75px; margin:0; padding:0">
<jsp:include page="/front-end/shop/header.jsp" />
</div>

<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
<input type="hidden" name="count" value="${list.size()}">

<div class="container mt-3 cart">
	<div class="row status-bar">
		<span class="col-12 col-md-4 status">STEP 1. 確認購買清單</span>
		<span class="col-12 col-md-4 status status-active">STEP 2. 填寫運送資料</span>
		<span class="col-12 col-md-4 status">STEP 3. 完成訂購</span>
	</div>
	
		<span class="storeId">
			<a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${storeId}">
				<i class="fas fa-store"></i>&nbsp; ${storeName}
			</a>
		</span>
	
	<table>
		<thead>
			<tr class="row title">
				<td class="col-lg-6">商品資訊</td>
				<td class="col-lg-2">數量</td>
				<td class="col-lg-2">價格</td>
				<td class="col-lg-2">小計</td>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="productVO" items="${list}" varStatus="s">
			<tr class="row item">
			
				<td class="col-4 col-md-2">
					<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${productVO.prodId}" />
				</td>
				
				<td class="col-8 col-md-4 prod_name">
					<input type="hidden" name="prodId${s.count}" value="${productVO.prodId}">	
					<input class="price" type="hidden" name="price${s.count}" value="${productVO.price}">
					<span>${prodInfoSvc.getOneProdInfo(productVO.isbn).prodName}</span>
				</td>
				
				<td class="col-6 col-md-2 td_ordQty">
					<input type="hidden" name="ordQty${s.count}" value="${productVO.ordQty}">
					${productVO.ordQty}
				</td>
				
				<td class="col-3 col-md-2 show_price"></td>
				
				<td class="col-3 col-md-2 subtotal"></td>
				
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="row" id="total"></tr>
			<tr class="row" id="hide_total" style="display:none"></tr>
			<tr class="row" id="show_shipfee"><td>&nbsp;</td></tr>
			<tr class="row" id="show_amount"><td>&nbsp;</td></tr>
			<tr><td>
				<input id="shipfee" type="hidden" name="shipfee">
				<input id="amount" type="hidden" name="amount">
			</td></tr>
			
		</tfoot>
	</table>
</div>

<div class="container mt-3">
	<div class="row title">
		<span><b>運送方式</b></span>	
	</div>
	
	
	<c:forEach var="shipVO" items="${shopSvc.getShipment(storeId)}" varStatus="s">
	<div class="row title ms-4" id="shipType">
		<input id="shiptype${s.count}" type="radio" name="shipType" value="${shipVO.isbn}" required>
		<label for="shiptype${s.count}" class="col-5 col-sm-3 col-md-2">
			<span class="col-3 col-md-2">				
				<c:if test="${shipVO.isbn==1001}">全家取貨付款</c:if>
				<c:if test="${shipVO.isbn==1002}">7-11取貨付款</c:if>
				<c:if test="${shipVO.isbn==1003}">萊爾富取貨付款</c:if>
				<c:if test="${shipVO.isbn==1004}">OK Mart取貨付款</c:if>
				<c:if test="${shipVO.isbn==1005}">賣家宅配</c:if>
			</span>
		</label>
		<span class="col-3 col-md-1">$ <span>${shipVO.price}</span></span>
	</div>
	</c:forEach>
	<span id="ship_info" class="info"></span>
</div>

<div class="container mt-3" id="payType">

	<div class="row title">
		<span><b>付款方式</b></span>
	</div>
	
	<div class="row title ms-4">
		<input id="paytype0" type="radio" name="payType" class="radio" value="0" required>
		<label for="paytype0">
			<span>信用卡線上刷卡</span>
		</label>
	</div>
	<div class="row title ms-4">
		<input id="paytype1" type="radio" name="payType" class="radio" value="1">
		<label for="paytype1">
			<span>銀行轉帳</span>
		</label>
	</div>
	<input id="paytype2" type="radio" name="payType" class="radio" value="2">
	<span id="pay_info" class="info"></span>
</div>


<div class="container mt-3">
	<div class="title">
		<span style="padding-left: 0px"><b>訂購人資料</b></span>
	</div>
	
	<div class="title">

	<ul class="formstyle--horizontal formstyle">
		<li>
			<label class="li_title">*姓名</label> 
			<input id="name" type="text" name="name" placeholder="請輸入姓名" value="${memVO.memName}" maxlength="10" required="required" class="floatLabel text">
			<span id="name_info" class="info"></span>
		</li> 
		<li>
			<label class="li_title">*手機</label> 
			<input id="mobile" type="tel" name="mobile" placeholder="請輸入手機號碼" value="${memVO.memMobile}" maxlength="10" required="required" class="floatLabel text">
			<span id="mobile_info" class="info"></span>
		</li> 
		<li id="cvs_required">
			<label class="li_title">*取件門市</label> 
			<input id="cvs" type="text" name="cvs" placeholder="請輸入取件門市" maxlength="20" required="required" class="floatLabel text">
			<span id="cvs_info" class="info"></span>
		</li> 
		<li id="addr_required">
			<label class="li_title">*地址</label> 
			<div>
				<div class="clearfix mb-2">
					<span id="memCity" style="display:none">${memVO.memCity}</span>
					<span id="memDist" style="display:none">${memVO.memDist}</span>
					<div class="controls-float" id="zipcode"></div> 

			</div> 
			<div class="clearfix">
				<div>
					<div class="controls-float">
						<input id="addr" type="text" name="addr" placeholder="請輸入地址" value="${memVO.memAddr}" maxlength="20" required="required" class="w-50 text"> 
						<span id="addr_info" class="info"></span>
					</div>
				</div>
			</div>
		</div>
		</li> 

		<li>
			<label class="li_title">備註</label> 
			<input id="memo" type="text" name="memo" class="w-50 text">
		</li>
	</ul>
</div>

	<ul class="bottom-btn">
<!-- 		<li > -->
<%-- 			<a href="<%=request.getContextPath()%>/front-end/cart/step1.jsp"><button class="btn btn-gray">回前頁</button></a> --%>
<!-- 		</li> -->
		<li >
			<input type="hidden" name="storeId" value="${storeId}">
			<button id="btnCheck" class="btn check" type="submit" name="action" value="confirm_check">送出訂單</button>
		</li>
	</ul>
</div>

</form>

<script>

let item = ${list.size()};
if (item == 0){
	window.location.href = "chat.jsp";
}

$('#btnCheck').attr('disabled', true);
$('#cvs_required').hide();

//頁面載入後先計算小計和總金額 & 價格加上千分位符號
$(function(){
	//計算小計、總金額
	count();
	//價格加千分位符號
	let num = $('table').find('.item').length;
	for (i=0; i<num; i++){
		let price = $('.item').eq(i).find('.price').val();
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
			let price = $('.item').eq(i).find('.price').val();
			let ordQty = $('.item').eq(i).find('.td_ordQty').text();
			let subtotal = price * ordQty;
			// 小計加千分位符號
			let show_subtotal = format_with_substring(subtotal);
			$('.item').eq(i).find('.subtotal').text(show_subtotal);
			total += subtotal;
		}
		let data = '<td class="col-7 col-sm-9 col-md-10">總計 ' + num + ' 項商品</td>' +
				'<td class="col-5 col-sm-3 col-md-2">NT$ ' + format_with_substring(total) +'</td>';
		$('#total').html(data);
		$('#hide_total').html(total);
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

$('input[name="shipType"]').click(function(){
	let shipFee = $(this).siblings('span').find('span').text();
	let dataShipfee = '<td class="col-7 col-sm-9 col-md-10">運費</td>' +
					'<td class="col-5 col-sm-3 col-md-2">NT$ ' + format_with_substring(shipFee) + '</td>';
	$('#show_shipfee').html(dataShipfee);
	$('#shipfee').val(shipFee);
	let total = $('#hide_total').text();
	let totalAmount = Number(total) + Number(shipFee);
	let dataAmount = '<td class="col-7 col-sm-9 col-md-10"><span>應付金額</span></td>' +
				'<td class="col-5 col-sm-3 col-md-2"><span>NT$ ' + format_with_substring(totalAmount) +  
				'</span></td>';
	$('#show_amount').html(dataAmount);
	$('#amount').val(totalAmount);
});

$('input[name=shipType]').click(function(){
	if ($('input[name=shipType]:checked').val() === "1005"){
		$('#cvs').html('');
		$('#cvs_info').html('');
		$('#cvs').removeAttr('required');
		$('#cvs_required').hide();
		$('#addr_required').show();
		$('.city').attr('required', 'required');
		$('.dist').attr('required', 'required');
		$('#addr').attr('required', 'required');
		$('#paytype0').removeAttr('disabled');
		$('#paytype1').removeAttr('disabled');
		$('#paytype2').attr('checked',false);
		$('#paytype2').attr('disabled',true);
		able_to_check();
	} else {
		$('#cvs_required').show();
		$('#cvs').attr('required', 'required');
		$('#addr').html('');
		$('#addr_info').html('');
		$('.city').removeAttr('required');
		$('.dist').removeAttr('required');
		$('#addr').removeAttr('required');
		$('#addr_required').hide();
		$('#paytype0').attr('disabled',true);
		$('#paytype1').attr('disabled',true);
		$('#paytype2').attr('checked',true);
		able_to_check();
	}
});

let city = $('#memCity').text();
if (city.substring(0, 1) === '台'){
	city = '臺' + city.substring(1, 3);
}
let dist = $('#memDist').text();
$("#zipcode").twzipcode({
	countySel: city, // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
	districtSel: dist, // 地區預設值
	zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
	css: ["city", "dist"], // 自訂 "城市"、"地區" class 名稱 
	countyName: "city", // 自訂城市 select 標籤的 name 值
	districtName: "dist" // 自訂地區 select 標籤的 name 值
});

$("#mobile").blur(function(){
	//獲取手機號,並去除左右兩邊空格
	let mobile = $.trim($("#mobile").val());
	if (is_mobile(mobile)){
		$("#mobile_info").html("");
	} else {
		$("#mobile_info").html("手機號碼格式不正確(ex:0912345678)");
		$('#btnCheck').attr('disabled', true);
// 		return false;
	}
});

$("#mobile").keyup(function(){
	//獲取手機號,並去除左右兩邊空格
	let mobile = $.trim($("#mobile").val());
	if (is_mobile(mobile)){
		$("#mobile_info").html("");
		able_to_check();
	}
});

$("#name").blur(function(){
	let name = $.trim($("#name").val());
	if (is_name(name)){
		$("#name_info").html("");
		$('#btnCheck').attr('disabled', true);
		able_to_check();
	} else {
		$("#name_info").html("姓名格式不正確");
// 		return false;
	}
});

$("#name").keyup(function(){
	let name = $.trim($("#name").val());
	if (is_name(name)){
		$("#name_info").html("");
		able_to_check();
	}
});

$("#addr").blur(function(){
	let addr = $.trim($("#addr").val());
	if (is_name(addr)){
		$("#addr_info").html("");
		$('#btnCheck').attr('disabled', true);
		able_to_check();
	} else {
		$("#addr_info").html("地址格式不正確");
// 		return false;
	}
});

$("#addr").keyup(function(){
	let addr = $.trim($("#addr").val());
	if (is_name(addr)){
		$("#addr_info").html("");
		able_to_check();
	}
});

$("#cvs").blur(function(){
	let addr = $.trim($("#cvs").val());
	if (is_name(addr)){
		$("#cvs_info").html("");
		able_to_check();
	} else {
		$("#cvs_info").html("取貨門市格式不正確");
		$('#btnCheck').attr('disabled', true);
// 		return false;
	}
});

$("#cvs").keyup(function(){
	let addr = $.trim($("#cvs").val());
	if (is_name(addr)){
		$("#cvs_info").html("");
		able_to_check();
	}
});

$("#memo").blur(function(){
	able_to_check();
});

$("#memo").keyup(function(){
	able_to_check();
});

function is_name(name) {
	if(name == "") {
		return false;
	} else {
		if( ! /[^0-9]{2,10}/.test(name) ) {
			return false;
		}
		return true;
	}
}

function is_mobile(mobile) {
	if( mobile == "") {
		return false;
	} else {
		if( ! /^09[0-9]{8}$/.test(mobile) ) {
			return false;
		}
		return true;
	}
}

function able_to_check(){
	let shipType = $('input[name=shipType]:checked').val();
	console.log('shipType: ' + shipType);
	let payType = $('input[name=payType]:checked').val();
	console.log('payType: ' + payType);
	let name = $.trim($("#name").val());
	console.log('name: ' + name);
	let mobile = $.trim($("#mobile").val());
	console.log('mobile: ' + mobile);
	let addr = $.trim($("#addr").val());
	console.log('addr: ' + addr);
	let cvs = $.trim($("#cvs").val());
	console.log('cvs: ' + cvs);
	let info = $('.info').text();
	console.log('info: ' + info);
	if (shipType != null && payType != null && name != '' && mobile != '' && info == ''){
		if ((shipType == '1005' && addr != '') || (shipType != '1005' && cvs != '')){
			$('#btnCheck').attr('disabled', false);
			console.log('unset disabled');
			return;
		}
	} 
			$('#btnCheck').attr('disabled', true);
			console.log('still disable because info is null');
}

$('input[name=payType]').click(function(){
	able_to_check();
});

</script>

</body>
</html>