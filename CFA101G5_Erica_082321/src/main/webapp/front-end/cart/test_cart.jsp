<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*" %>


    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>

<style>
img{
height: 150px;
weight: 150px;
}
</style>
</head>
<body>

          
<div>
<input type="hidden" name="prodId" value="1000001">1000001
<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=1000001" />
<input type="hidden" name="storeId" value="30001">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000002">1000002
<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=1000002" />
<input type="hidden" name="storeId" value="30001">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000003">1000003
<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=1000003" />
<input type="hidden" name="storeId" value="30001">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000004">1000004
<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=1000004" />
<input type="hidden" name="storeId" value="30001">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000005">1000005
<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=1000005" />
<input type="hidden" name="storeId" value="30001">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000006">1000006
<input type="hidden" name="storeId" value="30002">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000007">1000007
<input type="hidden" name="storeId" value="30002">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>

<div>
<input type="hidden" name="prodId" value="1000008">1000008
<input type="hidden" name="storeId" value="30002">
<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
<button class="cart" type="submit" name="action" value="add_to_cart">加入購物車</button>
</div>




<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
	<button type="submit" name="action" value="clear_all">清空購物車</button>
</form>


<script>

$('.cart').click(function(){
	let prodId = $(this).siblings('input[name="prodId"]').val();

	console.log(prodId);
	let storeId = $(this).siblings('input[name="storeId"]').val();
	console.log(storeId);

	
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
				window.location.href = "step1.jsp";
			} else if (data.status === "return"){
				if (confirm('購物車已有其他店家的商品．您是否想要清空購物車？')){
					console.log(prodId);
					remove_before_add(prodId);
				} else {
					console.log('no');
					return;
				}
			}
		},
		error: function(){alert("error")}
	})
})

function remove_before_add(prodId){
	$.ajax({
		url: '/CFA101G5/cart/cart.do',
		type: 'GET',
		data: {'action': 'remove_before_add',
				'prodId': prodId},
		dataType: 'json',
		success: function(){
			window.location.href = 'step1.jsp';
		},
		errer: function(){alert('error')}
	})
}

</script>

</body>
</html>