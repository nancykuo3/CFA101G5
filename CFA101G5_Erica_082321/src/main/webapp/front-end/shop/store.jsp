<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
Integer storeId = new Integer(request.getParameter("store"));
pageContext.setAttribute("storeId", storeId);
%>

<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService"/>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家商品</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/shop-store.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<script src="<%=request.getContextPath()%>/front-end/assets/jquery-1.12.4.min.js"></script>
</head>
<body>

<div id="header" style="height:75px">
    <jsp:include page="header.jsp" />
</div>
            
<div class="container">
    <div class="content mt-5 p-3">
        <div class="subtitle"><i class="fas fa-store-alt"></i>&nbsp;${storeSvc.getOneStore(storeId).storeName}</div>
    </div>	
    <div class="content mt-1 mb-5" style="height: 100%">
        
        <div class="row content-row">
            <c:forEach var="prodVO" items="${shopSvc.getStoreProd(storeId)}" varStatus="s">
            
                <div class="new-card col-3">
                    <a href="<%=request.getContextPath()%>/front-end/shop/product.jsp?prod=${prodVO.prodId}"><div class="prod-card">					
                        <div class="home-img">	
                            <img src="<%=request.getContextPath()%>/shop/shop.do?action=show_pic&prodId=${prodVO.prodId}">
                        </div>
                        <div class="prod-info">
                            <div class="prod-name">${prodVO.prodName}</div>
                            <div class="prod-price">${prodVO.price}</div>
                            <span class="prod-fig">已售出: ${prodVO.salesFig}</span>
                        </div>
                    </div></a>	
                </div>
            </c:forEach>
        </div>
    </div>

</div>


<div id="footer" style="bottom: 0">
    <jsp:include page="footer.jsp" />
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

</script>

</body>
</html>