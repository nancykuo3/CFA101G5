<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/store/order/assets/css/header.css">
</head>
<body>
<nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient">
    <div class="container"><a class="navbar-brand logo" href="#">桌迷．藏</a><button data-bs-toggle="collapse" data-bs-target="#navbarNav-1" class="navbar-toggler"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarNav-1">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active" href="#">首頁</a></li>
                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/store/order/order_all.jsp">訂單管理</a></li>
                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/test/login.do?action=logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>