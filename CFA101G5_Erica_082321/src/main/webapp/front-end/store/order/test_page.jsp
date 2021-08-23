<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>訂單管理-店家</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/store/order/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/store/order/assets/css/test_login.css">
    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

</head>
<body>

<jsp:include page="header.jsp" />

	
<form method="post" action="<%=request.getContextPath()%>/test/login.do">
<div class="col-md-4">
    <div class="card special-skill-item border-0">
        <div class="card-header bg-transparent border-0"><i class="icon ion-log-in"></i></div>
        <div class="card-body">
            <h3 class="card-title">LOG IN</h3>
            <p class="card-text" style="color: red;">	
            	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							<br><p style="color:red">${message}</p>
						</c:forEach>
				</c:if>
			</p>
            <p class="card-text">店家編號</p>
            <input type="text" name="storeId" placeholder="請輸入店家編號">
			<button class="btn" type="submit" name="action" value="check_storeId">登入</button>
        </div>
    </div>
</div>
</form>

</body>
</html>