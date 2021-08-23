<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<link rel="stylesheet" href="/CFA101G5/front-end/store/style.css">
<title>店家會員登入</title>

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
</head>
<body>

<%-- 錯誤表列
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if> --%>


<div class="container">
	<div class="row align-items-center">
		<div class="col-5"></div>
		<div class="col-2">
			<div class="login">

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/login/LoginServlet">
					<h1>
						<font color=white>Login</font>
					</h1>
					</br> <font color=white>帳號</font> 
					<input class="form-control" type="text" placeholder="請輸入帳號" name="acc" size="5" value="zhuoNong97" /> ${errorMsgs} <br />
					<font color=white>密碼</font> 
					<input class="form-control" type="password" placeholder="請輸入密碼" size="5" name="pwd" value="drazhN97" /> ${errorMsgs} 
					<input class="form-control" type="hidden" name="action" value="store">
					</br>
					<button type="submit" value="登入" class="btn btn-primary">登入</button>
					<a class="btn btn-primary" href="addStore.jsp">註冊</a>
			</FORM>	
    	</div>
    </div>
    <div class="col-5">
    </div>
  </div>
</div>

</body>
</html>
	