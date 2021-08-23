<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<title>會員資料新增</title>

<style>
body{
	padding: 0;
	margin: 0;
/* 	background: url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg"); */
	background-size: cover;
	background-attachment: fixed;
	background-position: center; 
}
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
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>

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
    <div class="col-2">
    </div>
    <div class="col-8">
   	<div class="login">
	    <div class="row">
	    	<div class="col-1">
	    	</div>
	    	<div class="col-10">
				<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/report/reportServlet">
						<h1><font color=white>新增檢舉</font></h1>
						</br>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>會員編號</font></label>
							<input class="form-control" type="TEXT" placeholder="請輸入會員編號" name="memid" size="45" value="${param.memid}" />
						</div>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>被檢舉會員編號</font></label>
							<input class="form-control" type="TEXT" placeholder="請輸入被檢舉會員編號" size="45" name="memidReported" value="${param.memidReported}" />
						</div>

						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>檢舉文字內容</font></label>
							<input class="form-control" type="TEXT" placeholder="請輸入檢舉文字內容" name="content" size="45" value="${param.content}" />
						</div>
						
						<input type="hidden" name="action" value="insert"> 
						<div class="mb-3">
							<button type="submit"  class="btn btn-primary">送出</button>					
						</div>
				</FORM>	
	    	</div>
	    	<div class="col-1">
	    	</div>
	    	</div>
	    </div>
    </div>
    <div class="col-2">
    </div>
  </div>
</div>

</body>
</html>
</body>	
</head>
</body>
</html>