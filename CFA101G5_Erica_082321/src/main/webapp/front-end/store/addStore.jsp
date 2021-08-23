<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<title>店家會員資料新增</title>

<style>
body {
	padding: 0;
	margin: 0;
	background:
		url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg");
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
		<div class="col-2"></div>
		<div class="col-8">
			<div class="login">
				<div class="row">
					<div class="col-1"></div>
					<div class="col-10">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet">
							<h1>
								<font color=white>店家會員資料新增</font>
							</h1>
							</br>
							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>帳號 </font></label> 
								<input class="form-control" type="text" placeholder="請輸入帳號" name="storeAcc" size="45" value="${param.storeAcc}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>密碼</font></label> 
								<input class="form-control" type="password" placeholder="請輸入密碼" name="storePwd" size="45" value="${param.storePwd}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>店家名稱</font></label> 
								<input class="form-control" type="text" placeholder="請輸入店家名稱" name="storeName" size="45" value="${param.storeName}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>統一編號</font></label> 
								<input class="form-control" type="text" placeholder="請輸入統一編號" name="storeGui" size="45" value="${param.storeGui}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>負責人</font></label> 
								<input class="form-control" type="text" placeholder="請輸入負責人" name="storePic" size="45" value="${param.storePic}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>電話</font></label> 
								<input class="form-control" type="text" placeholder="請輸入電話" name="storeTel" size="45" value="${param.storeTel}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>傳真</font></label> 
								<input class="form-control" type="text" placeholder="請輸入傳真" name="storeFax" size="45" value="${param.storeFax}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>登記地址</font></label> 
								<input class="form-control" type="text" placeholder="請輸入登記地址" name="storeAdd" size="45" value="${param.storeAdd}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>聯絡人</font></label> 
								<input class="form-control" type="text" placeholder="請輸入聯絡人" name="storePoc" size="45" value="${param.storePoc}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>連絡電話</font></label> 
								<input class="form-control" type="text" placeholder="請輸入連絡電話" name="storeConPhone" size="45" value="${param.storeConPhone}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>聯絡地址</font></label> 
								<input class="form-control" type="text" placeholder="請輸入聯絡地址" name="storeConAdd" size="45" value="${param.storeConAdd}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>信箱</font></label> 
								<input class="form-control" type="text" placeholder="請輸入信箱" name="storeEmail" size="45" value="rely2021@gmail.com" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>轉帳帳號</font></label> 
								<input class="form-control" type="text" placeholder="請輸入轉帳帳號" name="bankAccount" size="45" value="${param.bankAccount}" />
							</div>

							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label"><font color=white>可容納人數</font></label> 
								<input class="form-control" type="text" placeholder="請輸入可容納人數" name="capacity" size="45" value="${param.capacity}" />
							</div>

							<div class="dropdown">
								<select name="dayOff" class="form-select" aria-label="Default select example" >
								
								
								
								  <option selected>請選擇公休日</option>
								  <option value="1">星期一</option>
								  <option value="2">星期二</option>
								  <option value="3">星期三</option>
								  <option value="1">星期四</option>
								  <option value="2">星期五</option>
								  <option value="3">星期六</option>
								  <option value="3">星期日</option>
								</select>
							</div>
							</br>
<!-- 								<label for="exampleFormControlInput1" class="form-label"><font color=white>公休日</font></label> -->
<!-- 								<button class="btn btn-secondary dropdown-toggle" type="button" name="dayOff" data-bs-toggle="dropdown" aria-expanded="false">請選擇公休日</button> -->
<!-- 								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1"> -->
<!-- 									<li><a class="dropdown-item" href="#">星期一</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期二</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期三</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期四</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期五</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期六</a></li> -->
<!-- 									<li><a class="dropdown-item" href="#">星期日</a></li> -->
<!-- 								</ul> -->
							<div class="dropdown">
								<select class="form-select" aria-label="Default select example" name=startTime>
								  <option selected>請選擇營業開始時段</option>
								  <option value="1">01:00</option>
								  <option value="2">02:00</option>
								  <option value="3">03:00</option>
								  <option value="4">04:00</option>
								  <option value="5">05:00</option>
								  <option value="6">06:00</option>
								  <option value="7">07:00</option>
								  <option value="8">08:00</option>
								  <option value="9">09:00</option>
								  <option value="10">10:00</option>
								  <option value="11">11:00</option>
								  <option value="12">12:00</option>
								  <option value="13">13:00</option>
								  <option value="14">14:00</option>
								  <option value="15">15:00</option>
								  <option value="16">16:00</option>
								  <option value="17">17:00</option>
								  <option value="18">18:00</option>
								  <option value="19">19:00</option>
								  <option value="20">20:00</option>
								  <option value="21">21:00</option>
								  <option value="22">22:00</option>
								  <option value="23">23:00</option>
								  <option value="24">24:00</option>
								</select>
								</br>
								<select class="form-select" aria-label="Default select example" name="endTime">
								  <option selected>請選擇營業結束時段</option>
								 <option value="1">01:00</option>
								  <option value="2">02:00</option>
								  <option value="3">03:00</option>
								  <option value="4">04:00</option>
								  <option value="5">05:00</option>
								  <option value="6">06:00</option>
								  <option value="7">07:00</option>
								  <option value="8">08:00</option>
								  <option value="9">09:00</option>
								  <option value="10">10:00</option>
								  <option value="11">11:00</option>
								  <option value="12">12:00</option>
								  <option value="13">13:00</option>
								  <option value="14">14:00</option>
								  <option value="15">15:00</option>
								  <option value="16">16:00</option>
								  <option value="17">17:00</option>
								  <option value="18">18:00</option>
								  <option value="19">19:00</option>
								  <option value="20">20:00</option>
								  <option value="21">21:00</option>
								  <option value="22">22:00</option>
								  <option value="23">23:00</option>
								  <option value="24">24:00</option>
								</select>
							</div>

							
					</div>
<!-- concat(repeat('0', 開始), repeat('1' , 結束 - 開始), repeat('0', 24 - 結束)) -->
					<input type="hidden" name="action" value="insert"> 
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
