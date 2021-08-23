<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>桌迷藏購物商城</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/shop-store.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<script src="<%=request.getContextPath()%>/front-end/assets/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/shop/include.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
</head>

<body>

<div id="header" style="height:75px">
<jsp:include page="header.jsp" />
</div>

<div class="top-area">
	<div class="mt-3 top-bar">
		<div class="row type-bar">

			<div class="col-3 type-list">適合對象
				<div class="select-type type1">
				</div>
			</div>

			<div class="col-3 type-list">適合人數
				<div class="select-type type2">
				</div>
			</div>

			<div class="col-3 type-list">標籤
				<div class="select-type type3">
				</div>
			</div>

<!-- 			<div class="col-3 type-list">價格 -->
<!-- 				<div class="select-type"> -->
<!-- 					<a class="type-opt" href="#">~500元</a> -->
<!-- 					<a class="type-opt" href="#">500~1000元</a> -->
<!-- 					<a class="type-opt" href="#">1000~1500元</a> -->
<!-- 					<a class="type-opt" href="#">1500~2000元</a> -->
<!-- 					<a class="type-opt" href="#">2000元~</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>
</div>


<div class="container">

	<div class="content mt-5 p-3">
		<div class="subtitle"><i class="fas fa-trophy"></i>&nbsp;暢銷排行</div>
	</div>

	<div class="content mt-1 p-3">
		<div class="mt-3">

			<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
				
				<div class="carousel-inner">
					
					<div class="carousel-item active">
						<div class="row item-row">
							<c:forEach var="prodVO" items="${shopSvc.listTopProd()}" varStatus="s" begin="0" end="3">
							<div class="col-3 card-row">
								<div class="card item">	
									<div class="store"><a href="store.jsp?store=${prodVO.storeId}"><i class="fas fa-store"></i>&nbsp;${prodVO.storeName}</a></div>
									<div class="prod-card"><a href="product.jsp?prod=${prodVO.prodId}">
										<div class="home-img">	
											<div class="bookmark">
												<i class="fas fa-bookmark"></i>
												<span class="top">TOP</span>
												<span class="rank">${s.count}</span>
											</div>
											<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
										</div>
										<div class="prod-info">
											<div class="prod-name">${prodVO.prodName}</div>
											<span class="prod-price">${prodVO.price}</span>
											<span class="prod-fig">已售出: ${prodVO.salesFig}</span>
										</div>
									</a></div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				
					<div class="carousel-item">
						<div class="row item-row">
							<c:forEach var="prodVO" items="${shopSvc.listTopProd()}" varStatus="s" begin="4" end="7">
							<div class="col-3 card-row">
								<div class="card item">	
									<div class="store"><a href="store.jsp?store=${prodVO.storeId}"><i class="fas fa-store"></i>&nbsp;${prodVO.storeName}</a></div>
									<div class="prod-card"><a href="product.jsp?prod=${prodVO.prodId}">
										<div class="home-img">	
											<div class="bookmark">
												<i class="fas fa-bookmark"></i>
												<span class="top">TOP</span>
												<span class="rank">${s.count+4}</span>
											</div>
											<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
										</div>
										<div class="prod-info">
											<div class="prod-name">${prodVO.prodName}</div>
											<span class="prod-price">${prodVO.price}</span>
											<span class="prod-fig">已售出: ${prodVO.salesFig}</span>
										</div>
									</a></div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>	
				
				<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>

			</div>

		</div>
	</div>

</div>




<div class="container">

	<div class="content mt-5 p-3">
		<div class="subtitle"><i class="fas fa-bullhorn"></i>&nbsp;最新商品</div>
	</div>	
	<div class="content mt-1 new-prod">
		
		<div class="row new-prod-row">
			<c:forEach var="prodVO" items="${shopSvc.latestProd()}" varStatus="s">
				<div class="new-card">
					<div class="store">
						<a href="store.jsp?store=${prodVO.storeId}">
							<i class="fas fa-store"></i>&nbsp;${prodVO.storeName}
						</a>
					</div>
					<a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${prodVO.prodId}"><div class="prod-card">					
						<div class="home-img">	
							<img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
						</div>
						<div class="prod-info">
							<div class="prod-name">${prodVO.prodName}</div>
							<div class="prod-price">${prodVO.price}</div>
						</div>
					</div></a>	
				</div>
			</c:forEach>
		</div>
	</div>

</div>

<div style="background-color: black; margin-top: 50px; padding: 20px">

	<div style="background-color: black;" class="content p-3">
		<div class="subtitle"><i class="fas fa-store-alt"></i>&nbsp;熱門店家</div>
	</div>

	<div style="background-color: black; margin: 20px" class="content mt-1 p-3">
		<div class="row pb-3">
			<c:forEach var="storeVO" items="${shopSvc.getTopThreeStore()}" varStatus="s">
				<div class="col-4 store-list mt-3">
					<a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${storeVO.storeId}">
						<div class="store-card">
							<span>${storeVO.storeName}</span>
							<div class="store-img">
								<img src="../../image/shop_homepage_1.jpeg">
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>

</div>

<div id="header" style="height:75px">
<jsp:include page="footer.jsp" />
</div>


<script>
var myCarousel = document.querySelector('#carouselExampleControls')
var carousel = new bootstrap.Carousel(myCarousel, {
  interval: 5000,
  wrap: true
});

$('.store-list').hover(function(){
	$(this).find('.store-img>img').css('opacity', '.3')
}, function(){
	$(this).find('.store-img>img').css('opacity', '.7')
});

//價格加上千分位符號
$(function(){
	let num = $('.prod-price').length;
	console.log(num);
	for (i=0; i<num; i++){
		let price = $('.prod-price').eq(i).text();
		let show_price = format_with_substring(price);
		$('.prod-price').eq(i).html('$' + show_price);
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

$(function(){
	$.ajax({
		url: '/CFA101G5/shop/shop.do',
		type: 'GET',
		data: { 'action': 'get_type' },
		dataType: 'json',
		success: function(data){

			let htmlA = '';
			for (let i = 0; i < data.listA.length; i++) {
				let itemA = '<a href="/CFA101G5/front-end/shop/type.jsp?type=' + data.listA[i]["typeid"] + '">' + data.listA[i]["typeName"] + '</a>';
				htmlA = htmlA + itemA;
			}
			console.log(htmlA);
				$('.type1').html(htmlA);
				
			let htmlB = '';
			for (let i = 0; i < data.listB.length; i++) {
				let itemB = '<a href="/CFA101G5/front-end/shop/type.jsp?type=' + data.listB[i]["typeid"] + '">' + data.listB[i]["typeName"] + '</a>';
				htmlB = htmlB + itemB;
			}
			console.log(htmlB);
				$('.type2').html(htmlB);
				
			let htmlC = '';
			for (let i = 0; i < data.listB.length; i++) {
				let itemC = '<a href="/CFA101G5/front-end/shop/type.jsp?type=' + data.listC[i]["typeid"] + '">' + data.listC[i]["typeName"] + '</a>';
				console.log(itemC);
				htmlC = htmlC + itemC;
			}
			console.log(htmlC);
				$('.type3').html(htmlC);
		},
		error: function(){alert('error');}
	});
});

</script>
</body>
</html>