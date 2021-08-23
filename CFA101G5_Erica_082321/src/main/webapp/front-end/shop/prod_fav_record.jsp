<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>分類搜尋</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/shop-store.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<script src="<%=request.getContextPath()%>/front-end/assets/jquery-1.12.4.min.js"></script>
</head>

<body>

<div id="header" style="height:75px">
    <jsp:include page="header.jsp" />
</div>

<!-- <div class="top-area"> -->
<!-- <div class="mt-3 top-bar"> -->
<!-- <div class="row type-bar"> -->
<!-- 		<div class="col-3 type-list">適合對象 -->
<!-- 		    <div class="select-type type1"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-3 type-list">適合人數 -->
<!-- 			<div class="select-type type2"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-3 type-list">標籤 -->
<!-- 			<div class="select-type type3"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-3 type-list">價格 -->
<!-- 			<div class="select-type"> -->
<!-- 			    <a class="type-opt" href="#">~500元</a> -->
<!-- 				<a class="type-opt" href="#">500~1000元</a> -->
<!-- 				<a class="type-opt" href="#">1000~1500元</a> -->
<!-- 				<a class="type-opt" href="#">1500~2000元</a> -->
<!-- 				<a class="type-opt" href="#">2000元~</a> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- </div> -->
<!-- </div> -->
<!-- </div> -->
   
<div class="container">
    <div class="content mt-5 p-3">
        <div class="subtitle">收藏清單</div>
    </div>	
    <div class="content mt-1 new-prod">
        
        <div class="row new-prod-row">
            <c:forEach var="prodVO" items="${favList}" varStatus="s">

                <c:if test="${prodVO.status == 1}">
                    
                    <div class="new-card col-3">
                        <div class="store"><a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${prodVO.storeId}"><i class="fas fa-store"></i>&nbsp;${prodVO.storeName}</a></div>
                        <button class="remove-fav">x</button>
                        <a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${prodVO.prodId}"><div class="prod-card">					
                            <div class="home-img">	
                                <img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
                            </div>
                            <div class="prod-info">
                                <div id="prod-id" style="display:none">${prodVO.prodId}</div>
                                <div class="prod-name">${prodVO.prodName}</div>
                                <div class="prod-price">${prodVO.price}</div>
                                <span class="prod-fig">已售出: ${prodVO.salesFig}</span>
                            </div>
                        </div></a>	
                    </div>
                </c:if>
            
                <c:if test="${prodVO.status == 3}">
                    <div class="new-card col-3">
                        <div class="store"><a href="<%=request.getContextPath()%>/front-end/shop/store.jsp?store=${prodVO.storeId}"><i class="fas fa-store"></i>&nbsp;${prodVO.storeName}</a></div>
                        <button class="remove-fav">x</button>
                        <div class="prod-card">					
                            <div class="home-img">	
                                <img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
                            </div>
                            <span style="text-align:center;opacity: .6; color: #fff; line-height: 110px; width:90%; height: 120px;font-size: 32px; position: absolute; top: 20%; z-index: 5; background-color: #6C6C6C;">商品已下架</span>
                            <div class="prod-info">
                                <div id="prod-id" style="display:none">${prodVO.prodId}</div>
                                <div class="prod-name">${prodVO.prodName}</div>
                                <div class="prod-price">${prodVO.price}</div>
                                <span class="prod-fig">已售出: ${prodVO.salesFig}</span>
                            </div>
                        </div>	
                    </div>
                </c:if>


            </c:forEach>
        </div>
    </div>

</div>

    
</div>

<script>
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


$('.remove-fav').click(function(){
	let prodId = $(this).parent().find('#prod-id').text();
	$(this).parent('.new-card').attr('id', 'this-card');
	
	$.ajax({
		url: '/CFA101G5/shop/shop.do',
		type: 'GET',
		data: {'action': 'remove_fav',
				'prodId': prodId },
		dataType: 'json',
		success: function(data){
			console.log(data);
			$('#this-card').remove();
			$('#this-card').removeAttr('id');
		},
		error: function(){alert("error")}
	})
});

</script>
</body>
</html>