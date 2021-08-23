<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<link rel="stylesheet" href="style.css">
<title>一般會員登入</title>

<style>
.login {
	background-color: rgba(13, 11, 12, 0.5);
	border: 0px solid white;
	text-align: center;
	border-radius: 20px 20px 20px 20px;
	font-size: 25px;
	margin: auto;
	font-style: "微軟正黑體", cursive;
	line-height: 40px;
}
</style>
<body>
</head>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div class="container">
	<div class="row align-items-center">
		<div class="col-5"></div>
		<div class="col-2">
			<div class="login">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/LoginServlet">
					</br> <font color=white>註冊成功，請至信箱收信</font>
				</FORM>
			</div>
		</div>
		<div class="col-5"></div>
	</div>
</div>

</body>
</html>