<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>桌迷藏首頁</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/fontawesome-all.min.css">
<script src="<%=request.getContextPath()%>/front-end/assets/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>

<style>

.home-icon{
	padding:0px 20px;
}

.home-icon img{
	max-width: 100%;
	max-height: 100%;
}

.icon-border:hover{
	opacity: .5;
}

.icon-border{
	border:4px solid #000000; 
	border-radius:20px; 
	padding: 30px;
}

.icon-name{
	padding: 5px 0px 5px 15px;
	text-align: center;
	font-size: 20px;
	letter-spacing: 18px;
}

.home-bottom{
	text-align: center; 
	padding-top:40px;
}

.home-bottom span{
	padding: 0px 25px;
}

.home-bottom a{
	text-decoration: none;
	color: #fff;
	font-size: 16px;
}

.home-bottom a:hover{
	color: #6C6C6C;
}

</style>

</head>

<body>

<div id="header" style="height:70px">
<jsp:include page="/front-end/header-mem.jsp" />
</div>

<div class="page-area" style="margin-top: 150px;">
	<div class="row type-bar" style="width:80%;margin:0 auto">
		<div class="col-3 home-icon">
			<a href="<%=request.getContextPath()%>/reservation/findByPage.do?action=listStores&ACC_STATUS=1">
				<div class="icon-border">
					<img src="../image/confrontation.png">
				</div>
			</a>
		</div>
		
		<div class="col-3 home-icon">
			<a href="<%=request.getContextPath()%>/front-end/shop/homepage.jsp">
				<div class="icon-border">
					<img src="../image/shopping-bag.png">
				</div>
			</a>
		</div>
			
		<div class="col-3 home-icon">
			<a href="<%=request.getContextPath()%>/front-end/group/select_page.jsp">
				<div class="icon-border">
					<img src="../image/join.png">
				</div>
			</a>
		</div>
		
		<div class="col-3 home-icon">
			<a href="<%=request.getContextPath()%>/front-end/forum/viewForum.jsp">
				<div class="icon-border">
					<img src="../image/discussion.png">
				</div>
			</a>
		</div>
			
		<div class="col-3 icon-name">
			預約
		</div>
		<div class="col-3 icon-name">
			商城
		</div>
		<div class="col-3 icon-name">
			揪團
		</div>
		<div class="col-3 icon-name">
			討論區
		</div>
			
	</div>
	
</div>

<div style="background-color:#000000; height:100px; margin-top:130px;color:#fff">
	<div class="home-bottom">
		<span><a href="<%=request.getContextPath()%>/front-end/storeEvent/select_all.jsp">店家活動</a></span> 
		<span><a href="<%=request.getContextPath()%>/front-end/shopEvent/select_page.jsp">商城活動</a></span> 
		<span><a href="<%=request.getContextPath()%>/front-end/news/select_new.jsp">最新消息</a></span> 
		<span><a href="<%=request.getContextPath()%>/front-end/news/select_announcement.jsp">系統公告</a></span> 
		<span><a href="<%=request.getContextPath()%>/front-end/QA/QA_frontend.html">Q&A</a></span>
	</div>
</div>

</body>
</html>